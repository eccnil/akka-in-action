import akka.actor.{Actor, ActorLogging, ActorRef}
import scala.concurrent.duration._

class HelloWorldCaller (timer: FiniteDuration, actor: ActorRef) extends Actor with ActorLogging {
  case class TimerTick(msg: String)

  override def preStart(): Unit = {
    super.preStart()
    implicit val ec = context.dispatcher
    context.system.scheduler.schedule(
      timer,
      timer,
      self,
      new TimerTick("everybody")
    )
  }

  def receive = {
    case msg: String => log.info("received {}", msg)
    case tick: TimerTick => actor ! tick.msg
  }
}