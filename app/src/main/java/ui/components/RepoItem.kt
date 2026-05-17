package ec.edu.puce.githubclient.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RepoItem(
    name: String,
    description: String,
    avatarImg: String,
    language: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 18.dp)
        ) {
            AsyncImage(
                model = avatarImg,
                contentDescription = "Imagen de $name",
                modifier = Modifier.size(size = 65.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(width = 14.dp))

            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(height = 6.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(height = 6.dp))

                Text(
                    text = "Lenguaje principal: $language",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    RepoItem(
        name = "PauloGitProject",
        description = "Aplicación Android desarrollada con Jetpack Compose para visualizar repositorios de GitHub de forma sencilla y moderna.",
        avatarImg = "https://avatars.githubusercontent.com/u/2?v=4",
        language = "Kotlin"
    )
}