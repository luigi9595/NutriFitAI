# Come Verificare e Avviare il Server in LM Studio

## Verifica se il Server è Attivo

### Opzione 1: Guarda in LM Studio
1. In LM Studio, cerca la tab **"Server"** o **"Local Server"**
2. Dovrebbe mostrare lo stato del server
3. Se vedi "Server running on port 1234" è tutto ok!

### Opzione 2: Test nel Browser
Apri il browser e vai su:
```
http://localhost:1234/v1/models
```
Se vedi una risposta JSON con i modelli, il server è attivo!

## Come Avviare il Server in LM Studio

### Metodo 1: Tab Server
1. Clicca sulla tab **"Server"** in LM Studio
2. Clicca **"Start Server"**
3. Assicurati che la porta sia **1234**
4. Dovrebbe apparire "Server running"

### Metodo 2: Chat Interface
1. Nella schermata principale di LM Studio
2. In basso dovrebbe esserci un toggle/switch per **"Local Server"**
3. Attivalo e imposta porta **1234**

### Metodo 3: Settings
1. Vai in **Settings** (icona ingranaggio)
2. Cerca **"Server"** o **"API"**
3. Abilita **"Enable Server"**
4. Imposta **Port: 1234**
5. Clicca **"Start"** o **"Apply"**

## Indicatori che il Server è Attivo
- ✅ Vedi "Server running on port 1234"
- ✅ http://localhost:1234/v1/models risponde
- ✅ Icona/status verde nel UI di LM Studio
- ✅ Log che mostra "Server started successfully"

## Se Non Trovi l'Opzione Server
Alcune versioni di LM Studio hanno il server in posizioni diverse:
- Prova a cercare **"API"**, **"REST"**, o **"HTTP"**
- Controlla il menu **"Tools"** o **"Developer"**
- Cerca un'icona di **"play"** o **"power"** vicino al modello caricato