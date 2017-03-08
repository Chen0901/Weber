package org.lanqiao.dao;

import org.lanqiao.bean.Manager;

public interface ManagerDao {
	//管理员登录方法
	public Manager managerLogin(Manager manager);
	//修改管理员密码
	public int updateManagerPwd(Manager manger);
}
