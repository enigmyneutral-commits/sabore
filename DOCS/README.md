# Documentação técnica — Saborê Android

Esta documentação especifica o aplicativo Android nativo do Saborê. Não é a documentação da aplicação Next.js.

## Produto
O Saborê ajuda pessoas a descobrir, organizar, preparar e partilhar receitas. Cada utilizador tem um livro de receitas pessoal; os conteúdos são privados por padrão e só se tornam públicos por uma ação clara de publicação.

## Índice
- [Requisitos funcionais e não funcionais](product/requirements.md)
- [Arquitetura Android](architecture/android.md)
- [Telas e navegação](product/screens-and-navigation.md)
- [Modelo de dados](data/data-model.md)
- [Offline e sincronização](data/offline-sync.md)
- [API e integrações](integrations/api-and-integrations.md)
- [Segurança e privacidade](security/security-privacy.md)
- [Monetização](product/monetization.md)
- [Estratégia de testes](quality/testing.md)
- [Build e publicação](release/build-and-release.md)
- [Roadmap](roadmap.md)

## Convenções
- Marcar funcionalidades como **Planeada**, **Em implementação**, **Implementada** ou **Verificada**; documentação, por si só, não comprova implementação.
- Nunca incluir chaves, tokens, credenciais ou dados reais de utilizadores em exemplos.
- Mudanças de esquema devem ter migrações e testes.
- Mudanças de permissões, privacidade, pagamentos ou autenticação exigem revisão de segurança.
- A API e o backend são contratos separados; o cliente Android nunca é a autoridade final de autorização.
