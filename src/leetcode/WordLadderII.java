package leetcode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordLadderII
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 126. Word Ladder II
 */
public class WordLadderII {
    /**
     * Given two words (beginWord and endWord), and a dictionary's word list,
     * find all shortest transformation sequence(s) from beginWord to endWord, such that:

     Only one letter can be changed at a time
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]

     Return
     [
     ["hit","hot","dot","dog","cog"],
     ["hit","hot","lot","log","cog"]
     ]

     BFS + DFS

     无向图 -> BFS -> 树 -> DFS -> 结果

     hit -> hot -> dot -> dog - cog
                -> lot -> log - cog

     map : hot (hit)
           dot (hot)
           lot (hot)
           dog (dot)
           log (lot)
           cog (dog,log)

     time : O(V + E) * wordList(max(length))  不确定
            O(n ^ 2)
     space : O(n)

     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */

    //thinking process:
    //how to think about this
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList.size() == 0) return res;

        int curNum = 1;
        int nextNum = 0;
        boolean found = false;

        Queue<String> queue = new LinkedList<>();
        HashSet<String> unvisited = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();

        HashMap<String, List<String>> map = new HashMap<>();

        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            curNum--;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch);
                    String newWord = builder.toString();
                    if (unvisited.contains(newWord)) {
                        if (visited.add(newWord)) {
                            nextNum++;
                            queue.offer(newWord);
                        }
                        if (map.containsKey(newWord)) {
                            map.get(newWord).add(word);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(word);
                            map.put(newWord, list);
                        }
                        if (newWord.equals(endWord)) {
                            found = true;
                        }
                    }
                }
            }
            if (curNum == 0) {
                if (found) break;
                curNum = nextNum;
                nextNum = 0;
                unvisited.removeAll(visited);
                visited.clear();
            }
        }
        dfs(res, new ArrayList<>(), map, endWord, beginWord);
        return res;
    }

    private void dfs(List<List<String>> res, List<String> list, HashMap<String, List<String>> map, String endWord, 
            String startWord) {
        if (endWord.equals(startWord)) {
            list.add(0, startWord);
            res.add(new ArrayList<>(list));
            // list.remove(list.size - 1)
            list.remove(0);
            return;
        }
        list.add(0, endWord);
        if (map.get(endWord) != null) {
            for (String s : map.get(endWord)) {
                dfs(res, list, map, s, startWord);
            }
        }
        list.remove(0);
    }
}
