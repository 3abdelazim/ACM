import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class z {
    public static int[] z_func(String s) {
        int n = s.length();
        int [] z = new int[n];
        int left = 0;
        int right = 0;
        for (int i = 1; i < n; i++) {
            z[i] = Math.max(0, Math.min(right - i + 1, z[i - left]));
            while (i + z[i] < n && s.charAt(i + z[i]) == s.charAt(z[i])) {
                z[i]++;
            }
            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }
        return z;
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = br.readLine();
        String text = br.readLine();

        String s = pattern + "#" + text;
        int [] z = z_func(s);

        for (int i = 0; i < text.length(); i++) {
            if (z[pattern.length() + 1 + i] >= pattern.length()) {
                System.out.println("The substring occurs at index: " + i);
            }
        }
    }
}
