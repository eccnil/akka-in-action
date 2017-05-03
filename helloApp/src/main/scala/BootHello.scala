import akka.actor.{ActorSystem, Props}
import scala.concurrent.duration._

object BootHello extends App {

  val system = ActorSystem("hellokernel")
  val actor = system.actorOf(Props[HelloWorld])
  val config = system.settings.config
  val timer = config.getInt("helloworld.timer")
  system.actorOf(Props( new HelloWorldCaller(timer millis, actor)))
}