package atfImplementation.PMECommitment;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import MainPackage.DateTimeUtilities;

public class BestPMECommitment {
	Commitment bestCommitment;
	int transitTime;
	String EAD;
	public BestPMECommitment(List<Commitment> commitments) throws ParseException {
		
	Comparator<Commitment> commitDateComparator = new Comparator<Commitment>(){
		@Override
		public int compare(Commitment commit1, Commitment commit2){
			String date1 = commit1.getCommitmentDate(), date2 = commit2.getCommitmentDate();
			boolean isEarlier = false;
			try {
				isEarlier = DateTimeUtilities.isDate1BeforeDate2(date1, date2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if( isEarlier){
				return -1;
			}
			else if(date1.equals(date2)){
				return 0;
			}
			else return 1;
		}
	};
	Comparator<Commitment> rankComparator = new Comparator<Commitment>(){
		@Override
		public int compare(Commitment commit1, Commitment commit2){
			if(commit1.getCommitmentRank() > commit2.getCommitmentRank()){
				return 1;
			}
			else if(commit1.getCommitmentRank() == commit2.getCommitmentRank()){
				return 0;
			}
			else return -1;
		}
	};
	Comparator<Commitment> deliveryTimeComparator = new Comparator<Commitment>(){
		@Override
		public int compare(Commitment commit1, Commitment commit2){
			if(commit1.getDeliveryTime() < commit2.getDeliveryTime()){
				return -1;
			}
			if(commit1.getDeliveryTime() == commit2.getDeliveryTime()){
				return 0;
			}
			else return 1;
		}
	};
	//Get commitments from the PMECommitmentSubroutine
	Collections.sort(commitments, commitDateComparator);
	commitments = keepHighest(commitments, commitDateComparator);
	Collections.sort(commitments, rankComparator);
	commitments = keepHighest(commitments, rankComparator);
	commitments = removeNegativeIndicator(commitments);
	commitments = keepHighest(commitments, deliveryTimeComparator);
	bestCommitment = commitments.get(0);
	transitTime = bestCommitment.getServiceStd();
	EAD = bestCommitment.getCommitmentDate();
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

	private List<Commitment> removeNegativeIndicator(List<Commitment> commitments) {
		for(int i = 0; i < commitments.size(); i ++){
			if(commitments.get(i).getPreferredIndicator() < 0){
				commitments.remove(i);
			}
		}
		return commitments;
	}

	public int getTransitTime() {
		return transitTime;
	}
	public String getEAD(){
		return EAD;
	}
	public Commitment getBestCommitment(){
		return bestCommitment;
	}
	
	public List<Commitment> keepHighest(List<Commitment> commitments,  Comparator<Commitment> comparator){
		Commitment lastCommit = commitments.get(0);
		int i = 0;
		for(i = 0; i < commitments.size(); i++){
			if(comparator.compare(commitments.get(i), lastCommit) == 0){
				lastCommit = commitments.get(i);		
			}
			else {			
				break;
			}
		}
		return commitments.subList(0, i);
	}
	

	
}
