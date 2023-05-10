package com.example.dao;

import com.example.pojo.Child;


public interface ChildMapper {
	//子供情報取得
	Child selectByrecognitionid(String recognitionid);

}