package sample;


import static sample.Terrain.location;


/**
 * The type Perseverance.
 */
public  class Perseverance{

    /**
     * The enum State.
     */
    enum State{

        /**
         * De spin state.
         */
        DeSpin,

        /**
         * Cruise balance mass ejected state.
         */
        CruiseBalanceMassEjected,

        /**
         * Entry interface point state.
         */
        EntryInterfacePoint,

        /**
         * Guidance start state.
         */
        GuidanceStart,

        /**
         * Heading alignment state.
         */
        HeadingAlignment,

        /**
         * Begin sufr state.
         */
        BeginSUFR,

        /**
         * Parachute deploy state.
         */
        ParachuteDeploy,

        /**
         * Heat shield separation state.
         */
        HeatShieldSeparation,

        /**
         * Trn image acquisition begins state.
         */
        TRNImageAcquisitionBegins,

        /**
         * Trn valid solution state.
         */
        TRNValidSolution,

        /**
         * Backshell separation state.
         */
        BackshellSeparation,

        /**
         * Descent stage throttle down state.
         */
        DescentStageThrottleDown,

        /**
         * Rover separation state.
         */
        RoverSeparation,

        /**
         * Touchdown state.
         */
        Touchdown,

        /**
         * Descent stage engine cutoff state.
         */
        DescentStageEngineCutoff,

        /**
         * Surface operation state.
         */
        SurfaceOperation,
    }

    private static State currentState = State.DeSpin;
    private static double velocity = 10585.48;
    private static double altitude = 847.26;
    private static double distance = 2014.65;
    private static double touchDownTime = 919;
    private static double timeToNextPhase = 35;
    private static int mass = 1025;

    private static boolean heatShield = true;
    private static boolean parachute = true;
    private static boolean backShell = true;
    private static boolean camera = false;
    private static boolean cables = false;
    private static boolean thrusters = false;
    private static double cablesLength = 0.0;
    private static String error = "Communication With Perseverance Rover Lost...";
    private static boolean online = true;
    private static String completed = "Congratulations you have completed the mission";

    private static boolean safeLanding = false;
    /**
     * Instantiates a new Perseverance.
     */
    public Perseverance(){
    }


    /**
     * Check status and checks what stage of landing it is. If it is a certain landing stage it calls the method of landing.
     */
    public static void CheckStatus(){
        if (currentState == State.DeSpin){
            DeSpin();
        }
        else if (currentState == State.CruiseBalanceMassEjected) {
            CruiseBalanceMassEjected();
        }
        else if (currentState == State.EntryInterfacePoint) {
            EntryInterfacePoint();
        }
        else if (currentState == State.GuidanceStart) {
            GuidanceStart();
        }
        else if (currentState == State.HeadingAlignment) {
            HeadingAlignment();
        }
        else if (currentState == State.BeginSUFR) {
            BeginSUFR();
        }
        else if (currentState == State.ParachuteDeploy) {
            ParachuteDeploy();
        }
        else if (currentState == State.HeatShieldSeparation) {
            HeatShieldSeparation();
        }
        else if (currentState == State.TRNImageAcquisitionBegins) {
            TRNImageAcquisitionBegins();
        }
        else if (currentState == State.TRNValidSolution) {
            TRNValidSolution();
        }
        else if (currentState == State.BackshellSeparation) {
            BackshellSeparation();
        }
        else if (currentState == State.DescentStageThrottleDown) {
            DescentStageThrottleDown();
        }
        else if (currentState == State.RoverSeparation) {
            RoverSeparation();
        }
        else if (currentState == State.Touchdown) {
            Touchdown();
        }
        else if (currentState == State.DescentStageEngineCutoff) {
            DescentStageEngineCutoff();
        }
        else if (currentState == State.SurfaceOperation) {
            SurfaceOperation();
        }

    }

    // This is one example of a method which represents the a landing stage.
    // As seen this type of method will always start with calling a method which performs a calculation which
    // later gives the value and gets updated on the GUI.
    // Then the method checks if the changes that were supposed to be perforemd by the student were performed and that the
    // timer until next phase is 0. Later it perfoms a check that makes sure the user did not change any other variables and
    // after that it sets the time of the next phase and calls the sets currect state to the next phase.
    //This method technique gets done over and over again.
    private static void DeSpin(){
        changeAltitude(847.26 - 781.63, 35);
        changeVelocity(10585.48-10678.61, 35);
        changeDistance(2014.65 - 1909.63, 35);
        changeTimeToNextPhase(35, 35);
        changeTouchDownTimes(35, 35);

        if(getThrusters() == true && getTimeToNextPhase() == 0){
            if (parachute && online && !cables && heatShield && backShell && !camera && thrusters &&  cablesLength == 0.0 && mass == 1025 && safeLanding == false){
                setTimeToNextPhase(483);
                setCurrentState(State.CruiseBalanceMassEjected);
            }
            else{
                System.out.println(getError());
                setOnline(false);
            }
        }
        else if(getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }
    }

