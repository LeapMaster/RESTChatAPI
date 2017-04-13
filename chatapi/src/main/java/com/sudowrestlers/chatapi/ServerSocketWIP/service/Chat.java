package com.sudowrestlers.chatapi.ServerSocketWIP.service;
//
//import com.sudowrestlers.chatapi.entity.Message;
//
//import javax.ws.rs.*;
//import javax.ws.rs.container.AsyncResponse;
//import javax.ws.rs.container.Suspended;
//import javax.ws.rs.core.*;
//import java.net.URI;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * @author Student
// */
//@Path("chat")
public class Chat
{
//
//
//    protected Message first;
//    protected Message last;
//    protected int maxMessages = 10;
//    protected LinkedHashMap<String, Message> messages = new LinkedHashMap<String, Message>()
//    {
//        @Override
//        protected boolean removeEldestEntry(Map.Entry<String, Message> eldest)
//        {
//            boolean remove = size() > maxMessages;
//            if (remove) first = eldest.getValue().next;
//            return remove;
//        }
//    };
//
//    protected AtomicLong counter = new AtomicLong(0);
//
//    LinkedList<AsyncResponse> listeners = new LinkedList<AsyncResponse>();
//
//    ExecutorService writer = Executors.newSingleThreadExecutor();
//
//    @Context
//    protected UriInfo uriInfo;
//
//    @POST
//    @Consumes("text/plain")
//    public void post(final String text)
//    {
//        final UriBuilder base = uriInfo.getBaseUriBuilder();
//        writer.submit(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                synchronized (messages)
//                {
//                    Message message = new Message();
//                    message.ID = Integer.parseInt(Long.toString(counter.incrementAndGet()));
//                    message.message = text;
//
//                    if (messages.size() == 0)
//                    {
//                        first = message;
//                    }
//                    else
//                    {
//                        last.next = message;
//                    }
//                    messages.put(String.valueOf(message.ID), message);
//                    last = message;
//
//                    for (AsyncResponse async : listeners)
//                    {
//                        try
//                        {
//                            send(base, async, message);
//                        }
//                        catch (Exception e)
//                        {
//                            e.printStackTrace();
//                        }
//                    }
//                    listeners.clear();
//                }
//            }
//        });
//    }
//
//    @GET
//    public void receive(@QueryParam("current") String next, @Suspended AsyncResponse async)
//    {
//        final UriBuilder base = uriInfo.getBaseUriBuilder();
//        Message message = null;
//        synchronized (messages)
//        {
//            Message current = messages.get(next);
//            if (current == null) message = first;
//            else message = current.next;
//
//            if (message == null) {
//                queue(async);
//            }
//        }
//        // do this outside of synchronized block to reduce lock hold time
//        if (message != null) send(base, async, message);
//    }
//
//    protected void queue(AsyncResponse async)
//    {
//        listeners.add(async);
//    }
//
//    protected void send(UriBuilder base, AsyncResponse async, Message message)
//    {
//        URI nextUri = base.clone().path(Chat.class)
//                .queryParam("current", message.ID).build();
//        Link next = Link.fromUri(nextUri).rel("next").build();
//        Response response = Response.ok(message.message, MediaType.TEXT_PLAIN_TYPE).links(next).build();
//        async.resume(response);
//    }
}
