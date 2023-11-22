import com.sun.source.tree.AssertTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarListTest {

    private CarList carList;

    @Before
    public void setUp() throws Exception {
        carList = new CarArrayList();
        for (int i = 0; i < 100; i++) {
            carList.add(new Car("Brand " + i, i));
        }
    }

    @Test
    public void whenAdded100ElementsThenSizeMustBe100() {
        assertEquals(100, carList.size());
    }

    @Test
    public void whenRemoved1ElementByIndexThenSizeSmallerByOne() {
        assertTrue(carList.removeAt(0));
        assertEquals(99, carList.size());
    }

    @Test
    public void whenRemoved1ElementThenSizeSmallerByOne() {
        Car car = new Car("DeliteMe", 5);
        carList.add(car);
        assertEquals(101, carList.size());
        assertTrue(carList.remove(car));
        assertEquals(100, carList.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse(){
        Car car = new Car("ImFake", 4);
        assertFalse(carList.remove(car));
        assertEquals(100, carList.size());
    }

    @Test
    public void whenClearedThenSizeMustBeZero(){
        carList.clear();
        assertEquals(0, carList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBoundsThenThrowException(){
        carList.get(100);
    }

    @Test
    public void whenGetThenCarReturn(){
        Car car = carList.get(0);
        assertEquals("Brand 0", car.getBrand());
        //assertEquals(new Car("Brand 0", 0), car);
    }

    @Test
    public void whenAddedByIndexIntoMiddleThenMustBeIncreased(){
        Car car = new Car("Kia", 54);
        carList.add(car, 67);
        assertEquals(101, carList.size());
        Car newCar = carList.get(67);
        assertEquals(car.getBrand(), newCar.getBrand());
    }

    @Test
    public void whenAddedByIndexIntoStartThenMustBeIncreased(){
        Car car = new Car("Kia", 54);
        carList.add(car, 0);
        assertEquals(101, carList.size());
        Car newCar = carList.get(0);
        assertEquals(car.getBrand(), newCar.getBrand());
    }

    @Test
    public void whenAddedByIndexIntoEndThenMustBeIncreased(){
        Car car = new Car("Kia", 54);
        carList.add(car, 100);
        assertEquals(101, carList.size());
        Car newCar = carList.get(100);
        assertEquals(car.getBrand(), newCar.getBrand());
    }

}