package atfImplementation.PMECommitment;

public class Commitment {
	private int commitmentRank, preferredIndicator, serviceStd, deliveryTime, cutOffTime;
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

	/**
	 * @return the commitmentRank
	 */
	public int getCommitmentRank() {
		return commitmentRank;
	}

	/**
	 * @return the preferredIndicator
	 */
	public int getPreferredIndicator() {
		return preferredIndicator;
	}

	/**
	 * @return the serviceStd
	 */
	public int getServiceStd() {
		return serviceStd;
	}

	/**
	 * @return the deliveryTime
	 */
	public int getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * @return the commitmentDate
	 */
	public String getCommitmentDate() {
		return commitmentDate;
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
}
