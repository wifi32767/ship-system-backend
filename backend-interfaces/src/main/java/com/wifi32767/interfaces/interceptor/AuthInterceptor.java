package com.wifi32767.interfaces.interceptor;

import com.wifi32767.common.Permission;
import com.wifi32767.common.enums.Module;
import com.wifi32767.domain.context.UserContext;
import com.wifi32767.domain.user.model.UserVO;
import com.wifi32767.domain.user.service.UserService;
import com.wifi32767.infra.redis.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private RedisService redisService;

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        if (!(handler instanceof HandlerMethod method)) {
            return true; // 放行静态资源
        }

        // 检查注解，没有注解的视为public
        if (!method.hasMethodAnnotation(Permission.class)) {
            return true;
        }

        // 从cookie中获取token
        String token = null;
        if (req.getCookies() != null) {
            for (jakarta.servlet.http.Cookie cookie : req.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (token == null || token.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        Permission permission = method.getMethodAnnotation(Permission.class);
        Module module = permission.value();

        UserVO user = null;
        try {
            String userName = redisService.getValue("token:" + token);
            user = userService.getUserInfo(userName);
        } catch (Exception e) {
            log.error("Token 验证失败", e);
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        UserContext.set(user);
        boolean isAllowed = user.getUserRole().getModules().contains(module.getModuleId());
        if (!isAllowed) {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res,
                                Object handler, Exception ex) {
        UserContext.remove(); // 防止内存泄漏
    }
}