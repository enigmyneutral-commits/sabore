# Estratégia de testes e qualidade

## Unitários
- Casos de uso, validações, regras de visibilidade, mapeamento de DTOs e tratamento de erros.
- Testar que receitas novas são privadas e que publicar requer ação explícita.
- Testar conflitos e transições da fila de sincronização.

## Integração
- Repositórios local/remoto, Room migrations, serialização, autenticação e tratamento de respostas da API.
- Testes de autorização no backend para utilizadores diferentes.
- Verificar comportamento quando a rede falha ou a sessão expira.

## UI
- Fluxos de entrada, criação/edição de receita, confirmação de publicação, pesquisa, favoritos, logout e eliminação de conta.
- Estados vazios, carregamento, erro, teclado, texto ampliado, leitor de ecrã e orientação/tamanhos de ecrã relevantes.

## Segurança
- Acesso a receita privada de outra conta deve falhar.
- Testar uploads inválidos, abuso de endpoints e limites.
- Verificar ausência de tokens/PII nos logs e no crash reporting.
- Testar que entitlement Pro não pode ser ativado só alterando dados locais.

## CI
Cada pull request deve executar build, testes unitários, lint/análise estática e testes disponíveis. Builds de release devem ser reproduzíveis e assinados fora do repositório. Registar falhas e não declarar testes aprovados sem execução real.
