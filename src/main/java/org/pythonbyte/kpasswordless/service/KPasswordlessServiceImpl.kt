package org.pythonbyte.kpasswordless.service

import com.squareup.okhttp.*
import org.json.JSONObject
import org.pythonbyte.kpasswordless.domain.KPasswordlessCredential
import org.pythonbyte.kpasswordless.domain.KPasswordlessIdentity
import org.pythonbyte.kpasswordless.domain.KPasswordlessSignIn
import org.pythonbyte.kpasswordless.service.exceptions.KpasswordlessServiceException
import org.pythonbyte.krux.http.MediaTypes
import org.pythonbyte.krux.http.buildRequest
import org.pythonbyte.krux.http.sendRequest
import org.pythonbyte.krux.json.JsonObject
import org.pythonbyte.krux.properties.PropertyReader

class KPasswordlessServiceImpl : KPasswordlessService {
    private val propertiesFile = "/kpasswordless.properties"
    private val propertyReader = PropertyReader(propertiesFile)
    private val baseUrl = propertyReader.get("kpasswordless.baseUrl")

    private fun generateHeaders(privateKey: String): Headers {
        return Headers.Builder().apply {
            add("ApiSecret", privateKey)
            add("Content-Type", "application/json")
        }.build()
    }

    private fun createPostBody(bodyMap: Map<String, Any>): RequestBody {
        val jsonContent = JsonObject(JSONObject(bodyMap)).toString()

        return RequestBody.create(MediaTypes.UTF_JSON, jsonContent)
    }

    override fun registerToken(privateKey: String, identity: KPasswordlessIdentity): String {
        val request = buildRequest(
            "$baseUrl/register/token",
            generateHeaders(privateKey),
            createPostBody(identity.map()),
        )
        val response = sendRequest(request)

        return if (response.isOk()) {
            response.getJsonObject().getString("token")
        } else {
            throw KpasswordlessServiceException(response, "Register token")
        }
    }

    override fun addAliases(privateKey: String, identity: KPasswordlessIdentity, aliases: List<String>): Boolean {
        val request = buildRequest(
            "$baseUrl/alias",
            generateHeaders(privateKey),
            createPostBody(mapOf("userId" to identity.userId, "aliases" to aliases)),
        )

        val response = sendRequest(request)

        return if (response.isOk()) {
            true
        } else {
            throw KpasswordlessServiceException(response, "Add alias")
        }
    }

    override fun signin(privateKey: String, token: String): KPasswordlessSignIn {
        val request = buildRequest(
            "$baseUrl/signin/verify",
            generateHeaders(privateKey),
            createPostBody(mapOf("token" to token)),
        )
        val response = sendRequest(request)

        return if (response.isOk()) {
            KPasswordlessSignIn.create(response.getJsonObject())
        } else {
            throw KpasswordlessServiceException(response, "Sign in verify")
        }
    }

    override fun listCredentials(privateKey: String, identity: KPasswordlessIdentity): List<KPasswordlessCredential> {
        val request = buildRequest("$baseUrl/credentials/list?userId=${identity.userId}", generateHeaders(privateKey), createPostBody(mapOf("userId" to identity.userId)))
        val response = sendRequest(request)

        return if (response.isOk()) {
            KPasswordlessCredential.createList(response.getJsonObject().getArray("values"))
        } else {
            throw KpasswordlessServiceException(response, "List credentials")
        }
    }

    override fun deleteCredentials(privateKey: String, credentialId: String): Boolean {
        val request = buildRequest(
            "$baseUrl/credentials/delete",
            generateHeaders(privateKey),
            createPostBody(mapOf("credentialId" to credentialId)),
        )
        val response = sendRequest(request)

        return if (response.isOk()) {
            true
        } else {
            throw KpasswordlessServiceException(response, "Delete credentials")
        }
    }
}
