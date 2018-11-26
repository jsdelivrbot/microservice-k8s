# Infrastructure as a code with Ansible on EPAM cloud

This is a simple example how to implement infrastructure as a code using Ansible. The playbooks can be found in the infra subdirectory. This example uses ansible dynamic inventory configured by the `or2-ansible-init` command, the inventory group variables are stored in the inventory subdirectory.

Things you'll need:
* Linux/Mac OS based machine
* [ansible](https://www.ansible.com) - version >= 2.4
* [kubspray](https://github.com/kubernetes-incubator/kubespray) - to deploy k8s
* [cfssl](https://pkg.cfssl.org/R1.2/cfssl_linux-amd64)- to generate k8s ingress TLS
* [cfssljson](https://pkg.cfssl.org/R1.2/cfssljson_linux-amd64) - to generate k8s ingress TLS
* [maestro-cli](https://cloud.epam.com/maestro2/ui/help/develop/maestro_c=l=i) - to deploy cluster vms
* python-netaddr package installed - for kubespray

## Workflow
First you can get all the prerequisite software components listed above. Then you can install your maestro cli client, activate your project in the epam cloud and get familiar with the maestro-stack builder to build your kubernetes cluster maestro template.

Your instances should be running with additional volumes attached for docker and container storage (PVs).

To deploy kubernetes with kubespray you will need either a static inventory file or you can tag your desired hosts with the proper ansible groups. Before kubernetes deployment there are some prerequisite tasks which we will handle with ansible. Things like disabling swap, installing ntp and update packages. The playbook infra/prepare_cluster.yaml implements this preparation.

Other hosts like an external Elasticsearch instance can be configured with the infra/elasticsearch.yaml playbook. Make sure to upgrade the variables in this playbook to match your host's name and IP address.

## Create ansible groups in EPAM Orchestrator
```
or2-ansible-groups -a CREATE -g k8s-cluster -g kube-master \
    -g kube-node -g etcd -g vault -g elasticsearch -g jenkins
```

## Instantiate cluster nodes on EPAM cloud

> Instances should be created by a meastro template instead of manual deployment.
```
or2-run-maestro-stack -s k8s-cluster-stack
```

## Add maestro deployed hosts into ansible groups
In case the cluster nodes were deployed by a maestro stack make sure each node is inside it's ansible group `or2-ansible-hosts -a INCLUDE -g kube-node -g etcd -h hostecsb0124.epam.com`.

## Creating instances manually
> The number of etcd hosts must be an odd number.

Deploy a master which will not run containers.
```
or2-run-instances -g k8s-cluster -g kube-master -g etcd -g vault -i CentOS7_64-bit -s LARGE.40.HDD
```
Deploy two kubernetes nodes with etcd to do the heavy work.
```
or2-run-instances -g k8s-cluster -g kube-node -g etcd -c 2 -i CentOS7_64-bit -s XL.40.HDD
```
Deploy a simple node without etcd.
```
or2-run-instances -g k8s-cluster -g kube-node -i CentOS7_64-bit -s XL.40.HDD
```
Deploy a node for Elasticsearch and external Kibana for logs.
```
or2-run-instances -g elasticsearch -i CentOS7_64-bit -s LARGE.40.HDD
```


### Attach additional volumes
After all the instances are in running state we should attach additional volumes to be used by docker, kubernetes or for example elasticsearch to store data. The 40GB system disk will not be enough in the long term. This step should be included in the maestro-stack as a new command (Add command button).
Example to attach two volumes to the same instane.
```
or2-create-attach-volume -i kube-node_instance_id -s 80
or2-create-attach-volume -i kube-node_instance_id -s 60
```
Check the status of the attached disks using ansible: `ansible -a "lsblk" -i inventory/dev/ k8s-cluster`.

## Clone the kubespray project, update and symlink your inventory
Make sure to check the default inventory template of kubespray to compare your group_vars/k8s-cluster.yaml and group_vars/all.yaml for updates, and look into the kubepsray project's README.md.
```
git clone https://github.com/kubernetes-incubator/kubespray.git
cd kubespray
ln -s ../inventory/dev inventory/
meld inventory/sample/ inventory/dev/
```
