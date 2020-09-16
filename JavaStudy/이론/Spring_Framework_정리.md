# Spring Framework 정리

> 비기능적 요구사항(성능, 보안, 확장성, 안전성 등)을 만족하는 구조와 구현된 기능을 안정적으로 실행하도록 제어해주는 잘 만들어진 구조의 라이브러리 덩어리

- 프레임워크는 애플리케이션들의 최소한의 공통점을 찾아 하부 구조를 제공함으로써 개발자들로 하여금 시스템의 하부 구조를 구현하는데 들어가는 노력 절감.



## 프레임워크 사용 이유

> - 비기능적인 요소들을 초기 개발 단계마다 구현해야하는 불합리함 극복
> - 기능적인 요구사항에 집중 가능
> - 반복적으로 발견되는 문제 해결을 위한 특화된 Solution을 제공



## 디자인 패턴 VS 프레임워크

- 디자인 패턴: 애플리케이션을 설게할 때 필요한 구조적인 가이드라인이 되어 줄 수 있지만 구체적인 구현된 코드 제공하지 않음.
- 프레임워크: 디자인 패턴과 함께 패턴이 적용 된 기반 클래스 라이브러리를 제공해 프레임워크를 사용하는 구조적인 틀과 구현코드를 함께 제공. ->전반적인 가이드 적용



## IoC (Inversion of Control)

> "제어의 역전", 즉 인스턴스 생성부터 소멸까지 인스턴스 생명주기 관리를 개발자가 아닌 **컨테이너**가 대신 해줌. 즉, 컨테이너 역할을 해주는 프레임워크에게 제어하는 권한을 넘겨서 개발자의 코드가 신경 써야 할 것을 줄이는 전략.

- 컨테이너: 보통 인스턴스 생명주기를 관리, 생성된 인스턴스들에게 추가적인 기능을 제공하도록 하는 것
  - 개발자가 작성한 코드의 처리과정을 위임받는 독립적인 존재
  - 스프링 프레임워크의 핵심부에 위치, 종속객체 주입을 이용하여 애플리케이션 구성하는 컴포넌트들을 관리
  - 컨테이너는 적절한 설정만 되어있다면 누구의 도움없이도 프로그래머가 작성한 코드를 스스로 참조한 뒤 알아서 객체의 생성과 소멸을 컨트롤
- IoC는 **컨테이너**
  - IoC 컨테이너는 객체의 생성을 책임지고, 의존성을 관리
  - POJO(Plain Old Java Object, 가볍고 jre로만으로도 돌아가는 객체)의 생성, 초기화, 서비스, 소멸에 대한 권한을 가짐
  - 개발자들이 직접 POJO를 생성할 수 있지만 컨테이너에게 맡김
- Spring 컨테이너는 IoC 지원, 메타데이터(XML 설정)를 통해 beans를 관리하고 어플리케이션의 중요 부분 형성
-  XML 설정 : 객체간의 의존관계 설정
  - 새로 패키지/클래스 생성 시 해당 객체 생성하려면 프레임워크가 해당 패키지/클래스를 인식해야하지만 자동인식이 되지 않음
  - XML에 설정 추가함으로써 객체 사용 가능
- Spring 컨테이너는 관리되는 bean들을 의존성 투입(Dependency Injection)을 통해 IoC 지원
  
- Beans: 스프링 컨테이너가 관리해주는 객체 
  
- Ioc의 분류

  - DL(Dependency Lookup): 저장소에 저장되어 있는 Bean에 접근하기 위해 컨테이너가 제공하는 API를 이용하여 Bean을 Lookup함

  - DI(Dependency Injection): 각 클래스 간의 의존관계를 빈 설정(Bean definition) 정보를 바탕으로 컨테이너가 자동으로 연결해줌 *(DL은 컨테이너 종속성이 증가하여 주로 DI 사용)*

    - Setter Injection:<property> 태그 사용

      - setter 메서드를 통해 의존관계가 있는 Bean을 주입하려면 <property>의 ref 속성 사용

      - setter 메서드에 단순 값으로 인자를 넘기려고 할 시에 value 속성 이용

      - ```xml
        <bean id="hello" class="myspring.di.xml.Hello" scope="singleton">
        		<!-- setter injection 설정-->
        		<!-- setName("스프링") -->
        		<property name="name" value="스프링" />
        		<!-- setPrinter(new StringPrinter()) -->
        		<property name="printer" ref="strPrinter" />
        </bean>
        ```

    - Constructor Injection : <constructor-arg> 태그 사용

      - Constructor를 통해 의존 관계가 있는 Bean을 주입하려면 <constructor-arg> 태그 사용

      - 이 주입 방식은 생성자의 파라미터를 이용하기 때문에 한번에 여러 개의 객체 주입 가능

      - ```xml
        <!-- 만약 기본 생성자가 없을 때 인자 있는 생성자 설정해야 할 때 constructor injection을 해줘야함  -->
        	<!-- 기본생성자도 있고 인자 있는 생성자 둘 다 있을 때 setter와 construction 선택적으로 사용 가능  -->
        	<bean id="helloC" class="myspring.di.xml.Hello">
        		<!-- constructor injection 설정 -->
        		<constructor-arg index="0" value="생성자" />
        		<constructor-arg index="1" ref="conPrinter" />	
        	</bean>
        ```

      - 

    - Method Injection



