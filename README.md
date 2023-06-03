# Kpasswordless - A Kotlin client for passwordless.dev 

A Kotlin client for the passwordless.dev API (https://passwordless.dev/).

Note: If you want to use the test case, you will need to fill in test/resources/kpasswordless.properties

### Service ###

```

KPasswordlessIdentity {
    userName: String
    userId: String
    displayName: String;
}

interface KpasswordlessService {
    fun registerToken( privateKey: String, identity: KPasswordlessIdentity ): String
    fun addAliases( privateKey: String, identity: KPasswordlessIdentity, aliases: List<String> ): Boolean
}
```
