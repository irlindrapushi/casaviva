/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.utils.sets;

import java.util.function.Consumer;

/**
 *
 * @author Redjan Shabani
 * @param <E>
 */
public interface Set<E> {
	
	public void forEach(Consumer<E> consumer);
}
