/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.remote.sql;

import com.redis.casaviva.shop.dc.Dataset;
import com.redis.casaviva.shop.dc.Stock;
import java.util.function.Consumer;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 */
public class SqlDatasetStock implements Dataset<Stock>{

	@Override
	public int size() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Stock get(String key) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void forEach(Consumer<Stock> consumer) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
