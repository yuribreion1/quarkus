import io.smallrye.mutiny.Uni;
import model.Fact;
import model.Facts;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import service.FactService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/cat")
public class FactResource {

    @RestClient
    FactService factService;

    @Operation(summary = "Get fact of cats", description = "")
    @GET
    @Path("/fact")
    public Uni<Fact> getCatFact() {
        return factService.getFact();
    }

    @GET
    @Path("/facts")
    public Facts getCatFacts() {
        return factService.getFacts();
    }
}
