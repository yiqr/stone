package com.stone.commons.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author rose
 * @date 2022-11-19 18:10
 */
public class IPUtils {
    private static final String IP_UNKNOWN = "unknown";
    private static final String USER_AGENT = "User-Agent";

    /**
     * #func 获取IP地址
     * #desc getRemoteAddr
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 取X-Forwarded-For中第一个非unknown的有效IP字符串。
        if (ip.contains(",")) {
            ip = Arrays.stream(ip.split(",")).filter(v -> !IP_UNKNOWN.equalsIgnoreCase(v.trim()))
                    .findFirst().orElse(null);
        }
        return ip;
    }

    /**
     * 获得本机的机器名称，用来从配置文件中排除本机
     */
    public static String getLocalHostName() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (UnknownHostException e) {
            // ignore
        }
        return null;
    }

    /**
     * 返回主机名的全限定域名
     */
    public static String getFullyLocalHostName() {
        try {
            InetAddress inet = InetAddress.getLocalHost();
            return inet.getCanonicalHostName();
        } catch (UnknownHostException e) {
            // ignore
        }
        return null;
    }

    /**
     * 返回本机IP
     */
    public static String getLocalHostAddress() {
        try {
            InetAddress inet = InetAddress.getLocalHost();
            return inet.getHostAddress();
        } catch (UnknownHostException e) {
            return null;
        }
    }

    /**
     * 判断本机是否和传入的域名一致
     */
    public static boolean isDomainEqualsLocal(String domainName) {
        if (StringUtils.isBlank(domainName)) {
            return false;
        }
        try {
            return InetAddress.getByName(domainName).getHostAddress().equals(getLocalHostAddress());
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
