package org.lanqiao.bean;

/**
 * 
 * @author Administrator
 * @用户实体类
 */
public class RegisterUser {
	private int RUId; // 用户Id
	private String RUName; // 用户姓名
	private String RUPassWord; // 用户密码
	private int RUSex; // 用户性别
	private String RUIntroduction; // 用户简介
	private int RULevel; // 用户等级
	private String RUPic; // 用户头像保存路径

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

	public String getRUPassWord() {
		return RUPassWord;
	}

	public void setRUPassWord(String rUPassWord) {
		RUPassWord = rUPassWord;
	}

	public int getRUSex() {
		return RUSex;
	}

	public void setRUSex(int rUSex) {
		RUSex = rUSex;
	}

	public String getRUIntroduction() {
		return RUIntroduction;
	}

	public void setRUIntroduction(String rUIntroduction) {
		RUIntroduction = rUIntroduction;
	}

	public int getRULevel() {
		return RULevel;
	}

	public void setRULevel(int rULevel) {
		RULevel = rULevel;
	}

	public String getRUPic() {
		return RUPic;
	}

	public void setRUPic(String rUPic) {
		RUPic = rUPic;
	}

	public RegisterUser(int rUId, String rUName, String rUPassWord, int rUSex,
			String rUIntroduction, int rULevel, String rUPic) {
		RUId = rUId;
		RUName = rUName;
		RUPassWord = rUPassWord;
		RUSex = rUSex;
		RUIntroduction = rUIntroduction;
		RULevel = rULevel;
		RUPic = rUPic;
	}
	
	public RegisterUser(int rUId, String rUName, String rUPassWord, int rUSex,
			String rUIntroduction, int rULevel) {
		super();
		RUId = rUId;
		RUName = rUName;
		RUPassWord = rUPassWord;
		RUSex = rUSex;
		RUIntroduction = rUIntroduction;
		RULevel = rULevel;
	}

	public RegisterUser(int rUId, String rUName, String rUPassWord, int rUSex,
			String rUIntroduction) {
		super();
		RUId = rUId;
		RUName = rUName;
		RUPassWord = rUPassWord;
		RUSex = rUSex;
		RUIntroduction = rUIntroduction;
	}
	
	public RegisterUser(String rUName, String rUPassWord, int rUSex,
			String rUIntroduction) {
		super();
		RUName = rUName;
		RUPassWord = rUPassWord;
		RUSex = rUSex;
		RUIntroduction = rUIntroduction;
	}

	public RegisterUser() {
	}

	public RegisterUser(int rUId, String rUName, int rUSex) {
		RUId = rUId;
		RUName = rUName;
		RUSex = rUSex;
	}

	public RegisterUser(int rUId) {
		RUId = rUId;
	}

	@Override
	public String toString() {
		return "RegisterUser [RUId=" + RUId + ", RUName=" + RUName
				+ ", RUPassWord=" + RUPassWord + ", RUSex=" + RUSex
				+ ", RUIntroduction=" + RUIntroduction + ", RULevel=" + RULevel
				+ ", RUPic=" + RUPic + "]";
	}

	public RegisterUser(String rUName, String rUPassWord) {
		super();
		RUName = rUName;
		RUPassWord = rUPassWord;
	}
}
