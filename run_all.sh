#!/usr/bin/env bash

# database 01
java -jar -Dserver.port=8082 db-server/target/db-server-0.0.1-SNAPSHOT.jar &&

# database 02
java -jar -Dserver.port=8083 db-server/target/db-server-0.0.1-SNAPSHOT.jar &&

# web server responsável por escalonar as requisições
java -jar web-server/target/web-server-0.0.1-SNAPSHOT.jar &&

# web client que recebe as solicitações de criar transações
java -jar web-client/target/web-client-0.0.1-SNAPSHOT.jar
