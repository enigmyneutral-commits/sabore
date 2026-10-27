<div align="center">

# 🍲 Saborê

### Plataforma de Gastronomia, Receitas Digitais e Comunidade Culinária

**Receitas que conectam pessoas. Sabores que contam histórias.**

Uma plataforma para descobrir receitas, preservar tradições culinárias, organizar livros pessoais, compartilhar conhecimento gastronómico e conectar pessoas apaixonadas por cozinhar.

<br/>

**Web · Android · iOS · PWA · API · Inteligência Artificial**

</div>

---

## 1. Visão geral

O **Saborê** é um projeto de plataforma gastronómica digital concebido para reunir receitas brasileiras e internacionais, livros de receitas pessoais, publicação de conteúdo culinário, comunidade social e assistência inteligente para cozinhar.

A plataforma combina uma biblioteca gastronómica, um caderno digital privado e uma rede de pessoas que compartilham suas experiências culinárias.

Cada usuário poderá organizar suas próprias receitas, preservar preparações familiares, descobrir novos pratos e decidir quais conteúdos deseja compartilhar publicamente.

O projeto será desenvolvido com arquitetura modular, APIs desacopladas, controles de acesso, observabilidade e infraestrutura preparada para evolução progressiva.

### Objetivos do produto

- Centralizar receitas de diferentes culturas e especialidades gastronómicas.
- Permitir que qualquer usuário construa seu próprio livro de receitas digital.
- Garantir que receitas pessoais sejam privadas por padrão.
- Permitir a publicação voluntária de receitas na comunidade.
- Criar uma rede social especializada em gastronomia.
- Disponibilizar um assistente culinário baseado em inteligência artificial.
- Monetizar o produto por publicidade e assinaturas.
- Preservar a autoria e os direitos sobre os conteúdos publicados.
- Construir uma experiência acessível, responsiva e preparada para múltiplas plataformas.

> **Estado do projeto:** este README descreve a visão do produto, os requisitos e a arquitetura pretendida. A implementação de cada funcionalidade deverá ser verificada no código e nos testes antes de ser considerada concluída.

## 2. Modelo de negócio

O Saborê adotará um modelo freemium, permitindo que os usuários utilizem as funcionalidades essenciais gratuitamente e oferecendo uma assinatura premium.

### Plano gratuito

O plano gratuito deverá incluir:

- Cadastro e autenticação com Google.
- Acesso às receitas públicas.
- Pesquisa e filtros gastronómicos.
- Cadastro de receitas próprias.
- Livro de receitas pessoal.
- Receitas privadas e públicas.
- Favoritos e coleções.
- Perfil na comunidade.
- Publicação de receitas.
- Seguimento de outros cozinheiros.
- Interações sociais.
- Acesso ao assistente culinário dentro dos limites definidos.
- Exibição de publicidade nos espaços autorizados.

### Saborê Pro

**Preço de referência: R$ 4,99 por mês.**

Benefício principal: experiência sem anúncios da plataforma.

Benefícios adicionais que poderão ser incluídos após validação comercial:

- Recursos avançados de organização de receitas.
- Exportação personalizada de livros digitais.
- Limites ampliados para determinadas funcionalidades.
- Recursos adicionais do assistente culinário.
- Ferramentas avançadas de organização pessoal.

Os benefícios definitivos deverão ser descritos na página de assinatura antes da comercialização.

| Característica | Gratuito | Pro |
|---|---|---|
| Consultar receitas públicas | Sim | Sim |
| Livro pessoal | Sim | Sim |
| Cadastrar receitas | Sim | Sim |
| Publicar receitas | Sim | Sim |
| Comunidade | Sim | Sim |
| Favoritos e coleções | Sim | Sim |
| Publicidade da plataforma | Sim | Não |
| Recursos premium adicionais | Conforme definição | Conforme plano |
| Preço mensal de referência | R$ 0,00 | R$ 4,99 |

A assinatura deverá possuir gerenciamento de ciclo de vida, renovação, cancelamento, tratamento de falhas de pagamento e validação de direitos no servidor.

O sistema deverá respeitar as regras de faturamento e assinatura aplicáveis ao canal de distribuição utilizado.

## 3. Módulos funcionais

### 3.1. Identity & Access

Responsável pela autenticação e pelo gerenciamento de identidade.

Funcionalidades previstas:

- Autenticação exclusiva com Google.
- Cadastro automático no primeiro acesso.
- Associação da identidade externa à conta interna.
- Criação e edição de perfil.
- Gestão de sessões.
- Encerramento de sessões.
- Revogação de acesso.
- Exclusão de conta.
- Controle de permissões.
- Tratamento de falhas de autenticação.

A autenticação externa deverá ser validada no backend. O sistema não deverá confiar exclusivamente em informações recebidas do cliente.

### 3.2. Recipe Management

Gerenciamento integral de receitas.

Funcionalidades:

- Criar e editar receitas.
- Salvar rascunhos.
- Gerenciar ingredientes e quantidades.
- Organizar o modo de preparo.
- Definir rendimento.
- Informar tempos de preparo e cozimento.
- Adicionar fotografias.
- Categorizar receitas.
- Adicionar etiquetas.
- Criar variações.
- Arquivar e excluir receitas.
- Manter histórico de versões, quando aplicável.

