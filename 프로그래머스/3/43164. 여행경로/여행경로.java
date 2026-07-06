import java.util.*;

class Solution {
    String[][] tickets;
    boolean[] visited;
    List<String> route = new ArrayList<>();
    String[] answer;

    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        visited = new boolean[tickets.length];

        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        route.add("ICN");
        dfs("ICN");

        return answer;
    }

    boolean dfs(String current) {
        if (route.size() == tickets.length + 1) {
            answer = route.toArray(new String[0]);
            return true;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                route.add(tickets[i][1]);

                if (dfs(tickets[i][1])) {
                    return true;
                }

                route.remove(route.size() - 1);
                visited[i] = false;
            }
        }

        return false;
    }
}