/*
 * Copyright (C) 2017 Redjan Shabani
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

package com.redis.aza.stock.admin.utils;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

/**
 *
 * @author Redjan Shabani
 * @param <K>
 * @param <V>
 */
public interface Dataset<K,V> {
	public void forEach(Consumer<V> consumer);
	public void forEach(BiConsumer<K,V> consumer);
	
	
	
	public default HashMap<K,V> toMap() {
		HashMap<K,V> map = new HashMap<>();
		this.forEach((k,v) -> map.put(k, v));
		return map;
	}
}
