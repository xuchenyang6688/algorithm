package com.xcy.solutions.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 32. Longest Valid Parentheses
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
public class LongestValidParenthesesSolution {

    /**
     * Returns the length of the longest valid parentheses substring.
     *
     * Approach: Stack with indices
     * 1. Use a stack to store indices of unmatched '(' and potential boundaries
     * 2. Push -1 initially as a boundary marker for calculating lengths
     * 3. For each character:
     *    - If '(': push current index onto stack
     *    - If ')':
     *        a. Pop the top (matching '(' or boundary)
     *        b. If stack becomes empty, push current index as new boundary
     *        c. Else, calculate length: current index - stack.peek()
     * 4. Track maximum length throughout
     *
     * Key Insight: The stack always contains indices that represent:
     * - Unmatched '(' positions
     * - The last unmatched ')' position (as boundary for length calculation)
     * The difference between current index and stack top gives the length
     * of valid parentheses ending at current position.
     *
     * Visualization for s = ")()())":
     * i=0: ')', stack=[-1], pop → empty, push 0 → stack=[0]
     * i=1: '(', push 1 → stack=[0,1]
     * i=2: ')', pop → stack=[0], length=2-0=2, max=2
     * i=3: '(', push 3 → stack=[0,3]
     * i=4: ')', pop → stack=[0], length=4-0=4, max=4
     * i=5: ')', pop → empty, push 5 → stack=[5]
     * Result: max=4
     *
     * Time Complexity: O(n) - single pass through string
     *   - Each character pushed/popped at most once
     *
     * Space Complexity: O(n) - stack can hold up to n indices in worst case
     */
    public int longestValidParentheses(String s){
        if (s == null || s.isEmpty()){
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>(s.length());
        stack.push(-1);
        char[] charArray = s.toCharArray();
        int maxLength = 0;
        for (int i=0; i<charArray.length; i++){
            char character = charArray[i];
            if(character == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    maxLength = Math.max(maxLength, i-stack.peek());
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestValidParenthesesSolution solution = new LongestValidParenthesesSolution();
        assert 2 == solution.longestValidParentheses("(()");
        assert 6 == solution.longestValidParentheses("()())()()()");
        assert 4 == solution.longestValidParentheses("(()()");
    }
}
