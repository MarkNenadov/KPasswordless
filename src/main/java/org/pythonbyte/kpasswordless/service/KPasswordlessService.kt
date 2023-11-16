package org.pythonbyte.kpasswordless.service

import org.pythonbyte.kpasswordless.domain.KPasswordlessCredential
import org.pythonbyte.kpasswordless.domain.KPasswordlessIdentity
import org.pythonbyte.kpasswordless.domain.KPasswordlessSignIn

interface KPasswordlessService {
    fun registerToken(privateKey: String, identity: KPasswordlessIdentity): String
    fun addAliases(privateKey: String, identity: KPasswordlessIdentity, aliases: List<String>): Boolean
    fun signin(privateKey: String, token: String): KPasswordlessSignIn
    fun listCredentials(privateKey: String, identity: KPasswordlessIdentity): List<KPasswordlessCredential>
    fun deleteCredentials(privateKey: String, credentialId: String): Boolean
}
