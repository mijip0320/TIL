# Java 정리

### JVM(Java Virtual Machine)

* 자바 가상 머신(가상머신: 프로그램의 실행하기 위해 물리적 머신과 유사한 머신의 소프트웨어로 구현.)
* JVM은 자바 어플리케이션을 클래스 로더를 통해 읽어 들여 자바 API와 함께 실행.
* JAVA와 OS 사이의 중개자  역할을 하면서 JAVA가 OS의 환경에 구애받지 않고 재사용이 가능하게 해줌.
* 효율적인 메모리 관리, Garbage Collection 수행
* JVM은 스택기반의 가상머신



#### 자바프로그램 실행과정

> 1. 프로그램 실행 -> JVM은 OS로부터 프로그램이 필요로 하는 메모리를 할당받음
>
>    (용도에 따라 여러 영역으로 나누어 관리)
>
> 2. 자바 컴파일러(javac)가 자바 소스코드(.java)를 읽어들여 자바 바이트코드(.class)로 변환.
>
> 3. Class Loader를 통해 class 파일들을 JVM으로 로딩
>
> 4. 로딩된 class 파일들을 exe engine을 통해 해석
>
> 5. 해석된 바이트코드는 Runtime Data Areas에 배치되어 실질적인 수행이 이루어지게 됨.
>
> 6. JVM은 필요에 따라 Thread Synchronization과 GC같은 관리 작업 수행.



#### JVM 구성

##### Class Loader

* JVM내로 클래스 로드, 링크를 통해 배치하는 작업을 수행하는 모듈
* 런타임 시 동적으로 클래스 로드
* 자바는 동적 코드(컴파일 타임이 아니라 런타임에 참조)



##### Execution Engine(실행 엔진)

* 클래스를 실행시키는 역할
* 클래스 로더가 JVM내의 런타임 데이터 영역에 바이트 코드 배치, 실행엔진에 의해 실행됨
* 자바 바이트코드는 인간이 보기 편한 형태로 기술됨, 그래서 기계가 실행 할 수 있는 형태로 JVM이 내부적으로 바꿔줌
  * Interpreter: 자바 바이트코드를 명령어 단위로 읽어서 실행(단점: 수행이 느림)
  * JIT(Just-In-Time): Interpereter의 단점 보완, 적절한 시점에 바이트코드 전체를 컴파일하여 native code로 변경 후 native code로 직접 실행 
    * natvic code는 캐시에 보관되어 빠르게 수행됨
    * JIT 컴파일러가 컴파일하는 과정은 Interpreting보다 오래 걸려서 한번만 실행되는 코드는 컴파일 하지 않고 Interpreting하는 것이 유리!



#### Garbage Collector

> GC를 수행하는 모듈 = 쓰레드



#### Runtime Data Area

1. PC Register

   > - Thread가 시작 될 때 생성됨
   > - Thread가 어떤 부분을 어떤 명령으로 실행해야할 지에 대한 기록을 하는 부분(JVM 명령의 주소를 가짐)

2. JVM 스택 영역

   > - 프로그램 실행과정에서 임시로 할당되었다가 메소드를 빠져나가면 바로 소멸되는 특성의 데이터를 저장하는 영역
   > - 임시 데이터, 변수, 스레드, 메소드 정보 저장
   > - 메소드 호출 할때마다 각각의 스택 프레임(그 메서드만을 위한 공간)이 생성, 수행 끝나면 프레임별로 삭제
   > - 메소드 안 local variable들을 저장, 매개변수, 리턴 값, 연산된 값들 임시로 저장

3. Native method stack

   > - 바이트 코드가 아닌 실제 실행할 수 있는 기계어로 작성된 프로그램을 실행시키는 영역
   > - JAVA가 아닌 다른 언어로 작성된 코드를 위한 공간
   > - JAVA Native Interface를 통해 바이트코드로 전환, 저장

