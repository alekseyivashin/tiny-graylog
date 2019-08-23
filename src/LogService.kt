package ru.alekseiivashin.graylogkotlin

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.Closeable


/**
 * Created by a.ivashin on 23.08.2019
 */

class LogService(private val db: Database) : Closeable {

    fun init() = transaction(db) {
        SchemaUtils.create(Logs)
    }

    fun saveLog(log: Log) = transaction(db) {
        Logs.insert {
            it[event] = log.event ?: ""
            it[pageBlock] = log.pageBlock ?: ""
            it[requestId] = log.requestId ?: ""
            it[data] = log.data.toString()
        } get Logs.event
    }

    fun getAllLogs() = transaction(db) {
        Logs.selectAll().map {
            mapOf(
                "event" to it[Logs.event],
                "pageBlock" to it[Logs.pageBlock],
                "requestId" to it[Logs.requestId],
                "data" to it[Logs.data]
            )
        }
    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}