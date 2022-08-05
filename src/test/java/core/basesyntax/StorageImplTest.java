package core.basesyntax;

import core.basesyntax.impl.StorageImpl;
import org.junit.Assert;
import org.junit.Test;

public class StorageImplTest {
    @Test
    public void checkSizeIsNonStatic() {
        Storage<Integer, String> firstInstance = new StorageImpl<>();
        firstInstance.put(1, "Element 1");
        firstInstance.put(2, "Element 2");
        firstInstance.put(3, "Element 3");

        Assert.assertEquals("With three elements added to the storage, its size should be 3",
                3, firstInstance.size());

        Storage<Integer, String> secondInstances = new StorageImpl<>();

        Assert.assertEquals("After creating second empty storage instance its' size should be 0",
                0, secondInstances.size());
    }

    @Test
    public void addElementsToStorage() {
        Storage<Integer, String> storage = new StorageImpl<>();
        String elementOne = "Element 1";
        String elementTwo = "Element 2";
        String elementTree = "Element 3";

        storage.put(1, elementOne);
        storage.put(2, elementTwo);
        storage.put(3, elementTree);

        Assert.assertEquals("With three elements added to the storage, its size should be 3",
                3, storage.size());
        Assert.assertEquals(elementOne, storage.get(1));
        Assert.assertEquals(elementTwo, storage.get(2));
        Assert.assertEquals(elementTree, storage.get(3));
    }

    @Test
    public void getElementWhenKeyDontExist() {
        Storage<Integer, String> storage = new StorageImpl<>();

        Assert.assertNull(
                "When element with this key doesn't exist, the method should return \"null\"",
                storage.get(1234321));
    }

    @Test
    public void getElementWithKeyNull() {
        Storage<Integer, String> storage = new StorageImpl<>();
        String[] elemArr = new String[]{
                "Element 1"
                , "Element 2"
                , "Element 3"
                , "Element 4"
                , "Element 5"
                , "Element 6"
                , "Element 7"
                , "Element 8"
                , "Element 9"
                , "Element 10"
        };
        storage.put(1, elemArr[0]);
        storage.put(null, elemArr[1]);
        storage.put(3, elemArr[2]);
        storage.put(4, elemArr[3]);
        storage.put(5, elemArr[4]);
        storage.put(6, elemArr[5]);
        storage.put(7, elemArr[6]);
        storage.put(8, elemArr[7]);
        storage.put(9, elemArr[8]);
        storage.put(10, elemArr[9]);
        Assert.assertEquals(
                "Test failed! Method get(null) should return value",
                elemArr[1],
                storage.get(null));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[0],
                storage.get(1));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[2],
                storage.get(3));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[3],
                storage.get(4));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[4],
                storage.get(5));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[5],
                storage.get(6));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[6],
                storage.get(7));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[7],
                storage.get(8));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[8],
                storage.get(9));
        Assert.assertEquals(
                "Test failed! Method get(key) should return value",
                elemArr[9],
                storage.get(10));
    }

    @Test
    public void getElementWhenKeyIsObject() {
        Storage<Cat, String> storage = new StorageImpl<>();
        String elementOne = "Element 1";
        String elementTwo = "Element 2";
        String elementTree = "Element 3";
        Cat firstCat = new Cat("Myrchyk", "white");
        Cat secondCat = new Cat("Barsik", "black");
        Cat thirdCat = new Cat("Musia", "grey");

        storage.put(firstCat, elementOne);
        storage.put(secondCat, elementTwo);
        storage.put(thirdCat, elementTree);
        Cat sameSecondCat = new Cat("Barsik", "black");

        Assert.assertEquals(
                "Test failed! Method get(key) should return correct value",
                elementTwo, storage.get(sameSecondCat));
    }

    @Test
    public void addTwoElementsWithSameKey() {
        Storage<Cat, String> storage = new StorageImpl<>();
        Cat cat = new Cat("Myrchyk", "white");
        Cat sameCat = new Cat("Myrchyk", "white");
        String elementOne = "One";
        String elementTwo = "Two";

        storage.put(cat, elementOne);
        storage.put(sameCat, elementTwo);

        Assert.assertEquals(
                "With two elements added with the same key, "
                        + "the value should be rewritten",
                elementTwo,
                storage.get(cat));
        Assert.assertEquals("With two elements added with the same key, "
                        + "the storage size should be 1",
                1, storage.size());
    }

    @Test
    public void addTwoElementsWithNullKey() {
        Storage<Cat, String> storage = new StorageImpl<>();
        String elementOne = "One";
        String elementTwo = "Two";

        storage.put(null, elementOne);
        storage.put(null, elementTwo);

        Assert.assertEquals(
                "With two elements added with the same \"null\" key, "
                        + "the value should be rewritten",
                elementTwo,
                storage.get(null));
        Assert.assertEquals("With two elements added with the same \"null\" key, "
                        + "the storage size should be 1",
                1, storage.size());
    }
}
