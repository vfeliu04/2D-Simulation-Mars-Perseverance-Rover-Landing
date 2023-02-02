package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static sample.Perseverance.*;
import static sample.Terrain.landingTerrain;
import static sample.Controller.setTimer;


/**
 * The type Main.
 */
public class Main extends Application {


    // Sets up the GUI
    @Override
    // Starts the GUI and gives it a header and size
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GRAPHICDISPLAY.fxml"));
        primaryStage.setTitle("Mars Rover Panel");
        primaryStage.setScene(new Scene(root, 655, 761));
        primaryStage.show();
    }

    /**
     * The entry point of application.
     * Sets the timer and launches the GUI
     * @param args the input arguments
     */
    public static void main(String[] args) {
        setTimer(125);
        launch(args);

    }

    /**
     * Student code.
     * This represents all the code that the student must do
     * in order to pass from stage to stage and complete the mission.
     * Each if statement checks for the different landing stages
     */
    public static void studentCode() {
        CheckStatus();

        if(getCurrentState() == Perseverance.State.DeSpin){
            setThrusters(true);
        }
        else if(getCurrentState() == Perseverance.State.CruiseBalanceMassEjected) {
            setThrusters(false);
            setMass(885);
        }

        else if(getCurrentState() == Perseverance.State.GuidanceStart){
            setThrusters(true);
        }

        else if(getCurrentState() == Perseverance.State.HeadingAlignment){
            setThrusters(false);
        }

        else if(getCurrentState() == Perseverance.State.BeginSUFR){
            setMass(465);
        }

        else if(getCurrentState() == Perseverance.State.ParachuteDeploy){
            setParachute(false);
        }

        else if(getCurrentState() == Perseverance.State.HeatShieldSeparation){
            setCamera(true);
            setHeatShield(false);
        }

        else if(getCurrentState() == Perseverance.State.TRNImageAcquisitionBegins){
            int[][] landingTerrain = landingTerrain();

            int locationX;
            int locationY;

            int CTC;
            int LTC;
            int RTC;

            int LC;
            int RC;

            int LBC;
            int BC ;
            int RBC;

            //Loops through the 2D array list of random integers and tries to find the coordinates of 0 which is surrounded by 0s.

            for(int w = 0; w < landingTerrain.length-1; w++) {
                for (int q = 0; q < landingTerrain[w].length-1; q++) {
                    if(landingTerrain[w][q] == 0){

                        RC = landingTerrain[w][q+1];
                        LC = landingTerrain[w][q-1];

                        CTC =  landingTerrain[w-1][q];
                        RTC = landingTerrain[w-1][q+1];
                        LTC =  landingTerrain[w-1][q-1];

                        BC =  landingTerrain[w+1][q];
                        RBC = landingTerrain[w+1][q+1];
                        LBC =  landingTerrain[w+1][q-1];

                        if(RC == 0){
                            if(LC == 0){
                                if(CTC == 0){
                                    if(RTC == 0){
                                        if(LTC == 0){
                                            if(BC == 0){
                                                if(RBC== 0){
                                                    if(LBC == 0){
                                                        locationX = q;
                                                        locationY = w;
                                                        int counter = 0;
                                                        if(counter == 0){
                                                            System.out.println("Location X:" + q);
                                                            System.out.println("Location Y: " + w);
                                                            counter++;
                                                        }
                                                        setLandingCoordinates(locationX,locationY);
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        else if(getCurrentState() == Perseverance.State.BackshellSeparation){
            setBackShell(false);
        }
        else if(getCurrentState() == Perseverance.State.DescentStageThrottleDown){
            setThrusters(true);
        }
        else if(getCurrentState() == Perseverance.State.RoverSeparation){
            setCables(true);
            setCablesLength(20);
        }
        else if(getCurrentState() == Perseverance.State.Touchdown){
            setCables(false);
        }
        else if(getCurrentState() == Perseverance.State.DescentStageEngineCutoff){
            setThrusters(false);
        }
        
    }
}