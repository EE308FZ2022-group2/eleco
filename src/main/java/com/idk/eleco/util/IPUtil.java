package com.idk.eleco.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Description：IP工具类
 * @author zrt
 * @date 2018年9月22日 上午10:38:13
 */
public class IPUtil {

    // 方法1
    public String getNowIP1() throws IOException {
        String ip = null;
        String chinaz = "http://ip.chinaz.com";
        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
            Matcher m = p.matcher(inputLine.toString());
            if (m.find()) {
                String ipstr = m.group(1);
                ip = ipstr;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }
    // 方法2
    public String getNowIP2() throws IOException {
        String ip = null;
        BufferedReader br = null;
        try {
            URL url = new URL("https://v6r.ipip.net/?format=callback");
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            StringBuffer sb = new StringBuffer("");
            String webContent = "";
            while ((s = br.readLine()) != null) {
                sb.append(s + "\r\n");
            }
            webContent = sb.toString();
            int start = webContent.indexOf("(") + 2;
            int end = webContent.indexOf(")") - 1;
            webContent = webContent.substring(start, end);
            ip = webContent;
        } finally {
            if (br != null)
                br.close();
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }
    // 方法3
    public String getNowIP3() throws IOException {
        String ip = null;
        String objWebURL = "https://ip.900cha.com/";
        BufferedReader br = null;
        try {
            URL url = new URL(objWebURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            String webContent = "";
            while ((s = br.readLine()) != null) {
                if (s.indexOf("我的IP:") != -1) {
                    ip = s.substring(s.indexOf(":") + 1);
                    break;
                }
            }
        } finally {
            if (br != null)
                br.close();
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }
    // 方法4
    public String getNowIP4() throws IOException {
        String ip = null;
        String objWebURL = "https://bajiu.cn/ip/";
        BufferedReader br = null;
        try {
            URL url = new URL(objWebURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = "";
            String webContent = "";
            while ((s = br.readLine()) != null) {
                if (s.indexOf("互联网IP") != -1) {
                    ip = s.substring(s.indexOf("'") + 1, s.lastIndexOf("'"));
                    break;
                }
            }
        } finally {
            if (br != null)
                br.close();
        }
        if (StringUtils.isEmpty(ip)) {
            throw new RuntimeException();
        }
        return ip;
    }




}