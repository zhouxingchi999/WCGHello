package com.llife.common;

import java.sql.ResultSet;
import java.sql.SQLException;

public class timeAnalysis {
	public static void main(String arg[]){
		String time;
		int t;
		int[] timer=new int[24];
		for(int e:timer){
			e=0;
		}
		jdbcconn jdb=new jdbcconn("root","1234");
		ResultSet rs=jdb.getResultSet("Sina", "status");
		try {
			while(rs.next()){
				time=rs.getString("微博创建时间");
				System.out.println(time);
				t=10*(time.charAt(11)-48)+time.charAt(12)-48;
				timer[t]++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int e:timer)
		System.out.println(e);
		
	}
}
