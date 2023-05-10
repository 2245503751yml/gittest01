package com.example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.dao.ChildMapper;
import com.example.dao.ParentMapper;
import com.example.dao.RelationshipMapper;
import com.example.pojo.Child;
import com.example.pojo.Parent;
import com.example.pojo.Relationship;
import com.example.service.ChildService;
import com.example.service.RelationshipService;
import com.example.service.parentService;

import jakarta.annotation.Resource;

@SpringBootTest
@AutoConfigureMockMvc
public class BindingContrTest {

	@MockBean
	private ChildMapper childMapper;
	@MockBean
	private ParentMapper parentMapper;
	@MockBean
	private RelationshipMapper relationshipMapper;
	@SpyBean
	private ChildService childService;
	@SpyBean
	private parentService parentService;
	@SpyBean
	private RelationshipService relationshipService;
	@Resource
	private MockMvc mockMvc;

	/**
	 * バインド成功
	 * 
	 * @throws Exception
	 */
	@Test
	public void childmessageTest() throws Exception {
		String dateString = "2008-08-08";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateString);
		Child chil = new Child();
		chil.setId(5);
		chil.setRecognitionid("MNO345");
		chil.setBirthday(date);
		Mockito.when(childMapper.selectByrecognitionid(Mockito.anyString())).thenReturn(chil);

		Parent parent = new Parent();
		parent.setLineUserid("line_user1000");
		parent.setId(11);
		Mockito.when(parentMapper.selectBylineID(Mockito.anyString())).thenReturn(parent);

		Relationship relationship = new Relationship();
		Mockito.when(relationshipMapper.selectByPrimaryKey(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);
		relationship.setcId(5);
		relationship.setpId(11);
		Mockito.when(relationshipService.insertSelective(Mockito.anyInt(), Mockito.anyInt())).thenReturn(200);
		String jsonStr = "{\"destination\":\"U9e634d68ba4b03a1b0209b4604b188d8\",\"events\":[{\"type\":\"message\",\"message\":{\"type\":\"text\",\"id\":\"17992490742115\",\"text\":\"MNO345+2008-08-08\"},\"webhookEventId\":\"01GY6QARGS40PMKS91YE5HP2QV\",\"deliveryContext\":{\"isRedelivery\":false},\"timestamp\":1681705492935,\"source\":{\"type\":\"user\",\"userId\":\"line_user1000\"},\"replyToken\":\"6c2c211978f04249b54c8b29ac5f0af4\",\"mode\":\"active\"}]}";

		RequestBuilder rb = MockMvcRequestBuilders.post("/Binding/childBound").contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("子供の登校メッセージ発信手続完了しました", result.getResponse().getContentAsString());

	}

	/**
	 * 子供情報がない
	 * 
	 * @throws Exception
	 */
	@Test
	@Disabled
	public void childmessageErroTest() throws Exception {

		Mockito.when(childMapper.selectByrecognitionid(Mockito.anyString())).thenReturn(null);
		String jsonStr = "{\"destination\":\"U9e634d68ba4b03a1b0209b4604b188d8\",\"events\":[{\"type\":\"message\",\"message\":{\"type\":\"text\",\"id\":\"17992490742115\",\"text\":\"MNO345+2008-08-08\"},\"webhookEventId\":\"01GY6QARGS40PMKS91YE5HP2QV\",\"deliveryContext\":{\"isRedelivery\":false},\"timestamp\":1681705492935,\"source\":{\"type\":\"user\",\"userId\":\"line_user1000\"},\"replyToken\":\"6c2c211978f04249b54c8b29ac5f0af4\",\"mode\":\"active\"}]}";

		RequestBuilder rb = MockMvcRequestBuilders.post("/Binding/childBound").contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("正しいお子様の認証を入力していただけますか", result.getResponse().getContentAsString());

	}

