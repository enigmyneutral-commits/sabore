# Configuração Android e Firebase

O repositório atualmente contém a aplicação web Next.js. Esta pasta documenta a configuração Gradle Kotlin DSL para um futuro módulo Android nativo; ainda não há um projeto Android Studio completo aqui.

## 1. Gradle do projeto

No arquivo `<android-project>/build.gradle.kts`, declare o plugin Google Services sem aplicá-lo ao projeto inteiro:

```kotlin
plugins {
    // Mantenha os plugins existentes do projeto.
    id("com.google.gms.google-services") version "4.5.0" apply false
}
```

Se o arquivo já contiver um bloco `plugins`, adicione apenas a linha do Google Services dentro desse bloco. Não duplique o bloco.

## 2. Gradle do módulo de aplicação

No arquivo `<android-project>/<app-module>/build.gradle.kts`:

```kotlin
plugins {
    id("com.android.application")
    // Preserve também os plugins que já existirem no projeto.
    id("com.google.gms.google-services")
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:35.1.0"))
    implementation("com.google.firebase:firebase-analytics")

    // Adicione apenas produtos Firebase realmente utilizados pelo aplicativo.
    // Exemplo para Firebase Authentication:
    // implementation("com.google.firebase:firebase-auth")
}
```

Não replique cegamente os blocos de exemplo se o projeto Android já tiver os plugins ou as dependências definidos. Integre somente as linhas necessárias.

## 3. Arquivo de configuração Firebase

1. Crie ou selecione um projeto no Firebase Console.
2. Registre o aplicativo Android com o application ID exato definido no Gradle.
3. Baixe o arquivo `google-services.json`.
4. Coloque-o no diretório do módulo da aplicação, normalmente `app/google-services.json`.
5. Não altere seu nome para `Google-services.json`.
6. Execute a sincronização Gradle e verifique o build.

Não coloque o arquivo de configuração do app em um módulo incorreto. Revise também as regras de segurança, chaves e credenciais antes de publicar o aplicativo.

## 4. Login Google e Firebase Authentication

O produto Saborê foi especificado para login/cadastro com Google. Analytics sozinho não implementa autenticação. Quando o módulo Android for criado, configure Firebase Authentication ou o fluxo OAuth escolhido, valide tokens no backend e vincule a identidade do provedor a uma identidade interna do Saborê.

A autenticação da aplicação web Next.js não passa a funcionar apenas por incluir os plugins Gradle nesta documentação.

## 5. BoM e versões

O exemplo solicitado usa Firebase BoM `35.1.0` e Google Services Gradle Plugin `4.5.0`. Antes de adotar esses números numa aplicação real, confirme a compatibilidade com as versões do Android Gradle Plugin, Gradle Wrapper, Kotlin e os SDKs necessários no ambiente Android.

Referências:
- https://firebase.google.com/docs/android/setup
- https://firebase.google.com/docs/android/learn-more#bom

## Estado

- [x] Instruções Kotlin DSL documentadas.
- [ ] Projeto Android Studio criado.
- [ ] `google-services.json` configurado para o application ID real.
- [ ] Firebase Authentication integrado e testado.
- [ ] Firebase Analytics configurado e revisado quanto à privacidade.
- [ ] Build Android validado em ambiente local/CI.
