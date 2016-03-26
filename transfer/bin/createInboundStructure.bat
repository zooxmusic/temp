FOR /F %%M IN (programManagers.txt) DO (
    FOR /F %%I IN (inboundTemplate.txt) DO (
        echo Checking %ROOT_DIR%\Inbound\%PARTNER%\%%M\%%I
        if not exist %ROOT_DIR%\Inbound\%PARTNER%\%%M\%%I mkdir %ROOT_DIR%\Inbound\%PARTNER%\%%M\%%I
    )
)