# Spring Webflux Controller Junit 5 테스트

* Webflux 컨트롤러 테스트를 위해 `WebFluxTest` 어노테이션이 제공된다.
* 이 어노테이션은 `Spring Webflux`의 인프라에 필요한 빈들과 일부 제한된 빈들을 auto configured 해준다.
* 일부 빈들은 @Controller, @ControllerAdvice, @JsonCompoennt, Converter, Webfliter 등
* 만약, 이 외에 빈이 더 필요하다면 `@Import` 를 통해서 직접 주입할 수 있다.
* 이렇게 일부 제한된 빈만 등록하는 이유는 테스트시의 필요한 빈들만 주입하여 테스트 속도를 높이기 위함이다.
* 아래 링크를 참고하면 특정 테스트 어노테이션에 로드되는 빈들의 정보를 알 수 있다.
  * https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-test-auto-configuration.html#test-auto-configuration
* 또한 WebfluxTest 어노테이션에서는 특정 컨트롤러 빈만 로드되도록 할 수도 있어서, @MockBean과 함께 하나의 컨트롤러를 테스트 하는데에 자주 사용되기도 한다.
* 이때에는 @Import로 안에 Mock Service를 위한 Bean을 주입해주어야 한다.
  * Ex) @Import(BookService.class)

### 참고 :

* https://howtodoinjava.com/spring-webflux/webfluxtest-with-webtestclient/
* https://vividcode.io/get-started-with-rsocket-part-2-spring-integration/
* https://github.com/rajshah91/tutorial-master/blob/ed2ce704020087757cd327277cac7ffd8a2a99b6/tutorials-master/spring-5-webflux/src/test/java/com/baeldung/spring/rsocket/client/MarketDataRestControllerIntegrationTest.java
