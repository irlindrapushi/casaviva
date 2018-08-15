/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redis.utils.sets;

import java.time.Instant;

/**
 *
 * @author Redjan Shabani
 * @param <E>
 */
public interface History<E> extends Set<E> {
	
	
	
	public interface Event {
		public Instant instant();
		public Double weight();
		public Double value();
	}
}
