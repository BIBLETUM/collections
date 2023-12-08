import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CarMapTest {
    private CarMap carMap;

    @Before
    public void setUp() throws Exception {
        carMap = new CarHashMap();
        for (int i = 0; i < 100; i++) {
            CarOwner carOwner = new CarOwner(i, "Name" + i, "Last Name" + i);
            Car car = new Car("Brand " + i, i);
            carMap.put(carOwner, car);
        }
    }

    @Test
    public void whenClearedThenSizeMustBe0() {
        carMap.clear();
        assertEquals(0, carMap.size());
    }

    @Test
    public void whenAdded100ElementsThenSizeMustBe100() {
        assertEquals(100, carMap.size());
    }

    @Test
    public void whenRemovedCarOwnerThenSizeMustBeSmallerBy1() {
        CarOwner carOwner = new CarOwner(0, "Name" + 0, "Last Name" + 0);
        assertTrue(carMap.remove(carOwner));
        assertEquals(99, carMap.size());
        assertFalse(carMap.remove(carOwner));
    }

    @Test
    public void whenAddedExistingCarOwnerThenSizeMustNotChange() {
        CarOwner carOwner = new CarOwner(0, "Name" + 0, "Last Name" + 0);
        Car car = new Car("doesnot matter", 231);
        carMap.put(carOwner, car);
        assertEquals(car, carMap.get(carOwner));
        assertEquals(100, carMap.size());
    }

    @Test
    public void whenAddedExistingCarOwnerThenHisCarMustChangeToNewOne() {
        CarOwner carOwner = new CarOwner(0, "Name" + 0, "Last Name" + 0);
        Car oldCar = new Car("Brand " + 0, 0);
        assertEquals(oldCar, carMap.get(carOwner));
        Car newCar = new Car("new car", 231);
        carMap.put(carOwner, newCar);
        assertEquals(newCar, carMap.get(carOwner));
    }

    @Test
    public void whenGetCarOwnersSetThenItsSizeMustBe100() {
        Set<CarOwner> carOwnerSet = carMap.keySet();
        assertEquals(100, carOwnerSet.size());
    }

    @Test
    public void whenGetCarsListThenItsSizeMustBe100() {
        List<Car> cars = carMap.values();
        assertEquals(100, cars.size());
    }

}