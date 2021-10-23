import java.util.Scanner;

public class Game {

    public Game(){
        startGame();
    }

    private static void startGame(){

        Floor floor = new Floor();
        Elevator elevator = new Elevator();
        Ladder ladder = new Ladder();
        Snake snake = new Snake();
        KingCobra kingCobra = new KingCobra();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the player name and hit enter");
        String playerName = sc.nextLine();
        Player p1 = new Player(playerName);

        System.out.println("The game setup is ready");
        boolean flag = false;

        while (true){

            System.out.print("Hit enter to roll the dice");
            sc.nextLine();

            Dice.roll();
            int diceReading = Dice.getFaceValue();
            System.out.println("Dice gave " + diceReading);

            p1.setFloorNum(diceReading);
            int curFloor = p1.getFloor();

            //System.out.println(p1.getFloor() + " " + p1.getPoints() + " " + curFloor);

            if (!flag && diceReading != 1){
                System.out.println("Game cannot start until you get 1");
                p1.setFloorNum(-diceReading);
                continue;
            }

            else if (!flag && diceReading == 1){
                flag = true;
            }

            else if (curFloor > 13){
                System.out.println("Player cannot move");
                p1.setFloorNum(-diceReading);
                continue;
            }

            else if (curFloor == 2){
                elevator.updateDetails(p1);
            }

            else if (curFloor == 5){
                snake.updateDetails(p1);
            }

            else if (curFloor == 8){
                ladder.updateDetails(p1);
            }

            else if (curFloor == 11){
                kingCobra.updateDetails(p1);
            }

            floor.updateDetails(p1);

            if (curFloor == 13){
                System.out.println("Game over\n" + p1.getName() + " accumulated " + p1.getPoints() + " points\n---------------------------------------------------------------");
                break;
            }

        }

        sc.close();

    }

}
