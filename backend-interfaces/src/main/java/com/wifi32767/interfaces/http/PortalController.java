package com.wifi32767.interfaces.http;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.portal.model.StatsBoardVO;
import com.wifi32767.interfaces.common.Response;

import java.util.List;

public interface PortalController {
    /**
     * 收录统计板：返回收录总量、今日收录量以及今日校错量，并计算昨日同比。
     * 数据格式：JSON
     *
     * @return 收录统计板响应 DTO {@link com.wifi32767.domain.portal.model.StatsBoardVO}
     */
    Response<StatsBoardVO> getStatsBoard();

    /**
     * 最新新闻：返回最新10条新闻信息。
     * 数据格式：JSON
     *
     * @return 最新新闻列表 {@code List<com.wifi32767.domain.portal.model.NewsVO>}
     */
    Response<List<NewsVO>> getLatestNews();

    /**
     * 最新收录相关信息：返回最多30条最新收录信息。
     * 数据格式：JSON
     *
     * @return 最新收录列表 {@code List<com.wifi32767.domain.portal.model.DeviceVO>}
     */
    Response<List<DeviceVO>> getLatestRecords();

    /**
     * 信息推荐：返回随机6条推荐信息。
     * 数据格式：JSON
     *
     * @return 推荐信息列表 {@code List<com.wifi32767.domain.portal.model.DeviceVO>}
     */
    Response<List<DeviceVO>> getRecommendations();

    /**
     * 滚动新闻：返回收录最新10条新闻（检索页滚动展示）。
     * 数据格式：JSON
     *
     * @return 滚动新闻列表 {@code List<com.wifi32767.domain.portal.model.NewsVO>}
     */
    Response<List<NewsVO>> getRollingNews();

}