### 3.3. Recipe Privacy

Módulo responsável pelas permissões e pela visibilidade das receitas.

Estados previstos:

- `DRAFT`: rascunho.
- `PRIVATE`: privada.
- `SHARED`: compartilhada com pessoas autorizadas.
- `PUBLIC`: publicada na comunidade.
- `UNDER_REVIEW`: em análise.
- `ARCHIVED`: arquivada.

A aplicação deverá aplicar autorização no servidor para todas as operações relevantes.

Uma receita privada não poderá aparecer em buscas públicas, feeds, perfis de terceiros, exportações não autorizadas ou respostas do chatbot para outros usuários.

A publicação deverá exigir uma ação explícita do proprietário.

### 3.4. Recipe Discovery

Mecanismos para descoberta de conteúdo.

Funcionalidades:

- Pesquisa textual.
- Pesquisa por ingredientes.
- Categorias gastronómicas.
- Filtros.
- Ordenação.
- Receitas populares.
- Receitas recentes.
- Recomendações personalizadas.
- Receitas semelhantes.
- Coleções temáticas.
- Pesquisa semântica em uma fase posterior.

### 3.5. Personal Cookbook

Livro de receitas digital pessoal.

Funcionalidades:

- Minhas receitas.
- Receitas privadas.
- Receitas publicadas.
- Favoritos.
- Coleções personalizadas.
- Notas privadas.
- Histórico de preparações.
- Receitas arquivadas.
- Exportação para PDF.
- Importação estruturada de receitas.

O livro pessoal deverá preservar a separação entre as receitas de autoria do usuário e as receitas de terceiros que ele apenas salvou como referência.

### 3.6. Community

Rede social especializada em gastronomia.

Funcionalidades:

- Perfis públicos.
- Seguidores e pessoas seguidas.
- Feed de publicações.
- Curtidas e reações.
- Comentários e respostas.
- Avaliações de receitas.
- Compartilhamento.
- Publicação de resultados culinários.
- Coleções públicas.
- Descoberta de cozinheiros.
- Notificações sociais.
- Bloqueio de usuários.
- Denúncias e moderação.

#### Modelo de crescimento por conexões

O Saborê poderá representar a comunidade como um grafo lógico de relações.

Os principais elementos serão:

- Usuários.
- Receitas.
- Publicações.
- Seguimentos.
- Comentários.
- Avaliações.
- Coleções.

Cada usuário poderá descobrir outros cozinheiros por meio de receitas, categorias e relações sociais.

**Decisão arquitetural inicial:** utilizar PostgreSQL para armazenar as relações. Um banco de grafos não será necessário até que requisitos reais de escala ou análise de relações justifiquem sua adoção.

### 3.7. AI Cooking Assistant

Assistente culinário com inteligência artificial.

Funcionalidades previstas:

- Sugerir receitas a partir dos ingredientes disponíveis.
- Organizar anotações em receitas estruturadas.
- Explicar etapas de preparo.
- Ajustar quantidades conforme o rendimento.
- Sugerir substituições de ingredientes.
- Criar listas de compras.
- Auxiliar no planejamento de refeições.
- Responder perguntas sobre uma receita.
- Salvar resultados no livro pessoal.
- Manter histórico de conversas, conforme as preferências do usuário.

O assistente deverá respeitar as permissões de acesso às receitas e aos dados utilizados como contexto.

Conversas privadas não poderão ser publicadas automaticamente nem utilizadas para treinamento de modelos sem tratamento jurídico e autorização adequados.

Respostas sobre alergias, conservação de alimentos, segurança alimentar e informações nutricionais exigirão cuidados específicos.

### 3.8. Advertising

Módulo de publicidade.

Responsabilidades:

- Integrar provedores de publicidade.
- Definir espaços publicitários.
- Identificar anúncios.
- Aplicar as preferências de publicidade.
- Respeitar as escolhas de consentimento aplicáveis.
- Controlar a exibição de anúncios conforme o plano.
- Registrar métricas necessárias.
- Tratar anúncios reportados.
- Evitar publicidade em contextos inadequados.

A remoção de anúncios do plano Pro deverá ser garantida pelo estado de assinatura validado no backend.

### 3.9. Subscription & Billing

Gerenciamento de assinaturas e pagamentos.

Funcionalidades:

- Consultar planos.
- Iniciar assinatura.
- Validar compras.
- Gerenciar renovação.
- Processar eventos de pagamento.
- Tratar cancelamentos.
- Recuperar compras elegíveis.
- Consultar situação da assinatura.
- Tratar falhas de cobrança.
- Registrar histórico financeiro necessário.

O módulo deverá possuir uma camada de abstração para os provedores de pagamento.

Eventos externos deverão ser autenticados, processados de maneira idempotente e reconciliados com o estado persistido no servidor.

### 3.10. Notifications

Central de notificações.

Tipos previstos:

