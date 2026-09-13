@echo off
cd /d "%~dp0.."
if not exist "output\frames_parallel\frame_000.png" goto :missing
ffmpeg -y -framerate 12 -i output\frames_parallel\frame_%%03d.png -i input\audio\audio_base.wav -c:v libx264 -pix_fmt yuv420p -shortest videoclip_parallel.mp4
echo.
echo Listo: videoclip_parallel.mp4 generado en %cd%
pause
goto :eof
:missing
echo No se encontraron frames en output\frames_parallel
echo Genera los frames primero desde VideoClipGUI o VideoClipMain.
pause
