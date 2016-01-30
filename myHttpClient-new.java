import java.io.*;
import java.net.*;
import java.util.Random;

public class myHttpClient 
{
	public static char getRandomChar() 
	{
		Random generator = new Random();
		int character = generator.nextInt();
		return(char) character;
	}
	
	public static int getRandomValue() 
	{
		Random generator = new Random();
		int character = generator.nextInt();
		return character;
	}
	
	public static void main(String[] args) 
	{
		try 
		{
			URL url = new URL("http://localhost:12002");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setUseCaches(false);
			//String test = "<name>Hello</name>";
			int lengthOfStr=getRandomValue();
			String test="";
			for (int i=0 ; i<lengthOfStr ; i++) 
			{
				test+=getRandomChar();
			}
			byte[] bytes = test.getBytes();
			con.setRequestProperty("Content-length", String.valueOf(bytes.length));
			con.setRequestProperty("Content-type", "text/html");
			OutputStream out = con.getOutputStream();
			out.write(bytes);
			out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String temp;
			while((temp = in.readLine()) != null)
				System.out.println(temp);
			out.close();
			in.close();
			con.disconnect();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}