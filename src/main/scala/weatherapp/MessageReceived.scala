package weatherapp

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.ProcessBuilder
import java.lang.Process


class MessageReceived extends ListenerAdapter:


  override def onMessageReceived(messageEvent: MessageReceivedEvent): Unit =
    if !messageEvent.getAuthor.isBot && messageEvent.getMessage.getContentRaw.head == '!' then
      messageEvent.getMessage.reply(executeAction(messageEvent.getMessage.getContentRaw)).setSuppressEmbeds(true).queue()


  def executeAction(message: String): String =
    val commandText = message.trim.toLowerCase
    val verb        = commandText.takeWhile( _ != ' ' )
    val modifiers   = commandText.drop(verb.length).trim
    val action: String =
      verb match
        case "!weather"  => weatherReport(modifiers)
        case other       => "Invalid command."
    action


  def weatherReport(modifiers: String): String =
    try
      val path: String = "C:/Users/elias/vscodeProjects/python/fetchweatherdata/FetchWeatherData.py"
      val builder: ProcessBuilder = new ProcessBuilder("python", path, modifiers)
      val process: Process = builder.start()

      val reader: BufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream))
      val output: StringBuilder = new StringBuilder()
      output.append(reader.readLine())

      val exitCode: Int = process.waitFor()
      if exitCode == 0 then
        output.toString()
      else
        throw new RuntimeException("Error executing Python script. Exit code: " + exitCode)

    catch
      case e: Exception =>
        s"Error fetching weather information: ${e.getMessage}"




end MessageReceived

