package DDTbyjasonfile;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DDTjason {

	public static void main(String[] args) throws IOException, ParseException {
		JSONParser Parser= new JSONParser();
		FileReader file= new FileReader("D:\\AdvanceSel\\src\\test\\resources\\Data_E18.json");
		Object javaobj = Parser.parse(file);
		
		JSONObject obj=(JSONObject)javaobj;
		String name = obj.get("name").toString();
		String id = obj.get("id").toString();// even tough it was number,we just convert to tostring method
		 Object id1 = obj.get("id");// If not Converted to toString() also same value Appears
			String Branch = obj.get("Branch").toString();
			String Age = obj.get("Age").toString();
		String isStudent = obj.get("isStudent").toString();
		  Object isStudent1 = obj.get("isStudent");// If not Converted to toString() also same value Appears
		  //String backlogs = obj.get("backlogs").toString(); //Exception in thread "main" java.lang.NullPointerException: Cannot invoke "Object.toString()" because the return value of "org.json.simple.JSONObject.get(Object)" is null
			//at DDTbyjasonfile.DDTjason.main(DDTjason.java:25)
		 Object backlogs1 = obj.get("backlogs");
		  System.out.println(name);
		  System.out.println(id);
		  System.out.println(id1);// If not Converted to toString() also same value Appears, where it was a object output
		  System.out.println(Branch);
		  System.out.println(Age);
		  System.out.println(isStudent);
		  System.out.println(isStudent1);// If not Converted to toString() also same value Appears ,where it was a object output
		//  System.out.println(backlogs);// if converts to string, null pointer exception occurs
		  System.out.println(backlogs1);
	}

}
