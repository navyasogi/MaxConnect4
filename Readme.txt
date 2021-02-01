	Name : Navya R Sogi
	UTA ID : 1001753085
	Programming Language : JAVA
	
	Code Structure :
	Class : maxconnect4, AiPlayer, GameBoard
	 

        maxconnect4 :
	Functions : main(), HumanInteractiveMove(), ComputerOneMove(), printGameboardWithScore(), ComputerInteractiveMove(), printFinalOutcome(),isValidPlay().
	
        HumanInteractiveMove() -> this function takes input from user and makes the next move for human player.
        ComputerOneMove() -> this function is called when the computer have to make a move for a one-move mode game.
        printGameboardWithScore() -> this function display the current score and the board's current state.
        ComputerInteractiveMove() -> this function is called when the computer have to make a move for an interactive mode game.
        printFinalOutcome() -> this function prints the final score and declares winner or tie.
        isValidPlay() -> this function determines if a play is valid or not

        GameBoard :
        Functions : getScore(), getCurrentTurn(), getGameBoard(), printGameBoard(), getThreeCount(), getTwoCount(), getSingleCount()

        getScore() -> this function takes the current score and send it to printBoardAndScore() to print score.
        getCurrentTurn() -> this function traces the current turn.
        getGameBoard() -> this function returns the whole gameboard as a dual indexed array.
        printGameBoard() -> this function prints the current board state.
        getThreeCount() -> this function returns the possible triplets which result in a win for the player given as an argument.
	getTwoCount() -> this function returns the possible doubles which result in a win for the player given as an argument.
	getSingleCount() -> this function returns the possible single which result in a win for the player given as an argument.

        AiPlayer :
        Functions : findBestPlay(), Get_Min_Value(), Get_Max_Value(), EvaluationFunction()

        findBestPlay() -> this function makes the decision to make a move for the computer using the min and max value from the below given two functions
        Get_Min_Value() -> this function calculates the min value.
        Get_Max_Value() -> this function calculates the max value.
	EvaluationFunction() -> this is an Evaluation Function.


        Detailed information about each function of different classes are given in individual .java source file itself. Kindly check there.


	How to run Code :
	Compile using :
	        javac maxconnect4.java
                javac GameBoard.java
                javac AiPlayer.java
	
    	Execute using :
            (for interactive mode) :
	        java maxconnect4 interactive [input_file] [computer-next/human-next] [depth]  
            for example: java maxconnect4 interactive input1.txt computer-next 8 or java maxconnect4 interactive input1.txt human-next 8 

            (for one-move mode) :
                java maxconnect4 one-move [input_file] [output_file] [depth]  
            for example: java maxconnect4 one-move input2.txt output.txt 8

    	Command to retrieve execution time:
            time java maxconnect4 one-move [input_file] [output_file] [depth]  
            for example:time java maxconnect4 one-move input2.txt output.txt 8


    	Execution time chart :
      
       Depth: 1
        user 0m0.109s
       Depth: 2
        user 0m0.128s
       Depth: 3
        user 0m0.129s
       Depth: 4
        user 0m0.160s
       Depth: 5
        user 0m0.309s
       Depth: 6
        user 0m0.678s
       Depth: 7
        user 0m1.094s
       Depth: 8
        user 0m2.526s
       Depth: 9
        user 0m6.607s
       Depth: 10      
        user 0m26.528s


       Files : - Submitted a ZIPPED directory called assignment3_nxs3085.zip. Task 1 code is contained in maxconnect4 folder.
       







	
