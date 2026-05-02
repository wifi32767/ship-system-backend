package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.common.enums.Module;
import com.wifi32767.domain.entry.model.DeviceJsonVO;
import com.wifi32767.domain.entry.service.EntryService;
import com.wifi32767.domain.portal.model.DeviceVO;
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
@RequestMapping("/api/entry")
@Tag(name = "数据录入", description = "数据录入接口")
public class EntryControllerImp implements EntryController {

    @Resource
    private EntryService entryService;

    @Override
    @RequestMapping(path = "/single", method = RequestMethod.POST)
    @Permission(Module.ENTRY)
    @Operation(summary = "单条数据录入", description = "单条数据录入")
    public Response<String> entry(@RequestBody DeviceVO device) {
        try {
            entryService.single(device);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error entrying device", e);
            return new Response<>(Response.ERROR, "Failed to entry device" + e.getMessage());
        }
    }

    @Override
    @RequestMapping(path = "/batch", method = RequestMethod.POST)
    @Permission(Module.ENTRY)
    @Operation(summary = "批量数据录入", description = "批量数据录入，支持xlsx、csv格式")
    public Response<String> entryBatch(@RequestBody List<DeviceJsonVO> devices) {
        for (DeviceJsonVO deviceVO : devices) {
            log.info("deviceVO={}", deviceVO);
        }
        try {
            entryService.batch(devices);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error entrying device", e);
            return new Response<>(Response.ERROR, "Failed to entry device" + e.getMessage());
        }
    }
}