- Novos seguidores.
- Curtidas e reações.
- Comentários.
- Respostas.
- Novas receitas de perfis seguidos.
- Atualizações da assinatura.
- Avisos de segurança.
- Comunicados da plataforma.

As preferências de comunicação deverão ser respeitadas.

### 3.11. Administration & Moderation

Painel administrativo para operação da plataforma.

Funcionalidades:

- Gestão de usuários.
- Gestão de categorias.
- Gestão de receitas.
- Análise de denúncias.
- Moderação de publicações.
- Gestão de comentários.
- Suspensão e bloqueio de contas.
- Consulta de assinaturas.
- Monitoramento de anúncios.
- Gestão de conteúdo editorial.
- Gestão de solicitações de privacidade.
- Gestão de reclamações de direitos autorais.
- Auditoria de ações administrativas.
- Relatórios operacionais.

O acesso administrativo deverá seguir o princípio do menor privilégio.

## 4. Mapa de páginas

A interface deverá ser organizada por áreas funcionais.

### Acesso e cadastro

- Boas-vindas.
- Entrar com Google.
- Cadastro com Google.
- Seleção de interesses.
- Preferências iniciais.
- Permissões opcionais.
- Confirmação de conta.
- Tratamento de erros de autenticação.
- Termos e privacidade.

### Início e descoberta

- Página inicial.
- Receitas em destaque.
- Receitas populares.
- Novidades.
- Recomendações.
- Categorias.
- Coleções temáticas.
- Pesquisa.
- Filtros.
- Resultados.
- Histórico de pesquisa.

### Receitas

- Detalhes.
- Ingredientes.
- Modo de preparo.
- Modo de cozinha.
- Ajuste de rendimento.
- Avaliações.
- Perguntas.
- Fotografias de preparações.
- Receitas semelhantes.
- Compartilhamento.
- Impressão.
- Informações nutricionais, quando disponíveis.
- Alergênicos.
- Denúncia.

### Livro pessoal

- Meu livro.
- Minhas receitas.
- Receitas privadas.
- Receitas publicadas.
- Rascunhos.
- Favoritos.
- Coleções.
- Notas pessoais.
- Histórico.
- Receitas arquivadas.
- Exportação.
- Importação.

### Cadastro de receitas

- Informações básicas.
- Fotografia.
- Ingredientes.
- Unidades.
- Modo de preparo.
- Tempos.
- Rendimento.
- Dificuldade.
- Categoria.
- Etiquetas.
- Observações.
- Revisão.
- Privacidade.
- Publicação.

### Comunidade

- Feed.
- Descobrir cozinheiros.
- Perfil público.
- Receitas do perfil.
- Seguidores.
- Seguindo.
- Comentários.
- Respostas.
- Reações.
- Publicação de resultados.
- Coleções públicas.
- Bloqueios.
- Denúncias.
- Configurações sociais.

### Assistente culinário

- Tela inicial do chatbot.
- Nova conversa.
- Conversa individual.
- Histórico.
- Busca por ingredientes.
- Substituições.
- Ajuste de quantidades.
- Planejamento de refeições.
- Lista de compras.
- Salvar sugestão.
- Limites de utilização.
- Avisos e limitações.

### Assinatura e publicidade

- Conhecer o Pro.
- Comparar planos.
- Confirmar assinatura.
- Gerenciar assinatura.
- Restaurar compras.
- Histórico de pagamentos.
- Problemas de pagamento.
- Preferências de publicidade.
- Informações sobre anúncios.
- Reportar anúncio.

### Perfil e configurações

- Meu perfil.
- Editar perfil.
- Preferências culinárias.
- Preferências alimentares.
- Configurações da conta.
- Segurança.
- Sessões.
- Conta Google.
- Notificações.
- Privacidade.
- Idioma.
- Unidades de medida.
- Acessibilidade.
- Exportação de dados.
- Exclusão de conta.

### Ajuda e páginas jurídicas

- Central de ajuda.
- Perguntas frequentes.
- Suporte.
- Política de Privacidade.
- Termos de Uso.
- Política de Cookies e Tecnologias.
- Política de Publicidade.
- Política de Assinaturas.
- Política de Conteúdo.
- Direitos Autorais.
- Diretrizes da Comunidade.
- Política de Moderação.
- Direitos dos Titulares.
- Canal de Privacidade.
- Canal de Denúncias.
- Informações do fornecedor.
- Avisos de segurança alimentar.
- Informações da aplicação.

### Painel administrativo

- Dashboard.
- Usuários.
- Perfis.
- Receitas.
- Categorias.
- Ingredientes.
- Denúncias.
- Moderação.
- Comentários.
- Suspensões.
- Assinaturas.
- Pagamentos.
- Publicidade.
- Conteúdo editorial.
- Assistente de IA.
- Segurança.
- Solicitações de privacidade.
- Direitos autorais.
- Atendimento.
- Relatórios.
- Auditoria.
- Administradores.
- Configurações.

O inventário completo deverá ser mantido na documentação do projeto e atualizado conforme as páginas forem implementadas.

## 5. Arquitetura tecnológica proposta

