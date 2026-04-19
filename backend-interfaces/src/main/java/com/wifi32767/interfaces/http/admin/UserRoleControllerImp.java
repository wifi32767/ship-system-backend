package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.ModuleVO;
import com.wifi32767.domain.system.model.UserRoleVO;
import com.wifi32767.interfaces.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/role")
@Tag(name = "用户角色接口", description = "提供用户角色相关操作")
public class UserRoleControllerImp implements UserRoleController {

    @Override
    @RequestMapping(path = "/module", method = RequestMethod.GET)
    @Operation(summary = "获取板块列表", description = "返回系统中所有的模块信息")
    public Response<List<ModuleVO>> moduleGet() {
        return null;
    }

    @Override
    @RequestMapping(path = "/module", method = RequestMethod.POST)
    @Operation(summary = "添加板块", description = "创建新的系统模块")
    public Response<String> moduleInsert(@RequestParam String module) {
        return null;
    }

    @Override
    @RequestMapping(path = "/module", method = RequestMethod.PUT)
    @Operation(summary = "修改板块", description = "更新现有模块的信息")
    public Response<String> moduleEdit(@RequestBody ModuleVO module) {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "获取角色列表", description = "返回系统中所有的用户角色信息")
    public Response<List<UserRoleVO>> roleGet() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "添加角色", description = "创建新的用户角色")
    public Response<String> roleInsert(@RequestParam UserRoleVO role) {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    @Operation(summary = "修改角色", description = "更新现有用户角色的信息")
    public Response<String> roleEdit(@RequestBody UserRoleVO role) {
        return null;
    }
}
