import java.util.Scanner;

/**
 *
 * @author Navya Sogi
 * 
 */

public class maxconnect4 {
    public static GameBoard presentBoard = null;
    public static AiPlayer aiPlayer = null;
    public static Scanner reader = null;
    
    public static int CEILING = 99;
    public static final String TXT_COMPUTER = "computer.txt";
    public static final String TXT_HUMAN = "human.txt";
    public static int HUMAN_PLAYER;
    public static int COMPUTER_PLAYER;
    public static final int FIRST = 1;
    public static final int SECOND = 2;

    public enum GAME {
        INTERACTIVE,
        ONE_MOVE
    };

    public enum PARTCIPANT {
        HUMAN,
        COMPUTER
    };

    public static void main(String[] args){
        // check for the correct number of arguments
        if (args.length != 4) {
            System.out.println("Please enter four command-line arguments:\n"
                + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

            exit_function(0);
        }

        // parse the input arguments
        String startPhase = args[0].toString(); // the game mode
        String inputFile = args[1].toString(); // the input game file
        int depthLevel = Integer.parseInt(args[3]); // the depth level of the ai search

        //  initialize the game board with input from input file
        presentBoard = new GameBoard(inputFile);

        // create the Ai Player with present game board and depth level
        aiPlayer = new AiPlayer(depthLevel, presentBoard);

                
        switch (startPhase) {
        	case "interactive":
        		if (presentBoard.isBoardFull()) {
                    System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
                    exit_function(0);
                }
        		presentBoard.setGameMode(GAME.INTERACTIVE);
        		String moveNext = args[2].toString();
        		switch(moveNext) {
        			case "computer-next":
        				presentBoard.setFirstTurn(PARTCIPANT.COMPUTER);
                        ComputerInteractiveMove();
        				break;
        			case "human-next":
        				presentBoard.setFirstTurn(PARTCIPANT.HUMAN);
                        HumanInteractiveMove();
        				break;
        			default:
        				System.out.println("\n" + "value for 'next turn' is not recognized.  \n try again. \n");
                        exit_function(0);
        				break;
        		}
             
        		break;
        	case "one-move":
        		presentBoard.setGameMode(GAME.ONE_MOVE);
                String outputFileName = args[2].toString(); // the output game file
                ComputerOneMove(outputFileName);
        		break;
        	default:
        		System.out.println("\n" + startPhase + " is an unrecognized game mode \n try again. \n");
                exit_function(0);
        		break;
        
        }
    } // end of main()
    
    /**
     * This method makes one-move by a computer
     * 
     * @param outputFileName path for output file to save game current score
     */
    private static void ComputerOneMove(String outputFileName){
        // TODO Auto-generated method stub

        // variables to keep up with the game
        int nextMoveCol = 99; // the players choice of column to play
        boolean playMade = false; // set to true once a play has been made

        System.out.print("\nMaxConnect-4 game:\n");

        System.out.print("Game state before move:\n");

        // print the current game board
        presentBoard.printGameBoard();

        // print the current scores
        System.out.println("Score: Player-1 = " + presentBoard.getScore(FIRST) + ", Player-2 = " + presentBoard.getScore(SECOND)
            + "\n ");

        if (presentBoard.isBoardFull()) {
            System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
            return;
        }

        // ****************** this chunk of code makes the computer play

        int current_player = presentBoard.getCurrentTurn();

        // AI play - random play
        nextMoveCol = aiPlayer.findBestPlay(presentBoard);

        if (nextMoveCol == CEILING) {
            System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
            return;
        }

        // play the piece
        presentBoard.playPiece(nextMoveCol);

        // display the current game board
        System.out.println("move " + presentBoard.getPieceCount() + ": Player " + current_player + ", column "
            + (nextMoveCol + 1));

        System.out.print("Game state after move:\n");

        // print the current game board
        presentBoard.printGameBoard();

        // print the current scores
        System.out.println("Score: Player-1 = " + presentBoard.getScore(FIRST) + ", Player-2 = " + presentBoard.getScore(SECOND)
            + "\n ");

        presentBoard.printGameBoardToFile(outputFileName);

        // ************************** end computer play

    }

    /**
     * This method makes interactive move by computer
     * 
     */
    private static void ComputerInteractiveMove(){

        printGameboardWithScore();

        System.out.println("\n Computer's turn:\n");

        int nextMoveCol = CEILING; // the players choice of column to play

        // AI play - random play
        nextMoveCol = aiPlayer.findBestPlay(presentBoard);

        if (nextMoveCol == CEILING) {
            System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over.");
            return;
        }

        // play the piece
        presentBoard.playPiece(nextMoveCol);

        System.out.println("move: " + presentBoard.getPieceCount() + " , Player: Computer , Column: " + (nextMoveCol + 1));

        presentBoard.printGameBoardToFile(TXT_COMPUTER);

        if (presentBoard.isBoardFull()) {
            printGameboardWithScore();
            printFinalOutcome();
        } else {
            HumanInteractiveMove();
        }
    }

    /**
     * This method prints final outcome of the game.
     * 
     */
    
    private static void printFinalOutcome() {
        int human_score = presentBoard.getScore(maxconnect4.HUMAN_PLAYER);
        int comp_score = presentBoard.getScore(maxconnect4.COMPUTER_PLAYER);
        
        System.out.println("\n Final Result:");
        if(human_score > comp_score){
            System.out.println("\n Congratulations!! You won this game."); 
        } else if (human_score < comp_score) {
            System.out.println("\n You lost!! Good luck for next game.");
        } else {
            System.out.println("\n Game is tie!!");
        }
    }

    /**
     * This method makes interactive move by human .
     * 
     */
    private static void HumanInteractiveMove(){
        // TODO Auto-generated method stub
        printGameboardWithScore();

        System.out.println("\n Human's turn:\nKindly play your move here(1-7):");

        reader = new Scanner(System.in);

        int nextMoveCol = CEILING;

        do {
            nextMoveCol = reader.nextInt();
        } while (!isValidPlay(nextMoveCol));

        // play the piece
        presentBoard.playPiece(nextMoveCol - 1);

        System.out.println("move: " + presentBoard.getPieceCount() + " , Player: Human , Column: " + nextMoveCol);
        
        presentBoard.printGameBoardToFile(TXT_HUMAN);

        if (presentBoard.isBoardFull()) {
            printGameboardWithScore();
            printFinalOutcome();
        } else {
            ComputerInteractiveMove();
        }
    }

    private static boolean isValidPlay(int nextMoveCol) {
        if (presentBoard.isValidPlay(nextMoveCol - 1)) {
            return true;
        }
        System.out.println("Opps!!...Invalid column , Kindly enter column value between 1 to 7.");
        return false;
    }

    /**
     * This method displays current board state and score of each player.
     * 
     */
    public static void printGameboardWithScore() {
        System.out.print("Game state :\n");

        // print the current game board
        presentBoard.printGameBoard();

        // print the current scores
        System.out.println("Score: Player-1 = " + presentBoard.getScore(FIRST) + ", Player-2 = " + presentBoard.getScore(SECOND)
            + "\n ");
    }

    /**
     * This method is used when to exit the program prematurly.
     * 
     * @param value an integer that is returned to the system when the program exits.
     */
    private static void exit_function(int value) {
        System.out.println("exiting from MaxConnectFour.java!\n\n");
        System.exit(value);
    }
} // end of class connectFour