    private static void CruiseBalanceMassEjected() {

        changeAltitude(781.63-81.40, 483);
        changeVelocity(10678.61-11931-69, 483);
        changeDistance(1909.63-395.18, 483);
        changeTimeToNextPhase(483, 483);
        changeTouchDownTimes(483, 483);
        if(getMass() != 885){
            System.out.println(getError());
            setOnline(false);
        }
        else if(getThrusters() == false && getTimeToNextPhase() == 0){
            if (parachute && online && !cables && heatShield && backShell && !camera && !thrusters &&  cablesLength == 0.0 && safeLanding == false){
                setTimeToNextPhase(54);
                setCurrentState(State.EntryInterfacePoint);
            }
            else{
                System.out.println(getError());
                setOnline(false);
            }
        }
        else if( getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }
    }

    private static void EntryInterfacePoint() {
        changeAltitude(81.40-35.2, 54);
        changeVelocity(11931-11988.71, 54);
        changeDistance(395.18-212.85, 54);
        changeTimeToNextPhase(54,54);
        changeTouchDownTimes(54,54);
        if(getTimeToNextPhase() == 0){
            if (parachute && online && !cables && heatShield && backShell && !camera && !thrusters &&  cablesLength == 0.0 && mass == 885 && safeLanding == false){
                setTimeToNextPhase(84);
                setCurrentState(State.GuidanceStart);
            }
            else
            {
                System.out.println(getError());
                setOnline(false);
            }
        }
        else if( getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }


    }

    private static void GuidanceStart() {
        changeAltitude(35.2-10.4,84);
        changeVelocity(1988.71-2465.37,84);
        changeDistance(212.85-48.63,84);
        changeTimeToNextPhase(84,84);
        changeTouchDownTimes(84,84);
        if(getThrusters() == true && getAltitude() <= 11){
            if (parachute && online && !cables && heatShield && backShell && !camera && thrusters && cablesLength == 0.0 && mass == 885 && safeLanding == false) {
                setTimeToNextPhase(86);
                setCurrentState(State.HeadingAlignment);
            }
            else{
                System.out.println(getError());
                setOnline(false);
            }
        }
        else if(getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }
    }

    private static void HeadingAlignment() {
        changeAltitude(10.4-8.54,86);
        changeVelocity(2465.37-1068.29, 86);
        changeDistance(48.63-13.14, 86);
        changeTimeToNextPhase(86, 86);
        changeTouchDownTimes(86, 86);
        if(getThrusters() == false && getTimeToNextPhase() == 0) {
            if (parachute && online && !cables && heatShield && backShell && !camera && !thrusters && cablesLength == 0.0 && mass == 885 && safeLanding == false) {
                setTimeToNextPhase(17);
                setCurrentState(State.BeginSUFR);
            }
            else {
                System.out.println(getError());
                setOnline(false);
            }
        }
        else if(getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }

    }

    private static void BeginSUFR() {
        changeAltitude(8.54-7.42, 17);
        changeVelocity(1068.29-945.14, 17);
        changeDistance(13.14-9.8, 17);
        changeTimeToNextPhase(17, 17);
        changeTouchDownTimes(17, 17);
        if(getMass() != 465){
            System.out.println(getError());
            setOnline(false);
        }
        else if(getTimeToNextPhase() == 0){
            if (parachute && online && !cables && heatShield && backShell && !camera && !thrusters &&  cablesLength == 0.0 && mass == 465 && safeLanding == false) {
                setTimeToNextPhase(20);
                setCurrentState(State.ParachuteDeploy);
            }
            else {
                System.out.println(getError());
                setOnline(false);

            }
        }
        else if(getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }

    }

    private static void ParachuteDeploy() {
        changeAltitude(7.42 - 6.05, 20);
        changeVelocity(945.14-363.04,20);
        changeDistance(9.8-6.50, 20);
        changeTimeToNextPhase(20, 20);
        changeTouchDownTimes(20, 20);
        if(isParachute() == false && getTimeToNextPhase() == 0){
            if (!parachute && online && !cables && heatShield && backShell && !camera && !thrusters &&  cablesLength == 0.0 && mass == 465 && safeLanding == false) {
               setTimeToNextPhase(60);
                setCurrentState(State.HeatShieldSeparation);
            }
            else {
                System.out.println(getError());
                setOnline(false);

            }
        }
        else if(getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }


    }

