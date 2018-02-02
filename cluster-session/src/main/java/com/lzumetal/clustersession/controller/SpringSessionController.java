package com.lzumetal.clustersession.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author: liaosi
 * @date: 2018-02-02
 */
@RestController
@RequestMapping(value = "/springSession")
public class SpringSessionController {


    @RequestMapping("/testSession")
    public Map getSession(HttpServletRequest  request, HttpSession session) {
        String info = (String) session.getAttribute("info");
        if (info == null) {
            info = "Session中的此条信息由" + request.getLocalPort() + "端口的服务器生成";
            session.setAttribute("info", info);
        }
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("sessionId", session.getId());
        resMap.put("sessionClass", session.getClass());
        resMap.put("info", session.getAttribute("info"));
        return resMap;
    }

}
