import java.util.HashSet;
import java.util.Set;

public class PrimeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String numbers = "011";
		
		String[] n = numbers.split("");//["0","1","1"]
		int[] numArr = new int[n.length]; //[0,1,1]
		
		Set<Integer> answer = new HashSet<>();
		int ans = 0;
		
		for(int i=0; i<numArr.length; i++) {
			numArr[i] = Integer.parseInt(n[i]);
		}

		for(int i=1; i<=numArr.length; i++) {
			recursive(n,0,i,answer);
		}
		 ans = answer.size();
		 System.out.print(ans);
	}
	
	public static void recursive(String[] n, int depth, int k, Set set) { //숫자 조합
		//detph: 현재 내 숫자 길이
		//k : 만들 숫자 길이
		if(depth == k) {//원하는 k개의 숫자가 세팅됐으므로 더이상 순열생성X
			print(n,k,set);
			
		}else { //아직 k만큼 길이 생성이 되지 않았을 경우
			for(int i =depth; i<n.length; i++) { //depth터 문자열 길이까지 돌면서 숫자 길이를 늘린다
				swap(n,i,depth); //이때 숫자 조합을 바꿈
				recursive(n,depth+1,k,set); //바뀐 조함으로 다시 재귀로 depth+1로 호출
				//재귀를 계속 호출하다 if문에 걸리게 되면 아래 swap으로 넘어감
				swap(n,i,depth);//숫자 조합 완성, 원상복구(안하면 n 사용에 문제에 생김)
			}
		}
	}
	
	public static void swap(String[] n, int i, int j) { //순서 조합 바꾸기 함수
		String temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}
	
	public static void print(String[] n, int k, Set set) { //숫자 연결
		//StringBuilder의 append 메소드를 통해 string을 연결할 수 있다.
		StringBuilder s = new StringBuilder();
		for(int i=0; i<k; i++) {
			//System.out.print(n[i]);
			s.append(n[i]); //숫자 연결
		}
		
		primeNumberCheck(set,s); //해당 숫자가 소수인지 체크
	}
	
	public static void primeNumberCheck(Set set, StringBuilder s) { //소수 체크
		int num = Integer.parseInt(String.valueOf(s)); //입력받은 문자를 정수로 변환
		boolean prime = true;  //소수인지 체크해주는 불리안(true로 초기화)
		if(num<=1) { //1은 소수가 아니기에 set에 저장 x
			System.out.println("Prime number is one");
		}
		
		for(int i=2; i<=Math.sqrt(num); i++) { //입력받은 숫자의 루트값까지 순회하면서 소수인지 판별
			if(num%1==0) { //나눠지면(나머지가 0이면) 소수 x, break;
				prime = false;
				break;
			}
		}
		if(prime) { //나눠지는 값이 없다면 소수이므로 set에 추가, Hashset에서 중복을 거름.
			set.add(num);
		}
	}

}

