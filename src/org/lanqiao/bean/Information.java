package org.lanqiao.bean;

import java.util.Date;

/**
 * 
 * @author Administrator
 * @信息实体类
 */

public class Information {
	private int IId; // 信息Id
	private int RUId; // 用户Id
	private int KId; // 类别Id
	private String RUName; // 用户姓名
	private String IContent; // 信息内容
	private String IPicpath; // 图片路径
	private int good; // 点赞
	private int bad; // 踩
	private Date ITime; // 信息发布时间

	public int getIId() {
		return IId;
	}

	public void setIId(int iId) {
		IId = iId;
	}

	public int getRUId() {
		return RUId;
	}

	public void setRUId(int rUId) {
		RUId = rUId;
	}

	public int getKId() {
		return KId;
	}

	public void setKId(int kId) {
		KId = kId;
	}

	public String getRUName() {
		return RUName;
	}

	public void setRUName(String rUName) {
		RUName = rUName;
	}

	public String getIContent() {
		return IContent;
	}

	public void setIContent(String iContent) {
		IContent = iContent;
	}

	public String getIPicpath() {
		return IPicpath;
	}

	public void setIPicpath(String iPicpath) {
		IPicpath = iPicpath;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public Date getITime() {
		return ITime;
	}

	public void setITime(Date iTime) {
		ITime = iTime;
	}

	public Information(String rUName, int iId, int rUId, int kId,
			String iContent, int good, int bad, Date iTime, String iPicpath) {
		super();
		IId = iId;
		RUId = rUId;
		KId = kId;
		RUName = rUName;
		IContent = iContent;
		IPicpath = iPicpath;
		this.good = good;
		this.bad = bad;
		ITime = iTime;
	}

	public Information(int iId) {
		super();
		IId = iId;
	}

	public Information() {
		super();
	}

	@Override
	public String toString() {
		return "Information [IId=" + IId + ", RUId=" + RUId + ", KId=" + KId
				+ ", RUName=" + RUName + ", IContent=" + IContent
				+ ", IPic_path=" + IPicpath + ", good=" + good + ", bad="
				+ bad + ", ITime=" + ITime + "]";
	}

}
