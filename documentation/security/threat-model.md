# Modelo de ameaças inicial

## Ativos
Identidade/sessões, receitas privadas, notas, relações sociais, conversas com assistente, assinatura, mídias e operações administrativas.

## Ameaças
- IDOR/BOLA: leitura ou edição de receita alheia.
- Elevação de privilégios.
- Roubo ou replay de sessão.
- Webhook financeiro falso ou repetido.
- Upload malicioso.
- Spam, assédio e manipulação de avaliações.
- Exposição em logs, analytics ou contexto de IA.
- Prompt injection por conteúdo público.
- Abuso de endpoints e custos de IA.
- Rastreamento publicitário fora das preferências permitidas.

## Controles necessários
Autorização por recurso; sessões seguras e revogáveis; RBAC e auditoria; webhooks assinados e idempotentes; validação MIME/tamanho de uploads; quotas; gestor de segredos; redação de logs; testes de autorização negativos; backups e resposta a incidentes.

## Critérios de validação
- Usuário A não lê nem altera receita privada de B.
- Cliente não pode conceder a si próprio o Pro.
- Webhook repetido não duplica efeitos.
- Conteúdo privado não entra no feed público ou resposta de outro usuário.
- Suporte só acessa dados necessários conforme permissão.
