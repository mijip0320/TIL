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

   

