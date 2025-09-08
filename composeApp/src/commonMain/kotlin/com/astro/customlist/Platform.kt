package com.astro.customlist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform