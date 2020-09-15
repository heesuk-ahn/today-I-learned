# Resilience4j-bulkhead Overview

* Resilience4j 에서 제공해주는 bulkhead 패턴이다.
* Resilience4j-BulkHead 는 아래와 같은 2가지 bulkhead pattern 구현체를 제공한다.
  * SemaphoreBulkhead : 세마포를 이용한다.
  * FixedThreadPoolBulkhead : 고정된 thread pool 을 제공한다.

# 설정

* maxConcurrentCalls :
  * BulkHead가 요청 허용하는 최대 Call 수
  * 기본값은 25이다.
  * 만약 25가 꽉 차게되면 어떻게 될까? 에러가 발생하나? 아니면 wait가 발생하나?
