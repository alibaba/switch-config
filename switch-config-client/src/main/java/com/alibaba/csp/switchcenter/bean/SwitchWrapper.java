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

import java.util.Arrays;
import java.util.Set;

import com.alibaba.csp.switchcenter.bean.Switch.Level;

/**
 * @author jielong.hjl
 */
public class SwitchWrapper {

    private String name;

    private String nameSpace;

    private String valueDes;

    private String des;

    private String value;

    private String defaultValue;

    private ValueConfig[] valueConfig;

    private Level level;

    private String type;

    private Set<String> tags;

    public SwitchWrapper(String name, String nameSpace) {
        this.name = name;
        this.nameSpace = nameSpace;
    }

    public SwitchWrapper(Switch bean, String type) {
        this.name = bean.getName();
        this.nameSpace = bean.getNameSpace();
        this.valueDes = bean.getValueDes();
        this.des = bean.getDes();
        this.value = bean.getValue();
        this.defaultValue = bean.getDefaultValue();
        this.valueConfig = bean.getValueConfig();
        this.level = bean.getLevel();
        this.type = type;
        this.tags = bean.getTags();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
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

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "SwitchWrapper [name=" + name + ", nameSpace=" + nameSpace
            + ", valueDes=" + valueDes + ", des=" + des + ", value="
            + value + ", defaultValue=" + defaultValue + ", valueConfig="
            + Arrays.toString(valueConfig) + ", level=" + level + ", type="
            + type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
            + ((nameSpace == null) ? 0 : nameSpace.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        SwitchWrapper other = (SwitchWrapper) obj;
        if (name == null) {
            if (other.name != null) { return false; }
        } else if (!name.equals(other.name)) { return false; }
        if (nameSpace == null || "".equals(nameSpace.trim())) {
            if (other.nameSpace != null && !"".equals(other.nameSpace.trim())) { return false; }
        } else if (!nameSpace.equals(other.nameSpace)) { return false; }
        return true;
    }

}
