package com.sudowrestlers.chatapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by student on 4/4/17.
 */
@Path("/listener")
public class MessageListener {
    @GET
    @Produces("text/plain")
    public Response listenForMessages() {
        String output = "test";
        return Response.status(200).entity(output).build();
    }
}
