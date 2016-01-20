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
		
		commonIsDestinationHFPUBranch();
	}
	
	//TODO create and run get APO/FPO/DPO data subroutine
	
	//if (destination is of type HFPU) 
		//TODO create and run get HFPU Location subroutine
	//else
		//TODO lookup origin close times from ATF_Address_close
	
	//TODO create and run Calculate PME Commitments subroutine
	
	//TODO create and run Get Best PME Commitment subroutine
	
	//TODO [Drools] execute rules engine for Acceptance Rules
	
	//TODO [Drools] execute rule engine for Transit Time Rules
	
	//TODO Set delivery date based on calculated values
		//What is retrograde offset?
		//What is prograde offset?
	
	//TODO create and run Calculate PME Delivery Date subroutine
	
	//TODO return data back up to ATF main
	

}
