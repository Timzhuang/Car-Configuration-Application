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
import javax.servlet.http.HttpSession;

import model.Automobile;

/**
 * Servlet implementation class ModelOptionsServlet
 */
@WebServlet("/getModelOptions")
public class ModelOptionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModelOptionsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// get the chosen model from the server
		String modelChoice = request.getParameter("modelChoice");
		Automobile auto = getAuto(modelChoice);
		// send the html to the client
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
		writer.println(
				"<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head><title> configure a car </title></head>\n"+
				"<body>\n" +
				"<h1>Make Your Choice</h1>\n" +
				"<form action=\"printOptions\" method=\"POST\">\n" +
				"<TABLE BORDER=\"1\">\n" +
				"<tr><td>Make/Model:</td><td>"+ modelChoice +"</td></tr>\n");
		
		ArrayList<String> optionSetNames = auto.getOptionSetNames();
		// print all the optionSets
		for (int index = 0; index < optionSetNames.size(); index++)
		{
			ArrayList<String> optionNames = auto.getOptionNames(optionSetNames.get(index));
			writer.println(
					"<tr>\n" +
					"<td>"+ optionSetNames.get(index) +"</td>\n" +
					"<td>\n" +
					"<select name=\"option"+ index +"\">\n");
			
			
			// print all the options of the given optionSet
			for (String optName: optionNames)
			{
				writer.println("<option value=\"" + optName + "\">" + optName + "</option>");
			}
			writer.println(
					"</select>\n" +
					"</td>\n" +
					"</tr>\n"
					);
			
		}
		writer.println(		
				"<input type=\"submit\">" +
				"</table>" +
				"</form>\n" +
				"</body>\n" +
				"</html>"
				);
		// store the auto object into the session
		HttpSession session = request.getSession();
		session.setAttribute("auto", auto);
		session.setAttribute("size", optionSetNames.size());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/*
	 * getAuto: get the auto object from the server
	 * input: string modelChoice
	 * output: Automobile object
	 */
	Automobile getAuto(String modelChoice)
	{
		Socket socket = null;
		ObjectOutputStream writer = null;
		ObjectInputStream reader = null;
		Automobile auto = null;
		
		try {
			// connect to the server
			socket = new Socket(InetAddress.getLocalHost().getHostName(),4444);
			writer = new ObjectOutputStream( socket.getOutputStream() );
			reader = new ObjectInputStream( socket.getInputStream() );
			// request model list
			writer.writeObject("send auto");
			writer.writeObject(modelChoice);
			// receive model list
			auto = (Automobile) reader.readObject();
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
		return auto;
	}
}
