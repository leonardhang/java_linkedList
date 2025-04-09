import org.example.CustomList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomListTest {
    @Test
    public void testSize() {
        CustomList<String> customerList = new CustomList<>() {};
        customerList.add("1");
        assertEquals(1, customerList.size());
    }
    @Test
    public void testZeroSize() {
        CustomList<String> customerList = new CustomList<>();
        assertEquals(0, customerList.size());
    }
    @Test
    public void testTrueIsEmpty() {
        CustomList<String> customerList = new CustomList<>();
        assertTrue(customerList.isEmpty());
    }
    @Test
    public void testFalseIsEmpty() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        assertFalse(customerList.isEmpty());
    }
    @Test
    public void testToArray() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("3");
        String[] target = new String[3];
        customerList.toArray(target);
        String[] expected = {"1", "2", "3"};
        assertArrayEquals(expected, target);
    }
    @Test
    public void testUpperSizeToArray() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("3");
        String[] target = new String[4];
        customerList.toArray(target);
        String[] expected = {"1", "2", "3", null};
        assertArrayEquals(expected, target);
    }
    @Test
    public void testLowerSizeToArray() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("3");
        String[] target = new String[2];
        target = customerList.toArray(target);
        String[] expected = {"1", "2", "3"};
        assertArrayEquals(expected, target);
    }
    @Test
    public void testStringAdd() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("a");
        assertEquals("a", customerList.get(2));
    }
    @Test
    public void testIntAdd() {
        CustomList<Integer> customerList = new CustomList<>();
        customerList.add(1);
        customerList.add(2);
        customerList.add(3);
        assertEquals(3, customerList.get(2));
    }
    @Test
    public void testObjectAdd() {
        CustomList<Object> customerList = new CustomList<>();
        Person newPerson = new Person("test1", 1, 1);
        customerList.add(newPerson);
        assertEquals(newPerson, customerList.get(0));
    }

    @Test
    public void testClear() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("3");
        customerList.clear();
        assertEquals(0, customerList.size());
    }

    @Test
    public void testNotEquals() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        CustomList<String> compareList = new CustomList<>();
        compareList.add("2");
        assertNotEquals(customerList, compareList);
    }

    @Test
    public void testEquals() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        CustomList<String> compareList = new CustomList<>();
        compareList.add("1");
        assertEquals(customerList, compareList);
    }

    @Test
    public void testGet() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        assertEquals("1", customerList.get(0));
    }

    @Test
    public void testSet() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.set(0, "2");
        assertEquals("2", customerList.get(0));
    }

    @Test
    public void testAddByIndex() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("3");
        customerList.add(1, "a");
        assertEquals("a", customerList.get(1));
        assertEquals("2", customerList.get(2));
    }

    @Test
    public void testAddByIndexHead() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add(0, "a");
        assertEquals("a", customerList.get(0));
        assertEquals(2, customerList.size());
    }

    @Test
    public void testAddByIndexTail() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add(customerList.size(), "a");
        assertEquals("a", customerList.get(customerList.size() -1));
        assertEquals(3, customerList.size());
    }

    @Test
    public void testAddByIndexOutBounds() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
            customerList.add(2, "a");
        });
        assertEquals("out of bounds", e.getMessage());
    }

    @Test
    public void testRemove() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.remove(0);
        assertEquals("2", customerList.get(customerList.size() -1));
        assertEquals(1, customerList.size());
    }

    @Test
    public void testRemoveOutOfBounds() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        Exception e = assertThrows(IndexOutOfBoundsException.class, () -> {
            customerList.remove(2);
        });
        assertEquals("out of bounds", e.getMessage());
    }

    @Test
    public void testIndexOf() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        int targetIndex = customerList.indexOf("2");
        assertEquals(1, targetIndex);
        int target2Index = customerList.indexOf(2);
        assertEquals(-1, target2Index);
        customerList.add("2");
        int target3Index = customerList.indexOf("2");
        assertEquals(1, target3Index);
    }

    @Test
    public void testIndexOfOutOfBounds() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        int targetIndex = customerList.indexOf("3");
        assertEquals(-1, targetIndex);
    }

    @Test
    public void testLastIndexOf() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("2");
        int targetIndex = customerList.lastIndexOf("2");
        assertEquals(2, targetIndex);
        int target1Index = customerList.lastIndexOf(2);
        assertEquals(-1, target1Index);
    }

    @Test
    public void testLastIndexOfOutOfBounds() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        int targetIndex = customerList.lastIndexOf("3");
        assertEquals(-1, targetIndex);
    }

    @Test
    public void testAddFirst() {
        CustomList<String> customerList = new CustomList<>();
        customerList.addFirst("1");
        assertEquals("1", customerList.get(0));
        customerList.addFirst("2");
        assertEquals("2", customerList.get(0));
    }

    @Test
    public void testAddLast() {
        CustomList<String> customerList = new CustomList<>();
        customerList.addLast("1");
        assertEquals("1", customerList.get(customerList.size()-1));
        customerList.addLast("2");
        assertEquals("2", customerList.get(customerList.size()-1));
    }

    @Test
    public void testGetFirst() {
        CustomList<String> customerList = new CustomList<>();
        customerList.addLast("1");
        customerList.addLast("2");
        customerList.addLast("3");
        assertEquals("1", customerList.getFirst());
    }

    @Test
    public void testGetLast() {
        CustomList<String> customerList = new CustomList<>();
        customerList.addLast("1");
        customerList.addLast("2");
        customerList.addLast("3");
        assertEquals("3", customerList.getLast());
    }

    @Test
    public void testRemoveFirst() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("3");
        customerList.removeFirst();
        assertEquals("2", customerList.get(0));
    }

    @Test
    public void testRemoveLast() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("3");
        customerList.removeLast();
        assertEquals("2", customerList.get(customerList.size() -1));
    }

    @Test
    public void testReversed() {
        CustomList<String> customerList = new CustomList<>();
        customerList.add("1");
        customerList.add("2");
        customerList.add("3");
        customerList.reversed();
        CustomList<String> compareList = new CustomList<>();
        compareList.add("3");
        compareList.add("2");
        compareList.add("1");
        assertEquals(compareList, customerList);
    }

    @Test
    public void testSort() {
        CustomList<Integer> customerList = new CustomList<>();
        customerList.add(11);
        customerList.add(3);
        customerList.add(8);
        customerList.add(15);
        customerList.add(5);
        customerList.add(21);
        customerList.add(13);
        customerList.sort((a, b) -> a -b);
        CustomList<Integer> expected = new CustomList<>();
        expected.add(3);
        expected.add(5);
        expected.add(8);
        expected.add(11);
        expected.add(13);
        expected.add(15);
        expected.add(21);
        assertEquals(expected,customerList);
    }

    public static class Person {
        private String name;
        private int age;
        private int sex;
        public Person(String name,int age,int sex) {
            this.name=name;
            this.age =age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }

}