	/**
	 * 日付の形式が正しくありません
	 * 
	 * @throws Exception
	 */
	@Test
	@Disabled
	public void childmessageDateErroTest() throws Exception {
		String dateString = "2008-08-08";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateString);
		Child chil = new Child();
		chil.setId(5);
		chil.setRecognitionid("MNO345");
		chil.setBirthday(date);
		Mockito.when(childMapper.selectByrecognitionid(Mockito.anyString())).thenReturn(chil);
		String jsonStr = "{\"destination\":\"U9e634d68ba4b03a1b0209b4604b188d8\",\"events\":[{\"type\":\"message\",\"message\":{\"type\":\"text\",\"id\":\"17992490742115\",\"text\":\"MNO345+2008-08-02228\"},\"webhookEventId\":\"01GY6QARGS40PMKS91YE5HP2QV\",\"deliveryContext\":{\"isRedelivery\":false},\"timestamp\":1681705492935,\"source\":{\"type\":\"user\",\"userId\":\"line_user1000\"},\"replyToken\":\"6c2c211978f04249b54c8b29ac5f0af4\",\"mode\":\"active\"}]}";

		RequestBuilder rb = MockMvcRequestBuilders.post("/Binding/childBound").contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("正しいフォーマットを入力してください: 子供の認識ID + 子供の生年月日。例: 「ID + 2000-02-02」",
				result.getResponse().getContentAsString());
	}

	/**
	 * 日付の入力エラーです。
	 */
	@Test
	@Disabled
	public void childmessageBirthdayErroTest() throws Exception {
		String dateString = "2008-08-08";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateString);
		Child chil = new Child();
		chil.setId(5);
		chil.setRecognitionid("MNO345");
		chil.setBirthday(date);
		Mockito.when(childMapper.selectByrecognitionid(Mockito.anyString())).thenReturn(chil);
		String jsonStr = "{\"destination\":\"U9e634d68ba4b03a1b0209b4604b188d8\",\"events\":[{\"type\":\"message\",\"message\":{\"type\":\"text\",\"id\":\"17992490742115\",\"text\":\"MNO345+2008-08-09\"},\"webhookEventId\":\"01GY6QARGS40PMKS91YE5HP2QV\",\"deliveryContext\":{\"isRedelivery\":false},\"timestamp\":1681705492935,\"source\":{\"type\":\"user\",\"userId\":\"line_user1000\"},\"replyToken\":\"6c2c211978f04249b54c8b29ac5f0af4\",\"mode\":\"active\"}]}";

		RequestBuilder rb = MockMvcRequestBuilders.post("/Binding/childBound").contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("申し訳ございませんが、時間の入力に誤りがあるようです。もう一度入力し直していただけますでしょうか。",
				result.getResponse().getContentAsString());

	}

	/**
	 * 既にバインドされています
	 * 
	 * @throws Exception
	 */
	@Test
	@Disabled
	public void childmessagebindErroTest() throws Exception {
		String dateString = "2008-08-08";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateString);
		Child chil = new Child();
		chil.setId(5);
		chil.setRecognitionid("MNO345");
		chil.setBirthday(date);
		Mockito.when(childMapper.selectByrecognitionid(Mockito.anyString())).thenReturn(chil);

		Parent parent = new Parent();
		parent.setLineUserid("line_user1000");
		parent.setId(11);
		Mockito.when(parentMapper.selectBylineID(Mockito.anyString())).thenReturn(parent);

		Relationship relationship = new Relationship();
		relationship.setcId(5);
		relationship.setpId(11);

		Mockito.when(relationshipMapper.selectByPrimaryKey(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(relationship);

		String jsonStr = "{\"destination\":\"U9e634d68ba4b03a1b0209b4604b188d8\",\"events\":[{\"type\":\"message\",\"message\":{\"type\":\"text\",\"id\":\"17992490742115\",\"text\":\"MNO345+2008-08-08\"},\"webhookEventId\":\"01GY6QARGS40PMKS91YE5HP2QV\",\"deliveryContext\":{\"isRedelivery\":false},\"timestamp\":1681705492935,\"source\":{\"type\":\"user\",\"userId\":\"line_user1000\"},\"replyToken\":\"6c2c211978f04249b54c8b29ac5f0af4\",\"mode\":\"active\"}]}";
		RequestBuilder rb = MockMvcRequestBuilders.post("/Binding/childBound").contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("子供の登校メッセージ発信手続もうできでいます", result.getResponse().getContentAsString());

	}

	/**
	 * メッセージを送信成功
	 * 
	 * @throws Exception
	 */
	@Test
	@Disabled
	public void parentMessageTest() throws Exception {
		String dateString = "2008-08-08";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateString);

		Child chil = new Child();
		chil.setId(5);
		chil.setRecognitionid("MNO345");
		chil.setBirthday(date);
		Mockito.when(childMapper.selectByrecognitionid(Mockito.anyString())).thenReturn(chil);
		List<Parent> parents = new ArrayList<Parent>();
		parents.add(0, new Parent(11, null, "line_user1000", null));
		Mockito.when(parentMapper.selectByPrimaryUserID(Mockito.anyString())).thenReturn(parents);
		String jsonStr = "{\"events\":[{\"type\":\"message\",\"message\":{\"arrivaltime\":\"2023-02-02 08:50\",\"departuretime\":\"\",\"recognitionid\":\"ABC123\"}}]}";
		RequestBuilder rb = MockMvcRequestBuilders.post("/Message/parentMessage")
				.contentType(MediaType.APPLICATION_JSON).content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("line_user1000", result.getResponse().getContentAsString());
	}

	/**
	 * エラー時間
	 * 
	 * @throws Exception
	 */
	@Test
	@Disabled
	public void parentMessageDateErroTest() throws Exception {
		String dateString = "2008-08-08";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(dateString);

		Child chil = new Child();
		chil.setId(5);
		chil.setRecognitionid("MNO345");
		chil.setBirthday(date);
		Mockito.when(childMapper.selectByrecognitionid(Mockito.anyString())).thenReturn(chil);
		List<Parent> parents = new ArrayList<Parent>();
		parents.add(0, new Parent(11, null, "line_user1000", null));
		Mockito.when(parentMapper.selectByPrimaryUserID(Mockito.anyString())).thenReturn(parents);
		String jsonStr = "{\"events\":[{\"type\":\"message\",\"message\":{\"arrivaltime\":\"2023-02-032 08:50\",\"departuretime\":\"\",\"recognitionid\":\"ABC123\"}}]}";
		RequestBuilder rb = MockMvcRequestBuilders.post("/Binding/parentMessage")
				.contentType(MediaType.APPLICATION_JSON).content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("文字列に無効な文字が含まれています", result.getResponse().getContentAsString());
	}

	/**
	 * 子供情報がありません
	 * 
	 * @throws Exception
	 */
	@Test
	@Disabled
	public void parentMessageChildErroTest() throws Exception {
		Mockito.when(childMapper.selectByrecognitionid(Mockito.anyString())).thenReturn(null);
		String jsonStr = "{\"events\":[{\"type\":\"message\",\"message\":{\"arrivaltime\":\"2023-02-02 08:50\",\"departuretime\":\"\",\"recognitionid\":\"ABC123\"}}]}";
		RequestBuilder rb = MockMvcRequestBuilders.post("/Binding/parentMessage")
				.contentType(MediaType.APPLICATION_JSON).content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("子供情報がありません", result.getResponse().getContentAsString());
	}

	/**
	 * マシン文字化け
	 * 
	 * @throws Exception
	 */
	@Test
	@Disabled
	public void parentMessageErroTest() throws Exception {

		String jsonStr = "!#$%%&'(())===~~~ASDA+DW<QLDAS<>D<LA+SKD+KWOQKDMSL+A";
		RequestBuilder rb = MockMvcRequestBuilders.post("/Binding/parentMessage")
				.contentType(MediaType.APPLICATION_JSON).content(jsonStr);
		MvcResult result = mockMvc.perform(rb).andReturn();
		Assertions.assertEquals("例外が発生しました。管理者に連絡してください", result.getResponse().getContentAsString());
	}
}
