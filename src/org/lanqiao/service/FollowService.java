package org.lanqiao.service;

import org.lanqiao.bean.Follow;

public interface FollowService {
	// 关注
	public int addFollowUser(int RUId, int EUId);

	// 取关
	public int deleteFollowUser(int EUId);

	// 获取关注信息
	public Follow selectFollowById(Follow follow);
}
