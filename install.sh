#!/usr/bin/env bash
 
# Use single quotes instead of double quotes to make it work with special-character passwords
PASSWORD='coderslab'
HOSTNAME='student.edu'

echo
echo "Witaj w CodersLab!"
echo
echo "Ten skrypt zaktualizuje Twój system, zainstaluje kilka niezbędnych programów,"
echo "których będziesz potrzebować podczas kursu oraz skonfiguruje bazę danych MySQL."
echo "W tym czasie na ekranie pojawi się wiele komunikatów."
echo "ABY INSTALACJA SIĘ POWIODŁA MUSISZ MIEĆ DOSTĘP DO INTERNETU W TRAKCIE TRWANIA "
echo "INSTALACJI!"
read -n1 -r -p "Naciśnij dowolny klawisz, by kontynuować."

mkdir ~/.coderslab

# pausing updating grub as it might triger ui
sudo apt-mark hold grub*
echo
echo "Aktualizuję system..."

# update / upgrade
sudo apt update
sudo apt -y upgrade
echo
echo "Instaluję narzędzia systemowe..."


sudo add-apt-repository ppa:linuxuprising/java
sudo apt-get install -y oracle-java14-installer

sudo apt-get install -y openjfx

sudo apt install -y maven

sudo add-apt-repository ppa:cwchien/gradle -y
sudo apt install -y gradle

# install all used tools
sudo apt install -y curl vim git
sudo apt-get install -y libmysql-java

#install some additional tools
sudo apt-get install -y geany
sudo apt-get install -y mc

  
echo
echo "Instaluję bazę danych MySQL..."

# install mysql and give password to installer
sudo debconf-set-selections <<< "mysql-server mysql-server/root_password password $PASSWORD"
sudo debconf-set-selections <<< "mysql-server mysql-server/root_password_again password $PASSWORD"
sudo apt install -y mysql-server

echo "[mysqld]" >> /etc/mysql/my.cnf
echo "collation-server = utf8_unicode_ci" >> /etc/mysql/my.cnf
echo "init-connect='SET NAMES utf8'" >> /etc/mysql/my.cnf
echo "character-set-server = utf8" >> /etc/mysql/my.cnf
echo "default-time-zone='+02:00'" >> /etc/mysql/my.cnf

sudo snap install mysql-workbench-community
sudo snap connect mysql-workbench-community:password-manager-service :password-manager-service

echo "Dla pewności -- ponownie aktualizuję system..."
# update and upgrade all packages
sudo apt update -y
sudo apt upgrade -y

# energy stuff
sudo apt install -y tlp tlp-rdw
sudo tlp start
sudo apt-get install -y preload


# unpausing updating grub
sudo apt-mark unhold grub*
