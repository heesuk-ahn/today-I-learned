# [TIL] Spring Dependency Management `BOM`

* 스프링을 사용하다보면, 다양한 라이브러리들의 버전을 직접 관리하면, 라이브러리간 다른 버전의 라이브러리가 import 될 수 있다.
* 따라서 maven에 spring bom (bill of materials) 를 사용하면, 버전 관리를 중앙에서 해준다.
* 그래서 라이브러리에 직접 버전을 명시하지 않아도 된다.
* 예시로 직접 살펴보자.

```
// 1. buildScript 선언
// Gradle에서 제공 되는 빌드 기능 이외의 직접 만든 Plugin 기능이나 외부 기능(외부 라이브러리)을 사용하고자 한다면 추가로 정의
buildscript {
    // dependencies.classpath의 외부 라이브러리가 있는 repositories를 별도로 선언
    repositories {
        jcenter()
    }
    // Spring IO Platform의 Gradle Plugin인 dependency-management-plugin 선언
    dependencies {
        classpath 'io.spring.gradle:dependency-management-plugin:0.5.1.RELEASE'
    }
}

// 2. plugin 적용
apply plugin: 'java'
// Spring Dependency Management Plugin을 사용
apply plugin: 'io.spring.dependency-management'

// 3. Spring IO Platform 버전 지정
// Gradle에는 원래 정의 되지 않은 task 이지만 io.spring.dependency-management plugin 적용으로 새롭게 생긴 task 이며 Spring IO Platform의 버전을 선언 하게 됩니다.
dependencyManagement {
    imports {
        // Spring Dependency Management의 버전을 지정 하여 관련 의존성 라이브러리 버전을 같이 관리함.
        mavenBom 'io.spring.platform:platform-bom:1.1.2.RELEASE'
    }
}

// 4. 의존성 라이브러리 설정
repositories {
    jcenter()
}
dependencies {
    // 버전을 기입 하지 않으면 Spring Dependency Management에서 관리 되고 있는 버전으로 자동으로 사용하게 되어짐.
    compile 'org.springframework.boot:spring-boot-starter-web'
}


출처: https://gmind.tistory.com/entry/4-Gradle-프로젝트-만들기-with-스프링부트 [GMind]
```
