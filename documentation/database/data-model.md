# Modelo de dados proposto

O modelo abaixo é conceitual, não uma migração aplicada.

## Entidades principais

| Entidade | Finalidade |
|---|---|
| User | identidade interna, estado e timestamps |
| ExternalIdentity | provedor e subject externo, sem armazenar senha do Google |
| UserProfile | nome público, avatar, bio e preferências |
| Recipe | dono, título, descrição, categoria, visibilidade e metadados |
| RecipeVersion | snapshots de alterações quando necessário |
| Ingredient | ingrediente normalizado |
| RecipeIngredient | quantidade, unidade e ordem |
| RecipeStep | sequência e instrução de preparo |
| RecipeMedia | arquivo e vínculo de acesso |
| Category / Tag | classificação |
| Cookbook / CookbookRecipe | livros e referências salvas |
| RecipeRating / RecipeComment | interações |
| UserFollow / UserBlock | relações sociais |
| CommunityPost | publicação da comunidade |
| ContentReport / ModerationAction | denúncias e decisões |
| Conversation / ConversationMessage | histórico de IA conforme retenção |
| Subscription / PaymentEvent | estado comercial e eventos externos |
| Notification | avisos ao usuário |
| PrivacyRequest | solicitações de titulares |
| AuditEvent | trilha de operações sensíveis |

## Regras e integridade

- UUIDs ou identificadores imprevisíveis para recursos expostos.
- Foreign keys e restrições de consistência.
- Índices para dono, visibilidade, categoria, data e busca.
- Índices públicos devem conter somente receitas publicáveis.
- Dados privados continuam protegidos mesmo quando identificadores são conhecidos.
- Unicidade de seguimentos e avaliações conforme regra de produto.
- Webhooks e eventos externos devem possuir chave de idempotência.
- Exclusões devem considerar referências, retenção legal e auditoria.

## Modelo de privacidade

`visibility` pode assumir estados como `private`, `shared` e `public`, mas esse atributo não substitui autorização. Cada operação verifica também proprietário, permissões, estado do recurso e contexto do usuário.
