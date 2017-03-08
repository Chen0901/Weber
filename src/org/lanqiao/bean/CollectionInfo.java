package org.lanqiao.bean;

public class CollectionInfo {
	private int UId;
	private int IId;
	
	public int getUId() {
		return UId;
	}

	public void setUId(int uId) {
		UId = uId;
	}

	public int getIId() {
		return IId;
	}

	public void setIId(int iId) {
		IId = iId;
	}
	
	public CollectionInfo(int uId, int iId) {
		super();
		UId = uId;
		IId = iId;
	}
	
	public CollectionInfo() {
		super();
	}

}