A arquitetura inicial deverá priorizar produtividade, manutenção, segurança e evolução gradual.

### Frontend web

- Next.js.
- React.
- TypeScript.
- Tailwind CSS.
- Componentes acessíveis.
- Design responsivo.
- PWA, conforme os requisitos definidos.

### Aplicativo móvel

- React Native.
- Expo.
- TypeScript.

A aplicação móvel poderá compartilhar contratos, validações e componentes compatíveis com o frontend web.

### Backend

- NestJS.
- TypeScript.
- API REST.
- OpenAPI.
- Validação de entrada.
- Autorização centralizada.
- Arquitetura modular.

### Persistência e infraestrutura

- PostgreSQL como banco de dados principal.
- Armazenamento de objetos para fotografias e arquivos.
- Redis para cache e mecanismos que se beneficiem de baixa latência.
- Filas para processamento assíncrono, quando necessário.
- Docker para ambientes reproduzíveis.
- OpenTelemetry para observabilidade.

### Princípios arquiteturais

- Clean Architecture.
- DDD aplicado conforme a complexidade dos domínios.
- SOLID.
- Separação entre domínio e infraestrutura.
- APIs desacopladas.
- Result Pattern quando apropriado.
- Migrações versionadas.
- Testes automatizados.
- Configuração externa por ambiente.
- Observabilidade desde o início.

Não é necessário iniciar com microsserviços independentes. Um backend modular bem delimitado permite desenvolver o MVP com menor complexidade operacional e extrair serviços posteriormente, quando houver justificativa concreta.

## 6. Estrutura de diretórios proposta

```text
sabore/
├── apps/
│   ├── web/
│   │   ├── app/
│   │   │   ├── (public)/
│   │   │   ├── (auth)/
│   │   │   ├── (home)/
│   │   │   ├── recipes/
│   │   │   ├── categories/
│   │   │   ├── cookbook/
│   │   │   ├── community/
│   │   │   ├── assistant/
│   │   │   ├── subscription/
│   │   │   ├── settings/
│   │   │   ├── legal/
│   │   │   └── admin/
│   │   ├── components/
│   │   └── public/
│   │
│   ├── mobile/
│   │   └── src/
│   │       ├── app/
│   │       ├── features/
│   │       ├── components/
│   │       └── services/
│   │
│   └── api/
│       └── src/
│           ├── modules/
│           │   ├── identity/
│           │   ├── users/
│           │   ├── recipes/
│           │   ├── cookbook/
│           │   ├── discovery/
│           │   ├── community/
│           │   ├── moderation/
│           │   ├── assistant/
│           │   ├── advertising/
│           │   ├── subscriptions/
│           │   ├── notifications/
│           │   ├── privacy/
│           │   └── administration/
│           ├── shared/
│           └── main.ts
│
├── packages/
│   ├── contracts/
│   ├── validation/
│   ├── design-system/
│   └── shared-types/
│
├── infrastructure/
│   ├── docker/
│   ├── deployment/
│   └── monitoring/
│
├── documentation/
│   ├── architecture/
│   ├── api/
│   ├── database/
│   ├── security/
│   ├── privacy/
│   ├── product/
│   └── adr/
│
├── tests/
│   ├── integration/
│   ├── e2e/
│   └── security/
│
├── .env.example
├── .gitignore
├── README.md
└── LICENSE
```

Esta estrutura é uma referência arquitetural. Os diretórios e arquivos definitivos deverão acompanhar a implementação real do monorepo.

## 7. Modelo de dados

As entidades principais previstas são:

| Entidade | Responsabilidade |
|---|---|
| User | Identidade interna |
| ExternalIdentity | Vínculo com a identidade Google |
| UserProfile | Informações do perfil |
| Recipe | Dados principais da receita |
| RecipeVersion | Histórico de versões |
| Ingredient | Ingredientes normalizados |
| RecipeIngredient | Quantidades e unidades |
| RecipeStep | Etapas do preparo |
| RecipeCategory | Categorias |
| RecipeTag | Etiquetas |
| RecipeMedia | Arquivos associados |
| RecipePermission | Permissões de acesso |
| Cookbook | Livro ou coleção |
| CookbookRecipe | Associação entre livros e receitas |
| RecipeRating | Avaliações |
| RecipeComment | Comentários |
| RecipeFollow | Relações de seguimento |
| CommunityPost | Publicações comunitárias |
| UserBlock | Bloqueios |
| ContentReport | Denúncias |
| ModerationAction | Ações de moderação |
| UserNotification | Notificações |
| Conversation | Conversas com o assistente |
| ConversationMessage | Mensagens |
| Subscription | Estado da assinatura |
| PaymentEvent | Eventos de pagamento |
| AdvertisingPreference | Preferências de publicidade |
| PrivacyRequest | Solicitações de titulares |
| AuditEvent | Eventos de auditoria |

O modelo definitivo deverá ser normalizado conforme as necessidades do domínio, com chaves, restrições, índices e políticas de retenção apropriados.

### Regras fundamentais

