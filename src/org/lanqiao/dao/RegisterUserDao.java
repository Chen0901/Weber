package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.bean.RegisterUser;

public interface RegisterUserDao{
	//获取全部用户
	public List<RegisterUser> selectAllRegisterUser();
	//删除指定用户
	public int deleteRegisterUser(RegisterUser registerUser);
	//管理员查询指定用户
	public List<RegisterUser> searchRegisterUser(RegisterUser registerUser);
	//获取单个用户信息
	public RegisterUser selectOneRegisterUser(int RUId);
	//修改用户信息
	public int updatePersonalInformation(RegisterUser registerUser);
	//修改用户登录密码
	public int updatePassword(RegisterUser registerUser);
	//添加注册用户
	public int addRegisterUser(RegisterUser registerUser);
	//根据用户名查询用户是否存在
	public RegisterUser selectRegisterUserByRUName(RegisterUser registerUser);
	//用户登录
	public RegisterUser loginRegisterUser(RegisterUser registerUser);
	//获取用户关注的所有人
	public List<RegisterUser> selectAllFollow(int RUId);
	//获取用户的所有粉丝
	public List<RegisterUser> selectMyFans(int RUId);
}
