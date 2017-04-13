package com.sudowrestlers.chatapi.service;

import com.sudowrestlers.chatapi.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/users")
public class UserResource
{
    private Map<Integer, User> userDB = new ConcurrentHashMap<Integer, User>();
    private AtomicInteger idCounter = new AtomicInteger();

    public UserResource()
    {
    }

    @POST
    @Consumes("application/xml")
    public Response createUser(User user)
    {
        user.setUserId(idCounter.incrementAndGet());
        //user.put(user.getUserId(), user);
        System.out.println("Created user " + user.getUserId());
        return Response.created(URI.create("/users/" + user.getUserId())).build();

    }

    @GET
    @Path("{id}")
    @Produces("application/xml")
    public User getUser(@PathParam("id") int userId)
    {
        User user = userDB.get(userId);
        if (user == null)
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }

    @PUT
    @Path("{id}")
    @Consumes("application/xml")
    public void updateUser(@PathParam("id") int id, User update)
    {
        User current = userDB.get(id);
        if (current == null) throw new WebApplicationException(Response.Status.NOT_FOUND);

        current.setUser(update.getUsername());

    }
}