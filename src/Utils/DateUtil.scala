package Utils

import java.text.SimpleDateFormat
import java.util.Calendar

object DateUtil {
  private val hour12Format = new SimpleDateFormat("hh")
  private val hour24Format = new SimpleDateFormat("HH")
  private val minuteFormat = new SimpleDateFormat("mm")
  private val amPmFormat = new SimpleDateFormat("a")
  private val dateFormat = new SimpleDateFormat("dd:MM:yyyy")

  def completeTimeString: String = Calendar.getInstance.getTime.toString

  def date: String ={
    val timeNow = Calendar.getInstance.getTime
    dateFormat.format(timeNow)
  }
  def dateByDateMonthYearSeparately: (String, String, String) ={
    val timeNow = Calendar.getInstance.getTime
    val dateArray = dateFormat.format(timeNow).split(":")
    (dateArray(0),dateArray(1),dateArray(2))
  }

  def getTimeString(minute: String, hour: String): String = s"$hour:$minute"

  def time: String ={
    val timeNow = Calendar.getInstance.getTime
    s"${hour24Format.format(timeNow)}:${minuteFormat.format(timeNow)}"
  }

  def timeByMinuteAndHour: (String, String) ={
    val timeNow = Calendar.getInstance.getTime
    val hours = hour24Format.format(timeNow)
    val minutes = minuteFormat.format(timeNow)
    (hours, minutes)
  }

  def day: String ={
    val timeNow = Calendar.getInstance.getTime.toString
    timeNow.split(" ")(0)
  }

  def month: String ={
    val timeNow = Calendar.getInstance.getTime.toString
    timeNow.split(" ")(1)
  }

}
