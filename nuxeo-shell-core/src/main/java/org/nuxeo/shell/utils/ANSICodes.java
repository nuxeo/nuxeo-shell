/*
 * (C) Copyright 2006-2010 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     bstefanescu
 */
package org.nuxeo.shell.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jline.ANSIBuffer;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 */
public class ANSICodes {

    protected static final Pattern TPL = Pattern.compile("\\{([A-Za-z0-9]+)\\}");

    public static final int OFF = 0;

    public static final int BOLD = 1;

    public static final int UNDERSCORE = 4;

    public static final int BLINK = 5;

    public static final int REVERSE = 7;

    public static final int CONCEALED = 8;

    public static final int FG_BLACK = 30;

    public static final int FG_RED = 31;

    public static final int FG_GREEN = 32;

    public static final int FG_YELLOW = 33;

    public static final int FG_BLUE = 34;

    public static final int FG_MAGENTA = 35;

    public static final int FG_CYAN = 36;

    public static final int FG_WHITE = 37;

    public static final int BG_BLACK = 40;

    public static final int BG_RED = 41;

    public static final int BG_GREEN = 42;

    public static final int BG_YELLOW = 43;

    public static final int BG_BLUE = 44;

    public static final int BG_MAGENTA = 45;

    public static final int BG_CYAN = 46;

    public static final int BG_WHITE = 47;

    protected static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("off", OFF);
        map.put("header", BOLD);
        map.put("bold", BOLD);
        map.put("underscore", UNDERSCORE);
        map.put("blink", BLINK);
        map.put("reverse", REVERSE);
        map.put("concealed", CONCEALED);
        map.put("black", FG_BLACK);
        map.put("red", FG_RED);
        map.put("green", FG_GREEN);
        map.put("yellow", FG_YELLOW);
        map.put("blue", FG_BLUE);
        map.put("magenta", FG_MAGENTA);
        map.put("cyan", FG_CYAN);
        map.put("white", FG_WHITE);
        map.put("bg.black", BG_BLACK);
        map.put("bg.red", BG_RED);
        map.put("bg.green", BG_GREEN);
        map.put("bg.yellow", BG_YELLOW);
        map.put("bg.blue", BG_BLUE);
        map.put("bg.magenta", BG_MAGENTA);
        map.put("bg.cyan", BG_CYAN);
        map.put("bg.white", BG_WHITE);
    }

    public static int getCode(String key) {
        Integer code = map.get(key.toLowerCase());
        if (code == null) {
            try {
                return Integer.parseInt(key);
            } catch (NumberFormatException e) {
                return -1;
            }
        } else {
            return code.intValue();
        }
    }

    public static void append(ANSIBuffer buf, String text, String codeKey, boolean wiki) {
        int code = getCode(codeKey);
        if (code > -1) {
            if (wiki && code == BOLD) {
                buf.append("*")
                   .append(text)
                   .append("*");
            } else {
                buf.attrib(text, code);
            }
        } else if (wiki) {
            buf.append("{")
               .append(codeKey)
               .append("}")
               .append(text)
               .append("{")
               .append(codeKey)
               .append("}");
        } else {
            buf.append(text);
        }
    }

    public static void appendTemplate(ANSIBuffer buf, String content, boolean wiki) {
        Matcher m = TPL.matcher(content);
        int s = 0;
        while (m.find(s)) {
            String token = m.group(1);
            String key = '{' + token + '}';
            int i = content.indexOf(key, m.end());
            if (i == -1) {
                buf.append(content.substring(s, m.end()));
                s = m.end();
            } else {
                buf.append(content.substring(s, m.start()));
                String text = content.substring(m.end(), i);
                ANSICodes.append(buf, text, token, wiki);
                s = i + key.length();
            }
        }
        buf.append(content.substring(s, content.length()));
    }

}
