package ec.edu.puce.githubclient.models

import com.google.gson.annotations.SerializedName

data class RepositoryPayload(
    val name: String,
    val description: String,

    @SerializedName("private")
    val isPrivate: Boolean
)