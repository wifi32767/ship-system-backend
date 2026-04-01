package com.wifi32767.interfaces.http;

import com.wifi32767.domain.user.model.SimpleUserVO;
import com.wifi32767.domain.user.model.UserVO;
import com.wifi32767.domain.user.service.UserService;
import com.wifi32767.interfaces.response.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserControllerImp implements UserController {

    @Resource
    private UserService userService;

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<String> login(String username, String password) {
        try {
            String token = userService.login(username, password);
            // TODO: 将token存入redis，设置过期时间
            return new Response<>(token);
        } catch (Exception e) {
            log.error("Error during login: ", e);
            return new Response<>(Response.ERROR, "Login failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response<Integer> register(UserVO user) {
        try {
            return new Response<>(userService.register(user));
        } catch (Exception e) {
            log.error("Error during registration: ", e);
            return new Response<>(Response.ERROR, "Registration failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response<String> logout() {
        // TODO: 删除redis token
        return new Response<>(null);
    }

    @Override
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Response<List<SimpleUserVO>> getAllUsersInfo() {
        try {
            return new Response<>(userService.getAllUsersInfo());
        } catch (Exception e) {
            log.error("Error fetching all users: ", e);
            return new Response<>(Response.ERROR, "Failed to fetch users: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Response<String> deleteUser(String username) {
        try {
            userService.delete(username);
            return new Response<>("User deleted successfully");
        } catch (Exception e) {
            log.error("Error during user deletion: ", e);
            return new Response<>(Response.ERROR, "User deletion failed: " + e.getMessage());
        }
    }
}
