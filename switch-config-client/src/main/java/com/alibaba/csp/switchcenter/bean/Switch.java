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
package com.alibaba.csp.switchcenter.bean;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.csp.switchcenter.core.SwitchCallback;

/**
 * @author jielong.hjl
 */
public class Switch {

    /**
     * switch name
     */
    private String name;

    /**
     * switch namespace
     */
    private String nameSpace;

    /**
     * switch's option value description
     */
    private String valueDes;

    /**
     * switch's description
     */
    private String des;

    /**
     * switch's value json string
     */
    private String value;

    /**
     * switch's default value json string
     */
    private String defaultValue;

    /**
     * switch's option value
     */
    private ValueConfig[] valueConfig;

    /**
     * switch's level
     */
    private Level level = Level.p1;

    /**
     * switch's field type
     */
    private Class<?> type;

    /**
     * switch's callback
     */
    @SuppressWarnings("rawtypes")
    private Class<? extends SwitchCallback> callback;

    /**
     * switch's field generic
     */
    private Type generic;
    
    private long lastModifiedTime;
    
    private Set<String> tags;

	public static enum Level {
        /**
         * vital
         */
        p1,
        /**
         * important
         */
        p2,
        /**
         * general
         */
        p3,
        /**
         * significant
         */
        p4
    }

    /**
     *
     * @param name switch name
     * @param strValue switch value
     * @param nameSpace switch namespace
     * @param valueDes switch value description
     * @param des switch description
     * @param defaultValue switch default value
     * @param valueConfig switch value config
     * @param level switch value level
     * @param type switch value type
     */
    public Switch(String name, String strValue, String nameSpace, String valueDes, String des,
                  String defaultValue, ValueConfig[] valueConfig, Level level,
                  Class<?> type) {
        super();
        this.name = name;
        this.value = strValue;
        this.nameSpace = nameSpace;
        this.valueDes = valueDes;
        this.des = des;
        this.defaultValue = defaultValue;
        this.valueConfig = valueConfig;
        this.level = level;
        this.type = type;
    }

    public Switch() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValueDes() {
        return valueDes;
    }

    public void setValueDes(String valueDes) {
        this.valueDes = valueDes;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ValueConfig[] getValueConfig() {
        return valueConfig;
    }

    public void setValueConfig(ValueConfig[] valueConfig) {
        this.valueConfig = valueConfig;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    @SuppressWarnings("rawtypes")
    public Class<? extends SwitchCallback> getCallback() {
        return callback;
    }

    @SuppressWarnings("rawtypes")
    public void setCallback(Class<? extends SwitchCallback> callback) {
        this.callback = callback;
    }

    public Type getGeneric() {
        return generic;
    }

    public void setGeneric(Type generic) {
        this.generic = generic;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    public long getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(long lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	public void addTags(String... tags) {
		if (this.tags == null) {
			this.tags = new HashSet<String>();
		}

		if (tags == null || tags.length < 1) {
			return;
		}

		this.tags.addAll(Arrays.asList(tags));
	}

	@Override
	public String toString() {
		return "Switch [name=" + name + ", nameSpace=" + nameSpace
				+ ", valueDes=" + valueDes + ", des=" + des + ", value="
				+ value + ", defaultValue=" + defaultValue + ", valueConfig="
				+ Arrays.toString(valueConfig) + ", level=" + level + ", type="
				+ type + ", callback=" + callback + ", generic=" + generic
				+ "]";
	}
}