## 자바 빈 VS 스프링 빈

- 자바 빈: **데이터를 표현하는 것을 목적으로 하는 자바클래스**. 컴포넌트와 비슷한 의미로 사용되기도 한다.

  클래스에는 값을 저장하는 속성필드, get,set 메소드, 기본 생성자등을 포함하고 있다.

- **스프링 빈** : **스프링이 제어권을 가지고 직접 만들고 관계를 부여하는 오브젝트**



## DI (Dependecy Injection)

> 각 클래스간의 의존관계를 빈 설정 (Bean Definition) 정보를 바탕으로 컨테이너가 자동으로 연결해주는 것을 말함

- 의존성? **new**

  -  자동차는 내부적으로 타이어를 생산한다 -> Car car = new Tire(); -> Car와 Tire 사이에서 **Car가 Tire에 의존한다**

- 주입

  - 운전자가 타이어를 생산한다.

     운전자가 자동차를 생산하며 타이어를 장착한다.

  - Tire tire = new KoreanTire();

     Car car = new Car(tire);

  - **결국 자동차 내부에서 타이어를 생산하는 것이 아니라, 외부에서 생산된 타이어를 자동차에 장착하는 작업**이 주입

- 의존성 주입을 통하게 되면 **Car**는 그저 **Tire** 인터페이스를 구현한 어떤 객체가 들어와도 정상 작동 ->확장성이 좋아짐

- 개발자들은 빈 설정파일에서 의존관계 정보를 추가하면 객체 레퍼런스를 컨테이너로부터 주입 받아서, 실행 시에 동적으로 의존관계가 생성됨
- 컨테이너가 흐름의 주체가 되어 어플 코드에 의존관계를 주입
- 장점: 코드 단순화, 컴포넌트 간의 결합도가 제거됨

- **빈(Bean)**: 스프링이 IoC 방식으로 관리하는 객체, 스프링이 직접 생성과 제어를 담당하는 객체를 ''빈''이라고 부름

- **빈 팩토리(Bean Factory)**: 스프링의 IoC를 담당하는 핵심 컨테이너를 가리킴

  - 빈을 등록, 생성, 조회, 반환 기능 담당
  - 이것을 바로 사용 x, ApplicationContext를 주로 이용

- **애플리케이션 컨텍스트(Application Context)**: BeanFactory를 확장한 IoC 컨테이너, BeanFactory보다 더 많이 사용 

- jUnit

  - >Java에서 독립된 단위테스트(Unit Test)를 지원해주는 프레임워크

  - 단위 테스트? 소스 코드의 특정 모듈이 의도된 대로 정확히 작동하는지 검증하는 절차 ->모든 함수와 메소드에 대한 테스트 케이스를 작성하는 절차

  - junit에서 테스트를 지원하는 Annotation

    | Annotation                              | Explanation                                                  |
    | --------------------------------------- | ------------------------------------------------------------ |
    | @Test                                   | - @Test가 선언된 메서드를 수행<br />- 각각의 테스트가 서로 영향으로 주지 않고 독립적으로 실행 -> @Test마다 객체 생성됨 |
    | @Ignore                                 | - 테스트 실행하지 않게 됨                                    |
    | @Before                                 | -@Test 메소드가 실행되기 전에 반드시 실행되어짐<br />-@Test 메소드에서 공통으로 사용하는 코드를 @Before 메소드에 선언하여 사용 |
    | @RunWith(SpringJUnit4ClassRunner.class) | -jUnit 프레임워크의 테스트 실행방법을 확장할 때 사용<br />-SpringJUnit4ClassRunner 클래스를 지정해주면 테스트 진행 중 ApplicationContext를 만들고 관리하는 작업을 진행해줌 + singleton 보장 |
    | @ContextConfiguration                   | 스프링 빈 설정 파일의 위치를 지정할 때 사용되는 어노테이션   |
    | @Autowired                              | -해당 변수에 자동으로 빈 매핑해줌<br />-기존 getBean()(컨테이너한테 생성 객체를 가져옴)을 대체하는 annotation |

