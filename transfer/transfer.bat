


set PARTNER=%1
set ROOT_DIR=%2
if [%2]==[] (
    set ROOT_DIR=IMSTransferFiles
)

call config/partners/%PARTNER%/config.bat

call bin/createDirectories.bat

echo Running transfer script for Partner[%PARTNER%]

rem WinSCP.com /log=log/winscp.log /script=bin/process.bat

if %ERRORLEVEL%==1 goto ERROR

if %NEED_TO_DECRYPT%==true goto DECRYPT  

if %ERRORLEVEL%==1 goto ERROR

echo SUCCESS
exit /b 1


:DECRYPT
call decrypt.bat
exit /b 1


:ERROR
echo Error!
echo Sending ERROR mail
exit /b 0
