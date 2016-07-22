package main.src.java;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.simple.parser.ParseException;

public class ArchiveStatusHandler {

	   private static ArchiveStatusHandler singletonObject = new ArchiveStatusHandler();
	     
	   private ArchiveStatusHandler(){}

	   public static ArchiveStatusHandler getInstance(){
	      return singletonObject;
	   }

	    Map<String, String> linkedHashMapObject = new LinkedHashMap<String, String>();
	    
	    Set set = linkedHashMapObject.entrySet();
	    Iterator i = set.iterator();

		int archiveStateChangeCounter = 1;
	    	    	    
	    public void updateArchiveDB(String archivedData){	
	    	String in = "";
	    	in = in.concat("Archive State ").concat(Integer.toString(archiveStateChangeCounter));
	    
	    	linkedHashMapObject.put(in, archivedData);
	    
	    	archiveStateChangeCounter++;
	    }
	    
	    public void showAllData(){	    	
	        for(String key : linkedHashMapObject.keySet()) {
	        	System.out.println(key + ":\t" + linkedHashMapObject.get(key));
	        }
	    }
	           
	    public String returnAllArchiveData() throws ParseException{
	    	
	    	String archiveString = "";
	    	    		    		    	
	        for(String key : linkedHashMapObject.keySet()) {
	        	archiveString = archiveString.concat(key).concat("\n");
	        	archiveString = archiveString.concat(linkedHashMapObject.get(key)).concat("\n\n");
	        }

        	return archiveString;
	    }
	    
	    public void clearArchiveData(){
	    	archiveStateChangeCounter = 1;
	    	linkedHashMapObject.clear();
	    }
	}