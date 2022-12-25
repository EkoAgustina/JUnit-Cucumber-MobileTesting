@ECHO OFF

ECHO ============================STARTING APPIUM SERVER===================================
start /b appium --port 4723
timeout /t 35 /nobreak

ECHO ============================START TESTING============================================
call mvn clean test

ECHO ============================TESTING COMPLETE=========================================
tskill node