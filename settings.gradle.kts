rootProject.name = "DevOps"

val cacheUrl = "GRADLE_REMOTE_CACHE_URL"
if (System.getenv(cacheUrl) != null) {
    buildCache {
        remote<HttpBuildCache> {
            url = uri(System.getenv(cacheUrl))
            isAllowInsecureProtocol = true
            isAllowUntrustedServer = true

            isPush = System.getenv("GRADLE_REMOTE_CACHE_PUSH")?.toBoolean() ?: false

            val username = System.getenv("GRADLE_REMOTE_CACHE_USERNAME")
            val password = System.getenv("GRADLE_REMOTE_CACHE_PASSWORD")
            if (username != null && password != null) {
                credentials {
                    this.username = username
                    this.password = password
                }
            }
        }
    }
}
