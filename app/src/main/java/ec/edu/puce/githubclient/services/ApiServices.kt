package ec.edu.puce.githubclient.services

import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.models.RepositoryPayload
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {

    @GET("users/{username}/repos")
    suspend fun getRepositories(
        @Path("username") username: String
    ): List<Repository>

    @POST("user/repos")
    suspend fun createRepository(
        @Body repositoryPayload: RepositoryPayload
    ): Repository
}