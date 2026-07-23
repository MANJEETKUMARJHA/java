import java.util.*;

public class food {

    static class Food {
        long taste;
        int index;
        long times;

        Food(long taste, int index, long times) {
            this.taste = taste;
            this.index = index;
            this.times = times;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long m = sc.nextLong();

        long[] v = new long[n];
        long[] d = new long[n];

        for (int i = 0; i < n; i++)
            v[i] = sc.nextLong();

        for (int i = 0; i < n; i++)
            d[i] = sc.nextLong();

        PriorityQueue<Food> pq = new PriorityQueue<>(
                (a, b) -> Long.compare(b.taste, a.taste));

        // Initially insert first purchase of every food
        for (int i = 0; i < n; i++) {
            pq.offer(new Food(v[i], i, 1));
        }

        long answer = 0;

        while (m-- > 0 && !pq.isEmpty()) {

            Food current = pq.poll();

            if (current.taste <= 0)
                break;

            answer += current.taste;

            long nextTaste = v[current.index] - d[current.index] * current.times;

            pq.offer(new Food(nextTaste,
                    current.index,
                    current.times + 1));
        }

        System.out.println(answer);
    }
}