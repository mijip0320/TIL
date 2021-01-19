# JAVA 대소문자 구분하기

- charAt을 사용하여 원하는 문자를 추출
- Character.isUpperCase()를 사용하여 해당 문자가 대문자인지 구별
- Character.isLowerCase()를 사용하여 해당 문자가 소문자인지 구별
- Character.isDigit()을 사용하여 해당 문자가 숫자인지 구별

```java
public class Solution{
	public static void main(String[] args){
        String str = "aaaa222RRRR";
        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)==true){
                System.out.println("대문자 출력");
            }else if(Character.isLowerCase(ch)==true){
                System.out.println("소문자 출력");
            }else if(Character.isDigit(ch)==true){
                System.out.println("숫자 출력");
            }else{
                System.out.println("특수 문자 출력")
            }
        }
    }

}
```

