FOR /F %%M IN (outboundTemplate.txt) DO (
     echo Checking %ROOT_DIR%\Outbound\%PARTNER%\%%M
     if not exist %ROOT_DIR%\Outbound\%%M mkdir %ROOT_DIR%\Outbound\%PARTNER%\%%M
)