package com.llife.common;
import java.sql.*; 

public class jdbcconn{ 
 Connection conn=null; 
 java.sql.Statement stmt =null;
public jdbcconn(String user,String password){//构造函数加载驱动 
try{ 
	Class.forName("com.mysql.jdbc.Driver"); 
	conn =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=GBK", user, password);
	stmt=conn.createStatement();
}

catch(Exception e){ 
	System.out.println(e.getMessage()); 
	System.out.println("没进去");
} 
} 
public static void main(String arg[]){
	jdbcconn jb=new jdbcconn("root","1234");
}
public void execute(String command)throws SQLException{
		try{
		stmt.executeUpdate(command);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
public ResultSet getResultSet(String DBName,String Tab){
		ResultSet rs=null;
		try{
			stmt.executeUpdate("use "+DBName+";");
			
			rs=stmt.executeQuery("select * from "+Tab+";");
			
				
	
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return rs;
	}
} 
