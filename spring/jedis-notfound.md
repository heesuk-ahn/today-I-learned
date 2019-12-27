# [TIL] Spring boot 2에서 Redis 사용시 Jedis not found 문제

* Spring boot 2에서 `redis`의 기본 클라이언트가 `jedis`가 아니라 `lettuce`라서 발생된 문제이다
* 사용하기 위해서는 `org.springframework.boot:spring-boot-starter-data-redis` 에서 `lettuce-core` 를 제거해주어야 한다.
* 그 후, `jedis`client를 추가하면 정상적으로 사용 가능하다.

```

```
