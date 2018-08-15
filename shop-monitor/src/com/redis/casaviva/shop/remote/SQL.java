/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.remote;

import com.redis.casaviva.shop.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public abstract class SQL {
	
	public abstract String getHostName();
	public abstract String getPortNumber();
	public abstract String getDatabaseName();
	public abstract String getUserName();
	public abstract String getUserPass();
	
	public abstract void setHostName(String hostName);
	public abstract void setPortNumber(String portNumber);
	public abstract void setDatabaseName(String databaseName);
	public abstract void setUserName(String userName);
	public abstract void setUserPass(String userpass);
	
	
	
	
	public String getJdbcConnectionURL(){
		return "jdbc:sqlserver://" 
			+ this.getHostName() + ":" 
			+ this.getPortNumber() + ";databaseName=" 
			+ this.getDatabaseName() + ";";
	}	
	
	
	public Connection getConnection() throws SQLException{
		return  DriverManager.getConnection(this.getJdbcConnectionURL(), this.getUserName(), this.getUserPass());
	}
	
	public static SQL getInstance(){
		
		return new SQL() {
			
			Preferences preferences = Preferences.userNodeForPackage(SQL.class);			
			
			@Override
			public String getHostName() {return this.preferences.get("host", "");}

			@Override
			public String getPortNumber() {return this.preferences.get("port", "");}

			@Override
			public String getDatabaseName() {return this.preferences.get("database", "");}

			@Override
			public String getUserName() {return this.preferences.get("username", "");}

			@Override
			public String getUserPass() {return this.preferences.get("password", "");}

			@Override
			public void setHostName(String hostName) {this.preferences.put("host", hostName);}

			@Override
			public void setPortNumber(String portNumber) {this.preferences.put("port", portNumber);}

			@Override
			public void setDatabaseName(String databaseName) {this.preferences.put("database", databaseName);}

			@Override
			public void setUserName(String userName) {this.preferences.put("username", userName);}

			@Override
			public void setUserPass(String userPass) {this.preferences.put("password", userPass);}
		};
	}
<<<<<<< HEAD:shop-monitor/src/com/redis/casaviva/shop/remote/SqlServer.java
        
	public static void main(String[] args) {
		SqlServer sqlServer = SqlServer.getInstance();
		sqlServer.setHostName("217.21.146.248");
		sqlServer.setDatabaseName("L86724204J");
		sqlServer.setPortNumber("3306");
		sqlServer.setUserName("sa");
		sqlServer.setUserPass("");
        
	}
=======
>>>>>>> 81cfd3c97557bc4479e4bdef2e0e46dee3d9734f:shop-monitor/src/com/redis/casaviva/shop/remote/SQL.java
}
