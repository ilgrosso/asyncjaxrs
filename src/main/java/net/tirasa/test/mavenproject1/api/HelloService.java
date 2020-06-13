package net.tirasa.test.mavenproject1.api;

import java.util.concurrent.CompletionStage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Service
public interface HelloService {

    @GET
    @Path("/sayHello/{a}")
    @Produces(MediaType.TEXT_PLAIN)
    String sayHello(@PathParam("a") String a);

    @GET
    @Path("/resumeFromFastThread")
    @Produces("text/plain")
    CompletionStage<String> getBookDescriptionResumeFromFastThread();

}
