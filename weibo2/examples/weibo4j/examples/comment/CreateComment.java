package weibo4j.examples.comment;

import weibo4j.Comments;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.WeiboException;

public class CreateComment {

	public static void main(String[] args) {
		String access_token = "2.00z8exMCsoBBiDb60eef7223rCSNUE";
		String comments ="测试一";
		String id = "1";
		Comments cm = new Comments();
		cm.client.setToken(access_token);
		try {
			Comment comment = cm.createComment(comments, id);
			Log.logInfo(comment.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}
