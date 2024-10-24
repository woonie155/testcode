# 키오스크 주문 시스템
요구사항의 기능 구현과 테스트를 작성한다.

## 요구사항
1. 주문 목록에 음료 추가/삭제
2. 주문 목록 전체 지우기
3. 주문 목록 총 금액 계산하기
4. 주문 생성하기
   1. 주문 생성 시 재고 확인 및 개수 차감 후 생성하기
5. 한 종류의 음료 여러 잔을 한번에 담는 기능
6. 가게 운영 시간(10:00~22:00) 외에는 주문을 생성할 수 없다.
7. 주문을 위한 상품 후보 리스트 조회하기
   1. 상품마다 id, 상품 번호, 상품 타입, 판매 상태, 상품 이름, 가격을 나타낸다.
8. 재고는 상품번호를 가진다.
9. 재고와 관련 있는 상품 타입은 병 음료, 베이커리이다.
10. 관리자 페이지에서 신규 상품을 등록할 수 있다.
    1. 상품명, 상품 타입, 판매 상태, 가격 등을 입력받는다.
11. 관리자 페이지에서 일일 매출 통계를 메일로 전송할 수 있다.


-----
## 테스트 코드의 필요 이유

1. 수동테스트는 변화가 생길때마다 모든 케이스를 재테스트해야하고, 인력 비용 발생 및 실수 발생, 늦는 피드백 등이 따른다.
2. 자동테스트로 자동화를 통한 빠른 피드백, 안정감, 인력 비용 절감을 보완한다.
3. 소프트웨어 품질을 보증한다.

## 테스트 도구

1. JUnit5: 단위 테스트를 위한 테스트 프레임워크
2. AssertJ: 테스트 코드 작성을 원활하게 돕는 테스트 라이브러리

   풍부한 API와 메서드 체이닝을 지원한다.

   보통은 JUnit위에 AssertJ를 얹어 사용한다.


## TDD

Test Driven Development(테스트 주도 개발):

1. 테스트 코드를 먼저 작성한 다음 실제 코드를 작성하는 방법론이다.
2. 테스트케이스 목적은 테스트할 모듈의 기능확인 목적이다. 테스트를 먼저 작성함을 통해서 엣지 케이스를 놓치지 않도록한다.
3. TDD 기본 3단계는 레드-그린-리팩토링이다. 실패하는 테스트를 작성하고, 빠른시간 내에 테스트를 통과하도록 최소한의 코딩하고, 구현 코드 개선하며 테스트 통과를 유지한다.

## BDD

Behavior Driven Development(행동 주도 개발):

1. 시나리오에 기반한 테스트케이스에 집중해서 테스트 코드를 작성하는 방법론이다.
2. 테스트케이스 목적은 시나리오 대로 행위를 확인하는 목적이다. 개발자가 아닌 사람이 봐도 이해할 정도의 추상화를 권장한다. 사용자의 행위를 작성하고 결과 검증을 진행한다.
3. BDD 3단계는 Given-When-Then이다.

   Given: 시나리오 진행에 필요한 모든 준비 과정(객체, 값, 상태 등)

   When: 시나리오 행동 진행

   Then: 시나리오 진행에 대한 결과 명시, 검증


## 단위 테스트

클래스, 메서드 처럼 작은 코드 단위를 독립적으로 검증하는 테스트다.

    1. 경계값 테스트(해피 케이스, 예외 케이스)
    3이상의 점수에서 a 학점을 부여할 때,

    - 해피 케이스: 3을 테스트
    - 예외 케이스: 2을 테스트

## Layered Architecture 테스트

1. Persistence Layer
    1. CRUD 쿼리를 검증해야 한다.
2. Business Layer
    1. Persistence Layer 와 함께 통합 테스트를 추천한다.
3. Presentation Layer
    1. 입력값에 대해 검증해야 한다. (하위 레이어는 Mocking 처리)

테스트 환경 통합으로 ApplicationContext 생성 비용을 최소화하자. (예를들어 @MockBean 사용 시 기존 ApplicationContext과는 다른 구성이 되므로 재생성하게 된다.)

## MockMvc

