package com.sudowrestlers.chatapi;

import com.sudowrestlers.chatapi.persistence.MessageDAO;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by student on 4/13/17.
 */
@Path("/delete")
public class DeleteMessage {

    @GET
    @Transactional
    public Response createPodcastFromApplicationFormURLencoded(
            @QueryParam("messageID") String messageID) {

        MessageDAO dao = new MessageDAO();
        int ID = Integer.parseInt(messageID);

        int status = dao.deleteMessage(ID); //= DAO Insert Message
        if (status == -1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)// 400
                    .entity("Message does not exist. ").build();
        } else if (status == 0) {
            return Response
                    .status(Response.Status.BAD_REQUEST)// 400
                    .entity("Error, message not deleted. ").build();
        } else {
            return Response
                    .status(Response.Status.CREATED)// 201
                    .entity("Message was deleted.").build();
        }
    }


}

