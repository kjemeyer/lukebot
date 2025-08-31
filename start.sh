#!/bin/zsh

SERVER_PLUGIN_PATH="./Server/plugins"
PLUGIN_SOURCE="./kidbots/build/libs/kidbots-1.0-SNAPSHOT.jar"





# copy plugin to server
cp "$PLUGIN_SOURCE" "$SERVER_PLUGIN_PATH"

pushd Server
java -Xms2G -Xmx2G -jar paper-1.21.8-49.jar --nogui
popd
