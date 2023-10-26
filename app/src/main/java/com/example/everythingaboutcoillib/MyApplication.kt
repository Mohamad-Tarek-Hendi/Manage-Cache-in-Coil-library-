package com.example.everythingaboutcoillib

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger

class MyApplication : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {

        return ImageLoader(this).newBuilder()

            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    // How big our cache for example 10%
                    .maxSizePercent(0.1)
                    // Image could be garbage collected
                    .strongReferencesEnabled(true)
                    .build()
            }

            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()

    }


}