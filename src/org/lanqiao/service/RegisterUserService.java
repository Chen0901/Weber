package org.lanqiao.service;

import java.util.List;

import org.lanqiao.bean.RegisterUser;

public interface RegisterUserService {
	// 获取全部用户
	public List<RegisterUser> selectAllRegisterUser();
	// 删除指定用户
	public int deleteRegisterUser(RegisterUser registerUser);
	//管理员查询指定用户
	public List<RegisterUser> searchRegisterUser(RegisterUser registerUser);
	//修改用户信息
	public int updatePersonalInformation(RegisterUser registerUser); 
	//修改用户登录密码
	public int updatePassword(RegisterUser registerUser);
	//获取单个用户信息
	public RegisterUser selectOneRegisterUser(int RUId);
	//添加注册用户
	public int addRegisterUser(RegisterUser registerUser);
	//根据用户名提示该用户是否存在
	public int selectRegisterUserByRUName(RegisterUser registerUser);
	//用户登录
	public RegisterUser loginRegisterUser(RegisterUser registerUser);
	//获取用户关注的所有人
	public List<RegisterUser> selectAllFollow(int ruId);
	//获取用户的所有粉丝
	public List<RegisterUser> selectMyFans(int RUId);
}
