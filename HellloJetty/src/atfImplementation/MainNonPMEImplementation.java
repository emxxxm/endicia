package atfImplementation;

import java.util.ArrayList;
import java.util.HashMap;

import atfImplementation.nonPMECommitment.PRI_COT;

public class MainNonPMEImplementation extends AbsATFImplementation {

	PRI_COT pri;
	
	public MainNonPMEImplementation(HashMap<String, String> q) {
		super(q);
		pri = new PRI_COT(queryTuples);
	}

	@Override
	public void execute() {
		ArrayList<String> PRIOutput = pri.getPRI_COT();
		
	}

}
