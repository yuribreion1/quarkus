package service;

import io.smallrye.mutiny.Uni;
import model.Fact;
import model.Facts;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@RegisterRestClient(baseUri = "https://catfact.ninja/")
public interface FactService {

    @GET
    @Path("/fact")
    Uni<Fact> getFact();

    @GET
    @Path("/facts")
    Facts getFacts();
}
