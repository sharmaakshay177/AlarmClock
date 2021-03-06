package Utils

import Utils.DateUtil._
import Utils.SoundUtil._
import scala.concurrent.duration._

class ThreadPlayer(songName: String) extends Runnable{
  override def run(): Unit = {
    val resourcePath: String = getFileResource(songName)
    createPlayer(resourcePath).play()
  }
}

object PlayAlarm {
  // Modify this method to get fileName if multiple song file are there
  val alarmTone: String = getClass.getResource("/Avicii-Wake-Me-Up.wav").toString.substring(5)

  def checkAlarmTime(actualTime: String, alarmTime: String): Boolean = actualTime.equals(alarmTime)

  def playAlarm(duration: Int, threadPlayer: Thread):Unit ={
    // duration will be taken in minutes
    val deadline = duration.minutes.fromNow

    threadPlayer.start()
    while(deadline.hasTimeLeft()){}
    threadPlayer.stop()
  }

  // 1 min = 60,000 millis
  def minuteToMillis(minute: Int): Int = minute * 60000

  //TODO = add interrupt variable also so that it can stop check for passing an interruption in a thread
  def snoozeTimerAlarmPlay(snoozeTime: Int, duration: Int, thread: Thread, fun:(Int, Thread) => Unit): Unit ={
    fun(duration, thread)
    Thread.sleep(minuteToMillis(snoozeTime))
  }


  def createAlarmString(min: Int, hour: Int): String = getTimeString(min.toString, hour.toString)

}

object Local extends App{
  val timeNow = time
  println(time)

  val hour = "23"
  val minute = "28"
  val timeString = getTimeString(minute, hour)
  println(timeString)

  val fileName = getClass.getResource("/Avicii-Wake-Me-Up.wav").toString.substring(5)
  val threadPlayer = new Thread(new ThreadPlayer("Avicii-Wake-Me-Up.wav"))

  val deadline = 30.seconds.fromNow
  var checkTimer = true
  //threadPlayer.start()
  while(checkTimer){
    println(time)
    if(time.equals(timeString)){
      println(time)
      threadPlayer.start()
      while(deadline.hasTimeLeft()){
        println("still time left")
      }
      checkTimer = false
      println("making stop outer while loop")
      threadPlayer.stop()
      println("stopping the thread")
    }
    //else Thread.sleep(60000)
    //add a thread.sleep  minute by minute
  }

}
