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
        int population =100;
        int bushels = 2800;
        int acresOfLand = 1000;
        int landValue = 19;
        int acresToBuy = 0;
        int peopleStarved =0;
        int newPeople =0;
        int harvestBushels =0;
        int bushelsDestroyed =0;
        int bushelsPerAcre =0;
        int acresToSell = 0;


        String userChoice;
        for(int year = 1; year <=10; year++){
            System.out.println(printSummary(year,peopleStarved,newPeople,population,harvestBushels,
                    bushelsPerAcre,bushelsDestroyed,bushels,acresOfLand,landValue));

            System.out.println("\nDo you want to \"buy\" or \"sell\" your land?");
            userChoice = scanner.nextLine();
            while(true){

                if(userChoice.equals("buy")){
                    acresToBuy = askHowManyAcresToBuy(landValue,bushels);
                    acresOfLand = acresOfLand + acresToBuy;
                    bushels = bushels - (acresToBuy * landValue);
                    break;
                } else if (userChoice.equals("sell")) {
                    acresToSell = askHowManyAcresToSell(acresOfLand);
                    acresOfLand = acresOfLand - acresToSell;
                    bushels = bushels + (acresToSell * landValue);
                    break;
                }
                else{
                    System.out.println("Please enter a valid option");
                    System.out.println("\nDo you want to \"buy\" or \"sell\" your land?");
                    userChoice = scanner.nextLine();
                    }
            }

//            System.out.println(printSummary(year,peopleStarved,newPeople,population,harvestBushels,
//                    bushelsPerAcre,bushelsDestroyed,bushels,acresOfLand,landValue));
//
            int bushelsFeedToPeople = askHowMuchGrainToFeedPeople(bushels);
            bushels = bushels - bushelsFeedToPeople;

            int acresToPlant = askHowManyAcresToPlant(acresOfLand,population,bushels);
            bushels = bushels - (2*acresToPlant);

            int plagueDeaths = plagueDeaths(population);
            population -= plagueDeaths;

           peopleStarved = starvationDeaths(population,bushelsFeedToPeople);
           population -= peopleStarved;

           if (uprising(population,peopleStarved)){
               System.out.println("Game Over! You are thrown out of the office. You have starved the people");
               break;
           }

           if(peopleStarved == 0){
               newPeople = immigrants(population,acresOfLand,bushels);
               population += newPeople;
           }

           harvestBushels = harvest(acresToPlant);
           bushelsPerAcre = harvestBushels/acresToPlant;
           bushels += harvestBushels;

           bushelsDestroyed = grainEatenByRats(bushels);
           bushels -= bushelsDestroyed;

           landValue = newCostOfLand();

           if (year == 10){
               System.out.println(finalSummary(peopleStarved,newPeople,population,harvestBushels,
                       bushelsPerAcre,bushelsDestroyed,bushels,acresOfLand,landValue));
           }
        }


    }

    public String printSummary(int yearCount, int peopleStarved, int newPeople, int currentPopulation, int harvestBushels,
                               int bushelsPerAcres, int bushelsDestroyed, int bushelsStored, int ownedLand, int currentLandWorth) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nO great Hammurabi!\n")
                .append("You are in year " + yearCount + " of your ten year rule.\n")
                .append("In the previous year " + peopleStarved + " people starved to death.\n")
                .append("In the previous year " + newPeople + " people entered the kingdom.\n")
                .append("The population is now " + currentPopulation+"." + "\n")
                .append("We harvested " + harvestBushels + " bushels at " + bushelsPerAcres + " bushels per acre.\n")
                .append("Rats destroyed " + bushelsDestroyed + " bushels, leaving " + bushelsStored + " bushels in storage.\n")
                .append("The city owns " + ownedLand + " acres of land.\n")
                .append("Land is currently worth " + currentLandWorth + " bushels per acre.\n");
        return sb.toString();
    }

    public String finalSummary(int peopleStarved, int newPeople, int currentPopulation, int harvestBushels, int bushelsPerAcres, int bushelsDestroyed, int bushelsStored, int ownedLand, int currentLandWorth) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nO great Hammurabi!  Congratulations!\n")
                .append("You have completed your 10 year rule and you managed everything well\n")
                .append("In the previous year " + peopleStarved + " people starved to death.\n")
                .append("In the previous year " + newPeople + " people entered the kingdom.\n")
                .append("The population is now " + currentPopulation+"." + "\n")
                .append("We harvested " + harvestBushels + " bushels at " + bushelsPerAcres + " bushels per acre.\n")
                .append("Rats destroyed " + bushelsDestroyed + " bushels, leaving " + bushelsStored + " bushels in storage.\n")
                .append("The city owns " + ownedLand + " acres of land.\n")
                .append("Land is currently worth " + currentLandWorth + " bushels per acre.\n");
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


    public int askHowManyAcresToBuy(int price, int bushels){
        int input = getNumber("\nHow many acres of land do you want to buy?\n");
        while(input*price>bushels || input < 0){
            System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
            input = getNumber("\nHow many acres of land do you want to buy?\n");
        }
        return input;
    }
    public int askHowManyAcresToSell(int acresOwned){
        int input = getNumber("\nHow many acres of land do you want to sell?\n");
        while(acresOwned<input || input < 0){
            System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres left!");
            input = getNumber("\nHow many acres of land do you want to sell?\n");
        }
        return input;
    }
    public int askHowMuchGrainToFeedPeople(int bushels){
        int input = getNumber("How much grain do you want to feed the people?\n");
        while(bushels<input){
            System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels left!");
            input = getNumber("How much grain do you want to feed the people?\n");
        }
        return input;
    }
    public int askHowManyAcresToPlant(int acresOwned, int population, int bushels){
        int input = getNumber("How many acres do you want to plant with grain?\n");

        while(true){
            boolean flag = false;

            if(population*10 < input){
                System.out.println("O Great Hammurabi, surely you jest! We have only " + population + " people for farming!");
                flag = true;
            }

            if( bushels*2 < input) {
                System.out.println("O Great Hammurabi, surely you jest! We have only " + bushels + " bushels to plant with!");
                flag = true;
            }

            if(acresOwned < input){
                System.out.println("O Great Hammurabi, surely you jest! We have only " + acresOwned + " acres of land!");
                flag = true;
            }

            // check if we can break
            if(flag){
                input = getNumber("How many acres do you want to plant with grain?\n");
            }
            else
                break;
        }
        return input;

    }

    public int plagueDeaths(int population) {
        int numberOfDeaths = 0;
        if (rand.nextInt(100) < 15) {
            numberOfDeaths = population / 2;
            System.out.println("Plague killed half of your population!");
        }
        return numberOfDeaths;
    }

    public int starvationDeaths(int population, int bushelsFedToPeople){
        int numberOfDeaths = population - (bushelsFedToPeople/20);
        if(numberOfDeaths<0){
            numberOfDeaths = 0;
        }
        return numberOfDeaths;
    }
    public boolean uprising(int population, int howManyPeopleStarved){
        double starvation = (double)howManyPeopleStarved / (double)population;
        //System.out.println(starvation); for testing
        if(starvation * 100 < 45){
            return false;
        }
        return true;
    }

    public int immigrants(int population, int acresOwned, int grainInStorage){
        return (20 * acresOwned + grainInStorage) / (100 * population) + 1;
    }

    public int harvest(int acres){ //, int bushelsUsedAsSeed  Removed the extra parameter
        int myNewNumber = rand.nextInt(6)+1;
        int harvestSeeds = acres * myNewNumber;
        return harvestSeeds;
    }
    public int grainEatenByRats(int bushels){
        int grainEatenByRats = 0;
        if (rand.nextInt(100) < 40) {
            int cropDestroyed = rand.nextInt(21) +10;
            grainEatenByRats = bushels * cropDestroyed/100;
            System.out.println("Rats destroyed your Crop! ");
        }
        return grainEatenByRats;
    }

    public int newCostOfLand(){

        return rand.nextInt(23 - 17 + 1) + 17;
    }

}