    private static void HeatShieldSeparation() {
        changeAltitude(6.05-2.65, 60);
        changeVelocity(363.04-300.41, 60);
        changeDistance(6.50-2.68,60);
        changeTimeToNextPhase(60,60);
        changeTouchDownTimes(60,60);
        if(isHeatShield() == false && isCamera() == true && getTimeToNextPhase() == 0) {
            if (!parachute && online && !cables && !heatShield && backShell && camera && !thrusters && cablesLength == 0.0 && mass == 465 && safeLanding == false) {
                setTimeToNextPhase(6);
                setCurrentState(State.TRNImageAcquisitionBegins);
            } else {
                System.out.println(getError());
                setOnline(false);

            }
        }
        else if(getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }
    }



    private static void TRNImageAcquisitionBegins() {
        if (!(!parachute && online && !cables && !heatShield && backShell && camera && !thrusters && cablesLength == 0.0 && mass == 465)) {
            System.out.println(getError());
            setOnline(false);
        }
    }

    /**
     * Set landing coordinates.
     * This method is the method which the student will call in order to land the perseverance. The method
     * will compare the coordinates given by the user to the ones that were given in terrain class. If
     * they are correct then the user is able to move to the next phase of landing if not then the
     * mars rover crashes.
     *
     * As seen this method first update the crucial information as aforementioned then it removes the information
     * from the array list and finally it comapres them to see if it is safe to land.
     * @param x the x
     * @param y the y
     */
    public static void setLandingCoordinates(int x, int y){
        changeAltitude(2.65-2.29, 6);
        changeVelocity(200.41-194.68, 6);
        changeDistance(2.68-2.31,6);
        changeTimeToNextPhase(6,6);
        changeTouchDownTimes(6,6);

        int locationX = (int) location().get(0);
        int locationY = (int) location().get(1);
        if(x == locationX && y == locationY && safeLanding == false && getTimeToNextPhase() == 0){
            setSafeLanding(true);
            setTimeToNextPhase(17);
            setCurrentState(State.TRNValidSolution);
        }
        else if(getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }
    }

    private static void TRNValidSolution() {
        changeAltitude(2.29-1.36, 17);
        changeVelocity(194.68-182.41, 17);
        changeDistance(2.31-1.38, 17);
        changeTimeToNextPhase(17, 17);
        changeTouchDownTimes(17, 17);
        if(getTimeToNextPhase() == 0){
            if (!parachute && online && !cables && !heatShield && backShell && camera && !thrusters &&  cablesLength == 0.0 && mass == 465 && safeLanding == true) {
                setTimeToNextPhase(41);
                setCurrentState(State.BackshellSeparation);
            }
            else {
                System.out.println(getError());
                setOnline(false);

            }
        }

    }

    private static void BackshellSeparation() {
        changeAltitude((1.36-0.0130682), 41);
        changeVelocity((182.41-3.73), 41);
        changeDistance((1.38-0.013445076), 41);
        changeTimeToNextPhase(41, 41);
        changeTouchDownTimes(41, 41);
        if (isBackShell() == true && getTimeToNextPhase() < 0) {
            getError();
            setOnline(false);
        }
        else if (isBackShell() == false && getTimeToNextPhase() == 0){
            if (!parachute && online && !cables && !heatShield && !backShell && camera && !thrusters && cablesLength == 0.0 && mass == 465 && safeLanding == true) {
                setTimeToNextPhase(2);
                setCurrentState(State.DescentStageThrottleDown);
            }
            else {
                System.out.println(getError());
                setOnline(false);

            }
        }
    }

    private static void DescentStageThrottleDown() {
        changeAltitude((0.0130682 - 0.012418561), 2);
        changeVelocity((3.73 - 1.17), 2);
        changeDistance((0.013445076 - 0.012422348), 2);
        changeTimeToNextPhase(2, 2);
        changeTouchDownTimes(2, 2);

        if (isThrusters() == true && getTimeToNextPhase() == 0){
            if (!parachute && online && !cables && !heatShield && !backShell && camera && thrusters &&  cablesLength == 0.0 && mass == 465 && safeLanding == true) {
                setTimeToNextPhase(16);
                setCurrentState(State.RoverSeparation);
            }
            else {
                System.out.println(getError());
                setOnline(false);

            }
        }
        else if(getTimeToNextPhase() < 0 ){
            System.out.println(getError());
            setOnline(false);
        }
    }

