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
                setBody("""{"short_message": "ViewDoc", 
                    |"requestDateTime": "22.08.2019 15:33:11", 
                    |"browserUserAgent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36", 
                    |"requestName": "ViewDoc", 
                    |"pageBlock": "glossaryPage", 
                    |"userName": "uliya8@yopmail.com", 
                    |"UUID": "000f2438-69e8-4fa3-ad64-7db4b1ae92f1", 
                    |"userCategory": "Бухгалтер (коммерческие) М", 
                    |"isTrial": "false", 
                    |"subscriptionID": "1201", 
                    |"subscriptionName": "1201 Безлимит!", 
                    |"accessCreditType": "null", 
                    |"organizationID": "5", 
                    |"organizationName": "UlyaOrganization, название, кторое не вмещается в одну строку в моем профиле для личных данных - поле компания", 
                    |"infoBankName": "BELAW", 
                    |"docId": 180409, 
                    |"infoBankNameDocId": "BELAW_180409", 
                    |"docKind": "Кодекс Республики Беларусь", 
                    |"teamBlock": "AdminAdmin", 
                    |"author": "Admin", 
                    |"viewDocDurationId": null, 
                    |"sortResults": "complex", 
                    |"sectionFilter": "Все", 
                    |"searchRequest": "термин Интернет-ссылка для отображения в блоке", 
                    |"searchType": "quickSearch", 
                    |"backspin": "false", 
                    |"requestId": "9ca20a97-4a9b-4d45-8542-3dc9ec05fa8a"}""".trimMargin())
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
