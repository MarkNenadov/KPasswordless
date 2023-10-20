package org.pythonbyte.kpasswordless.service.exceptions

import com.squareup.okhttp.Response;
import org.pythonbyte.krux.http.HttpResponse

class KpasswordlessServiceException(response: HttpResponse, requestAlias: String) : RuntimeException() {
    val code: Int = response.getResponse().code()
    val responseMessage: String = response.getResponse().message()
    val responseBody: String = response.getResponse().body().string().trim()
    val requestAlias: String = requestAlias;

    override val message: String?
        get() = "KpasswordlessServiceException: $requestAlias request failed with code [$code] [$responseMessage] json [$responseBody]"
}