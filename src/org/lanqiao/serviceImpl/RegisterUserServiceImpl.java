package org.lanqiao.serviceImpl;

import java.util.List;
import org.lanqiao.bean.RegisterUser;
import org.lanqiao.dao.RegisterUserDao;
import org.lanqiao.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RegisterUserService")
public class RegisterUserServiceImpl implements RegisterUserService {
	RegisterUserDao registerUserDao;

	public RegisterUserDao getRegisterUserDao() {
		return registerUserDao;
	}

	@Autowired
	public void setRegisterUserDao(RegisterUserDao registerUserDao) {
		this.registerUserDao = registerUserDao;
	}

	// 获取全部用户
	@Override
	public List<RegisterUser> selectAllRegisterUser() {
		return registerUserDao.selectAllRegisterUser();
	}

	// 删除指定用户
	@Override
	public int deleteRegisterUser(RegisterUser registerUser) {
		return registerUserDao.deleteRegisterUser(registerUser);
	}

	@Override
	public List<RegisterUser> searchRegisterUser(RegisterUser registerUser) {
		return registerUserDao.searchRegisterUser(registerUser);
	}

	// 获取单个用户信息
	@Override
	public RegisterUser selectOneRegisterUser(int RUId) {
		return registerUserDao.selectOneRegisterUser(RUId);
	}

	// 修改用户信息
	@Override
	public int updatePersonalInformation(RegisterUser registerUser) {
		return registerUserDao.updatePersonalInformation(registerUser);
	}

	@Override
	// 添加注册用户
	public int addRegisterUser(RegisterUser registerUser) {
		return registerUserDao.addRegisterUser(registerUser);
	}

	@Override
	// 根据用户名提示该用户是否存在
	public int selectRegisterUserByRUName(RegisterUser registerUser) {
		if (registerUserDao.selectRegisterUserByRUName(registerUser) == null) {
			return 0;
		}
		return registerUserDao.selectRegisterUserByRUName(registerUser)
				.getRUId();
	}

	// 用户登录
	@Override
	public RegisterUser loginRegisterUser(RegisterUser registerUser) {
		return registerUserDao.loginRegisterUser(registerUser);

	}

	@Override
	public List<RegisterUser> selectAllFollow(int ruId) {
		return registerUserDao.selectAllFollow(ruId);
	}

	@Override
	public List<RegisterUser> selectMyFans(int RUId) {
		return registerUserDao.selectMyFans(RUId);
	}

	@Override
	public int updatePassword(RegisterUser registerUser) {
		return registerUserDao.updatePassword(registerUser);
	}

}
