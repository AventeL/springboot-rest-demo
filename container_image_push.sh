#!/bin/sh

az login
az acr login --name tpbookregistrymathis

docker tag librarytp tpbookregistrymathis.azurecr.io/librarytp:latest

docker push tpbookregistrymathis.azurecr.io/librarytp:latest