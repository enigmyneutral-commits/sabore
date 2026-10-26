# Segurança e privacidade

## Dados e visibilidade
- Receitas privadas por padrão.
- Publicação requer ação explícita e confirmação clara.
- A autorização é aplicada no backend a cada pedido, não apenas ocultando botões na UI.
- Conteúdo privado não pode ser incluído em feeds, endpoints públicos, notificações ou logs.
- Definir retenção, exportação e eliminação de dados e ficheiros.

## Autenticação e sessão
- Usar fluxo oficial Google Sign-In/Firebase ou equivalente depois de validar a arquitetura.
- Verificar tokens no servidor, validar emissor/audiência/expiração e associar identidade a uma conta interna.
- Aplicar expiração e revogação de sessão.
- Guardar credenciais temporárias com mecanismos Android adequados; nunca registar tokens.

## Proteção de rede e armazenamento
- HTTPS obrigatório em produção.
- Não desativar validação TLS.
- Não incluir segredos em código, assets, logs, strings, BuildConfig distribuído ou repositório.
- Usar Android Keystore quando for necessário proteger material criptográfico local.
- Validar inputs no cliente e novamente no servidor; limitar tamanho e tipo de uploads.
- Evitar permissões Android desnecessárias.

## Comunidade e moderação
- Denunciar conteúdo/contas, bloquear ou restringir interações quando previsto.
- Limitar spam e abuso; definir política de moderação, recurso e remoção.
- Escapar/formatar conteúdo de utilizador com segurança.
- Implementar rate limits no backend.

## IA culinária
- Enviar apenas os dados necessários para a tarefa.
- Informar quando uma pergunta é enviada a um serviço externo.
- Não enviar receitas privadas ou dados pessoais para treino sem consentimento explícito e base apropriada.
- Não apresentar recomendações como garantia de ausência de alergénios ou segurança alimentar.

## Privacidade e conformidade
Preparar política de privacidade, termos, declaração de dados da Google Play, processo de eliminação de conta e canal de contacto. Rever LGPD e requisitos aplicáveis com responsável jurídico antes do lançamento. A documentação não substitui avaliação legal.

## Checklist antes de release
- Rever permissões, dependências e SDKs.
- Verificar tráfego, logs, cache e backups.
- Testar acesso cruzado entre contas.
- Verificar processo de eliminação de conta e dados.
- Auditar publicidade, analytics e SDKs de terceiros.
- Nunca distribuir chaves privadas de IA, banco ou administração no aplicativo.
