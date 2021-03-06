package fr.ekito.injector.kotlinapp.ws

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by arnaud on 25/04/2016.
 */
interface GitHubService {

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Observable<List<Repo>>
}
