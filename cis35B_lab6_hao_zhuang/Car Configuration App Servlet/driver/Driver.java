package driver;
import adapter.*;
import util.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;
import client.CarConfigSocketClient;

class Driver
{
	
public static void main(String [] args) throws IOException, ClassNotFoundException
{
	CarConfigSocketClient client = new CarConfigSocketClient(4444,InetAddress.getLocalHost().getHostName());
	client.start();
}

}

