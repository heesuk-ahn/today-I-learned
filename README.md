![banner](banner-image.png)

Small knowledge gathers to become large knowledge.

## elastic-search

* [엘라스틱 서치 데이터 모델](elastic-search/data-model.md)

## kubernetes

* [Readiness probe 와 Liveness probe](kubernetes/healthcheck.md)
* [Helm 이란](kubernetes/what-is-helm.md)

## gradle

* [Gradle이란](gradle/gradle-intro.md)
* [Gradle-Plugin에 대해서](gradle/gradle-plugin.md)
* [Gradle-Project에 대해서](gradle/gradle-project-api.md)
* [Gradle-BuildScript](gradle/gradle-build-script.md)
* [transitive dependency란](gradle/transitive-dependency.md)
* [버전을 직접 입력하지 않아도 임포트되는 dependencies with bom](gradle/without-version-bom.md)
* [gradle에서 라이브러리 버전 비교하는 Task 만들기](gradle/comparing-version.md)
* [gradle 에서 라이브러리 버전을 제한하는 `constraints`](gradle/gradle-constraint-lib.md)
* [gradle optional](gradle/optional-plugin.md)

## design-patterns

* [sidecar pattern이란](design-patterns/side-car-pattern.md)
* [프록시 패턴이란](design-patterns/proxy-pattern.md)
* [10가지 아키텍처 - 계층화 패턴](design-patterns/10-architecture-layered.md)
* [Factory Pattern](design-patterns/factory-pattern.md)

## api-Gateway

* [Api Gateway Overview](api-gateway/api-gateway-overview.md)

##  clean-code

* [코드 가독성 - 도입과 원칙](clean-code/clean-code-rule.md)
* [코드 리뷰는 왜 해야하는가 (1)](clean-code/code-review.md)
* [코드 리뷰는 왜 해야하는가 (2)](clean-code/code-review2.md)
* [의미있는 이름 - 의도를 분명히 밝혀라](clean-code/naming/clean-code-naming1.md)
* [Extract Class](clean-code/extract-class.md)

## deploy

* [canary 배포 전략](deploy/deploy-canary.md)

## Java

* [java-8-stream-api](java/java-8-stream-overview.md)
* [자바8에서 인터페이스와 추상클래스](java/java-8-interface-vs-abstract.md)
* [Java 8 Function<T, R>](java/java-8-funtion.md)
* [Java 8 BiFunction<T, U, R>](java/java-8-bifunction.md)
* [Java 8 Consumer<T>](java/java-8-consumer.md)
* [Java 8 `orElse` 와 `orElseGet` 차이](java/java-8-orelse-orelseget.md)
* [Java 8 람다 캡처링](java/lambda-capturing.md)
* [Java 12 Shenandoah-gc](java/java-12-shenandoah-gc.md)
* [Garbage Collector란](java/what-is-gc.md)
* [Java method parameter `final`가 의미하는 것은?](java/parameter-final.md)
* [[vavr] 자바 순수함수 라이브러리 VAVR란?(1)](java/vavr/what-is-vavr-libary.md)
* [[vavr] Try 를 이용하여 Exception Handling](java/vavr/vavr-try-example.md)
* [[vavr] Set 사용해보기](java/vavr/vavr-set-api.md)

## Spring

* [bean singleton](spring/bean-singleton.md)
* [@SpringBootAnnotation 의 @ComponentScan 범위](spring/bean-scan-spring-annotation.md)
* [Spring boot 2.2 부터 Immutable properties 사용 가능](spring/immutable-property.md)
* [`@Configuration` vs `@ConfigurationProperties`](spring/configuration-vs-configuration-property.md)
* [Hystrix - Isolation 전략 Thread vs Semaphore](spring/hystrix-isolation.md)
* [Spring-Cloud bootstrap.yml 파일](spring/bootstrap-yml-file.md)
* [Spring-Data-Jpa 연관관계 (단방향)](spring/spring-data-jpa-singgle-mapping.md)
* [Spring Data Jpa 연관관계 (양방향)](spring/spring-data-jpa-bidirectional.md)
* [Spirng `@WebFluxTest` 어노테이션](spring/spring-webflux-unit-test.md)
* [Spring `@JsonCreator` 어노테이션](spring/jsoncreator-annotation.md)
* [Spring Dependency Management `BOM`](spring/spring-dependency-management-bom.md)
* [Lombok annotation - @EqualsAndHashCode(callSuper = true)](spirng/lombok-equals-and-hash.md)
* [Jedis dependecny configuration](spring/jedis-notfound.md)
* [EntityManagerFactory, EntitiyManager, PersistenceContext란?](spring/entity-manager.md)
* [Spring ApplicationEventPublisher](spring/application-event-publisher.md)
* [스프링 AOP 살펴보기](spring/spring-aop.md)
* [스프링 AOP 살펴보기 - 프록시패턴과 AOP](spring/spring-aop-proxy-pattern.md)
* [스프링 batch란?](spring/spring-batch/what-is-batch.md)

## Spring cloud

* [Ribbon Eager Load 설정](spring/spring-cloud/ribbon-eager-load.md)

## TDD

* [Java Junit 에서 Log 테스트하기](tdd/log-unit-test.md)

## lambda

* [람다 대수란 무엇인가](lambda/what-is-lambda.md)

## python

* [python에서의 lambda 사용 예제](python/python-lambda.md)
* [python venv 환경](python/python-venv.md)

## Reactive Programming

* [HOT & COLD Observable (1)](reactive-programming/hot-cold-observable-1.md)
* [리액티브 프로그래밍을 구성하는 블록들](reactive-programming/reactive-programming-building-block.md)

## monitoring

* [Hubble - 쿠버네티스를 위한 분산 모니터링 시스템](monitoring/humbble-for-kubernetes-monitoring.md)

## protocol

* [RSocket 살펴보기](protocol/rsocket-preview.md)
* [Spring Cloud RSocket](protocol/rsocket-1.md)
* [RSocket vs gRPC](protocol/rsocket-vs-grpc-1.md)

## Groovy

* [Groovy Single vs Double quote](groovy/groovy-single-double-quote.md)

## Security

* [SQL Injection](security/sql-injection.md)

## JS

* [JS Dev ecosystem](js/js-dev-ecosystem.md)

## git

* [git remote 를 ssh로 변경하기](git/change-git-remote-to-ssh.md)

## scala

* [스칼라 공변성이란?](scala/covariant.md)

## network

* [MimeType 이란?](network/what-is-mime-type.md)
* [Connection Timeout 과 Read Timeout](network/read-and-connection-timeout.md)

## Containers

* [리눅스 커널의 cgroups 이란?](container/what-is-cgroup.md)
* [리눅스 컨테이너란?](container/linux-container.md)

## opensource

* [Resilience4j Introduction](opensource/resilience4j/intro-resilience4j.md)

## etc

* [RSS란](etc/rss.md)
* [라인의 장애 처리 프로세스](etc/error-report-process-by-line.md)
* [The Twelve-Factor App - “설정"](etc/config-by-twelve-factor.md)
* [[프로그래밍 패러다임] 선언형 프로그래밍 vs 명령형 프로그래밍](etc/programming-paradigm.md)
* [스프링 부트 실행이 로컬 mac 환경에서 느릴 때](etc/slow-spring-boot.md)
* [소프트웨어 장인 정신 - 일을 하는 것도 중요하지만 어떻게 하는지도 중요하다.](etc/software-craftmanship-1.md)
* [암달의 법칙이란](etc/Amdahl-raw.md)
