package org.pythonbyte.kpasswordless.service.exceptions

import org.pythonbyte.krux.http.HttpResponse

class KPasswordlessServiceException(response: HttpResponse, requestAlias: String) : RuntimeException() {
    val code: Int = response.getResponse().code()
    val responseMessage: String = response.getResponse().message()
    val responseBody: String = response.getResponse().body().string().trim()
    val requestAlias: String = requestAlias

    override val message: String?
        get() =
            """
            KpasswordlessServiceException: 
            $requestAlias request failed with code [$code] 
            Response Message: [$responseMessage] 
            Response Body: [$responseBody]
            """.trimIndent()
}
