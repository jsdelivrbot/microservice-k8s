Role Name
=========

This role will create a volume group and a logical volume formatted to ext4 on attached ephemeral devices and mount the LVM under a specified mount path. By default it will use /dev/sdb to create an VG&LVM on top of it and mount it under /var/lib/docker.

Acts as a prepartion for docker, kubernetes local disk based persistent volumes backend and Elasticsearch deployments.

Requirements
------------

None.

Role Variables and default values
--------------

Volume Group to be created on all ephemeral disks (all attached disks except main OS disk /dev/sda)
  volume_group: vgcontainers

Logical Volume to be create on VG.
  logical_volume: lvdocker

Mount path where the LVM will be mounted.
  mount_path: /var/lib/docker

Block devices to create the VG on:
blk_devices: /dev/sdb
You can also list several devices here separated by commas "/dev/sdb,/dev/sdc".

Dependencies
------------

None.

Example Playbook
----------------

Including an example of how to use this role to prepare a kubernetes node's docker:

    - hosts: kube-node
      roles:
         - { role: ava.disks }

License
-------

BSD

Author Information
------------------

Csaba Szalai
