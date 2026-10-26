# Modelo de dados lógico

Este é um modelo lógico inicial. A implementação física depende do backend escolhido; Room usa entidades locais próprias e não deve ser tratada como autoridade global.

## Entidades
- **User**: id, displayName, avatarUrl opcional, bio opcional, createdAt, status.
- **UserPreferences**: userId, unidades, idioma, categorias preferidas, notificações.
- **Recipe**: id, ownerId, title, description, servings, prepTime, cookTime, visibility (PRIVATE/PUBLIC), status (DRAFT/PUBLISHED/ARCHIVED), createdAt, updatedAt, version.
- **Ingredient**: id, recipeId, name, quantity opcional, unit opcional, sortOrder.
- **RecipeStep**: id, recipeId, instruction, sortOrder, imageUrl opcional.
- **RecipeCategory**: id, name, slug.
- **RecipeCategoryLink**: recipeId, categoryId.
- **RecipeImage**: id, recipeId, storageKey, altText opcional, sortOrder.
- **Favorite**: userId, recipeId, createdAt.
- **Collection** e **CollectionRecipe**: coleções pessoais e respetivas receitas.
- **Follow**: followerId, followedId, createdAt.
- **Comment**: id, recipeId, authorId, body, createdAt, status.
- **Reaction**: userId, recipeId, type, createdAt.
- **Report**: id, reporterId, targetType, targetId, reason, status, createdAt.
- **SubscriptionEntitlement**: userId, productId, status, expiryTime, source; validar no servidor.
- **SyncOperation** local: operationId, entityType, entityId, operation, payloadVersion, state, retryCount, updatedAt.

## Invariantes
- Toda receita tem proprietário.
- PRIVATE é o valor padrão.
- PUBLIC não implica expor dados privados do autor.
- O servidor verifica propriedade e permissão em cada leitura/escrita.
- Comentários e relações sociais têm chaves e políticas de integridade.
- Eliminação/retirada de publicação deve refletir-se em caches e índices.
- Guardar apenas dados necessários; definir retenção para logs, uploads e conteúdo eliminado.

## Índices e paginação
Planejar índices para ownerId + updatedAt, visibility + status + createdAt, recipeId em ingredientes/etapas, e chaves únicas de favoritos/seguidores. Listas públicas devem usar paginação no servidor.
