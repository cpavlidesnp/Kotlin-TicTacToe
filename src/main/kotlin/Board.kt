class Board(private val depth: Int) {

    private var emptySpace = "_"
    private var board = Array(depth){Array(depth){emptySpace}}
    var moveCount: Int = 0
    var winner: String = ""

    fun resetBoard(){
        emptySpace = "_"
        board = Array(depth){Array(depth){emptySpace}}
        moveCount = 0
        winner = ""
        printBoard()
    }

    fun printBoard(){
        for (r in board){
            for (c in r) {
                print("| $c |")
            }
            println()
        }
    }


    fun printBoard_2(){

        board.forEach { row ->
            row.forEach { element ->
                print(element)
            }
            println()
        }
    }


    fun makeMove(player: String, input_x: String, input_y: String): Boolean {

        return try {

            val x = input_x.toInt()-1
            val y = input_y.toInt()-1

            if (checkPositionValid(x, y)) {

                if (checkPositionEmpty(x, y)) {
                    board[x][y] = player
                    printBoard()
                    moveCount++
                    true
                } else {
                    println("You cant play at that position")
                    false
                }
            } else {
                println("The position is invalid")
                false
            }
        }catch (e: java.lang.Exception){
            println("Invalid input, expecting number")
            false

        }

    }

    private fun checkPositionEmpty(x: Int, y: Int): Boolean{
        return board[x][y]==emptySpace
    }


    private fun checkPositionValid(x: Int, y: Int): Boolean{
        try {
            board[x][y]
        } catch (e: ArrayIndexOutOfBoundsException) {
            return false
        }
        return true
    }

    fun checkResult(x:Int, y:Int, player:String): Boolean{
        return if (checkWinner(x, y, player)){
            winner = player
            true
        }else if (moveCount==(depth*depth)){
            winner = "DRAW"
            true
        }else{
            false
        }
    }

    private fun checkWinner(x:Int,y:Int,player:String): Boolean {

        //check horizontal
        for (i in 0 until depth){
            if (board[x][i]!=player){
                break
            }
            if (i==depth-1){
                return true
            }
        }

        //check vertical
        for (i in 0 until depth){
            if (board[i][y]!=player){
                break
            }
            if (i==depth-1){
                return true
            }
        }
        //check diagonal
        if (x==y) {
            for (i in 0 until depth) {
                if (board[i][i] != player) {
                    break
                }
                if (i == depth - 1) {
                    return true
                }
            }
        }

        //check reverse diagonal
        if (x+y==depth-1) {
            for (i in 0 until depth) {
                if (board[i][(depth-1)-i] != player) {
                    break
                }
                if (i == depth - 1) {
                    return true
                }
            }
        }

        return false
    }

}