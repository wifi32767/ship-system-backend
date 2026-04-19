package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.entry.service.EntryService;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.interfaces.common.Response;
import com.wifi32767.interfaces.dto.EntryBatchRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @Operation(summary = "单条数据录入", description = "单条数据录入")
    public Response<String> entry(DeviceVO device) {
        try {
            return new Response<>(entryService.single(device));
        } catch (Exception e) {
            log.error("", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(path = "/batch", method = RequestMethod.POST)
    @Operation(summary = "批量数据录入", description = "批量数据录入，支持xlsx、csv格式")
    public Response<EntryBatchRespDTO> entryBatch(List<DeviceVO> devices) {
        return null;
    }
}
