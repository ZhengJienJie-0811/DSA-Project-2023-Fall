import java.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class KeywordCounter {
	private String urlStr;
	private String content;

	public KeywordCounter(String urlStr) {
		this.urlStr = urlStr;
	}

	private String fetchContent() throws IOException {
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String retVal = "";

		String line = null;

		while ((line = br.readLine()) != null) {
			retVal = retVal + line + "\n";
		}

		return retVal;
	}

	public int countKeyword(String keyword) throws IOException {
		if (content == null) {
			content = fetchContent();
		}

		// To do a case-insensitive search, we turn the whole content and keyword into
		// upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();

		int retVal = 0;
		int i = content.length();
		int j = keyword.length();
		int n = BoyerMoore(content, keyword);
		while (n != -1) {
			content = content.substring(n + j, i);
			i = content.length();
			n = BoyerMoore(content, keyword);
			retVal++;
		}

		return retVal;
	}

	// 使用BoyerMoore Alg
	public int BoyerMoore(String T, String P) {
		int i = P.length() - 1;
		int j = P.length() - 1;
		do {
			if (P.charAt(j) == T.charAt(i)) {
				if (j == 0) {
					return i;
				} else {
					i--;
					j--;
				}
			} else {
				i = i + P.length() - min(j, 1 + last(T.charAt(i), P));
				j = P.length() - 1;
			}
		} while (i <= T.length() - 1);

		return -1;
	}

	public int last(char c, String P) {
		// Bonus: Implement last occurence function
		for (int i = P.length() - 1; i >= 0; i--) {
			if (P.charAt(i) == c) {
				return i;
			}
		}
		return -1;

	}

	public int min(int a, int b) {
		if (a < b)
			return a;
		else if (b < a)
			return b;
		else
			return a;
	}
}