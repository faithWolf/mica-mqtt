/*
 * Copyright 2015 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License, version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package net.dreamlu.iot.mqtt.core.util;

/**
 * Math utility methods.
 *
 * @author netty
 */
public final class MathUtil {

	private MathUtil() {
	}

	/**
	 * Fast method of finding the next power of 2 greater than or equal to the supplied value.
	 *
	 * <p>If the value is {@code <= 0} then 1 will be returned.
	 * This method is not suitable for {@link Integer#MIN_VALUE} or numbers greater than 2^30.
	 *
	 * @param value from which to search for next power of 2
	 * @return The next power of 2 or the value itself if it is a power of 2
	 */
	public static int findNextPositivePowerOfTwo(final int value) {
		assert value > Integer.MIN_VALUE && value < 0x40000000;
		return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
	}

	/**
	 * Fast method of finding the next power of 2 greater than or equal to the supplied value.
	 * <p>This method will do runtime bounds checking and call {@link #findNextPositivePowerOfTwo(int)} if within a
	 * valid range.
	 *
	 * @param value from which to search for next power of 2
	 * @return The next power of 2 or the value itself if it is a power of 2.
	 * <p>Special cases for return values are as follows:
	 * <ul>
	 *     <li>{@code <= 0} = 1</li>
	 *     <li>{@code >= 2^30} = 2^30</li>
	 * </ul>
	 */
	public static int safeFindNextPositivePowerOfTwo(final int value) {
		return value <= 0 ? 1 : value >= 0x40000000 ? 0x40000000 : findNextPositivePowerOfTwo(value);
	}

}
