

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient extends JFrame
{
    private JButton connectButton;
    private JButton sendButton;
    private JTextField nameTextField;
    private JTextField chatTextField;
    private JTextArea jta;
    private BufferedReader in;
    private PrintWriter out;
    private boolean connected=false;

    public ChatClient()
    {
        super("ChatClient");

        ActionHandler ah=new ActionHandler();
        KeyHandler kh=new KeyHandler();

        JPanel mainPanel=new JPanel();
        add(mainPanel,BorderLayout.CENTER);

        JPanel topPanel=new JPanel();
        topPanel.setLayout(new GridLayout(2,1));
        add(topPanel,BorderLayout.NORTH);

        JPanel panel1=new JPanel();
        ((FlowLayout)panel1.getLayout()).setAlignment(FlowLayout.LEFT);
        topPanel.add(panel1);

        panel1.add(new JLabel("Name:"));
        nameTextField=new JTextField(20);
        nameTextField.addKeyListener(kh);
        panel1.add(nameTextField);

        connectButton=new JButton("Connect");
        connectButton.addActionListener(ah);
        connectButton.setEnabled(false);
        panel1.add(connectButton);

        JPanel panel2=new JPanel();
        ((FlowLayout)panel2.getLayout()).setAlignment(FlowLayout.LEFT);
        topPanel.add(panel2);

        panel2.add(new JLabel("Message:"));
        chatTextField=new JTextField(20);
        chatTextField.addKeyListener(kh);
        panel2.add(chatTextField);

        sendButton=new JButton("Send");
        sendButton.setEnabled(false);
        sendButton.addActionListener(ah);
        panel2.add(sendButton);

        jta=new JTextArea();
        jta.setEditable(false);
        JScrollPane jsp=new JScrollPane(jta);
        jsp.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setPreferredSize(new Dimension(500,500));
        mainPanel.add(jsp);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void connect()
    {
        try
        {
            Socket s=new Socket("localhost",5000);
            in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            out=new PrintWriter(s.getOutputStream(),true);
            out.println(nameTextField.getText());
            new Worker().start();
            sendButton.setEnabled(true);
            connected=true;
            connectButton.setText("Disconnect");
            chatTextField.requestFocus();
        }
        catch(IOException ioe)
        {
            System.out.println(ioe);
        }
    }

    public static void main(String[] args)
    {
        new ChatClient();
    }

    private class KeyHandler extends KeyAdapter
    {
        public void keyTyped(KeyEvent e)
        {
            if(e.getSource()==nameTextField)
            {
                connectButton.setEnabled(true);
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                {
                    connect();
                }
            }
            else if(e.getSource()==chatTextField)
            {
                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                {
                    if(out==null) return;
                    out.println(chatTextField.getText());
                    chatTextField.setText("");
                    jta.setCaretPosition(jta.getText().length()-1);
                }
            }
        }
    }

    private class ActionHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==connectButton)
            {
                if(!connected)
                {
                    connect();
                }
                else
                {
                    try
                    {
                        out.close();
                        in.close();
                        sendButton.setEnabled(false);
                        connectButton.setText("Connect");
                        connected=false;
                    }
                    catch(IOException ioe)
                    {
                        System.out.println(ioe);
                    }
                }
            }
            else if(e.getSource()==sendButton)
            {
                if(out==null) return;
                out.println(chatTextField.getText());
                chatTextField.setText("");
                chatTextField.requestFocus();
                jta.setCaretPosition(jta.getText().length()-1);
            }
        }
    }

    private class Worker extends Thread
    {
        public void run()
        {
            try
            {
                String line;
                while((line=in.readLine())!=null)
                {
                    jta.append(line+"\n");
                }
                connected=false;
                connectButton.setEnabled(false);
                sendButton.setEnabled(false);
            }
            catch(IOException ioe)
            {
                System.out.println(ioe);
            }
        }
    }
}