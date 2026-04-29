package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.common.enums.Module;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.domain.system.model.FullDeviceVO;
import com.wifi32767.domain.system.service.AuditService;
import com.wifi32767.interfaces.common.Permission;
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
@RequestMapping("/api/audit")
@Tag(name = "审核管理接口", description = "提供录入信息审核相关操作")
public class AuditControllerImp implements AuditController {

    @Resource
    private AuditService auditService;

    @Override
    @RequestMapping(path = "/search", method = RequestMethod.POST)
    @Operation(summary = "搜索录入信息", description = "根据标题和状态获取录入信息")
    public Response<List<FullDeviceVO>> searchAuditDevices(@RequestBody AuditSearchParamsVO params) {
        try {
            List<FullDeviceVO> devices = auditService.searchAuditDevices(params);
            return new Response<>(devices);
        } catch (Exception e) {
            log.error("Error searching audit devices: ", e);
            return new Response<>(Response.ERROR, "Failed to search audit devices: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(path = "/page", method = RequestMethod.POST)
    @Operation(summary = "搜索录入信息", description = "根据标题和状态获取录入信息")
    public Response<List<FullDeviceVO>> searchAuditDevices(@RequestBody AuditSearchParamsVO params, @RequestParam int page, @RequestParam int size) {
        try {
            List<FullDeviceVO> devices = auditService.searchAuditDevices(params, page, size);
            return new Response<>(devices);
        } catch (Exception e) {
            log.error("Error searching audit devices: ", e);
            return new Response<>(Response.ERROR, "Failed to search audit devices: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    @Permission(Module.AUDIT)
    @Operation(summary = "录入信息审核", description = "审核录入信息")
    public Response<String> audit(@RequestBody AuditSearchParamsVO params) {
        try {
            auditService.audit(params);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error auditing: ", e);
            return new Response<>(Response.ERROR, "Failed to audit: " + e.getMessage());
        }
    }
}
