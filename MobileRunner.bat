@ECHO OFF

ECHO ============================STARTING APPIUM SERVER===================================
start /b appium --port 4723
timeout /t 6 /nobreak

ECHO ============================START TESTING============================================
call mvn clean test

ECHO Testing Complete . . . . !
tskill node

::allure generate --clean && allure open