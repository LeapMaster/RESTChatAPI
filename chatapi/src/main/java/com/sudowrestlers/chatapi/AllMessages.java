package com.sudowrestlers.chatapi;


import com.sudowrestlers.chatapi.entity.Message;
import com.sudowrestlers.chatapi.persistence.MessageDAO;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by student on 3/30/17.
 */

@Path("/all")
public class AllMessages {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMessages() {

        MessageDAO dao = new MessageDAO();
        List<Message> messageArrayList = dao.getAllMessages();

        String output = "";
        JSONArray jsonArrayList = new JSONArray(messageArrayList);
        output = jsonArrayList.toString();

        if (!(messageArrayList.size() > 0)) {
            output = "Where are my messages\nI was told there would be messages";
        }

        return Response.status(200).entity(output).build();


        


    }

}
