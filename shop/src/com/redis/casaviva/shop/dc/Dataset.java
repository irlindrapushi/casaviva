/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.casaviva.shop.dc;

import java.util.function.Consumer;

/**
 *
 * @author Redjan Shabani info@redis.com.al
 * @param <T>
 */
public interface Dataset<T> {
	public int size();
	public T get(String key);
	public void forEach(Consumer<T> consumer);
}
