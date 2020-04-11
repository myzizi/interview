package hatecode._1000_1999;

import java.util.*;
public class _1156SwapForLongestRepeatedCharacterSubstring {
 /*
 1156. Swap For Longest Repeated Character Substring
 Given a string text, we are allowed to swap two of the characters 
 in the string. Find the length of the longest substring with repeated characters.

 

Example 1:

Input: text = "ababa"
Output: 3
 */
    //thinking process: 
    
    //
    public int maxRepOpt1(String s) {
        HashMap<Character,List<Integer>> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            map.computeIfAbsent(s.charAt(i), v->new ArrayList<>()).add(i);
        }
        
        int res = 0;
        for (List<Integer> list : map.values()) {
            int cur = 1, pre = 0, sum = 1;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) == list.get(i - 1) + 1) cur++;
                else {
                    pre = list.get(i) == list.get(i - 1) + 2 ? cur : 0;
                    cur = 1;
                }
                sum = Math.max(sum, cur + pre);
            }
            res = Math.max(res, sum + (sum < list.size() ? 1 : 0));
        }
        return res;
    }
    
    //sliding window solution
    public int maxRepOpt1_SlidingWin(String text) {
        int len = text.length();

        int[] dict = new int[26];
        for (int i = 0; i < len; ++i)
            ++dict[text.charAt(i) - 'a'];

        HashMap<Character, Integer> win = new HashMap();
        int res = 0, sizeMoreThanTwo = 0, maxId = -1;

        for (int l = 0, r = 0; r < len; ++r) {
            char c = text.charAt(r);

            win.put(c, win.getOrDefault(c, 0) + 1);
            if (win.get(c) == 2)
                ++sizeMoreThanTwo;

            while (win.size() > 2 || sizeMoreThanTwo > 1) {
                c = text.charAt(l++);
                win.put(c, win.getOrDefault(c, 0) - 1);
                if (win.get(c) == 1)
                    --sizeMoreThanTwo;
                if (win.get(c) == 0)
                    win.remove(c);
            }

            for (Character _c : win.keySet()) {
                res = (win.size() == 1 || dict[_c - 'a'] > win.get(_c)) ? Math.max(res, r - l + 1)
                        : Math.max(res, r - l);
            }
        }

        return res;
    }
}