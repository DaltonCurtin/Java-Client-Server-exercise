package chatClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client 
{
	private String host;
	private int port;
	
	//private Scanner userInput;
	private PrintWriter  output;
	
	private Socket socket;
	
	public Client(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	
	public void runClient()
	{
		try {
			
			socket  = new Socket(host, port);
			
			//System.out.println(socket.isConnected());
			//System.out.println(socket.isBound());
			//System.out.println(socket.getLocalAddress());
			//System.out.println(socket.getLocalPort());
			//System.out.println(socket.getPort());
			
			output	= new PrintWriter(socket.getOutputStream(), true);
			Scanner input = new Scanner(System.in);
			String userInput;
			
			new ClientInputThread(socket).start();
			
			
			do
			{
				userInput = input.nextLine();
				
				output.println(userInput);
				
			}while(!(userInput.contains("Exit")));
			input.close();
		} catch (UnknownHostException e1) {
			System.out.println("Unknown Host, check ip/port.");
			e1.printStackTrace();
		} catch (IOException e1) {
			System.out.println("Server disconected or can not be reached.");
			
		}
		
		
	
	}

}
