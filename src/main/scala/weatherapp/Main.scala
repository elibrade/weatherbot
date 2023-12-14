package weatherapp
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent


@main def main() =
    val builder = JDABuilder.createDefault("MTE4NDYyODc1Mjk3ODgyNTI1OQ.GT7y5i.JUTu9HdGlkR0jDE5eFxq9QCtuHZTya6BI8d07c")
    builder.enableIntents(GatewayIntent.MESSAGE_CONTENT)
    builder.addEventListeners(new MessageReceived)
    builder.build()

