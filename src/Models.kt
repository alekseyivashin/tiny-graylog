package ru.alekseiivashin.graylogkotlin

import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.exposed.sql.Table


/**
 * Created by a.ivashin on 23.08.2019
 */

data class Log(
    @JsonProperty("short_message")
    val event: String?,
    val pageBlock: String?,
    val requestId: String?
) {

    @JsonAnySetter
    val data: MutableMap<String, String> = mutableMapOf()

}

object Logs: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val event = text("event")
    val pageBlock = text("page_block")
    val requestId = text("request_id")
    val data = text("data")
}