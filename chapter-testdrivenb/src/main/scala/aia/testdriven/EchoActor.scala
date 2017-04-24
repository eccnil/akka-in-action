package aia.testdriven

import akka.actor.Actor

/**
  * Created by ecnil on 1/2/17.
  */
class EchoActor extends Actor {
  override def receive = {
    case msg => {
      sender() ! msg
    }
  }
}
