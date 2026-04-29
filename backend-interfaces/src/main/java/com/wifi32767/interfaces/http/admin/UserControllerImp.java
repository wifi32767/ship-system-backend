package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.common.enums.Module;
import com.wifi32767.domain.system.model.UserRoleVO;
import com.wifi32767.domain.user.model.UserVO;
import com.wifi32767.domain.user.service.UserService;
import com.wifi32767.infra.redis.RedisService;
import com.wifi32767.interfaces.common.Permission;
import com.wifi32767.interfaces.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/user")
@Tag(name = "用户管理", description = "提供用户账号相关操作")
public class UserControllerImp implements UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisService redisService;

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Operation(summary = "用户登录", description = "使用用户名和密码进行登录，成功后返回一个token")
    public Response<String> login(@RequestParam String username, @RequestParam String password) {
        try {
            String token = userService.login(username, password);
            // 最好用事务来保证原子性，但这里简化处理
            redisService.addToSet("user:" + username, token);
            redisService.setValue("token:" + token, username, 3600 * 72);
            return new Response<>(token);
        } catch (Exception e) {
            log.error("Error during login: ", e);
            return new Response<>(Response.ERROR, "Login failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Permission(Module.USER)
    @Operation(summary = "用户注册", description = "使用用户信息进行注册，成功后返回新用户的ID")
    public Response<Integer> register(@RequestBody UserVO user) {
        try {
            return new Response<>(userService.register(user));
        } catch (Exception e) {
            log.error("Error during registration: ", e);
            return new Response<>(Response.ERROR, "Registration failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @Operation(summary = "用户登出", description = "用户登出，删除对应的token")
    public Response<String> logout(@CookieValue(value = "token", required = false) String token) {
        if (!StringUtils.isEmpty(token)) {
            redisService.remove("token:" + token);
        }
        return new Response<>(null);
    }

    @Override
    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    @Permission(Module.USER)
    @Operation(summary = "获取所有用户信息", description = "返回系统中所有用户的基本信息列表")
    public Response<List<UserVO>> getAllUsersInfo(@RequestParam int pageNum, @RequestParam int pageSize) {
        try {
            return new Response<>(userService.getAllUsersInfo(pageNum, pageSize));
        } catch (Exception e) {
            log.error("Error fetching all users: ", e);
            return new Response<>(Response.ERROR, "Failed to fetch users: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    @Permission(Module.USER)
    @Operation(summary = "删除用户", description = "根据用户名删除用户账号")
    public Response<String> deleteUser(@RequestBody String username) {
        try {
            userService.deleteUser(username);
            return new Response<>("User deleted successfully");
        } catch (Exception e) {
            log.error("Error during user deletion: ", e);
            return new Response<>(Response.ERROR, "User deletion failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    @Operation(summary = "修改用户信息", description = "根据用户名修改用户信息")
    public Response<String> updateUserInfo(@RequestBody UserVO user) {
        try {
            userService.updateUserInfo(user);
            return new Response<>("User info updated successfully");
        } catch (Exception e) {
            log.error("Error during user info update: ", e);
            return new Response<>(Response.ERROR, "User info update failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/allUsersRole", method = RequestMethod.GET)
//    @Permission(Module.USER)
    @Operation(summary = "获取用户角色", description = "返回用户角色列表（表示所有用户角色）")
    public Response<List<UserRoleVO>> getAllUsersRole() {
        try {
            return new Response<>(userService.getAllUsersRole());
        } catch (Exception e) {
            log.error("Error fetching all users role: ", e);
            return new Response<>(Response.ERROR, "Failed to fetch users role: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/addUserRole", method = RequestMethod.POST)
    @Permission(Module.USER)
    @Operation(summary = "添加用户角色", description = "添加用户角色")
    public Response<String> addUserRole(@RequestBody UserRoleVO userRoleVO) {
        try {
            userService.addUserRole(userRoleVO);
            return new Response<>("User role added successfully");
        } catch (Exception e) {
            log.error("Error during user role addition: ", e);
            return new Response<>(Response.ERROR, "User role addition failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/removeUserRole", method = RequestMethod.DELETE)
    @Permission(Module.USER)
    @Operation(summary = "删除用户角色", description = "根据角色ID删除用户角色")
    public Response<String> removeUserRole(@RequestParam int roleId) {
        try {
            userService.removeUserRole(roleId);
            return new Response<>("User role removed successfully");
        } catch (Exception e) {
            log.error("Error during user role removal: ", e);
            return new Response<>(Response.ERROR, "User role removal failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/allModules", method = RequestMethod.GET)
    @Permission(Module.USER)
    @Operation(summary = "获取所有模块权限", description = "返回系统中所有模块权限列表")
    public Response<Map<Integer, String>> getAllModules() {
        return new Response<>(Arrays.stream(Module.values()).collect(Collectors.toMap(
                Module::getModuleId,
                module -> module.getModuleName()
        )));
    }

    @Override
    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    @Permission(Module.USER)
    @Operation(summary = "添加用户权限", description = "为指定角色添加权限")
    public Response<String> addPermission(@RequestParam int roleId, @RequestParam int permissionId) {
        try {
            userService.addPermission(roleId, permissionId);
            return new Response<>("Permission added successfully");
        } catch (Exception e) {
            log.error("Error during permission addition: ", e);
            return new Response<>(Response.ERROR, "Permission addition failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/addPermissionBatch", method = RequestMethod.POST)
    @Permission(Module.USER)
    @Operation(summary = "批量添加用户权限", description = "为指定角色批量添加权限")
    public Response<String> addPermissionBatch(@RequestParam int roleId, @RequestBody List<Integer> permissionIds) {
        try {
            userService.addPermissionBatch(roleId, permissionIds);
            return new Response<>("Permissions added successfully");
        } catch (Exception e) {
            log.error("Error during permission batch addition: ", e);
            return new Response<>(Response.ERROR, "Permission batch addition failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/removePermission", method = RequestMethod.DELETE)
    @Permission(Module.USER)
    @Operation(summary = "删除用户权限", description = "从指定角色中删除权限")
    public Response<String> removePermission(@RequestParam int roleId, @RequestParam int permissionId) {
        try {
            userService.removePermission(roleId, permissionId);
            return new Response<>("Permission removed successfully");
        } catch (Exception e) {
            log.error("Error during permission removal: ", e);
            return new Response<>(Response.ERROR, "Permission removal failed: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/removePermissionBatch", method = RequestMethod.DELETE)
    @Permission(Module.USER)
    @Operation(summary = "批量删除用户权限", description = "从指定角色中批量删除权限")
    public Response<String> removePermissionBatch(@RequestParam int roleId, @RequestBody List<Integer> permissionIds) {
        try {
            userService.removePermissionBatch(roleId, permissionIds);
            return new Response<>("Permissions removed successfully");
        } catch (Exception e) {
            log.error("Error during permission batch removal: ", e);
            return new Response<>(Response.ERROR, "Permission batch removal failed: " + e.getMessage());
        }
    }
}
