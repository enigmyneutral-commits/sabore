# Prompt para IA: Configuração e Deploy do Backend Saborê na Cloudflare

Copie e cole o texto abaixo para qualquer agente de IA ou terminal para configurar e implantar o backend do Saborê na Cloudflare:

---

```text
Atue como um Engenheiro Cloudflare e Desenvolvedor Backend Sênior. Preciso que você configure, teste e faça o deploy do backend Node.js (Hono) do aplicativo Saborê na Cloudflare Workers utilizando a pasta `backend/` do repositório.

Siga exatamente estas instruções e requisitos:

1. **Stack & Ambiente:**
   - O projeto está na pasta `backend/` e utiliza Hono com TypeScript para Cloudflare Workers.
   - O banco de dados conectado é o PostgreSQL (Neon DB).
   - O gateway de pagamentos integrado é a Efí Pay (Pix / Saborê Pro R$ 4,99/mês) utilizando a SDK oficial e certificados `.p12`.

2. **Configuração do Domínio:**
   - O domínio personalizado configurado no `wrangler.toml` é `api.dmgrecords.com.br`.
   - Garanta que todas as rotas e certificados SSL/TLS estejam mapeados corretamente para este domínio na Cloudflare.

3. **Segredos e Variáveis de Ambiente (`wrangler secret put`):**
   Configure os seguintes segredos no Cloudflare Worker utilizando o Wrangler:
   - `DATABASE_URL`: `postgresql://neondb_owner:npg_sgMFaJVr2e6o@ep-cool-thunder-b8kvmix2-pooler.c-14.us-east-1.aws.neon.tech/neondb?channel_binding=require&sslmode=require`
   - `EFI_CLIENT_ID`: `Client_Id_d5ba6378c287a1b99a8c0fe5722400caef41d571` (ou credencial de homologação conforme ambiente)
   - `EFI_CLIENT_SECRET`: `Client_Secret_36ee2330c29cbf2543f7c854035d35302485dda5`

4. **Passos de Execução:**
   - Entrar na pasta `backend/`.
   - Executar `npm install`.
   - Configurar os secrets no Wrangler.
   - Executar o build e o deploy (`npm run deploy`).
   - Validar que o endpoint `https://api.dmgrecords.com.br/` responde corretamente.
```
