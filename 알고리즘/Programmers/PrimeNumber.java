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
	
	public static void recursive(String[] n, int depth, int k, Set set) { //���� ����
		//detph: ���� �� ���� ����
		//k : ���� ���� ����
		if(depth == k) {//���ϴ� k���� ���ڰ� ���õ����Ƿ� ���̻� ��������X
			print(n,k,set);
			
		}else { //���� k��ŭ ���� ������ ���� �ʾ��� ���
			for(int i =depth; i<n.length; i++) { //depth�� ���ڿ� ���̱��� ���鼭 ���� ���̸� �ø���
				swap(n,i,depth); //�̶� ���� ������ �ٲ�
				recursive(n,depth+1,k,set); //�ٲ� �������� �ٽ� ��ͷ� depth+1�� ȣ��
				//��͸� ��� ȣ���ϴ� if���� �ɸ��� �Ǹ� �Ʒ� swap���� �Ѿ
				swap(n,i,depth);//���� ���� �ϼ�, ���󺹱�(���ϸ� n ��뿡 ������ ����)
			}
		}
	}
	
	public static void swap(String[] n, int i, int j) { //���� ���� �ٲٱ� �Լ�
		String temp = n[i];
		n[i] = n[j];
		n[j] = temp;
	}
	
	public static void print(String[] n, int k, Set set) { //���� ����
		//StringBuilder�� append �޼ҵ带 ���� string�� ������ �� �ִ�.
		StringBuilder s = new StringBuilder();
		for(int i=0; i<k; i++) {
			//System.out.print(n[i]);
			s.append(n[i]); //���� ����
		}
		
		primeNumberCheck(set,s); //�ش� ���ڰ� �Ҽ����� üũ
	}
	
	public static void primeNumberCheck(Set set, StringBuilder s) { //�Ҽ� üũ
		int num = Integer.parseInt(String.valueOf(s)); //�Է¹��� ���ڸ� ������ ��ȯ
		boolean prime = true;  //�Ҽ����� üũ���ִ� �Ҹ���(true�� �ʱ�ȭ)
		if(num<=1) { //1�� �Ҽ��� �ƴϱ⿡ set�� ���� x
			System.out.println("Prime number is one");
		}
		
		for(int i=2; i<=Math.sqrt(num); i++) { //�Է¹��� ������ ��Ʈ������ ��ȸ�ϸ鼭 �Ҽ����� �Ǻ�
			if(num%1==0) { //��������(�������� 0�̸�) �Ҽ� x, break;
				prime = false;
				break;
			}
		}
		if(prime) { //�������� ���� ���ٸ� �Ҽ��̹Ƿ� set�� �߰�, Hashset���� �ߺ��� �Ÿ�.
			set.add(num);
		}
	}

}

