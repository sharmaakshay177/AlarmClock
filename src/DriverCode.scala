
import Utils.{AlarmItemSetting, ThreadPlayer}
import Utils.SettingsUtil.DefaultAlarmTone
import Utils.PlayAlarm.{checkAlarmTime, playAlarm}
import Utils.DateUtil.time
import org.slf4j.LoggerFactory



object DriverCode extends App {

  val logger = LoggerFactory.getLogger(getClass)

  val demoAlarmItem = AlarmItemSetting("22:00",
    Some(DefaultAlarmTone),
    5,
    "please wake me up",
    None)
  val demoKey = "28:10:2020"

  val keySet = Set[String]("16:25","16:30","16:45")

  val alarmTime = "19:55"
//  val alarmTimeList = List("20:26","20:28","20:30")
//
//  val player = new Thread (new ThreadPlayer)
//  var runCheck = true
//  while(runCheck){
//    logger.info(s"time now is $time")
//    if(checkAlarmTime(time,alarmTime)){
//      logger.info("playing alarm")
//      playAlarm(1,isTime = true,player)
//      logger.info("Alarm Done for 1 minute and now stopped")
//      //runCheck = false
//      logger.info(" runCheck is false as playing multiple alarms")
//    }else {
//      logger.info(s"Putting this thread for sleep for 1 min as time now $time")
//      Thread.sleep(30000)
//    }
//  }
  val alarmTimeList = List("22:55","22:51","22:53","23:09","23:29")

  val alwaysRun = true
  while(alwaysRun){
    logger.info(s"time now is $time")
    if(alarmTimeList.contains(time)){
      logger.info(s"Time condition met and time now is $time")
      val player = new Thread(new ThreadPlayer(DefaultAlarmTone))
      logger.info("playing alarm")
      playAlarm(1, player)
      logger.info("Alarm Done for 1 minute and now stopped")
    }
  }

}
