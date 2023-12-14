package weatherapp

// import net.dv8tion.jda.api.{AccountType, JDABuilder}
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter


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



  def weatherReport(location: String): String =
      if location.nonEmpty then
          s"https://www.ilmatieteenlaitos.fi/saa/$location"
      else
          "You must specify the location."



end MessageReceived

