# PV243 - Accommodation Reservation System

##### Add to standalone-full.xml in your Wildfly the following line:
````
<jms-queue name="dataQueue" entries="java:jboss/exported/jms/queue/dataQueue"/>
````
Edit link to docs: https://docs.google.com/forms/d/e/1FAIpQLSfDoOxwAgQix7tEWuSMG64KMR5ZbiNMU-IIgXs9eapSz2TFsw/formResponse

## Prerequisites

- JDK 8
- WildFly 10
- Keycloak 4.0.0.Beta3

### Start the Keycloak Server

    $KEYCLOAK_HOME/bin/standalone.sh -Djboss.socket.binding.port-offset=100

The URL for Keycloak server will be http://localhost:8180

Finally import : TODO

### Start and Configure the WildFly Server

- Download Keycloak Wildfly adapter 4.0.0.Beta3 from [keycloak downloads](https://www.keycloak.org/downloads.html)
- Extract Keycloak adapter into WILDFLY_HOME
- Start WildFly server
````
EAP_HOME/bin/standalone.sh
````
- To install the Keycloak adapter run following commands:
````
$WILDFLY_HOME/bin/jboss-cli.sh -c --file=$WILDFLY_HOME/bin/adapter-install.cli
$WILDFLY_HOME/bin/jboss-cli.sh -c --command=:reload
````
