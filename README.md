# Saborê 🍲
### Receitas que conectam pessoas

Saborê é uma aplicação web responsiva construída com Next.js, React e TypeScript para descoberta de receitas, livro culinário pessoal, comunidade gastronómica e assistência culinária.

> **Status:** MVP visual em desenvolvimento. Algumas telas são navegáveis, mas os dados de receitas são demonstrativos. Autenticação Google, persistência no servidor, publicação social, pagamentos, anúncios reais e provedor de IA ainda não estão integrados. Não utilizar em produção nem inserir dados pessoais reais neste protótipo.

## Executar localmente

Requisitos: Node.js 20.9+ e npm.

```bash
git clone https://github.com/enigmyneutral-commits/sabore.git
cd sabore
npm install
cp .env.example .env
npm run dev
```

Abra [http://localhost:3000](http://localhost:3000).

```bash
npm run typecheck
npm run lint
npm run build
```

## Stack atual

- Next.js 15 App Router
- React 19
- TypeScript em modo estrito
- Tailwind CSS 4
- Lucide React
- Imagens externas de demonstração via URL

## Funcionalidades representadas no MVP

- Página inicial com receitas em destaque.
- Pesquisa de receitas e filtros por categoria.
- Página detalhada da receita, ingredientes e etapas.
- Categorias gastronómicas.
- Formulário de cadastro de receitas com armazenamento local demonstrativo.
- Livro pessoal com conteúdo de exemplo.
- Feed comunitário ilustrativo.
- Chatbot culinário com respostas locais predefinidas.
- Página do plano Saborê Pro (preço de referência: R$ 4,99/mês).
- Perfil, configurações, notificações, ajuda e páginas preliminares de privacidade e termos.
- Layout responsivo, metadados, manifest e páginas de erro.

## Rotas principais

| Rota | Propósito |
|---|---|
| `/` | Início |
| `/explorar` | Pesquisar e filtrar receitas de exemplo |
| `/categorias` | Navegar pelas categorias |
| `/receitas/[id]` | Detalhes de uma receita pública de demonstração |
| `/adicionar` | Formulário local de cadastro |
| `/meu-livro` | Livro pessoal demonstrativo |
| `/comunidade` | Feed comunitário fictício |
| `/assistente` | Chatbot demonstrativo |
| `/pro` | Apresentação do plano |
| `/perfil` | Perfil de demonstração |
| `/configuracoes` | Preferências locais |
| `/notificacoes` | Notificações ilustrativas |
| `/privacidade` | Minuta de privacidade |
| `/termos` | Minuta dos termos |
| `/ajuda` | Perguntas frequentes |
| `/excluir-conta` | Informações sobre exclusão |

## Documentação técnica

A documentação detalhada está em [documentation/](documentation/README.md). Inclui visão de arquitetura, inventário de telas, modelo de dados, API proposta, segurança, privacidade, monetização, operação e roadmap.

## Arquitetura planejada

A evolução deverá adotar backend modular, API REST versionada e PostgreSQL. Os módulos de domínio previstos incluem identidade, receitas, livro pessoal, comunidade, moderação, assistente, publicidade, assinaturas, notificações e privacidade. A UI não será a fronteira de segurança.

## Privacidade por padrão

Cadastrar uma receita não deve publicá-la. Em produção, todas as leituras e alterações deverão ter autorização validada no servidor, e conteúdos privados não poderão aparecer em buscas públicas, feeds, mídias acessíveis sem autorização, exportações de terceiros ou contexto de IA não autorizado.

O armazenamento local atual não oferece autenticação, sincronização, backup ou isolamento seguro entre contas.

## Login Google

O login/cadastro com Google é requisito de produto, mas o OAuth ainda não está integrado. A implementação exigirá configuração do provedor, callbacks, validação server-side, gestão de sessões e análise das regras das lojas de aplicativos.

## Saborê Pro e anúncios

O preço de referência é R$ 4,99/mês para uma experiência sem os anúncios abrangidos pela oferta. A tela atual é informativa e não processa pagamentos. Antes de comercializar, integrar o sistema de cobrança adequado ao canal, validar compras no servidor e tratar renovação, cancelamento, reembolso e webhooks idempotentes.

## Assistente culinário

O chatbot atual utiliza respostas locais de demonstração; não chama um modelo externo. A integração real deve incluir limites de uso/custo, timeout, observabilidade, minimização de dados e controles para não expor receitas privadas.

## Qualidade e segurança

A CI está configurada para executar typecheck, lint e build. Antes de produção, adicionar testes unitários, integração, end-to-end e autorização; revisão de dependências; gestão de segredos; backups; observabilidade; testes de carga conforme requisitos; e revisão jurídica das políticas.

## Aviso jurídico

As páginas legais existentes são minutas de produto, não documentos finais. O controlador, os fornecedores reais, as finalidades, retenções e contatos deverão ser identificados e revisados por profissional jurídico antes do lançamento.

## Licença

A licença do código ainda precisa ser definida. Marca, fotografias e conteúdo culinário podem estar sujeitos a direitos independentes.
