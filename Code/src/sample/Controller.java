package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static sample.Perseverance.*;

import javafx.scene.image.Image;


/**
 * The type Controller.
 */
public class Controller {

    private static int timer;
    @FXML
    private ImageView flyImage;
    @FXML
    private Text currentStage1;
    @FXML
    private ImageView mainRover ;
    @FXML
    private ImageView thrusterAttachment ;
    @FXML
    private ImageView backShell;
    @FXML
    private ImageView heatShield;
    @FXML
    private Text velocityText;
    @FXML
    private  Text altitudeText;
    @FXML
    private Text distanceText;
    @FXML
    private Text timeToNextPhaseText;
    @FXML
    private Text timeUntilLandingText;
    @FXML
    private ImageView parachute;
    @FXML
    private Group thrusters;
    @FXML
    private Label MissionCompleted;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView layOut;

    private Random rand = new Random();

    private ArrayList<ImageView> allWind = new ArrayList<>();

    private static int min = 300;
    private static int max = 530;

    /**
     * Instantiates a new Controller.
     */
    public Controller(){

        // Here a new timer is initiated where the wind lines shown in the GUI are updated every 2 milliseconds.
        // According to the stage at which the Rover is at there are different numbers of wind lines this is set by the methofd initializeWind()
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(getCurrentState() == State.DeSpin){
                            initializeWind(40);
                        }
                        else if(getCurrentState() == State.ParachuteDeploy){
                            initializeWind(20);
                        }
                        else if (getCurrentState() == State.DescentStageEngineCutoff) {
                            initializeWind(0);
                        }

                        alterWind();

                    }
                });
            }
        }, 0, 2);

        //Here the second timer is initiated where the information crucial to the landing is updated according to a timer set up by the user
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Main.studentCode();

                        if(getCurrentState() == Perseverance.State.DeSpin && isOnline() == true){
                            currentStage1.setText("De-Spin");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                        }
                        else if(getCurrentState() == Perseverance.State.CruiseBalanceMassEjected && isOnline() == true) {
                            currentStage1.setText("Cruise Balance Mass Ejected");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                        }
                        else if(getCurrentState() == Perseverance.State.EntryInterfacePoint && isOnline() == true){
                            currentStage1.setText("Entry Interface Point");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                        }
                        else if(getCurrentState() == Perseverance.State.GuidanceStart && isOnline() == true){
                            currentStage1.setText("Guidance Start");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                        }
                        else if(getCurrentState() == Perseverance.State.HeadingAlignment && isOnline() == true){
                            currentStage1.setText("Heading Alignment");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                        }
                        else if(getCurrentState() == Perseverance.State.BeginSUFR && isOnline() == true){
                            currentStage1.setText("Begin SUFR");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                        }
                        else if(getCurrentState() == Perseverance.State.ParachuteDeploy && isOnline() == true){
                            currentStage1.setText("Parachute Deploy");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");

                            parachute.setVisible(true);
                        }
                        else if(getCurrentState() == Perseverance.State.HeatShieldSeparation && isOnline() == true){
                            currentStage1.setText("Heat Shield Separation");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ "feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");

                            int counter = 0;
                            mainPane.getChildren().remove(heatShield);
                            heatShield.setLayoutX(0);
                        }
                        else if(getCurrentState() == Perseverance.State.TRNImageAcquisitionBegins && isOnline() == true){
                            currentStage1.setText("TRN Image Acquisition Begins");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                        }
                        else if(getCurrentState() == Perseverance.State.TRNValidSolution && isOnline() == true){
                            currentStage1.setText("TRN Valid Solution");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                        }
                        else if(getCurrentState() == Perseverance.State.BackshellSeparation && isOnline() == true){
                            currentStage1.setText("Back Shell Separation");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");

                            mainPane.getChildren().remove(backShell);
                            mainPane.getChildren().remove(parachute);
                        }
                        else if(getCurrentState() == Perseverance.State.DescentStageThrottleDown && isOnline() == true){
                            currentStage1.setText("Descent Stage Throttle Down");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");
                            if(thrusters.isVisible() == false){
                                thrusters.setVisible(true);
                            }
                            else{
                                thrusters.setVisible(false);
                            }

                        }
                        else if(getCurrentState() == Perseverance.State.RoverSeparation && isOnline() == true){
                            currentStage1.setText("Rover Separation");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");


                            if(thrusters.isVisible() == false){
                                thrusters.setVisible(true);
                            }
                            else{
                                thrusters.setVisible(false);
                            }


                            flyImage.setOpacity(flyImage.getOpacity() - 0.4);
                        }
                        else if(getCurrentState() == Perseverance.State.Touchdown && isOnline() == true){
                            currentStage1.setText("Touchdown");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");

                            if(thrusters.isVisible() == false){
                                thrusters.setVisible(true);
                            }
                            else{
                                thrusters.setVisible(false);
                            }
                        }
                        else if(getCurrentState() == Perseverance.State.DescentStageEngineCutoff && isOnline() == true){
                            currentStage1.setText("Descent Stage Engine Cutoff");
                            velocityText.setText(Double.toString(getVelocity()) + " mph");
                            altitudeText.setText(Double.toString(getAltitude()) + " feet");
                            distanceText.setText(Double.toString(getDistance())+ " feet");
                            timeToNextPhaseText.setText(Double.toString(getTimeToNextPhase()) + " seconds");
                            timeUntilLandingText.setText(Double.toString(getTouchDownTime()) + " seconds");

                            thrusters.setVisible(false);
                            thrusterAttachment.setVisible(false);
                            mainRover.setY(70);
                        }
                        else if(getCurrentState() == Perseverance.State.SurfaceOperation && isOnline() == true){
                            currentStage1.setText("Surface Operation");
                            velocityText.setText("0 " + "mph");
                            altitudeText.setText("0 " + "feet");
                            distanceText.setText("0 " + "feet");
                            timeToNextPhaseText.setText("0 " + "seconds");
                            timeUntilLandingText.setText("0 " + "seconds");
                            if(MissionCompleted.isVisible() == true){
                                MissionCompleted.setVisible(false);
                            }
                            else{
                                MissionCompleted.setVisible(true);
                            }
                        }
                    }
                });
            }
        }, 0, getTimer());
    }

    // This method is the method which inputs the windlines in random locations throughout the map and checks where it is vertically to see if it needs to be reset
    private void alterWind(){
        for(int i = 0; i < allWind.size(); i++) {
            if(allWind.get(i).getLayoutY() < 40){
                allWind.get(i).setLayoutY((int)Math.floor(Math.random()*(max-min+1)+min));
                allWind.get(i).setLayoutX(rand.nextInt(600));
            }

            else{
                allWind.get(i).setLayoutY(allWind.get(i).getLayoutY() - 1);
            }

        }
    }

    // This is the method which will input or remove the windlines on the GUI. It does his by inputing an ImageView on the GUI.
    private void initializeWind(int windNum) {

        FileInputStream input = null;

        try {
            input = new FileInputStream("/Users/vicentefeliu/IdeaProjects/IAPERSEVERANCEGUI1/src/img/Vertical-Line.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);


        // Remove wind here if needed
        if(windNum < allWind.size()) {
            removeWind(allWind.size() - windNum);
        }

        if(windNum != allWind.size()) {
            for (int i = 0; i < windNum; i++) {
                ImageView wind = new ImageView(image);
                wind.setFitHeight(30);
                wind.setFitWidth(50);
                wind.setLayoutX(rand.nextInt(600));
                wind.setOpacity(0.22);
                wind.setLayoutY((int) Math.floor(Math.random() * (max - min + 1) + min));
                allWind.add(wind);
                mainPane.getChildren().add(wind);

                mainRover.toFront();
                thrusterAttachment.toFront();
                thrusters.toFront();
                backShell.toFront();
                heatShield.toFront();
                parachute.toFront();
                layOut.toFront();
            }
        }
    }

    // This method will remove windlines on the GUI
    private void removeWind(int amount){
        while(amount > 0){
            // Could randomise the index if needed
            mainPane.getChildren().remove(allWind.get(amount-1));
            allWind.get(amount-1).setVisible(false);
            allWind.get(amount-1).setImage(null);
            allWind.remove(amount-1);
            amount--;
        }
    }

    /**
     * Gets timer.
     *
     * @return the timer
     */
    public static int getTimer() { return timer; }

    /**
     * Sets timer.
     *
     * @param timer1 the timer 1
     */
    public static void setTimer(int timer1) { timer = timer1; }
}
