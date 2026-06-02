package com.wifi32767.interfaces.http.admin;

import com.wifi32767.common.Permission;
import com.wifi32767.common.Response;
import com.wifi32767.common.enums.Module;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.adapter.repository.CountryRepository;
import com.wifi32767.domain.system.service.DeviceService;
import com.wifi32767.interfaces.dto.DeviceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/device")
@Tag(name = "信息接口", description = "提供信息相关操作")
public class ModifyControllerImp implements ModifyController {

    @Resource
    private DeviceService deviceService;

    @Resource
    private ClassRepository classRepository;

    @Resource
    private CountryRepository countryRepository;

    @Override
    @RequestMapping(path = "/count", method = RequestMethod.GET)
    @Operation(summary = "获取信息数量", description = "返回所有信息数量")
    public Response<Integer> getDeviceCount() {
        try {
            return new Response<>(deviceService.deviceCount());
        } catch (Exception e) {
            log.error("Error fetching device count");
            return new Response<>(Response.ERROR, "Failed to fetch device count: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(path = "/audit-count", method = RequestMethod.GET)
    @Operation(summary = "获取审计信息数量", description = "根据信息状态返回审计信息数量")
    public Response<Integer> getAuditDeviceCount(@RequestParam int type) {
        try {
            return new Response<>(deviceService.auditDeviceCount(type));
        } catch (Exception e) {
            log.error("Error fetching audit device count");
            return new Response<>(Response.ERROR, "Failed to fetch audit device count: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "获取信息列表", description = "返回所有信息数据")
    public Response<List<DeviceDTO>> getDeviceList() {
        try {
            return new Response<>(LDeviceVO2LDeviceDTO(deviceService.getDeviceList()));
        } catch (Exception e) {
            log.error("Error fetching device list", e);
            return new Response<>(Response.ERROR, "Failed to fetch device list: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(path = "/page", method = RequestMethod.GET)
    @Operation(summary = "获取信息列表（分页）", description = "返回指定页码和每页大小的信息数据")
    public Response<List<DeviceDTO>> getDeviceList(@RequestParam int page, @RequestParam int size) {
        try {
            return new Response<>(LDeviceVO2LDeviceDTO(deviceService.getDeviceList(page, size)));
        } catch (Exception e) {
            log.error("Error fetching device page list", e);
            return new Response<>(Response.ERROR, "Failed to fetch device page list: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    @Permission(Module.MODIFY)
    @Operation(summary = "修改信息", description = "更新指定的信息")
    public Response<String> modifyDevice(@RequestBody DeviceVO deviceVO) {
        try {
            deviceService.modifyDevice(deviceVO);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error modifying device", e);
            return new Response<>(Response.ERROR, "Failed to modify device: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE)
    @Permission(Module.MODIFY)
    @Operation(summary = "删除信息", description = "删除指定的信息")
    public Response<String> deleteDevice(@RequestParam Integer id) {
        try {
            deviceService.deleteDevice(id);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error deleting device", e);
            return new Response<>(Response.ERROR, "Failed to delete device: " + e.getMessage());
        }
    }

    private List<DeviceDTO> LDeviceVO2LDeviceDTO(List<DeviceVO> deviceVOList) {
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        for (DeviceVO deviceVO : deviceVOList) {
            deviceDTOList.add(
                    DeviceDTO.builder()
                            .id(deviceVO.getId())
                            .deviceName(deviceVO.getDeviceName())
                            .deviceUseYear(deviceVO.getDeviceUseYear())
                            .devicePrice(deviceVO.getDevicePrice())
                            .deviceUsingUnit(deviceVO.getDeviceUsingUnit())
                            .deviceLocation(deviceVO.getDeviceLocation())
                            .deviceLongitude(deviceVO.getDeviceLongitude())
                            .deviceLatitude(deviceVO.getDeviceLatitude())
                            .deviceImg(deviceVO.getDeviceImg())
                            .deviceVideo(deviceVO.getDeviceVideo())
                            .deviceIntroduce(deviceVO.getDeviceIntroduce())
                            .deviceInsqlTime(deviceVO.getDeviceInsqlTime())
                            .deviceChangesqlTime(deviceVO.getDeviceChangesqlTime())
                            .deviceClass(deviceVO.getDeviceClassId() == null ? null : classRepository.getClassNameById(deviceVO.getDeviceClassId()))
                            .deviceStyle(deviceVO.getDeviceStyleId() == null ? null : classRepository.getStyleNameById(deviceVO.getDeviceStyleId()))
                            .deviceType(deviceVO.getDeviceTypeId() == null ? null : classRepository.getTypeNameById(deviceVO.getDeviceTypeId()))
                            .deviceCountry(deviceVO.getDeviceCountryId() == null ? null : countryRepository.getCountryNameById(deviceVO.getDeviceCountryId()))
                            .build()
            );
        }
        return deviceDTOList;
    }
}