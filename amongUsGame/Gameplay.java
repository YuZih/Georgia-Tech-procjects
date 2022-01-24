public class Gameplay {
    public static void main(String[] args){
        BlueAstronaut bob = new BlueAstronaut("Bod", 20, 6, 30);
        BlueAstronaut heath = new BlueAstronaut("Heath", 30, 3, 21);
        BlueAstronaut albert = new BlueAstronaut("Albert", 44, 2, 0);
        BlueAstronaut angel = new BlueAstronaut("Angel", 0, 1, 0);
        RedAstronaut liam = new RedAstronaut("Liam", 19, "experienced");
        RedAstronaut suspiciousPerson = new RedAstronaut("Suspicious Person", 100, "expert");


        liam.sabotage(bob); //1
        liam.freeze(suspiciousPerson); //2
        liam.freeze(albert); //3
        albert.emergencyMeeting(); //4
        suspiciousPerson.emergencyMeeting(); //5
        bob.emergencyMeeting(); //6
        heath.completeTask(); //7
        heath.completeTask(); //8
        heath.completeTask(); //9
        liam.freeze(angel); //10
        liam.sabotage(bob); //11
        liam.sabotage(bob); //11
        liam.freeze(bob); //12
//        angel.emergencyMeeting(); //13
        liam.sabotage(heath); //14
        liam.sabotage(heath); //14
        liam.sabotage(heath); //14
        liam.sabotage(heath); //14
        liam.sabotage(heath); //14
        liam.freeze(heath); //15
    }
}
