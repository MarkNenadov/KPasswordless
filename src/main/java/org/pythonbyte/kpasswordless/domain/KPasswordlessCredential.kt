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
            val credential =
                KPasswordlessCredential().apply {
                    with(jsonObject) {
                        getObject("descriptor").let {
                            id = it.getString("id")
                            type = it.getString("type")
                        }

                        publicKey = getString("publicKey")
                        userHandle = getString("userHandle")
                        origin = getString("origin")
                        country = getString("country")
                        device = getString("device")
                    }
                }

            return credential
        }

        fun createList(array: List<JsonObject>): List<KPasswordlessCredential> {
            return array.map { jsonObject -> create(jsonObject) }
        }
    }
}
