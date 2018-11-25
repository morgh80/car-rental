package pl.milgro.carrental.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.milgro.carrental.domain.Cost;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PriceListServiceTest {

    @Autowired
    private PriceListService priceListService;

    @Test
    public void isGettingCostByName() {
        //Given
        Cost testCost = new Cost("Test", 0.0);
        priceListService.saveCost(testCost);

        //When
        Cost result = priceListService.getCostByName("Test");

        //Then
        Assert.assertEquals(testCost, result);
    }

    @Test
    public void isSavingCost() {
        //Given
        Cost testCost = new Cost("Test", 1.05);

        //When
        Cost result = priceListService.saveCost(testCost);

        //Then
        Assert.assertEquals(testCost, result);
    }

    @Test
    public void isDeletingCostByName() {
        //Given
        Cost testCost = new Cost("Test", 1.05);
        Cost result = priceListService.saveCost(testCost);

        //When
        priceListService.deleteByName("Test");

        //Then
        Assert.assertNull(priceListService.getCostByName("Test"));

    }

    @After
    public void cleanUp() {
        priceListService.deleteByName("Test");
    }

}
