

import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate{
    //instance variable
    private int numTasks;
    private int taskSpeed;

    //Constructors
    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed){
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }

    public BlueAstronaut(String name){
        this(name, 15, 6, 10);
    }

    //Methods
    //This method is extended from an abstract one in Player.java
    @Override
    public void emergencyMeeting() {
        if (!this.isFrozen()){
            //Sort the array of players first
            Player[] vote_players = getPlayers();
            Arrays.sort(vote_players);

            //Define some variables first
            int validCount = 0;
            int validIndex1 = 1;
            int validIndex2 = 1;
            int voteIndex = vote_players.length - 1;

            //Voting Process(1): Choose two valid player out
            Player currentOne = vote_players[voteIndex];
            while (validCount < 2){
                if (!currentOne.isFrozen()){
                    if (validCount == 0){
                        validIndex1 = voteIndex;
                    }
                    else if (validCount == 1){
                        validIndex2 = voteIndex;
                    }
                    validCount += 1;
                }
                voteIndex -= 1;
                currentOne = vote_players[voteIndex];
            }

            //Voting Process(2): Set the most suspicious player to be frozen.
            //If two players have the same highest susLevel, no player will be voted off.
            if (vote_players[validIndex1].getSusLevel() != vote_players[validIndex2].getSusLevel()) {
                vote_players[validIndex1].setFrozen(true);
            }

            //Check the game is over or not
            this.gameOver();
        }
    }


    //This method is provided by the interface of Crew mate.
    @Override
    public void completeTask() {
        if (!this.isFrozen() && numTasks != 0){
            if (taskSpeed > 20){
                numTasks -= 2;
            }
            else{
                numTasks -= 1;
            }

            if (numTasks < 0){
                numTasks = 0;
            }

            if (numTasks == 0){
                System.out.println("I have completed all my tasks");
                this.setSusLevel((int)(this.getSusLevel() * 0.5));
            }
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut){
            BlueAstronaut anotherBlue = (BlueAstronaut) o;
            if (this.numTasks == anotherBlue.numTasks && this.taskSpeed == anotherBlue.taskSpeed) {
                return super.equals(o);
            }
            return false;
        }
        return false;
    }


    @Override
    public String toString() {
        String blue_toString = super.toString() + "I have " + this.numTasks + " left over.";
        if (this.getSusLevel() > 15) return blue_toString.toUpperCase();
        else return  blue_toString;
    }


}
