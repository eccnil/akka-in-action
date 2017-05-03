How to lunch this.
----------------

using docker:

- first terminal `docker run --name server -v $PWD:/app -it hseeberger/scala-sbt`
- second terminal `docker run --name client -v $PWD:/app --link server -it hseeberger/scala-sbt`

on the server:

```scala
cd /app

sbt console

scala> :paste
// Entering paste mode (ctrl-D to finish)
val conf = """
akka {
  actor {
    provider = "akka.remote.RemoteActorRefProvider"
  } 
  remote {
    enabled-transports = ["akka.remote.netty.tcp"]
    netty.tcp {
      hostname = "0.0.0.0"
      port = 2551
    } 
  }
} 
"""
import com.typesafe.config._
import akka.actor._
val config = ConfigFactory.parseString(conf)

val backend = ActorSystem("backend", config) //rename to "frontend" for the client

// Exiting paste mode, now interpreting.
```

the url is: [akka.tcp://backend@server:2551/user/simple] 