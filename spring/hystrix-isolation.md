#  Hystrix - Isolation 전략 Thread vs Semaphore

## Hystrix 간단한 설명

* 분산 시스템에서, 불가피하게 많은 서비스 종속 중에서 일부는 실패를 할 수 밖에 없다.
* `Hystrix`는 장애 내성과 지연 내성을 가진 분산된 서비스 들 간의 통신을 돕는 라이브러리이다.
* `Hystrix`는 Circuit Breaker 의 구현체이기도 하다.
* 관련 글 : https://martinfowler.com/bliki/CircuitBreaker.html
* `Hystrix` 에서는 __다른 서비스가 다른 서비스에 영향을 끼치지 않도록, 격리를 시켜야 할 책임이 있다.__
* 격리의 방법으로는 2가지가 존재한다.
  * `Thread & Thread Pools`
  * `Semaphore`
* `Thread` 방식에서는 서비스 호출이 별도의 스레드에서 수행된다.
  * 예컨데, Tomcat의 스레드 풀과 서비스에 대한 호출 스레드가 격리가 되는 것이다.
  * 이렇게 하면, 네트워크 상의 타임아웃 위에 스레드 타임아웃을 둘 수 있다.
  * 하지만, 별도의 스레드를 사용하는 만큼 비용이 수반된다. 이를 가리켜 __연산 오버헤드__ 라고도 표현한다.
* `semaphore` 방식에서는 서비스 호출을 위해 별도의 스레드를 만들지 않는다.
  * __단지 각 서비스에 동시 호출 수를 제한할 뿐이다.__
  * 동시적인 요청을 스레드 세마포 카운트를 이용해서 차감하는 방식이다.

## Hystix Isolation overview

![hystrix-isolation](../static/spring/hystrix-isolation.png)

* 그렇다면, 세마포 격리 방식이 thread pool 방식보다는 좋은 것 아닌가?
  * 많은 스레드를 사용하면서 발생하는 CPU 오버헤드가 발생하지 않기 때문에.
* 그러나, Hystrix Configuration 문서에서는 Thread 격리를 권장한다고 한다.
* 서비스 호출의 지연으로부터 보호되는 별도의 계층을 가질 수 있기 때문이다. 위의 이미지에서 보면 `Thread Timeouts` 부분이다.
* 그리고 `Semaphore`를 써야하는 경우는 몇몇 경우에서만 권장한다.
  * 호출량이 너무 많아서 분리된 스레드의 사용이 주는 오버헤드가 큰 경우.
  * 네트워크 요청이 발생하지 않는 경우
* 그러면  `Semaphore` 를 선택하게되면 어떤 문제가 생길 수 있을까?
  * 성능적으로는 thread 풀을 따로 할당하는 비용이 들지 않으므로 thread-context switch 비용이 적어서 유리할 것.
  * 그러나, 만약 특정 서비스에서 지연이 발생하게 되면 thread time out 효과를 기대할 수 없다.

### 참고 :
* https://github.com/Netflix/Hystrix/wiki/Configuration#thread-or-semaphore (넷플릭스 Hystrix)  
* http://woowabros.github.io/experience/2017/08/21/hystrix-tunning.html (Hystrix, APi Gateway를 도와줘)
