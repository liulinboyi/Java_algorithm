package KMP;

import java.util.ArrayList;

/**
 * 学习KMP
 * https://www.bilibili.com/video/BV1PD4y1o7nd
 */
public class KMP {
    public KMP() {

    }

    // aabaabaaf
    // aabaaf
    // 查找最长相等前后缀
    public int findEqualFrontTail(String s) {
        int equalSubsLength = 0;
        // 前缀们
        ArrayList<String> front = new ArrayList<>();
        // 后缀们
        ArrayList<String> tail = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            front.add(s.substring(0, i));
        }
        for (int i = s.length() - 1; i > 0; i--) {
            String subs = s.substring(i, s.length());
            tail.add(subs);
            if (front.contains(subs)) {
                if (subs.length() > equalSubsLength) {
                    equalSubsLength = subs.length();
                }
            }
        }
        return equalSubsLength;
    }

    public ArrayList<Integer> search1(String str) {
        ArrayList<Integer> table = new ArrayList<>();
        for (int i = 1; i < str.length() + 1; i++) {
            String subs = str.substring(0, i);
            // 每个子串寻找最长相等前后缀
            Integer res = findEqualFrontTail(subs);
//            System.out.println(res);
            // 将寻找到的每个子串寻找最长相等前后缀存入ArrayList，即前缀表
            table.add(res);
//            System.out.println(table);
        }
        System.out.println(table);
        return table;
    }

    public ArrayList<Integer> search(String str) {
        // a  a  b  a  a  f
        // j  i             str[i] == str[j] :=> j++;   next: [0]
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        //   ji             next.add(i,j) :=> i++;      next: [0,1]
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        //    j  i          j >= 1 && str[i] != str[j] :=> j = next.get(0) :=> j = 0
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        // j     i          next.add(i,j) :=> i++;      next: [0,1,0]
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        // j        i       str[i] == str[j] :=> j++;
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        //    j     i       next.add(i, j) :=> i++;     next: [0,1,0,1]
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        //       j     i    str[i] == str[j] :=> j++;
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        //       j     i    nex.add(i, j)  :=> i++;     next: [0,1,0,1,2]
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        //       j        i  j >= 1 && str[i] != str[j] :=> j = next.get(1) :=> j = 1
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        //    j           i  j >= 1 && str[i] != str[j] :=> j = next.get(0) :=> j = 0
        // 0  1  2  3  4  5

        // a  a  b  a  a  f
        // j              i  next.add(i,j) :=>           next: [0,1,0,1,2,0]
        // 0  1  2  3  4  5

        int j = 0;
        ArrayList<Integer> next = new ArrayList<>();
        next.add(0);
        for (int i = 1; i < str.length(); i++) {
            while (j >= 1 && str.charAt(i) != str.charAt(j)) { // 前后缀不同
                j = next.get(j - 1); // 向前回溯
            }
            if (str.charAt(i) == str.charAt(j)) { // 找到相同的前后缀
                j++;
            }
            next.add(i, j); // 将j（前后缀的长度），赋值给next[i]
        }
        return next;
    }

    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        ArrayList<Integer> next = search(needle);
        int i = 0;
        int j = 0;
        while (i < haystack.length()) {
            while (j >= 1 && haystack.charAt(i) != needle.charAt(j)) {
                j = next.get(j - 1);
            }

            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }

            if (j == needle.length()) {
                return i - needle.length() + 1;
            }
            i++;
        }
        return -1;
    }

    private boolean check(String str, String sub, Integer front) {
        System.out.println(str.substring(front, front + sub.length()) + " | " + sub);
        return str.substring(front, front + sub.length()).equals(sub);
    }

    public static void main(String[] args) {
        KMP s = new KMP();
        System.out.println(s.check("hello", "ll", s.strStr("hello", "ll")));
        System.out.println(s.check("aabaabaaf", "aabaaf", s.strStr("aabaabaaf", "aabaaf")));
        System.out.println(s.check("aabaabaaf", "", s.strStr("aabaabaaf", "")));
    }
}
