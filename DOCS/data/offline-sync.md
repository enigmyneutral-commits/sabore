# Persistência offline e sincronização

## Objetivo
Permitir consulta de conteúdo previamente guardado e edição de rascunhos sem rede, sem tratar a base local como fonte de verdade para permissões globais.

## Regras
- Room guarda receitas em cache, rascunhos, favoritos pendentes e fila de operações quando necessário.
- DataStore guarda preferências simples; tokens de autenticação não devem ser guardados em texto simples.
- Cada alteração local recebe identificador único e timestamp/versionamento.
- WorkManager envia operações quando a rede estiver disponível.
- Operações remotas devem ser idempotentes ou usar idempotency keys.
- Respostas de conflito devem ter política definida: por exemplo, detetar versões divergentes e pedir resolução em vez de sobrescrever silenciosamente.
- Uma eliminação local pendente não deve ser interpretada como autorização para apagar recursos remotos sem validação do servidor.
- Logout deve limpar dados pessoais em cache conforme a política de privacidade, mantendo apenas o que for explicitamente permitido.

## Estados sugeridos
PENDING → SYNCING → SYNCED; falhas transitórias regressam a PENDING com backoff; falhas permanentes ficam visíveis para correção.

## Limites
Não prometer que a comunidade, pagamentos, pesquisa global ou assistente IA funcionam offline. Indicar claramente quais recursos exigem rede.
