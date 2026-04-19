package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.interfaces.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/audit")
@Tag(name = "审核管理接口", description = "提供录入信息审核相关操作")
public class AuditControllerImp implements AuditController {

    @Override
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Operation(summary = "搜索录入信息", description = "根据标题和状态获取录入信息")
    public Response<List<DeviceVO>> searchAuditDevices(@RequestBody AuditSearchParamsVO params) {
        return null;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Operation(summary = "录入信息审核", description = "审核录入信息")
    public Response<String> audit(@RequestBody AuditSearchParamsVO params) {
        return null;
    }
}
