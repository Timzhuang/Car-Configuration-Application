package driver;
import adapter.*;
import model.Automobile;
import util.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import server.*;

class Driver
{
	
public static void main(String [] args) throws IOException, ClassNotFoundException
{
	Server server = new Server();
	server.runServer();
}

}

