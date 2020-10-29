package Utils

case class AlarmItemSetting(alarmTime: String,
                            ringTone: Option[String],
                            snoozeTime: Int,
                            label: String,
                            repeat: Option[Any])

object SettingsUtil {
  val DefaultAlarmTone: String = "Avicii-Wake-Me-Up.wav"
  val defaultAlarmRingDuration: Int = 1 // will be used in minutes
}
