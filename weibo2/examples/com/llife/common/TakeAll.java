package com.llife.common;

import java.io.IOException;
import java.util.Calendar;

import weibo4j.Comments;
import weibo4j.Timeline;
import weibo4j.Users;
//import weibo4j.examples.oauth2.Log;
import weibo4j.model.CommentWapper;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.model.Paging;

public class TakeAll{
	private String userTabCol="userID, 昵称, 姓名, 省份, 城市, 位置, 个性签名, 个性化域名, 性别, 粉丝数, 好友数, 微博数, 收藏数, 创建时间, 是否允许所有人给我发私信, 是否是微博认证用户, 是否允许所有人对我的微博进行评论, 该用户是否关注当前登录用户, 用户的在线状态, 用户的互粉数, 用户当前的语言版本";
	private String statusTabCol="微博所属用户ID,微博id,微博创建时间,微博内容,微博来源,是否已收藏,转发数量,评论数量,微博可见性";
	private String userID;
	private String quest; 
	
	private long statusNum;
	private int pageNum;
	
	private Paging page;
	private StatusWapper[] status=new StatusWapper[1];
	
	private CommentWapper comment;
	
	private User user;
	
	private Users um;
	private Timeline tm ;
	private Comments cm;
	
	private jdbcconn jdb=new jdbcconn("root","1234");
	
	
	public TakeAll(String access_token){
		um =new Users();
		tm = new Timeline();
		cm = new Comments();
		
		um.client.setToken(access_token);
		tm.client.setToken(access_token);
		cm.client.setToken(access_token);
		
		page=new Paging(1,50);
	}
	
	public void setUserById(String id){
		try{
			userID=id;
			
			user= um.showUserById(userID);
			
			
			
			status[0] = tm.getUserTimelineByUid(userID,page,0,0);
			statusNum=status[0].getTotalNumber();
			pageNum=(int)Math.ceil(statusNum/50);
			status=new StatusWapper[pageNum];
			
			
		}catch(WeiboException e){
			e.printStackTrace();
		}
	}
	public void setUserByScreenName(String screen_name){
		try{
			user= um.showUserByScreenName(screen_name);			
			userID=user.getId();
			
			status[0] = tm.getUserTimelineByName(screen_name,page,0,0);
			statusNum=status[0].getTotalNumber();
			pageNum=(int)Math.ceil(statusNum/50);
			status=new StatusWapper[pageNum];
		}catch(WeiboException e){
			e.printStackTrace();
		}
	}
public static void main(String arg[]) throws IOException{
	TakeAll t=new TakeAll("2.00dUA_TDsoBBiDfa28d95ca6FNfAwD");
	t.setUserByScreenName("天才小熊猫");
	t.insertUserInfoIntoDB("sina");
	t.insertUserStatus("sina");
}
	public void insertUserInfoIntoDB(String DB){
		Calendar cl=Calendar.getInstance();
		cl.setTime(user.getCreatedAt());
		proceedUserInfo();
		quest="INSERT INTO User("+userTabCol+") VALUES("+user.getId()+','+"'"+user.getScreenName()+"'"+','+"'"+user.getName()+"'"+','+user.getProvince()+','+user.getCity()+','+"'"+user.getLocation()+"'"+','+"'"+user.getDescription()+"'"+','+"'"+user.getUserDomain()+"'"+','+"'"+user.getGender()+"'"+','+user.getFollowersCount()+','+user.getFriendsCount()+','+user.getStatusesCount()+','+user.getFavouritesCount()+','+"'"+cl.get(1)+"-"+(cl.get(2)+1)+"-"+cl.get(5)+" "+cl.get(11)+':'+cl.get(12)+':'+cl.get(13)+"'"+','+user.isAllowAllActMsg()+','+user.isVerified()+','+user.isAllowAllComment()+','+user.isFollowMe()+','+user.getOnlineStatus()+','+user.getBiFollowersCount()+','+"'"+user.getLang()+"'"+");";
		try{
			jdb.execute("USE "+DB);
			jdb.execute(quest);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}
	public void insertUserStatus(String DB) throws IOException{
		Calendar cl=Calendar.getInstance();
			try{
				for(int j=0;j<pageNum;j++){
						status[j]=tm.getUserTimelineByUid(userID, page,0,0);
						page.setPage(j+1);
					}
			}
			catch(Exception e){
				System.out.println(e.getStackTrace());
			}
			for(int j=0;j<pageNum;j++)
				for(Status s : status[j].getStatuses()){
				s.setText(delQ(s.getText()));
				cl.setTime(s.getCreatedAt());
				quest="INSERT INTO status("+statusTabCol+") VALUES("+s.getUser().getId()+','+s.getId()+','+"'"+cl.get(1)+"-"+(cl.get(2)+1)+"-"+cl.get(5)+" "+cl.get(11)+':'+cl.get(12)+':'+cl.get(13)+"'"+','+"'"+s.getText()+"'"+','+"'"+s.getSource().getName()+"'"+","+s.isFavorited()+","+s.getRepostsCount()+","+s.getCommentsCount()+","+s.getVisible().getType()+");";
				try{
					jdb.execute("USE "+DB);
					jdb.execute(quest);
				}
				catch(Exception e){
					System.out.println(e.getStackTrace());
					System.out.println(quest);
					System.in.read();
				}
			}
	}
	public void proceedUserInfo(){
		user.setScreenName(delQ(user.getScreenName()));
		user.setDescription(delQ(user.getDescription()));
		user.setName(delQ(user.getName()));
		user.setUserDomain(delQ(user.getUserDomain()));
		
	}
	private String delQ(String a){
			if(a.contains("'")){
				a=a.replace("'","\\'");
			}
		return a;
	}
//	public  static void main(String arg[]){
//		jdbcconn jdb=new jdbcconn("root","1234");
//		um.client.setToken(access_token);
//		try {
//			User user = um.showUserByScreenName(uid);
//			quest+=user.getId()+','+"'"+user.getScreenName()+"'"+','+"'"+user.getName()+"'"+','+user.getProvince()+','+user.getCity()+','+"'"+user.getLocation()+"'"+','+"'"+user.getDescription()+"'"+','+"'"+user.getUserDomain()+"'"+','+"'"+user.getGender()+"'"+','+user.getFollowersCount()+','+user.getFriendsCount()+','+user.getStatusesCount()+','+user.getFavouritesCount()+','+"'"+user.getCreatedAt()+"'"+','+user.isAllowAllActMsg()+','+user.isVerified()+','+user.isAllowAllComment()+','+user.isFollowMe()+','+user.getOnlineStatus()+','+user.getBiFollowersCount()+','+"'"+user.getLang()+"'"+");";
//			Log.logInfo(user.toString());
//		} catch (WeiboException e) {
//			e.printStackTrace();
//		}
//		try{
//			jdb.execute("USE java;");
//			jdb.execute(quest);
//			System.out.println(quest);
//		}
//		catch(Exception e){
//			System.out.println('a');
//		}
//	}
}