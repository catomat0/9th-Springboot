# Spring 핵심 개념 정리

- **SOLID**
    - 단일 책임 원칙 (SRP) - 한 클래스는 하나의 책임만
    - 개방 폐쇄 원칙 (OCP) - 확장에는 열려있고, 변경에는 닫혀있는 (다형성 활용) / 역할과 구현 분리
    - 리스코프 치환 원칙 (LSP) - 객체는 프로그램의 정확성을 깨뜨리지 않으며 하위타입으로 교체가능 해야함 (인터페이스 규약)
    - 인터페이스 분리 원칙 (ISP) - 특정 인터페이스보단 여러개의 구현체에 범용 인터페이스 지향
    - 의존관계 역전 원칙 (DIP) - 추상화에 의존, 구체화에 의존 지양

  출처 : 김영한 강의

---

- **DI**
    - 의존 관계 주입 - 객체 간의 의존성을 외부에서 주입 (객체의 재 사용성 증가 / 스프링 관리 용이)
        - 생성자 주입 (지향)
        - 필드 주입 (지양)
        - 세터 주입 (soso)
        - ++ Spring - @Autowired (자동 주입)

  출처 : 김영한 강의

---

- **IoC**

  Inversion of Control, 제어의 역전

  객체의 생성과 의존성 관리를 **개발자가 직접 new로 제어하지 않고**, 프레임워크(Spring IoC 컨테이너)가 대신 관리하는 개념 → 스프링 컨테이너가 제어권을 가짐.

  어노테이션 @Bean / @Component 를 통하여 스프링컨테이너가 객체를 생성하게 함.

  @Bean(이름) → 수동등록  
  @Component → 자동 등록 (클래스명의 앞 글자 소문자로 대치)

  → 수동 등록이 자동 등록보다 우선순위가 높아 동일한 빈 이름 등록 시 우선순위에 따라 수동 빈이 자동 빈을 오버라이딩 해서 사용하도록 된다.  
  → 수동 빈의 오버라이딩이 개발자가 의도하지않은 방향으로 진행될 수 있으므로 해당 예외에 대해서는 스프링 설치시 자동 생성되는 프로젝트명 application 을 실행하여 스프링 콘솔을 확인 하여 예외를 잡도록하자.

  출처 : 김영한 강의

---

- **생성자 주입 vs 수정자, 필드 주입 차이**

    ```java
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    ```

    ```java
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    ```

    ```java
    @Autowired
    private UserRepository userRepository;
    ```

    - 생성자 주입 : 명시적으로 주입 설정하는 생성자 주입을 권장
    - 수정자 주입 : 객체 생성 시점에는 주입관계 설정이 안되어 있으므로 null 주의
    - 필드 주입 : 어노테이션을 통한 필드내 컨테이너의 주입 / 순환 문제를 스프링 컨테이너가 관리하기 때문에 문제 발생 시 찾기 어렵고, final 설정 불가하므로 싱글턴 패턴 x

  출처 : 김영한 강의

---

- **AOP**
    - 관점 지향 프로그래밍

      → 공통 관심 사항 : 모든 메서드들이 가지거나 수행해야할 사항 (걸리는시간)  
      → 핵심 관심 사항 : 개발자가 중점적으로 보는 메서드나 핵심 로직이 가지거나 수행해야할 사항

  이 두가지 부분을 분리하여 관리에 용이하도록 하는 기술

  출처 : 김영한 강의

---

- **서블릿**

  클라이언트의 요청(주로 HTTP)을 받아 처리하고, 결과(HTML, JSON 등)를 응답하는 역할

    - 서블릿 : 자바로 만든 웹 요청 처리 객체 (컴포넌트)
    - 서블릿 컨테이너 : 서블릿을 관리하는 실행환경(관리자) (ex : tomcat, jetty …) → 서블릿의 생명 주기 관리 - WAS의 핵심 모듈

  **Request 흐름**  
  WAS → 서블릿 컨테이너 → 서블릿 (클래스 단위) → 서블릿 선언 클래스 내부의 doPost(), doGet() 메서드 단위로 요청에 대한 종류에 따라서 실행되는 메서드가 다름을 알자.


### 추가

- FrontController

  **Front-Controller** 가 필요한 이유

  공통처리가 어렵다 - Controller에서 공통적으로 처리해야할 일이 너무 많다. 미리 공통 기능을 처리해 둘 필요가 생김.

  스프링 웹 MVC - DispatcherServlet 이 FrontController 패턴으로 구현되어있다.


- Spring Ioc 컨테이너 vs 서블릿 컨테이너

  Spring Ioc 컨테이너 : Spring Bean 컨테이너라고 부르기도함. / Bean 객체를 관리 / DI 관리

  서블릿 컨테이너 : http 요청에 관한 서블릿 객체를 관리

  브라우저 요청 → 서블릿 컨테이너(Tomcat)  
  → DispatcherServlet  
  → IoC 컨테이너(ApplicationContext)  
  → Controller Bean 실행  
  → Service / Repository 호출  
  → 결과 반환
