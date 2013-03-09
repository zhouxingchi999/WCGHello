package weibo4j.examples.user;

import weibo4j.Users;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

public class ShowUser {

	public static void main(String[] args) {
		String access_token ="2.00z8exMCsoBBiDb60eef7223rCSNUE";
		String uid ="2023798543";
		Users um = new Users();
		um.client.setToken(access_token);
		try {
			User user = um.showUserById(uid);
			
			Log.logInfo(user.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
