package unittest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dataHandler.DataMaster;
import dataHandler.IDataMaster;

public class TestFileClasses {

	ArrayList<String> refVal;

	@Before
	public void setup() {
		IDataMaster m = DataMaster.getInstance();

		refVal = m.getRefValue().getMilitaryZipRanges();
	}

	@Test
	public void test() {
		assertEquals(3, refVal.size());

		for (String s: refVal){
			System.out.println(s);
		}
	}

	@Test
	public void test2() {
		assertEquals(3, refVal.size());

	}

}
