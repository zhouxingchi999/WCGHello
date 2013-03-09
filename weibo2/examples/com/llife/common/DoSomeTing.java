package com.llife.common;

import weibo4j.Friendships;
import weibo4j.Timeline;

import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONObject;
import weibo4j.model.Paging;

public class DoSomeTing {
	public static void main(String arg[]){
		String access_token = "2.00z8exMCsoBBiDb60eef7223rCSNUE";
		String[] id=new String[200];
		Paging page=new Paging();
		Friendships fm = new Friendships();
		Timeline tm = new Timeline();
		fm.client.setToken(access_token);
		tm.client.setToken(access_token);
		try {
			page.setCount(100);
			 StatusWapper status = tm.getUserTimelineByUid("2023798543",page,0,0);
				for(Status s : status.getStatuses()){
					 tm.Destroy(s.getId());
					Log.logInfo(s.toString());
				}
			
//			id= fm.getFriendsIdsByUid("2023798543",200,0);
//			for(int i=0;i<200;i++){
//			fm.destroyFriendshipsDestroyById(id[i]);
//			}
//			System.out.println(id);
		} catch (Exception e) {
			
		}
	}
}
