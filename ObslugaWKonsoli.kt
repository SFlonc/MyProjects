import org.jsoup.Jsoup
import java.io.File
enum class ExecutableInstruction{ CONTAINS, EXPORT }
data class Command(val instruction: ExecutableInstruction, val url:String, val parameter:String)
class RawCommand{
    var instruction=""
    var url=""
    var parameter=""
}
interface CommandExecutorInterface{
    fun exec(rawcommand: RawCommand)
    fun normalizeRawCommand(rawcommand: RawCommand):Command
}
class CommandsParser{
    fun parseCommand(text:String):RawCommand{
        val list=text.split(" ")//rozdziel na podstawie spacji
        return RawCommand().apply {
            instruction=list[0]
            url=list[1]
            var text=""
            for(i in 2..list.lastIndex)     text+="${list[i]} "
            parameter=text.trimEnd() //trimEnd() - utnij wszystkie puste znaki patrzac od konca
        }
    }
}
interface WebEngineInterface{
    fun contains(text:String):Boolean
    fun export(path:String)
}
class WebEngine(url:String):WebEngineInterface{
    private val response= Jsoup.connect(url).get().body().text() //dostaniemy cialo dokumentu
    override fun contains(text: String): Boolean=response.contains(text.toRegex())
    override fun export(path: String) {
        try{
            val file= File(path)
            file.printWriter().use { pw -> pw.print(response) }//use otworzy writera, zacznie wpisywac,
        }// jak skonczy to zakmnie plik
        catch (e:Exception){ println(e) }
    }
}
class CommandExecutor():CommandExecutorInterface{
    override fun exec(rawcommand: RawCommand) {
        val command=normalizeRawCommand(rawcommand)
        val webEngine=WebEngine(command.url)
        when(command.instruction){
            ExecutableInstruction.CONTAINS -> {
                val result=webEngine.contains(command.parameter)
                println(result)
            }
            ExecutableInstruction.EXPORT -> {
                webEngine.export(command.parameter)
            }
        }
    }
    override fun normalizeRawCommand(rawcommand: RawCommand): Command {
        val instr=rawcommand.instruction.uppercase()
        val url=rawcommand.url.lowercase()
        val execInstr=when(instr){
            "CONTAINS" -> ExecutableInstruction.CONTAINS
            "EXPORT" ->ExecutableInstruction.EXPORT
            else -> throw IllegalArgumentException()
        }
        return Command(execInstr, url, rawcommand.parameter)
    }
}
fun main() {
    val parser=CommandsParser()
    val executor=CommandExecutor()
    while(true){
        try {
            print("Command: ")
            val input=readLine().toString()
            if(input.uppercase()=="EXIT") break
            val rawCommand = parser.parseCommand(input)
            executor.exec(rawCommand)
        }
        catch(e:Exception){ println("Blad ! ${e.message}") }
    }
}