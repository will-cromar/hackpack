public class sieve {
    public static void main(String[] args) {
        int n = (int) 1e6;
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++)
            isPrime[i] = true;
        
        for (int factor = 2; factor * factor <= n; factor++) {
            if (isPrime[factor]) {
                for (int j = factor; j * factor <= n; j++) {
                    isPrime[factor * j] = false;
                }
            }
        } 
    }
}