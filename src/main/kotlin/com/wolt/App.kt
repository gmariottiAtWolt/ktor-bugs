package com.wolt

import com.fasterxml.jackson.annotation.JsonInclude
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    EngineMain.main(emptyArray())
}

fun Application.ktorMain() {
    install(StatusPages) {
        exception<BadRequestException>(::exceptionHandler)
    }
    install(ContentNegotiation) {
        jackson {
            setSerializationInclusion(JsonInclude.Include.ALWAYS)
            setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS)
        }
    }
    routing {
        post {
            call.receive<RequestBody>()
        }
    }
}

suspend fun exceptionHandler(call: ApplicationCall, cause: BadRequestException) {
    var nestedExcCounter = 0
    var curr = cause.cause
    while (curr is BadRequestException) {
        nestedExcCounter++
        curr = curr.cause
    }
    call.respond(HttpStatusCode.BadRequest, "Got $nestedExcCounter BadRequestExceptions")
}

data class RequestBody(val test: String)
