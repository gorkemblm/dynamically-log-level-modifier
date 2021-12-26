# How it's work dynamically-log-level-modifier?

The project aims to dynamically change the log level during runtime.

When you wake up the main thread, 3 different threads also stand up.

Since a local http server is created in the project, we change the level changes by rest-call.

We make the change using a POST method.

endpoint: localhost:8080/log/change-level

This method takes 2 different parameters.

1 - loggerName : requests the classpath of the class to which the log level will change.

2 - logLevel : it specifies which level we want to convert to.

Usage images are below;


# PATH : httpserver
Default log levels for *httpserver*:
![Screenshot from 2021-12-26 19-33-41](https://user-images.githubusercontent.com/83100550/147414497-c886e54c-99ae-4b55-bd50-86aff06b3f72.png)

Rest call for *httpserver*:
![Screenshot from 2021-12-26 19-41-25](https://user-images.githubusercontent.com/83100550/147414711-21e370ad-a033-4e64-a8bc-4e6f013e5cee.png)

After changing the log level for *httpserver*:
![Screenshot from 2021-12-26 19-34-04](https://user-images.githubusercontent.com/83100550/147414504-2de2fa96-0f37-4e96-9603-27dd867df9d8.png)

# PATH : firstchildrenpath
Default log levels for *firstchildrenpath*:
![Screenshot from 2021-12-26 19-40-19](https://user-images.githubusercontent.com/83100550/147414687-2dbdd1c0-de55-4a94-a900-045701899e7b.png)

Rest call for *firstchildrenpath*:
![Screenshot from 2021-12-26 19-40-32](https://user-images.githubusercontent.com/83100550/147414688-e503cc34-4280-473c-ba20-82c07c03ab97.png)

After changing the log level for *firstchildrenpath*:
![Screenshot from 2021-12-26 19-40-46](https://user-images.githubusercontent.com/83100550/147414690-75cf53d9-ecdc-49b3-891e-b331507d2e7d.png)

# PATH : secondchildrenpath
Default log levels for *secondchildrenpath*:
![Screenshot from 2021-12-26 19-42-50](https://user-images.githubusercontent.com/83100550/147414746-ab734036-bbe5-468e-aa0d-c0a82c2a199f.png)

Rest call for *secondchildrenpath*:
![Screenshot from 2021-12-26 19-42-56](https://user-images.githubusercontent.com/83100550/147414747-a973be4b-ae22-4f4f-ab70-d95d3ec059be.png)

After changing the log level for *secondchildrenpath*:
![Screenshot from 2021-12-26 19-43-03](https://user-images.githubusercontent.com/83100550/147414748-27f5a684-bf82-462f-957f-b041b0c33c76.png)

