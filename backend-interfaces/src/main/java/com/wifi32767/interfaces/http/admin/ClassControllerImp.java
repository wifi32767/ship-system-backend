package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.ClassVO;
import com.wifi32767.domain.system.model.TypeVO;
import com.wifi32767.domain.system.service.ClassService;
import com.wifi32767.interfaces.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/class")
@Tag(name = "分类管理接口", description = "提供一、二、三级分类相关操作")
public class ClassControllerImp implements ClassController {

    @Resource
    private ClassService classService;

    @Override
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Operation(summary = "获取所有一级分类列表", description = "获取所有一级分类列表")
    public Response<List<ClassVO>> getClasses() {
        try {
            return new Response<>(classService.getClasses());
        } catch (Exception e) {
            log.error("Error fetching classes list", e);
            return new Response<>(Response.ERROR, "Failed to fetch classes list" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/list/search", method = RequestMethod.GET)
    @Operation(summary = "搜索分类", description = "根据关键词模糊匹配分类名称")
    public Response<List<ClassVO>> searchClasses(@RequestParam String keyword) {
        try {
            return new Response<>(classService.searchClasses(keyword));
        } catch (Exception e) {
            log.error("Error searching classes", e);
            return new Response<>(Response.ERROR, "Failed to search classes" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/class", method = RequestMethod.POST)
    @Operation(summary = "创建一级分类", description = "创建一级分类")
    public Response<String> createClass(@RequestBody TypeVO classVO) {
        try {
            classService.createClass(classVO);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error creating class", e);
            return new Response<>(Response.ERROR, "Failed to create class" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/class", method = RequestMethod.PUT)
    @Operation(summary = "更新一级分类信息", description = "更新一级分类信息")
    public Response<String> updateClass(@RequestBody TypeVO classVO) {
        try {
            classService.updateClass(classVO);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error updating class", e);
            return new Response<>(Response.ERROR, "Failed to update class" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/class", method = RequestMethod.DELETE)
    @Operation(summary = "删除一级分类", description = "根据ID删除指定的一级分类")
    public Response<String> deleteClass(@RequestParam Integer classId) {
        try {
            classService.deleteClass(classId);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error deleting class", e);
            return new Response<>(Response.ERROR, "Failed to delete class" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/style", method = RequestMethod.POST)
    @Operation(summary = "创建二级分类（样式）", description = "在指定父级分类下创建二级分类")
    public Response<String> createStyle(@RequestBody TypeVO styleVO, @RequestParam Integer parentId) {
        try {
            classService.createStyle(styleVO, parentId);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error creating style", e);
            return new Response<>(Response.ERROR, "Failed to create style" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/style", method = RequestMethod.PUT)
    @Operation(summary = "更新二级分类（样式）信息", description = "更新二级分类信息")
    public Response<String> updateStyle(@RequestBody TypeVO styleVO) {
        try {
            classService.updateStyle(styleVO);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error updating style", e);
            return new Response<>(Response.ERROR, "Failed to update style" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/style", method = RequestMethod.DELETE)
    @Operation(summary = "删除二级分类（样式）", description = "根据ID删除指定的二级分类")
    public Response<String> deleteStyle(@RequestParam Integer styleId) {
        try {
            classService.deleteStyle(styleId);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error deleting style", e);
            return new Response<>(Response.ERROR, "Failed to delete style" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @Operation(summary = "创建三级分类（类型）", description = "在指定父级分类下创建三级分类")
    public Response<String> createType(@RequestBody TypeVO typeVO, @RequestParam Integer parentId) {
        try {
            classService.createType(typeVO, parentId);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error creating type", e);
            return new Response<>(Response.ERROR, "Failed to create type" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    @Operation(summary = "更新三级分类（类型）信息", description = "更新三级分类信息")
    public Response<String> updateType(@RequestBody TypeVO typeVO) {
        try {
            classService.updateType(typeVO);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error updating type", e);
            return new Response<>(Response.ERROR, "Failed to update type" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/type", method = RequestMethod.DELETE)
    @Operation(summary = "删除三级分类（类型）", description = "根据ID删除指定的三级分类")
    public Response<String> deleteType(@RequestParam Integer typeId) {
        try {
            classService.deleteType(typeId);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error deleting type", e);
            return new Response<>(Response.ERROR, "Failed to delete type" + e.getMessage());
        }
    }
}
