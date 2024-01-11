val board = mutableMapOf(
    // starting chess board as a dictionary, makes for easy coordinates of pieces
    // 'mutable' so every spot can be updated
    "a8" to "R2", "b8" to "K2", "c8" to "B2", "d8" to "Q2", "e8" to "M2", "f8" to "B2", "g8" to "K2", "h8" to "R2",
    "a7" to "P2", "b7" to "P2", "c7" to "P2", "d7" to "P2", "e7" to "P2", "f7" to "P2", "g7" to "P2", "h7" to "P2",
    "a6" to " □", "b6" to " ■", "c6" to " □", "d6" to " ■", "e6" to " □", "f6" to " ■", "g6" to " □", "h6" to " ■",
    "a5" to " ■", "b5" to " □", "c5" to " ■", "d5" to " □", "e5" to " ■", "f5" to " □", "g5" to " ■", "h5" to " □",
    "a4" to " □", "b4" to " ■", "c4" to " □", "d4" to " ■", "e4" to " □", "f4" to " ■", "g4" to " □", "h4" to " ■",
    "a3" to " ■", "b3" to " □", "c3" to " ■", "d3" to "R1", "e3" to " ■", "f3" to " □", "g3" to " ■", "h3" to " □",
    "a2" to "P1", "b2" to "P1", "c2" to "P1", "d2" to "P1", "e2" to "P1", "f2" to "P1", "g2" to "P1", "h2" to "P1",
    "a1" to "R1", "b1" to "K1", "c1" to "B1", "d1" to "Q1", "e1" to "M1", "f1" to "B1", "g1" to "K1", "h1" to "R1"
)
const val fs = false // false shorthand. reason: formatting would be wacky with my organized map
val valid = mutableMapOf(
// available moving spots by coordinates
    "a8" to fs, "b8" to fs, "c8" to fs, "d8" to fs, "e8" to fs, "f8" to fs, "g8" to fs, "h8" to fs,
    "a7" to fs, "b7" to fs, "c7" to fs, "d7" to fs, "e7" to fs, "f7" to fs, "g7" to fs, "h7" to fs,
    "a6" to fs, "b6" to fs, "c6" to fs, "d6" to fs, "e6" to fs, "f6" to fs, "g6" to fs, "h6" to fs,
    "a5" to fs, "b5" to fs, "c5" to fs, "d5" to fs, "e5" to fs, "f5" to fs, "g5" to fs, "h5" to fs,
    "a4" to fs, "b4" to fs, "c4" to fs, "d4" to fs, "e4" to fs, "f4" to fs, "g4" to fs, "h4" to fs,
    "a3" to fs, "b3" to fs, "c3" to fs, "d3" to fs, "e3" to fs, "f3" to fs, "g3" to fs, "h3" to fs,
    "a2" to fs, "b2" to fs, "c2" to fs, "d2" to fs, "e2" to fs, "f2" to fs, "g2" to fs, "h2" to fs,
    "a1" to fs, "b1" to fs, "c1" to fs, "d1" to fs, "e1" to fs, "f1" to fs, "g1" to fs, "h1" to fs
    // formatting?! // size 65? from rook movement?
)
// watch for weird false and add additions of the valid spots


var currentPlayer = true; var user = "0"; var opponent = "0"; var selTemp = "null"
var ogX = 0; var ogY = 0; var recursion = 1
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

fun start(player: Boolean) {
    when (player) {
        true -> {
            user = "1"; opponent = "2"
        }

        false -> {
            user = "2"; opponent = "1"
        }
    }
    printBoard()
    println("\n\nPlayer $user select the corresponding coordinate of the piece to move.")
    try {
        selTemp = readln()
        if (board[selTemp].toString().contains(user)) {
            moving(
                xCoordAlpha(selTemp.substring(startIndex = 0, endIndex = 1)),
                selTemp.substring(startIndex = 1, endIndex = 2).toInt()
            )
            // x,y coordinate
        } else {
            start(currentPlayer)
        }
    } catch (e: Exception) {
        println("Not a valid coordinate.")
        start(currentPlayer)
    }
}

//x coord translators -----------------------------------------------------
fun xCoordAlpha(x: String): Int {

    when (x) {
        "a" -> return 1; "b" -> return 2; "c" -> return 3; "d" -> return 4
        "e" -> return 5; "f" -> return 6; "g" -> return 7; "h" -> return 8
    }
    return 0
}

fun xCoordNum(x: Int): String {

    when (x) {
        1 -> return "a"; 2 -> return "b"; 3 -> return "c"; 4 -> return "d"
        5 -> return "e"; 6 -> return "f"; 7 -> return "g"; 8 -> return "h"
    }
    return "null"
}

