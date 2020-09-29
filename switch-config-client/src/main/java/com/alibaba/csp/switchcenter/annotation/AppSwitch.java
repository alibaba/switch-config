/*
 * Copyright 1999-2020 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.switchcenter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alibaba.csp.switchcenter.bean.Switch;
import com.alibaba.csp.switchcenter.bean.Switch.Level;
import com.alibaba.csp.switchcenter.core.SwitchCallback;

/**
 * @author chudong
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AppSwitch {

	/**
	 * Returns the description of the usage.
	 *
	 * @return the description of the usage
	 */
	String des();

	/**
	 * Returns the description of the switch value.
	 *
	 * @return the description of the switch value
	 */
	String valueDes() default "";

	/**
	 * switch's option value (optional)
	 *
	 * @return switch's option value (optional)
	 */
	String[] values() default {};

	/**
	 * switch level
	 *
	 * @return switch level
	 * @see Switch
	 */
	Switch.Level level() default Level.p4;

	/**
	 * switch callback
	 *
	 * @return switch callback
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends SwitchCallback> callback() default SwitchCallback.class;

	/**
	 * switch tags
	 * 
	 * @return switch tags
	 */
	String[] tags() default {};

}
