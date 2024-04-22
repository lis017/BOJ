class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
		
		int same = 0;	//같은 숫자 개수
		int erased = 0;	//0의 개수	//지워진 숫자
			
		for(int i = 0; i < lottos.length; i++) {			//로또 같은 숫자 갯수 세기
			if ( lottos[i] == 0)
				erased++;
			for ( int j = 0; j < win_nums.length; j++) {
				if (lottos[i] == win_nums[j])
					same++;

				 
			}
		}
		
		//if ( erased == 0)
			//same++;
		if ( erased == 6) {
			same++;
			erased--;
		}
        
        if ( same == 0 && erased == 0)
			same++;
		
		
        int[] answer = new int[2];       
		answer[0] = 7 - (same + erased);
		answer[1] = 7 - same;

		
		
        return answer;
		
	}
}