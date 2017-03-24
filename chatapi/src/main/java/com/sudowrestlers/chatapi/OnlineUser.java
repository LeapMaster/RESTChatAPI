package com.sudowrestlers.chatapi;

import com.sudowrestlers.chatapi.entity.User;
import com.sudowrestlers.chatapi.persistence.UserDAO;
import org.json.JSONArray;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/online")
public class OnlineUser {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getUser() {


        //Return a list of users
        UserDAO dao = new UserDAO();
        List<User> usersArrayList = dao.getAllUsers();
        String output = "";

        JSONArray jsonArrayList = new JSONArray(usersArrayList);
        output = jsonArrayList.toString();

        if (!(usersArrayList.size() > 0)) {
            output = "I have no friends :(";
        }

        return Response.status(200).entity(output).build();
    }

}