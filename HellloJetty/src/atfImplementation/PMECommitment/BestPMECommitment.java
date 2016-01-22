package atfImplementation.PMECommitment;

import java.util.ArrayList;

import atfImplementation.Commitment;

public class BestPMECommitment {

	public BestPMECommitment(ArrayList<Commitment> commitments) {
	
	//Get commitments from the PMECommitmentSubroutine
	
	//Sort Commitments by Preliminary Date in descending order (earliest date first).
	//Keep only the commitments that share the earliest preliminary date
	
	//Sort commitments by Commitment Rank in descending order
	//Keep only commitments with highest Commitment Rank
	
	//if (any commitments have preferred indicator == -1) 
		//Keep only commitments with preferred indicator == 1 
    
	//Save the first commitment where deliveryTime = Earliest Delivery Time
	
	//transitTime = serviceStandard
	//EAD = commitmentDate
	}

	public int getTransitTime() {
		return 1;
	}
	
}
