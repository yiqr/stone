package com.stone.commons.utils;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author rose
 * @date 2022-11-17 22:28
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtils {


    public static void response(ServletResponse response, MediaType mediaType, int statusCode, String message)
            throws IOException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType(mediaType.toString());
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setStatus(statusCode);
        PrintWriter writer = resp.getWriter();
        writer.write(message);
        writer.flush();
    }
}
