# Come Provare NutriFitAI Online con LM Studio Locale

## Prerequisiti
1. LM Studio in esecuzione con il modello `google/gemma-3-27b`
2. Server LM Studio attivo su `http://localhost:1234`

## Metodo 1: Usando ngrok (Consigliato)

### 1. Installa ngrok
```bash
# Windows (con Chocolatey)
choco install ngrok

# O scarica da https://ngrok.com/download
```

### 2. Configura ngrok
```bash
# Registrati su ngrok.com per un account gratuito
# Poi autentica il client
ngrok config add-authtoken YOUR_AUTH_TOKEN
```

### 3. Esponi LM Studio
```bash
ngrok http 1234
```

Otterrai un output simile a:
```
Forwarding  https://abc123xyz.ngrok-free.app -> http://localhost:1234
```

### 4. Aggiorna l'app
Modifica `ApiClient.kt`:
```kotlin
private const val BASE_URL = "https://abc123xyz.ngrok-free.app/"
```

### 5. Ricompila e carica su Appetize.io
```bash
./gradlew assembleDebug
```
Carica `app/build/outputs/apk/debug/app-debug.apk` su https://appetize.io

## Metodo 2: Port Forwarding Router

### 1. Configura Port Forwarding
- Accedi al router (di solito 192.168.1.1)
- Port forward: 1234 esterno → IP_TUO_PC:1234 interno
- Ottieni il tuo IP pubblico da https://whatismyipaddress.com

### 2. Aggiorna l'app
```kotlin
private const val BASE_URL = "http://TUO_IP_PUBBLICO:1234/"
```

### 3. Build e Upload
Come sopra, compila e carica su Appetize.io

## Metodo 3: Tailscale (VPN Mesh)

### 1. Installa Tailscale
- Sul PC: https://tailscale.com/download
- Sul dispositivo Android di test

### 2. Connetti entrambi i dispositivi
Entrambi devono essere sulla stessa rete Tailscale

### 3. Usa l'IP Tailscale
```kotlin
private const val BASE_URL = "http://100.x.x.x:1234/" // IP Tailscale del PC
```

## Note di Sicurezza
- **ngrok**: I dati passano attraverso i server ngrok
- **Port Forwarding**: Espone il servizio a internet - usa con cautela
- **Tailscale**: Più sicuro, crea una rete privata virtuale

## Test Locale Semplice
Se vuoi solo testare localmente:
1. **Emulatore Android Studio**: Già configurato con `10.0.2.2:1234`
2. **Dispositivo fisico**: 
   - Connetti alla stessa WiFi del PC
   - Trova IP locale del PC (es: `ipconfig` su Windows)
   - Modifica `Constants.kt` con l'IP locale