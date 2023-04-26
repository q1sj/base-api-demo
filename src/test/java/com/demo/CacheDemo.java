package com.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xsy.base.cache.CacheManagerWrapper;
import com.xsy.base.cache.CacheWrapper;
import com.xsy.sys.entity.BooleanKey;
import com.xsy.sys.entity.ListKey;
import com.xsy.sys.entity.ObjectKey;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import java.util.*;

/**
 * @author Q1sj
 * @date 2022.9.27 14:20
 */
public class CacheDemo {
    public static final BooleanKey NORMAL_EVENT_SAVE_PIC_KEY = new BooleanKey("file.normalEvent.savePic", null);
    public static final ListKey<Integer> LIST_KEY = new ListKey<>("list", Integer.class);

    public static final ObjectKey<Map<String, String>> m = new ObjectKey<>("obj", Collections.emptyMap(), new TypeReference<Map<String, String>>() {
    });

    public static void main(String[] args) {
        CacheManagerWrapper cacheManagerWrapper = new CacheManagerWrapper(new ConcurrentMapCacheManager());
        CacheWrapper demoCache = cacheManagerWrapper.getCache("demo");
        demoCache.put(NORMAL_EVENT_SAVE_PIC_KEY, true);
        Boolean b = demoCache.get(NORMAL_EVENT_SAVE_PIC_KEY);
        demoCache.evict(NORMAL_EVENT_SAVE_PIC_KEY);
        Boolean b2 = demoCache.get(NORMAL_EVENT_SAVE_PIC_KEY);
        Boolean b3 = demoCache.get(NORMAL_EVENT_SAVE_PIC_KEY, () -> true);
        Boolean b4 = demoCache.get(NORMAL_EVENT_SAVE_PIC_KEY);
        List<Integer> list = demoCache.get(LIST_KEY, () -> Arrays.asList(1, 2));
        Map<String, String> map = new HashMap<>();
        map.put("a","b");
        map.put("a2","b2");

        demoCache.put(m,map);
        Map<String, String> map1 = demoCache.get(m);
    }
}



