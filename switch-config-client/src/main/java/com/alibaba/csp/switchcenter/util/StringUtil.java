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
package com.alibaba.csp.switchcenter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jielong.hjl
 */
public class StringUtil {

    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * <p>
     * Checks if a String is whitespace, empty ("") or null.
     * </p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Checks if a String is not empty (""), not null and not whitespace only.
     * </p>
     *
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if the String is not empty and not null and not
     *         whitespace
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    // Empty checks
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Checks if a String is empty ("") or null.
     * </p>
     *
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>
     * NOTE: This method changed in Lang version 2.0. It no longer trims the
     * String. That functionality is available in isBlank().
     * </p>
     *
     * @param str
     *            the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * @param string
     * @return
     */
    public static boolean nullOrBlank(String string) {
        if (string == null || "".equals(string.trim())) {
            return true;
        }
        return false;
    }

    /**
     * caculate string's MD5 value
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        StringBuilder md5Sb = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());

            for (byte b : md.digest()) {
                md5Sb.append(Integer.toHexString(b & 0xFF));
            }
        } catch (NoSuchAlgorithmException ex) {
            md5Sb = null;
        }

        return md5Sb == null ? "" : md5Sb.toString();
    }

    /**
     * @param content
     * @param length
     * @return
     */
    public static String flushLeft(String content, long length) {
        char c = ' ';

        StringBuilder builder = new StringBuilder();
        builder.append(content);

        length = length - content.length();
        for (int i = 0; i < length; i++) {
            builder.append(c);
        }

        return builder.toString();
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

}
