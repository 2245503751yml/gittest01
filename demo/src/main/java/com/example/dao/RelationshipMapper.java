package com.example.dao;

import com.example.pojo.Relationship;

public interface RelationshipMapper {

	// 関係を検索
	Relationship selectByPrimaryKey(int cId, int pId);

	// 関係を挿入
	int insertSelective(int cId, int pId);

	// 関係を解除
	int updateRelationshipUnBound(int cId, int pId);

	// 関係バインドを修正
	int updateRelationshipBound(int cId, int pId);
}