4. Method Area(=Class area / Static area)

   > - 클래스 정보를 처음 메모리 공간에 올릴 때 **초기화되는 대상을 저장하기 위한 메모리 공간**. 
   > - 클래스 데이터를 위한 공간
   > - main 메소드의 호출에서부터 계속된 메소드의 호출로 흐름을 이어감

5. Heap 영역

   > - 객체를 저장하는 가상 메모리 공간, **new** 연산자로 생성된 객체와 배열을 저장
   > - 힙 영역은 New/Young Generation, Tenured Generation, Permanent Generation으로 나뉨
   >   - Permanent Generation
   >     - 생성된 객체들의 정보의 주소값이 저장된 공간
   >     - Class loader에 의해 load되는 Class, Method 등에 대한 Meta 정보가 저장되는 영역이고 JVM에 의해 사용됨.
   >     - Reflection을 사용하여 동적으로 클래스가 로딩되는 경우에 사용
   >     - Spring Framework(Reflection 자주 사용) 이용할 경우 이 영역에 대한 고려 필요
   >   - New/Young 영역
   >     - **Eden**: 객체들이 최초로 생성되는 공간
   >     - **Survivor 0 / 1**: Eden에서 참초되는 객체들이 저장되는 공간
   >   - Old 영역
   >     - New Area에서 일정 시간 참조되고 있는, 살아남은 객체들이 저장되는 공간 Eden 영역에 객체가 가득차게 되면 첫 번째 GC(minor GC)가 발생



### Collection

- List,Map,Set, Stack, Queue 인터페이스를 기준으로 여러 구현체 존재
- 사용 이유? 다수의 Data를 다루는데 표준화된 클래스들을 제공, Datastructure 직접 구현 필요 x
- 상황에 따라 객체의 수를 동적으로 정할 수 있음
  - List: `ArrayList`,` Linked List`
  - Map: `HashMap`(key-value 형태), `LinkedHashMap`(key에 대한 순서 보장)
  - Set: `HashSet`(value에 대해서 중복 값 저장 x, `Map`의 key-value 구조에서 key 대신에 value가 들어가 value를 key로 하는 자료구조), `LinkedHashSet`(순서 보장)
  - Stack과 Queue: `stack`은 new로 생성, `Queue`는 `LinkedList`에 new 이용



### Generic

- 제네릭은 자바에서 안정성을 맡고 있음
- 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에서 사용하는 것으로, 컴파일 과정에서 타입체크를 해주는 기능 ->안정성을 높이고 형변환의 번거로움을 줄여줌, 코드도 간결해줌
- api를 설계하는데 있어서 보다 명확한 의사 전달 가능



### 오버라이딩(재정의) vs 오버로딩(다중정의)

|                      Overriding(재정의)                      | Overloading(다중정의)                                        |
| :----------------------------------------------------------: | ------------------------------------------------------------ |
|                메소드를 하위 클래스에서 정의                 | 메소드를 같은 클래스에서 정의                                |
| - 메소드 이름 동일<br />-Parameter(개수 및 데이터 타입) 동일<br />-Return 타입 동일 | - 메소드 이름 동일<br />-Parameter(개수 및 데이터 타입) 동일<br />-Return 타입이 다를 수 있음 |
| 접근 제한자: 하위 메소드의 접근 범위가 상위 메소드의 접근 점위 보다 넓거나 같아야 한다. | 접근 제한자: 관계없음                                        |
| 예외 처리: 예외 발생시 같은 예외 형식이거나, 더 구체적인 예외 형식이어야 한다. | 예외 처리: 관계 없음                                         |



### Access Modifier

변수 또는 메소드의 접근 범위 설정

- public(+): 어떤 클래스에서라도 접근 가능
- protected(#): 해당 패키지 내 그리고 해당 클래스를 상속받은 외부 패키지의 클래스에서 접근 가능
- default: 같은 디렉토리(같은 package)내의 다른 클래스에서 참조 가능
- private(-): 클래스 내부에서만 참조 가능