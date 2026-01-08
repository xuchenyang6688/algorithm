package com.xcy.solutions.stack;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155. Min Stack
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 * Time Complexity: O(1)
 * Space Complexity: O(N)
 *
 * Key Insight: Maintain a parallel minStack that tracks the current minimum for each stack level.
 * - On push: minStack.push(Math.min(val, minStack.peek()))  // O(1)
 * - On pop: Synchronously pop both stacks                  // O(1)
 * - getMin: Return minStack.peek()                         // O(1)
 * Optimization: Initialize minStack with Integer.MAX_VALUE sentinel to avoid null checks.
 */
public class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minimumStack;
    public MinStack(){
        stack = new ArrayDeque<>();
        minimumStack = new ArrayDeque<>();
        // Sentinel: eliminates null checks for minStack
        minimumStack.push(Integer.MAX_VALUE);
    }

    public int getMin(){
        return minimumStack.peek();
    }

    public void push(int value){
//        if (minimumStack.isEmpty()){
//            stack.push(value);
//            minimumStack.push(value);
//        }else{
//            int currentMin = minimumStack.peek();
//            stack.push(value);
//            minimumStack.push(Math.min(value, currentMin));
//        }

        stack.push(value);
        // No need to check if minimuStack is empty before calling peek(), thanks to the sentinel
        minimumStack.push(Math.min(minimumStack.peek(), value));

    }
    public void pop(){
        stack.pop();
        minimumStack.pop();

    }

    public int top(){
        return stack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        assert minStack.top() == 1 && minStack.getMin() == 1;
        minStack.push(2);
        assert minStack.top() == 2 && minStack.getMin() == 1;
        minStack.push(3);
        assert minStack.top() == 3 && minStack.getMin() == 1;
        minStack.push(-1);
        assert minStack.top() == -1 && minStack.getMin() == -1;
        minStack.pop();
        assert minStack.top() == 3 && minStack.getMin() == 1;
    }
}
