package ApacheMain.outputwrappers;

import java.util.ArrayList;
import java.util.HashMap;

import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.Location;
import atfImplementation.PMECommitment.Commitment;

public class DazzleOutputMain extends AbsDazzleOutput {
	ArrayList<Location> PRIlocationList = null;
	Location HFPULoc = null;
	Commitment commitment = null;

	public DazzleOutputMain(HashMap<String, Object> output) throws CalculationNotPossibleException {
		super(output);


		if (output.get(QueryStrings.LOCATION) != null) {
			PRIlocationList = (ArrayList<Location>) output.get(QueryStrings.LOCATION);
		}

		if (output.get(QueryStrings.destTypeToString(QueryStrings.DESTTYPE_HFPU)) != null) {
			HFPULoc = (Location) output.get(QueryStrings.destTypeToString(QueryStrings.DESTTYPE_HFPU));
		}

		if (output.get(QueryStrings.COMMITMENT) != null) {
			commitment = (Commitment) output.get(QueryStrings.COMMITMENT);
		}

	}

	public ArrayList<Location> getPRIlocationList() {
		return PRIlocationList;
	}

	public Location getHFPULoc() {
		return HFPULoc;
	}

	public Commitment getCommitment() {
		return commitment;
	}

	public void setPRIlocationList(ArrayList<Location> pRIlocationList) {
		PRIlocationList = pRIlocationList;
	}

	public void setHFPULoc(Location hFPULoc) {
		HFPULoc = hFPULoc;
	}

	public void setCommitment(Commitment commitment) {
		this.commitment = commitment;
	}

}
