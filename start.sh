#!/bin/zsh

pushd Server
java -Xms2G -Xmx2G -jar paper-1.21.8-49.jar --nogui
popd
