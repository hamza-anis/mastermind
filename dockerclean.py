from os import system
from sys import argv 

system("docker stop "+argv[1])
system("docker rm "+argv[1])