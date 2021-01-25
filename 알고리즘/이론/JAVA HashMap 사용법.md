# JAVA HashMap 사용법



## HashMap이란?

> Map 컬렉션

- 키와 값은 모두 객체, 키는 중복 불가, 값은 중복 가능
- 해싱(Hashing)을 사용하기 때문에 많은 양의 데이터를 검색하는 데 있어 뛰어난 성능을 보임

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcfpMTT%2FbtqEvxLt6qb%2FMXYNWUvXCKfRvNWjDMZoq0%2Fimg.png)



## 사용법

### :blue_heart:HashMap 선언

```java
HashMap<String, String> map1 = new HashMap<String, String>(); //HashMap 생성
HashMap<String, String> map2 = new HashMap<>(); //new에서 타입 파라미터 생략 가능
HashMap<String, String> map3 = new HashMap<>(map1); //map1의 모든 값을 가진 HashMap 생성
HashMap<String, String> map4 = new HashMap<>(10); //초기 용량(capacity) 지정
HashMap<String, String> map5 = new HashMap<>(10, 0.7f); //초기 capacity, load factor 지정
HashMap<String, String> map6 = new HashMal<String, String>(){{
    put("a","b");
}}
```

- HashMap을 생성하려면 키 타입과 값 타입을 파라미터로 주고 기본생성자를 호출
- HashMap은 저장공간보다 값이 추가로 들어오면 List처럼 저장공간을 추가로 늘리는데 List처럼 저장공간을 한 칸씩 늘리지 않고 약 두배로 늘림. ->과부하 발생



### :purple_heart:HashMap 값 추가

```java
HashMap<Integer, String> map = new HashMap<>(); //new에서 타입 파라미터 생략 가능
map.put(1,"사과"); //값 추가
map.put(2, "바나나");
map.put(3, "포도");
```

- 값 추가 시 **put(key, value)** 메소드 사용
- 만약 입력되는 키 값이 HashMap 내부에 존재한다면 기존의 값은 새로 입력되는 값으로 대치



### :heart:HashMap 값 삭제

```java
HashMap<Integer, String> map = new HashMap<Integer, String() {{ //초기값 지정
	put(1, "사과");
    put(2, "바나나");
    put(3, "포도");
}};

map.remove(1); //key값 1 제거
map.clear(); //모든 값 제거
```

- 값 제거 시 **remove(key)** 메소드를 사용하면 됨.
- 키 값으로만 제거 가능, 모든 값 제거 시 **clear()** 사용



### :green_heart:HashMap 값 출력

```java
HashMap<Integer, String> map = new HashMap<Integer, String>(){{ //초기값 지정
	put(1, "사과");
    put(2, "바나나");
    put(3, "포도");
}};

System.out.println(map); //전체 출력: {1=사과, 2=바나나, 3=포도}
System.out.println(map.get(1)); //key값 1의 value = 사과 출력\

//entrySet() 활용
for (Entry<Integer, String> entry: map.entrySet()) {
    System.out.println("[Key]: "+entry.getKey()+" [Value]:"+entry.getValue());
}
//[Key]:1 [Value]:사과
//[Key]:2 [Value]:바나나
//[Key]:3 [Value]:포도

//KeySet() 활용
for(Integer i: map.keySet()) { //저장된 key값 확인
    System.out.println("[Key]: "+i+" [Value]: "+map.get(i));
}
//[Key]:1 [Value]:사과
//[Key]:2 [Value]:바나나
//[Key]:3 [Value]:포도
```

- 그냥 일반 **print**하게 되면 {}로 묶어 Map의 전체 key값, value가 출력
- 특정 key값의 value를 가져오고 싶으며 **get(key)** 사용
- 전체 출력: **entrySet()** - key와 value 모두가 필요한 경우, **keySet()** - key값만 필요할 경우
- keySet은 사용하기 간단하지만  key값을 이용해서 value를 찾는 과정에서 시간이 많이 소모되므로 많은 양의 데이터를 가져와야 한다면 entrySet()이 좋음(약 20%~200% 성능 저하가 있음)



### :heartpulse:Iterator 사용

```java
HashMap<Integer,String> map = new HashMap<Integer,String>(){{//초기값 지정
    put(1,"사과");
    put(2,"바나나");
    put(3,"포도");
}};

//entrySet().iterator()
Iterator<Entry<Integer, String>> entries = map.entrySet().iterator();
while(entries.hasNext()){
    Map.Entry<Integer, String> entry = entries.next();
    System.out.println("[Key]:" + entry.getKey() + " [Value]:" +  entry.getValue());
}
//[Key]:1 [Value]:사과
//[Key]:2 [Value]:바나나
//[Key]:3 [Value]:포도
		
//keySet().iterator()
Iterator<Integer> keys = map.keySet().iterator();
while(keys.hasNext()){
    int key = keys.next();
    System.out.println("[Key]:" + key + " [Value]:" +  map.get(key));
}
//[Key]:1 [Value]:사과
//[Key]:2 [Value]:바나나
//[Key]:3 [Value]:포도
```

- HashMap의 전체출력 시 반복문을 사용하지 않고 Iterator를 사용