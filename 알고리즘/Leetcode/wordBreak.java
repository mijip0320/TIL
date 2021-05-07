package Section15;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class leetcode_wordBreak_ps {
  public static boolean wordBreak(String s, List<String> wordDict) {
    boolean[] answer = new boolean[s.length()+1];
    Set<String> wordSet = new HashSet<>(wordDict);
    answer[0] = true;
    for(int i = 1; i <= s.length(); i++) { //end
      for(int j = 0; j < i; j++) { //start
        if(answer[j] && wordSet.contains(s.substring(j, i))) {
          answer[i] = true;
          break;
        }
      }

    }
    return answer[s.length()];

  }

  public static void main (String[] args) {
    String s = "leetcode";
    List<String> wordDict = new ArrayList<>();
    wordDict.add("leet");
    wordDict.add("code");
    wordBreak(s, wordDict);
  }

}
