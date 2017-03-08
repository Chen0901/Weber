package org.lanqiao.bean;

public class Follow {
	private int RUId;//关注人
	private int EUId;//被关注人
	public int getRUId() {
		return RUId;
	}
	public void setRUId(int rUId) {
		RUId = rUId;
	}
	public int getEUId() {
		return EUId;
	}
	public void setEUId(int eUId) {
		EUId = eUId;
	}
	public Follow(int rUId, int eUId) {
		super();
		RUId = rUId;
		EUId = eUId;
	}
	
	public Follow(int eUId) {
		super();
		EUId = eUId;
	}
	public Follow() {
		super();
	}
	@Override
	public String toString() {
		return "Follow [RUId=" + RUId + ", EUId=" + EUId + "]";
	}
	
}
