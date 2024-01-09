#!/bin/sh

az login
az acr login --name tpbookregistrymathis

docker pull tpbookregistrymathis.azurecr.io/librarytp:latest
docker run -d -p 8080:8080 tpbookregistrymathis.azurecr.io/librarytp:latest