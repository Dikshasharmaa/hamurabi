package hamurabi;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomHamurabiTest {
    Hamurabi ham;

    @Before
    public void setUp() throws Exception {
        this.ham = new Hamurabi();
    }

    @Test
    public void checkPrintSummary() {
        int yearCount = 1;
        int peopleStarved = 0;
        int newPeople = 5;
        int currentPopulation = 100;
        int harvestBushels = 3000;
        int bushelsPerAcres = 3;
        int bushelsDestroyed = 200;
        int bushelsStored = 2000;
        int ownedLand = 1000;
        int currentLandWorth = 19;
        String actual = this.ham.printSummary(yearCount, peopleStarved, newPeople, currentPopulation, harvestBushels, bushelsPerAcres, bushelsDestroyed, bushelsStored, ownedLand, currentLandWorth);
        String expected = "O great Hammurabi!\nYou are in year 1 of your ten year rule.\nIn the previous year 0 people starved to death.\nIn the previous year 5 people entered the kingdom.\nThe population is now 100.\nWe harvested 3000 bushels at 3 bushels per acre.\nRats destroyed 200 bushels, leaving 2800 bushels in storage.\nThe city owns 1000 acres of land.\nLand is currently worth 19 bushels per acre.";
        Assert.assertEquals(expected, actual);
    }
}