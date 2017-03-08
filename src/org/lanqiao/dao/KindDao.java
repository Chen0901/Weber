package org.lanqiao.dao;

import java.util.List;

import org.lanqiao.bean.Kind;

public interface KindDao {
	//添加类别
	public int addKind(Kind kind);
	//删除指定类别
	public int deleteKind(Kind kind);
	//修改指定类别
	public int updateKind(Kind kind);
	//获取全部的类别信息
	public List<Kind> selectAllKind();
	//查询指定类别信息
	public List<Kind> searchKind(Kind kind);
}
