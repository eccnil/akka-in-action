package aia.testdriven

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.WordSpecLike

/**
  * Created by ecnil on 1/2/17.
  */
class EchoActorTest extends TestKit (ActorSystem("testsystem"))
  with WordSpecLike
  with ImplicitSender
  with StopSystemAfterAll{

  "an echo actor" must {
    "Reply with the same message it receives without ask" in {
      val echo = system.actorOf(Props[EchoActor], "echo2")
      echo ! "some Message"
      expectMsg("some Message")
    }
  }

}
