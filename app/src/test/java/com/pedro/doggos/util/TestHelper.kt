package com.pedro.doggos.util

import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

fun createHttpException(): HttpException {
    val responseBody = "Error".toResponseBody()
    return HttpException(Response.error<Any>(403, responseBody))
}

fun randomString(): String {
    val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return java.util.Random().ints(Random.nextLong(0, 30), 0 , source.length)
        .toArray()
        .asSequence()
        .map(source::get)
        .joinToString("")
}

fun randomInt(): Int {
    return ThreadLocalRandom.current().nextInt(0, 1001)
}