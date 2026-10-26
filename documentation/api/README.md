# API Saborê — contrato proposto

Prefixo sugerido: `/api/v1`. Estes endpoints são exemplos de desenho, não rotas implementadas.

## Identidade
- `GET /auth/google`
- `GET /auth/google/callback`
- `POST /auth/logout`
- `GET /users/me`
- `PATCH /users/me`
- `DELETE /users/me`

## Receitas
- `GET /recipes`
- `POST /recipes`
- `GET /recipes/:id`
- `PATCH /recipes/:id`
- `DELETE /recipes/:id`
- `POST /recipes/:id/publish`
- `POST /recipes/:id/archive`

## Livro pessoal
- `GET /cookbook`
- `GET /cookbook/recipes`
- `GET /cookbook/collections`
- `POST /cookbook/collections`
- `PATCH /cookbook/collections/:id`
- `DELETE /cookbook/collections/:id`
- `POST /cookbook/export`

## Comunidade
- `GET /community/feed`
- `GET /community/users/:id`
- `POST /community/users/:id/follow`
- `DELETE /community/users/:id/follow`
- `POST /community/posts`
- `POST /community/posts/:id/comments`
- `POST /community/reports`
- `POST /community/users/:id/block`

## Assistente
- `POST /assistant/conversations`
- `GET /assistant/conversations`
- `GET /assistant/conversations/:id`
- `POST /assistant/conversations/:id/messages`
- `DELETE /assistant/conversations/:id`

## Assinaturas
- `GET /subscriptions/plans`
- `GET /subscriptions/me`
- `POST /subscriptions/checkout`
- `POST /subscriptions/restore`
- `POST /webhooks/payments/:provider`

## Convenções
- Validar e normalizar todas as entradas.
- Retornar erros consistentes, sem dados internos ou segredos.
- Paginar listas.
- Aplicar rate limit por usuário/IP conforme endpoint.
- Definir idempotency keys em operações com efeitos externos.
- Documentar autenticação, esquemas e códigos de erro em OpenAPI.
- Não permitir leitura de recursos privados sem autorização explícita.