*추후 추가 예정*



## 전략2: Annotation+XML 설정을 사용해서 설정하는 방식 (ver 2.5 이후)

### A. Bean 등록 Annotation

- **@Component**: 컴포넌트를 나타내는 일반적인 일반적인 스테레오 타입으로 <bean> 태그와 동일한 역할을 함

- @**Repository**: 퍼시스턴스 레이어, 영속성을 가지는 속성(파일, 데이터베이스)를 가진 클래스 ->ex) DAO 클래스

- **@Controller**: 웹 어플리케이션에서 웹 요청과 응답을 처리하는 클래스

- ```java
  @Component("consolePrinterBean")
  public class ConsolePrinterBean implements IPrinter {
  	public void print(String message) {
  		System.out.println(message);
  	}
  }
  
  ```

### B. Bean 의존관계 주입 Annotation

- **@Autowired**: 정밀한 의존관계 주입이 필요한 경우 유용
  - 변수, setter 메서드, 생성자, 일반 메서드에 적용 가능
  - 의존하는 객체를 주입할 때 주로 *Type*을 이용
  - <property>,<constructor-arg> 태그와 동일한 역할을 함
- **@Resource**: 어플리케이션에서 필요로 하는 자원을 자동 연결할 때 사용
  - 변수, setter 메서드에 적용 가능
  - 의존하는 객체를 주입할 때 주로 *Name*을 이용
- **@Value**: 단순 값을 주입할 때 사용하는 annotation
- **@Qualifier**: @Autowired와 같이 사용되며 동일한 타입의 Bean 객체가 여러 개 존재할 때 특정 Bean을 찾기 위해 @Qualifier를 같이 사용
- **<<context:component-scan>> 태그** : @Component를 통해 자동으로 Bean 등록, @Autowired로 의존관계 주입받는 어노테이션 클래스에서 선언하여 사용했을 경우 해당 클래스가 위치한 특정 패키지 scan을 위한 설정을 따로 xml에서 해주어야 함.



## 전략3: Java Config를 사용해서 설정하는 방식(Spring Boot, No XML) (ver 3.0 이후)

- **@Configuration**: config 역할을 하는 클래스인 경우 선언

- **@ComponentScan(basePackages = {"패키지명"})** 

- **@PropertySource("classpath: config/db.properties")**: XML의 Properties file 정보 설정 대신 태그 사용

- **@Bean**: @Components와 같은 역할, 메소드 위에 선언되어 method명이 Bean의 id가 됨

  - ``` java
    /**
     * 	<!-- Properties file 정보 설정 -->
    	<context:property-placeholder location="classpath:config/values.properties" />
    	
    	<!-- Component Auto Scanning을 위한 설정 -->
    	<context:component-scan base-package="myspring.di.annotation" />
     *위 설정들을 아래 @PropertySource, @ComponentScan로 설정함
     *
     */
    //설정을 위한 클래스
    @Configuration
    @PropertySource("classpath:config/values.properties")
    @ComponentScan(basePackages = {"myspring.di.annotation"})
    public class HelloBeanConfig {
    
    }
    ```

  - ```java
    @Bean
    	public Hello hello() {
    		Hello hello = new Hello();
    		hello.setName(env.getProperty("configName"));
    		hello.setPrinter(strPrinter());
    		return hello;
    	}
    ```







 ## 라이브러리 VS 프레임워크

- 구분 방법? 실행제어가 어디서 일어나는가에 달려있음
- 어떤 어플리케이션을 만드는가의 주도권을 프레임워크는 프레임워크가 갖는것이고, 라이브러리는 어플리케이션 개발자가 가짐
- 프레임워크이라는 개발 틀 안에서 개발자는 특정 기능 구현을 위해 라이브러리 사용
- 라이브러리는 개발자가 만든 클래스에서 직접 호출하여 사용, **실행의 흐름에 대한 제어를 개발자의 코드가 관장**
- 프레임워크는 반대로 **프레임워크에서** 개발자가 개발자가 만든 클래스를 호출하여 **실행의 흐름에 대한 제어를 담당**
- +) API(Application Programming Interface): 응용 프로그램에서 사용할 수 있도록, 운영 체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스
  - ex) util.ArrayList ->객체 직접 생성 가능, 해당 메소드도 직접 사용 가능 ->개발자가 주도권을 갖고 있으며, 라이브러리와 비슷



## Spring Framework

> 자바 엔터프라이즈 개발을 편하게 해주는 오픈소스 경량급 애플리케이션 프레임워크

