package DataBaseUtils

import Utils.AlarmItemSetting
import Utils.SettingsUtil.DefaultAlarmTone
import net.openhft.chronicle.map._
import org.slf4j.LoggerFactory

object ChronicleMapUtil {
  //TODO = wrap all under Either case for return
  //TODO = create this method to be generic for the class of arguments
  def createChronicMapBuilder[A,B](dbName: String,
                                   avgKey: String,
                                   avgValue: AlarmItemSetting,
                                   entries: Long): ChronicleMapBuilder[String,AlarmItemSetting] ={
    val builder = ChronicleMapBuilder.of(classOf[String], classOf[AlarmItemSetting])
      .name(dbName)
      .averageKey(avgKey)
      .averageValue(avgValue)
      .entries(entries)
    builder
  }

  def createChronicMapFromBuilder[Key, Val](chronicleMapBuilder: ChronicleMapBuilder[Key, Val]): ChronicleMap[Key,Val] = chronicleMapBuilder.create()

  def enterRecordInMap[Key, Val](key: Key,
                                 value: Val,
                                 chronicleMap: ChronicleMap[Key, Val]):Unit ={
    chronicleMap.put(key, value)
  }
  //TODO = Update the method with Either condition
  def getRecordFromMap[Key, Val](key: Key,
                                 chronicleMap: ChronicleMap[Key, Val]): Val ={
    chronicleMap.get(key)
  }

}


object ChronicleMapTestingBasic extends App{
  import ChronicleMapUtil._
  val logger = LoggerFactory.getLogger(getClass)

  //creating InMemory Chronicle Map (will be removed after the process terminated)
  // need to change it to persisted chronicle map later
  val demoAlarmItem = AlarmItemSetting("22:00",
                                      Some(DefaultAlarmTone),
                                      5,
                                      "please wake me up",
                                      None)
  logger.info("created a demo AlarmItemSetting")
  val demoKey = "22:41"

  logger.info("created a demo Key")
  //using Builder approach for building chronicle map
  val chronicleMapBuilder = ChronicleMapBuilder
    .of(classOf[String], classOf[AlarmItemSetting])
    .name("Alarm-items-map")
    .averageKey("28:10:2020")
    .averageValue(demoAlarmItem)
    .entries(100)

  val builderFromMethod = createChronicMapBuilder("demo-creation",
    demoKey,
    demoAlarmItem,
    100)

  val mapFromBuilder = createChronicMapFromBuilder(builderFromMethod)

  enterRecordInMap("29:10:2020",
    AlarmItemSetting("22:00",
      Some(DefaultAlarmTone),
      5,
      "please wake me up",
      None),
    mapFromBuilder)

  val getRecord = getRecordFromMap("29:10:2020",mapFromBuilder)

  println(getRecord)


}
