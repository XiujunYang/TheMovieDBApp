package com.example.themoviedbapp.util;

import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.cache.disk.DiskStorageCache;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import static com.example.themoviedbapp.util.AppConstant.MAX_DISK_CACHE_LOW_SIZE;
import static com.example.themoviedbapp.util.AppConstant.MAX_DISK_CACHE_SIZE;
import static com.example.themoviedbapp.util.AppConstant.MAX_DISK_CACHE_VERYLOW_SIZE;

/**
 * Created by Jean on 2018/3/12.
 */

public class Util {

    public static ImagePipelineConfig getImgPiplineConfig(Context context) {
        int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
        int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
        final DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(context)
                .setMaxCacheSize(MAX_DISK_CACHE_SIZE)
                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE)
                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERYLOW_SIZE)
                .build();
        final MemoryCacheParams memoryCacheParams = new MemoryCacheParams(MAX_MEMORY_CACHE_SIZE, Integer.MAX_VALUE,
                MAX_MEMORY_CACHE_SIZE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return memoryCacheParams;
            }
        };

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)
                .build();
        return config;
    }
}
