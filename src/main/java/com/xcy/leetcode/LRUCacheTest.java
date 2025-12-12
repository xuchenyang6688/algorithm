package com.xcy.leetcode;

public class LRUCacheTest {
    //Need to add -ea in the VM options when running the program to enable assertions
    public static void main(String[] args) {
        LRUCacheTest test = new LRUCacheTest();
        test.testGetNonExistingKey();
        test.testPutAndGet();
        test.testExceedingCapacity();
        test.testGetMoveNodeAndExceedingCapacity();
        test.test();
        System.out.println("All tests passed.");
    }

    void testGetNonExistingKey() {
        LRUCache lruCache = new LRUCache(1);
        assert lruCache.get(1) == -1;
    }

    void testPutAndGet() {
        LRUCache lruCache = new LRUCache(1);
        lruCache.put(1, 10);
        assert lruCache.get(1) == 10;

        lruCache.put(1, 20);
        assert lruCache.get(1) == 20;
    }

    void testExceedingCapacity() {
        LRUCache lruCache = new LRUCache(1);
        lruCache.put(1, 10);
        lruCache.put(2, 20); // Exceeds capacity
        assert lruCache.get(2) == 20;
        assert lruCache.get(1) == -1; // Least recently used key
    }

    void testGetMoveNodeAndExceedingCapacity() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 10);
        lruCache.put(2, 20);
        assert lruCache.get(1) == 10; // Access key 1

        lruCache.put(3, 30); // Exceeds capacity
        assert lruCache.get(3) == 30;
        assert lruCache.get(2) == -1; // Key 2 should be evicted
    }

    void test(){
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        assert lRUCache.get(1) == 1;
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        assert lRUCache.get(2) == 2;
        assert lRUCache.get(1) == 1;    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        assert lRUCache.get(3) == 3;
        assert lRUCache.get(2) == -1;    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        assert lRUCache.get(4) == 4;
        assert lRUCache.get(1) == -1;    // return -1 (not found)
        assert lRUCache.get(3) == 3;    // return 3
        assert lRUCache.get(4) == 4;    // return 4
    }
}
