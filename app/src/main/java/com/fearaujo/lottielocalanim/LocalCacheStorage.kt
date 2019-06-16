package com.fearaujo.lottielocalanim

class LocalCacheStorage {

    private companion object {
        const val ANIM_PATH = "/data/data/com.fearaujo.lottielocalanim/cache/lottie/lottie_file.json"
    }

    fun fetchAnimationPathFromCache() = ANIM_PATH

}