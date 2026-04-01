package com.wifi32767.interfaces.http;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.search.model.SearchParamsVO;
import com.wifi32767.domain.search.service.SearchService;
import com.wifi32767.interfaces.dto.ClassStatsDTO;
import com.wifi32767.interfaces.dto.CountryStatsDTO;
import com.wifi32767.interfaces.dto.EchartsOptionDTO;
import com.wifi32767.interfaces.response.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/search")
public class SearchControllerImp implements SearchController {

    @Resource
    private SearchService searchService;

    @Override
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public Response<List<DeviceVO>> search(SearchParamsVO req) {
        try {
            return new Response<>(searchService.searchDevice(req));
        } catch (Exception e) {
            log.error("Fail to search devices", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "class-stats", method = RequestMethod.GET)
    public Response<List<ClassStatsDTO>> getClassStats(SearchParamsVO req) {
        try {
            Map<String, Integer> stats = searchService.getClassStats(req);
            List<ClassStatsDTO> result = stats.entrySet().stream()
                    .map(entry -> new ClassStatsDTO(entry.getKey(), entry.getValue()))
                    .toList();
            return new Response<>(result);
        } catch (Exception e) {
            log.error("Fail to get class stats", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "class-analysis", method = RequestMethod.GET)
    public Response<EchartsOptionDTO> getClassAnalysis(SearchParamsVO req) {
        try {
            Map<String, Integer> stats = searchService.getClassStats(req);
            EchartsOptionDTO option = new EchartsOptionDTO();
            option.setTitle(new EchartsOptionDTO.Title(true, "类别占比分析"));
            option.setTooltip(new EchartsOptionDTO.Tooltip(true, "item"));
            List<EchartsOptionDTO.KVData> data = stats.entrySet().stream()
                    .map(entry -> new EchartsOptionDTO.KVData(entry.getKey(), entry.getValue()))
                    .toList();
            option.setSeries(new EchartsOptionDTO.Series("pie", "50%", data));
            return new Response<>(option);
        } catch (Exception e) {
            log.error("Fail to get class analysis", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "country-stats", method = RequestMethod.GET)
    public Response<List<CountryStatsDTO>> getCountryStats(SearchParamsVO req) {
        try {
            Map<String, Integer> stats = searchService.getCountryStats(req);
            List<CountryStatsDTO> result = stats.entrySet().stream()
                    .map(entry -> new CountryStatsDTO(entry.getKey(), entry.getValue()))
                    .toList();
            return new Response<>(result);
        } catch (Exception e) {
            log.error("Fail to get country stats", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "country-analysis", method = RequestMethod.GET)
    public Response<EchartsOptionDTO> getCountryAnalysis(SearchParamsVO req) {
        try {
            Map<String, Integer> stats = searchService.getCountryStats(req);
            EchartsOptionDTO option = new EchartsOptionDTO();
            option.setTitle(new EchartsOptionDTO.Title(true, "国家占比分析"));
            option.setTooltip(new EchartsOptionDTO.Tooltip(true, "item"));
            List<EchartsOptionDTO.KVData> data = stats.entrySet().stream()
                    .map(entry -> new EchartsOptionDTO.KVData(entry.getKey(), entry.getValue()))
                    .toList();
            option.setSeries(new EchartsOptionDTO.Series("pie", "50%", data));
            return new Response<>(option);
        } catch (Exception e) {
            log.error("Fail to get country analysis", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }


    @Override
    @RequestMapping(value = "year-analysis", method = RequestMethod.GET)
    public Response<EchartsOptionDTO> getYearAnalysis(SearchParamsVO req) {
        try {
            Map<Integer, Integer> stats = searchService.getYearStats(req);
            EchartsOptionDTO option = new EchartsOptionDTO();
            option.setTitle(new EchartsOptionDTO.Title(true, "投产年份分析"));
            option.setTooltip(new EchartsOptionDTO.Tooltip(true, "axis"));
            option.setXAxis(new EchartsOptionDTO.XAxis(true, "category"));
            option.setYAxis(new EchartsOptionDTO.YAxis(true, "value"));
            List<Integer[]> data = stats.entrySet().stream()
                    .map(entry -> new Integer[]{entry.getKey(), entry.getValue()})
                    .sorted(Comparator.comparing(entry -> entry[0]))
                    .toList();
            option.setSeries(new EchartsOptionDTO.Series("line", "50%", data));
            return new Response<>(option);
        } catch (Exception e) {
            log.error("Fail to get year analysis", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "company-relation-analysis", method = RequestMethod.GET)
    public Response<EchartsOptionDTO> getCompanyRelationAnalysis(SearchParamsVO req) {
        try {
            Map<String, Integer> stats = searchService.getCompanyRelationStats(req);
            EchartsOptionDTO option = new EchartsOptionDTO();
            option.setTitle(new EchartsOptionDTO.Title(true, "企业关系分析"));
            option.setTooltip(new EchartsOptionDTO.Tooltip(true, "item"));
            option.setXAxis(new EchartsOptionDTO.XAxis(true, "category"));
            option.setYAxis(new EchartsOptionDTO.YAxis(true, "value"));
            List<Object[]> data = stats.entrySet().stream()
                    .map(entry -> new Object[]{entry.getKey(), entry.getValue()})
                    .sorted((a, b) -> {
                        Integer val1 = (Integer) a[1];
                        Integer val2 = (Integer) b[1];
                        return val2.compareTo(val1);  // 降序
                    })
                    .toList();
            option.setSeries(new EchartsOptionDTO.Series("bar", "50%", data));
            return new Response<>(option);
        } catch (Exception e) {
            log.error("Fail to get company relation analysis", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }
}
