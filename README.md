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
