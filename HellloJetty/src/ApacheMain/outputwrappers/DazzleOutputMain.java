package ApacheMain.outputwrappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import MainPackage.QueryStrings;
import atfImplementation.CalculationNotPossibleException;
import atfImplementation.Location;
import atfImplementation.PMECommitment.Commitment;

@XmlRootElement(name = "MailCommitment")
public class DazzleOutputMain extends AbsDazzleOutput {
	
	@XmlElement(name = "PRILocation")
	LocationList PRILocationList = null;
	//Collection<Location> PRILocationList = null;
	//GenericEntity<ArrayList<Location>> PRILocationList = null;
	//ArrayList<Location> PRIlocationList = null;
	Location HFPULoc = null;
	Commitment commitment = null;

	public DazzleOutputMain() {
		super();
		throw new WebApplicationException("unimplemented");
	}

	public DazzleOutputMain(HashMap<String, Object> output) throws CalculationNotPossibleException {
		super(output);

		if (output.get(QueryStrings.LOCATION) != null) {
			PRILocationList = new LocationList((ArrayList<Location>) output.get(QueryStrings.LOCATION));
		}
		
//		if (output.get(QueryStrings.LOCATION) != null) {
//			PRILocationList = (ArrayList<Location>) output.get(QueryStrings.LOCATION);
//		}

		//		if (output.get(QueryStrings.LOCATION) != null) {
		//			PRILocationList = new GenericEntity<ArrayList<Location>>((ArrayList<Location>) output.get(QueryStrings.LOCATION)){};
		//		}

		if (output.get(QueryStrings.destTypeToString(QueryStrings.DESTTYPE_HFPU)) != null) {
			HFPULoc = (Location) output.get(QueryStrings.destTypeToString(QueryStrings.DESTTYPE_HFPU));
		}

		if (output.get(QueryStrings.COMMITMENT) != null) {
			commitment = (Commitment) output.get(QueryStrings.COMMITMENT);
		}

	}

	public Collection<Location> getPRILocationList() {
		return PRILocationList;
	}
	//	public GenericEntity<ArrayList<Location>> getPRILocationList() {
	//		return PRILocationList;
	//	}

	public Location getHFPULoc() {
		return HFPULoc;
	}

	public Commitment getCommitment() {
		return commitment;
	}

	public void setPRILocationList(LocationList PRILocationList) {
		this.PRILocationList = PRILocationList;
	}

	//	public void setPRILocationList(GenericEntity<ArrayList<Location>> PRILocationList) {
	//		this.PRILocationList = PRILocationList;
	//	}

	public void setHFPULoc(Location hFPULoc) {
		HFPULoc = hFPULoc;
	}

	public void setCommitment(Commitment commitment) {
		this.commitment = commitment;
	}

}
