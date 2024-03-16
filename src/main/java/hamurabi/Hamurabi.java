package hamurabi;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Hamurabi {
    Random rand = new Random();
    Scanner scanner;

    public Hamurabi() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        (new Hamurabi()).playGame();
    }

    void playGame() {
    }

    public String printSummary(int yearCount, int peopleStarved, int newPeople, int currentPopulation, int harvestBushels, int bushelsPerAcres, int bushelsDestroyed, int bushelsStored, int ownedLand, int currentLandWorth) {
        StringBuilder sb = new StringBuilder();
        sb.append("O great Hammurabi!\n").append("You are in year " + yearCount + " of your ten year rule.\n").append("In the previous year " + peopleStarved + "people starved to death.\n").append("In the previous year " + newPeople + "people entered the kingdom.\n").append("The population is now " + currentPopulation + "\n").append("We harvested " + harvestBushels + " bushels at " + bushelsPerAcres + " bushels per acre.").append("Rats destroyed " + bushelsDestroyed + " bushels, leaving " + bushelsStored + " bushels in storage.").append(" The city owns " + ownedLand + "acres of land.").append("Land is currently worth " + currentLandWorth + " bushels per acre.");
        return sb.toString();
    }

    public int getNumber(String message) {
        while(true) {
            System.out.print(message);

            try {
                return this.scanner.nextInt();
            } catch (InputMismatchException var3) {
                System.out.println("\"" + this.scanner.next() + "\" isn't a number!");
            }
        }
    }

    public int askHowManyAcresToBuy(int price, int bushels) {
        int input;
        for(input = this.getNumber("How many acres of land do you want to buy?\n"); input * price > bushels; input = this.getNumber("How many acres of land do you want to buy?\n")) {
            System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
        }

        return input;
    }

    public int askHowManyAcresToSell(int acresOwned) {
        int input;
        for(input = this.getNumber("How many acres of land do you want to sell?\n"); acresOwned < input; input = this.getNumber("How many acres of land do you want to sell?\n")) {
            System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres left!");
        }

        return input;
    }

    public int askHowMuchGrainToFeedPeople(int bushels) {
        int input;
        for(input = this.getNumber("How much grain do you want to feed the people?\n"); bushels < input; input = this.getNumber("How much grain do you want to feed the people?\n")) {
            System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
        }

        return input;
    }

    public int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        int input = this.getNumber("How many acres do you want to plant with grain?\n");

        while(true) {
            boolean flag = false;
            if (population * 10 > input) {
                System.out.println("O Great Hammurabi, surely you jest! We have only " + population + " people for farming!");
                flag = true;
            }

            if (bushels * 2 < input) {
                System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels to plant with!");
                flag = true;
            }

            if (acresOwned < input) {
                System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres of land!");
                flag = true;
            }

            flag = true;
            if (false) {
                return input;
            }

            input = this.getNumber("How many acres do you want to plant with grain?\n");
        }
    }

    public int plagueDeaths(int population) {
        int numberOfDeaths = population / 2;
        return numberOfDeaths;
    }
}
