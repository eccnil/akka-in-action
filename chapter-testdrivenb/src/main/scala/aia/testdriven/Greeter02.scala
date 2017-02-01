package aia.testdriven

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

object Greeter02 {
  def props (listener: Option[ActorRef] = None) = Props( new Greeter02(listener))
}
/**
  * Created by ecnil on 1/2/17.
  */
class Greeter02 (listener: Option[ActorRef]) extends Actor with ActorLogging{
  override def receive = {
    case Greeting (who) => {
      val message = "Hello " + who +"!"
      log.info(message)
      listener.foreach(_ ! message) //optionally sends to the listener
    }
  }

}
