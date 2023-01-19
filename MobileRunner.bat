@ECHO OFF

ECHO:
ECHO ============================STARTING APPIUM SERVER===================================
start /b appium --port 4723
timeout /t 6 /nobreak

ECHO:
ECHO ============================START TESTING============================================
call mvn clean test

ECHO:
ECHO Testing Complete . . . . !
tskill node

::allure generate --clean && allure open