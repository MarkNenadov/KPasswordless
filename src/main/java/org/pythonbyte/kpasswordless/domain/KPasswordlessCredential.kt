package org.pythonbyte.kpasswordless.domain

import org.pythonbyte.krux.json.JsonObject

class KPasswordlessCredential {
    var id = ""
    var type = ""
    var publicKey = ""
    var userHandle = ""
    var origin = ""
    var country = ""
    var device = ""

    companion object {
        private fun create(jsonObject: JsonObject): KPasswordlessCredential {
            val credential = KPasswordlessCredential().apply {
                val descriptorObject = jsonObject.getObject("descriptor")
                id = descriptorObject.getString("id")
                type = descriptorObject.getString("type")

                publicKey = jsonObject.getString("publicKey")
                userHandle = jsonObject.getString("userHandle")
                origin = jsonObject.getString("origin")
                country = jsonObject.getString("country")
                device = jsonObject.getString("device")
            }

            return credential
        }

        fun createList(array: List<JsonObject>): List<KPasswordlessCredential> {
            return array.map { jsonObject -> create( jsonObject ) }
        }
    }
}