1. Cada receita possui um proprietário.
2. Uma receita privada só pode ser acessada por usuários autorizados.
3. A publicação é explícita.
4. Favoritar uma receita não transfere sua autoria.
5. O conteúdo de terceiros deve respeitar as permissões originais.
6. Eventos financeiros precisam de rastreabilidade.
7. As alterações relevantes de permissões devem ser auditáveis.
8. Dados pessoais e dados públicos devem ter tratamento claramente definido.

## 8. API e contratos

A API será responsável pelas regras de negócio e pela autorização de acesso.

O prefixo inicial proposto é:

`/api/v1`

### Identidade

```text
GET    /api/v1/auth/google
GET    /api/v1/auth/google/callback
POST   /api/v1/auth/logout
GET    /api/v1/users/me
PATCH  /api/v1/users/me
DELETE /api/v1/users/me
```

As rotas de autenticação deverão seguir o fluxo efetivamente adotado pelo provedor e pelo cliente.

### Receitas

```text
GET    /api/v1/recipes
POST   /api/v1/recipes
GET    /api/v1/recipes/:id
PATCH  /api/v1/recipes/:id
DELETE /api/v1/recipes/:id

POST   /api/v1/recipes/:id/publish
POST   /api/v1/recipes/:id/archive
POST   /api/v1/recipes/:id/favorite
DELETE /api/v1/recipes/:id/favorite
```

A API deverá validar propriedade e permissões em todas as operações.

### Livro pessoal

```text
GET    /api/v1/cookbook
GET    /api/v1/cookbook/recipes
POST   /api/v1/cookbook/collections
GET    /api/v1/cookbook/collections
PATCH  /api/v1/cookbook/collections/:id
DELETE /api/v1/cookbook/collections/:id
POST   /api/v1/cookbook/export
```

### Comunidade

```text
GET    /api/v1/community/feed
GET    /api/v1/community/users
GET    /api/v1/community/users/:id
POST   /api/v1/community/users/:id/follow
DELETE /api/v1/community/users/:id/follow

POST   /api/v1/community/posts
GET    /api/v1/community/posts/:id
POST   /api/v1/community/posts/:id/comments
POST   /api/v1/community/reports
POST   /api/v1/community/users/:id/block
```

### Assistente culinário

```text
POST   /api/v1/assistant/conversations
GET    /api/v1/assistant/conversations
GET    /api/v1/assistant/conversations/:id
POST   /api/v1/assistant/conversations/:id/messages
DELETE /api/v1/assistant/conversations/:id
```

### Assinaturas

```text
GET    /api/v1/subscriptions/plans
GET    /api/v1/subscriptions/me
POST   /api/v1/subscriptions/checkout
POST   /api/v1/subscriptions/restore
POST   /api/v1/webhooks/payments/:provider
```

O contrato definitivo dependerá do provedor de pagamentos e do canal de distribuição.

Os endpoints acima são propostas de API, não uma declaração de rotas já implementadas. O contrato OpenAPI deverá ser mantido em conjunto com o código.

## 9. Segurança

A segurança deverá ser incorporada à arquitetura desde o início.

### Autenticação e autorização

- Validar credenciais externas no servidor.
- Utilizar sessões ou tokens com controles adequados.
- Implementar autorização por recurso.
- Aplicar o princípio do menor privilégio.
- Proteger operações administrativas.
- Limitar tentativas e requisições abusivas.
- Implementar revogação de sessões.
- Validar propriedade de receitas e coleções.

### Proteção de dados

- HTTPS em produção.
- Gestão segura de segredos.
- Validação de entradas.
- Proteção contra injeções.
- Controle de acesso a arquivos.
- URLs assinadas para mídias privadas.
- Limitação de requisições.
- Registros estruturados sem exposição indevida de dados pessoais.
- Políticas de retenção e exclusão.
- Backups e procedimentos de recuperação.

### Pagamentos

- Validar eventos recebidos dos provedores.
- Verificar assinaturas dos webhooks.
- Implementar idempotência.
- Reconciliar o estado da assinatura.
- Não armazenar dados de cartão sem necessidade e controles adequados.
- Não conceder benefícios premium com base apenas no estado apresentado pelo cliente.

### Inteligência artificial

- Respeitar as permissões de acesso aos conteúdos.
- Limitar o contexto enviado ao modelo.
- Evitar exposição de dados pessoais.
- Registrar erros sem armazenar indiscriminadamente o conteúdo das conversas.
- Aplicar limites de uso e orçamento.
- Tratar entradas externas como conteúdo não confiável.
- Validar ações executadas por ferramentas.

### Testes de segurança

O projeto deverá incluir testes para:

- Acesso indevido a receitas privadas.
- Escalada de privilégios.
- Manipulação de identificadores.
- Exposição de mídias privadas.
- Repetição de webhooks.
- Bypass de assinatura.
- Abuso de endpoints.
- Acesso não autorizado a dados de terceiros.

## 10. Privacidade e conformidade jurídica

A plataforma deverá ser projetada para atender à legislação brasileira aplicável, incluindo a Lei Geral de Proteção de Dados Pessoais, o Código de Defesa do Consumidor, as regras de comércio eletrônico e a legislação de direitos autorais.

