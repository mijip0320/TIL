import java.util.*;
public class useScanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("�̸�, ����, ����, ü��, ���� ���θ� ��ĭ���� �и��Ͽ� �Է�: ");
		
		Scanner scanner = new Scanner(System.in);
		
		String name = scanner.next(); //���ڿ� �б�
		System.out.print("�̸��� "+name+", ");
		
		String city = scanner.next(); //���ڿ� �б�
		System.out.print("���ô� "+city+", ");
		
		int age = scanner.nextInt(); //���� �б�
		System.out.print("���̴� "+age+", ");
		
		double weight = scanner.nextDouble(); //�Ǽ� �б�
		System.out.print("ü���� "+weight+", ");
		
		boolean isSingle = scanner.nextBoolean(); //���� �б�
		System.out.println("���ſ��δ� "+isSingle+"�Դϴ�. ");
		
		scanner.close(); //scanner ��ü �ݱ�

	}

}
