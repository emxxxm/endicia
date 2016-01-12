package dataHandler;

public abstract class AbsDataMaster {
	APOFPODPOData d;
	
	public AbsDataMaster() {
		d = new APOFPODPOData();
	}
	
	public APOFPODPOData getAPOFPODPOData() {
		return d;
	}
	
}
