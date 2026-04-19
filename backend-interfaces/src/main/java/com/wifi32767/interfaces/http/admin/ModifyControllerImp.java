package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.interfaces.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/device")
@Tag(name = "信息接口", description = "提供信息相关操作")
public class ModifyControllerImp implements ModifyController {

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "获取信息列表", description = "返回所有信息数据")
    public Response<List<DeviceVO>> getDeviceList() {
        return null;
    }

    @Override
    @RequestMapping(path = "/page", method = RequestMethod.GET)
    @Operation(summary = "获取信息列表（分页）", description = "返回指定页码和每页大小的信息数据")
    public Response<List<DeviceVO>> getDeviceList(@RequestParam int page, @RequestParam int size) {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    @Operation(summary = "修改信息", description = "更新指定的信息")
    public Response<String> modifyDevice(@RequestBody DeviceVO deviceVO) {
        return null;
    }
}