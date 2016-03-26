@echo off
SetLocal ENABLEEXTENSIONS ENABLEDELAYEDEXPANSION

call bin/createInboundStructure.bat config/programManagers.txt config/inboundTemplate.txt
call bin/createOutboundStructure.bat config/outboundTemplate.txt