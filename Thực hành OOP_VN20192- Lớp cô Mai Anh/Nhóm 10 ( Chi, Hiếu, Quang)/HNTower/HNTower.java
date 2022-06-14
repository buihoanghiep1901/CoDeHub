package HNTower;


import java.util.Optional;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HNTower extends BorderPane  {

	private static Color BACKGROUND_COLOR = Color.BLACK; 
    private static Color BORDER_COLOR = Color.WHITE;
    private static Color DISK_COLOR = Color.RED;
    private static Color MOVE_DISK_COLOR = Color.rgb(180,180,255);
    public int n;
    private Canvas canvas;    
    private GraphicsContext g; 
    Stage stage = new Stage();
    private int status;  

    private static final int GO = 0;         
    private static final int PAUSE = 1;    
    private static final int STEP = 2;     
    private static final int RESTART = 3;  
    private int[][] tower;
    private int[] towerHeight;
    private int moveDisk;
    private int moveTower;


    private Button runPauseButton;  // 3 control buttons for controlling the animation
    private Button nextStepButton;
    private Button startOverButton;

    public HNTower () {
    	
    	this.setStyle("-fx-background-color: #1c1c1c");
		TextInputDialog input = new TextInputDialog("");
		input.setTitle("Number of Disk!");
		input.setContentText("Please enter a number: ");
		Optional<String> result = input.showAndWait();
		n = Integer.parseInt(result.get());

        canvas = new Canvas(430,143);
        g = canvas.getGraphicsContext2D();

        runPauseButton = new Button("Run");
        runPauseButton.setOnAction( e -> doStopGo() );
        runPauseButton.setMaxWidth(10000);
        runPauseButton.setPrefWidth(10);
        nextStepButton = new Button("Next Step");
        nextStepButton.setOnAction( e -> doNextStep() );
        nextStepButton.setMaxWidth(10000);
        nextStepButton.setPrefWidth(10);
        startOverButton = new Button("Start Over");
        startOverButton.setOnAction( e -> doRestart() );
        startOverButton.setMaxWidth(10000);
        startOverButton.setPrefWidth(10);
        startOverButton.setDisable(true);
        HBox bottom = new HBox( runPauseButton, nextStepButton, startOverButton);
        bottom.setStyle("-fx-border-color: rgb(100,0,0); -fx-border-width: 4px 0 0 0");
        HBox.setHgrow(runPauseButton, Priority.ALWAYS);
        HBox.setHgrow(nextStepButton, Priority.ALWAYS);
        HBox.setHgrow(startOverButton, Priority.ALWAYS);
        
        BorderPane root = new BorderPane(canvas);
        root.setBottom(bottom);
        root.setStyle("-fx-border-color: rgb(100,0,0); -fx-border-width: 4px");
        
        Scene scene = new Scene(root);
		stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        new AnimationThread().start();  
    } 


    synchronized private void doStopGo() {
        if (status == GO) {  // Animation is running.  Pause it.
            status = PAUSE;
            nextStepButton.setDisable(false);
            runPauseButton.setText("Run");
        }
        else {  // Animation is paused.  Start it running.
            status = GO;
            nextStepButton.setDisable(true);  // disabled when animation is running
            runPauseButton.setText("Pause");
        }
        notify();  // Wake up the thread so it can see the new status value!
    }
    
    synchronized private void doNextStep() {
        status = STEP;
        notify();
    }

    synchronized private void doRestart() {
        status = RESTART;
        notify();
    }


    private class AnimationThread extends Thread {
        AnimationThread() {
            setDaemon(true);
        }
        public void run() {
            while (true) {
                Platform.runLater( () -> {
                    runPauseButton.setText("Run");
                    runPauseButton.setDisable(false);
                    nextStepButton.setDisable(false);
                    startOverButton.setDisable(true);
                });
                setUpProblem(); 
                status = PAUSE;
                checkStatus(); 
                Platform.runLater( () -> startOverButton.setDisable(false) );
                try {
                    solve(n,0,1,2);  
                        
                    status = PAUSE;
                    Platform.runLater( () -> { // Make sure user can only click startOver.
                        runPauseButton.setDisable(true);
                        nextStepButton.setDisable(true);
                        startOverButton.setDisable(false);
                    } );
                    checkStatus(); 
                }
                catch (IllegalStateException e) {
                    
                }
            }
        }
    }


    synchronized private void checkStatus() {
        while (status == PAUSE) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
    
        if (status == RESTART)
            throw new IllegalStateException("Restart");
        
    }


  
    synchronized private void setUpProblem() {
        moveDisk= 0;
        tower = new int[3][n];
        for (int i = 0; i < n; i++)
            tower[0][i] = n - i;
        towerHeight = new int[3];
        towerHeight[0] = n;
        Platform.runLater( () -> drawInitialFrame() );
    }

    private void solve(int disks, int from, int to, int spare) {
        if (disks == 1)
            moveOne(from,to);
        else {
            solve(disks-1, from, spare, to);
            moveOne(from,to);
            solve(disks-1, spare, to, from);
        }
    }


  
    synchronized private void moveOne(int fromStack, int toStack) {
        moveDisk = tower[fromStack][towerHeight[fromStack]-1];
        moveTower = fromStack;
        delay(120);
        towerHeight[fromStack]--;
        putDisk(MOVE_DISK_COLOR,moveDisk,moveTower,towerHeight[fromStack]);
        delay(80);
        putDisk(BACKGROUND_COLOR,moveDisk,moveTower,towerHeight[fromStack]);
        delay(80);
        moveTower = toStack;
        putDisk(MOVE_DISK_COLOR,moveDisk,moveTower,towerHeight[toStack]);
        delay(80);
        putDisk(DISK_COLOR,moveDisk,moveTower,towerHeight[toStack]);
        tower[toStack][towerHeight[toStack]] = moveDisk;
        towerHeight[toStack]++;
        moveDisk = 0;
        if (status == STEP)
            status = PAUSE;
        checkStatus();
    }


    synchronized private void delay(int milliseconds) {
        try {
            wait(milliseconds);
        }
        catch (InterruptedException e) {
        }
    }

    private void putDisk(Color color, int disk, int t, int h) {
        Platform.runLater( () -> {
            g.setFill(color);
            if (color == BACKGROUND_COLOR) {
                g.fillRoundRect(75+140*t - 5*disk - 6, 116-12*h - 1, 10*disk+12, 12, 10, 10);
            }
            else {
               
                
                g.fillRoundRect(75+140*t - 5*disk - 5, 116-12*h, 10*disk+10, 10, 10, 10);
            }
            g.setFill(BORDER_COLOR);
       	 	g.fillRect(72,18,5,110);
            g.fillRect(212,18,5,110);
            g.fillRect(352,18,5,110);
        });
    }


    
    private void drawInitialFrame() {
    	g.setFill(BACKGROUND_COLOR);
        g.fillRect(0,0,430,143);
        g.setFill(DISK_COLOR);
      
        for (int t = 0; t < 3; t++) {
            for (int i = 0; i < towerHeight[t]; i++) {
                int disk = tower[t][i];
              
                g.fillRoundRect(75+140*t - 5*disk - 5, 116-12*i, 10*disk+10, 10, 10, 10);
            }
        }
        
        g.setFill(BORDER_COLOR);
        g.fillRect(10,128,130,5);
        g.fillRect(150,128,130,5);
        g.fillRect(290,128,130,5);
        g.fillRect(72,18,5,110);
        g.fillRect(212,18,5,110);
        g.fillRect(352,18,5,110);
    }


	
}