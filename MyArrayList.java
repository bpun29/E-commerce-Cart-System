
import java.util.Arrays;

public class MyArrayList<T> {
	private static final int DEFAULT_SIZE = 10;
    private Object[] elements;
    private int size;

    public MyArrayList() {
        elements = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void add(T element) {
        if (size == elements.length) {
        	reSize();
        }
        elements[size] = element;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return (T) elements[index];
    }
    public int get(Object o) {
		for(int i=0;i<size;i++) {
			if(elements[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
    }
    private void reSize() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }
    public boolean contains(Object o) {
		return get(o) != -1;
	}
    public void set(int i, Object o) {
    	elements[i] = o;
	}
}