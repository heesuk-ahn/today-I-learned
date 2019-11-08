# @SpringBootAnnotation 의 @ComponentScan 범위

* Spring Boot Annotation 은 내부에 @ComponentScan을 가지고 있다.
* @ComponentScan은 Bean을 스캔한다.
* 이때, 스캔하는 경로는 __현재 @SpringBootAnnotation 이 적용된 package의 하위 디렉토리에서 모든 Bean을 스캐닝한다.__
* 예를들어, 만약 현재 @SpringBootAnnotation 이 `com.root.gateway`에 있다면, 스캐닝 하는 하위 디렉토리는 패키지가 gateway의 하위에 있어야 한다.
* 이 말은 즉슨  `com.root.other` 이라는 패키지에 속해있는 bean은 스캐닝 대상이 아니라는 것이다.
* 이런 경우에는 `@ComponentScan()` 을 추가해서 어노테이션 스캔을 할 모든 패키지를 적어주어야 한다.
* 예를 들어, @ComponentScan({"com.project.web", "com.project.business”}) 와 같이 추가를 해주어야 한다.
