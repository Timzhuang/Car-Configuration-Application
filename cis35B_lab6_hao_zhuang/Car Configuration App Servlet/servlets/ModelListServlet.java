package servlets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.Socket;
/**
 * Servlet implementation class ModelListServlet
 */
@WebServlet("/ModelListServlet")
public class ModelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModelListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> modelList = getModelList();
		
		// send the html to the client
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(
				"<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head><title> send a list of available models to the client </title></head>\n"+
				"<body>\n" +
				"<form action=\"getModelOptions\" method=\"POST\">\n" +
				"Choose one from the list of Available models:<br>\n" +
				"<select name=\"modelChoice\" >");
		// print all model options
		for (String model : modelList)
			writer.println("<option value=\"" + model + "\">" + model + "</option>");
		
		writer.println(
				"</select>\n" +
				"<input type=\"submit\">"+
				"</form>\n" +
				"</body>\n" +
				"</html>"  
				);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/*
	 * getModelList: request the model list from the server
	 */
	public ArrayList<String> getModelList()
	{
		Socket socket = null;
		ObjectOutputStream writer = null;
		ObjectInputStream reader = null;
		ArrayList<String> modelList = null;

		try {
			// connect to the server
			socket = new Socket(InetAddress.getLocalHost().getHostName(),4444);
			writer = new ObjectOutputStream( socket.getOutputStream() );
			reader = new ObjectInputStream( socket.getInputStream() );
			// request model list
			writer.writeObject("send model list");
			// receive model list
			modelList = (ArrayList<String>) reader.readObject();
			// close the connection
			writer = null;
			reader = null;
			socket.close();
		}
		catch (IOException socketError) {
			 System.err.println("Unable to connect to the server");
		}
		catch (Exception e){     
			System.err.println("Unable to obtain stream to/from the server" );     
		}
		
		return modelList;
		
	}

}
