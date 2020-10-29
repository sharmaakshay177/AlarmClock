package Utils

import java.io.{BufferedInputStream, FileInputStream}

import javazoom.jl.player.Player
import javazoom.jl.player.advanced.AdvancedPlayer

object SoundUtil {

  def getFileResource(fileName: String): String ={
    getClass.getResource(s"/$fileName").toString.substring(5)
  }

  def createPlayer(file: String): Player={
    val bufferInputStream = new BufferedInputStream(
      new FileInputStream(file)
    )
    val player = new Player(bufferInputStream)
    player
  }

  def playSound(player: Player):Unit = player.play()
  def stopSound(player: Player):Unit = player.close()

  def createAdvancePlayer(file: String): Unit={
    val bufferedInputStream = new BufferedInputStream(
      new FileInputStream(file)
    )
    val advancedPlayer = new AdvancedPlayer(bufferedInputStream)
  }

}

object localTesting extends App{

  val actualPath = getClass.getResource("/Avicii-Wake-Me-Up.wav").toString
  println(actualPath)
  val audioFileName = getClass.getResource("/Avicii-Wake-Me-Up.wav").toString.substring(5)
  println(audioFileName)
  val player = SoundUtil.createPlayer(audioFileName)
  player.play()
  while(!player.isComplete){
    println(player.getPosition)
  }
  player.close()
  //createAdvancePlayer(audioFileName)
}
