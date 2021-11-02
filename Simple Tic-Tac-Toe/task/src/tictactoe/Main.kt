package tictactoe

var counter = 1
var gameOn = true
var letter = 'X'

var board = mutableListOf(
    mutableListOf('-'),
    mutableListOf('|', ' ', ' ', ' ', '|'),
    mutableListOf('|', ' ', ' ', ' ', '|'),
    mutableListOf('|', ' ', ' ', ' ', '|'),
    mutableListOf('-')
)

fun main() {

    printBoard()

    do {
        letter = if (counter % 2 == 0) 'O' else 'X'
        enterCoordinates()
        if(!checkWinner()) draw() else gameOn = false
        printBoard()
    } while (gameOn)

    if(counter < 10) {
        println("$letter wins")
    } else println("draw")

}

/**
 * Prints the board.
 */
fun printBoard() {
    for (i in board) {
        if (i.joinToString("") == "-") {
            repeat(9) { print("-") }
            println()
        } else {
            println(i.joinToString(" "))
        }
    }
}

fun enterCoordinates() {
    print("Enter the coordinates: ")

    try {
        val (row, col) = readLine()!!.split(' ').map {
            it.toInt()
        }

        if (row !in 1..3 || col !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            return enterCoordinates()
        }

        if (board[row][col] == 'X' || board[row][col] == 'O') {
            println("This cell is occupied! Choose another one!")
            return enterCoordinates()
        } else board[row][col] = letter

    } catch (e: Exception) {
        println("You should enter numbers!")
        return enterCoordinates()
    }
}

fun checkWinner(): Boolean {

    for(i in 1..3) {
        // Rows
        if(board[i][1] == letter && board[i][2] == letter && board[i][3] == letter) {
            return true
        }

        if(board[1][i] == letter && board[2][i] == letter && board[3][i] == letter) {
            return true
        }
    }

    if(board[1][1] == letter && board[2][2] == letter && board[3][3] == letter) {
        return true
    }

    if(board[1][3] == letter && board[2][2] == letter && board[3][1] == letter) {
        return true
    }

    return false
}

fun draw() {
    if(counter == 9) {
        gameOn = false
    }
    counter++
}





