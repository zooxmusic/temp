open %URL%

cd %IN_DIR%
lcd config/partners/%PARTNER%/Inbound
get *.*
cd /
# TODO - fix eventually
lcd ../../../

cd %OUT_DIR%
lcd config/partners/%PARTNER%/Outbound
put *.*

close

exit