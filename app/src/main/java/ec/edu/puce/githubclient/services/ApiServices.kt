package ec.edu.puce.githubclient.services

import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.models.RepositoryPayload
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {

    @GET("user/repos?sort=created&direction=desc")
    suspend fun getRepositories(): List<Repository>

    @POST("user/repos")
    suspend fun createRepository(
        @Body repositoryPayload: RepositoryPayload
    ): Repository

    @PATCH("repos/{owner}/{repo}")
    suspend fun updateRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Body repositoryPayload: RepositoryPayload
    ): Repository

    @DELETE("repos/{owner}/{repo}")
    suspend fun deleteRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Unit>
}