Os requisitos deverão ser confirmados por revisão jurídica antes do lançamento comercial.

### Documentos e páginas necessários

- Política de Privacidade.
- Termos de Uso.
- Política de Cookies e Tecnologias.
- Política de Publicidade.
- Política de Assinaturas.
- Condições do Saborê Pro.
- Política de Conteúdo.
- Política de Moderação.
- Diretrizes da Comunidade.
- Direitos Autorais.
- Política de Retenção e Exclusão.
- Canal de Privacidade.
- Canal de Direitos Autorais.
- Canal de Denúncias.
- Central de Ajuda.
- Informações do controlador.
- Instruções para exclusão da conta.

### Requisitos operacionais

- Mapear os dados pessoais tratados.
- Identificar finalidades e bases legais.
- Documentar fornecedores e compartilhamentos.
- Definir prazos de retenção.
- Implementar solicitações dos titulares.
- Implementar exclusão de conta.
- Controlar permissões de acesso.
- Avaliar transferências internacionais.
- Gerenciar incidentes de segurança.
- Documentar decisões relevantes de privacidade.

Consentimento não deve ser utilizado indiscriminadamente como base legal para todo tratamento. Cada finalidade deverá ser analisada conforme a legislação aplicável.

A política publicada deverá refletir o comportamento real do sistema e os fornecedores efetivamente utilizados.

## 11. Publicidade e assinatura

A publicidade deverá funcionar de maneira independente do conteúdo editorial e dos recursos premium.

### Regras propostas

- Anúncios identificados.
- Espaços publicitários definidos.
- Controle de exibição conforme o plano.
- Respeito às preferências aplicáveis.
- Proteção contra anúncios maliciosos.
- Possibilidade de denúncia.
- Tratamento de erros de carregamento.
- Métricas operacionais adequadas.

A assinatura Pro deverá remover a publicidade abrangida pelo benefício contratado.

O sistema deverá diferenciar:

- Assinatura ativa.
- Assinatura em período de carência, quando aplicável.
- Cancelamento agendado.
- Assinatura expirada.
- Pagamento pendente.
- Pagamento recusado.
- Reembolso.
- Revogação de compra.

As regras para concessão ou manutenção dos benefícios deverão ser consistentes com o provedor de cobrança e os termos comerciais.

## 12. Interface e design system

A identidade visual deverá transmitir proximidade, qualidade gastronómica e facilidade de uso.

### Diretrizes visuais

- Fundo predominantemente branco.
- Verde gastronómico como cor principal de apoio.
- Tons quentes para elementos de destaque.
- Fotografias gastronómicas de qualidade.
- Tipografia legível.
- Navegação simples.
- Componentes reutilizáveis.
- Layout responsivo.
- Acessibilidade.
- Ausência de tema escuro como padrão.

### Componentes compartilhados

- RecipeCard.
- RecipeGrid.
- RecipeDetail.
- IngredientList.
- RecipeSteps.
- CategoryCard.
- UserProfileCard.
- CommunityPost.
- CommentThread.
- SearchBar.
- FilterPanel.
- CookbookCollection.
- ChatConversation.
- SubscriptionCard.
- AdvertisementSlot.
- PrivacyNotice.
- ConfirmationDialog.
- EmptyState.
- LoadingState.
- ErrorState.

Os componentes deverão possuir estados de carregamento, erro, vazio e sucesso, além de comportamentos acessíveis para teclado e leitores de tela.

## 13. Observabilidade e operação

A plataforma deverá possuir mecanismos para diagnosticar falhas e acompanhar o desempenho.

### Métricas

- Latência das APIs.
- Taxa de erros.
- Disponibilidade.
- Tempo de processamento de imagens.
- Falhas de autenticação.
- Falhas de pagamento.
- Tempo de resposta do chatbot.
- Consumo de recursos.
- Uso de armazenamento.
- Erros de processamento assíncrono.

### Instrumentação

- Logs estruturados.
- Métricas operacionais.
- Traces distribuídos quando necessários.
- Identificadores de correlação.
- Alertas.
- Dashboards.
- Monitoramento de tarefas assíncronas.

OpenTelemetry poderá ser utilizado para instrumentação, com integração a uma infraestrutura de observabilidade compatível.

Os logs não deverão registrar indiscriminadamente receitas privadas, tokens, credenciais ou conteúdo sensível.

## 14. Testes e qualidade

A qualidade será validada por testes em diferentes níveis.

### Testes unitários

Validar regras de domínio, cálculos, permissões e transições de estado.

### Testes de integração

Validar persistência, APIs, autenticação, armazenamento e integrações externas.

### Testes end-to-end

Validar fluxos completos, incluindo:

1. Cadastro com Google.
2. Criação de receita privada.
3. Recuperação da receita após novo acesso.
4. Tentativa de acesso não autorizado.
5. Publicação voluntária.
6. Salvamento de receitas de terceiros.
7. Seguimento de cozinheiros.
8. Interação com o chatbot.
9. Assinatura Pro.
10. Remoção de anúncios para assinantes.
11. Cancelamento de assinatura.
12. Solicitação de exclusão de conta.

