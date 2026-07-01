import java.io.*;
import java.util.*;

class Solution {
    int n;
    boolean[] visited;
    Set<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        n = numbers.length();
        visited = new boolean[n];
        dfs(numbers, "", 0);
        for ( int num : set){
            System.out.println(num);
            if ( isPrime(num)){
                answer++;
            }
        }
        return answer;
    }
    void dfs(String numbers, String sum, int depth){
        for( int i = 0; i < n; i++){
            if ( !visited[i]){
                visited[i] = true;
                dfs(numbers, sum + numbers.charAt(i), depth+1);
                visited[i] = false;
            }

        }
        if ( sum != ""){
            set.add(Integer.parseInt(sum));
        }
    }
    boolean isPrime(int num){
        if ( num <= 1) return false;
        for(int i = 2; i * i <= num; i++){
            if ( num % i == 0 ){
                return false;
            } 
        }
        return true;
    }
}