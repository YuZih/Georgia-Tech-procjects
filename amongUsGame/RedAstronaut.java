

import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
    //instance variable
    private String skill;

    //Constructors
    public RedAstronaut(String name, int susLevel, String skill){
        super(name, susLevel);
        this.skill = skill.toLowerCase();
    }

    public RedAstronaut(String name){
        this(name, 15, "experienced");
    }


    //Methods
    //This method is extended from an abstract one in Player.java
    @Override
    public void emergencyMeeting(){
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
                if (!currentOne.equals(this) && !currentOne.isFrozen()){
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

    //This method is provided by the interface of Impostor.
    @Override
    public void freeze(Player p){
        if (!this.isFrozen() && p instanceof BlueAstronaut && !p.isFrozen()){
            if (this.getSusLevel() < p.getSusLevel()){
                p.setFrozen(true);
            }
            else {
                this.setSusLevel(this.getSusLevel() * 2);
            }
        }
        gameOver();
    }

    //This method is provided by the interface of Impostor.
    @Override
    public void sabotage(Player p){
        if (!this.isFrozen() && p instanceof BlueAstronaut){
            if (!p.isFrozen()){
                if (this.getSusLevel() < 20){
                    p.setSusLevel((int)(p.getSusLevel() * 1.5));
                }
                else {
                    p.setSusLevel((int)(p.getSusLevel() * 1.25));
                }
            }

        }
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof RedAstronaut){
            RedAstronaut anotherRed = (RedAstronaut) o;
            if (this.skill.equals(anotherRed.skill)) {
                return super.equals(o);
            }
            return false;
        }
        return false;
    }


    @Override
    public String toString() {
        String red_toString = super.toString() + " I am an " + this.skill + " player!";
        if (this.getSusLevel() > 15) return red_toString.toUpperCase();
        else return  red_toString;
    }
}
