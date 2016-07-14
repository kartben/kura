FROM fedora:24

MAINTAINER Jens Reimann <jreimann@redhat.com>

# Install base

RUN dnf -y install java-1.8.0-openjdk-headless

# Install dependencies

RUN dnf -y install net-tools

# Export port 80

EXPOSE 80

# Copy file

ADD target/*.rpm /

# Install Kura

RUN rpm -Uvh *.rpm

# Define entry point

CMD /usr/bin/kura-server