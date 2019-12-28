
# [TIL] 스프링 AOP 살펴보기

* AOP 란 관점지향 프로그래밍 언어라고도 한다.
* AOP는 프로그램의 핵심인 __비즈니스 로직과 분리되는 횡단관심사(cross-cutting concern)를 분리하여 모듈화하여 설계하고 개발하는 방법론__
이다.

![crosscutting](../static/spring/aop-crosscutting.png)

* 위와 같이 __횡단관심사를 따로 분리하여 핵심로직에 영향을 주지않고 핵심로직에서 벗어나 새로운 관점에서 바라본다하여 관점지향 프로그래밍(AOP)라 한다.__

## 흩어진 AAA 와 BBB

```
class A {
  method a() {
    AAA
    블라블라1
    BBB
  }
  method b() {
    AAA
    블라블라2
    BBB
  }
}

class B {
  method c() {
    AAA
    블라블라3
    BBB
  }
}
```

* 코드에서 중복은 죄악이다.
* 왜냐면 코드가 커지면 커질 수록 관리하기가 어려워지기 때문이다.
* 왜 관리하기가 어려울까? 만약 위의 AAA와 BBB 로직을 수정한다고 해보자.
* 그때마다 AAA와 BBB가 사용된 모든 클래스에서 수정을 해주어야 한다.
* 그러다가 어떤 부분에서는 __실수로 수정을 못한다면?__ 버그가 발생하게 될 것이다.
* `위와 같은 코드를 어떻게하면 개선할 수 있을까` 에서 AoP (관점지향 프로그래밍)이 등작하게 되었다.

## 모아 놓은 AAA와 BBB

```
class A {
  method a() {
      블라블라1
  }
  method b() {
      블라블라2
  }
}

class B  {
  method c() {
      블라블라3
  }
}

class AAABBB {
  method aaabbb(JoinPoint point) {
    AAA
    point.execute();
    BBB
  }
}
```

* 위에 예제를 보면 반복되던 AAA와 BBB를 모아서 `AAABBB` 클래스를 만들었다.
* 이러한 것을 관점지향 프로그래밍이라고 한다.
* 그러면 이러한 AoP는 어떻게 구현할 수 있을까?

## 다양한 AoP 구현 방법

* 컴파일 시에 AOP 코드를 집어넣는 방법 : A.java --> (AOP) --> A.class (AspectJ)
* 바이트 코드 조작 하는 방법 : A.java -> A.class --> (AOP) --> 메모리
클래스 로더가 클래스를 읽어올 때, 조작한다. (AspectJ 제공)
* 프록시 패턴 (스프링 AOP가 사용하는 방법)
  * 프록시 패턴에 대해서는 따른 TIL 에 정리하여서 스프링 AOP에 대해서 이해하도록 한다.

### 참고

* https://www.inflearn.com/course/spring_revised_edition (예제로 배우는 스프링 입문 - 백기선 (인프런 강의))
* https://gangnam-americano.tistory.com/57 ([Spring] AOP(Aspect Oriented Programming)이란?)
