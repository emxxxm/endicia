package atfImplementation.nonPMECommitment;

public class PRI_COT {
	
	public PRI_COT(String ZIP, String EAD){
		//[DataAccess] get USPS_HOL from ATF_REF_VALUE
		
		//if(EAD is a USPS_HOL)
			//DOW = 8
		//else DOW=EAD_DOW
		
		//[DataAccess]Lookup PRI COT for DOW from ATF_COT
		
		//if PRI_COT found, save PRI_COT
		//else 
			//[DataAccess]lookup DEFAULT_COT_PRI for DOW from ATF_REF_VALUE
			//save PRI_COT
		
		//return PRI_COT;
		
		
	}

}
