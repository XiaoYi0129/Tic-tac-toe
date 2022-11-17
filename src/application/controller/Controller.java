package application.controller;

import application.TicClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final int PLAY_1 = 1;
    private static final int PLAY_2 = 2;
    private static final int EMPTY = 0;
    private static final int BOUND = 90;
    private static final int OFFSET = 15;

    private TicClient ticClient;
    private int playerNum=0;


    @FXML
    private Pane base_square;

    @FXML
    private Rectangle game_panel;


    private static boolean TURN = true;
    //true is 1, false is 2

    private static final int[][] chessBoard = new int[3][3];
    private static final boolean[][] flag = new boolean[3][3];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //if(playerNum==0){
           //ticClient.sendPairCommand();
       // }else {
            game_panel.setOnMouseClicked(event -> {
                System.out.println("controller change panel");
                int x = (int) (event.getX() / BOUND);
                int y = (int) (event.getY() / BOUND);
                if(playerNum==0){
                    if(ticClient.sendPairCommand()){
                        System.out.println("Connect with opponent");
                        playerNum=ticClient.getPlayerNum();
                    }else {
                        System.out.println("NO opponent, controller can't put chess");
                    }
                }else {
                    if (refreshBoard(x, y)) {

                        String command = x + "," + y;

                        ticClient.sendCommand(command);
                        System.out.println("player" + TURN + " put chess on " + x + "," + y + " Now it's turn to player " + !TURN);
                        TURN = !TURN;
                    }
                }

            });
        //}


    }

    private boolean refreshBoard (int x, int y) {
        if(playerNum==1){
            if(TURN==true){
                if(chessBoard[x][y] == EMPTY){
                    chessBoard[x][y]=1;
                    drawChess();
                    System.out.println("player1 draw chess on"+x+","+y);

                    return true;
                }else {
                    System.out.println("player1 have been put a chess");
                    return false;//
                }
            }else {
                System.out.println("now it's turn to player2");
                return false;//
            }
        }
        if(playerNum==2){
            if(TURN==false){
                if(chessBoard[x][y] == EMPTY){
                    chessBoard[x][y]=2;
                    drawChess();
                    System.out.println("player2 draw chess on"+x+","+y);
                    return true;
                }else {
                    System.out.println("player2 have been put a chess");
                    return false;//
                }
            }else {
                System.out.println("now it's turn to player1");
                return false;//
            }
        }


        /*
        if (chessBoard[x][y] == EMPTY) {
            chessBoard[x][y] = TURN ? PLAY_1 : PLAY_2;
            drawChess();
            return true;
        }
        return false;*/
        return false;
    }

    private void drawChess () {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[0].length; j++) {
                if (flag[i][j]) {
                    // This square has been drawing, ignore.
                    continue;
                }
                switch (chessBoard[i][j]) {
                    case PLAY_1:
                        drawCircle(i, j);
                        break;
                    case PLAY_2:
                        drawLine(i, j);
                        break;
                    case EMPTY:
                        // do nothing
                        break;
                    default:
                        System.err.println("Invalid value!");
                }
            }
        }
    }

    private void drawCircle (int i, int j) {
        Circle circle = new Circle();
        base_square.getChildren().add(circle);
        circle.setCenterX(i * BOUND + BOUND / 2.0 + OFFSET);
        circle.setCenterY(j * BOUND + BOUND / 2.0 + OFFSET);
        circle.setRadius(BOUND / 2.0 - OFFSET / 2.0);
        circle.setStroke(Color.RED);
        circle.setFill(Color.TRANSPARENT);
        flag[i][j] = true;
    }

    private void drawLine (int i, int j) {
        Line line_a = new Line();
        Line line_b = new Line();
        base_square.getChildren().add(line_a);
        base_square.getChildren().add(line_b);
        line_a.setStartX(i * BOUND + OFFSET * 1.5);
        line_a.setStartY(j * BOUND + OFFSET * 1.5);
        line_a.setEndX((i + 1) * BOUND + OFFSET * 0.5);
        line_a.setEndY((j + 1) * BOUND + OFFSET * 0.5);
        line_a.setStroke(Color.BLUE);

        line_b.setStartX((i + 1) * BOUND + OFFSET * 0.5);
        line_b.setStartY(j * BOUND + OFFSET * 1.5);
        line_b.setEndX(i * BOUND + OFFSET * 1.5);
        line_b.setEndY((j + 1) * BOUND + OFFSET * 0.5);
        line_b.setStroke(Color.BLUE);
        flag[i][j] = true;
    }
    public void test(){

        System.out.println(66);
    }
    public void setTicClient(TicClient ticClient){
        this.ticClient=ticClient;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }
}
