package org.lanqiao.serviceImpl;

import org.lanqiao.bean.Follow;
import org.lanqiao.dao.FollowDao;
import org.lanqiao.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;

public class FollowServiceImpl implements FollowService {
	FollowDao followDao;

	public FollowDao getFollowDao() {
		return followDao;
	}
	@Autowired
	public void setFollowDao(FollowDao followDao) {
		this.followDao = followDao;
	}
	// ¹Ø×¢
	@Override
	public int addFollowUser(int RUId,int EUId) {
		return followDao.addFollowUser(RUId, EUId);
	}
	// È¡¹Ø
	@Override
	public int deleteFollowUser(int EUId) {
		return followDao.deleteFollowUser(EUId);
	}
	@Override
	public Follow selectFollowById(Follow follow) {
		return followDao.selectFollowById(follow);
	}
}
