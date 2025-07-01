# Setup ngrok con Chocolatey (Metodo Facile)

## 1. Installa Chocolatey (solo la prima volta)
Apri **PowerShell come Amministratore** e incolla:
```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

## 2. Installa ngrok
Sempre in PowerShell (Admin):
```powershell
choco install ngrok -y
```

## 3. Registrati e ottieni il token
1. Vai su https://ngrok.com/signup
2. Accedi con Google o email  
3. Copia il token da: https://dashboard.ngrok.com/get-started/your-authtoken

## 4. Configura ngrok
In PowerShell:
```powershell
ngrok config add-authtoken IL_TUO_TOKEN_QUI
```

## 5. Esponi LM Studio
```powershell
ngrok http 1234
```

## 6. Copia l'URL
Vedrai qualcosa tipo:
```
Forwarding  https://abc123xyz.ngrok-free.app -> http://localhost:1234
```

## 7. Aggiorna l'app
Nel file `ApiClient.kt` cambia:
```kotlin
private const val BASE_URL = "https://abc123xyz.ngrok-free.app/"
```

**Fatto!** 🎉