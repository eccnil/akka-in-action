package aia.testdriven

import org.scalatest.{MustMatchers, WordSpecLike}
import akka.testkit.{TestActorRef, TestKit}
import akka.actor._


class SilentActor01Test extends TestKit (ActorSystem ("testsystem")) //extends from testKit and provides an actor system for testing
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll {

  "A Silent Actor" must {
    "change state when it receives a message, single threaded" in {
      import SilentActor._ //import the menssages

      val silentActor = TestActorRef[SilentActor] //creates a testActorRef for single thread
      silentActor ! SilentMessage("hi")
      silentActor.underlyingActor.state must (contain("hi")) //gets the underlying actor and assets the state
    }
    "change state when int receives a message, multi-threaded" in {
      import SilentActor._
      val silentActor = system.actorOf(Props[SilentActor], "s3") // test system used to create an actor
      silentActor ! SilentMessage ("whisper1")
      silentActor ! SilentMessage ("whisper2")
      silentActor ! GetState(testActor) //test actor is created by testKit and ¿its me?
      expectMsg(Vector("whisper1","whisper2"))
    }
  }
}