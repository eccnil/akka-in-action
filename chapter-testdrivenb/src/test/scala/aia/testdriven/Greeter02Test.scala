package aia.testdriven
import akka.testkit.{CallingThreadDispatcher, EventFilter, TestKit}
import akka.actor.{ActorSystem, Props, UnhandledMessage}
import com.typesafe.config.ConfigFactory
import org.scalatest.WordSpecLike


class Greeter02Test extends TestKit(ActorSystem("testsystem"))
  with WordSpecLike
  with StopSystemAfterAll {

  "the greeter (2)" must {
    "say hello world! gen a Greeting (workd) is setn to it" in {
      val props = Greeter02.props(Some(testActor))
      val greeter = system.actorOf(props, "greeter0201")
      greeter ! Greeting("World")
      expectMsg("Hello World!")
    }
    "say something else and see what happens" in {
      val props = Greeter02.props(Some(testActor))
      val greeter = system.actorOf(props, "greeter0202")
      system.eventStream.subscribe(testActor, classOf[UnhandledMessage])
      greeter ! "World"
      expectMsg(UnhandledMessage("World", system.deadLetters, greeter))
      expectNoMsg()
    }
  }

}
