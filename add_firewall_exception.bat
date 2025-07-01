@echo off
echo ========================================
echo Aggiunta Regole Firewall per ngrok
echo ========================================
echo.
echo IMPORTANTE: Esegui come Amministratore!
echo.
pause

:: Aggiungi regole firewall per ngrok
echo Creazione regola in entrata...
netsh advfirewall firewall add rule name="ngrok - Inbound" dir=in action=allow program="%PROGRAMFILES%\ngrok\ngrok.exe" enable=yes
netsh advfirewall firewall add rule name="ngrok - Inbound (Any Path)" dir=in action=allow program="ngrok.exe" enable=yes

echo.
echo Creazione regola in uscita...
netsh advfirewall firewall add rule name="ngrok - Outbound" dir=out action=allow program="%PROGRAMFILES%\ngrok\ngrok.exe" enable=yes
netsh advfirewall firewall add rule name="ngrok - Outbound (Any Path)" dir=out action=allow program="ngrok.exe" enable=yes

:: Aggiungi anche regole per LM Studio
echo.
echo Creazione regole per LM Studio (porta 1234)...
netsh advfirewall firewall add rule name="LM Studio - Port 1234" dir=in action=allow protocol=TCP localport=1234
netsh advfirewall firewall add rule name="LM Studio - Port 1234 Out" dir=out action=allow protocol=TCP localport=1234

echo.
echo ========================================
echo Regole Firewall create con successo!
echo ========================================
echo.
pause