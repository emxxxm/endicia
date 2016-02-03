package unittest;

import java.text.ParseException;
import java.util.HashMap;

import org.junit.Test;

import MainPackage.QueryParser;
import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.MainATFImplementation;

public class TestMainLogic {

	@Test //Run through the main logic, if no error is thrown then it passes
	public void testMainLogic() throws NumberFormatException, CalculationNotPossibleException, ParseException {
		HashMap<String, String> q = QueryParser.getFakeQueryPRITuples();
		
		q.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_FCM);
		
		MainATFImplementation mainLogic = new MainATFImplementation(q);
		mainLogic.execute();
		HashMap<String, String> output = mainLogic.getOutput();

		String xmlResp = "<ExpressMail>";
		for (String s: output.keySet()) {
			xmlResp += "<" + s + ">" + output.get(s) + "</" + s + ">"; 
		}
		xmlResp += "</ExpressMail>";
		System.out.println(xmlResp);
	}

	@Test //Run through the main logic, if no error is thrown then it passes
	public void testMainLogic2() throws NumberFormatException, CalculationNotPossibleException, ParseException {
		HashMap<String, String> q = QueryParser.getFakeQueryPRITuples();
		q.put(QueryStrings.DEST_TYPE, "2");
		q.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_FCM);
		
		MainATFImplementation mainLogic = new MainATFImplementation(q);
		mainLogic.execute();
		HashMap<String, String> output = mainLogic.getOutput();

		String xmlResp = "<ExpressMail>";
		for (String s: output.keySet()) {
			xmlResp += "<" + s + ">" + output.get(s) + "</" + s + ">"; 
		}
		xmlResp += "</ExpressMail>";
		System.out.println(xmlResp);
	}
	
	@Test (timeout=1000) //Run through the main logic, if no error is thrown then it passes
	public void testMainLogicPME() throws NumberFormatException, CalculationNotPossibleException, ParseException {
		HashMap<String, String> q = QueryParser.getFakeQueryPRITuples();
		q.put(QueryStrings.DEST_TYPE, "2");
		q.put(QueryStrings.MAIL_CLASS, QueryStrings.MAIL_CLASS_PME);
		
		MainATFImplementation mainLogic = new MainATFImplementation(q);
		mainLogic.execute();
		HashMap<String, String> output = mainLogic.getOutput();

		String xmlResp = "<ExpressMail>";
		for (String s: output.keySet()) {
			xmlResp += "<" + s + ">" + output.get(s) + "</" + s + ">"; 
		}
		xmlResp += "</ExpressMail>";
		System.out.println(xmlResp);
	}
	
	

}
