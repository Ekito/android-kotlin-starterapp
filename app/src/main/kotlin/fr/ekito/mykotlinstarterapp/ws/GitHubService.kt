package fr.ekito.injector.kotlinapp.ws

import fr.ekito.mykotlinstarterapp.ws.Github
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by arnaud on 25/04/2016.
 */
interface GitHubService {

    @GET("users/{user}")
    fun listRepos(@Path("user") user: String): Call<List<Github>>
}
