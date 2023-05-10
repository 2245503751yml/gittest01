package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.pojo.Child;
import com.example.pojo.Parent;
import com.example.service.ChildService;
import com.example.service.Reaty;
import com.example.service.parentService;
import com.jayway.jsonpath.JsonPath;
@RestController
@RequestMapping("/Message")
@ResponseBody
public class MessagesController {
	@Autowired
	private Reaty reaty;
	@Autowired
	private ChildService childService;
	@Autowired
	private parentService parentService;
	/**
	 * 子供情報メッセージを送信API
	 * @param json
	 * @return
	 */
	@PostMapping("/parentMessage")
	public String parentMessage(@RequestBody String json) {
		try {
			//登校時間
			String arrivaltime = JsonPath.parse(json).read("$.events[0].message.arrivaltime");
			//下校時間
			String departuretime=JsonPath.parse(json).read("$.events[0].message.departuretime");
			//子供識別ID
			String recognitionid=JsonPath.parse(json).read("$.events[0].message.recognitionid");
			//メッセージの時間
			String time;
			//メッセージの状態
			String message;
			
			//登校時間と下校時間が空です
			boolean bothTimesEmpty = arrivaltime.isEmpty() && departuretime.isEmpty();
			//登校時間と下校時間が空いていません
			boolean bothTimesNotEmpty = !arrivaltime.isEmpty() && !departuretime.isEmpty();
			if (bothTimesEmpty || bothTimesNotEmpty) {
			     return "時間異常";
			}
			//子供情報を検索
			Child child= childService.selectByrecognitionid(recognitionid);
			//子供情報は空です
			if (child==null) {
				return "子供情報がありません";
			}
			//下校時間が空いていません
			if (!departuretime.isEmpty()&& isValidDateTime(departuretime)) {
				time=departuretime;
				message="到着されました";
			 //登校時間が空いていません	
			}else if (!arrivaltime.isEmpty()&& isValidDateTime(arrivaltime)) {
				time=arrivaltime;
				message="退室しました";
			}else {
				return "文字列に無効な文字が含まれています";
			}
			//子供バインドの保護者の情報が取得
			List<Parent> parents=parentService.selectByPrimaryUserID(recognitionid);
			//保護者たちの情報が空です
			if (parents.isEmpty()) {
				return "";
			}
			//メッセージ送信失敗リスト
			String parent2="";
			//メッセージが送信
			for(int i=0 ;i<parents.size();i++) {
				//メッセージ送信失敗
				parent2 += reaty.minGoodsnum(parents.get(i).getLineUserid(), child.getName(), time, message) + "\n";
			}
			return parent2;
		} catch (Exception e) {
			// TODO: handle exception
			return "例外が発生しました。管理者に連絡してください";
		}
	}
	
	/**
	 * 時間形式検査
	 * @param str
	 * @return
	 */
	public static boolean isValidDateTime(String str) {
		//時間の形式をチェックする 
	    boolean isValid = str.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
	    //形式正確
	    if (isValid) {
	    	//文字列を時間に変換する
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	        try {
	        	//時間正確
	        	LocalDateTime.parse(str, formatter);
	            // 使用 dateTime 对象进行时间操作
	            return true;
	        } catch (DateTimeParseException e) {
	        	//変換失敗
	            return false;
	        }
	    } else {
	        return false;
	    }
	}
}
