#!/bin/bash

function pull {
	echo
    echo pulling $1
    cd ../$1
    git checkout master
    git pull origin master
}


pull ava-api-acp
pull ava-api-alfa-service
pull ava-api-beta-service
pull ava-api-common
pull ava-api-contract
pull ava-api-deployment
pull ava-api-libraries
pull ava-api-test-common
pull ava-api-test-end-to-end
