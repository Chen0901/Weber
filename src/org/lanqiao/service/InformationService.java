package org.lanqiao.service;

import java.util.List;

import org.lanqiao.bean.Information;

public interface InformationService {
	// 发表信息
	public int addpublishInformation(Information information);

	// 删除指定信息
	public int deleteInfo(Information information);

	// 搜索指定信息
	public List<Information> searchInfo(Information information);

	// 获取全部发布信息
	public List<Information> selectAllInfo();

	// 获取本人发布的信息和关注人的信息
	public List<Information> selectInfoById(Information information);

	// 删除发布的信息
	public int deletepublishInformation(int IId);

	// 获取个人发布信息
	public List<Information> selectOneInformation(int RUId);

	// 获取信息展示给游客
	public List<Information> selectInfoForUser();

	// 根据类别获取信息展示给用户
	public List<Information> selectInfoForRegister(Information information);

	// 获取个人发布信息展示给用户
	public List<Information> selectInfoForSelf(Information information);

	// 根据类别获取信息展示给用户或游客
	public List<Information> selectInfoByKind(Information information);

	// 根据用户搜索获取信息展示给用户或游客
	public List<Information> searchInfoForUser(Information information);

	public int addGood(Information information); // 点赞

	public int addBad(Information information); // 踩

	// 根据用户收藏获取信息
	public List<Information> selectInfoByCollect(int RUId);

	// 查询信息用于评论
	public Information selectOneInfoById(Information information);
}
