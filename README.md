# NutriFit AI

App Android semplice per chat con assistente AI per nutrizione e fitness.

## Configurazione

### LM Studio
1. Avvia LM Studio sul tuo PC
2. Carica il modello `deepseek/deepseek-r1-0528-qwen3-8b`
3. Avvia il server su `http://localhost:1234`

### Android Studio
1. Apri il progetto in Android Studio
2. Per emulatore: l'app è già configurata per usare `10.0.2.2:1234`
3. Per dispositivo fisico: modifica `Constants.kt` e inserisci l'IP del tuo PC

### Build
```bash
./gradlew assembleDebug
```

## Funzionalità
- Chat con AI per consigli su dieta e allenamento
- Interfaccia minimale e veloce
- Preparata per future integrazioni con Supabase e Stripe

## Architettura
- Jetpack Compose per UI
- Retrofit per networking
- MVVM pattern
- Nessuna dependency injection complessa