package org.lanqiao.dao;

import org.lanqiao.bean.CollectionInfo;

public interface CollectionInfoDao {
	// 收藏
	public int addCollectionInformation(int RUId, int IId);

	// 取消收藏
	public int deleteCollectionInformation(CollectionInfo collectionInfo);

	// 通过用户Id和信息Id查询收藏信息
	public CollectionInfo selectCollectionInfoById(CollectionInfo collectionInfo);
}
