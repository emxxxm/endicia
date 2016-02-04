package atfImplementation.PMECommitment;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Commitment")
public class Commitment {
	private int commitmentRank, preferredIndicator, serviceStd, deliveryTime; //cutOffTime; //TODO cutoff time is needed for final output
	private String commitmentDate;

	public Commitment(int commitmentRank, int preferredIndicator, int serviceStd, int deliveryTime,
			String commitmentDate) {
		this.commitmentRank = commitmentRank;
		this.preferredIndicator = preferredIndicator;
		this.serviceStd = serviceStd;
		this.deliveryTime = deliveryTime;
		this.commitmentDate = commitmentDate;
		//TODO this.cutOffTime = ?;
	}

	public String printCommitment() {
		String commitmentString = "";
		commitmentString += "<commitmentRank>" + commitmentRank + "</commitmentRank>";
		commitmentString += "<commitmentDate>" + commitmentDate + "</commitmentDate>";
		commitmentString += "<deliveryTime>" + deliveryTime + "</deliveryTime>";
		commitmentString += "<serviceStd>" + serviceStd + "</serviceStd>";
		commitmentString += "<preferredIndicator>" + preferredIndicator + "</preferredIndicator>";
		return commitmentString;
	}
	
	@Override
	public String toString() {
		return printCommitment();
	}

	public int getCommitmentRank() {
		return commitmentRank;
	}

	public int getPreferredIndicator() {
		return preferredIndicator;
	}

	public int getServiceStd() {
		return serviceStd;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public String getCommitmentDate() {
		return commitmentDate;
	}

	public void setCommitmentRank(int commitmentRank) {
		this.commitmentRank = commitmentRank;
	}

	public void setPreferredIndicator(int preferredIndicator) {
		this.preferredIndicator = preferredIndicator;
	}

	public void setServiceStd(int serviceStd) {
		this.serviceStd = serviceStd;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public void setCommitmentDate(String commitmentDate) {
		this.commitmentDate = commitmentDate;
	}
}
