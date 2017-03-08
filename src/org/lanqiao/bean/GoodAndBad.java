package org.lanqiao.bean;

public class GoodAndBad {
	private int RUId;
	private int IId;

	public int getRUId() {
		return RUId;
	}

	public void setRUId(int rUId) {
		RUId = rUId;
	}

	public int getIId() {
		return IId;
	}

	public void setIId(int iId) {
		IId = iId;
	}

	public GoodAndBad() {
	}

	public GoodAndBad(int rUId, int iId) {
		super();
		RUId = rUId;
		IId = iId;
	}

}
