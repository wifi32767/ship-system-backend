package com.wifi32767.interfaces.http;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.portal.model.StatsBoardVO;
import com.wifi32767.domain.portal.service.PortalService;
import com.wifi32767.interfaces.response.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/portal")
public class PortalControllerImp implements PortalController {
    @Resource
    private PortalService portalService;

    @Override
    @RequestMapping(value = "stats-board", method = RequestMethod.GET)
    public Response<StatsBoardVO> getStatsBoard() {
        try {
            return new Response<>(portalService.getStatsBoard());
        } catch (Exception e) {
            log.error("Failed to get stats board", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "latest-news", method = RequestMethod.GET)
    public Response<List<NewsVO>> getLatestNews() {
        try {
            return new Response<>(portalService.getLatestNews(10));
        } catch (Exception e) {
            log.error("Failed to get latest news", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "latest-records", method = RequestMethod.GET)
    public Response<List<DeviceVO>> getLatestRecords() {
        try {
            return new Response<>(portalService.getLatestDevices(30));
        } catch (Exception e) {
            log.error("Failed to get latest records", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "recommendations", method = RequestMethod.GET)
    public Response<List<DeviceVO>> getRecommendations() {
        try {
            return new Response<>(portalService.getRandomDevices(6));
        } catch (Exception e) {
            log.error("Failed to get recommendations", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    @Override
    @RequestMapping(value = "rolling-news", method = RequestMethod.GET)
    public Response<List<NewsVO>> getRollingNews() {
        try {
            return new Response<>(portalService.getLatestNews(10));
        } catch (Exception e) {
            log.error("Failed to get rolling news", e);
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }
}
