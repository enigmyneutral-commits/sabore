# Ambientes e implantação

## Estado atual
A aplicação Next.js pode ser executada localmente. Produção ainda depende de configuração e validação do ambiente.

## Ambientes
- Development
- Test
- Staging
- Production

Cada ambiente deve ter URLs, credenciais e serviços isolados. Nunca reutilizar segredos de produção em desenvolvimento.

## Pipeline recomendado
1. Checkout da revisão.
2. Instalar dependências usando lockfile.
3. Typecheck.
4. Lint.
5. Testes unitários e de integração.
6. Build.
7. Verificação de dependências e segredos.
8. Deploy em staging.
9. Smoke tests.
10. Promoção controlada para produção.

## Operação
Configurar health checks, logs estruturados, métricas, alertas, backups, retenção, rotação de segredos, rollback e runbook de incidentes. A estratégia de deploy deverá ser definida conforme o provedor selecionado.
