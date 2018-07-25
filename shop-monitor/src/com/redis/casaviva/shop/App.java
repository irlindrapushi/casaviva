/*
 * Copyright (C) 2018 user
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.redis.casaviva.shop;

import com.redis.casaviva.shop.core.Stock;
import com.redis.casaviva.shop.mssql.StockSQL;
import com.redis.casaviva.shop.swing.FrameStock;
import java.util.prefs.Preferences;

/**
 *
 * @author user
 */
public class App {
	
	private static final Preferences PREFERENCES = Preferences.userNodeForPackage(App.class);
	
	public static void main(String[] args) {
		Stock stock = StockSQL.select(PREFERENCES.node("stock").get("code", ""));
		if(stock != null) {			
			FrameStock frame = new FrameStock(stock);
			frame.setVisible(true);
		}
	}
}