- 애플리케이션 프레임워크: 특정 계층이나 기술, 업무 분야에 국한되지 않고 애플리케이션의 전 영역을 포괄하는 범용적인 프레임워크

*추후 추가 예정*



## MyBatis

> 자바 오브젝트와 SQL문 사이의 자동 Mapping 기능을 지원하는 ORM(Object-Relational-Mapping ->자바와 SQL문을 매핑해주는, ) 프레임워크

- SQL을 별도의 파일로 분리해서 관리
  - SQL문이 존재하는 **mapping File** 작성
- 객체-SQL 사이의 파라미터 Mapping 작업을 자동으로 해줌
- 개발자가 익숙한 SQL을 그대로 이용하면서 JDBC 코드 작성의 불편함도 제거
- 특징:
  - 쉬운 접근성과 코드의 간결함
  - JDBC의 모든 기능을 MyBatis가 대부분 제공
  - SQL문과 프로그래밍 코드의 분리
  - 다양한 프로그래밍 언어로 구현가능



## MyBatis3 주요 컴포넌트 역할

- **MyBatis 설정파일(SqlMapConfig.xml)** : 데이터베이스의 접속 주소 정보나 Mapping 파일의 경로 등의 고정된 환경정보(개발 정보, 운영 정보)를 설정

  - ```xml
    <typeAliases> <!-- VO 객체를 alias를 이용해 저장 -->
    		<typeAlias alias="User" type="myspring.user.vo.UserVO" />
    		<typeAlias alias="Person" type="myspring.user.vo.PersonVO" />
    		<typeAlias alias="Student" type="myspring.user.vo.StudentVO" />
    		<typeAlias alias="Dept" type="myspring.user.vo.DeptVO" />
    		<typeAlias alias="Course" type="myspring.user.vo.CourseVO" />
    		<typeAlias alias="CourseStatus" type="myspring.user.vo.CourseStatusVO" />
    	</typeAliases>
    ```

  - 

- **SqlSessionFactoryBuilder**: MyBatis 설정 파일을 바탕으로 SqlSessionFactory를 생성

- **SqlSessionFactory**: SqlSession을 생성

- **SqlSession**: 핵심적인 역할!

  - 매핑 파일에 있는 SQL 실행이나 트랜잭션 관리 실행
  - SqlSession 오브젝트는 Thread-Safe 하지 않으므로 thread마다 필요에 따라 생성 ->SqlSessionTemplate은 thread safe, multi-thread 환경에서도 개발 가능

- **mapping 파일(user.xml)**: SQL문과 OR Mapping을 설정 

- 개발자가 SpringBean 설정파일에  SqlSessionFactoryBean을 Spring Bean으로 등록해줘야 함



## MyBatis-Spring의 주요 컴포넌트의 역할

- **MyBatis 설정파일(sqlMapConfig.xml)** : VO 객체 정보 설정(디비 접속정보, 매핑 파일 정보)

- **SqlSessionFactoryBean**: MyBatis 설정 파일을 바탕으로 SqlSessionFactory를 생성, Spring Bean으로 등록

- **SqlSessionTemplate**: SQL 실행이나 트랜잭션 관리, SqlSession 인터페이스 구현 & thread-safe, Spring Bean으로 등록

- **Mapping 파일(user.xml)**: SQL문과 OR Mapping을 설정

  - ```xml
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
    	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <!-- <mapper namespace="myspring.user.dao.mapper.UserMapper"> -->
    <mapper namespace="userNS"> <!-- 다른 클래스에서 사용 시 user namespace 사용-->
        <!-- namespace 설정 이유: mapper 파일이 여러개가 존재 할 수 있기 때문, 파일명이 다르더라도 id가 같으면 다른 파일의 데이터들이 같은 메모리상에 적재됨, id 충돌 발생 -->
    
    	<!-- SqlMapConfig의 alias를 resultType에서 가져다가 씀 -->
        <!-- Sql 실행 후 결과를 담아 둘 resultType에 지정한 UserVO 객체에 저장, 이 작업은 MyBatis가 내부적으로 매핑해줌 -->
    	<select id="selectUserById" parameterType="string" resultType="User">
    		select * from users where userid=#{value} <!--런타임 시 value값을 가져옴 -->
    	</select>
    
        <!-- 전체 레코드 조회-->
        <!-- 전체 레코드 조회 결과의 각각 레코드를 UserVO객체에 담아 그 객체들을 List 객체에 모두 담음 -->
    	<select id="selectUserList" resultType="User">
    		select * from users order by userid
    	</select>
        
        <!-- parameterType="User" -> UserVO 객체(alias = User)에 들어 있는 getId, getName, getGender, getCity를 부름 -->
        <insert id="insertUser" parameterType="User">
    		insert into users
    		values(#{userId},#{name},#{gender},#{city} )
    	</insert>
    ```

