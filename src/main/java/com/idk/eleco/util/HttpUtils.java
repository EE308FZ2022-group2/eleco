package com.idk.eleco.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * http工具类
 */
public class HttpUtils {

    /**
     * 获取请求ip
     *
     * @param request
     * @return
     */
    public   String getIp(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || unknown.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            String benji = "127.0.0.1";
            String bj = "0:0:0:0:0:0:0:1";
            if (benji.equals(ipAddress) || bj.equals(ipAddress)) {
                ///根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if(inet != null){
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        ///对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        int i = 15;
        String s = ",";
        if (ipAddress != null && ipAddress.length() > i) {
            if (ipAddress.indexOf(s) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public String getIpAddr(HttpServletRequest request) {
        //目前则是网关ip
        String ip = request.getHeader("X-Real-IP");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(',');
            if (index != -1) {
                //只获取第一个值
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            //取不到真实ip则返回空，不能返回内网地址。
            return "";
        }
    }

    /**
     * 获取真实的ip
     *
     * @param request
     * @return
     * @throws UnknownHostException
     */
    public  String getRealIp(HttpServletRequest request) {
        String ip;
        // 有的user可能使用代理，为处理用户使用代理的情况，使用x-forwarded-for
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = request.getHeader("x-forwarded-for");
        }
        if ("127.0.0.1".equals(ip)) {
            try {
        // 获取本机真正的ip地址
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ip;
    }


}