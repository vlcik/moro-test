package cz.moro.sokrates.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cz.moro.sokrates.service.IUserService;
 
public class UserCountInterceptor extends HandlerInterceptorAdapter {
 
    private static final Logger logger = LoggerFactory.getLogger(UserCountInterceptor.class);
 
    @Autowired
	private IUserService userService;
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        
    	Long count = userService.getUserCount();
        logger.info("Count: " + count.toString());
        request.setAttribute("count", count.toString());
        //if returned false, we need to make sure 'response' is sent
        return true;
    }
}