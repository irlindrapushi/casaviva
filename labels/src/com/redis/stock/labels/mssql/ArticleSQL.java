/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.stock.labels.mssql;

import com.redis.stock.labels.core.Article;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Redjan Shabani
 */
public class ArticleSQL {
	
	private static final String SQL_SELECT = ""
		+ "SELECT "
		+ "	KOD AS code, "
		+ "	BC AS barcode, "
		+ "	KLASIF AS sector, "
		+ "	KLASIF2 AS category, "
		+ "	PERSHKRIM AS description, "
		+ "	KODFKL AS supplier, "
		+ "	NJESI AS unit "
		+ "FROM "
		+ "	CA.dbo.ARTIKUJ";
	public static void forEach(Consumer<Article> consumer) {		
		try(Connection cn = SQL.getConnection()) {
			PreparedStatement ps = cn.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Article article = new Article(
					rs.getString("code"), 
					rs.getString("barcode"), 
					rs.getString("sector"),
					rs.getString("category"),
					rs.getString("description"),
					rs.getString("supplier"), 
					rs.getString("unit")
				);
				consumer.accept(article);
			}
			
		} 
		catch (SQLException ex) {
			Logger.getLogger(ArticleSQL.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
