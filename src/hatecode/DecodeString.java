package hatecode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DecodeString
 * Creator : duqiang
 * Date : Sep, 2017
 * Description : TODO
 */
public class DecodeString {

    /**
     * 394. Decode String
     * 
     * Given an encoded string, return it's decoded string.
     * 
     * The encoding rule is: k[encoded_string], where the encoded_string inside the
     * square brackets is being repeated exactly k times. Note that k is guaranteed
     * to be a positive integer.
     * 
     * You may assume that the input string is always valid; No extra white spaces,
     * square brackets are well-formed, etc.
     * 
     * Furthermore, you may assume that the original data does not contain any
     * digits and that digits are only for those repeat numbers, k. For example,
     * there won't be input like 3a or 2[4].
     * 
     * Examples:
     * 
     * s = "3[a]2[bc]", return "aaabcbc".
     * 
     * s = "3[a2[c]]", return "accaccacc".
     * 
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     * 
     * time : O(n); space : O(n);
     * 
     * @param s
     * @return
     */

    //similiar to caculator pattern: 
    // the whole process:
    // we get each character from the string, it has 4 cases,
    // digit [ ] and others
    // if it digit we get the number and put into counterStack
    // if we encounter [, we have to push previous string whatever into resStack,
    // and have temp variable res = "" to get new string.
    // if we encounter ], which means res got ab of [ab], and we have to get
    // previous
    // count number which is in counterStack, and we iterate counter times and
    // assign
    // to res, so finally res should be get all strings

    public String decodeString(String s) {
        if (s == null || s.length() < 1)  return "";

        String res = "";
        Stack<Integer> cntStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            char ch = s.charAt(idx);
            if (Character.isDigit(ch)) {
                int count = ch - '0';
                idx++;
                for (; (idx < s.length()) && (Character.isDigit(s.charAt(idx)));) {
                    count = count * 10 + s.charAt(idx) - '0';
                    idx++;
                }
                cntStack.push(count);
            } else if (ch == '[') {
                // here push ***2[abc], push *** to stack
                // if we have recursive [] then we have to use the res and count stack
                // to store these values
                resStack.push(res);
                res = "";
                idx++;
            } else if (ch == ']') {
                // here we pop previous results we stored in resStack, which could be
                // 3[a2[c]] case
                StringBuilder sb = new StringBuilder(resStack.pop());
                int time = cntStack.pop();
                for (int i = 0; i < time; i++) {
                    sb.append(res);
                }
                res = sb.toString();
                idx++;
            } else {
                res += ch;
                idx++;
            }
        }
        return res;
    }
    
    //this is from goog interview question:
    /*
     * {a, b}c{d,e}f 需要return 所有可能的组合，acdf，acef，bcdf，bcef。
     * followup 怎么处理 nested case:    a{b{c, d}e{f}}
     */
    
    //TODO: not finished yet, this return incorrect answer
    public static List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 1) return res;
        
        res.add("");//for bootstrap
        int idx = 0;
        while(idx < s.length()) {
            char ch = s.charAt(idx);
            if (ch != '{') {
                for(int i= 0; i< res.size();i++) res.set(i, res.get(i) + ch);
            } else {
                List<String> temp = new ArrayList<>();
                int opened = 1;
                while(ch != '}' || opened > 0) {
                    int j = idx+1;
                    char r = s.charAt(j);
                    while(j < s.length() && opened > 0) {
                        if (r == '{') opened++;
                        if (r == '}') opened--;
                        r++;
                    }
                    List<String> right = decode(s.substring(idx+1, r));
                    for(String l: res) {
                        for(String str : right) {
                            temp.add(l + str);
                        }
                    }
                    idx = r;
                }
                res = temp;
            }
            idx++;
        }
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(decode("a{b,c}"));
    }
}
