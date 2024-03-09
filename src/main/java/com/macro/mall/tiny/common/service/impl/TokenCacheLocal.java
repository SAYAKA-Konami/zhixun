package com.macro.mall.tiny.common.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.macro.mall.tiny.common.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component(value = "LocalCacheService")
@AllArgsConstructor
public class TokenCacheLocal implements RedisService {

    private  Cache<String, Object> tokenCache;

    @Override
    public void set(String key, Object value, long time) {
        this.set(key, value);
    }

    @Override
    public void set(String key, Object value) {
        tokenCache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return tokenCache.getIfPresent(key);
    }

    @Override
    public Boolean del(String key) {
        tokenCache.invalidate(key);
        return true;
    }

    @Override
    public Long del(List<String> keys) {
        keys.forEach(k -> tokenCache.invalidate(k));
        return (long) keys.size();
    }

    @Override
    public Boolean expire(String key, long time) {
        return null;
    }

    @Override
    public Long getExpire(String key) {
        return null;
    }

    @Override
    public Boolean hasKey(String key) {
        return null;
    }

    @Override
    public Long incr(String key, long delta) {
        return null;
    }

    @Override
    public Long decr(String key, long delta) {
        return null;
    }

    @Override
    public Object hGet(String key, String hashKey) {
        return null;
    }

    @Override
    public Boolean hSet(String key, String hashKey, Object value, long time) {
        return null;
    }

    @Override
    public void hSet(String key, String hashKey, Object value) {

    }

    @Override
    public Map<Object, Object> hGetAll(String key) {
        return null;
    }

    @Override
    public Boolean hSetAll(String key, Map<String, Object> map, long time) {
        return null;
    }

    @Override
    public void hSetAll(String key, Map<String, ?> map) {

    }

    @Override
    public void hDel(String key, Object... hashKey) {

    }

    @Override
    public Boolean hHasKey(String key, String hashKey) {
        return null;
    }

    @Override
    public Long hIncr(String key, String hashKey, Long delta) {
        return null;
    }

    @Override
    public Long hDecr(String key, String hashKey, Long delta) {
        return null;
    }

    @Override
    public Set<Object> sMembers(String key) {
        return null;
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return null;
    }

    @Override
    public Long sAdd(String key, long time, Object... values) {
        return null;
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return null;
    }

    @Override
    public Long sSize(String key) {
        return null;
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return null;
    }

    @Override
    public List<Object> lRange(String key, long start, long end) {
        return null;
    }

    @Override
    public Long lSize(String key) {
        return null;
    }

    @Override
    public Object lIndex(String key, long index) {
        return null;
    }

    @Override
    public Long lPush(String key, Object value) {
        return null;
    }

    @Override
    public Long lPush(String key, Object value, long time) {
        return null;
    }

    @Override
    public Long lPushAll(String key, Object... values) {
        return null;
    }

    @Override
    public Long lPushAll(String key, Long time, Object... values) {
        return null;
    }

    @Override
    public Long lRemove(String key, long count, Object value) {
        return null;
    }
}
