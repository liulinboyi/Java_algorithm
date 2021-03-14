package LeetCode.solution387;

import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    /**
     * 暴力
     *
     * @param s
     * @return
     */
    public int firstUniqChar1(String s) {
        int length = s.length();
        int result = -1;
        int count = 0;
        // 重复元素位置，跳过
        ArrayList<Integer> skips = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            if (skips.contains(i)) {
                continue;
            }
            result = i;
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    count++;
                } else {
                    // 如果相等，添加到重复元素数组中
                    skips.add(j);
                }
            }
            if (count == length - i - 1) {
                break;
            } else {
                count = 0;
                result = -1;
            }
        }
        return result;
    }

    /**
     * 使用HashMap加速
     *
     * @param s
     * @return
     */
    public int firstUniqChar2(String s) {

        class Info {
            Integer count = null;
            Integer firstIndex = null;

            public Info(Integer count, Integer firstIndex) {
                this.count = count;
                this.firstIndex = firstIndex;
            }
        }
        int length = s.length();
        final int[] result = {-1};

        HashMap<Character, Info> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            Info value = map.get(s.charAt(i));
            if (value == null) {
                Info info = new Info(0, i);
                map.put(s.charAt(i), info);
            } else {
                value.count = value.count + 1;
                map.put(s.charAt(i), value);
            }
        }
        for (int i = 0; i < length; i++) {
            if (map.get(s.charAt(i)).count == 0) {
                return i;
            }
        }
        return -1;
//        final int[] min = {length};
//        map.forEach((key, value) -> {
//            if (value.count == 0) {
//                if (min[0] >= value.firstIndex) {
//                    min[0] = value.firstIndex;
//                }
//                result[0] = min[0];
//            }
//        });
//        return result[0];
    }

    /**
     * 使用数组加速
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {

        class Info {
            Integer count = null;
            Integer firstIndex = null;

            public Info(Integer count, Integer firstIndex) {
                this.count = count;
                this.firstIndex = firstIndex;
            }
        }
        int length = s.length();

        int[] arr = new int[256];
        for (int i = 0; i < length; i++) {
            int index = s.codePointAt(i);
            int value = arr[index];
            if (value == 0) {
                arr[index] = 1;
            } else {
                arr[index] += 1;
            }
        }
        for (int i = 0; i < length; i++) {
            int index = s.codePointAt(i);
            int value = arr[index];
            if (value == 1) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
//        s.firstUniqChar("leetcode");
        s.firstUniqChar("loveleetcode");
//        s.firstUniqChar("cbbc");
//        s.firstUniqChar("cc");
//        System.out.println(s.firstUniqChar("aadadaad"));
    }
}
