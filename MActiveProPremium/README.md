# MActivePro Premium — Protótipo Android 0.1

Projeto independente de demonstração, não afiliado à MActivePro, Apple, Nike ou Adidas.

## Implementado
- Aplicativo Android nativo em Kotlin + Jetpack Compose.
- Catálogo programático de 100 variações visuais originais (mesmo layout base com paletas e categorias distintas).
- Filtro por 10 categorias, pré-visualização e favoritos em memória.
- Tela de detalhes e botão de instalação desabilitado com mensagem honesta de incompatibilidade ainda não verificada.

## Ainda não implementado
- 100 designs exclusivos (as 100 variações atuais são derivadas de um único componente).
- Persistência de favoritos, conexão BLE, extração/conversão de arquivos, instalação real, sincronização e suporte a hardware.
- Testes em dispositivo físico e build de APK; este projeto é código-fonte inicial, não release.

## Abrir
1. Instale Android Studio recente com JDK 17 e Android SDK 35.
2. Abra esta pasta como projeto Gradle e sincronize as dependências.
3. Execute em emulador Android 8+ ou aparelho físico.
4. Se seu ambiente pedir atualizações de Gradle/AGP, use as versões compatíveis sugeridas pelo Android Studio.

## Próxima fase de compatibilidade
Inspecionar XAPK original legalmente obtido e identificar serviços BLE e formato de faces; validar comandos somente em hardware próprio, com registro de logs e medidas de recuperação. Nunca enviar bytes experimentais para o relógio sem validar protocolo e segurança.

## Gerar APK no GitHub Actions
O projeto inclui `.github/workflows/build-android.yml`. Envie o conteúdo da pasta do projeto para um repositório GitHub, abra Actions > Build Android APK > Run workflow. Se a compilação passar, baixe o artefato `MActiveProPremium-debug-apk`. O APK gerado é de depuração, não é uma versão de produção. **Esta rotina ainda não foi executada nem validada.**
