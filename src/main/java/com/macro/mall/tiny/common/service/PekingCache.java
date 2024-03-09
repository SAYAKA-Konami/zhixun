package com.macro.mall.tiny.common.service;

import java.util.Optional;

public interface PekingCache<K, V> {

    void set(K key, V value);

    Optional<V> get(K key);

    void del(K key);
}
