package com.wifi32767.interfaces.http.admin;

import com.wifi32767.common.Permission;
import com.wifi32767.common.Response;
import com.wifi32767.common.enums.Module;
import com.wifi32767.domain.system.model.EntryLogVO;
import com.wifi32767.domain.system.service.LogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/log")
@Tag(name = "日志管理接口", description = "日志管理接口")
public class LogControllerImp implements LogController {

    @Resource
    private LogService logService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @Permission(Module.LOG)
    @Operation(summary = "获取日志列表", description = "返回所有日志信息")
    public Response<List<EntryLogVO>> getLogs(@RequestParam int pageNum, @RequestParam int pageSize) {
        try {
            return new Response<>(logService.getLogs(pageNum, pageSize));
        } catch (Exception e) {
            log.error("Error fetching logs");
            return new Response<>(Response.ERROR, "Failed to fetch logs" + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/delete")
    @Permission(Module.LOG)
    @Operation(summary = "删除日志", description = "根据ID删除指定日志")
    public Response<String> deleteLog(@RequestParam int id) {
        try {
            logService.deleteLog(id);
            return new Response<>(Response.SUCCESS, "Log deleted successfully");
        } catch (Exception e) {
            log.error("Error deleting log with id: {}", id, e);
            return new Response<>(Response.ERROR, "Failed to delete log: " + e.getMessage());
        }
    }

    @Override
    @DeleteMapping("/batch-delete")
    @Permission(Module.LOG)
    @Operation(summary = "批量删除日志", description = "批量删除指定日志")
    public Response<String> deleteLogsBatch(@RequestBody List<Integer> ids) {
        try {
            if (ids == null || ids.isEmpty()) {
                return new Response<>(Response.ERROR, "ID list cannot be empty");
            }
            logService.deleteLogsBatch(ids);
            return new Response<>(Response.SUCCESS, "Logs deleted successfully");
        } catch (Exception e) {
            log.error("Error batch deleting logs with ids: {}", ids, e);
            return new Response<>(Response.ERROR, "Failed to batch delete logs: " + e.getMessage());
        }
    }
}
