package org.pythonbyte.haveibeenkwned.service

import com.squareup.okhttp.Headers
import com.squareup.okhttp.MediaType
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import org.json.JSONObject
import org.pythonbyte.haveibeenkwned.domain.KPasswordlessIdentity
import org.pythonbyte.krux.json.JsonObject
import org.pythonbyte.krux.properties.PropertyReader
import java.lang.Exception

class KpasswordlessServiceImpl : KpasswordlessService {
    private val propertiesFile = "/kpasswordless.properties"
    private val propertyReader = PropertyReader(propertiesFile)
    private val baseUrl = propertyReader.get("kpasswordless.baseUrl")

    private fun generateHeaders( privateKey: String ): Headers {
        return Headers.Builder().add( "ApiSecret", privateKey ).add( "Content-Type", "application/json" ).build();
    }

    private fun createPostBody( bodyMap: Map<String, Any> ) : RequestBody {
        val jsonContent = JsonObject( JSONObject( bodyMap ) ).toString()

        return RequestBody.create( MediaType.parse( "application/json; charset=utf-8"), jsonContent)
    }

    override fun registerToken( privateKey: String, identity: KPasswordlessIdentity): String {
        val request = Request.Builder().url(  "$baseUrl/register/token" ).headers( generateHeaders( privateKey ) ).post( createPostBody( identity.map() ) ).build();
        val response = OkHttpClient().newCall( request ).execute();

        if ( response.code() == 200 ) {
            val responseObject = JsonObject( JSONObject( response.body().string().trim() ))
            return responseObject.getString("token")
        }
        throw Exception( "Register Token failed with code [" + response.code() + "] [" + response.message() + "]" );
    }

    override fun addAliases( privateKey: String, identity: KPasswordlessIdentity, aliases: List<String>): Boolean {
        val request = Request.Builder().url( "$baseUrl/alias" ).headers( generateHeaders( privateKey ) ).post( createPostBody( mapOf( "userId" to identity.userId, "aliases" to aliases ) ) ).build();
        val response = OkHttpClient().newCall( request ).execute();

        if ( response.code() == 200 ) {
            return true
        }
        throw Exception( "Register Token failed with code [" + response.code() + "] [" + response.message() + "]" );
    }
 }
