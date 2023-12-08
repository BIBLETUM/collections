import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarHashMap implements CarMap {

    private static final int INIT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    private Entry[] array = new Entry[INIT_CAPACITY];
    private int size = 0;

    @Override
    public void put(CarOwner key, Car value) {
        if ((LOAD_FACTOR * array.length) <= size) {
            increaseArray();
        }
        if(add(key, value, array)){
            size++;
        }
    }

    private boolean add(CarOwner key, Car value, Entry[] dst) {
        int position = getElementPosition(key, dst.length);
        if (dst[position] == null) {
            dst[position] = new Entry(key, value, null);
            return true;
        } else {
            Entry element = dst[position];
            while (element.next != null) {
                if (element.key.equals(key)) {
                    element.value = value;
                    return false;
                } else {
                    element = element.next;
                }
            }
            if (element.key.equals(key)) {
                element.value = value;
                return false;
            } else {
                element.next = new Entry(key, value, null);
                return true;
            }
        }
    }

    private void increaseArray() {
        Entry[] newArray = new Entry[array.length * 2];
        for (Entry entry : array) {
            Entry element = entry;
            while (element != null) {
                add(element.key, element.value, newArray);
                element = element.next;
            }
        }
        array = newArray;
    }

    @Override
    public Car get(CarOwner key) {
        int position = getElementPosition(key, array.length);
        Entry element = array[position];
        while (element != null){
            if(element.key.equals(key)){
                return element.value;
            }
            element = element.next;
        }
        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> owners = new HashSet<>();
        for (Entry entry : array) {
            Entry element = entry;
            while (element != null) {
                owners.add(element.key);
                element = element.next;
            }
        }
        return owners;
    }

    @Override
    public List<Car> values() {
        List<Car> cars = new ArrayList<>();
        for (Entry entry : array) {
            Entry element = entry;
            while (element != null) {
                cars.add(element.value);
                element = element.next;
            }
        }
        return cars;
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, array.length);
        if (array[position] == null) {
            return false;
        }
        Entry entryPrev = array[position];
        Entry entry = entryPrev.next;
        if (entryPrev.key.equals(key)) {
            array[position] = entry;
            size--;
            return true;
        }
        while (entry != null) {
            if (entry.key.equals(key)) {
                entryPrev.next = entry.next;
                size--;
                return true;
            } else {
                entryPrev = entry;
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = new Entry[INIT_CAPACITY];
        size = 0;
    }

    private int getElementPosition(CarOwner key, int arrayLen) {
        return Math.abs(key.hashCode()) % arrayLen;
    }

    private static class Entry {
        CarOwner key;
        Car value;
        Entry next;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
