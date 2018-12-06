package pl.milgro.carrental.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.milgro.carrental.domain.Cost;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostServiceTest {

    @Autowired
    private CostService costService;

    @Test
    @Transactional
    public void isGettingCostByName() {
        //Given
        Cost testCost = new Cost("Test", 0.0);
        costService.saveCost(testCost);

        //When
        Cost result = costService.getCostByName("Test");

        //Then
        Assert.assertEquals(testCost, result);
    }

    @Test
    @Transactional
    public void isSavingCost() {
        //Given
        Cost testCost = new Cost("Test", 1.05);

        //When
        Cost result = costService.saveCost(testCost);

        //Then
        Assert.assertEquals(testCost, result);
    }

    @Test
    @Transactional
    public void isDeletingCostById() {
        //Given
        Cost testCost = new Cost("Test", 1.05);
        Cost result = costService.saveCost(testCost);

        //When
        costService.deleteById(result.getId());

        //Then
        Assert.assertNull(costService.getCostByName("Test"));

    }

    @Test
    @Transactional
    public void isDeletingCostByName() {
        //Given
        Cost testCost = new Cost("Test", 1.05);
        Cost result = costService.saveCost(testCost);

        //When
        costService.deleteByName(result.getName());

        //Then
        Assert.assertNull(costService.getCostByName("Test"));

    }

}
