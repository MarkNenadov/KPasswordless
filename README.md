# Kpasswordless - A Kotlin client for passwordless.dev 

A Kotlin client for the passwordless.dev API (https://passwordless.dev/).

Latest release: [[v0.0.3](https://github.com/MarkNenadov/KPasswordless/releases/download/v0.0.3/KPasswordless-0.0.3.jar)]

Note: Between registerToken() and signIn() you will need to make the front-end signInWtih*() call that the passwordless.dev docs mention.
Note: If you want to use the test case, you will need to fill in test/resources/kpasswordless.properties

### Service ###

```

KPasswordlessIdentity {
    userName: String
    userId: String
    displayName: String;
}

KPasswordlessSignIn {
    isSuccess: Boolean
    userId: String
    timestamp: String
    origin: String
    device: String
    country: String
    nickname: String
    expiresAt: String
    credentialId: String
    type: String
    rpId: String
}

class KpasswordlessCredential {
    var id: String
    var type: String
    var publicKey: String
    var userHandle: String
    var origin: String
    var country: String
    var device: String
}

interface KpasswordlessService {
    fun registerToken( privateKey: String, identity: KPasswordlessIdentity ): String
    fun addAliases( privateKey: String, identity: KPasswordlessIdentity, aliases: List<String> ): Boolean
    fun signin( privateKey: String, token: String ): KPasswordlessSignIn
    fun deleteCredentials(privateKey: String, credentialId: String): Boolean
}
```

Runtime Exceptions:
* KpasswordlessServiceException

## Tech

Kotlin, IntelliJ, Maven

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=kotlin,idea,maven" />
  </a>
</p>
