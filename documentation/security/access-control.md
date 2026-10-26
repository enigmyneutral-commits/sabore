# Autenticação e autorização

## Estado
OAuth Google ainda não integrado no protótipo.

## Fluxo pretendido
1. Cliente inicia fluxo OAuth.
2. Provedor autentica o usuário.
3. Backend valida a resposta/token de acordo com o fluxo escolhido.
4. Backend associa issuer/subject externo a uma identidade interna.
5. Sessão segura é emitida.
6. Cada requisição protegida valida sessão, estado de conta e autorização do recurso.

## Regras
- Não confiar em e-mail ou IDs enviados pelo cliente sem validação criptográfica.
- Não armazenar senha Google.
- Cookies web devem usar Secure, HttpOnly e política SameSite adequada quando for usada autenticação por cookie.
- Proteger fluxos contra CSRF, replay e redirecionamentos não autorizados.
- Revogar sessões no logout/encerramento conforme a estratégia.
- Aplicar MFA a administradores quando disponível/adequado.
- Usar RBAC para funções administrativas e verificar propriedade para recursos do usuário.
- Limitar escopos OAuth ao mínimo necessário.

## Login exclusivamente Google
A exigência de produto é login/cadastro por Google. Antes de publicar para iOS, avaliar as regras aplicáveis da Apple sobre provedores de login. A autenticação do frontend depende de integração real; botões ou telas de demonstração não constituem login funcional.
