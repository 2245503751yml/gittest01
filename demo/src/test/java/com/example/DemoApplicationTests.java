package com.example;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.pojo.Child;
import com.example.pojo.Parent;
import com.example.pojo.Relationship;
import com.example.service.ChildService;
import com.example.service.RelationshipService;
import com.example.service.parentService;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private ChildService childService;
	@Autowired
	private parentService parentService;
	@Autowired
	private RelationshipService relationshipService;
	
	//子供識別Idに基づく、子供情報を取得
	@Test
	@Disabled
	public void selectChild() {
		   String recognitionid="MNO345";
		   Child child=childService.selectByrecognitionid(recognitionid);
		   System.out.println(child.toString());
	}
	
	//保護者情報を取得
	@Test
	@Disabled
	public void selectBylineID() {
		String userId="line_user1000";
		Parent parent = parentService.selectBylineID(userId);
		   System.out.println(parent.toString());
	}
	
	//保護者情報を挿入する
	@Test
	@Disabled
	public void insertSelective() {
		String userId="line_user100000";
		Parent parent2=new Parent();
    	parent2.setLineUserid(userId);
		int i =parentService.insertSelective(parent2);
		if (i!= 0) {
			System.out.println("正常に挿入されました");
		}else {
			System.out.println("挿入に失敗しました");
		}
		
	}
	
	//保護者と子供のバインド関係を取得
	@Test
	public void selectByPrimaryKey() {
		int pId=11;
	    int cId=5;
		Relationship relationship=relationshipService.selectByPrimaryKey(cId, pId);
		System.out.println(relationship.toString());
		
	}
	//バインド関係挿入
	@Test
	@Disabled
	public void rinsertSelective() {
		int pId=11;
	    int cId=5;
		int p=relationshipService.insertSelective(cId, pId);
		if (p!= 0) {
			System.out.println("正常に挿入されました");
		}else {
			System.out.println("挿入に失敗しました");
		}
		
	}
	//子供識別IDに基づく、保護者情報を取得
	@Test
	@Disabled
	public void selectByPrimaryUserID() {
		String recognitionid="MNO345";
		List<Parent> parents=parentService.selectByPrimaryUserID(recognitionid);
		for (Parent parent : parents) {
			System.out.println(parent.toString());
		}
	}
}
