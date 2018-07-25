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
package com.redis.casaviva.shop.core;

import java.time.Instant;

/**
 *
 * @author user
 */
public interface Article {
	public String getCode();
	public String getBarcode();
	public String getSector();
	public String getCategory();
	public String getDescription();
	public String getUnit();
	public Double getSellPrice();
	public Double getSellPriceTemp();
	public Instant getSellPriceTime();
}
