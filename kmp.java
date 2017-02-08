import java.util.*;

public class kmp {
	static int[] kmpPreprocess(String word) {
		int[] reset = new int[word.length() + 1];
		
		int i = 0, j = -1;
		reset[0] = -1;
		
		while (i < word.length()) {
			while (j >= 0 && word.charAt(i) != word.charAt(j))
				j = reset[j];
			
			i++; j++;
			reset[i] = j;
		}
		
		return reset;
	}

	static int kmpSearch(String word, String pattern) {
		int[] reset = kmpPreprocess(pattern);
		
		int i = 0, j = 0;
		int cnt = 0;
		
		while (i < word.length()) {
			while (j >= 0 && word.charAt(i) != pattern.charAt(j))
				j = reset[j];
			
			i++; j++;
			if (j == pattern.length()) {
				cnt++;
				j = reset[j];
			}
		}
		
		return cnt;
	}
}