// ------------------------------------------------------------------------
fun moving(x: Int, y: Int) {
    if (board[selTemp].toString().contains("P")) {
        pawn(x, y)
        intermission(x, y)
    } else if (board[selTemp].toString().contains("R")) {
        rook(x, y)
        intermission(x, y)
    } else if (board[selTemp].toString().contains("K")) {

    } else if (board[selTemp].toString().contains("B")) {

    } else if (board[selTemp].toString().contains("Q")) {

    } else if (board[selTemp].toString().contains("M")) {

    }

}

fun intermission(x: Int, y: Int) {
    ogX = x; ogY = y
    println("Rad. Where would you like to move?")
    try {
        selTemp = readln()
        endGame(
            xCoordAlpha(selTemp.substring(startIndex = 0, endIndex = 1)),
            selTemp.substring(startIndex = 1, endIndex = 2).toInt()
        )
    } catch (e: Exception) {
        println("Not a valid coordinate.")
        intermission(x, y)
    }
}

fun endGame(x: Int, y: Int) {
    if (valid[xCoordNum(x) + y] == true) {
        board[xCoordNum(x) + y] = board[xCoordNum(ogX) + ogY].toString()
        board[xCoordNum(ogX) + ogY] = replace(ogX, ogY)
        currentPlayer = !currentPlayer
        for (j in 8 downTo 1) {
            val find2 = j.toString()
            for (i in 97..104) {
                val find1 = i.toChar().toString()
                val find3 = find1 + find2
                valid[find3] = fs
            }
        }
        start(currentPlayer)
    } else {
        println("Invalid spot to move. Starting turn again.")
        start(currentPlayer)
    }
}

fun replace(x: Int, y: Int): String {
    return if (y % 2 == 1) {
        if (x % 2 == 0) {
            " □"
        } else {
            " ■"
        }
    } else {
        if (x % 2 == 1) {
            " □"
        } else {
            " ■"
        }
    }
}

// ---------------------------------------------------------------------------------

var mod1 = 0; var mod2 = 0
// coordinate translator to keys

fun pawn(x: Int, y: Int) {
    val pawnTemp = xCoordNum(x)
    when (user) {
        "1" -> {
            mod1 = 1; mod2 = 2
            if (y != 2) {
                mod2 = 0
            }
        }

        "2" -> {
            mod1 = -1; mod2 = -2
            if (y != 7) {
                mod2 = 0
            }
        }
    }
    if (!board[pawnTemp + (y + mod1)].toString().contains("2") || !board[pawnTemp + (y + mod1)].toString()
            .contains("1")
    ) {
        valid[pawnTemp + (y + mod1)] = true
    }
    if (!board[pawnTemp + (y + mod2)].toString().contains("2") || !board[pawnTemp + (y + mod2)].toString()
            .contains("1")
    ) {
        valid[pawnTemp + (y + mod2)] = true
    }
    valid[pawnTemp + (y)] = false
    if (x < 7) {
        if (board[xCoordNum(x + 1) + (y + mod1)].toString().contains(opponent)) {
            valid[xCoordNum(x + 1) + (y + mod1)] = true
        }
    }
    if (x > 0) {
        if (board[xCoordNum(x - 1) + (y + mod1)].toString().contains(opponent)) {
            valid[xCoordNum(x + 1) + (y + mod1)] = true
        }
    }
}
// merged movement system for both rook and bishop paths, uses multipliers 'm1,m2' to determine directions
// multipliers:
// as 1, further top right most direction
// as 0 , rook, limits to only North, South, East, West directions as multiplier forces
// as -1 pushes path down and/or left direction
// addition strictly to these
// and addition the could be positive or negative to continuosly forge a path one tile at a time.
fun universalMove(x: Int, y: Int, m1: Int, m2: Int, remaining: Int){
    for (i in 1..remaining){
        if (board[xCoordNum(x + (i * m2)) + (y + (i * m1))].toString().contains(user)){
            break
        }
        else if (board[xCoordNum(x + (i * m2)) + (y + (i * m1))].toString().contains(opponent)){
            valid[xCoordNum(x + (i * m2)) + (y + (i * m1))] = true
            break
        }
        else {
            valid[xCoordNum(x + (i * m2)) + (y + (i * m1))] = true
        }
    }
}

fun rook(x: Int, y:Int){
    when (recursion){
        1 -> universalMove(x, y, 1, 0, 8 - y)
        2 -> universalMove(x, y, 0, 1, 8 - x)
        3 -> universalMove(x, y, -1, 0, y)
        4 -> universalMove(x, y, 0, -1, x)
    }
    if (recursion != 4){
        recursion++
        rook(x,y)
    }
    recursion = 1
}

//----------------------------------------------------------------------------------
fun main() {
    start(currentPlayer)
}
// get some classes/ objects
// when expression
