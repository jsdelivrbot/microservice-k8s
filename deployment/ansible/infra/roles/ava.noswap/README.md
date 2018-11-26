Role Name
=========

This role will remove swap from the host. To be used for Elasticsearch VMs and K8s nodes.

Requirements
------------

None.

Role Variables
--------------

swap_volgroup: "VolGroup00-LogVol01"

Dependencies
------------

None.

Example Playbook
----------------

Including an example of how to use your role (for instance, with variables passed in as parameters) is always nice for users too:

    - hosts: servers
      roles:
         - { role: ava.noswap }

License
-------

BSD

Author Information
------------------

Csaba Szalai
