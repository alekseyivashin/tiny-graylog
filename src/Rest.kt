package ru.alekseiivashin.graylogkotlin

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post


/**
 * Created by a.ivashin on 23.08.2019
 */
    
fun Routing.root(logService: LogService) {
    get("/") {
        call.respond(logService.getAllLogs())
    }

    post("/") {
        val log = call.receive<Log>()
        val event = logService.saveLog(log)
        call.respond(event)
    }
}