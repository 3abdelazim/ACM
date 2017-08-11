import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class rabin_karp {
    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = br.readLine();
        String text = br.readLine();

        long m = 1000000007;
        long base = 300;

        long hp = 0;
        for (int i = 0; i < pattern.length(); i++) {
            hp = (hp * base) % m;
            hp = (hp + (int) pattern.charAt(i)) % m;
        }

        long ht = 0;
        for (int i = 0; i < pattern.length(); i++) {
            ht = (ht * base) % m;
            ht = (ht + (int) text.charAt(i)) % m;
        }

        long power = 1;
        for (int i = 0; i < pattern.length() - 1; i++) {
            power = (power * base) % m;
        }

        if (ht == hp) {
            System.out.println("The substring occurs at index: 0");
        }
        for (int start = 1, end = pattern.length(); end < text.length(); start++, end++) {
            long tmp = ((int) text.charAt(start - 1) * power) % m;
            ht = (((ht - tmp) % m) + m) % m;
            ht = (ht * base) % m;
            ht = (ht + text.charAt(end)) % m;

            if (ht == hp) {
                System.out.println("The substring occurs at index: " + start);
            }
        }
    }
}
