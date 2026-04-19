package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.ClassVO;
import com.wifi32767.domain.system.model.TypeVO;
import com.wifi32767.interfaces.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/class")
@Tag(name = "分类管理接口", description = "提供一、二、三级分类相关操作")
public class ClassControllerImp implements ClassController {

    @Override
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Operation(summary = "获取所有一级分类列表", description = "获取所有一级分类列表")
    public Response<List<ClassVO>> getClasses() {
        return null;
    }

    @Override
    @RequestMapping(value = "/list/search", method = RequestMethod.GET)
    @Operation(summary = "搜索分类", description = "根据关键词模糊匹配分类名称")
    public Response<List<ClassVO>> searchClasses(@RequestParam String keyword) {
        return null;
    }

    @Override
    @RequestMapping(value = "/class", method = RequestMethod.POST)
    @Operation(summary = "创建一级分类", description = "创建一级分类")
    public Response<String> createClass(@RequestBody TypeVO classVO) {
        return null;
    }

    @Override
    @RequestMapping(value = "/class", method = RequestMethod.PUT)
    @Operation(summary = "更新一级分类信息", description = "更新一级分类信息")
    public Response<String> updateClass(@RequestBody TypeVO classVO) {
        return null;
    }

    @Override
    @RequestMapping(value = "/class", method = RequestMethod.DELETE)
    @Operation(summary = "删除一级分类", description = "根据ID删除指定的一级分类")
    public Response<String> deleteClass(@RequestParam Integer classId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/style", method = RequestMethod.POST)
    @Operation(summary = "创建二级分类（样式）", description = "在指定父级分类下创建二级分类")
    public Response<String> createStyle(@RequestBody TypeVO styleVO, @RequestParam Integer parentId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/style", method = RequestMethod.PUT)
    @Operation(summary = "更新二级分类（样式）信息", description = "更新二级分类信息")
    public Response<String> updateStyle(@RequestBody TypeVO styleVO) {
        return null;
    }

    @Override
    @RequestMapping(value = "/style", method = RequestMethod.DELETE)
    @Operation(summary = "删除二级分类（样式）", description = "根据ID删除指定的二级分类")
    public Response<String> deleteStyle(@RequestParam Integer styleId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @Operation(summary = "创建三级分类（类型）", description = "在指定父级分类下创建三级分类")
    public Response<String> createType(@RequestBody TypeVO typeVO, @RequestParam Integer parentId) {
        return null;
    }

    @Override
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    @Operation(summary = "更新三级分类（类型）信息", description = "更新三级分类信息")
    public Response<String> updateType(@RequestBody TypeVO typeVO) {
        return null;
    }

    @Override
    @RequestMapping(value = "/type", method = RequestMethod.DELETE)
    @Operation(summary = "删除三级分类（类型）", description = "根据ID删除指定的三级分类")
    public Response<String> deleteType(@RequestParam Integer typeId) {
        return null;
    }
}
