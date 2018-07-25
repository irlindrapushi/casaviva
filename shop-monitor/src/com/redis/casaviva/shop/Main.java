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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author user
 */
public class Main {
	
	private static final Preferences PREFERENCES = Preferences.userNodeForPackage(Main.class);
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		
		
		
		Stock stock = StockSQL.select(PREFERENCES.node("stock").get("code", ""));
		
		FrameStock frame = new FrameStock();
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}

			@Override
			public void windowOpened(WindowEvent we) {
				frame.setStock(stock);
			}				
		});

		
		
		
		
		
		
		
		
		
		try {
//			UIManager.setLookAndFeel(com.jtattoo.plaf.mcwin.McWinLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.noire.NoireLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.texture.TextureLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.smart.SmartLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.mint.MintLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.luna.LunaLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.hifi.HiFiLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.graphite.GraphiteLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.fast.FastLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.class.getName());
//			UIManager.setLookAndFeel(com.jtattoo.plaf.aero.AeroLookAndFeel.class.getName());
			UIManager.setLookAndFeel(com.jtattoo.plaf.acryl.AcrylLookAndFeel.class.getName());
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		frame.setVisible(true);
	}
}