Mock(가짜) 객체를 사용해 스프링 MVC 동작을 재현할 수 있는 테스트 프레임워크다.

    @WebMvcTest: Controller 와 관련된 빈을 사용할 수 있다.
    
    @Mock: Mock 객체를 만든다. @ExtendWith(MokitoExtention.class)를 필요로한다.
    
    @InjectMocks: @Mock객체를 자동 주입하며 Mock객체를 만든다.
    
    @MockBean: 컨테이너에 등록된 빈을 Mock 객체로 사용할 수 있다.
    
    @SpringbootTest: 스프링 컨텍스트에 등록된 빈들을 사용할 수 있다.
    
    @DataJpaTest: Repository(jpa)와 관련된 빈을 사용할 수 있다. (주의) Persistence Layer 제한이므로 JPA의 변경감지는 활용할 순 없다.

## 좋은 테스트

1. 테스트하기 어려운 영역을 구분하고 분리하기 (외부로 분리해서 파라미터로 받기)

   관측할 때마다 다른 값에 의존하는 코드: 현재 날짜/시간, 랜덤 값, 전역변수/함수, 사용자 입력 등

   외부세계에 영향을 주는 코드: 표준 출력, 메시지 발송, 데이터베이스에 기록하기 등


2. DisplayName을 섬세하게

    메서드 자체의 관점보다 도메인 정책 관점: 도메인 용어를 사용하여 한층 추상화된 내용 담기 

    문장으로 작성하며, 테스트 행위에 대한 결과까지 작성한다.

    테스트의 현상을 중점으로 기술하지 않는다. (ex, “~하면 실패한다”)


## Test Double (테스트 더블, 테스트 대역)

1. Dummy : 아무 것도 하지 않는 깡통 객체

2. Fake: 단순한 형태로 동일한 기능은 수행하나, 프로덕션에서 쓰기에는 부족한 객체 (ex, FakeRepository는 메모리에 단순 저장하는 용도)

3. **Stub**: 테스트에서 요청한 것에 대해 미리 준비한 결과를 제공하는 객체

4. Spy: Stub이면서 호출된 내용을 기록하여 보여 수 있는 객체이고, 기능 중 일부는 실제 객체처럼 동작시키고 일부만 Stubbing할 수 있다.

5. **Mock**: 행위에 대한 기대를 명세하고, 그에 따라 동작하도록 만들어진 객체

6. **Stub vs Mock: 비슷하지만, 검증 목적이 다르다.**

7. **Stub은 상태검증(state verification)으로, 어떤 기능을 요청했을 때 상태가 바뀜을 검증한다.**

8. **Mock은 행위검증(behavior verification)으로, 어떤 행위에 대해서 어떤 결과가 나옴을 검증한다.**


### Classicist vs Mockist

1. Mockist: 목킹 위주로 설계 하자.

2. Classicist: 진짜 객체 위주로 테스트 하자. 
   
   목킹으로 인한 문제가 숨겨질 수 있으니 꼭 필요한 경우만 목킹한다. 예를들면 외부서비스만 목킹한다. 

    또한, 실제 프로덕션 코드에서 런타임 시점에 일어날 일을 정확하게 스터빙, 목킹 했다고 단언하기 힘들다.


### Test Fixture
원하는 상태로 고정시킨 객체들로 재사용을 위함 (공통된 given영역을 통일한다)
단점) 여러 테스트가 담긴 클래스라면 문서로서 부적합해질 수 있다. 각 테스트 입장에서 봤을 때 아예 몰라도 테스 내용을 이해하는데 문제없는 경우에 활용하자.

    @BeforeAll: 전체 테스트 전에 실행
    
    @BeforeEach: 테스트마다 실행
    
    @AfterAll, @AfterEach도 마찬가지다.
    
    @Transaction: 테스트 클래스에 할당하면 테스트 단위로 롤백하므로 데이터에 의한 에러를 야기시키지 않는다. 스프링배치에서 테스트할땐 여러 트랜잭션이 발생하므로 이 어노테이션을 활용하긴 어려우므로 아래 방식을 택한다.
    
    @AfterEach:
    jpa- deleteAIlnBatch() : 테이블 단위로 벌크성삭제한다. (주의)외래키에 의해서 삭제에 실패할 수 있다. 
    jpa- deleteAll() : 건 단위로 삭제한다. 건 단위로 연관객체도 같이 삭제한다. (느리지만 안전하다.)
    
    @ParameterizedTest: 여러 파라마티들에 대해 결과값 테스트를 진행할 수 있다. @CsvSource, @MethodSource, @ValueSoruce 등과 같이 쓰인다.
    
    @DinamicTest: 동일 변수에 대해서 연속 시나리오를 작성할 수 있다. (ex, 재고 1개에 대해서 1개 차감하는 테스트와 재고가 0개가 되어서 1개 차감에 대해 실패하는 테스트다.) @TestFactory와 함께 쓰인다.