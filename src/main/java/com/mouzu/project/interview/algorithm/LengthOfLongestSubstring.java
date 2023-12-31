package com.mouzu.project.interview.algorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 字符串中的最长不重复子串
 * 「滑动窗口」
 *  我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表枚举子串的起始位置，而右指针即为rk；
 *  将左指针向右移动一格，表示开始枚举下一个字符作为起始位置，然后不断地向右移动右指针，但需要保证这两个指针对应的子串中没有重复的字符。
 *  在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。记录下这个子串的长度；
 *  在枚举结束后，我们找到的最长的子串的长度即为答案。
 *  abccefghiijk
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        String s=in.nextLine();
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        System.out.println("LengthOfLongestSubstring is "+ans);
    }

}
