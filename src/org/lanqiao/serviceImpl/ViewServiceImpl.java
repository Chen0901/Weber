package org.lanqiao.serviceImpl;

import java.util.List;

import org.lanqiao.bean.UserView;
import org.lanqiao.dao.ViewDao;
import org.lanqiao.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("ViewService")
public class ViewServiceImpl implements ViewService {
      ViewDao viewDao;
	/**
	 * @return the viewDao
	 */
	public ViewDao getViewDao() {
		return viewDao;
	}
	/**
	 * @param viewDao the viewDao to set
	 */
	@Autowired
	public void setViewDao(ViewDao viewDao) {
		this.viewDao = viewDao;
	}
	@Override
	public int addView(UserView userView) {
		return viewDao.addView(userView);
	}
	@Override
	public List<UserView> selectViewById(int IId) {
		return viewDao.selectViewById(IId);
	}
	@Override
	public int deleteView(int VId) {
		return viewDao.deleteView(VId);
	}

}
