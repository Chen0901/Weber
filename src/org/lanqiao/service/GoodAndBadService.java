package org.lanqiao.service;

import org.lanqiao.bean.GoodAndBad;

public interface GoodAndBadService {
	// 查询是否点过赞或踩
	public GoodAndBad selectGBInfoById(GoodAndBad goodAndBad);

	// 添加用户踩或赞的信息
	public int addGBInfo(GoodAndBad goodAndBad);
}
