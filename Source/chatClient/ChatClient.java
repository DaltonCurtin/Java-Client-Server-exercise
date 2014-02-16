package chatClient;

public class ChatClient 
{
	public static void main(String[] args) 
	{
	String hostName 	= args[0];
	int	   portNumber	= Integer.parseInt(args[1]);
	System.out.println(args[0] + ":" + args[1]);
	Client client = new Client(hostName, portNumber);
	
	client.runClient();
	} 
}


