package org.lanqiao.serviceImpl;

import java.util.List;

import org.lanqiao.bean.Information;
import org.lanqiao.dao.InformationDao;
import org.lanqiao.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("InformationService")
public class InformationServiceImpl implements InformationService{
	private InformationDao informationDao;

	public InformationDao getInformationDao() {
		return informationDao;
	}
	
	@Autowired
	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
	}

	@Override
	//发表信息
	public int addpublishInformation(Information information) {
		return informationDao.addpublishInformation(information);
	}
	
	//删除指定信息
	@Override
	public int deleteInfo(Information information) {
		// TODO Auto-generated method stub
		return informationDao.deleteInfo(information);
	}
	@Override
	public List<Information> selectAllInfo() {
		return informationDao.selectAllInfo();
	}

	@Override
	public List<Information> searchInfo(Information information) {
		return informationDao.searchInfo(information);
	}
	
	/*@Override
	//获取个人发布的信息
	public Information selectPublishInformation(int IId) {
		return informationDao.selectPublishInformation(IId);
	}*/
	
	@Override
	//删除发布的信息
	public int deletepublishInformation(int IId) {
		return informationDao.deletepublishInformation(IId);
	}

	@Override
	public List<Information> selectOneInformation(int RUId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Information> selectInfoForUser() {
		return informationDao.selectInfoForUser();
	}

	@Override
	public List<Information> selectInfoByKind(Information information) {
		return informationDao.selectInfoByKind(information);
	}

	@Override
	public List<Information> searchInfoForUser(Information information) {
		return informationDao.searchInfoForUser(information);
	}

	@Override
	public List<Information> selectInfoForRegister(Information information) {
		return informationDao.selectInfoForRegister(information);
	}

	@Override
	public int addGood(Information information) {
		return informationDao.addGood(information);
	}

	@Override
	public int addBad(Information information) {
		return informationDao.addBad(information);
	}

	@Override
	public List<Information> selectInfoById(Information information) {
		return informationDao.selectInfoById(information);
	}

	@Override
	public List<Information> selectInfoForSelf(Information information) {
		return informationDao.selectInfoForSelf(information);
	}

	@Override
	public List<Information> selectInfoByCollect(int RUId) {
		return informationDao.selectInfoByCollect(RUId);
	}

	@Override
	public Information selectOneInfoById(Information information) {
		return informationDao.selectOneInfoById(information);
	}
	
}
