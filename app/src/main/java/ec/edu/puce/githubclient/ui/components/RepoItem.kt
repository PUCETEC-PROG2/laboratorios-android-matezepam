package ec.edu.puce.githubclient.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ec.edu.puce.githubclient.models.GithubUser
import ec.edu.puce.githubclient.models.Repository

@Composable
fun RepoItem(
    repository: Repository,
    modifier: Modifier = Modifier,
    onEditClick: (Repository) -> Unit = {},
    onDeleteClick: (Repository) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            AsyncImage(
                model = repository.owner.avatarUrl,
                contentDescription = "Avatar de ${repository.owner.login}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = repository.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                repository.description?.let { description ->
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                repository.language?.let { language ->
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = language,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            IconButton(
                onClick = {
                    onEditClick(repository)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar repositorio"
                )
            }

            IconButton(
                onClick = {
                    onDeleteClick(repository)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar repositorio"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    val repository = Repository(
        id = 1234,
        name = "RepositorioDjango",
        description = "Proyecto de Python de Mateo",
        language = "Python",
        owner = GithubUser(
            id = 123213,
            login = "matezepam",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    )

    RepoItem(
        repository = repository
    )
}