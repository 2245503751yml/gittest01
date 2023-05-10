package com.example.dao;

import java.util.List;

import com.example.pojo.Parent;


public interface ParentMapper {
	
	//ユーザーLINEIDに基づいて、保護者情報取得
	Parent selectBylineID(String lineuserid);
	//子供識別IDに基づいて、保護者たちLINEIDを取得
	List<Parent> selectByPrimaryUserID(String recognitionid);
	//保護者情報を挿入
	int insertSelective(Parent row);
}