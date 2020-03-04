# Gradle `optional` 제공해주는 플러그인 Nebula Extra Configurations

## Nebula Extra Configurations

* 이 플러그인은 디펜던시에 optional 또는 provided 등의 플래그를 주어 배포시에는 제거할 수 있도록
지원해준다.


## optional scope

```
compile 'org.apache.commons:commons-lang3:3.3.2', optional
```

* 위에 scope 를 보면 optional 이 true 인 것을 알 수 있다.
* optional 이 true 이므로, 실제 jar 배포시에는 해당 디펜던시는 포함되지 않는다.

## provided scope

```
/* 배포시에는 빠지고, 컴파일시에는 들어가는 의존성을 provided에 지정한다. */
sourceSets {
    main {
        compileClasspath += configurations.provided
    }
    test {
        compileClasspath += configurations.provided
    }
}

// war 프로젝트일 경우
war {
    classpath -= configurations.provided
}
```

* 컴파일시에는 클래스 패스에 넣지만, 실행/배포시에는 빠져야 하는 의존성에 사용하면 유용하다.
