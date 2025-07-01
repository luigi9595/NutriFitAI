# Alternative a ngrok per Tunnel Sicuri

## 1. LocalTunnel (Open Source)
```bash
# Installa con npm
npm install -g localtunnel

# Usa
lt --port 1234
```

## 2. Cloudflare Tunnel (Gratuito)
1. Scarica da: https://github.com/cloudflare/cloudflared/releases
2. Non richiede registrazione per test veloci:
```bash
cloudflared tunnel --url http://localhost:1234
```

## 3. Serveo (Semplice, no installazione)
```bash
# Solo SSH, nessun download!
ssh -R 80:localhost:1234 serveo.net
```

## 4. Bore (Leggero)
```bash
# Scarica da: https://github.com/ekzhang/bore/releases
bore local 1234 --to bore.pub
```

## 5. Tailscale (VPN Mesh - Più Sicuro)
- Scarica: https://tailscale.com/download
- Crea rete privata tra i tuoi dispositivi
- Nessun tunnel pubblico, più sicuro

## Test Locale Senza Tunnel
Se preferisci evitare tunnel esterni:

### Opzione A: USB Debugging
1. Collega Android via USB
2. Abilita USB Debugging
3. Usa `adb reverse`:
```bash
adb reverse tcp:1234 tcp:1234
```
4. L'app può usare `http://localhost:1234`

### Opzione B: WiFi Locale
1. PC e telefono sulla stessa WiFi
2. Trova IP del PC: `ipconfig`
3. Usa l'IP locale nell'app: `http://192.168.1.X:1234`

## Consiglio
Per test rapidi, **Cloudflare Tunnel** o **Serveo** sono ottime alternative che non triggano antivirus.