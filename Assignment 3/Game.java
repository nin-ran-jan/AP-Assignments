import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private Dice _d;

    public Game(){
        this.players = new ArrayList<Player>();
        this._d = new Dice();
        this.startGame();
    }

    private void startGame(){

        EmptyFloor emptyFloor = new EmptyFloor();
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of players you want in the game: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++){
            System.out.println("Enter the player name and hit enter");
            String playerName = sc.nextLine();
            Player p = new Player(playerName);
            this.players.add(p);
        }

        System.out.println("The game setup is ready\n");

        int numPlayerCompleted = 0;

        while (true){

            for (Player p : this.players){

                if (!p.getCompletionStatus()){
                    System.out.println(p.getName() + " it's your turn!");
                    System.out.print("Hit enter to roll the dice");
                    sc.nextLine();

                    this._d.roll();
                    int diceReading = this._d.getFaceValue();
                    //int diceReading = sc.nextInt();
                    System.out.println("Dice gave " + diceReading);

                    p.setFloorNum(diceReading);
                    int curFloor = p.getFloor();

                    if (!p.getFlag() && diceReading != 1){
                        System.out.println("Game cannot start until you get 1");
                        p.setFloorNum(-diceReading);
                        System.out.println();
                        continue;
                    }

                    else if(!p.getFlag() && diceReading == 1){
                        p.setFlag();
                    }

                    else if (curFloor > 20){
                        System.out.println("Player cannot move");
                        p.setFloorNum(-diceReading);
                        System.out.println();
                        continue;
                    }

                    else if (curFloor == 2){
                        Elevator elevator = new Elevator();
                        elevator.updateDetails(p);
                    }

                    else if (curFloor == 5 || curFloor == 16){
                        Snake snake = new Snake();
                        snake.updateDetails(p);
                    }

                    else if (curFloor == 8 || curFloor == 14){
                        Ladder ladder = new Ladder();
                        ladder.updateDetails(p);
                    }

                    else if (curFloor == 11){
                        KingCobra kingCobra = new KingCobra();
                        kingCobra.updateDetails(p);
                    }

                    else if(curFloor == 19){
                        Nagini nagini = new Nagini();
                        nagini.updateDetails(p);
                    }

                    emptyFloor.updateDetails(p);
                    System.out.println();

                    if (curFloor == 20){
                        System.out.println("You're done!\n" + p.getName() + " accumulated " + p.getPoints() + " points\n---------------------------------------------------------------");
                        p.gameCompleted();
                        numPlayerCompleted++;
                    }
                }

            }

            
            if (numPlayerCompleted == this.players.size()){
                System.out.println("Game over!");

                int maxScore = Integer.MIN_VALUE;
                String winner = "";
                boolean flag = false;

                for (Player p : players){

                    if (p.getPoints() > maxScore){
                        winner = p.getName();
                        maxScore = p.getPoints();
                        flag = false;
                    }

                    else if (p.getPoints() == maxScore){
                        flag = true;
                    }

                    System.out.println(p.getName() + " accumulated " + p.getPoints() + " points");
                }

                System.out.println();

                if (!flag){
                    System.out.println("The winner is " + winner + "! Congratulations! You got a score of " + maxScore + ".");
                }
                else{
                    System.out.println("Looks like there has been a tie at " + maxScore + " points! No one wins.");
                }

                System.out.println("---------------------------------------------------------------");

                break;
            }

        }

        sc.close();

    }

}
