import java.io.*;
import java.util.*;

class Solution {
    boolean[] visited;
    int n;
    Set<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        n = numbers.length();
        visited = new boolean[n];
        int answer = 0;
        dfs(numbers, "", 0);
        //System.out.println("set = " + set);
        for( int s : set){
            if(isPrime(s)){
                answer++;
            }
        }
        return answer;
    }
    void dfs(String numbers, String number, int depth){
        for( int i = 0; i < n; i ++){
            if ( !visited[i] ){
                visited[i] = true;
                dfs(numbers, number + numbers.charAt(i), depth + 1);
                visited[i] = false;
            }
        }
        if ( !number.isEmpty() ){
            set.add(Integer.parseInt(number) );
        }
    }
    boolean isPrime(int prime){
        if ( prime <= 1){
            return false;
        }
        for( int i = 2; i * i <= prime; i++){
            if ( prime % i == 0 ) {
                return false;
            }
        }
        //System.out.println("prime = " + prime);
        return true;
    }
}