### Qualidade contínua

- Verificação de tipos.
- Lint.
- Formatação.
- Testes automatizados.
- Análise de dependências.
- Verificação de segredos.
- Revisão de migrações.
- Testes de acessibilidade.
- Verificação de contratos da API.

Nenhuma funcionalidade crítica deverá ser considerada pronta apenas porque a interface visual foi concluída.

## 15. Estratégia de infraestrutura

A infraestrutura será definida de acordo com os requisitos de disponibilidade, custo e escala.

### Ambientes

- Desenvolvimento.
- Testes.
- Homologação.
- Produção.

Cada ambiente deverá possuir configuração e credenciais próprias.

### Banco de dados

O PostgreSQL será o banco principal.

Requisitos:

- Migrações versionadas.
- Índices conforme padrões de consulta.
- Restrições de integridade.
- Backups.
- Monitoramento.
- Controle de conexões.
- Procedimentos de recuperação.

### Arquivos

As fotografias e mídias deverão ser armazenadas em serviço apropriado de objetos, evitando armazenar arquivos binários diretamente nas tabelas de negócio.

O processamento de imagens deverá considerar formatos, dimensões, miniaturas, validação do arquivo e limites de upload.

### Processamento assíncrono

Filas poderão ser utilizadas para:

- Processamento de imagens.
- Notificações.
- Exportação de livros.
- Eventos de assinatura.
- Tarefas de moderação.
- Processamento de métricas.

O processamento deverá ser idempotente quando houver possibilidade de repetição de eventos.

### Implantação

A implantação deverá utilizar pipelines automatizados, verificações de qualidade, gestão segura de segredos e procedimentos de rollback.

A infraestrutura definitiva será escolhida após validar os requisitos e o custo operacional.

## 16. Roadmap

### Fase 1 — Fundação

- Estrutura do repositório.
- Design system.
- Autenticação Google.
- Perfis.
- Banco de dados.
- API inicial.
- Documentação.
- Configuração de ambientes.

### Fase 2 — Biblioteca e livro pessoal

- Categorias.
- Pesquisa.
- Detalhes de receitas.
- Cadastro e edição.
- Privacidade.
- Favoritos.
- Coleções.
- Publicação voluntária.

### Fase 3 — Comunidade

- Feed.
- Seguidores.
- Perfis públicos.
- Comentários.
- Avaliações.
- Denúncias.
- Moderação.
- Notificações.

### Fase 4 — Monetização

- Integração de publicidade.
- Plano Pro.
- Pagamentos.
- Validação de assinatura.
- Cancelamento.
- Remoção de anúncios.
- Gestão administrativa.

### Fase 5 — Assistente culinário

- Conversas.
- Histórico.
- Sugestões por ingredientes.
- Ajuste de quantidades.
- Lista de compras.
- Planejamento de refeições.
- Controles de custo e segurança.

### Fase 6 — Preparação para produção

- Auditoria de segurança.
- Testes de autorização.
- Testes de carga conforme a necessidade.
- Observabilidade.
- Backups.
- Procedimentos de recuperação.
- Revisão jurídica.
- Documentação operacional.
- Preparação para distribuição móvel.

As fases serão concluídas com base em critérios verificáveis, e não apenas pela presença de telas ou componentes visuais.

## 17. Indicadores de produto

### Aquisição

- Novos usuários.
- Origem das visitas.
- Conversão de páginas públicas.
- Cadastros concluídos.

### Ativação

- Primeira receita cadastrada.
- Primeira receita salva.
- Primeira publicação.
- Primeiro seguimento.

### Retenção

- Usuários ativos diários e mensais.
- Retorno após o cadastro.
- Frequência de utilização do livro pessoal.
- Frequência de consulta a receitas.

### Comunidade

- Receitas publicadas.
- Seguimentos.
- Comentários.
- Compartilhamentos.
- Receitas salvas.
- Denúncias e tempo de moderação.

### Monetização

- Conversão para Pro.
- Assinaturas ativas.
- Cancelamentos.
- Receita recorrente.
- Receita publicitária.
- Custo do assistente por usuário.

As metas deverão ser definidas após a coleta de dados reais de utilização.

## 18. Configuração e variáveis de ambiente

O projeto deverá fornecer um arquivo `.env.example` com as variáveis necessárias para cada serviço.

Exemplo conceitual:

```dotenv
NODE_ENV=development

API_PORT=3001
WEB_URL=http://localhost:3000
API_URL=http://localhost:3001

DATABASE_URL=

GOOGLE_CLIENT_ID=
GOOGLE_CLIENT_SECRET=
GOOGLE_REDIRECT_URI=

SESSION_SECRET=

OBJECT_STORAGE_ENDPOINT=
OBJECT_STORAGE_BUCKET=
OBJECT_STORAGE_ACCESS_KEY=
OBJECT_STORAGE_SECRET_KEY=

REDIS_URL=

PAYMENT_PROVIDER=
PAYMENT_WEBHOOK_SECRET=

AD_PROVIDER=
AD_PROVIDER_CLIENT_ID=

AI_PROVIDER=
AI_API_KEY=
AI_MODEL=

OTEL_SERVICE_NAME=sabore-api
OTEL_EXPORTER_OTLP_ENDPOINT=
```

