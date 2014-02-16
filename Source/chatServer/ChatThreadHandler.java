package chatServer;

import java.io.*;
import java.net.*;
//import java.net.Socket;



public class ChatThreadHandler extends Thread
{
	// The server socket.
	private static ServerSocket serverSocket = null;
	// The client socket.
	private static Socket clientSocket = null;

	// This chat server can accept up to maxClientsCount clients' connections.
	private static final int maxClientsCount = 20;
	private static final ClientThread[] threads = new ClientThread[maxClientsCount];

	final int portNumber;
	
	ChatThreadHandler(int port)
	{
		this.portNumber = port;
	}
	
	public void run()
	{
		try{
			serverSocket = new ServerSocket(portNumber);
			
		} catch (IOException e)
		{
			System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		//Thread Counter
		
		while(true)
		{
			try {
				clientSocket = serverSocket.accept();
				int threadCount = 0;
				for (threadCount = 0; threadCount < maxClientsCount; threadCount++) 
					{
			          if (threads[threadCount] == null) 
			          {
			            (threads[threadCount] = new ClientThread(clientSocket, threads)).start();
			            break;
			          }
			        }
				if(threadCount > maxClientsCount)
				{
					PrintStream os = new PrintStream(clientSocket.getOutputStream(), true);
			          os.println("Server too busy. Try later.");
			          os.close();
			          clientSocket.close();
				}
				
				
				
			} catch (IOException e) {
				System.out.println("Exception caught trying to connect to a client.");
				e.printStackTrace();
			}
			
		}
	}
	

}
