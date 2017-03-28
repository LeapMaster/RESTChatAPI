package com.sudowrestlers.chatapi;

import com.sudowrestlers.chatapi.entity.Message;
import com.sudowrestlers.chatapi.persistence.MessageDAO;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by student on 3/23/17.
 */
@Path("/add")
public class AddMessage {

    @PUT

    @Produces("text/plain")
    @Transactional
    public Response createPodcastFromApplicationFormURLencoded(
            @QueryParam("message") String message) {

        MessageDAO dao = new MessageDAO();


        Long newMessageIDLong = dao.createMessage(message); //= DAO Insert Message
        int newMessageID;
        if (newMessageIDLong == -1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)// 400
                    .entity("Message was not inserted. ")
                    .header("Location",
                            "#").build();
        } else {
            newMessageID = newMessageIDLong.intValue();
            return Response
                    .status(Response.Status.CREATED)// 201
                    .entity("A new was created with id "
                            + newMessageID)
                    .header("Location",
                            "(URI of message)"
                                    + String.valueOf(newMessageID)).build();
        }

    }
}
