Role Name
=========

This role will upgrade operating system packages and reboot the host if there were kernel upgrades.

Requirements
------------

None.

Role Variables
--------------

None.

Dependencies
------------

None

Example Playbook
----------------

    - hosts: servers
      roles:
         - { role: ava.osupdate }

License
-------

BSD

Author Information
------------------

Csaba Szalai
