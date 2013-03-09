package weibo4j.examples.friendships;

import weibo4j.Friendships;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

public class GetFollowsActive {

	public static void main(String[] args) {
		String access_token = "2.00z8exMCsoBBiDb60eef7223rCSNUE";
		String uid = "2023798543";
		Friendships fm = new Friendships();
		int i=0;
		fm.client.setToken(access_token);
		try {
			UserWapper users = fm.getFollowersActive(uid,200);
			for(User u : users.getUsers()){
				Log.logInfo(u.toString());
				i++;
				System.out.println(i);
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
