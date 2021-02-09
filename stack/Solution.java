package stack;

import java.util.Objects;

// 括号匹配 Solution

public class Solution {

    public boolean isValid(String s) {
        int length = s.length();
//        if (length % 2 != 0) {
//            return false;
//        }

        ArrayStack<String> stack = new ArrayStack<>();
        for (int i = 0; i < length; i++) {
            String str = String.valueOf(s.charAt(i));
//            System.out.println(str);
            if (Objects.equals(str, "[") || Objects.equals(str, "{") || Objects.equals(str, "(")) {
                stack.push(str);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                String top = stack.pop();
                if (Objects.equals(top, "(") && !Objects.equals(str, ")")) {
                    return false;
                }
                if (Objects.equals(top, "[") && !Objects.equals(str, "]")) {
                    return false;
                }
                if (Objects.equals(top, "{") && !Objects.equals(str, "}")) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean res = solution.isValid("[{()}]}");
        System.out.println(res);
    }
}
