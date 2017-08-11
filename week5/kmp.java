import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class kmp {
    public static int[] fail_func(String s) {
        int n = s.length();
        int [] f = new int[n];
        for (int i = 1; i < n; i++) {
            int j = f[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = f[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            f[i] = j;
        }
        return f;
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = br.readLine();
        String text = br.readLine();

        int [] f = fail_func(pattern);

        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = f[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                System.out.println("The substring occurs at index: " + (i - j + 1));
                j = f[j - 1];
            }
        }
    }
}
