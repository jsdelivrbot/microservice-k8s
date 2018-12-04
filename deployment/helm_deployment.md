# Helm deployment

## Prerequests

Make sure the following softwares were installed:

Helm is installed according to the instructions in this article:

- [Installing Helm](https://docs.helm.sh/using_helm/#installing-helm)

Helm is [initialized](https://docs.helm.sh/helm/#helm-init) together with local Minikube.
- `helm init`
This command installs Tiller (the Helm server-side component) onto your Kubernetes Cluster and sets up local configuration in $HELM_HOME (default ~/.helm/).

## Deploying and deleting Helm charts

Open a Git Bash and navigate to ./deployment/helm/charts.
The particular charts are separated based on their use case, reflecting infrastructural or business purposes.
Each chart contains the necessary Kubernetes items to be deployed (deployment, service, ingress, configmap, etc.)

In order to [install](https://docs.helm.sh/helm/#helm-install) a so called _release_ use the following commands:
- `helm install zipkin`
- `helm install postgres`
- `helm install zookeeper`

__Kafka:__ Helm chart for Kafka is not ready. Use the following kubernetes deployment scripts.

- `kubectl apply -f kafka-service.yaml`
- `kubectl apply -f kafka-deployment.yaml`

The microservices releases use a chart that has to be used with proper template values.
- `kubemasterIP=$(kubectl config view | grep server | cut -d "/" -f 3 | cut -d ":" -f 1)`
- `wildCard="ingress.wildCardDomain=${kubemasterIP}.nip.io"`
- `helm install --debug --name alfa-service --set $wildCard --set nameOverride=alfa-service --namespace default service`
- `helm install --debug --name beta-service --set $wildCard --set nameOverride=beta-service --namespace default service`
- `helm install --debug --name acp          --set $wildCard --set nameOverride=acp          --namespace default service`

_Ingress:_ In order to expose your services to http://<my_ip>/<service-name> instead of random ports install the following kubernetes deployment script.
For example you can reach alfa at https://192.168.99.100/alfa-service/alfa with this
- `minikube addons enable ingress`
- `kubectl create -f ingress-minikube.yaml`

In order to simulate an install use the following command, extended with `--dry-run`:
- `helm install --dry-run --debug --name alfa-release --set nameOverride=alfa-service --namespace default service`

[Deleting](https://docs.helm.sh/helm/#helm-delete) a _release_ can be done with these commands:
- `helm delete zipkin`
- `helm delete postgres`
- `helm install zookeeper`
- `helm delete alfa-service`
- `helm delete beta-service`
Sometimes a previously installed release may cause name conflict. Purging a release can be done with these commands:
- `helm delete --purge alfa-service`

Examining logs of a pod:
- `kubectl logs -f alfa-service-#ID`