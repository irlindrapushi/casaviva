/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.remote.sql;

import com.redis.casaviva.shop.dc.Stock;
import com.redis.casaviva.shop.remote.SqlServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SqlStock {
	
	private static final String SQL = ""
		+ "SELECT code, barcode, description, "
		+	"M1, M5, M6, M7, M8, M9, M10, M11, M12, M13, M14, M15, M16, "
		+	"M1 + M5 + M6 + M7 + M8 + M9 + M10 + M11 + M12 + M13 + M14 + M15 + M16 AS M0 "
		+ "FROM ViewStocks "
		+ "ORDER BY code ASC";
	public static List<Stock> read(){
		List<Stock> stocks = new ArrayList<>();
		
		try(Connection conn = SqlServer.getInstance().getConnection()){
			PreparedStatement ps = conn.prepareStatement(SQL);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Stock stock = new Stock(rs.getString("code"), rs.getString("barcode"), rs.getString("description"));
				stock.put("M1", rs.getDouble("M1"));
				stock.put("M5", rs.getDouble("M5"));
				stock.put("M6", rs.getDouble("M6"));
				stock.put("M7", rs.getDouble("M7"));
				stock.put("M8", rs.getDouble("M8"));
				stock.put("M9", rs.getDouble("M9"));
				stock.put("M10", rs.getDouble("M10"));
				stock.put("M11", rs.getDouble("M11"));
				stock.put("M12", rs.getDouble("M12"));
				stock.put("M13", rs.getDouble("M13"));
				stock.put("M14", rs.getDouble("M14"));
				stock.put("M15", rs.getDouble("M15"));
				stock.put("M16", rs.getDouble("M16"));
				stock.put("M0", rs.getDouble("M0"));
				stocks.add(stock);
			}
		}
		catch (SQLException ex) {
			Logger.getLogger(SqlStock.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		return stocks;
	}
}
