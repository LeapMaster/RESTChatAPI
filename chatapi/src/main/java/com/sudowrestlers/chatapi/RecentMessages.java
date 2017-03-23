package com.sudowrestlers.chatapi;

import com.sudowrestlers.chatapi.entity.Message;
import com.sudowrestlers.chatapi.persistence.MessageDAO;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/recent")
public class RecentMessages {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getMessage() {
//        // Return a simple message
//        String output = "Where are my messages\nI was told there would be messages";
//        return Response.status(200).entity(output).build();

        //Return a list of messages
        MessageDAO dao = new MessageDAO();
        List<Message> messageArrayList = dao.getAllMessages();
        String output = "";
//        for (int index = 0; index < messageArrayList.size(); index++) {
//            Message currentMessage = messageArrayList.get(index);
//            output += currentMessage.toString() + "\n";
//        }
        JSONArray jsonArrayList = new JSONArray(messageArrayList);
        output = jsonArrayList.toString();

        if (!(messageArrayList.size() > 0)) {
            output = "Where are my messages\nI was told there would be messages";
        }

        return Response.status(200).entity(output).build();
    }

}