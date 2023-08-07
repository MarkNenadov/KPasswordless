package org.pythonbyte.haveibeenkwned.service

import com.squareup.okhttp.*
import org.json.JSONObject
import org.pythonbyte.haveibeenkwned.domain.KPasswordlessIdentity
import org.pythonbyte.haveibeenkwned.domain.KPasswordlessSignIn
import org.pythonbyte.haveibeenkwned.domain.KpasswordlessCredential
import org.pythonbyte.krux.http.buildRequest
import org.pythonbyte.krux.json.JsonObject
import org.pythonbyte.krux.properties.PropertyReader
import org.pythonbyte.krux.http.sendRequest
import java.lang.Exception

class KpasswordlessServiceImpl : KpasswordlessService {
    private val propertiesFile = "/kpasswordless.properties"
    private val propertyReader = PropertyReader(propertiesFile)
    private val baseUrl = propertyReader.get("kpasswordless.baseUrl")

    private fun generateHeaders( privateKey: String ): Headers {
        return Headers.Builder().add( "ApiSecret", privateKey ).add( "Content-Type", "application/json" ).build()
    }

    private fun createPostBody( bodyMap: Map<String, Any> ) : RequestBody {
        val jsonContent = JsonObject( JSONObject( bodyMap ) ).toString()

        return RequestBody.create( MediaType.parse( "application/json; charset=utf-8"), jsonContent)
    }

    override fun registerToken( privateKey: String, identity: KPasswordlessIdentity): String {
        val request = buildRequest( "$baseUrl/register/token", generateHeaders( privateKey ), createPostBody( identity.map() ) )
        val response = sendRequest( request )

        if ( response.code() == 200 ) {
            val responseObject = JsonObject( JSONObject( response.body().string().trim() ))
            return responseObject.getString("token")
        }
        throw Exception( "Register Token failed with code ${response.code()}] [${response.message()}]" )
    }

    override fun addAliases( privateKey: String, identity: KPasswordlessIdentity, aliases: List<String>): Boolean {
        val request = buildRequest("$baseUrl/alias", generateHeaders( privateKey), createPostBody( mapOf( "userId" to identity.userId, "aliases" to aliases ) ) )

        val response = sendRequest( request )

        if ( response.code() == 200 ) {
            return true
        }
        throw Exception( "Add Aliases failed with code [${response.code()}] [${response.message()}]" )
    }

    override fun signin( privateKey: String, token: String ): KPasswordlessSignIn {
        val request = buildRequest( "$baseUrl/signin/verify", generateHeaders( privateKey ), createPostBody( mapOf( "token" to token ) ) )
        val response = sendRequest( request )

        if ( response.code() == 200 ) {
            val responseObject = JsonObject( JSONObject( response.body().string().trim() ))

            return KPasswordlessSignIn.create(  responseObject )
        }

        val responseJson = response.body().string().trim()

        throw Exception( "Sign In failed with code [${response.code()}] [${response.message()}] json [$responseJson]" )
    }

    override fun listCredentials( privateKey: String, identity: KPasswordlessIdentity ): List<KpasswordlessCredential> {
        val request = buildRequest( "$baseUrl/credentials/list?userId=${identity.userId}", generateHeaders( privateKey ), createPostBody( mapOf( "userId" to identity.userId )  ) )
        val response = sendRequest( request )

        if ( response.code() == 200 ) {
            val responseJson = response.body().string().trim()
            val responseObject = JsonObject( JSONObject(responseJson))

            return KpasswordlessCredential.createList( responseObject.getArray("values") )
        }

        val responseJson = response.body().string().trim()

        throw Exception( "List Credentials failed with code [${response.code()}] [${response.message()}] json [$responseJson]" )
    }

    override fun deleteCredentials(privateKey: String, credentialId: String): Boolean {
        val request = buildRequest("$baseUrl/credentials/delete", generateHeaders( privateKey), createPostBody( mapOf( "credentialId" to credentialId ) ) )

        val response = sendRequest( request )

        if ( response.code() == 200 ) {
            return true
        }

        throw Exception( "Delete Aliases failed with code [${response.code()}] [${response.message()}] json [${response.body().string().trim()}]" )
    }

}
