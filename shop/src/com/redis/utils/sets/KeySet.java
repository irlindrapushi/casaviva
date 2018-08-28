/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.utils.sets;

import java.util.function.BiConsumer;

/**
 *
 * @author Redjan Shabani
 * @param <K>
 * @param <V>
 */
public interface KeySet<K,V> extends Set<V>{
	
	public void forEach(BiConsumer<K,V> consumer);
}
