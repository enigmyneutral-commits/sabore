# API e integrações

## Princípio
O cliente Android comunica com um backend autenticado por HTTPS. O endereço base é configurável por ambiente; credenciais privadas e chaves administrativas nunca ficam no APK.

## Contratos sugeridos
- Autenticação: validar identidade Google no servidor e emitir/gerir sessão conforme arquitetura escolhida.
- Perfil: obter e atualizar os campos autorizados.
- Receitas: CRUD do proprietário; listagem pública com paginação; publicação/retirada de publicação.
- Imagens: pedido de upload autorizado, validação de conteúdo, armazenamento privado/público de acordo com política.
- Pesquisa: filtros e paginação.
- Livro/favoritos/coleções: operações por utilizador autenticado.
- Comunidade: feed, seguir, comentários, reações e denúncias.
- Assistente: endpoint protegido, limites de utilização, proteção contra abuso e controlo de custos.
- Assinaturas: estado verificado no backend com integração com Google Play Billing e notificações de compra validadas no servidor, conforme configuração final.

## Padrões de resposta
Definir envelopes ou modelos de erro consistentes, códigos HTTP adequados, requestId de diagnóstico e mensagens que não revelem informação sensível. Nunca expor stack traces ao utilizador.

## Resiliência
Timeouts, cancelamento de pedidos, retry apenas para falhas transitórias, backoff exponencial e idempotência em operações mutáveis. Não repetir automaticamente compras ou publicações sem garantias de idempotência.

## Ambiente
Separar desenvolvimento, staging e produção. Usar dados fictícios em desenvolvimento. Definir política de versionamento e compatibilidade da API.
