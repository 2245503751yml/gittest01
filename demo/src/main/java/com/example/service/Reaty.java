package com.example.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;

@Service
public class Reaty {

	@Value("${line.bot.channel-token}")
	private String channelToken;
	
	/**
	 * メッセージ再送
	 * @param lineid　保護者LINEユーザーID
	 * @param chilname　子供名前
	 * @param time　時間
	 * @param messages　
	 * @return
	 * @throws Exception
	 */
	@Retryable( maxAttempts = 3, backoff = @Backoff(delay = 1000L))
	 public String minGoodsnum(String lineid,String chilname,String time,String messages) throws Exception {
	    String message = "ご清聴ありがとうございました。お子様の"+ chilname +"様は、"+time+"に学校に"+ messages+"ことをご報告申し上げます。";
        // メッセージオブジェクトを作成
        TextMessage textMessage = new TextMessage(message);
        // プッシュメッセージオブジェクトを作成
        PushMessage pushMessage = new PushMessage(lineid, textMessage);
        // メッセージ送信クライアントを作成
        LineMessagingClient client = LineMessagingClient.builder(channelToken).build();
        client.pushMessage(pushMessage).get();
        return null;
	}
	
	/**
	 * コールバック関数、失敗した lineuserID を返す
	 * @param e
	 * @param lineid
	 * @return
	 */
	@Recover
    public String recover(Exception e,String lineid) {
        return lineid;
    }
}
