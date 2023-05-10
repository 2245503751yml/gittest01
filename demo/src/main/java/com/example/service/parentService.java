package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ParentMapper;
import com.example.pojo.Parent;

@Service
public class parentService {

	@Autowired
	private ParentMapper parentMapper;
	
	//保護者情報を検索
	public Parent selectBylineID(String lineuserid) {
		return parentMapper.selectBylineID(lineuserid);
	}
	//子供識別IDに基づいて、保護者情報を検索
	public List<Parent> selectByPrimaryUserID(String recognitionid) {
		return parentMapper.selectByPrimaryUserID(recognitionid);
	}
	//保護者情報を挿入
	public int insertSelective(Parent row) {
		return parentMapper.insertSelective(row);
	}
}
