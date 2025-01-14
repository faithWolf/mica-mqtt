/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">http://www.apache.org/licenses/LICENSE-2.0</a>
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dreamlu.iot.mqtt.core.util.timer;


/**
 * TimerTask
 *
 * @author kafka、guest、L.cm
 */
public abstract class TimerTask implements Runnable {

	/**
	 * 延迟时间
	 */
	protected final long delayMs;
	/**
	 * 时间槽
	 */
	protected TimerTaskEntry timerTaskEntry;

	protected TimerTask() {
		this(30000L);
	}

	protected TimerTask(long delayMs) {
		this.delayMs = delayMs;
	}

	public void cancel() {
		synchronized (this) {
			if (timerTaskEntry != null) {
				timerTaskEntry.remove();
			}
			timerTaskEntry = null;
		}
	}

	public void setTimerTaskEntry(TimerTaskEntry entry) {
		// if this timerTask is already held by an existing timer task entry,
		// we will remove such an entry first.
		synchronized (this) {
			if (timerTaskEntry != null && timerTaskEntry != entry) {
				timerTaskEntry.remove();
			}
			timerTaskEntry = entry;
		}
	}

	public TimerTaskEntry getTimerTaskEntry() {
		return timerTaskEntry;
	}

	public long getDelayMs() {
		return delayMs;
	}

}
