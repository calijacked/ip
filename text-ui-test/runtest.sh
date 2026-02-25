#!/usr/bin/env bash

# set repo root
REPO_ROOT=$(pwd)/..

# create bin directory if it doesn't exist
mkdir -p "$REPO_ROOT/bin"

# delete previous output
rm -f ACTUAL.TXT

# compile all java files recursively from src/main/java into bin folder
find "$REPO_ROOT/src/main/java" -name "*.java" > sources.txt
if ! javac -Xlint:none -d "$REPO_ROOT/bin" @sources.txt
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi
rm sources.txt

# run the program with input redirection
java -cp "$REPO_ROOT/bin" ragebait.Ragebait < input.txt > ACTUAL.TXT

# compare output with expected
if diff ACTUAL.TXT EXPECTED.TXT >/dev/null; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi