# SpringBoot 정리

> Spring Boot: 단독으로 실행이 가능하고(stand-alone), 제품 수준의(production-grade) 스프링 기반 어플 리케이션을 제작하는 것을 목표로 진행된 프로젝트

- 스프링 부트의 주요 기능

  - 단독 실행이 가능한 수준의 스프링 어플리케이션 제작이 가능합니다. 
  - 내장된 Tomcat, Jetty, UnderTow 등의 서버를 이용해서 별도의 서버를 설치하지 않고 실행 가능
  -  최대한 내부적으로 자동화된 설정을 제공
  - XML 설정 없이 단순한 설정 방식을 제공

- 스프링 부트 프로젝트 구조:

  - src/main/java : 자바 Source 파일들
  - src/main/resources/application.properties : 스프링 부트 프로퍼티 값들을 모아 놓은 파일
  - src/main/resources/static : html, css 같은 정적 파일들
  - src/main/resources/templates : jsp, thymeleaf 같은 동적 파일들
  - src/test/java : 자바 테스트 파일들
  - @SpringBootApplication 클래스의 적정 위치는 디폴트 패키지에 위치
    - 내부에 선언된 @ComponentScan 어노테이션이 해당 클래스 기준으로 현 위치와 그 아래 위치까 지 스프링 Bean을 나타내는 어노테이션이 선언된 클래스를 찾기 때문
    -  @SpringBootApplication  = @SpringBootConfiguration + @ComponentScan + @EnableAutoConfiguration 

- @SpringBootApplication 설정은 Bean을 두 단계로 나눠서 등록

  1.  @ComponentScan : project 생성시 정해준 default 패키지 부터 scanning
     -  @ComponentScan은 스프링 프레임워크에서 @Repository, @Configuration, @Service 등 스 프링 빈을 나타내는 어노테이션을 @ComponentScan이 붙은 클래스가 위치해 있는 현재 패키지를 기준 으로 그 아래 패키지까지 찾아내서 스프링 빈으로 등록하는 기능을 가진 어노테이션
  2.   @EnableAutoConfiguration
     - 스프링 부트에서 스프링 프레임워크에서 많이 쓰이는 스프링 bean 들을 자동적으로 컨테이너에 등록하는 역할을 하는 어노테이션
     -  @EnableAutoConfiguration이 등록하는 bean들의 목록은 spring-boot-autoconfigure-2.X.X.RELEASE.jar 파일에 포함

- 웹 어플리케이션 타입 지정:

  -  스프링 부트는 SpringApplication 객체를 통해 어플리케이션 타입을 지정
  - SpringApplication 객체는 스프링 컨테이너의 인터페이스인 ApplicationContext를 개발자 대신 만들어줌
  - WebApplicationType.SERVLET - AnnotationConfigServletWebServerApplicationContext

- 외부 설정 1:

  - 스프링 부트는 외부 설정을 통해 스프링 부트 어플리케이션의 환경설정 혹은 설정값을 정할 수 있음

  - 외부 설정은 크게 properties, YAML, 환경변수, 커맨드 라인 인수 등이 있음

  - ```java
    //MyRunner.java
    //시작하자마자 동작 시작
    @Component
    @Order(2)
    public class MyRunner implements ApplicationRunner{
    //order를 1로 주면 제일 먼저 동작되는 runner 클래스로 지정됨
    	@Value("${miji.name}") //miji 부분이 같아야함
    	String name;
    ```

    

  - ```properties
    #톰캣 포트 번호
    server.port=8086
    #miji 부분이 같아야함
    #사용자 정의 환경변수 선언
    #miji.name=Miji
    miji.age=${random.int(1,50)}
    miji.fullName=Park ${miji.name}
    ```

  - Property 우선순위서 4번째가 커맨드 라인 아규먼트, 15번째가  JAR 안에 있는 application properties

    - 4번째 우선 순위인 커맨드 라인 아규먼트로 설정

    - jar 실행 시 : target>java -jar jartest-0.0.1-SNAPSHOT.jar --vega2k.name=springboot

    - 2번째 우선 순위인 테스트에 있는 @TestPropertySource

      - ```properties
        #test.properties 파일 작성
        miji.name=Miji TEST
        ```

      - ```java
        //ApplicationTests 클래스 작성
        @RunWith(SpringRunner.class)
        @TestPropertySource(locations="classpath:/test.properties")
        @SpringBootTest
        public class ApplicationTests {
        	@Autowired
        	Environment environment;
        	@Test
        	public void contextLoads() {
        		assertThat(environment.getProperty("miji.name")).isEqualTo("test");
        	}
        }
        ```

- 외부 설정 2: @ConfigurationProperties 어노테이션을 통한 외부 설정값 주입

  -  @ConfigurationProperties 프로퍼티 파일의 값을 받은 클래스를 하나 생성하여 그 클래스를 @Autowired 같은 어노테이션을 통해 자동 주입하는 방법이 type-safe, 유지보수 측면에서 더 좋은 장점을 가짐

  - ```java
    //MijiProperties.java
    @Component
    @ConfigurationProperties("miji")
    public class MijiProperties {
    	private String name;
    	private int age;
    	private String fullName;
        
     	getter();
     	setter();
    }
    
    ```

  - ```java
    //MyRunner.java
    @Component
    public class MyRunner implements ApplicationRunner {
     @Value("${miji.name}")
    	String name;
    	
    //	@Value("${miji.age}")
    //	int age;
    	
    	@Autowired
    	private MijiProperties property;
    	
    	@Autowired
    	private String hello;
    	
    	private Logger logger = LoggerFactory.getLogger(MyRunner.class);
    	
    	@Override
    	public void run(ApplicationArguments args) throws Exception {
    		logger.info("====>START Info Mode에서만 출력됩니다.<====" );
    		logger.debug("Logger 클래스 이름: "+logger.getClass().getName());
    		logger.debug("Profile: "+hello);
    		logger.debug("miji.name : "+ name);
    		logger.debug("miji age: "+ property.getAge());
    		logger.debug("miji.fullName: "+property.getFullName());
    		logger.info("====>END Info Mode에서만 출력됩니다.<====" );
    	}
    	
    }
    
    ```

  - ```xml
    <!--pom.xml에 의존성 추가-->
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-configuration-processor</artifactId>
    	<optional>true</optional>
    </dependency>
    ```

    

- 스프링 부트 프로파일: 스프링 부트 애플리케이션의 런타임 환경을 관리 가능.

  - 어플리케이션 작동 시 테스트 환경(개발 환경)에서 실행할 지, 프로덕션 환경(운영 환경)에서 실행할 지를 프로파일을 통해 관리할 수 있음

  - ```java
    //운영 모드에서 사용하는 config 클래스
    @Profile("prod")
    @Configuration
    public class BaseConfiguration {
    @Bean
    	public String hello() {
    		return "hello prod";
    	}
    }
    ```

  - ```java
    //개발 모드에서 사용하는 config 클래스
    @Profile("test")
    @Configuration
    public class BaseConfiguration {
    @Bean
    	public String hello() {
    		return "hello test";
    	}
    }
    ```

  - ```properties
    #어떤 걸 설정해주느냐에 따라 다름
    spring.profiles.active=prod
    ```

  - ```java
    //MyRunner.java
    @Component
    public class MyRunner implements ApplicationRunner {
     @Autowired
     private String hello;
     public void run(ApplicationArguments args) throws Exception {
    	System.out.println(hello);
     }
    }
    
    ```

    

## Spring Boot 데이터

> ORM(Object-Relational Mapping)과 JPA (Java Persistence API) : 객체와 릴레이션을 맵핑 할 때 발생하는 개념적 불일치를 해결하는 프레임워크
>
> JPA: ORM을 위한 자바 (EE) 표준이다

- Spring-Data-JPA 란? : JPA를 쉽게 사용하기 위해 스프링에서 제공하는 프레임워크

  - Repository Bean을 자동 생성
  - 쿼리 메소드 자동 구현
  - @EnableJpaRepositories (스프링 부트가 자동으로 설정 해줌.)

- **@Entity** : 엔티티 클래스임을 지정하며 DB 테이블과 매핑하는 객체를 나타내는 어노테이션

  - 데이터베이스에서 표현하려고 하는 유형, 무형의 객체로서 서로 구별되는 것

  - 이 객체들은 DB 상에서는 보통 table로서 나타내어짐

  - @Id : 엔티티의 기본키를 나타내는 어노테이션

  - @GeneratedValue : 주 키의 값을 자동 생성하기 위해 명시하는 데 사용되는 어노테이션

  - ```java
    @Entity
    public class Account {
    	//@Id-pk 설정, @GeneratedValue-자동 값 증가
    	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    	private Long id;
    	
    	@Column(unique = true)
    	private String username;
    	
    	private String password;
    	
    	private String email;
    
    	public Long getId() {
    		return id;
    	}
    
    	public void setId(Long id) {
    		this.id = id;
    	}
    
    	public String getUsername() {
    		return username;
    	}
    
    	public void setUsername(String username) {
    		this.username = username;
    	}
    
    	public String getPassword() {
    		return password;
    	}
    
    	public void setPassword(String password) {
    		this.password = password;
    	}
    	
    	public String getEmail() {
    		return email;
    	}
    	
    	public void setEmail(String email) {
    		this.email = email;
    	}
    
    	@Override
    	public String toString() { //실제론 비밀번호는 노출 x!
    		return "Account [id=" + id + ", username=" + username + ", password=" + password + "]";
    	}
    
    	@Override
    	public int hashCode() {
    		final int prime = 31;
    		int result = 1;
    		result = prime * result + ((id == null) ? 0 : id.hashCode());
    		result = prime * result + ((password == null) ? 0 : password.hashCode());
    		result = prime * result + ((username == null) ? 0 : username.hashCode());
    		return result;
    	}
    
    	@Override
    	public boolean equals(Object obj) {
    		if (this == obj)
    			return true;
    		if (obj == null)
    			return false;
    		if (getClass() != obj.getClass())
    			return false;
    		Account other = (Account) obj;
    		if (id == null) {
    			if (other.id != null)
    				return false;
    		} else if (!id.equals(other.id))
    			return false;
    		if (password == null) {
    			if (other.password != null)
    				return false;
    		} else if (!password.equals(other.password))
    			return false;
    		if (username == null) {
    			if (other.username != null)
    				return false;
    		} else if (!username.equals(other.username))
    			return false;
    		return true;
    	}
    	
    	
    	
    }
    
    ```
  
- Repository 인터페이스 :  AccountRepository의 구현체를 따로 작성하지 않아도 Spring-Data-JPA가 자동적으로 해당 문자열 username에 대한 인수를 받아 자동적으로 DB Table과 매핑

  - ```java
    public interface AccountRepository extends JpaRepository<Account, Long>{
     Account findByUsername(String username);
    }
    ```

  - ```properties
    #application.properties 파일에 JPA에 의한 데이터베이스 자동 초기화 설정
    spring.jpa.hibernate.ddl-auto=create
    #JPA가 생성한 SQL문을 보여줄 지에 대한 여부를 알려주는 프로퍼티
    spring.jpa.show-sql=true 
    ```

  - spring.jpa.hibernate.ddl-auto=create|create-drop|update|validate

    - create : JPA가 DB와 상호작용할 때 기존에 있던 스키마(테이블)을 삭제하고 새로 만드는 것을 뜻한다.
    - create-drop: JPA 종료 시점에 기존에 있었던 테이블을 삭제
    - update : 기존 스키마는 유지하고, 새로운 것만 추가하고, 기존의 데이터도 유지한다. 변경된 부분만 반영함 주로 개발 할 때 적합
    - validate: 엔티티와 테이블이 정상 매핑 되어 있는지를 검증

  - JPA 테스트: 

    ```java
    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class AccountRepositoryTest {
    	
    	@Autowired
    	private AccountRepository repository;
    	
    
    	@Test //@Ignore
    	public void account() throws Exception {
    		System.out.println(repository.getClass().getName());
    		//1. Account 객체 생성 -> 등록 
    		Account account = new Account();
    		account.setUsername("pengsoo");
    		account.setPassword("1234");
    		//account.setEmail("dooly2@aaa.com");
    		 
    		
    		Account addAccount = repository.save(account);
    		System.out.println(addAccount.getId() + " "+ addAccount.getUsername());
    	}
    }
    
    ```
    
    
  
- Optional 객체 반환

- > Optional는 “존재할 수도 있지만 않 할 수도 있는 객체”, 즉, ”null이 될 수도 있는 객체” 을 감싸 고 있는 일종의 래퍼 클래스

  -  Java8은 함수형 언어의 접근 방식에서 영감을 받아 java.util.Optional라는 새로운 클래스를 도입함

  -  명시적으로 해당 변수가 null일 수도 있다는 가능성을 표현할 수 있음. (따라서 불필요한 NullPointException 방어 로직을 줄일 수 있음)

  - ```java
    //AccountRepositoryTest.java
    @Test @Ignore
    	public void finder() {
    		Account account = repository.findByUsername("lambda");
    		System.out.println(account);
    		Optional<Account> optional = repository.findById(100L);
    		//System.out.println(optional.isPresent());
    		
    		if(optional.isPresent()) {
    			Account account2 = optional.get();
    			System.out.println(account2); 
    		}
    		Optional<Account> optEmail = repository.findByEmail("dooly@aaa.com");
    		System.out.println(optEmail.isPresent());
    		//Supplier의 함수형인터페이스 추상메서드: T get()
            //람다식 사용
    		Account account3 = optEmail.orElseThrow(() ->new RuntimeException("요청한 Email주소를 가진 Account가 없음"));
    		System.out.println(account3);
    		
    		List<Account> accountList = repository.findAll(); //Iterable
    		accountList.forEach(acct -> System.out.println(acct));
    		accountList.forEach(System.out::println);
    		
    	}
    ```

  - ```java
    //AccountRepository.java
    import java.util.Optional;
    public interface AccountRepository extends JpaRepository<Account, Long>{
     Optional<Account> findByUsername(String username);
    }
    ```

  - 



## 스프링 부트 웹 MVC

> Spring MVC 설정을 개발자가 하지 않아도 내부에 spring-boot-autoconfigure.jar 파일에 포함된 META-INF 디렉토리 내에 spring.factories의org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration에서 WebMvc와 관련된 자동 설정 클래스가 적용

- 스프링 부트가 제공해주는 웹MVC의 기능들을 확장하고 싶을 때 추가적으로 설정 파일을 만들면 됨

- @RequestBody 어노테이션을 통한 HTTP 메세지와 객체 매핑 : 

  - JsonMessageConverter
    - HTTP 요청 본문을 Json객체로 변경하거나, Json객체를 HTTP 응답 본문으로 변겨알 때는 JsonMessageConverter가 사용됨
      - ex) {"username":"vega2k", "password":"123"} <-> User
    -  Controller에서 json 타입에 대한 정보를 명시하지 않아도 ContentNegotiatingViewResolver를 통해 자동적으로 json 형식으로 데이터를 반환하도록 스프링 부트에서 제공함
  - @PostMapping: @GetMapping과 비슷하게 @RequestMapping(method=RequestMethod.POST)의 축약형

- ```java
  //entity/User.java
  @Entity
  public class User {
  	@Id
  	@GeneratedValue
  	private Long id;
  	@Column
  	private String name;
  	@Column(unique=true)
  	private String email;
  }
  
  ```

- ```java
  //RESTUserController.java
  @PostMapping("/users")
  	public User insert(@RequestBody User addUser) {
  		return repository.save(addUser);
  	}
  	
  	@GetMapping("/users")
  	public List<User> getUsers() {
  		return repository.findAll();
  	}
  	@RequestMapping(value = "/usersXml", produces = {"application/xml"} )
  	public Users getUsersXml() {
  		Users userXml = new Users();
  		userXml.setUsers(repository.findAll());
  		return userXml;
  	}
  @PutMapping("/users/{id}")
  	public User updateUser(@PathVariable Long id, @RequestBody User userDetail) {
  		User user= repository.findById(id).orElseThrow(()->new ResourceNotFoundException("User", "Id", id));
  		//값을 변경
  		user.setName(userDetail.getName());
  		user.setEmail(userDetail.getEmail());
  		//DB에 save()
  		User updatedUser = repository.save(user);
  		return updatedUser;
  	}
  	
  	@DeleteMapping("/users/{id}")
  	public ResponseEntity<?> deleteUser(@PathVariable Long id){
  		Optional<User> optional = repository.findById(id);
  		//요청한 id와 매핑하는 User가 없는 경우 (java 11에 추가)
  		if(optional.isEmpty()) {
  			return new ResponseEntity<>("요청한 User가 없습니다.",HttpStatus.NOT_FOUND);
  		}
  		//db에서 삭제 실행
  		repository.deleteById(id);
  		return new ResponseEntity<>(id+ "유저가 삭제 완료", HttpStatus.OK);
  	}
  ```

- ```java
  //사용자 정의 Exception class
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public class ResourceNotFoundException extends RuntimeException {
   private String resourceName;
   private String fieldName;
   private Object fieldValue;
   public ResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
   super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
   this.resourceName = resourceName;
   this.fieldName = fieldName;
   this.fieldValue = fieldValue;
   }
   public String getResourceName() { return resourceName; }
   public String getFieldName() { return fieldName; }
   public Object getFieldValue() { return fieldValue; }
  }
  ```

