# Arquitetura — Saborê

## Estado atual

O repositório contém uma aplicação web Next.js usando App Router, React, TypeScript, Tailwind CSS e dados de receitas definidos em código para demonstração. O formulário de cadastro persiste localmente no navegador; não existe ainda uma API de negócio persistente.

## Arquitetura alvo

A evolução proposta utiliza um backend modular com API REST, PostgreSQL como fonte de verdade, armazenamento de objetos para mídia e serviços externos encapsulados em adapters. Redis e filas entram quando houver necessidade real de cache, rate limiting distribuído ou processamento assíncrono.

## Contexto lógico

- Web Next.js: páginas públicas, navegação e experiência do usuário.
- API: validação, autenticação, autorização e regras de negócio.
- PostgreSQL: usuários, receitas, versões, livros, comunidade e assinaturas.
- Object storage: imagens e arquivos.
- Provedores externos: OAuth Google, publicidade, pagamentos e modelos de IA.
- Operação: logs estruturados, métricas, traces, alertas, backups e CI.

## Módulos de domínio

1. Identity & Access.
2. User Profiles.
3. Recipe Management.
4. Recipe Privacy.
5. Recipe Discovery.
6. Personal Cookbook.
7. Community & Social Graph.
8. Moderation.
9. AI Cooking Assistant.
10. Advertising.
11. Subscription & Billing.
12. Notifications.
13. Privacy Requests.
14. Administration & Audit.

## Princípios

- Começar por monólito modular, não microsserviços prematuros.
- Separar domínio, aplicação, adaptadores e infraestrutura.
- Aplicar autorização no servidor em cada operação.
- Versionar contratos e migrações.
- Validar dados nas fronteiras.
- Usar idempotência em integrações com eventos repetíveis.
- Não incluir receitas privadas em índices públicos ou contexto de IA sem autorização.
- Documentar decisões arquiteturais em ADRs.

## Fluxo de publicação

1. O usuário cria receita em estado privado.
2. A API valida propriedade e dados.
3. O usuário escolhe publicar explicitamente.
4. A API valida elegibilidade e política de conteúdo.
5. O estado público é persistido e o índice público atualizado.
6. Ao tornar privada ou remover, feeds e índices devem refletir a alteração conforme a política de cache.

## Observação

Esta arquitetura é uma proposta de evolução. A presença deste documento não significa que backend, banco ou integrações estejam implementados.
