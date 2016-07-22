package main.src.java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import main.src.java.ArchiveStatusHandler;
 
@Path("/status")
public class CallBackClass {
	
	ArchiveStatusHandler archiveStatusObject = ArchiveStatusHandler.getInstance(); 

	@POST
	@Path("/handle")
	@Consumes(MediaType.APPLICATION_JSON)	
	public void consumeIncomingData(InputStream incomingData) throws JSONException {
		
		StringBuilder serviceBuilder = new StringBuilder();
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				serviceBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		
		String dataParsed = serviceBuilder.toString();
		//System.out.println("Data Received: " + dataParsed);
		
		JSONParser jParser = new JSONParser();
		
		try {
			
			JSONObject jObject = (JSONObject) jParser.parse(dataParsed);
			
			//String sessionID = (String) jObject.get("sessionId");
			//String archiveStatus = (String) jObject.get("status");
			
			//System.out.println("Status Received: " + archiveStatus);
			//System.out.println("SessionID Received: " + sessionID);
			
			archiveStatusObject.updateArchiveDB(dataParsed);	
			archiveStatusObject.showAllData();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	} 
	
	@GET
	@Path("/show")
	@Produces(MediaType.TEXT_PLAIN)
	public Response returnAllArchiveData() throws ParseException {
						
		String result = archiveStatusObject.returnAllArchiveData();
		
		if(result.isEmpty()){
			result = "No data present";
		}

		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/clear")
	@Produces(MediaType.TEXT_PLAIN)
	public Response clearArchiveData() {
		
		String result = "All data cleared";
		
		archiveStatusObject.clearArchiveData();
		
		return Response.status(200).entity(result).build();
	}
}