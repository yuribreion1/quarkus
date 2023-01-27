import java.util.LinkedList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

@Path("/example")
@Produces("text/plain")
public class ExampleResource {

    private final LinkedList<Long> list = new LinkedList<>();

    @Inject
    MeterRegistry registry;

    @GET
    @Path("prime/{number}")
    public String checkIfPrime(@PathParam("number") Long number) {
        if (number < 1) {
            registry.counter("example.prime.number", "type", "not natural").increment();
            return "Only natural numbers can be prime numbers";
        }
        if (number == 1) {
            registry.counter("example.prime.number","type", "one").increment();
            return number + " is not prime";
        }
        if (number == 2 || number % 2 == 0) {
            registry.counter("example.prime.number","type","even").increment();
            return number + " is not prime";
        }
        if (timedTestPrimeNumber(number)) {
            registry.counter("example.prime.number","type","prime").increment();
            return number + " is prime!";
        } else {
            registry.counter("example.prime.number","type","not prime").increment();
            return number + " is not prime";
        }
    }

    protected boolean timedTestPrimeNumber(Long number) {
        Timer.Sample sample = Timer.start(registry);
        boolean result = testPrimeNumber(number);
        sample.stop(registry.timer("example.prime.number.test", "prime", result + ""));
        return result;
    }

    protected boolean testPrimeNumber(Long number) {
        for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