    private static void RoverSeparation() {
        changeAltitude((0.012418561 - 0), 16);
        changeVelocity((1.17 - 0), 16);
        changeDistance((0.012422348 - 0), 16);
        changeTimeToNextPhase(16, 16);
        changeTouchDownTimes(16, 16);
        if(isCables() == true && getTimeToNextPhase() == 0) {
            if (!parachute && online && cables && !heatShield && !backShell && camera && thrusters && cablesLength == 20.0 && mass == 465 && safeLanding == true) {
                setTimeToNextPhase(7);
                setCurrentState(State.Touchdown);
            } else {
                System.out.println(getError());
                setOnline(false);

            }
        }
        else if(getTimeToNextPhase() < 0){
            System.out.println(getError());
            setOnline(false);
        }

    }

    private static void Touchdown() {
        setAltitude(0);
        setVelocity(0);
        setDistance(0);
        setTouchDownTime(0);
        changeTimeToNextPhase(7, 7);
        if(isCables() == false && getTimeToNextPhase() == 0){
             if (!parachute && online && !cables && !heatShield && !backShell && camera && thrusters &&  cablesLength == 20.0 && mass == 465 && safeLanding == true) {
                setTimeToNextPhase(11);
                setCurrentState(State.DescentStageEngineCutoff);
            }
            else {
                System.out.println(getError());
                setOnline(false);
            }
        }
        else if(getTimeToNextPhase() < 0) {
            System.out.println(getError());
            setOnline(false);
        }
    }

    private static void DescentStageEngineCutoff() {
        setThrusters(false);
        setAltitude(0);
        setVelocity(0);
        setDistance(0);
        setTouchDownTime(0);
        changeTimeToNextPhase(11,11);
        if(getTimeToNextPhase() == 0){
            if (!parachute && online && !cables && !heatShield && !backShell && camera && !thrusters &&  cablesLength == 20.0 && mass == 465 && safeLanding == true) {
            setCurrentState(State.SurfaceOperation);
            }
            else {
                getError();
                setOnline(false);
            }
        }
    }

    private static void SurfaceOperation() {
        setAltitude(0);
        setVelocity(0);
        setDistance(0);
        setTouchDownTime(0);
        setTimeToNextPhase(0);
        if (!parachute && online && !cables && !heatShield && !backShell && camera && !thrusters &&  cablesLength == 20.0 && mass == 465 && safeLanding == true) {
            System.out.println(getCompleted());
        }
        else {
            getError();
            setOnline(false);

        }
    }

    /**
     * Change velocity.
     * @param number                 the number
     * @param rateOfChangeOfVelocity the rate of change of velocity
     */
    public static void changeVelocity(double number, double rateOfChangeOfVelocity) {
        setVelocity(getVelocity() - (number / (rateOfChangeOfVelocity)));
    }

    /**
     * Change altitude.
     *
     * @param number                 the number
     * @param rateOfChangeOfAltitude the rate of change of altitude
     */
    public static void changeAltitude(double number ,double rateOfChangeOfAltitude) {
        setAltitude(getAltitude() - (number / rateOfChangeOfAltitude));
    }

    /**
     * Change distance.
     *
     * @param number                 the number
     * @param rateOfChangeOfDistance the rate of change of distance
     */
    public static void changeDistance(double number, double rateOfChangeOfDistance) {
        setDistance(getDistance() -  (number / rateOfChangeOfDistance));
    }

    /**
     * Change touch down times.
     *
     * @param number                      the number
     * @param rateOfChangeOFTouchDownTime the rate of change of touch down time
     */
    public static void changeTouchDownTimes(double number, double rateOfChangeOFTouchDownTime) {
        setTouchDownTime((int) (getTouchDownTime() - (number / rateOfChangeOFTouchDownTime)));
    }

    /**
     * Change time to next phase.
     *
     * @param number                        the number
     * @param rateOfChangeOfTimeToNextPhase the rate of change of time to next phase
     */
    public static void changeTimeToNextPhase(double number, double rateOfChangeOfTimeToNextPhase) {
        setTimeToNextPhase((int) (getTimeToNextPhase() - (number / rateOfChangeOfTimeToNextPhase)));
    }

    /**
     * Gets completed.
     *
     * @return the completed
     */
    public static String getCompleted() { return completed; }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public static double getVelocity() {
        return velocity;
    }

