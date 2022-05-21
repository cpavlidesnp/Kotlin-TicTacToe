private var board = Board(0)
private var isGameActive:Boolean = true
private const val player1: String = "X"
private const val player2: String = "O"
private var currentPlayer = player1
private var player_input_x = ""
private var player_input_y = ""
private var checkFinished: Boolean = false
private var playAgain: String = ""

fun main() {
    board = Board(3)
    board.printBoard()
    while (isGameActive) {
        println("Current move count is ${board.moveCount}")
        println("$currentPlayer plays, provide vertical position")

        player_input_x = readLine()?:""

        println("$currentPlayer plays, provide horizontal position")

        player_input_y = readLine()?:""


        if (board.makeMove(currentPlayer, player_input_x, player_input_y)) {
            checkFinished = board.checkResult(player_input_x.toInt()-1, player_input_y.toInt()-1, currentPlayer)
            if (!checkFinished){
                currentPlayer = switchPlayer(currentPlayer)
            }else{
                println("The game has ended with winner: ${board.winner}")
                println("Would you like to play again (y/n)")
                playAgain = readLine() ?: ""
                if (playAgain=="y" || playAgain=="yes") {
                    board.resetBoard()
                }else{
                    isGameActive = false
                }

            }
        }
    }
}

fun validateNumber(number:String):Boolean{
    return try{
        number.toInt()
        true
    }catch (e: java.lang.Exception){
        false
    }
}

fun switchPlayer(currentPlayer: String): String{
    return if (currentPlayer==player1){
        player2
    }else{
        player1
    }
}