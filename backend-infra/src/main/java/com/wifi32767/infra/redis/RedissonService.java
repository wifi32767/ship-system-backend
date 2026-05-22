package com.wifi32767.infra.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("redissonService")
public class RedissonService implements RedisService {

    private static final long DELAY = 3000;

    @Resource
    private RedissonClient redissonClient;

    public <T> void setValue(String key, T value) {
        redissonClient.<T>getBucket(key).set(value);
    }

    @Override
    public <T> void setValue(String key, T value, long expired) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value, expired, TimeUnit.SECONDS);
    }

    public <T> T getValue(String key) {
        return redissonClient.<T>getBucket(key).get();
    }

    @Override
    public <T> RQueue<T> getQueue(String key) {
        return redissonClient.getQueue(key);
    }

    @Override
    public <T> RBlockingQueue<T> getBlockingQueue(String key) {
        return redissonClient.getBlockingQueue(key);
    }

    @Override
    public <T> RDelayedQueue<T> getDelayedQueue(RBlockingQueue<T> rBlockingQueue) {
        return redissonClient.getDelayedQueue(rBlockingQueue);
    }

    @Override
    public void setAtomicLong(String key, long value) {
        redissonClient.getAtomicLong(key).set(value);
    }

    @Override
    public Long getAtomicLong(String key) {
        return redissonClient.getAtomicLong(key).get();
    }

    @Override
    public long incr(String key) {
        return redissonClient.getAtomicLong(key).incrementAndGet();
    }

    @Override
    public long incrBy(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(delta);
    }

    @Override
    public long decr(String key) {
        return redissonClient.getAtomicLong(key).decrementAndGet();
    }

    @Override
    public long decrBy(String key, long delta) {
        return redissonClient.getAtomicLong(key).addAndGet(-delta);
    }

    @Override
    public void remove(String key) {
        redissonClient.getBucket(key).delete();
    }

    @Override
    public void remove(String... keys) {
        for (String key : keys) {
            redissonClient.getBucket(key).delete();
        }
    }

    @Override
    public void removeByPattern(String pattern) {
        redissonClient.getKeys().deleteByPattern(pattern);
    }

    @Override
    public void expireByPattern(String pattern) {
        Iterable<String> keysIterable = redissonClient.getKeys().getKeysByPattern(pattern);
        // 使用 RBatch 批量执行，提高效率
        RBatch batch = redissonClient.createBatch();
        int cnt = 0;
        final int batchSize = 1000; // 每 1000 条提交一次，避免内存积压

        for (String key : keysIterable) {
            batch.getBucket(key).expireAsync(DELAY, TimeUnit.MILLISECONDS);
            cnt++;
            if (cnt % batchSize == 0) {
                batch.execute();
                batch = redissonClient.createBatch(); // 重置 batch
            }
        }
        // 提交最后一批
        if (cnt % batchSize != 0) {
            batch.execute();
        }
    }

    @Async
    @Override
    public void expireByPatternAsync(String pattern) {
        expireByPattern(pattern);
        // TODO: 测试异步执行
        log.info("async expire finished: {}", pattern);
    }

    public void delayedRemove(long delay, String key) {
        redissonClient.getBucket(key).expire(Duration.ofMillis(delay));
    }

    @Override
    public void delayedRemove(long delay, String... keys) {
        for (String key : keys) {
            redissonClient.getBucket(key).expire(Duration.ofMillis(delay));
        }
    }

    @Override
    public void delayedRemove(String key) {
        redissonClient.getBucket(key).expire(Duration.ofMillis(DELAY));
    }

    @Override
    public void delayedRemove(String... keys) {
        for (String key : keys) {
            redissonClient.getBucket(key).expire(Duration.ofMillis(DELAY));
        }
    }

    @Override
    public boolean isExists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    @Override
    public void addToSet(String key, String value) {
        RSet<String> set = redissonClient.getSet(key);
        set.add(value);
    }

    @Override
    public <T> Set<T> getSet(String key) {
        RSet<T> set = redissonClient.getSet(key);
        return set.readAll();
    }

    @Override
    public boolean isSetMember(String key, String value) {
        RSet<String> set = redissonClient.getSet(key);
        return set.contains(value);
    }

    @Override
    public void addToList(String key, String value) {
        RList<String> list = redissonClient.getList(key);
        list.add(value);
    }

    @Override
    public String getFromList(String key, int index) {
        RList<String> list = redissonClient.getList(key);
        return list.get(index);
    }

    @Override
    public <K, V> RMap<K, V> getMap(String key) {
        return redissonClient.getMap(key);
    }

    @Override
    public void addToMap(String key, String field, String value) {
        RMap<String, String> map = redissonClient.getMap(key);
        map.put(field, value);
    }

    @Override
    public String getFromMap(String key, String field) {
        RMap<String, String> map = redissonClient.getMap(key);
        return map.get(field);
    }

    @Override
    public <K, V> V getFromMap(String key, K field) {
        return redissonClient.<K, V>getMap(key).get(field);
    }

    @Override
    public void addToSortedSet(String key, String value) {
        RSortedSet<String> sortedSet = redissonClient.getSortedSet(key);
        sortedSet.add(value);
    }

    @Override
    public RLock getLock(String key) {
        return redissonClient.getLock(key);
    }

    @Override
    public RLock getFairLock(String key) {
        return redissonClient.getFairLock(key);
    }

    @Override
    public RReadWriteLock getReadWriteLock(String key) {
        return redissonClient.getReadWriteLock(key);
    }

    @Override
    public RSemaphore getSemaphore(String key) {
        return redissonClient.getSemaphore(key);
    }

    @Override
    public RPermitExpirableSemaphore getPermitExpirableSemaphore(String key) {
        return redissonClient.getPermitExpirableSemaphore(key);
    }

    @Override
    public RCountDownLatch getCountDownLatch(String key) {
        return redissonClient.getCountDownLatch(key);
    }

    @Override
    public <T> RBloomFilter<T> getBloomFilter(String key) {
        return redissonClient.getBloomFilter(key);
    }

    @Override
    public Boolean setNx(String key) {
        return redissonClient.getBucket(key).trySet("lock");
    }

    @Override
    public Boolean setNx(String key, long expired, TimeUnit timeUnit) {
        return redissonClient.getBucket(key).trySet("lock", expired, timeUnit);
    }

    @Override
    public RBitSet getBitSet(String key) {
        return redissonClient.getBitSet(key);
    }

}
