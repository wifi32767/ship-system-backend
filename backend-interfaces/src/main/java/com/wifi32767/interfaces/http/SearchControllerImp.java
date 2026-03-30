package com.wifi32767.interfaces.http;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.SearchParamsVO;
import com.wifi32767.domain.portal.service.SearchService;
import com.wifi32767.interfaces.response.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController()
@CrossOrigin("*")
public class SearchControllerImp implements SearchController {

    @Resource
    private SearchService searchService;

    @Override
    @RequestMapping(value = "/api/user/search", method = RequestMethod.GET)
    public Response<List<DeviceVO>> search(SearchParamsVO req) {
        try {
            return new Response<>(searchService.searchDevice(req));
        } catch (Exception e) {
            log.error("Fail to search devices", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "/api/user/class-stats", method = RequestMethod.GET)
    // 考虑把这个接口改成返回List<DTO>
    public Response<Map<String, Integer>> getClassStats(SearchParamsVO req) {
        try {
            return new Response<>(searchService.getClassStats(req));
        } catch (Exception e) {
            log.error("Fail to get class stats", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }
}
