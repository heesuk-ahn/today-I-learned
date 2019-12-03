# [TIL] transitive dependency란?

## transitive dependency (전이 의존성)란?

* 어떤 라이브러리를 의존성으로 추가하면, 그 라이브러리 가지고 있는 의존성이 함께 딸려온다.
* 그렇게 딸려온 디펜던시들을 transitive dependency (전이 의존성) 이라고 한다.
* 이렇게 재귀적으로 라이브러리가 라이브러리를 요구하게 되는 상황을 전의 의존성이라 한다.
* 즉, 디펜던시의 디펜던시들이다.
  * MyProject
    * A
      * X
* 위와 같이 MyProject <- A 이고, MyProject <- X의 의존성이 생기게 된다.

## gradle이 제공하는 전이 의존성 간 충돌 방지 전략

* 최신 우선 : 가장 최신 의존성이 기본적으로 사용된다.
* 빠른 실패 : 버전 충돌이 일어나면 빠른 시간안에 실패한다. 이렇게 되면 개발자 스스로 충돌을 제어할 수 있게 된다. ResolutionStrategy 참조.
* 버전 충돌을 커스터마이징할 수 있게 Gradle을 만들어가고 있다.

* 버전 충돌 해결 방법
  * 충돌이 발생하는 라이브러리를 최상위 의존성으로 버전을 명시하여 강제(forced) 지정한다.
    * https://docs.gradle.org/current/dsl/org.gradle.api.artifacts.dsl.DependencyHandler.html 참고

```
dependencies {
  implementation('org.hibernate:hibernate:3.1') {
    //in case of versions conflict '3.1' version of hibernate wins:
    force = true

    //특정한 전이의존성 제거 (디펜던시 내의 라이브러리 모듈만 제거가능):
    exclude module: 'cglib' //by artifact name
    exclude group: 'org.jmock' //by group
    exclude group: 'org.unwanted', module: 'iAmBuggy' //by both name and group

    //disabling all transitive dependencies of this dependency
    transitive = false
  }
}

```    

## Dependency Management 활용: BOM

* 규모가 큰 라이브러리는 여러 모듈로 쪼개져서 배포되는 경우가 있다.
  * 예를 들어 Jackson은 jackson-core, jackson-databind, jackson-dataformat-yaml 등의 모듈로 나눠져 있다.
* 보통은 문제가 안되지만, 이렇게 __나눠진 모듈끼리 버전이 안 맞으면 공포의 ClassNotFoundException을 유발하는 원인이 된다.__
  * 예를 들어 jackson-core는 2.8인데 jackson-databind는 2.6 일 수 있다.
* 그래서 이렇게 쪼개진 라이브러리들은 대부분 `bill of materials (BOM)`을 함께 배포한다.
* BOM을 임포트하면 __해당 라이브러리의 모든 모듈을 특정 버전으로 고정할 수 있다.__

### 참고

* https://blog.sapzil.org/2018/01/21/taming-maven-transitive-dependencies/
