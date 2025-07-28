package com.fabien.africschool

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform