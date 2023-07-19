# 401 Unauthorised

- A [401](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/401) error implies you don't have the right to access a resource.
- This can be because of a wrong access token or an absence of it.
- To add an access token to your request add it as a part of the header
- If you want to make it a part of all your requests you can add an interceptor which does the job of attaching the access token to each request. [Reference](https://stackoverflow.com/questions/59910397/how-to-fix-401-unauthorized-error-with-using-retrofit-in-android)
```
import okhttp3.Credentials
import okhttp3.Interceptor


class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}
```
- In case a token expires or you want a new token, you can add an interceptor or an authenticator which does this for you as well
  - [ ] https://stackoverflow.com/questions/55688352/retrofit-handle-refresh-token-expiration
  - [ ] https://proandroiddev.com/jwt-authentication-and-refresh-token-in-android-with-retrofit-interceptor-authenticator-da021f7f7534
  - [ ] https://medium.com/@manuchekhrdev/access-token-expiration-refresh-token-retrofit-interceptor-coroutines-c3c75069de86
  - [ ] https://medium.com/android-news/how-we-do-automatic-token-refreshing-using-androids-new-work-manager-a0347f506117
