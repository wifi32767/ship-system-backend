package com.wifi32767.infra.adapter.repository;

import com.wifi32767.infra.redis.RedisService;

import java.util.Random;

public final class DeviceCache {
    public static final String ALL_LIST = "device:all:list";
    public static final String ALL_LIST_PAGE = "device:all:list:page:%d|%d";
    public static final String ALL_LIST_PAGE_PATTERN = "device:all:list:page:*";
    public static final String DEVICE_BY_ID = "device:by:id:";
    public static final String LATEST_NEWS_LIMIT = "device:latest:news:limit:";
    public static final String LATEST_NEWS_LIMIT_PATTERN = "device:latest:news:limit:*";
    public static final String LATEST_DEVICES_LIMIT = "device:latest:devices:limit:";
    public static final String LATEST_DEVICES_LIMIT_PATTERN = "device:latest:devices:limit:*";
    public static final String DEVICE_COUNT_TOTAL = "device:count:total";
    public static final String DEVICE_COUNT_BY_DATE = "device:count:date:%s";  // %s = yyyy-MM-dd
    public static final String DEVICE_COUNT_BY_DATE_PATTERN = "device:count:date:*";
    public static final String DEVICE_CORRECTION_COUNT_BY_DATE = "device:correction:date:%s";
    public static final String DEVICE_CORRECTION_COUNT_BY_DATE_PATTERN = "device:correction:date:*";
    // 随机缓存过期时间防止缓存雪崩
    private static final Random rand = new Random();

    private DeviceCache() {
    } // 禁止实例化

    public static long getExpireTime() {
        return 60 * 60 * 24 * 3 + rand.nextInt(60 * 60 * 24 * 3);
    }

    public static void removeAllList(RedisService redisService) {
        redisService.remove(ALL_LIST, DEVICE_COUNT_TOTAL);
        redisService.expireByPatternAsync(ALL_LIST_PAGE_PATTERN);
        redisService.expireByPatternAsync(LATEST_NEWS_LIMIT_PATTERN);
        redisService.expireByPatternAsync(LATEST_DEVICES_LIMIT_PATTERN);
        redisService.expireByPatternAsync(DEVICE_COUNT_BY_DATE_PATTERN);
        redisService.expireByPatternAsync(DEVICE_CORRECTION_COUNT_BY_DATE_PATTERN);
    }
}
