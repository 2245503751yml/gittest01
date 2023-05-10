package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ChildMapper;
import com.example.pojo.Child;

@Service
public class ChildService {

	@Autowired
	private ChildMapper childMapper;
	
	//子供情報を検索
	public Child selectByrecognitionid(String recognitionid) {
		return childMapper.selectByrecognitionid(recognitionid);
	}
}
