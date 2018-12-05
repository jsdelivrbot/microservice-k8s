# Remote deployment

## Prerequests
If you want to deploy your service to our remote kubernetes cluster first you need to get your authentication **config** file from our **devOps** colleague.
It will look something like this:
```
apiVersion: v1
clusters:
- cluster:
    certificate-authority-data:
    server:
  name: cluster.local
contexts:
- context:
    cluster: cluster.local
    namespace: api-dev
    user: admin-cluster.local
  name: admin-cluster.local
current-context: admin-cluster.local
kind: Config
preferences: {}
users:
- name: admin-cluster.local
  user:
    client-certificate-data:
    client-key-data:
```
## 1. Push docker image to artifactory
When you have the deployable docker image you have to push it to our artifactory.

Login (before your minikube is started):

```
docker login -u $ARTIFACTORY_USER -p $ARTIFACTORY_PASSWORD artifactory.***.com:6018
```
Tag your image (after your minikube is started and eval $(minikube docker-env) is executed):
```
docker tag account-service:latest artifactory.***.com:6018/api-account-service:1.0.0-SNAPSHOT
```
Push it:
```
docker push artifactory.***.com:6018/api-account-service:1.0.0-SNAPSHOT
```

## 2. Connect to the Remote server
Export your **config** file with your bash:
```
export KUBECONFIG=./config
```
After that you are connected immediately to the remote cluster.
Now you can use **kubectl** commands remotely but from now on you **cannot** use **minikube** commands.

## 2. Remote Helm deployment
```
kubectl create namespace development
kubectl create secret docker-registry regcred --docker-server=artifactory.company.com:6018 \
  --docker-username=<company-username/company-technical-username> \
  --docker-password=<company-password/company-technical-user-password> \
  --docker-email=<company-email/company-technical-user-email>

helm install --debug --name account-service \
  --set nameOverride=account-service \
  --set image.repository=artifactory.company.com:6018 \
  --set image.name=account-service \
  --set image.tag=1.0.0-SNAPSHOT \
  --set imagePullSecret=regcred \
  --set ingress.wildCardDomain=account-service.10.***.129.***.nip.io \
  --set image.pullPolicy=Always \
  --namespace=development \
  service
```

## 3. Dashboard
Fill the namespcace input field with development
https://kubernetes-dashboard.10.253.129.19.nip.io/#!/pod?namespace=development

## Hints
```
kubectl config get-contexts
kubectl config set-context admin-cluster.local --namespace=development
helm list

kubectl get all --all-namespaces
```
