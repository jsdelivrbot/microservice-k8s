# Ansible playbooks for AVA-API project

## Requirements 
* Linux/Mac OS based machine
* Ansible >= 2.4 installed
* python-netaddr package installed
* ansible-galaxy install -r requirements.yml

## Nameserver config in ava.domains role
Please check the hard-coded values in the files/resolv.conf of ava.domains role.

## Prepare kubernetes cluster vms for kubespray deployment
Run `ansible-playbook -i inventory/yourcluster prepare-cluster.yml -b -v` and check the results.

## Infra config with site.yml
Running the site.yml playbook will configure all the VMs in your infrastructure based on your inventory.
`ansible-playbook -i inventory/yourcluster site.yml -b -v`