- **Spring Bean 설정 파일(beans.xml)**: SqlSessionFactoryBean을 Bean으로 등록할 때 DataSource 정보와 MyBatis config 파일정보, Mapping 파일의 정보 함께 설정 & SqlSessionTemplate을 Bean으로 등록

  - ```xml
    <!-- SqlSessionTemplate 클래스를 Bean으로 등록 -->
    	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    		<constructor-arg ref="sqlSessionFactory" />
    	</bean>
    	
    	<!-- SQLSessionFactoryBean 클래스를 Bean으로 등록(MyBatis 설정) -->
    	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!-- connection을 리턴해주는 ref=dataSource 설정-->
    		<property name="dataSource" ref="dataSource" />
            <!-- VO 객체가 있는 SqlMapConfig.xml의 위치설정 설정-->
    		<property name="configLocation" value="classpath:config/SqlMapConfig.xml" />
    		<property name="mapperLocations">
    			<list>
                    <!-- SQL문이 있는 매핑 파일 설정-->
    			<!-- 파일 이름이 Mapper로 끝나는 모든 파일들을 value 태그로 지정 -->
    				<value>classpath:config/*Mapper.xml</value>
    			</list>
    		</property>
    	</bean>
    ```



- SqlSessionFactoryBean을 통해 sqlSessionFactory를 내부적으로 생성 ->생성된 sqlSessionFactory을 SqlSessionTemplate으로 생성자로 전달, 이 템플릿을 개발자가 작성한 DAO 클래스에서 사용

- Test파일에서 mapper파일을 이용해 sql 구문을 호출할 때

  - ```xml
    <!--UserMapper.xml-->
    <mapper namespace="userNS">
    
    	<!-- SqlMapConfig의 alias를 resultType에서 가져다가 씀 -->
        <!-- parameterType: 구문에 전달될 파라미터의 패키지 경로를 포함한 전체 클래스명이나 별칭-->
    	<select id="selectUserById" parameterType="string" resultType="User">
    		select * from users where userid=#{value}
    	</select>
    
        <!-- 실행되고 난 후 결과값을 UserVO 객체에 값이 저장됨. -->
    	<select id="selectUserList" resultType="User">
    		select * from users order by userid
    	</select>
    
    	<insert id="insertUser" parameterType="User">
    		insert into users
    		values(#{userId},#{name},#{gender},#{city} )
    	</insert>
    
    ```
```
    
  - ```java
    //DataSourceTest.java
    @Test 
    	public void users() {
    		System.out.println(session.getClass().getName());
    		
    		//select by id
            //selectOne("네임스페이스.select id", 전달받을 매개변수(parametertype))
    		UserVO user = session.selectOne("userNS.selectUserById","dooly");
    		System.out.println("user: "+user);
    		}
    
    		//insert
    		UserVO user1 = new UserVO("spring", "스프링", "남", "서울");
    		int cnt = session.insert("insertUser", user1);
    		System.out.println("등록 건수"+cnt);
    		
    		//update
    		UserVO user2 = new UserVO("spring", "스프링", "여", "경기");
    		int cnt2 = session.update("updateUser", user2);
    		System.out.println("업데이트 건수: "+cnt2);
    
    		//delete, 추후 업데이트 예정
    
    		//전체목록 조회
    		System.out.println("전체목록 출력:");
    		List<UserVO> userList= session.selectList("userNS.selectUserList");
    		for (UserVO userVO : userList) {
    			System.out.println(userVO);
    		}
