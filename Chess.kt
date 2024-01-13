import java.util.*
import kotlin.math.min
class Master{
    val list1 = listOf(1,0, -1,0, 0,1, 0,-1, 1,1, 1,-1, -1,-1, -1,1)
}
class Knight{
    val list1 = listOf(1,2, 2,1, 2,-1, 1,-2, -1,-2, -2,-1, -2,1, -1,2)
}
class Bishop(x: Int, y: Int){
    val list1 = listOf(1,1, -1,1, -1,-1, 1,-1, min(8 - x, 8 - y), min(8 - x, y), min(x, y), min(x, 8 - y))
}
class Rook(x: Int, y: Int){
    val list1 = listOf(1,0, 0,1, -1,0, 0,-1, 8 - y, 8 - x, y, x)
}

val board = mutableMapOf(
    // starting chess board as a dictionary, makes for easy coordinates of pieces
    // 'mutable' so every spot can be updated
    "a8" to "R2", "b8" to "K2", "c8" to "B2", "d8" to "Q2", "e8" to "M2", "f8" to "B2", "g8" to "K2", "h8" to "R2",
    "a7" to "P2", "b7" to "P2", "c7" to "P2", "d7" to "P2", "e7" to "P2", "f7" to "P2", "g7" to "P2", "h7" to "P2",
    "a6" to " □", "b6" to " ■", "c6" to " □", "d6" to " ■", "e6" to " □", "f6" to " ■", "g6" to " □", "h6" to " ■",
    "a5" to " ■", "b5" to " □", "c5" to " ■", "d5" to " □", "e5" to " ■", "f5" to " □", "g5" to " ■", "h5" to " □",
    "a4" to " □", "b4" to " ■", "c4" to " □", "d4" to " ■", "e4" to " □", "f4" to "Q1", "g4" to " □", "h4" to " ■",
    "a3" to " ■", "b3" to " □", "c3" to " ■", "d3" to " □", "e3" to " ■", "f3" to " □", "g3" to " ■", "h3" to " □",
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
var ogX = 0; var ogY = 0; var recursion = 1; var pawnMove = false; var knightMove = false; var masterMove = false
var rookMove = false; var bishopMove = false
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
        pawnMove = true
        pawn(x, y)
        possible()
        intermission(x, y)
    } else if (board[selTemp].toString().contains("R")) {
        rookMove = true
        UM1(x, y)
        possible()
        rookMove = false
        intermission(x, y)
    } else if (board[selTemp].toString().contains("K")) {
        knightMove = true
        UM2(x, y)
        possible()
        intermission(x, y)
    } else if (board[selTemp].toString().contains("B")) {
        UM1(x, y)
        possible()
        intermission(x, y)
    } else if (board[selTemp].toString().contains("Q")) {
        UM1(x, y)
        rookMove = true
        UM1(x, y)
        rookMove = false
        possible()
        intermission(x, y)
    } else if (board[selTemp].toString().contains("M")) {
        masterMove = true
        UM2(x, y)
        possible()
        intermission(x, y)
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

fun possible(){
    if(!valid.containsValue(true)){
        println("Selected piece cannot move. Select another one.")
        start(currentPlayer)
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
                valid[find3] = false
            }
        }
        //promotion, potential bug mixing up a move with another pawn. Don't think so but noted.
        if (pawnMove && (y == 1 || y == 8)){
            pawnPromo(x,y)
        }
        pawnMove = false; knightMove = false; masterMove = false
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

fun pawnPromo(x: Int, y: Int){
    println("\nPawn promotion achieved!\nType the following for your moved to promote to:" +
            "\n'Q' for a queen. 'K' for a knight. 'B' for a bishop. 'R' for a rook.")
    when(readln().uppercase(Locale.getDefault())){
        "Q" -> board[xCoordNum(x) + y] = "Q$user"
        "K" -> board[xCoordNum(x) + y] = "K$user"
        "B" -> board[xCoordNum(x) + y] = "B$user"
        "R" -> board[xCoordNum(x) + y] = "R$user"
        else -> {
            println("Invalid promotion. Try again."); pawnPromo(x, y)
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

fun UM1(x: Int, y:Int){
    var brObj = listOf(1)
    if (rookMove){
        brObj = Rook(x, y).list1
    }
    else{
        brObj = Bishop(x, y).list1
    }
    when (recursion){
        1 -> universalMove(x, y, brObj[0], brObj[1], brObj[8])
        2 -> universalMove(x, y, brObj[2], brObj[3], brObj[9])
        3 -> universalMove(x, y, brObj[4], brObj[5], brObj[10])
        4 -> universalMove(x, y, brObj[6], brObj[7], brObj[11])
    }
    if (recursion != 4){
        recursion++
        UM1(x,y)
    }
    recursion = 1
}
fun universalMove(x: Int, y: Int, mY: Int, mX: Int, remaining: Int){
    for (i in 1..remaining){
        if (board[xCoordNum(x + (i * mX)) + (y + (i * mY))].toString().contains(user)){
            break
        }
        else if (board[xCoordNum(x + (i * mX)) + (y + (i * mY))].toString().contains(opponent)){
            valid[xCoordNum(x + (i * mX)) + (y + (i * mY))] = true
            break
        }
        else {
            valid[xCoordNum(x + (i * mX)) + (y + (i * mY))] = true
        }
    }
}

fun UM2(x: Int, y: Int){
    var lL = listOf(1)
    if(knightMove){
        lL = Knight().list1
    }
    else{
        lL = Master().list1
    }
    when (recursion){ //no need for error catch. maps just resort to a null
        // might have to recreate a map of spots so nulls don't stack, maybe not if the nulls don't do anything
        1 -> { valid[xCoordNum(x + lL[0]) + (y + lL[1])] = true
            checkSpot(x + lL[0], y + lL[1])
        }
        2 -> { valid[xCoordNum(x + lL[2]) + (y + lL[3])] = true
            checkSpot(x + lL[2], y + lL[3])
        }
        3 -> { valid[xCoordNum(x + lL[4]) + (y + lL[5])] = true
            checkSpot(x + lL[4], y + lL[5])
        }
        4 -> { valid[xCoordNum(x + lL[6]) + (y + lL[7])] = true
            checkSpot(x + lL[6], y + lL[7])
        }
        5 -> { valid[xCoordNum(x + lL[8]) + (y + lL[9])] = true
            checkSpot(x + lL[8], y + lL[9])
        }
        6 -> { valid[xCoordNum(x + lL[10]) + (y + lL[11])] = true
            checkSpot(x + lL[10], y + lL[11])
        }
        7 -> { valid[xCoordNum(x + lL[12]) + (y + lL[13])] = true
            checkSpot(x + lL[12], y + lL[13])
        }
        8 -> { valid[xCoordNum(x + lL[14]) + (y + lL[15])] = true
            checkSpot(x + lL[14], y + lL[15])
        }
    }
    if (recursion != 8){
        recursion++
        UM2(x,y)
    }
    recursion = 1
}// knight looping for some reason, does not cause harm though...

fun checkSpot(x: Int, y: Int){
    if(board[xCoordNum(x)+y].toString().contains(user)){
        valid[xCoordNum(x)+y] = false
    }
}

//----------------------------------------------------------------------------------
fun main() {
    start(currentPlayer)
}