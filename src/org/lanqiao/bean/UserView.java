package org.lanqiao.bean;

import java.util.Date;

/**
 * 
 * @author Administrator
 * @评论实体类
 */

public class UserView {
	private int VId;// 评论Id
	private int RUId;// 用户Id
	private String RUName;// 用户姓名
	private int IId;// 信息Id
	private String VContent;// 评论内容
	private Date VTime;// 评论时间

	public int getVId() {
		return VId;
	}

	public void setVId(int vId) {
		VId = vId;
	}

	public int getRUId() {
		return RUId;
	}

	public void setRUId(int rUId) {
		RUId = rUId;
	}

	public String getRUName() {
		return RUName;
	}

	public void setRUName(String rUName) {
		RUName = rUName;
	}

	public int getIId() {
		return IId;
	}

	public void setIId(int iId) {
		IId = iId;
	}

	public String getVContent() {
		return VContent;
	}

	public void setVContent(String vContent) {
		VContent = vContent;
	}

	public Date getVTime() {
		return VTime;
	}

	public void setVTime(Date vTime) {
		VTime = vTime;
	}

	public UserView(int vId, int rUId, String rUName, int iId, String vContent,
			Date vTime) {
		super();
		VId = vId;
		RUId = rUId;
		RUName = rUName;
		IId = iId;
		VContent = vContent;
		VTime = vTime;
	}

	public UserView(int rUId, int iId, String vContent, Date vTime) {
		super();
		RUId = rUId;
		IId = iId;
		VContent = vContent;
		VTime = vTime;
	}

	public UserView() {
		super();
	}

	@Override
	public String toString() {
		return "UserView [VId=" + VId + ", RUId=" + RUId + ", IId=" + IId
				+ ", VContent=" + VContent + ", VTime=" + VTime + "]";
	}

}
