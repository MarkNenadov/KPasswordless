package org.pythonbyte.kpasswordless.service.exceptions

import org.pythonbyte.krux.http.HttpResponse

class KPasswordlessServiceException(response: HttpResponse, val requestAlias: String) : RuntimeException() {
    val code: Int = response.code()
    val responseMessage: String = response.message()
    val responseBody: String = response.getBody()?.string()?.trim() ?: ""

    override val message: String
        get() =
            """
            KpasswordlessServiceException: 
            $requestAlias request failed with code [$code] 
            Response Message: [$responseMessage] 
            Response Body: [$responseBody]
            """.trimIndent()
}
