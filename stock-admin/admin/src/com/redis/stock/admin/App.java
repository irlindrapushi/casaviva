/*
 * Copyright (C) 2018 Redjan Shabani
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
package com.redis.stock.admin;

import com.redis.stock.sql.SQL;
import com.redis.stock.admin.swing.FrameMain;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import com.redis.stock.core.Inventory;

/**
 *
 * @author Redjan Shabani
 */
public class App  {
	
	private static final Preferences PREFERENCES = Preferences.userNodeForPackage(App.class);
			
	public static void main(String[] args) {
		
		FrameMain frame = new FrameMain() {
			@Override
			public Inventory getInventory() {
				return SQL.getInventory();
			}
		};
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				try {
					SQL.disconnect();
				} 
				catch (SQLException ex) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void windowOpened(WindowEvent we) {
				try {
					SQL.connect();
				} 
				catch (SQLException ex) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
					JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
				}
			}			
		});
		
		frame.setVisible(true);
		
	}
}
