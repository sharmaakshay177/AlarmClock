
import DataBaseUtils.ChronicleMapUtil.{createChronicMapBuilder, createChronicMapFromBuilder, enterRecordInMap, getRecordFromMap}
import Utils.{AlarmItemSetting, ThreadPlayer}
import Utils.SettingsUtil.DefaultAlarmTone
import Utils.PlayAlarm.playAlarm
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

  logger.info("creating a local database of individual alarm setting")
  logger.info("creating chronicle map")
  val chronicleMapBuilder = createChronicMapBuilder("local-alarm-item.db",
                                                  "00:00",
                                                    demoAlarmItem,
                                                    100)

  val chronicleMap = createChronicMapFromBuilder(chronicleMapBuilder)

  val alarmItem1 = AlarmItemSetting("13:29",
                                    Some("Born-To-Shine.wav"),
                                    5,
                                    "Testing alarm 1",
                                    None)
  val alarmItem2 = AlarmItemSetting("13:27",
                                    Some("Avicii-Wake-Me-Up.wav"),
                                    5,
                                    "Testing alarm 1",
                                    None)

  logger.info("putting in both the objects into the map")
  enterRecordInMap("13:29",alarmItem1,chronicleMap)
  enterRecordInMap("13:27",alarmItem2,chronicleMap)

  val alarmTimeList = List("22:55","22:51","22:53","23:09","23:29",
                           "13:29","13:27")

  //TODO = Add a scheduler or a better condition handler here
  val alwaysRun = true
  while(alwaysRun){
    logger.info(s"time now is $time")
    if(alarmTimeList.contains(time)){
      logger.info("getting record back at the match and getting songName")
      val songName = {
        val record = {
          getRecordFromMap(time, chronicleMap) match {
            case Some(item: AlarmItemSetting) => item
          }
        }
        record.ringTone match {
          case Some(name: String) => name
          case None => "No Song Found"
        }
      }
      logger.info(s"Time condition met and time now is $time")
      val player = new Thread(new ThreadPlayer(songName))
      logger.info("playing alarm")
      playAlarm(1, player)
      logger.info("Alarm Done for 1 minute and now stopped")
    }else{
      logger.info("Putting thread to sleep for 10 sec")
      Thread.sleep(10000)
    }
  }
}