```




## Mapper 인터페이스

> Mapper 파일에 기재된 SQL을 호출하기 위한 인터페이스

- 인터페이스 사용 하지 않았을 때: SQL을 호출하는 프로그램은 SqlSession의 메서드의 아규먼트에 문자열로 **네임스페이스+"."+SQL ID** 로 지정, 문자열로 지정되어 있기 때문에 버그가 숨겨져 있으면 사용 불가

- 인터페이스 사용 했을 때: **패키지 이름+"."+인터페이스 이름+"."+메서드 이름**

  - 네임스페이스 속성에는 패키지를 포함한 Mapper 인터페이스 이름
  - SQL ID에는 매핑하는 메서드 이름을 지정

- ```java
  //Mapper 인터페이스
  package myspring.user.dao.mapper;
  import java.util.List;
  
  import org.apache.ibatis.annotations.Mapper;
  import org.apache.ibatis.annotations.Param;
  import org.apache.ibatis.annotations.Select;
  
  import myspring.user.vo.UserVO;
  
  //@MyMapper
  //검색이 대상이 되는 Package 아래의 인터페이스들 중에서 Mapper로서 작성한 인터페이스로만 범위를 좁히려면 Marker 인터페이스와 Marker 인터페이스와 Marker 어노테이션을 작성하여 인터페이스임을 인식 
  @Mapper
  public interface UserMapper {
  	//@Select("select * from users where userid=#{id}")
  	//UserVO selectUserById(@Param("id") String id);
  	UserVO selectUserById(String id);
  	List<UserVO> selectUserList();
  	void insertUser(UserVO userVO);
  	void updateUser(UserVO userVO);
  	void deleteUser(String id);
  }
  ```

- ```xml
  <!-- Mapper 파일 네임스페이스 변경-->
  <!-- <mapper namespace="studentNS"> -->
  <mapper namespace="myspring.user.dao.mapper.StudentMapper">
  ```




## Spring MVC

> 모델-뷰-컨트롤러(MVC)는 소프트웨어 공학에서 사용되는 아키텍쳐 패턴으로 MVC 패턴의 주 목적은 Business Logic과 Presentation logic을 분리하기 위함

- MVC 패턴 사용 시 사용자 인터페이스로부터 비즈니스 로직을 분리
  
- 애플리케이션의 시각적 요소나 그 이면에서 실행되는 비즈니스 로직을 서로 영향 없이 고칠 수 있는 애플리케이션 제작 가능
  
- **Model**: 애플리케이션의 정보(데이터, Business Logic 포함)

- **View**: 사용자에게 제공할 화면(Presentation Logic)

- **Controller**: Model과 View 사이의 상호 작용을 관리

- 순서:
  1. 클라이언트가 컨트롤러를 요청을 보냄
  2. 컨트롤러가 클라이언트의 요청을 받아 모델을 호출
  3. 모델이 호출의 결과(쿼리의 결과)를 컨트롤러에게 전달
  4. 컨트롤러는 결과를 받아 데이터의 화면 생성 담당인 뷰에게 *화면생성요청*을 함
  5. 뷰는 결과화면을 컨트롤러에게 전달
  6. 컨트롤러는 클라이언트에게 뷰의 결과화면으로 응답
  
- **Model Component**: 데이터 저장소(ex : 데이터베이스 등)와 연동하여 사용자가 입력한 데이터나 사용자에게 출력할 데이터를 다루는 일을 함
  - 여러 개의 데이터 변경 작업(추가, 변경, 삭제)을 하나의 작업으로 묶는 트랜잭션을 다루는 일도 함
  - DAO 클래스, VO 클래스, Service 클래스에 해당
  
- **View Component**: 모델이 처리한 데이터나 그 작업 결과를 가지고 사용자에게 출력할 화면을 만드는 일을 함
  - 생성된 화면은 웹 브라우저가 출력하고, 뷰 컴포넌트는 HTML과 CSS, Java Script를 사용하여 웹 브라우저가 출력할 UI를 만듦
  - Html과 JSP를 사용하여 작성할 수 있음
  
- **Controller Component**: 클라이언트의 요청을 받았을 때 그 요청에 대해 실제 업무를 수행하는 모델 컴포넌트를 호출하는 일을 함
  - 클라이언트가 보낸 데이터가 있다면, 모델을 호출할 때 전달하기 쉽게 데이터를 적절히 가공하는 일을 함
  - 모델이 업무 수행을 완료하면, 그 결과를 가지고 화면을 생성하도록 뷰에게 전달(클라이언트 요청에 대해 모델과 뷰를 결정하여 전달)
  - 주로 Servlet를 사용하여 작성할 수 있음
  - **Front Controller**는 클라이언트가 보낸 요청을 받아서 공통적인 작업을 먼저 수행,  적절한 세부 Controller에게 작업을 위임
    - Front Controller 패턴은 인증이나 권한 체크처럼 모든 요청에 대하여 공통적으로 처리해야 하는 로직이 있을 경우 전체적으로 클라이언트의 요청을 중앙 집중적으로 관리하고자 할 경우에 사용
    - Spring MVC도 Front Controller 역할을 하는 **DispatcherServlet** 이라는 클래스를 계층의 맨 앞단에 놓고, 서버로 들어오는 모든 요청을 받아서 처리하도록 구성

  

  ### Spring MVC의 주요 구성 요소

  

  - **DispatcherServlet**
    - web.xml에서 설정
    - 클라이언트의 요청을 받아서 Controller에게 클라이언트의 요청을 전달하고, 리턴한 결과값을 View에게 전달하여 알맞은 응답을 생성

  - **HandlerMapping**: URL과 요청 정보를 기준으로 어떤 핸들러 객체를 사용할지 결정하는 객체이며, DispatcherServlet은 하나 이상의 핸들러 매핑을 가질 수 있음

  - **Controller**: 클라이언트의 요청을 처리한 뒤, Model를 호출하고 그 결과를 DispatcherServlet에게 알려 줌

    - @Controller: Controller 클래스 정의

    - @RequestMapping: HTTP 요청 URL을 처리할 Controller 메소드 정의

    - @RequestParam: HTTP 요청에 포함된 파라미터 참조 시 사용

    - ```jsp
      <!--userList.jsp-->
      <c:forEach var="user" items="${userList}">
          <tr>
          	<td>
          		<a href="getUser.do?userid=${user.userId}">${user.userId}
      			</a>
      		</td>
              <td>${user.name}</td>
              <td>${user.gender}</td>
              <td>${user.city}</td>
          </tr>
      </c:forEach>
      ```

    - ```java
      //UserController.java
      //서블릿이 먼저 불려지게 하기 위해서 getUser.do가 먼저 불려지게끔 설정하기 위해
      //요청온 url에 해당하는 컨트롤러를 찾아주는 역할
      @RequestMapping("/getUser.do")
      //servlet의 request.getParameter와 같은 역할을 함
      //String id가 아닌 String userid이면 @RequestParam 다음에 있는 인자값은 넣지 않아도 됨
      public ModelAndView getUser(@RequestParam("userid") String id) {
      	//service 호출
      	UserVO user = service.getUser(id);
      	//jsp에 전달(view name, ModelandView의 객체(uservo)를 접근 할 수 있는 키값, modelobject)
      	return new ModelAndView("userDetail", "uservo", user);
      }
      ```

    - @ModelAttribute: HTTP 요청에 포함된 파라미터를 모델 객체로 바인딩

    - ```jsp
      <!--userInsert.jsp-->
      <body>
      	<div class="container">
      		<h2 class="text-center">사용자 정보 등록</h2>
      		<div>
      			<form method="post" action="insertUser.do"  >
      				<table  class="table table-bordered table table-hover">
      					<tr>
      						<td>아이디 :</td>
      						<td><input type="text" name="userId"  /></td>
      					</tr>
      					<tr>
      						<td>이름 :</td>
      						<td><input type="text" name="name" /></td>
      					</tr>
      					<tr>
      						<td>성별 :</td>
      						<td><c:forEach var="genderName" items="${sessionScope.gender}">
      									<input type="radio" name="gender" value="${genderName}">${genderName}
      							  </c:forEach></td>
      					</tr>
      					<tr>
      						<td>거주지 :</td>
      						<td><select name="city">
      								<c:forEach var="cityName" items="${sessionScope.city}">
      									<option value="${cityName}">${cityName}</option>
      								</c:forEach>
      								</select></td>
                          </tr>
      				</table>
      			</form>
      		</div>
      	</div>
      </body>
      ```

    - ```java
      //UserController.java
      @RequestMapping("/insertUser.do")
      	public String insertUser(@ModelAttribute UserVO user) {
      		//등록요청
      		service.insertUser(user);		
      		//등록 후에 바로 사용자리스트 화면에 출력
      		return "redirect:/getUserList.do";
      	}
      
      //UserVO.java
      private String userId;
      	private String name;
      	private String gender;
      	private String city;
      ```

    - 

  - **ModelAndView**: Controller가 처리한 데이터 및 화면에 대한 정보를 보유한 객체(UserVO)

  - **View**: Controller의 처리 결과 화면에 대한 정보를 보유한 객체

  - **ViewResolver**: Controller가 리턴한 뷰 이름을 기반으로 Controller 처리 결과를 생성할 뷰를 결정



## REST

> Open API(Application Programming Interface) 이란?
>
> 개방형 API. API가 응용 프로그램을 개발할 때 사용하는 인터페이스라는 의미이므로, Open API는 프로그래밍에서 사용할 수 있는 개방되어 있는 상태의 인터페이스를 말한다. 
>
> Open API와 함께 자주 거론되는 기술이 REST이며, 대부분 Open API는 REST 방식으로 지원되고 있다.

> REST(REpresentational State Transfer) 란?
>
> HTTP URI + HTTP Method HTTP URI를 통해 제어할 자원(Resource)을 명시하고, HTTP Method (GET, POST, PUT, DELETE)를 통해 해당 자원(Resource)를 제어하는 명령을 내리는 방식의 아키텍쳐

| HTTP Method | CRUD             |
| ----------- | ---------------- |
| POST        | Create(Insert)   |
| GET         | Read(Select)     |
| PUT         | Update or Create |
| DELETE      | Delete           |

> RESTful API는 HTTP와 URI 기반으로 자원에 접근할 수 있도록 제공하는 애플리케이션 개발 인터페이스 (REST의 원리를 따르는 시스템은 RESTful이란 용어로 지칭된다)

- 기존의 게시판은 GET과 POST만으로 자원에 대한 CRUD를 처리하며, URI는 액션을 나타낸다.
- RESTful 게시판은 4가지 메서드를 모두 사용하여 CRUD를 처리하며, URI는 제어하려는 자원을 나타낸다.

> JSON은 경량(lightweight)의 DATA-교환 형식

- Javascript에서 객체를 만들 때 사용하는 표현식을 의미한다.

- JSON 표현식은 사람과 기계 모두 이해하기 쉬우며 용량이 작아서, 최근에는 JSON이 XML을 대체해서 데이터 전송 등에 많이 사용한다.

- 특정 언어에 종속되지 않으며, 대부분의 프로그래밍 언어에서 JSON 포맷의 데이터를 핸들링 할 수 있는 라이브러리를 제공하고 있다. 

- name-value 형식의 쌍(pair) 

  - ```json
    {
    	"firstName": "Brett",
    	"lastName":"McLaughlin",
    	"email": "brett@newInstance.com"
    }
    ```

- 값들의 순서화된 리스트 형식

  - ```java
    {
    	"firstName": "Brett",
    	"lastName":"McLaughlin",
    	"email": brett@newInstance.com,
    	"hobby":["puzzles", "swimming"]
    } 
    ```

- JSON 라이브러리 - Jackson : Jackson은 JSON 형태를 Java 객체로, Java 객체를 JSON 형태로 변환해주는 Java 용 JSON 라이브러리이다.

- XML VS HTML

  - | XML                                     | HTML                                    |
    | --------------------------------------- | --------------------------------------- |
    | Data를 전달하는 것에 포커스를 맞춘 언어 | Data를 표현하는 것에 포커스를 맞춘 언어 |
    | 사용자가 마음대로 Tag를 정의할 수 있다. | 미리 정의된 Tag만 사용할 수 있다.       |



#### Spring MVC기반 RESTful 웹서비스 구현 절차

1. RESTful 웹서비스를 처리할 RestfulController 클래스 작성 및 @RestController 어노테이션 선언하여 Bean으로 등록 

2.  요청을 처리할 메서드에 @RequestMapping @RequestBody와 @ResponseBody 어노테이션 선언 
3. REST Client Tool (Postman)을 사용하여 각각의 메서드 테스트 
4. Ajax 통신을 하여 RESTful 웹서비스를 호출하는 HTML 페이지 작성

| Action      | Resource URI | HTTP Method |
| ----------- | ------------ | ----------- |
| 사용자 목록 | /users       | GET         |
| 사용자 보기 | /users/{id}  | GET         |
| 사용자 등록 | /users       | POST        |
| 사용자 수정 | /users       | PUT         |
| 사용자 삭제 | /users/{id}  | DELETE      |

- Spring MVC에서는 클라이언트에서 전송한 XML이나 JSON 데이터를 Controller에서 Java객체로 변환해서 받을 수 있는 기능(수신)을 제공하고 있다. 

- Java객체를 XML이나 JSON으로 변환해서 전송할 수 있는 기능(송신)을 제공하고 있다.

- **@RequestBody**: HTTP Request Body(요청 몸체)를 Java객체로 전달받을 수 있다.

- **@ResponseBody**: Java 객체를 HTTP Response Body (응답 몸체)로 전송할 수 있다.

- ```java
  //@ResponseBody가 없는 getByIdInHTML 메서드의 경우 : ViewResolver에 의해 선택된 /user.jsp가 포워드 되어지고, user.jsp에서 UserModel 객체를 참조한다.
  
  @Controller
  @RequestMapping("/user")
  public class UserController{
      @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
      public String getByIdInHTML(@PathVariable String id, ModelMap model){
          UserModel user = new UserModel();
          user.setId(id);
          user.setName("ellie");
          model.addAttribute("user", user);
          return "user";
      }
  }
  
  
  //@ResponseBody가 있는 getByIdInJSON 메서드의 경우: MappingJacksonHttpMessageConverter가 리턴값인 UserModel 객체를 JSON으로 변환하는 작업을 처리한다.
  
  @Controller
  @RequestMapping("/user")
  public class UserController{
      @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
      @ResponseBody
      public UserModel getByIdInJSON(@PathVariable String id){
          UserModel user = new UserModel();
          user.setId(id);
          user.setName("ellie");
          model.addAttribute("user", user);
          return "user";
      }
  }
  ```

