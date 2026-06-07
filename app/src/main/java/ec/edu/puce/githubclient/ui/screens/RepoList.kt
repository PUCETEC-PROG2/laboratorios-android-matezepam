package ec.edu.puce.githubclient.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.ui.components.RepoItem
import ec.edu.puce.githubclient.viewmodels.RepoListViewModel

@Composable
fun RepoList(
    modifier: Modifier = Modifier,
    viewModel: RepoListViewModel = viewModel()
) {
    var showForm by remember { mutableStateOf(false) }

    var repoToEdit by remember { mutableStateOf<Repository?>(null) }
    var repoToDelete by remember { mutableStateOf<Repository?>(null) }

    var editName by remember { mutableStateOf("") }
    var editDescription by remember { mutableStateOf("") }

    val repos by viewModel.repos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMsg by viewModel.errorMsg.collectAsState()
    val successMsg by viewModel.successMsg.collectAsState()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(successMsg) {
        successMsg?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearSuccessMessage()
        }
    }

    if (showForm) {
        RepoForm(
            onBackClick = {
                showForm = false
            },
            viewModel = viewModel
        )
    } else {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        showForm = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar repositorio"
                    )
                }
            }
        ) { paddingValues ->

            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                errorMsg?.let { message ->
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp)
                    )
                }

                if (!isLoading && errorMsg == null) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(repos) { repository ->
                            RepoItem(
                                repository = repository,
                                onEditClick = { selectedRepository ->
                                    repoToEdit = selectedRepository
                                    editName = selectedRepository.name
                                    editDescription = selectedRepository.description ?: ""
                                },
                                onDeleteClick = { selectedRepository ->
                                    repoToDelete = selectedRepository
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    repoToEdit?.let { repository ->
        AlertDialog(
            onDismissRequest = {
                repoToEdit = null
            },
            title = {
                Text(
                    text = "Actualizar repositorio"
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = editName,
                        onValueChange = {
                            editName = it
                        },
                        label = {
                            Text("Nombre")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = editDescription,
                        onValueChange = {
                            editDescription = it
                        },
                        label = {
                            Text("Descripción")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (editName.isNotBlank()) {
                            viewModel.updateRepo(
                                oldName = repository.name,
                                newName = editName.trim(),
                                newDescription = editDescription.trim()
                            )

                            repoToEdit = null
                        }
                    }
                ) {
                    Text(
                        text = "Guardar"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        repoToEdit = null
                    }
                ) {
                    Text(
                        text = "Cancelar"
                    )
                }
            }
        )
    }

    repoToDelete?.let { repository ->
        AlertDialog(
            onDismissRequest = {
                repoToDelete = null
            },
            title = {
                Text(
                    text = "Confirmar eliminación"
                )
            },
            text = {
                Text(
                    text = "¿Seguro que deseas eliminar el repositorio ${repository.name}?"
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteRepo(repository.name)
                        repoToDelete = null
                    }
                ) {
                    Text(
                        text = "Eliminar"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        repoToDelete = null
                    }
                ) {
                    Text(
                        text = "Cancelar"
                    )
                }
            }
        )
    }
}