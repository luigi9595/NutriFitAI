@echo off
echo ========================================
echo Aggiunta Eccezione Windows Defender
echo ========================================
echo.
echo Questo script aggiungera' ngrok.exe alle eccezioni di Windows Defender
echo.
echo IMPORTANTE: Esegui questo file come Amministratore!
echo (Click destro - Esegui come amministratore)
echo.
pause

:: Richiedi il percorso di ngrok.exe
set /p NGROK_PATH="Inserisci il percorso completo di ngrok.exe (es. C:\Users\Administrator\Downloads\ngrok.exe): "

:: Verifica se il file esiste
if not exist "%NGROK_PATH%" (
    echo.
    echo ERRORE: File non trovato in: %NGROK_PATH%
    echo Verifica il percorso e riprova.
    pause
    exit /b 1
)

echo.
echo Aggiunta eccezione per: %NGROK_PATH%
echo.

:: Aggiungi eccezione per il file
powershell -Command "Add-MpPreference -ExclusionPath '%NGROK_PATH%'"

:: Aggiungi anche la cartella contenente
for %%F in ("%NGROK_PATH%") do set NGROK_DIR=%%~dpF
powershell -Command "Add-MpPreference -ExclusionPath '%NGROK_DIR%'"

:: Aggiungi eccezione per il processo
for %%F in ("%NGROK_PATH%") do set NGROK_NAME=%%~nxF
powershell -Command "Add-MpPreference -ExclusionProcess '%NGROK_NAME%'"

echo.
echo ========================================
echo Eccezioni aggiunte con successo!
echo ========================================
echo.
echo Eccezioni create per:
echo - File: %NGROK_PATH%
echo - Cartella: %NGROK_DIR%
echo - Processo: %NGROK_NAME%
echo.
echo Ora puoi usare ngrok senza problemi!
echo.
pause