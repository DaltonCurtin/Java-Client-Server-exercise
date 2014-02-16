package chatClient;

import java.io.*;
import java.net.*;

public class ClientInputThread extends Thread {
	private Socket socket;
	private BufferedReader input;
	
	public ClientInputThread(Socket socket)
	{
		this.socket = socket;
	}
	
	public void run()
	{
	try {
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		while(true)
				{
					String returnString = input.readLine();
					System.out.println(returnString);
				}
		} 
	catch (IOException e) 
		{
			System.out.println("Host can not be reached");
			//System.out.println(e.getMessage());
			System.exit(0);
		}
		
	}
}