Os nomes definitivos deverão acompanhar a implementação dos provedores escolhidos.

**Segurança:** o arquivo `.env` real não deverá ser versionado. Credenciais, tokens e segredos devem ser armazenados em mecanismos apropriados de gestão de segredos.

## 19. Documentação do projeto

A documentação técnica deverá evoluir junto com o código.

Estrutura recomendada:

```text
documentation/
├── architecture/
│   ├── overview.md
│   ├── system-context.md
│   ├── module-boundaries.md
│   └── deployment.md
├── api/
│   ├── conventions.md
│   └── authentication.md
├── database/
│   ├── data-model.md
│   └── migrations.md
├── security/
│   ├── threat-model.md
│   ├── access-control.md
│   └── incident-response.md
├── privacy/
│   ├── data-inventory.md
│   ├── retention.md
│   └── data-subject-requests.md
├── product/
│   ├── requirements.md
│   ├── page-inventory.md
│   ├── monetization.md
│   └── roadmap.md
└── adr/
    ├── 0001-modular-monolith.md
    └── 0002-postgresql.md
```

Decisões arquiteturais relevantes deverão ser registradas em ADRs (*Architecture Decision Records*), incluindo contexto, alternativas, justificativa e consequências.

## 20. Instalação e desenvolvimento

Os comandos de instalação serão documentados após a estrutura real do repositório ser definida.

O fluxo de desenvolvimento deverá contemplar:

1. Instalação das dependências.
2. Configuração das variáveis de ambiente.
3. Inicialização dos serviços necessários.
4. Aplicação das migrações.
5. Execução do backend.
6. Execução do frontend.
7. Execução dos testes.
8. Verificação de qualidade.
9. Preparação do build.
10. Implantação em ambiente apropriado.

Os comandos definitivos deverão ser mantidos atualizados no README e nos scripts do projeto.

## 21. Princípios de desenvolvimento

O desenvolvimento do Saborê deverá seguir estas diretrizes:

- Não considerar uma interface visual como funcionalidade concluída.
- Não expor receitas privadas por APIs públicas.
- Não conceder privilégios premium apenas com validações no cliente.
- Não confiar em dados externos sem validação.
- Não armazenar segredos no repositório.
- Não publicar conteúdo de terceiros sem autorização adequada.
- Não coletar dados sem finalidade definida.
- Não registrar informações pessoais desnecessárias nos logs.
- Não introduzir microsserviços sem justificativa operacional.
- Não adicionar dependências sem avaliar manutenção e segurança.
- Não considerar documentos jurídicos substitutos de controles técnicos.
- Não declarar conformidade ou certificações sem evidências.
- Manter testes para regras de negócio e autorização.
- Documentar decisões que afetem a arquitetura.
- Priorizar acessibilidade, desempenho e manutenção.

## 22. Licenciamento

A licença do projeto deverá ser definida antes de sua distribuição.

A escolha dependerá de o Saborê ser:

- Um produto comercial proprietário.
- Um projeto de código aberto.
- Um núcleo aberto com componentes comerciais.
- Uma plataforma com licenças distintas para código, conteúdo e marca.

A licença deverá refletir a estratégia comercial e os direitos efetivamente disponíveis. Não se deve incluir um arquivo de licença genérico sem decidir essas condições.

## 23. Estado de implementação

O desenvolvimento deverá acompanhar o estado de cada módulo em um inventário verificável.

| Estado | Significado |
|---|---|
| `PLANNED` | Planejado |
| `IN_PROGRESS` | Em desenvolvimento |
| `IMPLEMENTED` | Implementado |
| `TESTED` | Validado por testes |
| `PRODUCTION_READY` | Preparado para produção após as verificações aplicáveis |

A classificação deverá ser baseada em evidências no repositório, nos testes e na configuração operacional.

A presença de requisitos neste documento não significa que os recursos já estejam implementados.

## 24. Próximos marcos

Os próximos marcos recomendados são:

1. Criar a estrutura inicial do monorepo.
2. Formalizar os requisitos funcionais e não funcionais.
3. Definir os contratos iniciais da API.
4. Modelar o banco de dados.
5. Implementar autenticação e autorização.
6. Implementar receitas privadas e públicas.
7. Criar o livro pessoal.
8. Implementar o fluxo de publicação.
9. Desenvolver a comunidade.
10. Integrar assinatura e publicidade.
11. Implementar o assistente culinário.
12. Concluir testes, documentação e preparação para produção.

Cada marco deverá possuir critérios de aceitação e testes correspondentes.

---

<div align="center">

### Saborê

**Um lugar para descobrir receitas, preservar tradições e compartilhar o prazer de cozinhar.**

Receitas que conectam pessoas. 🍲

<br/>
<small>© 2026 DEEVO Soluções Financeiras LTDA — CNPJ: 63.187.175/0001-70. Todos os direitos reservados.</small>

</div>
