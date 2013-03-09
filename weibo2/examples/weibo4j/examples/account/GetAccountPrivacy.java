package weibo4j.examples.account;

import weibo4j.Account;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONObject;

public class GetAccountPrivacy {

	public static void main(String[] args) {
		String access_token ="2.00z8exMCsoBBiDb60eef7223rCSNUE";
		Account am = new Account();
		am.client.setToken(access_token);
		try {
			int i=0;
			String[] s;
            JSONObject json = am.getAccountPrivacy();
            int len=json.length();
            s=JSONObject.getNames(json);
            for(;i<len;i++ )
				Log.logInfo(s[i]);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
