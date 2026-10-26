# Build, assinatura e publicação

## Desenvolvimento
1. Instalar uma versão suportada do Android Studio e JDK conforme a versão de Gradle/AGP escolhida.
2. Configurar SDKs Android necessários.
3. Obter o projeto e sincronizar Gradle.
4. Configurar endpoints e credenciais de desenvolvimento fora do controlo de versão.
5. Executar testes e compilar variante debug.

Os comandos exatos dependem dos arquivos Gradle existentes. Como este repositório ainda precisa ser inspecionado durante a implementação, não assumir nomes de módulos ou tarefas que não tenham sido confirmados.

## Release
- Definir applicationId, versionCode, versionName, minSdk e targetSdk.
- Configurar assinatura de release de forma segura; keystore e passwords nunca são commitados.
- Produzir Android App Bundle (.aab) e testar em faixa interna da Google Play.
- Preencher ficha da loja, classificação etária, Data safety, política de privacidade e informações de anúncios/assinaturas.
- Validar processo de eliminação de conta, suporte e contactos.
- Fazer testes em dispositivos reais e monitorizar falhas após lançamento.

## Checklist de publicação
- [ ] Build release bem-sucedido.
- [ ] Testes críticos executados.
- [ ] Sem segredos no APK/repositório.
- [ ] Links legais acessíveis.
- [ ] Fluxos de login, compra e eliminação testados.
- [ ] Capturas de ecrã e ficha da loja verificadas.
- [ ] Política de privacidade e declarações atualizadas.
- [ ] Plano de rollback e monitorização definido.
