package unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import atfImplementation.PMECommitment.APOFPODPOSubroutine;
import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import junit.framework.TestCase;

public class TestFileClasses{

	static ArrayList<String> refVal;
	static ArrayList<String> holidays;
	
	@BeforeClass
	public static void setUp() {
		IDataMaster m = DataMaster.getInstance();
		
		//For testMilitaryZipRanges
		refVal = m.getRefValue().getMilitaryZipRanges();
		holidays = m.getRefValue().getHolidays();
	}

	@Test
	public void testMilitaryZipRanges() {
		assertEquals(3, refVal.size());
		for (String s: refVal){
			System.out.println(s);
		}
	}

	@Test
	public void testSingletonReuseability() {
		assertEquals(3, refVal.size());
	}
	
	@Test
	public void test2() {
		System.out.println(refVal);
		assertEquals(103, holidays.size());


	}
	
	@Test
	public void temporaryTestAPOFPODPOsubroutine() {
		//APOFPODPOSubroutine afd = new APOFPODPO();
//		afd.
	}

}