- WebMvcConfigurer를 통한 정적 리소스 매핑

- ViewResolver : Controller에서 반환한 값(ModelAndView 혹은 Model)을 통해 뷰를 만드는 역할

- ContentNegotiatingViewResolver:  동일한 URI에서 HTTP Request에 있는 Content-type 및 Accept 헤더를 기준으로 다양한 Content-type으로 응답할 수 있게 하는 ViewResolver

  - ```java
    @RestController
    public class UserRestController {
    	@RequestMapping(value="/users2", produces = {"application/xml"})
    	public List<User> getUsers2() {
    		return userRepository.findAll();
    	}
        /*결과: 
        <List>
     		<Item>
     			<id>1</id>
     			<name>홍길동</name>
     			<email>test@aa.com</email>
     			</Item>
    	</List>
    	*/
        
    @RequestMapping(value="/usersxml", produces = { "application/xml"})
    public Users getUsersXml() {
    	Users users = new Users();
    	users.setUsers(userRepository.findAll());
    	return users;
    	}
    }
    
    /*결과:
    <Users>
     <User id="1">
     <name>홍길동</name>
     <email>test@aa.com</email>
     </User>
    </Users>
    */
    ```

  - ```java
    @JacksonXmlRootElement
    public class Users implements Serializable{
    	private static final long serialVersionUID = 22L;
    	
    	@JacksonXmlProperty(localName="User") //<-추가됨, 루트 태그로 설정됨
    	@JacksonXmlElementWrapper(useWrapping=false) //<-추가됨
    	private List<User> users = new ArrayList<>();
    	public void setUsers(List<User> users) {
    		this.users = users;
    	}
    public List<User> getUsers() {
    	return users;
    	}
    }
    ```

  - ```java
    @Entity
    public class User implements Serializable {
    	private static final long serialVersionUID = 21L;
    	
    	@Id
    	@GeneratedValue
    	@JacksonXmlProperty(isAttribute = true)//<-추가됨
    	private Long id;
    	
    	@JacksonXmlProperty//<-추가됨
     	private String name;
     	
    	@JacksonXmlProperty//<-추가됨
     	private String email;
    
    ```



- 정적 리소스 지원 : 예) http://localhost:8080/hello.html

  - classpath:/resources 형식으로 지원

  - 정적 리소스 맵핑 설정 변경: 

    - ```properties
      spring.mvc.static-path-pattern=/static/**
      #“/hello.html” => /static/hello.html
      ```

    - **WebMvcConfigurer**를 통한 정적 리소스 매핑: *addResourceHandlers* 메서드로 커스터마이징

    - *addResourceHandlers*는 리소스 등록 및 핸들러를 관리하는 객체인 ResourceHandlerRegistry를 통해 리소스 위치와 이 리소스와 매칭될 url을 등록

    - *setCachePeriod*는 캐시를 얼마나 지속할 지 정하는 메서드

    - ```java
      //config/WebConfig.java
      @Configuration
      public class WebConfiguration implements WebMvcConfigurer {
      	@Override
      	public void addResourceHandlers(ResourceHandlerRegistry registry) {
      		//해당 url 패턴(mm)을 매칭 ->mobile에 매칭됨
      		registry.addResourceHandler("/mm/**") 
      				// 반드시 디렉토리 이름(mobile) 다음에 / 을 주어야 한다.
      				.addResourceLocations("classpath:/mobile/")
      				.setCachePeriod(20);// 20초
      		//localhost:8086/mm/mobile.html로 출력됨
      	}
      }
      ```

    - ```xml
      <!--실행할 xml 파일-->
      <!--src/main/resources/m/hello.html-->
      <html>
      <body>
      	<h1>Mobile Hello Static
      	Resources</h1>
      </body>
      </html>
      ```





### Thymeleaf

> - Thymeleaf는 스프링 부트가 자동 설정을 지원하는 웹 템플릿 엔진. HTML문서에 HTML5 문법으 로 서버쪽 로직을 수행하고 적용시킬 수 있음
> -  HTML 디자인에 전혀 영향을 미치지 않고 웹 템플릿 엔진을 통해 HTML을 생성
> -  th:xx 형식으로 속성을 html 태그에 추가하여 값을 처리할 수 있음

- JSP를 권장하지 않는 이유? JAR 패키징 할 때는 동작하지 않고, WAR 패키징 해야 함, Undertow(Servlet Engine)는 JSP를 지원하지 않음



*추후 추가 예정*



