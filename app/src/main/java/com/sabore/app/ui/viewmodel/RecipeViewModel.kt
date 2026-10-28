package com.sabore.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabore.app.data.model.Recipe
import com.sabore.app.data.model.RecipeCreateRequest
import com.sabore.app.data.repository.RecipeRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun loadPublicRecipes(category: String? = null, query: String? = null,
                          difficulty: String? = null, maxTime: Int? = null,
                          page: Int? = null, limit: Int? = null) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                recipeRepository.getPublicRecipes(category, query, difficulty, maxTime, page, limit)
                // As recipes vem pelo StateFlow do repository
                recipeRepository.recipes.collect { recipes ->
                    _recipes.value = recipes
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro ao carregar receitas"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createRecipe(request: RecipeCreateRequest, onResult: (Recipe?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val created = recipeRepository.createRecipe(request)
                onResult(created)
            } catch (e: Exception) {
                _error.value = e.message ?: "Erro ao criar receita"
                onResult(null)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
