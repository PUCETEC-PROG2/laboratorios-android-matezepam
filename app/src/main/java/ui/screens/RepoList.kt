package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ec.edu.puce.githubclient.ui.components.RepoItem

@Composable
fun RepoList() {
    Column(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 40.dp)
    ) {

        RepoItem(
            name = "PauloAndroidApp",
            description = "Aplicación móvil desarrollada en Android Studio usando Jetpack Compose para visualizar repositorios de GitHub.",
            avatarImg = "https://avatars.githubusercontent.com/u/3?v=4",
            language = "Kotlin"
        )

        RepoItem(
            name = "GitHubComposeClient",
            description = "Proyecto académico enfocado en la creación de una interfaz moderna para mostrar repositorios de forma dinámica.",
            avatarImg = "https://avatars.githubusercontent.com/u/4?v=4",
            language = "Jetpack Compose"
        )

        RepoItem(
            name = "MobileLabProject",
            description = "Laboratorio de desarrollo Android orientado a componentes reutilizables y diseño limpio de interfaces.",
            avatarImg = "https://avatars.githubusercontent.com/u/5?v=4",
            language = "Kotlin"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepoListPreview() {
    RepoList()
}