    /**
     * Sets velocity.
     *
     * @param velocity1 the velocity 1
     */
    public static void setVelocity(double velocity1) {
        velocity = velocity1;
    }

    /**
     * Gets altitude.
     *
     * @return the altitude
     */
    public static double getAltitude() {
        return altitude;
    }

    /**
     * Sets altitude.
     *
     * @param altitude1 the altitude 1
     */
    public static void setAltitude(double altitude1) {
        altitude = altitude1;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public static double getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance1 the distance 1
     */
    public static void setDistance(double distance1) {
        distance = distance1;
    }

    /**
     * Gets touch down time.
     *
     * @return the touch down time
     */
    public static double getTouchDownTime() {
        return touchDownTime;
    }

    /**
     * Sets touch down time.
     *
     * @param touchDownTime1 the touch down time 1
     */
    public static void setTouchDownTime(int touchDownTime1) {
        touchDownTime = touchDownTime1;
    }

    /**
     * Sets mass.
     *
     * @param mass the mass
     */
    public static void setMass(int mass) { Perseverance.mass = mass; }

    /**
     * Is heat shield boolean.
     *
     * @return the boolean
     */
    public static boolean isHeatShield() {
        return heatShield;
    }

    /**
     * Sets heat shield.
     *
     * @param heatShield1 the heat shield 1
     */
    public static void setHeatShield(boolean heatShield1) {
        heatShield = heatShield1;
    }

    /**
     * Is parachute boolean.
     *
     * @return the boolean
     */
    public static boolean isParachute() {
        return parachute;
    }

    /**
     * Sets parachute.
     *
     * @param parachute1 the parachute 1
     */
    public static void setParachute(boolean parachute1) {
        parachute = parachute1;
    }

    /**
     * Is back shell boolean.
     *
     * @return the boolean
     */
    public static boolean isBackShell() {
        return backShell;
    }

    /**
     * Sets back shell.
     *
     * @param backShell1 the back shell 1
     */
    public static void setBackShell(boolean backShell1) { backShell = backShell1; }

    /**
     * Is camera boolean.
     *
     * @return the boolean
     */
    public static boolean isCamera() {
        return camera;
    }

    /**
     * Sets camera.
     *
     * @param camera1 the camera 1
     */
    public static void setCamera(boolean camera1) { camera = camera1; }

    /**
     * Is cables boolean.
     *
     * @return the boolean
     */
    public static boolean isCables() {
        return cables;
    }

    /**
     * Sets cables.
     *
     * @param cables1 the cables 1
     */
    public static void setCables(boolean cables1) { cables = cables1; }

    /**
     * Sets cables length.
     *
     * @param cablesLength1 the cables length 1
     */
    public static void setCablesLength(double cablesLength1) { cablesLength = cablesLength1; }

    /**
     * Gets error.
     *
     * @return the error
     */
    public static String getError() {
        return error;
    }

    /**
     * Gets current state.
     *
     * @return the current state
     */
    public static State getCurrentState() {
        return currentState;
    }

    /**
     * Sets current state.
     *
     * @param currentState1 the current state 1
     */
    public static void setCurrentState(State currentState1) {
        currentState = currentState1;
    }

    /**
     * Gets time to next phase.
     *
     * @return the time to next phase
     */
    public static double getTimeToNextPhase() {
        return timeToNextPhase;
    }

    /**
     * Sets time to next phase.
     *
     * @param timeToNextPhase1 the time to next phase 1
     */
    public static void setTimeToNextPhase(int timeToNextPhase1) {
        timeToNextPhase = timeToNextPhase1;
    }

    /**
     * Gets mass.
     *
     * @return the mass
     */
    public static int getMass() {
        return mass;
    }

    /**
     * Gets thrusters.
     *
     * @return the thrusters
     */
    public static boolean getThrusters() {
        return thrusters;
    }

    /**
     * Sets thrusters.
     *
     * @param thrusters1 the thrusters 1
     */
    public static void setThrusters(boolean thrusters1) { thrusters = thrusters1; }

    /**
     * Is thrusters boolean.
     *
     * @return the boolean
     */
    public static boolean isThrusters() {
        return  thrusters;
    }

    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    public static boolean isOnline() { return online; }

    /**
     * Sets online.
     *
     * @param online1 the online 1
     */
    public static void setOnline(boolean online1) {
        online = online1;
    }

    /**
     * Sets safe landing.
     *
     * @param safeLanding1 the safe landing 1
     */
    public static void setSafeLanding(boolean safeLanding1) { safeLanding = safeLanding1; }

}