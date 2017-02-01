package aia.testdriven

import akka.actor.{Actor, ActorRef, Props}

object SendingActor {
  //next couple of lines only to create actors OUTSIDE of any actor
  def props(receiver: ActorRef) =
    Props(new SendingActor(receiver)) //receiver is passed through the Props to te constructor of te Sendig Actor
  case class Event(id: Long)
  case class SortEvents(unsorted: Vector[Event]) //request message, immutable vector
  case class SortedEvents(sorted: Vector[Event]) //response message immutable vector
}
/**
  * Created by ecnil on 30/1/17.
  */
class SendingActor (receiver: ActorRef) extends Actor{
  import SendingActor._

  override def receive = {
    case SortEvents (unsorted) =>
      receiver ! SortedEvents(unsorted.sortBy(_.id))
  }

}
