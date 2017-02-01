package aia.testdriven

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef, Props}

object FilteringActor {
  case class Event(id: Long)
  def props (nextActor: ActorRef, buffSize: Int) =  Props( new FilteringActor(nextActor, buffSize))
}

/**
  * Created by ecnil on 31/1/17.
  */
class FilteringActor (nextActor: ActorRef, buffSize: Int) extends Actor {
  import FilteringActor._
  var lastsMessages = Vector[Event]()

  override def receive: Receive = {
    case event: Event => {
      //filter event
      if (!lastsMessages.contains(event)){
        nextActor ! event
        lastsMessages = (lastsMessages :+ event).takeRight(buffSize)
      }
    }
  }
}
