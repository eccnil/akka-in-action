package aia.testdriven

import akka.actor.{Actor, ActorRef}
import akka.actor.Actor.Receive

object SilentActor{
  case class SilentMessage(data: String)
  case class GetState(receiver: ActorRef)
}

/**
  * Created by ecnil on 29/1/17.
  */
class SilentActor extends Actor{
  import SilentActor._
  var internalState = Vector[String]()
  def receive = {
    case SilentMessage (data) => {
      internalState = internalState :+ data
    }
    case GetState (receiver) => {
      receiver ! internalState
    }
  }
  def state = internalState
}
