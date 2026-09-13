@echo off
cd /d "%~dp0.."
if not exist "output\frames_serial\frame_000.png" goto :missing
ffmpeg -y -framerate 12 -i output\frames_serial\frame_%%03d.png -i input\audio\audio_base.wav -c:v libx264 -pix_fmt yuv420p -shortest videoclip_serial.mp4
echo.
echo Listo: videoclip_serial.mp4 generado en %cd%
pause
goto :eof
:missing
echo No se encontraron frames en output\frames_serial
echo Genera los frames primero desde VideoClipGUI o VideoClipMain.
pause
