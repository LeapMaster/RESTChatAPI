package com.sudowrestlers.chatapi;

import com.sudowrestlers.chatapi.entity.Message;
import com.sudowrestlers.chatapi.persistence.MessageDAO;
import org.json.JSONException;
import org.json.JSONObject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by student on 3/23/17.
 */
@Path("/add")
public class AddMessage {

    @POST
    @Consumes("application/json")
    @Transactional
    public Response createPodcastFromApplicationFormURLencoded(String jsonData) {
        JSONObject jsonObject = null;
        String messageValue = "";
        int userID = 0;


        try {
            jsonObject = new JSONObject(jsonData);
            messageValue = jsonObject.getString("message");
            userID = Integer.parseInt(jsonObject.getString("userID"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MessageDAO dao = new MessageDAO();


        int newMessageID = dao.createMessage(messageValue, userID); //= DAO Insert Message
        if (newMessageID == -1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)// 400
                    .entity("Message was not inserted. ").build();
        } else {
            return Response
                    .status(Response.Status.CREATED)// 201
                    .entity("A new message was created with id "
                            + newMessageID).build();
        }
    }


}
