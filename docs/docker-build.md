#### Setting up with Docker (without docker-compose usage)

You can build a Docker image of the JanusGraph visualizer with the included `Dockerfile`.
This will use the current version of the `main` branch of the source GitHub repository.
The Docker image can be built by calling the `docker build -f full.Dockerfile` command, for example:

```sh
docker build --tag=janusgraph-visualizer:latest -f full.Dockerfile .
```

If you had already built node project on your host then you can create a Docker image faster by using `Dockerfile` instead of `full.Dockerfile`: 

```sh
docker build --tag=janusgraph-visualizer:latest .
```

The image can also be downloaded from Docker hub: [`janusgraph/janusgraph-visualizer:latest`](https://hub.docker.com/r/janusgraph/janusgraph-visualizer).

```sh
docker pull janusgraph/janusgraph-visualizer:latest
```

The Docker image can then be run by calling `docker run` and exposing the necessary ports for communication. See [Docker's documentation](https://docs.docker.com/engine/reference/commandline/run/) for more options on how to run the image.

```sh
# if you built the image yourself
docker run --rm -d -p 3000:3000 -p 3001:3001 --name=janusgraph-visualizer --network=host janusgraph-visualizer:latest
# if you downloaded from Docker Hub
docker run --rm -d -p 3000:3000 -p 3001:3001 --name=janusgraph-visualizer --network=host janusgraph/janusgraph-visualizer:latest
```
Note that `--network=host` is not needed if you don't run your gremlin server in the host machine. 

* Open the browser and navigate to
```sh
http://localhost:3001
```

The Docker container can be stopped by calling `docker stop janusgraph-visualizer`.

