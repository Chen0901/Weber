package org.lanqiao.dao;

import org.lanqiao.bean.Follow;

public interface FollowDao {
	//关注
	public int addFollowUser(int RUId,int EUId);
	//取关
	public int deleteFollowUser(int EUId);
	//获取关注信息
	public Follow selectFollowById(Follow follow);
}
