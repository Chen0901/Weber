package org.lanqiao.serviceImpl;

import org.lanqiao.bean.Manager;
import org.lanqiao.dao.ManagerDao;
import org.lanqiao.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {
	ManagerDao managerDao;

	public ManagerDao getManagerDao() {
		return managerDao;
	}

	@Autowired
	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	public Manager managerLogin(Manager manager) {
		return managerDao.managerLogin(manager);
	}

	@Override
	public int updateManagerPwd(Manager manger) {
		return managerDao.updateManagerPwd(manger);
	}

}
