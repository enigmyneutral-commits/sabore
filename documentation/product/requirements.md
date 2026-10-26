# Requisitos do produto

## Requisitos funcionais

- RF-001: login/cadastro exclusivamente por Google na experiência definida.
- RF-002: criar receitas privadas por padrão.
- RF-003: editar, arquivar e excluir receitas próprias.
- RF-004: publicar receita apenas mediante ação explícita.
- RF-005: pesquisar receitas públicas por texto, categoria e filtros.
- RF-006: salvar receitas públicas em um livro pessoal sem transferir autoria.
- RF-007: seguir perfis, comentar, avaliar e denunciar conteúdo quando módulo social estiver ativo.
- RF-008: oferecer assistente culinário com contexto autorizado e limites de uso.
- RF-009: suportar plano gratuito com publicidade e Pro sem anúncios abrangidos.
- RF-010: validar status de assinatura no servidor.
- RF-011: permitir gestão de preferências, pedidos de dados e exclusão.
- RF-012: fornecer moderação e ferramentas administrativas com permissões restritas.

## Requisitos não funcionais

- RNF-001: autorização server-side e isolamento entre usuários.
- RNF-002: responsividade, acessibilidade e bom desempenho percebido.
- RNF-003: APIs versionadas e documentação de contratos.
- RNF-004: logs, métricas, alertas e correlação de requisições.
- RNF-005: backups e procedimentos de recuperação.
- RNF-006: testes automatizados de regras críticas.
- RNF-007: minimização de dados pessoais e retenção definida.
- RNF-008: tratamento seguro de uploads.
- RNF-009: idempotência em webhooks e tarefas repetíveis.
- RNF-010: gestão de segredos fora do código.

## Critério central de aceite de privacidade

Usuário A não pode consultar ou modificar receita privada do usuário B alterando ID, URL, parâmetros, requisições de API, cabeçalhos ou caminhos de arquivo.
