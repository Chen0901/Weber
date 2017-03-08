package org.lanqiao.serviceImpl;

import java.util.List;

import org.lanqiao.bean.Kind;
import org.lanqiao.dao.KindDao;
import org.lanqiao.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("KindService")
public class KindServiceImpl implements KindService{
	KindDao kindDao;

	public KindDao getKindDao() {
		return kindDao;
	}

	@Autowired
	public void setKindDao(KindDao kindDao) {
		this.kindDao = kindDao;
	}

	@Override
	public List<Kind> selectAllKind() {
		return kindDao.selectAllKind();
	}

	@Override
	public int addKind(Kind kind) {
		return kindDao.addKind(kind);
	}

	@Override
	public int deleteKind(Kind kind) {
		return kindDao.deleteKind(kind);
	}

	@Override
	public int updateKind(Kind kind) {
		return kindDao.updateKind(kind);
	}

	@Override
	public List<Kind> searchKind(Kind kind) {
		return kindDao.searchKind(kind);
	}
	
}
