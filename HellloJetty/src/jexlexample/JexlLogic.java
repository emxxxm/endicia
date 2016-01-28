package jexlexample;

import java.util.ArrayList;

import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;

public class JexlLogic {

	public static boolean evalHFPU(String mailClass) throws NumberFormatException, CalculationNotPossibleException {
		if (Integer.parseInt(QueryStrings.mapMailClassToInt(mailClass)) < 4) {
			return true;
		} else {
			return false;
		}
	}

	public static int evalTime(String originZip, String destZip) {
		int originInt = Integer.parseInt(originZip), destInt = Integer.parseInt(destZip);
		return Math.abs(originInt - destInt);
	}
	
	public static ArrayList<String> getLocationWithinRadius(String location, String radius) {
		ArrayList<String> locs = new ArrayList<String>();
		
		locs.add("Mountain View");
		locs.add("San Francisco");
		
		return locs;
	}
}
