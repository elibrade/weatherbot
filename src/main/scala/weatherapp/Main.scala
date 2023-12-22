package weatherapp
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent


@main def main() =
    val builder = JDABuilder.createDefault("YOUR_API_KEY")
    builder.enableIntents(GatewayIntent.MESSAGE_CONTENT)
    builder.addEventListeners(new MessageReceived)
    builder.build()

