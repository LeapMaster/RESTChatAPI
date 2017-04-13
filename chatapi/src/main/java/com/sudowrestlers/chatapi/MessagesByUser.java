package com.sudowrestlers.chatapi;

import com.google.gson.GsonBuilder;
import com.sudowrestlers.chatapi.entity.Message;
import com.sudowrestlers.chatapi.persistence.MessageDAO;
import org.json.JSONArray;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by student on 3/30/17.
 */

@Path("/messagesbyuser")
public class MessagesByUser {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getAllMessages(@QueryParam("userID") int userID) {

        MessageDAO dao = new MessageDAO();
        List<Message> messageArrayList = dao.getMessagesByUser(userID);

        String output = "";
        output = new GsonBuilder().setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz")
                .create()
                .toJson(messageArrayList);

        if (!(messageArrayList.size() > 0)) {
            output = "This user has written no messages.";
        }

        return Response.status(200).entity(output).build();





    }



}
