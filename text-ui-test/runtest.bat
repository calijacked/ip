@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete previous output
if exist ACTUAL.TXT del ACTUAL.TXT

REM create a temporary file list of all java files recursively
if exist sources.txt del sources.txt
for /R ..\src\main\java %%f in (*.java) do echo %%f >> sources.txt

REM compile all java files from the list
javac -Xlint:none -d ..\bin @sources.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    del sources.txt
    exit /b 1
)
del sources.txt

REM run the program, feed commands from input.txt file and redirect output
java -classpath ..\bin ragebait.Ragebait < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT