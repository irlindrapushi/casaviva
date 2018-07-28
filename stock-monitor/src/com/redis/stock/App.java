/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock;

import com.redis.stock.core.Enterprise;
import com.redis.stock.core.Item;
import com.redis.stock.core.Stock;
import com.redis.stock.mssql.ItemDB;
import com.redis.stock.swing.FrameEnterprise;
import com.redis.stock.utils.Set;
import java.util.Objects;
import java.util.prefs.Preferences;


/**
 *
 * @author Redjan Shabani
 */
public class App {
	
	
	
	
	
	
	
	private static final Preferences PREFS = Preferences.userNodeForPackage(App.class);
	
	
	
	
	public static void start(Enterprise enterprise) {
		FrameEnterprise frameEnterprise = new FrameEnterprise(enterprise);
		frameEnterprise.setVisible(true);
	}
	
	public static void pause() {
		
	}
	
	public static void resume() {
		
	}
	
	public static void close() {
		
	}
	
	
	
	public static void main(String[] args) {
		if(args.length == 2) {
			if(Objects.equals(args[0], "-e")) {
				Enterprise enterprise = new EnterpriseImpl(PREFS.node(args[1]));
				App.start(enterprise);
			}
			else {
				System.err.println("Invalid agument key: " + args[0]);
			}
		}
		else {
			System.err.println("Invalid number of arguments!");
		}
	}
	
	
	
	
	
	
	
	
	private static class EnterpriseImpl implements Enterprise {
	
		private final Preferences preferences;

		public EnterpriseImpl(Preferences preferences) {
			this.preferences = preferences;
		}	

		@Override
		public String getUid() {return this.preferences.get("uid", "");}

		@Override
		public String getName() {return this.preferences.get("name", "");}

		@Override
		public String getAddress() {return this.preferences.get("address", "");}

		@Override
		public String getPhone() {return this.preferences.get("phone", "");}

		@Override
		public String getWebsite() {return this.preferences.get("website", "");}

		@Override
		public String getEmail() { return this.preferences.get("email", "");}

		@Override
		public String toString() {return "[" + this.getUid() + "] " + this.getName() ;}

		@Override
		public Set<Stock> getStocks() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public Set<Item> getItems() {			
			return new ItemDB();
		}
	}
}
