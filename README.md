![banner](banner-image.png)

Small knowledge gathers to become large knowledge.

## elastic-search

* [엘라스틱 서치 데이터 모델](elastic-search/data-model.md)

## kubernetes

* [Readiness probe 와 Liveness probe](kubernetes/healthcheck.md)
* [Helm 이란](kubernetes/what-is-helm.md)
* [쿠버네티스 볼륨 - 퍼시스턴트 볼륨과 퍼시스턴트 볼륨 클레임](kubernetes/kubernetes-volume.md)
* [kustomize `namePrefix` 와 `nameSuffix`](kubernetes/kustomization-name-prefix.md)

## cloud

* [Istio 는 미래의 Hybrid cloud 의 역할을 할 수 있다](cloud/istio-hybrid-cloud.md)
* [Istio ServiceEntry란?](cloud/istio-service-entry.md)

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
* [점진적 개선 - Strangler Pattern](design-patterns/strangler-pattern.md)
* [Bulk-Head Pattern](design-patterns/bulk-head-pattern.md)

## api-Gateway

* [Api Gateway Overview](api-gateway/api-gateway-overview.md)

##  clean-code

* [코드 가독성 - 도입과 원칙](clean-code/clean-code-rule.md)
* [코드 리뷰는 왜 해야하는가 (1)](clean-code/code-review.md)
* [코드 리뷰는 왜 해야하는가 (2)](clean-code/code-review2.md)
* [의미있는 이름 - 의도를 분명히 밝혀라](clean-code/naming/clean-code-naming1.md)
* [Extract Class](clean-code/extract-class.md)
* [형식 맞추기](clean-code/clean-code-format.md)
* [이펙티브 자바 - 생성자 대신 정적 팩터리 메서드를 고려하라](clean-code/factory-method.md)

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
* [Value Based Class](java/value-based-class.md)
* [[vavr] 자바 순수함수 라이브러리 VAVR란?(1)](java/vavr/what-is-vavr-libary.md)
* [[vavr] Try 를 이용하여 Exception Handling](java/vavr/vavr-try-example.md)
* [[vavr] Set 사용해보기](java/vavr/vavr-set-api.md)
* [Optional을 올바르게 사용하기](java/optional-practice.md)
* [이왕이면 Generic Type으로 만들라.](java/generic-type.md)
* [Java의 예외처리](java/java-exception.md)
* [sneaky Throws (몰래 예외처리하기) 란?](java/sneaky-throws.md)
* [Java ScheduledExecutorService](java/scheduled-executor-service-overview.md)

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
* [스프링과 캐시](spring/spring-cache.md)

## Spring cloud

* [Ribbon Eager Load 설정](spring/spring-cloud/ribbon-eager-load.md)
* [Spirng Cloud 환경 변수 관리를 위한 Config, Consul, Archaius 간단히 살펴보기](spring/spring-cloud/config-and-consul-and-archaius.md)

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

## React

* [react 란? (1)](react/what-is-react.md)
* [react 란? (2)](react/what-is-react-2.md)
* [Container & Component 아키텍처 패턴](react/container-component-pattern.md)

## React-Native

* [Hello World 출력하기](react-native/react-native-hello-world.md)
* [State의 사용](react-native/state.md)
* [Props의 사용](react-native/props.md)
* [TouchableOpacity 사용](react-native/touchable-opacity.md)

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
* [CIDR (사이더)란?](network/cidr-expression.md)

## infra

* [Nas 란?](infra/nas.md)

## Containers

* [리눅스 커널의 cgroups 이란?](container/what-is-cgroup.md)
* [리눅스 컨테이너란?](container/linux-container.md)

## opensource

* [Resilience4j Introduction](opensource/resilience4j/intro-resilience4j.md)
* [Archaius Overview](opensource/archaius/archaius-overview.md)
* [Archaius Get Started](opensource/archaius/archaius-get-started.md)
* [Archaius User Guide](opensource/archaius/archaius-user-guide.md)
* [Archaius Dynamic Properties Validation](opensource/archaius/archaius-prop-validation.md)
* [Archaius Deployment Context](opensource/archaius/archaius-deployment-context.md)
* [분산 처리 환경에서 사용 가능한 데이터 저장소, Zookeeper](opensource/zookeeper/zookeeper-overview.md)
* [분산 KEY-VALUE 스토어 ETCD 살펴보기](opensource/etcd/etcd-overview.md)
* [대규모 확장이 가능한 분산 추적 시스템 Grafana Tempo 발표](opensource/grafana/tempo-release.md)

## msa

* [아키텍트에 대한 진화적 관점](msa/evolutionary-perspective.md)
* [기술 부채](msa/technical-debt.md)

## database

* [binary log 란?](database/binary-log.md)
* [CPA 이론과 모순](database/cpa.md)
* [CPA 를 보완하기 위한 PACLE 이론](database/pacle.md)
* [WAL 파일이란? (Write Ahead Log)](database/wal-file.md)

## DevOps

* [SRE (Site Reliability Engineering) 이란?](devops/what-is-sre.md)
* [가용성 - 시스템이 다운되지 않고 정상 운영되는 시간의 비율](devops/availability.md)

## etc

* [RSS란](etc/rss.md)
* [라인의 장애 처리 프로세스](etc/error-report-process-by-line.md)
* [The Twelve-Factor App - “설정"](etc/config-by-twelve-factor.md)
* [[프로그래밍 패러다임] 선언형 프로그래밍 vs 명령형 프로그래밍](etc/programming-paradigm.md)
* [스프링 부트 실행이 로컬 mac 환경에서 느릴 때](etc/slow-spring-boot.md)
* [소프트웨어 장인 정신 - 일을 하는 것도 중요하지만 어떻게 하는지도 중요하다.](etc/software-craftmanship-1.md)
* [암달의 법칙이란](etc/Amdahl-raw.md)
* [기술을 안다는 것](etc/learning-skills.md)
* [하인리히의 법칙](etc/heinrich-raw.md)
* [Chunk 지향 처리](etc/chunk.md)
