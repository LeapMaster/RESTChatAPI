
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer
{
    private ArrayList<PrintWriter> clientList;

    public ChatServer()
    {
        try
        {
            clientList=new ArrayList<PrintWriter>();
            ServerSocket ss=new ServerSocket(5000);
            while(true)
            {
                Socket s=ss.accept();
                PrintWriter out=new PrintWriter(s.getOutputStream(),true);
                synchronized(clientList)
                {
                    clientList.add(out);
                }
                BufferedReader in=new BufferedReader(new InputStreamReader(
                        s.getInputStream()));
                Worker w=new Worker(in,out);
                w.start();
            }
        }
        catch(IOException ioe)
        {
            System.out.println(ioe);
        }
    }

    private class Worker extends Thread
    {
        private BufferedReader in;
        private PrintWriter out;
        private String name;

        public Worker(BufferedReader in,PrintWriter out)
        {
            this.in=in;
            this.out=out;
        }

        public void run()
        {
            try
            {
                String line;
                name=line=in.readLine();
                synchronized(clientList)
                {
                    for(int i=0;i<clientList.size();i++)
                    {
                        clientList.get(i).println(name+" has joined the chat. ");
                    }
                }
                while((line=in.readLine())!=null)
                {
                    synchronized(clientList)
                    {
                        for(int i=0;i<clientList.size();i++)
                        {
                            clientList.get(i).println(name+": "+line);
                        }
                    }
                }
                synchronized(clientList)
                {
                    for(int i=0;i<clientList.size();i++)
                    {
                        clientList.get(i).println(name+" has left the chat. ");
                    }
                    clientList.remove(out);
                }
            }
            catch(IOException ioe)
            {
                System.out.println(ioe);
            }
        }
    }

    public static void main(String[] args)
    {
        new ChatServer();
    }
}