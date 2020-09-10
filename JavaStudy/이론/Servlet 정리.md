# Servlet 정리

- CGI Program: 다양한 언어로 작성 가능, 사용하기 편함 -> 하지만 각기 다른 OS shell에서 실행되기 때문에 CGI 프로그램의 응답시간이 길고 확장성이 없음
- Process : 멀티태스킹 - Process란 현재 진행중인 프로그램의 작업을 의미, 코드와 데이터의 공유가 없음
- Thread: 멀티스레딩 - 하나의 프로그램 내에서 여러 개의  작업이 동시에 일어나는 경우
  - 스레드  = 하나의 프로세스내의 세부적인 작업 단위로 코드와 데이터를 공유
- CGI vs Servlet:
  - CGI : 클라이언트 요청당 하나의 프로세스 생성 동시 접속자 증가시 서버에 부하 가중 
  - Servlet : 클라이언트 요청을 쓰레드로 생성 CGI 성능 문제 해결
- Servlet의 장점:
  - 각 요청마다 다른 스레드로 실행, 처리 속도가 빠름
  - 확장성이 있음
  - 객체 지향적
  - Java 언어로만 구현
  - 플랫폼으로부터 독립적
- Servlet의 단점:
  - 비즈니스 로직과 프리젠테이션 로직을 구분하기 힘들기 때문에 웹 응답을 생성하는 데 사용하기 어렵다. ->JSP (Java Server Page)로 해결
  - 동시성 문제를 고려해야 한다.
- Template Page : HTML내에 코드 포함하는 방법
- HTML내에 코드를 포함하는 기술: PHP(Apache에서 동작), ASP(Active Server Page), JSP(Java Server Page ->웹 서버 독립적)
- JSP : Java 코드를 포함하는 Html
  - JSP page는 Web Container에 의해 서블릿 인스턴스로 변환 되며, 변환된 서블 릿은 해당 JSP page에 대한 요청을 처리한다. ->결국 Servlet
  - ASP나 PHP page는 HTTP요청이 들어올 때 마다 interpret 되는 반면, JSP는 Java bytecode로 컴파일된다.
  - Servlet과 JSP의 주된 차이점은 JSP가 Presentation logic에 포커스를 맞추고 있다는 점이다
  - 장점:
    - 스레드가 사용되기 때문에 웹 응용프로그램의 성능과 확장성이 있음
    - Java 기반이라 platform independent하다
    - 객체지향언어와 모든 API를 사용
  - 단점:
    - Presentation login과 Business logic을 같이 포함하는 경우가 있음
    - 동시성 문제 고려
    - 디버깅이 어려움.



*추후 추가 예정*

