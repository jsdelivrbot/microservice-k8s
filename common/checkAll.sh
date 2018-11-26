#!/bin/sh

# $1 -> content
function printWithHr {
	content=$1
	
	printf '%*s\n' "${COLUMNS:-$(tput cols)}" '' | tr ' ' =
	echo $content
	printf '%*s\n' "${COLUMNS:-$(tput cols)}" '' | tr ' ' =
	echo
}

# $1 -> content
function printWithUnderline {
	content=$1
	
	echo $content
	underlineLength=`echo -n $content | wc -m`
	printf "%0.s-" $(seq 1 $underlineLength)
	echo
}

# $1 -> folder
# $2 -> tryPull
# $3 -> branch
function findChanges {
	currentFolder=$1
	tryPull=$2
	branch=$3
	
	cd $currentFolder
	currentBranch=`git rev-parse --abbrev-ref HEAD`
	
	if [ "$branch" != unspecified ] && [ "$branch" != "$currentBranch" ];then
		git checkout $branch
	fi
	
	printWithHr "Searching for changes in $currentFolder ($currentBranch)"
	git fetch
	
	success="## $currentBranch...origin/$currentBranch"
	status=`git status --branch $currentBranch --porcelain`
	
	
	if [ "$status" != "$success" ]; then
		printWithUnderline "Changes detected"
		git status
		if [ "$tryPull" == true ]; then
			printWithUnderline "Perform pull"
			git pull
		else
			echo "Pull omitted."
		fi
		
	else
		echo "No changes detected"
	fi
}

# $1 -> tryPull
# $2 -> branch
function checkAll {
	tryPull=$1
	branch=$2
	
	printWithHr "Setting environment variables for the script"
	SCRIPT=$(readlink -f "$0")
	SCRIPTPATH=$(dirname "$SCRIPT")
	

	for d in $SCRIPTPATH/../*; do
		if [[ -d "$d" && ! -L "$d" ]]; then
			findChanges $d $tryPull $branch
		fi
	done
}

function printHelp {
	printWithHr "Usage"
	echo "checkAll.sh [options]"
	echo 
	printWithUnderline "Options (every option is optional :)"
	echo 
	echo " -h       show usage help (if specified no other operation will run)"
	echo " -p       if set, when changes detected performs a git pull"
	echo " -b %s    branch name to be checked, if not specified the current branch will be checked"
}

# Setting the option index to start position
OPTIND=1
# Default options
isHelp=false
tryPull=false
branch=unspecified

# Setting options based on arguments
while getopts hpb: option
do
	case "${option}"
		in
		h)
			isHelp=true
			;;
		p)
		    tryPull=true
			;;
		b)
			branch=$OPTARG
			;;
	esac
done

# Show help or perform the check with the options
if [ "$isHelp" == true ]; then
	printHelp
else
	checkAll $tryPull $branch
fi
