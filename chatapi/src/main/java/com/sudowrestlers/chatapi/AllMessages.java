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


/**
 * Created by student on 3/30/17.
 */

@Path("/all")
public class AllMessages {

    @GET
    @Produces("text/plain")
    public Response getAllMessages() {

        MessageDAO dao = new MessageDAO();

        


    }

}
