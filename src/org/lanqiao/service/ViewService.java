package org.lanqiao.service;

import java.util.List;

import org.lanqiao.bean.UserView;

public interface ViewService {
	public int addView(UserView userView);// 添加评论
	
	// 查询评论
	public List<UserView> selectViewById(int IId);
	
	// 删除评论
	public int deleteView(int VId);
}
