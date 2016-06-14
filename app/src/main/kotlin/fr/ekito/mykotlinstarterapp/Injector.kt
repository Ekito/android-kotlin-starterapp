package fr.ekito.mykotlinstarterapp

import fr.ekito.injector.kotlinapp.ws.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by arnaud on 14/06/2016.
 */
object Injector {

    val githubWS: GitHubService by lazy { initGithubWS(httpClient()) }

    fun httpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return httpClient
    }

    fun initGithubWS(httpClient: OkHttpClient): GitHubService {

        val gsonConverterFactory = GsonConverterFactory.create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(httpClient).addConverterFactory(gsonConverterFactory)
                .build()

        val service = retrofit.create<GitHubService>(GitHubService::class.java)
        return service
    }

}