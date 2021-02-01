import java.util.*;

/**
 * This is the AiPlayer class. It simulates a minimax player for the max connect four game. 
 *
 * @author Navya Sogi
 * 
 */

public class AiPlayer {

    public int depth_level;
    public GameBoard gameBoardShallow;

    /**
     * The constructor essentially does nothing except instantiate an AiPlayer object.
     * 
     * @param currentGame
     * 
     */
    public AiPlayer(int depth, GameBoard currentGame) {
        this.depth_level = depth;
        this.gameBoardShallow = currentGame;
    }

    /**
     * This method makes the decision to make a move for the computer using 
     * the min and max value from the below given two functions.
     * 
     * @param currentGame The GameBoard object that is currently being used to play the game.
     * @return an integer indicating which column the AiPlayer would like to play in.
     */
    public int findBestPlay(GameBoard currentGame){
        int playChoice = maxconnect4.CEILING;
        if (currentGame.getCurrentTurn() == maxconnect4.FIRST) {
            int v = Integer.MAX_VALUE;
            for (int i = 0; i < GameBoard.COLUMNS; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
                    nextMoveBoard.playPiece(i);
                    int value = Get_Max_Value(nextMoveBoard, depth_level, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (v > value) {
                        playChoice = i;
                        v = value;
                    }
                }
            }
        } else {
            int v = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.COLUMNS; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextMoveBoard = new GameBoard(currentGame.getGameBoard());
                    nextMoveBoard.playPiece(i);
                    int value = Get_Min_Value(nextMoveBoard, depth_level, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (v < value) {
                        playChoice = i;
                        v = value;
                    }
                }
            }
        }
        return playChoice;
    }

    /**
     * This method calculates the min value.
     * 
     * @param gameBoard The GameBoard object that is currently being used to play the game.
     * @param depth_level depth to which computer will search for making best possible next move
     * @param alpha_value maximum utility value for MAX player
     * @param beta_value maximum utility value for MIN player 
     * @return an integer indicating which column the AiPlayer would like to play in.
     */

    private int Get_Min_Value(GameBoard gameBoard, int depth_level, int alpha_value, int beta_value) {
      if (!gameBoard.isBoardFull() && depth_level > 0) {
            int v = Integer.MAX_VALUE;
            for (int i = 0; i < GameBoard.COLUMNS; i++) {
                if (gameBoard.isValidPlay(i)) {
                    GameBoard board4NextMove = new GameBoard(gameBoard.getGameBoard());
                    board4NextMove.playPiece(i);
                    int value = Get_Max_Value(board4NextMove, depth_level - 1, alpha_value, beta_value);
                    if (v > value) {
                        v = value;
                    }
                    if (v <= alpha_value) {
                        return v;
                    }
                    if (beta_value > v) {
                        beta_value = v;
                    }
                }
            }
            return v;
        } else {
            // this is a terminal state
            //return gameBoard.getScore(maxconnect4.SECOND) - gameBoard.getScore(maxconnect4.FIRST);
        	return EvaluationFunction(gameBoard);
        }
    }

    /**
     * This method calculates the max value.
     * 
     * @param gameBoard The GameBoard object that is currently being used to play the game.
     * @param depth_level depth to which computer will search for making best possible next move
     * @param alpha_value maximum utility value for MAX player
     * @param beta_value maximum utility value for MIN player 
     * @return an integer indicating which column the AiPlayer would like to play in.
     */

    private int Get_Max_Value(GameBoard gameBoard, int depth_level, int alpha_value, int beta_value){
       if (!gameBoard.isBoardFull() && depth_level > 0) {
            int v = Integer.MIN_VALUE;
            for (int i = 0; i < GameBoard.COLUMNS; i++) {
                if (gameBoard.isValidPlay(i)) {
                    GameBoard board4NextMove = new GameBoard(gameBoard.getGameBoard());
                    board4NextMove.playPiece(i);
                    int value = Get_Min_Value(board4NextMove, depth_level - 1, alpha_value, beta_value);
                    if (v < value) {
                        v = value;
                    }
                    if (v >= beta_value) {
                        return v;
                    }
                    if (alpha_value < v) {
                        alpha_value = v;
                    }
                }
            }
            return v;
        } else {
            // this is a terminal state
            //return gameBoard.getScore(maxconnect4.SECOND) - gameBoard.getScore(maxconnect4.FIRST);
        	return EvaluationFunction(gameBoard);
        }
    }
    /**
     * This is Evaluation Function. If node is full and player2 Wins its ends Highest value
     * If Player1 wins it sens least value.
     * When depth is reached it calculates value giving weighted average to 4,3,2
     * 4 has weight of 1000
     * 3 has weight of 50
     * 2 has weight of 10
     * @param currentGame The GameBoard object that is currently being used to
     * play the game.
     * @return an integer showing the value of that node
     */
    private int EvaluationFunction(GameBoard currentGame)
    {
        int result = 0;
        if (currentGame.isBoardFull()) {
            if((currentGame.getScore(2) - currentGame.getScore(1)) > 0){
                return 1000000000;
            } else if((currentGame.getScore(2) - currentGame.getScore(1)) == 0){
                return 0;
            } else {
                return -1000000000;
            }
        } else {

            result = (currentGame.getScore(1) * 1000) + (currentGame.getThreeCount(1) * 50) + (currentGame.getTwoCount(1) * 10)
                    - (currentGame.getScore(2) * 1000) - (currentGame.getThreeCount(2) * 50) - (currentGame.getTwoCount(2) * 10);
            //System.out.println("Eval: " + -result);
        }
        return (-result);
    }

}
