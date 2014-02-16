package chatServer;

import java.io.*;
import java.net.*;


public class ClientThread extends Thread
{
	private BufferedReader input = null;

	
	private PrintWriter output;
	
	
	private Socket clientSocket = null;
	private final ClientThread[] threads;
	private int maxClientsCount;
	private String userInput;
	private String userName;
	
	ClientThread(Socket socket, ClientThread[] threads)
	{
		this.clientSocket 	 = socket;
		this.threads		 = threads;
		this.maxClientsCount = threads.length;
	}
	
	public void run()
	{
	try{
		/*instantiates a BufferedReader using InputStreamReader as a liaison 
		 * for the ClientSocket's InputStream; This will allow us to get data from 
		 * the clients
		  */
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
		/*Instantiates a PrinterWriter using clientSocket's 
		OutPutStream to send data to clients
		*/
		output = new PrintWriter(clientSocket.getOutputStream(), true);
		
		output.println("Welcome to the server, please enter your name: ");
		userName = input.readLine();
		broadcast(String.format("\nWelcome %s to the server. ", userName));
		
		do
		{
		
	
					
			userInput = input.readLine();
			
			broadcast(String.format("%s : ",userName) + userInput);
		
		}while(!(userInput.contains("/quit")));
		
		
		
		}catch(IOException e)
		{
			System.out.println("Exception caught when creating input/output streams");
			System.out.println(e.getMessage());
		}
			
	}
	public void broadcast(String input)
	{
		for(int x = 0; x < maxClientsCount; x++)
		{
			if(threads[x] == null)
			{
				break;
			}
			threads[x].output.println(input);
		}
	}

}
