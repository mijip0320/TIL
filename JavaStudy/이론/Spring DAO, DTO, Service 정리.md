# Spring DAO, DTO, Service 정리

1. DAO: Data Access Object

   1. DB를 사용해 데이터를 조회하거나 조작하는 기능을 담당함

   2. 비즈니스 로직이나 dB와 관련없는 코드들을  persistence mechanism(Database에 data를 CRUD(Create, Read, Update, Drop)하는 계층)과 분리하기 위해 사용

   3. DAO를 하나 만들어 DB 전용 객체로만 쓰게 하기 위함

   4. Spring에서 DAO는 **@Repository annotation**으로 정의, 인터페이스로 정의하고 구현한 class에 annotation을 붙여 사용(interface로 구성하면 확장성과 유연성이 높아짐)

      ```java
      public interface UserDao {
          /**
           * user 테이블에서 모든 유저의 정보를 가져온다.
           * 
           * @return 모든 유저의 정보
           */
          public List<User> getUsers();
      }
      ```

      ```java
      @Repository("userDao")
      public class UserDaoImpl implements UserDao {
          @Override
          public List<User> getUsers()
          {
              // 리스트 생성
              List<User> result = new ArrayList<User>();
      
              // 데이터베이스에서 유저 목록을 가져온다.
              result.add(...);
              ...
      
              return result;
          }
      }
      ```

2. DTO: Data Transfer Object

   1. 계층간 데이터 교환을 위한 자바빈즈(Java Beans)
   2. 데이터베이스 레코드의 데이터를 매핑하기 위한 데이터 객체
   3. DTO는 보통 로직을 가지고 있지 않고 data와 그 data에 접근을 위한 getter, setter만 가지고 있음
   4. DTO는 Database에서 Data를 얻어 Service나 Controller 등으로 보낼 때 사용하는 객체

   ```java
   public class User {
       private String name;
       private int age;
   
       public String getName() {
           return this.name;
       }
   
       public void setName(String name) {
           this.name = name;
       }
   
       public void getAge() {
           return this.age;
       }
   
       public void setAge(int age)
       {
           this.age = age;
       }
   
       @Override
       public String toString() {
           return "name='" + name + "', age=" + age;
       }
   }
   ```

3. Service: 비즈니스 로직에 들어가는 부분

   1. Controller가 Request를 받으면 적절한 Service에 전달, 전달받은 Service는 비즈니스 로직을 처리
   2. DAO로 데이터베이스를 접근, DTO로 데이터를 전달받은 다음, 적절한 처리를 해 반환

   ```java
   public interface UserService {
       /**
        * 유저 정보를 텍스트 파일로 저장한다.
        * 
        * @param path 저장할 파일의 경로
        * @return 저장한 유저의 개수
        */
       public int saveUsersAsTextFile(String path);
   }
   ```

   ```java
   @Service("userService")
   public class UserServiceImpl implements UserService {
       private static final Logger LOGGER = Logger.getLogger("UserServiceImpl");
   
       @Autowired
       private UserDao userDao;
   
       @Override
       public int saveUsersAsTextFile(String path) {
           List<User> users = userDao.getUsers();
   
           // 비즈니스 로직
           try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
               StringBuilder result = new StringBuilder();
               for(User user : users) {
                   result.append(user);
                   result.append('\n');
               }
   
               fileOutputStream.write(result.toString().getBytes());
           } catch (IOException exception) {
               LOGGER.log(Level.SEVERE, "파일을 쓸 수 없습니다.");
               throw new IllegalStateException(String.format("Can't write a file. path: %s", path));
           }
   
           return users.size();
       }
   }
   ```

   - DAO로부터 DTO 리스트를 받고, DTO의 리스트를 파일로 저장하는 코드
   - @Autowired annotation으로 userDao bean을 찾아서 연결 -> **DI(Dependency Injection, 의존성 주입)**

```java
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/save/users", method = RequestMethod.GET)
    public ModelAndView saveUsers(ModelAndView mv) {
        // 유저를 얻어와서 텍스트 파일로 저장한다.
        int saveCount = userService.saveUsersAsTextFile("users.txt");

        // 뷰에서 결과를 보여주기 위해 저장한 개수를 뷰에 넘긴다.
        mv.addObject("saveCount", saveCount);
        mv.setViewName("saveUsersResultView");

        return mv;
    }
}
```

 