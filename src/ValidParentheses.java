import java.util.Stack;

public class ValidParentheses {
    /**
     * EASY
     * 20. Valid Parentheses
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     *
     * Example 1:
     * Input: s = "()"
     * Output: true
     *
     * Example 2:
     * Input: s = "()[]{}"
     * Output: true
     *
     * Example 3:
     * Input: s = "(]"
     * Output: false
     *
     * Constraints:
     * 1 <= s.length <= 104
     * s consists of parentheses only '()[]{}'.
     * */
    public boolean isValid(String s) {
        if (s.length() == 1) return false;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(0);
            } else if (s.charAt(i) == '{') {
                stack.push(1);
            } else if (s.charAt(i) == '[') {
                stack.push(2);
            } else if (stack.size() == 0) {
                return false;
            } else if (stack.pop() != 0 && s.charAt(i) == ')') {
                return false;
            } else if (stack.pop() != 1 && s.charAt(i) == '}') {
                return false;
            } else if (stack.pop() != 2 && s.charAt(i) == ']') {
                return false;
            }
        }

        if(stack.size() !=0) return false;

        return true;
    }

    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        System.out.println(solution.isValid("({[)}"));
    }
}
