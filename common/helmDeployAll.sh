#!/bin/sh
green=`tput setaf 2`
reset=`tput sgr0`

function printWithHr {
	printf '%*s\n' "${COLUMNS:-$(tput cols)}" '' | tr ' ' =
	echo $1
	printf '%*s\n' "${COLUMNS:-$(tput cols)}" '' | tr ' ' =
}

SECONDS=0

printWithHr "Setting environment variables for the deploy"
SCRIPT=$(readlink -f "$0")
SCRIPTPATH=$(dirname "$SCRIPT")
#eval $(minikube docker-env)

printWithHr "Initialize helm"
helm init --wait
kubectl rollout status -w deployment/tiller-deploy --namespace=kube-system

#printWithHr "Enabling Ingress"
#minikube addons enable ingress

printWithHr "Deleting helm charts"
helm delete --purge zipkin
#helm delete --purge postgres
#helm delete --purge zookeeper
#helm delete --purge alfa-service
#helm delete --purge beta-service
#helm delete --purge acp
#helm delete --purge exchange-rate
#helm delete --purge account-service

helm delete --purge external-mock-service
helm delete --purge card-management-service

#printWithHr "Deleting kafka service and deployment"
#cd $SCRIPTPATH/../deployment/kubernetes/kafka
#kubectl delete -f kafka-deployment.yaml
#kubectl delete -f kafka-service.yaml
#cd -

#printWithHr "Installing kafka service and deployment"
#cd $SCRIPTPATH/../deployment/kubernetes/kafka
#kubectl apply -f kafka-service.yaml
#kubectl apply -f kafka-deployment.yaml
#kubectl rollout status -w deployment/kafka-broker --namespace=default
#cd -

printWithHr "Installing Helm charts"
cd $SCRIPTPATH/../deployment/helm/charts
#printWithHr "Installing Postgres"
#helm install postgres --name postgres --wait
#printWithHr "Installing Zookeper"
#helm install zookeeper --name zookeeper --wait
printWithHr "Installing Zipkin"
helm install zipkin --name zipkin --wait
#printWithHr "Installing Kong"
#helm install kong --name kong --wait
#printWithHr "Installing acp"
#helm install --name acp          --set nameOverride=acp          --namespace default service --wait
#printWithHr "Installing beta-service"
#helm install --name beta-service --set nameOverride=beta-service --namespace default service --wait
#printWithHr "Installing alfa-service"
#helm install --name alfa-service --set nameOverride=alfa-service --namespace default service --wait
#printWithHr "Installing exchange-rate-service"
#helm install --name exchange-rate-service --set nameOverride=exchange-rate-service --namespace default service --wait
#printWithHr "Installing account-service"
#helm install --name account-service --set nameOverride=account-service --namespace default service --wait

printWithHr "Installing external-mock-service"
helm install --name external-mock-service --set nameOverride=external-mock-service --namespace default service --wait
printWithHr "Installing card-management-service"
helm install --name card-management-service --set nameOverride=card-management-service --namespace default service --wait

cd -

#export ZOOKEEPER_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].nodePort}" services zookeeper)
#export ZOOKEEPER_IP=$(kubectl get nodes --namespace default -o jsonpath="{.items[0].status.addresses[0].address}")
#echo "${green}Zookeper url: ${reset}" http://$ZOOKEEPER_IP:$ZOOKEEPER_PORT

export ZIPKIN_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].nodePort}" services zipkin)
export ZIPKIN_IP=$(kubectl get nodes --namespace default -o jsonpath="{.items[0].status.addresses[0].address}")
echo "${green}Zipkin url: ${reset}" http://$ZIPKIN_IP:$ZIPKIN_PORT

echo "It took $SECONDS seconds"