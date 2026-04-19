package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.CountryVO;
import com.wifi32767.infra.dao.po.Country;
import com.wifi32767.interfaces.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/country")
@Tag(name = "国家管理接口", description = "提供国家信息相关操作")
public class CountryControllerImp implements CountryController {

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "获取国家列表", description = "返回所有国家信息")
    public Response<List<CountryVO>> countryGet() {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "添加国家", description = "创建新的国家记录")
    public Response<String> countryInsert(@RequestBody Country country) {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE)
    @Operation(summary = "删除国家", description = "根据ID删除指定国家记录")
    public Response<String> countryDelete(@RequestParam Integer countryId) {
        return null;
    }

    @Override
    @RequestMapping(path = "/batch", method = RequestMethod.DELETE)
    @Operation(summary = "批量删除国家", description = "根据ID列表批量删除国家记录")
    public Response<String> countryBatchDelete(@RequestBody List<Integer> countryIds) {
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    @Operation(summary = "修改国家", description = "更新指定国家的信息")
    public Response<String> countryEdit(@RequestBody Country country) {
        return null;
    }
}
