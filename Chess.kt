val board = mutableMapOf(
    // starting chess board as a dictionary, makes for easy coordinates of pieces
    // 'mutable' so every spot can be updated
    "a8" to "R2", "b8" to "K2", "c8" to "B2", "d8" to "Q2", "e8" to "M2", "f8" to "B2", "g8" to "K2", "h8" to "R2",
    "a7" to "P2", "b7" to "P2", "c7" to "P2", "d7" to "P2", "e7" to "P2", "f7" to "P2", "g7" to "P2", "h7" to "P2",
    "a6" to " □", "b6" to " ■", "c6" to " □", "d6" to " ■", "e6" to " □", "f6" to " ■", "g6" to " □", "h6" to " ■",
    "a5" to " ■", "b5" to " □", "c5" to " ■", "d5" to " □", "e5" to " ■", "f5" to " □", "g5" to " ■", "h5" to " □",
    "a4" to " □", "b4" to " ■", "c4" to " □", "d4" to " ■", "e4" to " □", "f4" to " ■", "g4" to " □", "h4" to " ■",
    "a3" to " ■", "b3" to " □", "c3" to " ■", "d3" to " □", "e3" to " ■", "f3" to " □", "g3" to " ■", "h3" to " □",
    "a2" to "P1", "b2" to "P1", "c2" to "P1", "d2" to "P1", "e2" to "P1", "f2" to "P1", "g2" to "P1", "h2" to "P1",
    "a1" to "R1", "b1" to "K1", "c1" to "B1", "d1" to "Q1", "e1" to "M1", "f1" to "B1", "g1" to "K1", "h1" to "R1"
)
var currentPlayer = 1; var user = "0"; var opponent = "0";

fun printBoard() {

    for (j in 8 downTo 1) {
        val find2 = j.toString()
        println()
        for (i in 97..104) {
            val find1 = i.toChar().toString()
            val find3 = find1 + find2
            print(board[find3] + " ")
        }

    }
}

fun start(player: Int){
    when (player){
        1 -> {
            user = "1"; opponent = "2"
        }
        2 -> {
            user = "2"; opponent = "1"
        }
    }
    println("\n\nPlayer $user select the corresponding coordinate of the piece to move.");
    try {
        println(board[readLine()])
    }
    catch (e: Exception){
        println("Not a valid coordinate.")
    }

}

fun main(){
    printBoard()
    start(currentPlayer)
}
// get some classes/ objects
// when expression
