package com.xcy.solutions.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Least Recent Used Cache: 最近最少使用
 * HashMap + Dual-way Linked List
 * HashMap: Get value by key
 * Dual-way Linked List: Maintain the usage order of nodes, and easy to add, remove or move nodes
 * - Dummy head and tail nodes to avoid null checks about the node.prev or node.next
 * - Node needs to contain key and value, the key is for remove the node from HashMap when evict the least recently used node.
 * - Move node can be splitted into remove node and add node to head
 * - capacity: max number of items in the cache, it's the number of nodes in the linked list
 */
public class LRUCache {
    private Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;
    private final int capacity;
    private int size;

    public LRUCache (int capacity) {
        this.cache = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public int get (int key) {
        if (cache.containsKey(key)){
            Node node = cache.get(key);
            moveNodeToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put (int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveNodeToHead(node);
        }else{
            Node node = new Node(key, value);
            cache.put(key, node);
            addNodeToHead(node);
            size ++;
            if (size > capacity) {
                Node lastNode = tail.prev;
                cache.remove(lastNode.key); //Remember to remove from the HashMap
                removeNode(lastNode);
                size --; // Remember to update the size
            }
        }
    }

    /**
     * Move the node can be divided into remove node and add node to head
     * @param node
     */
    private void moveNodeToHead(Node node){
        if (head.next == node ){
            return;
        }
        removeNode(node);
        addNodeToHead(node);
    }

    private void addNodeToHead(Node node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    /**
     * When calling the removing node, there at least one node between head and tail,
     * @return the removed node
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        private Node (){

        }
        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
