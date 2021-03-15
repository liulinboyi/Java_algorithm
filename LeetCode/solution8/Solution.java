package LeetCode.solution8;

class Solution {
    public int myAtoi(String s) {
        int index = 0;
        int sign = 1;
        int total = 0;
        if (s.length() == 0) return 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(index) == ' ') {
                index++;
            } else {
                break;
            }
        }

        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        while (index < s.length()) {
            // ASCII码
            int digit = s.charAt(index) - '0';
            if (digit < 0 || digit > 9) break; // 不是数字

//            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total) {
//                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//            }
            total = 10 * total + digit;
            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            index++;
        }
        return total * sign;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
//        s.myAtoi("4193 with words");
        System.out.println(s.myAtoi("-91283472332"));
    }
}