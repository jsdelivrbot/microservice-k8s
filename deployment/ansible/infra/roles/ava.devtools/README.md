Role Name
=========

Install default packages for development (git, vim, netstat, etc).

Requirements
------------

None.

Role Variables
--------------

devtools_packages: (current default values)
  - git
  - mc
  - telnet
  - vim
  - nano


Dependencies
------------

None.

Example Playbook
----------------

Including an example of how to use your role (for instance, with variables passed in as parameters) is always nice for users too:

    - hosts: servers
      roles:
         - { role: ava.devtools }

License
-------

BSD

Author Information
------------------

Csaba Szalai
