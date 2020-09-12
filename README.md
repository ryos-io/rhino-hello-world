## Rhino Sample Project

Sample project to play around or as basis to build upon. To check out the project:

```shell script
$ git clone git@github.com:ryos-io/rhino-hello-world.git
```

to build the project, use :

```shell script
$ cd rhino-hello-world
$ mvn clean install
```

Import the project to your IDE from existing resources. Before you run the sample simulations, start InfluxDB and Grafana by hitting the docker-compose command:

```shell script
$ docker-compose up
```

You can access Grafana on http://localhost:3000/ with the default username:password = admin:admin.

<img width="667" alt="Screenshot 2020-09-13 at 00 22 16" src="https://user-images.githubusercontent.com/1160613/93005992-3ba74800-f557-11ea-876a-163e5f573f97.png">

After the first login on Grafana, there is no measurement yet. Now, you can go back to your IDE and run the **ExtractInformationWithMapTest** :

<img width="921" alt="Screenshot 2020-09-13 at 00 26 46" src="https://user-images.githubusercontent.com/1160613/93006052-d6a02200-f557-11ea-959e-bc6df9299d97.png">

Rhino will create a new Grafana dashboard for the simulation execution, and you can observe the incoming simulation execution metrics:
<img width="1164" alt="Screenshot 2020-09-13 at 00 29 33" src="https://user-images.githubusercontent.com/1160613/93006084-38f92280-f558-11ea-88ea-fd52f21d8740.png">
