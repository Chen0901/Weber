package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.bean.UserView;

public interface ViewDao {
	public int addView(UserView userView); // 添加评论
	
	// 查询评论
	public List<UserView> selectViewById(int IId);

	// 删除评论
	public int deleteView(int VId);
}
