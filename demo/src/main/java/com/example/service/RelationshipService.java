package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.RelationshipMapper;
import com.example.pojo.Relationship;

@Service
public class RelationshipService {

	@Autowired
	private RelationshipMapper relationshipMapper;

	public Relationship selectByPrimaryKey(int cId, int pId) {
		return relationshipMapper.selectByPrimaryKey(cId, pId);
	}

	public int insertSelective(int cId, int pId) {
		return relationshipMapper.insertSelective(cId, pId);
	}

	public int updateRelationshipUnBound(int cId, int pId) {
		return relationshipMapper.updateRelationshipUnBound(cId, pId);
	}

	public int updateRelationshipBound(int cId, int pId) {
		return relationshipMapper.updateRelationshipBound(cId, pId);
	}
}
