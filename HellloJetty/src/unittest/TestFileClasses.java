package unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataHandler.DataMaster;
import dataHandler.IDataMaster;
import junit.framework.TestCase;

public class TestFileClasses{

	static ArrayList<String> refVal;
	
	@BeforeClass
	public static void setUp() {
		IDataMaster m = DataMaster.getInstance();
		refVal = m.getRefValue().getMilitaryZipRanges();
	}

	@Test
	public void test() {
		System.out.println(refVal);
		assertEquals(3, refVal.size());
		assertEquals(3, refVal.size());

//		for (String s: refVal){
//			System.out.println(s);
//		}
	}

	@Test
	public void test2() {
		System.out.println(refVal);
		assertEquals(3, refVal.size());

	}
	
	@Test
	public void test3() {
		System.out.println(refVal);
		assertEquals(3, refVal.size());

	}

}
