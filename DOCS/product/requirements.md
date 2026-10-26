# Requisitos do produto

## Objetivo
Entregar uma aplicação Android culinária intuitiva para guardar receitas, descobrir ideias e participar numa comunidade.

## Requisitos funcionais
- RF-01 — Autenticação: permitir iniciar sessão com Google, após configuração segura do fornecedor e do backend.
- RF-02 — Perfil: mostrar nome de apresentação, avatar e preferências; permitir editar os campos autorizados.
- RF-03 — Livro pessoal: criar, editar, consultar e eliminar receitas próprias.
- RF-04 — Privacidade: novas receitas são privadas por padrão.
- RF-05 — Publicação: publicar receita somente após confirmação explícita; permitir retirar publicação conforme as regras do serviço.
- RF-06 — Pesquisa: procurar por título, ingredientes, categorias e filtros disponíveis.
- RF-07 — Categorias: doces, salgados, forno, cozinha brasileira e internacional, entre outras configuráveis.
- RF-08 — Favoritos e coleções: guardar receitas e organizá-las.
- RF-09 — Comunidade: perfis públicos, seguir utilizadores, feed, comentários e reações, sujeitos às regras de moderação.
- RF-10 — Imagens: selecionar e enviar imagens de receitas com validação de tipo e tamanho.
- RF-11 — Assistente culinário: responder a perguntas de cozinha e ajudar com substituições, etapas e ideias, identificando limitações e alergénios com cuidado.
- RF-12 — Estado de rede: indicar carregamento, ausência de rede, erro, estado vazio e tentativa novamente.
- RF-13 — Offline: consultar e editar rascunhos e receitas guardadas localmente; sincronizar quando possível.
- RF-14 — Conta: disponibilizar saída de sessão e processo de eliminação de conta/dados de acordo com a política do serviço.
- RF-15 — Monetização: apresentar plano gratuito com publicidade e Saborê Pro sem anúncios da plataforma, conforme documentação comercial e políticas da loja.

## Requisitos não funcionais
- RNF-01 — Segurança: tráfego HTTPS, autorização no servidor, armazenamento seguro de tokens e ausência de segredos embutidos.
- RNF-02 — Privacidade: minimização de dados e consentimento/avisos claros quando aplicável.
- RNF-03 — Acessibilidade: suporte a leitor de ecrã, contraste adequado, alvos táteis confortáveis e escalonamento de texto.
- RNF-04 — Desempenho: listas paginadas, imagens otimizadas e operações pesadas fora da thread principal.
- RNF-05 — Resiliência: erros recuperáveis, timeouts e sincronização idempotente.
- RNF-06 — Qualidade: testes unitários, de integração e UI automatizados.
- RNF-07 — Compatibilidade: definir minSdk/targetSdk e dispositivos suportados antes do primeiro release.
- RNF-08 — Observabilidade: logs sem segredos nem conteúdo privado; recolha de diagnóstico transparente e configurável.

## Critérios de aceitação fundamentais
1. Uma receita recém-criada não aparece publicamente sem ação explícita de publicação.
2. Um utilizador não consegue ler, editar ou apagar receitas privadas de outra conta, mesmo manipulando pedidos.
3. Falhas de rede não apagam silenciosamente alterações locais.
4. O estado de uma assinatura é validado pelo serviço/backend, não por uma preferência local.
5. Fluxos críticos possuem testes automatizados e mensagens de erro compreensíveis.

## Fora do escopo do cliente Android
- O Android não deve conter credenciais administrativas, regras de autorização como única proteção, lógica de pagamento confiável ou chaves privadas de IA.
- O cliente não substitui o backend, a consola de administração ou a aplicação web.
