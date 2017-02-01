package aia.testdriven

import akka.actor.{Actor, ActorSystem}
import akka.actor.Actor.Receive
import akka.testkit.TestKit
import org.scalatest.{MustMatchers, WordSpecLike}

import scala.util.Random

/**
  * Created by ecnil on 30/1/17.
  */
class SendingActor02Test extends TestKit (ActorSystem ("testsystem")) //extends from testKit and provides an actor system for testing
  with WordSpecLike
  with MustMatchers
  with StopSystemAfterAll{

  "a Sending Actor" must {
    "send a message to another actor when it has finished processing" in {
      import SendingActor._
      val props = SendingActor.props(testActor) // receiver is passed to props
      val sendingActor = system.actorOf(props, "sendingActor")
      val size = 1000
      val maxInclusive = 100000

      def randomEvents() = (0 until size).map{ _ =>
        Event(Random.nextInt(maxInclusive))
      }.toVector

      val unsorted = randomEvents()
      val sortEvents = SortEvents (unsorted)
      sendingActor ! sortEvents

      expectMsgPF() { //verifies (part of) msg instead of providing exact expectation
        case SortedEvents(events) =>
          events.size must be(size)
          unsorted.sortBy(_.id) must be(events)
      }
    }
    "filter out particular messages" in {
      import FilteringActor._
      val props = FilteringActor.props(testActor, 5) // receiver is passed to props
      val filter = system.actorOf(props, "filterActor")
      filter ! Event(1)
      filter ! Event(2)
      filter ! Event(1)
      filter ! Event(3)
      filter ! Event(1)
      filter ! Event(4)
      filter ! Event(5)
      filter ! Event(5)
      filter ! Event(6)
      val eventIds = receiveWhile(){ //receives messages until the case stateme does not match anyone
        case Event(id) if id <= 5 => id
      }
      eventIds must be(List(1,2,3,4,5)) //assert that duplicates are not in the list
      expectMsg (Event (6))
    }
    "filter out particular messages using expectNoMsg" in {
      import FilteringActor._
      val props = FilteringActor.props(testActor, 5)
      val filter = system.actorOf(props, "filterActor2")
      filter ! Event(1)
      filter ! Event(2)
      expectMsg(Event(1))
      expectMsg(Event(2))
      filter ! Event(1)
      expectNoMsg //slow
      filter ! Event(3)
      expectMsg(Event(3))
      expectNoMsg //pain slow
    }
  }
}
