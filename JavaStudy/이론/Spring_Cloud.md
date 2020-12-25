# Spring Cloud

- 장점: 배포가 쉽고 장애를 고치기 쉬움
  - 높은 확장성(scale out)
- 단점: 서비스들이 복잡해지고 통신이 많아짐
  - 디버깅이 어려워짐
  - 자원 차지가 커짐/많아짐
- 넷플릭스의 영향을 많이 받음
- Service discovery
  - 등록
  - 발견
  - Health Check(서비스가 살아있는지/죽었는지 특정 주기로 체크)
  - Local Cache(여러 대의 서비스들이 등록되고 health check하는데 서비스가 많아지면 해당 정보가 많아져 캐싱처리하고 쉽게 접근 해줌)
- Api Gateway(서버들의 정보를 다 가지고 있다가 필요한 서비스들에게 동적으로 라우팅 시켜줌)
  - 인증
  - 동적 라우팅
  - 로드밸런싱(2개의 인스턴스가 떠있고 하나의 자원이 고갈될 때 다른 자원이랑 나눠서 밸런싱해줌)
  - 보안
- Circuit Breaker(특정 서비스에 장애가 발생 했을 때 장애 확산을 방지하기 위해 중간에 연결을 끊음)
  - 라우팅 되었던 부분들을 call back을 이용해 다른 자원으로 연결
  - 장애 격리
  - 빠른 실패 및 빠른 복구
  - Fallback을 통한 장애 대응
- Config Server
  - 설정 중앙화(장점: 전체적으로 설정에 대한 정보들을 각 어플이 가지지 않아도 됨->노출하지 않아도 되는 정보들을 노출안해도 됨 ex-password, 설정 값들 변경이 용이함)
  - 설정을 별도의 스토리지에 관리
  - 설정 암,복호화



![Article: Decoupling in Cloud Era: Building Cloud Native Microservices with Spring  Cloud Azure - Mobile Monitoring Solutions](https://res.infoq.com/articles/spring-cloud-azure/en/resources/spring-cloud-azure-1541139702005.jpg)