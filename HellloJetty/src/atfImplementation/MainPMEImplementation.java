package atfImplementation;

import java.util.HashMap;

import atfImplementation.PMECommitment.APOFPODPOSubroutine;

public class MainPMEImplementation extends AbsATFImplementation {
	APOFPODPOSubroutine afd;
	int retrogradeOffset;
	int progradeOffset;
	
	public MainPMEImplementation(HashMap<String, String> q) throws CalculationNotPossibleException {
		super(q);
		afd = new APOFPODPOSubroutine(queryTuples);
	}

	@Override
	public void execute() {
		retrogradeOffset = afd.getRetrogradeOffset();
		progradeOffset = afd.getProgradeOffset();
		
	}

}
