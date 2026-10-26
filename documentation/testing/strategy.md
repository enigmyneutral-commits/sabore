# Estratégia de testes

## Unitários
Validar regras de receita, estados de visibilidade, regras de assinatura e lógica de domínio.

## Integração
Validar banco, repositórios, autenticação, armazenamento de mídia, pagamento, filas e integrações externas.

## End-to-end
- Navegação responsiva.
- Pesquisa e filtros.
- Cadastro/edição de receita.
- Acesso a receitas privadas.
- Publicação explícita.
- Seguir/bloquear/denunciar usuários.
- Pro, renovação e cancelamento.
- Exclusão de conta.
- Preferências de publicidade.
- Tratamento de erros.

## Segurança
Testes negativos para IDOR/BOLA, escalada de privilégios, upload inseguro, repetição de webhooks, rate limit e vazamento de dados em logs.

## Critérios
Não considerar feature pronta apenas porque a tela renderiza. Exigir critérios de aceite, testes relevantes e documentação atualizada. Os fluxos demonstrativos atuais não substituem testes integrados de produção.
