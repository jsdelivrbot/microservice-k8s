#!/bin/sh
SECONDS=0
declare -a statuses
overallStatus=0

# $1 -> content
function printWithHr() 
{
	printf '%*s\n' "${COLUMNS:-$(tput cols)}" '' | tr ' ' =
	echo "$1"
	printf '%*s\n' "${COLUMNS:-$(tput cols)}" '' | tr ' ' =
}

# $1 -> content
function printWithUnderline() 
{
	content="$1"
	
	echo $content
	underlineLength=`echo -n $content | wc -m`
	printf "%0.s-" $(seq 1 $underlineLength)
	echo
}

# $1  -> Content
# $2 -> Status
# $3 -> Result variable name (the result will be assigned to this variable)
# 	 		simple variable -> variable
# 	 		array variable  -> variable+
function getStatusString()
{
	local red=`tput setaf 1`
	local green=`tput setaf 2`
	local reset=`tput sgr0`
	
	content="$1"
	status="$2"
	resultVariable="$3"
	
	if [ $status -ne 0 ]; then
		# Checking if we add it to an array
		if [[ "$resultVariable" = *"+" ]]; then
			eval $resultVariable="(\"$content $red [ error ] $reset\")"
		else
			eval $resultVariable="\"$content $red [ error ] $reset\""
		fi
        
		overallStatus=$status
    else
		# Checking if we add it to an array
		if [[ "$resultVariable" = *"+" ]]; then
			eval $resultVariable="(\"$content $green [ ok ] $reset\")"
		else
			eval $resultVariable="\"$content $green [ ok ] $reset\""
		fi
	fi
}

# $1  -> Command name
# $.. -> Command to be run with all parameters
function runwithstatus() 
{
	local commandName="$1"
	shift
	
	printWithHr "$commandName"
    "$@"
    local status=$?
	
	getStatusString "$commandName" "$status" "statuses+"
}

# Setting the option index to start position
OPTIND=1
executeTests=" test "
# Setting options based on arguments
while getopts px option
do
	case "${option}"
		in
		p)
			mavenLocalFolder=~/.m2/repository
			echo "Purging local maven repos at $mavenLocalFolder"
			rm -rf $mavenLocalFolder
			;;
		x)
		   echo "Tests are disabled"
		   executeTests=""
		   ;;
	esac
done

printWithHr "Stopping gradle daemon"
gradle --stop

#printWithHr "Setting minikube docker environment"
#runwithstatus "Minikube env setting" eval $(minikube docker-env)

printWithHr "Setting environment variables for the build"
SCRIPT=$(readlink -f "$0")
SCRIPTPATH=$(dirname "$SCRIPT")

cd $SCRIPTPATH/../common
commandName="Building common scripts"
runwithstatus "$commandName" ./gradlew clean publishToMavenLocal

cd $SCRIPTPATH/../libraries/external-script-gradle-plugin
commandName="Building external-script-gradle-plugin"
runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

cd $SCRIPTPATH/../libraries/app-versioning-gradle-plugin
commandName="Building app-versioning-gradle-plugin"
runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

cd $SCRIPTPATH/../test-common
commandName="Building ava-api-test-common"
runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

cd $SCRIPTPATH/../libraries/oas-generator-template
commandName="Building oas-generator-template"
runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

#cd $SCRIPTPATH/../libraries/raml-oas-converter-gradle-plugin
#commandName="Building raml-oas-converter-gradle-plugin"
#runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

#cd $SCRIPTPATH/../ava-api-contract/events/account-events
#commandName="Generating and building kafka events for account service"
#runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

#cd $SCRIPTPATH/../ava-api-libraries/kafka-event-base
#commandName="Building kafka-event-base"
#runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

#cd $SCRIPTPATH/../ava-api-libraries/kafka-connector
#commandName="Building kafka-connector"
#runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

cd $SCRIPTPATH/../libraries/service-base
commandName="Building service-base"
runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

# Must be placed before any contract build step
cd $SCRIPTPATH/../contract/api/contract-commons
commandName="Generating common contract type/resource/trait artifact"
runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

#cd $SCRIPTPATH/../ava-api-contract/api/acp-account
#commandName="Generating and building acp-account contract related artifacts"
#runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

cd $SCRIPTPATH/../contract/api/external
commandName="Generating and building external contract related artifacts"
runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

cd $SCRIPTPATH/../contract/api/card-management
commandName="Generating and building card-management contract related artifacts"
runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

#cd $SCRIPTPATH/../ava-api-contract/api/account
#commandName="Generating and building 	account contract related artifacts"
#runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

#cd $SCRIPTPATH/../ava-api-contract/api/exchange-rate
#commandName="Generating and building exchange rate contract related artifacts"
#runwithstatus "$commandName" ./gradlew clean $executeTests publishToMavenLocal

#cd $SCRIPTPATH/../ava-api-acp/
#commandName="Building acp"
#runwithstatus "$commandName" ./gradlew clean build $executeTests

#cd $SCRIPTPATH/../ava-api-account-service
#commandName="Building account service"
#runwithstatus "$commandName" ./gradlew clean build $executeTests

#cd $SCRIPTPATH/../ava-api-exchange-rate-service/
#commandName="Building exchange-rate-service"
#runwithstatus "$commandName" ./gradlew clean build $executeTests

cd $SCRIPTPATH/../external-mock
commandName="Building external mock service"
runwithstatus "$commandName" ./gradlew clean build $executeTests publishToMavenLocal
commandName="Creating docker image for card management service"
runwithstatus "$commandName" docker image build . -t epam.com/external-mock-service


cd $SCRIPTPATH/../card-management
commandName="Building card management service"
runwithstatus "$commandName" ./gradlew clean build $executeTests
commandName="Creating docker image for card management service"
runwithstatus "$commandName" docker image build . -t epam.com/card-management-service

#cd $SCRIPTPATH/../ava-api-account-service
#commandName="Creating docker image for account service"
#runwithstatus "$commandName" docker image build . -t epam.com/account-service

#cd $SCRIPTPATH/../ava-api-acp
#commandName="Creating docker image for acp"
#runwithstatus "$commandName" docker image build . -t epam.com/acp

#cd $SCRIPTPATH/../ava-api-exchange-rate-service
#commandName="Creating docker image for exchange-rate service"
#runwithstatus "$commandName" docker image build . -t epam.com/exchange-rate-service


echo
getStatusString "Overall status" "$overallStatus" "overAllStatusString"
printWithUnderline "$overAllStatusString (in: $SECONDS seconds)"

for status in "${statuses[@]}"
do
  printf "$status\n"
done

