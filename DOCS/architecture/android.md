# Arquitetura Android

## Stack proposta
- Kotlin como linguagem principal.
- Jetpack Compose e Material 3 para UI.
- Uma Activity principal com Navigation Compose.
- ViewModel por fluxo/tela, Coroutines e Flow.
- Camadas de apresentação, domínio e dados, com dependências direcionadas para o domínio.
- Room para dados locais e DataStore para preferências não sensíveis.
- WorkManager para tarefas adiáveis de sincronização.
- Cliente HTTP com serialização tipada, timeout e tratamento consistente de erros.
- Firebase Authentication/Google Sign-In apenas depois de validar a configuração de OAuth e a verificação de identidade no backend.

## Organização sugerida
```
app/
  src/main/
  src/test/
  src/androidTest/
core/
  model/
  designsystem/
  database/
  network/
feature/
  auth/
  home/
  search/
  recipe/
  cookbook/
  favorites/
  profile/
  community/
  assistant/
  settings/
```
A estrutura pode ser simplificada no MVP, mas os limites entre UI, regras de negócio e acesso a dados devem permanecer claros.

## Fluxo de dependências
UI Compose → ViewModel → casos de uso (quando úteis) → interfaces de repositório → implementação local/remota.

A UI não deve chamar diretamente o banco de dados nem a API. Os ViewModels expõem estado imutável e recebem ações da interface.

## Estado de UI
Representar explicitamente estados de carregamento, conteúdo, vazio e erro. Evitar múltiplos booleanos incompatíveis. Usar eventos de curta duração para navegação e mensagens.

## Configuração
- Usar version catalog/Gradle para dependências fixadas.
- Separar configurações debug e release.
- Segredos locais apenas em mecanismos fora do controlo de versão; chaves privadas não devem ser entregues ao APK.
- Definir applicationId, minSdk, targetSdk e versões após verificar requisitos atuais do Google Play.
- CI deve compilar, executar testes e verificar formatação/análise estática.

## Decisões ainda a validar
Backend e fornecedor de imagens, mecanismo final de autenticação, versões de SDK/dependências, política de cache, telemetria e fornecedor de IA.
