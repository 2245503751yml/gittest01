package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.Child;
import com.example.pojo.Parent;
import com.example.pojo.Receiving;
import com.example.pojo.Relationship;
import com.example.service.ChildService;
import com.example.service.RelationshipService;
import com.example.service.parentService;
import com.jayway.jsonpath.JsonPath;

@RestController
@RequestMapping("/Binding")
@ResponseBody
public class BindingController {

	@Autowired
	private ChildService childService;
	@Autowired
	private parentService parentService;
	@Autowired
	private RelationshipService relationshipService;
	// 子供の情報が見つかりませんでした
	private static final String CHILD_ERRO_MSG = "正しいお子様の認証を入力していただけますか";
	// dateエラーメッセージ
	private static final String DATE_ERRO_MSG = "申し訳ございませんが、時間の入力に誤りがあるようです。もう一度入力し直していただけますでしょうか。";
	//文字格式的错误
	private static final String ERRO_MSG = "正しいフォーマットを入力してください: 子供の認識ID + 子供の生年月日。例: 「ID + 2000-02-02」";
	// バインド解除、保護者情報がない
	private static final String UNBOUND_MSG = "大変申し訳ございませんが、お子様のバインドがされていないため、解除することができません";

	/**
	 * 子供情報をバインド
	 * 
	 * @param json
	 * @return
	 */
	@PostMapping("/childBound")
	public String childBound(@RequestBody String json) {
		//
		Receiving receiving = name(json);
		if (receiving.getMessage() != null) {
			return receiving.getMessage();
		}
		String userId = receiving.getLineUserid();
		// 保護者チェック
		Parent parent = parentService.selectBylineID(userId);
		// 保護者情報がない
		if (parent == null) {
			Parent parent2 = new Parent();
			parent2.setLineUserid(userId);
			// 保護者情報を追加する
			parentService.insertSelective(parent2);
		}
		// も一度保護者情報を検索
		parent = parentService.selectBylineID(userId);
		// 保護者主キーID
		int pId = parent.getId();
		// 子供主キーID
		int cId = receiving.getChild().getId();
		// 子供と保護者の関係を検索
		Relationship relationship = relationshipService.selectByPrimaryKey(cId, pId);
		// 検索は空です
		if (relationship == null || relationship.getIsdelete() == 1) {
			int result = relationship == null ? relationshipService.insertSelective(cId, pId)
					: relationshipService.updateRelationshipBound(cId, pId);
			if (result > 0) {
				return "子供の登校メッセージ発信手続完了しました";
			}
			return "子供の登校メッセージ発信手続失敗しました、担当者にご連絡いただけますでしょう。";
		}
		// 子供と保護者の関係がある
		return "子供の登校メッセージ発信手続もうできでいます";
	}

	/**
	 * 子供情報をバインド
	 * 
	 * @param json
	 * @return
	 */
	@PostMapping("/childUnbound")
	public String childUnbound(@RequestBody String json) {

		Receiving receiving = name(json);
		if (receiving.getMessage() != null) {
			return receiving.getMessage();
		}
		String userId = receiving.getLineUserid();

		// 保護者チェック
		Parent parent = parentService.selectBylineID(userId);
		// 保護者情報がない
		if (parent == null) {
			return UNBOUND_MSG;
		}
		// 保護者主キーID
		int pId = parent.getId();
		// 子供主キーID
		int cId = receiving.getChild().getId();
		// 子供と保護者の関係を検索
		Relationship relationship = relationshipService.selectByPrimaryKey(cId, pId);
		// 検索は空です
		if (relationship != null) {
			// 子供と保護者の関係を追加する
			if (0 < relationshipService.updateRelationshipUnBound(cId, pId)) {
				return "子供の登校メッセージ発信のバインド解除手続完了しました";
			}
			// 追加に失敗しました、文字列を返します
			return "子供の登校メッセージ発信のバインド解除手続失敗しまし た、担当者にご連絡いただけますでしょう。";
		}
		// 子供と保護者の関係がある
		return "子供の登校メッセージ発信のバインド解除手続もうできでいます";

	}

	public Receiving name(String json) {
		Receiving receiving = new Receiving();
		try {
			// 保護者ユーザーIDを取得
			String userId = JsonPath.parse(json).read("$.events[0].source.userId");
			// 子供情報を取得
			String text = JsonPath.parse(json).read("$.events[0].message.text");
			// 分割文字列
			String[] parts = text.split("\\+");
			// 子供識別ID
			String recognitionid = parts[0];
			// 誕生日
			String birthday = parts[1];
			// 子供識別IDチェック
			Child child = childService.selectByrecognitionid(recognitionid);
			// 子供情報がない
			if (child == null) {
				receiving.setMessage(CHILD_ERRO_MSG);
				return receiving;
			}
			// 誕生日チェック
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			formatter.setLenient(false); // 禁用宽松模式
			Date date = formatter.parse(birthday);
			if (!date.equals(child.getBirthday())) {
				receiving.setMessage(DATE_ERRO_MSG);
				return receiving;
			}

			receiving.setChild(child);
			receiving.setLineUserid(userId);
			return receiving;
		} catch (Exception e) {
			receiving.setMessage(ERRO_MSG);
			return receiving;
		}
	}
}
