package com.wolt

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AppTest {

    @Test
    fun `just a test`() = testApplication {
        environment { developmentMode = false }

        val response = client.post {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody("""{"field": "test"}""")
        }

        assertEquals("Got 0 BadRequestExceptions", response.bodyAsText())
    }

    @Test
    fun `just another test`() = testApplication {
        environment { developmentMode = false }

        val response = client.post {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            setBody("""{"field": "test"}""")
        }

        assertEquals("Got 0 BadRequestExceptions", response.bodyAsText())
    }
}