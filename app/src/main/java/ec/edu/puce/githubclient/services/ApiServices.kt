package ec.edu.puce.githubclient.services

import ec.edu.puce.githubclient.models.Repository
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET("users/{username}/repos")
    suspend fun getRepositories(
        @Path("username") username: String
    ): List<Repository>
}