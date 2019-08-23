package ru.alekseiivashin.graylogkotlin

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ module(testing = true) }) {
            logService.init()
            handleRequest(HttpMethod.Get, "/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }

            handleRequest(HttpMethod.Post, "/") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                setBody(
                    """{"short_message": "Doc", 
                    |"requestDateTime": "12.12.1019 11:53:00", 
                    |"browserUserAgent": "Agent", 
                    |"requestName": "Doc"}""".trimMargin()
                )
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
