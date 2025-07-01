# Installazione ngrok su Windows - Guida Semplice

## Passaggio 1: Scarica ngrok
1. Vai su https://ngrok.com/download
2. Clicca su "Download for Windows"
3. Salva il file ZIP sul tuo PC

## Passaggio 2: Installa ngrok
1. Estrai il file ZIP scaricato
2. Troverai un file `ngrok.exe`
3. **Opzione A**: Copia `ngrok.exe` in `C:\Windows\System32` (per usarlo da qualsiasi cartella)
4. **Opzione B**: Tieni `ngrok.exe` in una cartella e apri il prompt dei comandi in quella cartella

## Passaggio 3: Registrati su ngrok (Gratis)
1. Vai su https://ngrok.com/signup
2. Registrati con email o Google/GitHub
3. Dopo il login, vai su https://dashboard.ngrok.com/get-started/your-authtoken
4. Copia il tuo authtoken (sarà tipo: `2gxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx`)

## Passaggio 4: Configura il token
Apri il Prompt dei Comandi (cmd) e esegui:
```cmd
ngrok config add-authtoken IL_TUO_TOKEN_QUI
```

## Passaggio 5: Esponi LM Studio
1. Assicurati che LM Studio sia in esecuzione su porta 1234
2. Nel Prompt dei Comandi, esegui:
```cmd
ngrok http 1234
```

## Passaggio 6: Usa l'URL pubblico
Vedrai qualcosa come:
```
Session Status                online
Account                       tuoemail@example.com (Plan: Free)
Version                       3.5.0
Region                        Europe (eu)
Latency                       32ms
Web Interface                 http://127.0.0.1:4040
Forwarding                    https://abc123xyz.ngrok-free.app -> http://localhost:1234
```

L'URL `https://abc123xyz.ngrok-free.app` è quello che userai nell'app!

## Passaggio 7: Aggiorna l'app
1. Apri il file `app/src/main/java/com/example/nutrifitai/data/api/ApiClient.kt`
2. Cambia questa riga:
```kotlin
private const val BASE_URL = "http://10.0.2.2:1234/"
```
In:
```kotlin
private const val BASE_URL = "https://IL_TUO_URL_NGROK.ngrok-free.app/"
```

## Note Importanti
- L'URL ngrok cambia ogni volta che riavvii ngrok (nel piano gratuito)
- Lascia ngrok in esecuzione mentre testi l'app
- Se vedi "Tunnel not found", riavvia ngrok

## Comandi Utili
- `ngrok http 1234` - Espone la porta 1234
- `ngrok http 1234 --region eu` - Usa server europei (più veloce dall'Italia)
- `Ctrl+C` - Ferma ngrok