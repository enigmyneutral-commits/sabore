# Plano de Implementação - Aplicativo Android Saborê

Este plano detalha a criação do aplicativo Android **Saborê** em Jetpack Compose, seguindo os requisitos de visão do produto (Biblioteca Gastronômica, Livro Pessoal, Comunidade, Modo de Cozinha, Autenticação Google, Anúncios AdMob para o plano gratuito e Assinatura Saborê Pro por R$ 4,99/mês para remoção de anúncios).

## User Review Required

> [!IMPORTANT]
> - **Autenticação:** Integração com Google Sign-In (Credential Manager / Firebase Auth).
> - **Monetização (AdMob + Pro):** Exibição de banners AdMob para usuários gratuitos e opção de assinatura Saborê Pro (R$ 4,99/mês) via Google Play Billing para remover anúncios.
> - **Arquitetura:** Clean Architecture / MVVM com Jetpack Compose, Room Database para armazenamento local (livro pessoal e cache offline) e Coroutines/Flow.

## Open Questions

- Nenhuma dúvida pendente. O escopo está alinhado com o documento de visão e requisitos funcionais.

## Proposed Changes

### Estrutura do Projeto Android (`com.sabore.app`)

#### [NEW] [build.gradle.kts (Project & App)](file:///run/media/deevo-financeira/AI_KNOWLEDGE/sabore-main/build.gradle.kts)
- Configuração do Gradle com dependências do Jetpack Compose, Material 3, Room, Hilt, Firebase, Credential Manager, Play Billing e Google Mobile Ads (AdMob).

#### [NEW] [AndroidManifest.xml](file:///run/media/deevo-financeira/AI_KNOWLEDGE/sabore-main/app/src/main/AndroidManifest.xml)
- Declaração de permissões (Internet, Billing, AdMob App ID) e Activities.

#### [NEW] [MainActivity.kt](file:///run/media/deevo-financeira/AI_KNOWLEDGE/sabore-main/app/src/main/MainActivity.kt)
- Activity principal configurada com Compose e Injeção de Dependências.

### Camada de Dados e Domínio
#### [NEW] [SaboreDatabase.kt & Entities](file:///run/media/deevo-financeira/AI_KNOWLEDGE/sabore-main/app/src/main/java/com/sabore/app/data/local/SaboreDatabase.kt)
- Entidades Room: `UserEntity`, `RecipeEntity`, `IngredientEntity`, `StepEntity`, `CookbookRecipeEntity`.
- Repositórios para gerenciamento de receitas locais e remotas.

### Camada de Apresentação (Jetpack Compose UI)
#### [NEW] [Navigation & Screens](file:///run/media/deevo-financeira/AI_KNOWLEDGE/sabore-main/app/src/main/java/com/sabore/app/ui/navigation/SaboreNavGraph.kt)
- **LoginScreen (AUTH-02/03):** Autenticação Google.
- **HomeScreen (HOME-01/06):** Feed, categorias e banner AdMob (para plano gratuito).
- **RecipeDetailScreen & CookingMode (REC-01/03/04):** Detalhes da receita e modo de preparo passo a passo.
- **MyCookbookScreen (REC-10):** Livro de receitas pessoal, favoritos e botão "Adicionar receita".
- **AddRecipeScreen:** Cadastro de receitas em 5 etapas (Básico, Ingredientes, Preparo, Organização, Privacidade).
- **Profile & ProSubscriptionScreen:** Gerenciamento de perfil e assinatura Saborê Pro (R$ 4,99/mês).

## Verification Plan

### Automated Tests
- Testes unitários para ViewModels e regras de negócio de receitas e visibilidade.
- Testes de DAO do Room.

### Manual Verification
- Execução do app no emulador Android.
- Validação do fluxo de login com Google, navegação pelas categorias, cadastro de receita privada, modo de cozinha, exibição de anúncios AdMob no plano gratuito e simulação de assinatura Pro.
