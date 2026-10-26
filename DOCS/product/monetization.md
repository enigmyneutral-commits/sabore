# Monetização

## Modelo proposto
- Plano gratuito com anúncios da plataforma, claramente identificados e separados do conteúdo editorial.
- Saborê Pro: referência inicial de R$ 4,99/mês, sem anúncios da plataforma.
- Preço, impostos, renovação, período de teste, cancelamento e benefícios devem ser confirmados antes da publicação.

## Regras de implementação
- Exibir paywall transparente com preço local, periodicidade, benefícios e condições.
- Implementar compras com Google Play Billing se essa for a distribuição escolhida e cumprir as políticas vigentes.
- Validar compras e entitlement no backend; não confiar em flags locais.
- Restaurar compras elegíveis após reinstalação e explicar como gerir/cancelar assinatura.
- Não colocar anúncios em fluxos sensíveis, nem disfarçá-los como receitas ou ações de navegação.
- Respeitar consentimento e requisitos de privacidade dos SDKs de anúncios.

## Critérios de aceitação
- Utilizador gratuito pode usar as funções gratuitas anunciadas.
- Pro remove anúncios da plataforma, mas não altera a privacidade de receitas.
- Falha de verificação de compra não deve conceder Pro indefinidamente.
- Estado da assinatura e comportamento de renovação são comunicados com clareza.
