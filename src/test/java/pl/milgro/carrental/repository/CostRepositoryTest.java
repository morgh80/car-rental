package pl.milgro.carrental.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.milgro.carrental.domain.Cost;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostRepositoryTest {

    @Autowired
    CostRepository repository;

    @Test
    @Transactional
    public void isSavingCost() {
        //Given
        Cost testCost = new Cost("Test", 0.0);

        //When
        Cost result = repository.save(testCost);

        //Then
        Assert.assertEquals(testCost, result);
        Assert.assertTrue(repository.findByName(testCost.getName()).isPresent());
    }

    @Test
    @Transactional
    public void isFindingById() {
        //Given
        Cost testCost = new Cost("Test", 0.0);
        Cost savedCost = repository.save(testCost);
        Long id = savedCost.getId();

        //When
        Cost result = repository.findById(id).orElse(null);

        //Then
        Assert.assertNotNull(result);
        Assert.assertEquals(id, result.getId());
    }

    @Test
    @Transactional
    public void isFindingByName() {
        //Given
        Cost testCost = new Cost("Test", 0.0);
        Cost savedCost = repository.save(testCost);
        String name = savedCost.getName();

        //When
        Cost result = repository.findByName(name).orElse(null);

        //Then
        Assert.assertNotNull(result);
        Assert.assertEquals(name, result.getName());
    }

    @Test
    @Transactional
    public void isDeletingById() {
        //Given
        Cost testCost = new Cost("Test", 0.0);
        Cost savedCost = repository.save(testCost);
        Long id = savedCost.getId();

        //When
        repository.deleteById(id);

        //Then
        Assert.assertEquals(Optional.empty(), repository.findById(id));
    }

    @Test
    @Transactional
    public void isDeletingByName() {
        //Given
        Cost testCost = new Cost("Test", 0.0);
        Cost savedCost = repository.save(testCost);
        String name = savedCost.getName();

        //When
        repository.deleteByName(name);

        //Then
        Assert.assertEquals(Optional.empty(), repository.findByName(name    ));
    }

    @After
    @Transactional
    public void cleanUp() {
        repository.deleteByName("Test");
    }

}
