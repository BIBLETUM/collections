import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarSetTest {

    private CarSet<Car> carSet;

    @Before
    public void setUp(){
        carSet = new CarHash<>();
        for (int i = 0; i < 100; i++) {
            carSet.add(new Car("Brand " + i, i));
        }
    }

    @Test
    public void whenAdded100CarThenSizeEquals100(){
        assertEquals(100, carSet.size());
    }
    @Test
    public void whenAddedCarThenSizeBiggerBy1() {
        Car car = new Car("Brand 100", 100);
        assertTrue(carSet.add(car));
        assertEquals(101, carSet.size());
    }

    @Test
    public void whenCarRemovedThenSizeSmallerBy1() {
        Car car = new Car("Brand 0",0);
        assertTrue(carSet.remove(car));
        assertEquals(99, carSet.size());
    }

    @Test
    public void whenAddedExistingCarThenSizeEquals100(){
        Car car = new Car("Brand 0",0);
        assertFalse(carSet.add(car));
        assertEquals(100, carSet.size());
    }

    @Test
    public void whenNonExistentElementRemovedThenReturnFalse(){
        Car car = new Car("ImFake", 4);
        assertFalse(carSet.remove(car));
        assertEquals(100, carSet.size());
    }

    @Test
    public void size() {
        assertEquals(100, carSet.size());
    }

    @Test
    public void clear() {
        carSet.clear();
        assertEquals(0, carSet.size());
    }

    @Test
    public void checkCarExistence(){
        Car car = new Car("Brand 0", 0);
        Car carFalse = new Car("aboba", 2131);
        assertTrue(carSet.contains(car));
        assertFalse(carSet.contains(carFalse));
    }
}