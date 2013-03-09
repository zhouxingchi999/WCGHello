package weibo4j.examples.timeline;

import weibo4j.Timeline;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class ShowStatus {

	public static void main(String[] args) {
		String access_token = "2.00dUA_TDsoBBiDa9d5624271nyWkJE";
		String id = "3523178502485750";
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		try {
			Status status = tm.showStatus(id);

				Log.logInfo(status.toString());

		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
