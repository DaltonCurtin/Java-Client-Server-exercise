package chatServer;


import java.util.*;

public class ChatServer 
{
	
	public static void main(String[] args)
	{
		//instantiates a port for the server to run on
		final int port = 1077;
		//runs the ChatThreadHandler on a separate thread
		new ChatThreadHandler(port).start();
		
		
		//instantiates a new scanner for user input
		Scanner terminal = new Scanner(System.in);
		String status;

		//instantiates Sentinel value
		final String Sentinel = "Exit"; 
		
		//waits for command "Exit" to terminate program
		do
		{
			
			System.out.print("Please enter Exit to close: ");
			status = terminal.next();
			if(status.contains(Sentinel))
			{
				break;
			}
		}while(!(status.contains(Sentinel)));
		
		terminal.close();
		System.exit(0);
		
	}
	
	
}
