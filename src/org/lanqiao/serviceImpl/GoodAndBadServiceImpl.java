package org.lanqiao.serviceImpl;

import org.lanqiao.bean.GoodAndBad;
import org.lanqiao.dao.GoodAndBadDao;
import org.lanqiao.service.GoodAndBadService;
import org.springframework.beans.factory.annotation.Autowired;

public class GoodAndBadServiceImpl implements GoodAndBadService {
	GoodAndBadDao goodAndBadDao;
	
	public GoodAndBadDao getGoodAndBadDao() {
		return goodAndBadDao;
	}

	@Autowired
	public void setGoodAndBadDao(GoodAndBadDao goodAndBadDao) {
		this.goodAndBadDao = goodAndBadDao;
	}

	@Override
	public GoodAndBad selectGBInfoById(GoodAndBad goodAndBad) {
		return goodAndBadDao.selectGBInfoById(goodAndBad);
	}

	@Override
	public int addGBInfo(GoodAndBad goodAndBad) {
		return goodAndBadDao.addGBInfo(goodAndBad);
	}
	
}
