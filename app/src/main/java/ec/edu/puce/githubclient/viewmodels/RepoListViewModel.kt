package ec.edu.puce.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.edu.puce.githubclient.models.Repository
import ec.edu.puce.githubclient.models.RepositoryPayload
import ec.edu.puce.githubclient.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoListViewModel : ViewModel() {

    private val username = "matezepam"

    private val _repos = MutableStateFlow<List<Repository>>(emptyList())
    val repos: StateFlow<List<Repository>> = _repos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMsg = MutableStateFlow<String?>(null)
    val errorMsg = _errorMsg.asStateFlow()

    private val _successMsg = MutableStateFlow<String?>(null)
    val successMsg = _successMsg.asStateFlow()

    init {
        fetchRepos()
    }

    fun fetchRepos() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            _successMsg.value = null

            try {
                _repos.value = RetrofitClient.apiService.getRepositories()
            } catch (e: Exception) {
                _errorMsg.value = "Error al cargar repositorios: ${e.localizedMessage}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createRepo(
        name: String,
        description: String,
        isPrivate: Boolean,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            _successMsg.value = null

            try {
                val repositoryPayload = RepositoryPayload(
                    name = name,
                    description = description,
                    isPrivate = isPrivate
                )

                RetrofitClient.apiService.createRepository(repositoryPayload)

                _repos.value = RetrofitClient.apiService.getRepositories()

                _successMsg.value = "Repositorio creado correctamente"

                onSuccess()

            } catch (e: Exception) {
                _errorMsg.value = "Error al crear repositorio: ${e.localizedMessage}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateRepo(
        oldName: String,
        newName: String,
        newDescription: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            _successMsg.value = null

            try {
                val repositoryPayload = RepositoryPayload(
                    name = newName,
                    description = newDescription
                )

                val updatedRepository = RetrofitClient.apiService.updateRepository(
                    owner = username,
                    repo = oldName,
                    repositoryPayload = repositoryPayload
                )

                _repos.value = _repos.value.map { repository ->
                    if (repository.name == oldName) {
                        updatedRepository
                    } else {
                        repository
                    }
                }

                _successMsg.value = "Repositorio actualizado correctamente"

            } catch (e: Exception) {
                _errorMsg.value = "Error al actualizar repositorio: ${e.localizedMessage}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteRepo(repoName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMsg.value = null
            _successMsg.value = null

            try {
                val response = RetrofitClient.apiService.deleteRepository(
                    owner = username,
                    repo = repoName
                )

                if (response.isSuccessful) {
                    _repos.value = _repos.value.filter { repository ->
                        repository.name != repoName
                    }

                    _successMsg.value = "Repositorio eliminado correctamente"
                } else {
                    _errorMsg.value = "Error al eliminar repositorio. Código: ${response.code()}"
                }

            } catch (e: Exception) {
                _errorMsg.value = "Error al eliminar repositorio: ${e.localizedMessage}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearSuccessMessage() {
        _successMsg.value = null
    }
}