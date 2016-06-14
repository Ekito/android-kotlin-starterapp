package fr.ekito.injector.kotlinapp.ws

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by arnaud on 25/04/2016.
 */
interface GitHubService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>
}
