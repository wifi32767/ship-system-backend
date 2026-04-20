package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.CountryVO;
import com.wifi32767.domain.system.service.CountryService;
import com.wifi32767.infra.dao.po.Country;
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
@RequestMapping("/api/country")
@Tag(name = "国家管理接口", description = "提供国家信息相关操作")
public class CountryControllerImp implements CountryController {

    @Resource
    private CountryService countryService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "获取国家列表", description = "返回所有国家信息")
    public Response<List<CountryVO>> countryGet() {
        try {
            return new Response<>(countryService.getCountries());
        } catch (Exception e) {
            log.error("Error fetching countries: ", e);
            return new Response<>(Response.ERROR, "Failed to fetch countries: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "添加国家", description = "创建新的国家记录")
    public Response<String> countryInsert(@RequestBody Country country) {
        try {
            countryService.insertCountry(CountryVO.builder()
                    .countryId(country.getCountryId())
                    .countryName(country.getCountryName())
                    .englishName(country.getEnglishName())
                    .build());
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error inserting country: ", e);
            return new Response<>(Response.ERROR, "Failed to insert country: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE)
    @Operation(summary = "删除国家", description = "根据ID删除指定国家记录")
    public Response<String> countryDelete(@RequestParam Integer countryId) {
        try {
            countryService.deleteCountry(countryId);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error deleting country: ", e);
            return new Response<>(Response.ERROR, "Failed to delete country: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(path = "/batch", method = RequestMethod.DELETE)
    @Operation(summary = "批量删除国家", description = "根据ID列表批量删除国家记录")
    public Response<String> countryBatchDelete(@RequestBody List<Integer> countryIds) {
        try {
            countryService.deleteCountries(countryIds);
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error deleting countries: ", e);
            return new Response<>(Response.ERROR, "Failed to delete countries: " + e.getMessage());
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    @Operation(summary = "修改国家", description = "更新指定国家的信息")
    public Response<String> countryEdit(@RequestBody Country country) {
        try {
            countryService.updateCountry(CountryVO.builder()
                    .countryId(country.getCountryId())
                    .countryName(country.getCountryName())
                    .englishName(country.getEnglishName())
                    .build());
            return new Response<>(null);
        } catch (Exception e) {
            log.error("Error updating country: ", e);
            return new Response<>(Response.ERROR, "Failed to update country: " + e.getMessage());
        }
    }
}
