import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarCollectionTest {
    private CarCollection<Car> carCollection;

    @Before
    public void setUp() throws Exception {
        carCollection = new CarLinkedList();
        for (int i = 0; i < 100; i++) {
            carCollection.add(new Car("Brand " + i, i));
        }
    }

    @Test
    public void checkForEach(){
        int index = 0;
        for (Car car : carCollection) {
            index++;
        }
        assertEquals(100, index);
    }
}