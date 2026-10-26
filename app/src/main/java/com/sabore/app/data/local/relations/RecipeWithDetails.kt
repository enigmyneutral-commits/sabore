package com.sabore.app.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.sabore.app.data.local.entities.IngredientEntity
import com.sabore.app.data.local.entities.RecipeEntity
import com.sabore.app.data.local.entities.StepEntity

data class RecipeWithDetails(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredients: List<IngredientEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val steps: List<StepEntity>
)
