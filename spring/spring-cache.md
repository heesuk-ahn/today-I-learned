# 스프링과 캐시

## 캐시

* 컴퓨터 사이언스에서 '캐시'는 무엇을 의미할까?
  * WIKI: "컴퓨터 과학에서 데이터나 값을 미리 복사해 놓는 임시 장소를 가리킨다."
* 캐시는 위에 정의 처럼 원래 데이터를 접근하는데에 오래 걸리는 경우나 데이터를 다시 계산하는데에
걸리는 시간을 줄이기 위해 사용한다.
* 캐시에는 여러가지 종류가 있다. 하드웨어에서의 캐시도 있고, 소프트웨어에서의 캐시도 있다.
  * 하드웨어에서의 캐시에는 CPU 캐시 (L1 캐시, L2 캐시), 디스크 캐시 등이 있다.
  * 브라우저 캐시는 브라우저에 이미지 등 정보를 미리 저장해서 재사용한다.
  * 데이터 베이스 캐시는 동일한 쿼리에 대한 결과 값을 저장하여 처리하는 캐시이다.

## 캐시의 용어

* 효율적으로 캐시를 사용하기 위해서는 캐시에 대한 용어들도 알아야 한다.
  * Cache : 캐시는 미래에 필요할 것이고 빠르게 검색할 수 있는 데이터를 담은 저장소 라고 정의 한다.
  * Cache Hit : 데이터 요소가 캐시에 요청되고 해당 키에 대한 요소가 존재하면 Cache Hit (또는 단순히 'Hit')라고 한다.
  * Cache Miss : 데이터 요소가 캐시에 요청되고 해당 키에 대해 요소가 존재하지 않으면 Cache Miss (또는 단순히 'Miss')라고 한다.
* 당연히 효율적인 캐시를 설계하기 위해서는 Cache Hit 가 높아야 할 것이다.

## 스프링에서의 캐시

* 스프링에서도 소프트웨어 레벨에서의 캐시를 지원한다.
* Spring 3.1버전부터 Spring Application에 캐시를 쉽게 추가할 수 있도록 기능을 제공하게 되었다.
* Spring에서 캐시 추상화는 메소드를 통해 기능을 지원하는데, 메소드가 실행되는 시점에 파라미터에 대한 캐시 존재 여부를 판단하여 없으면 캐시를 등록하게 되고,
캐시가 있으면 메소드를 실행 시키지 않고 캐시 데이터를 Return 해주게 된다.
* Spring 캐시 추상화를 지원하기 때문에 개발자는 별도의 캐시 로직을 작성하지 않아도 된다.
하지만 캐시를 저장하는 저장소는 직접 설정을 해주어야 한다. Spring에서는 CacheManager라는 Interface를 제공하여 캐시를 구현하도록 하고 있다.

```
[Spring Cache dependecy]

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

* Spring Boot에서는 spring-boot-starter-cache Artifact를 추가 하여 CacheManager를 구성 할 수 있다.
* __기본적으로 별도의 추가적인 서드파티 모듈이 없는 경우에는 Local Memory에 저장이 가능한 ConcurrentMap기반인 ConcurrentMapCacheManager가 Bean으로 자동 생성 된다.__
* 이외에도 EHCache, Redis등의 서드파티 모듈을 추가 하게 되면 EHCacheCacheManager, RedisCacheManager를 Bean으로 등록 하여 사용할 수 있다.
* 이렇게 되면 별도로 다른 설정 없이도 단순 Memory Cache가 아닌 Cache Server를 대상으로 캐시를 저장 할 수 있도록 지원하고 있다.

### 스프링 캐시 어노테이션

* Spring에서는 Cache Annotation을 지원하여 좀 더 쉽게 메서드에 대한 캐시를 제어할 수 있다.

```
* @EnableCaching
  * Annotation 기반 캐싱 설정을 사용 (내부적으로 Spring AOP 이용)
  * proxyTargetClass
    * proxy 로직을 `JDK proxy` 를 사용할지, `CGLib Proxy` 를 사용할지 결정한다.
    * default 는 false 이다
    * false -> `JDK Proxy` 사용, true -> `CGlib Proxy` 를 사용한다
  * mode
    * 위빙 모드에 대한 설정
    * Default PROXY : 기존의 Spring AOP 를 이용한 RTW(Run Time Weaving) 방식
    * ASPECTJ : aspectj 라이브러리를 이용한 CTW, LTW 방식 지원
  * order : AOP order 설정 | Default Integer.MAX_VALUE
```

```
* @Cacheable
  * 캐싱할 수 있는 메서드를 지정하기 위해 사용
  * cacheName : 캐시의 이름
  * key : 캐시명이 같을 때, 구분하는 값
  * cacheManager : 사용할 캐시 매니저 설정 (EHCacheManager, RedisCacheManager등)
  * cacheResolver : Cache 키에 대한 결과 값을 돌려주는 Resolver. 결과값을 Custom 하게 처리할 수 있다.
  * condition : SpEL 표현식을 통해 특정 조건에 부합하는 경우에만 캐시 사용. 연산 조건이 true인 경우에만 캐싱
  * unless : 캐싱이 이루어지지 않는 조건을 설정. 연산 조건이 true 이면 경우에는 캐싱되지 않는다.
  예시) id가 null아 아닌 경우에만 캐싱 (unless = "#id == null")
  * sync : 캐시 구현체가 Thread safe 하지 않는 경우, 자체적으로 캐시에 동기화를 거는 속성. Default false
```

```
* @CacheEvict
  * 메서드 실행 시, 해당 캐시를 삭제
  * cacheName : 캐시명
  * key : 같은 캐시명을 사용 할 때, 구분되는 구분 값
  * keyGenerator : 특정 로직에 의해 cache key를 만들고자 하는 경우 사용
  * cacheManager : 사용할 CacheManager를 지정 (EHCacheManager, RedisCacheManager등)
  * cacheResolver : Cache 키에 대한 결과 값을 돌려주는 Resolver. 결과값을 Custom 하게 처리할 수 있다.
  * condition : SpEL 표현식을 통해 특정 조건에 부합하는 경우에만 캐시 사용. 연산 조건이 true인 경우에만 캐싱
  * allEntries : Cache Key에 대한 전체 데이터 삭제 여부
  * beforeInvocation : true면 메서드 실행 이전에 캐시 삭제, false면 메서드 실행 이후 삭제
```

```
* @CachePut
  * 메서드 실행에 영향을 주지 않고 캐시를 갱신해야 하는 경우 사용
  * 보통은 @Cacheable과 @CachePut Annotation을 같이 사용하지 않는다.
   (둘은 다른 동작을 하기 때문에, 실행순서에 따라 다른 결과가 나올 수 있다.)
  * @CachePut Annotation은 캐시 생성용으로만 사용한다.
  * cacheName : 캐시명
  * key : 같은 캐시명을 사용 할 때, 구분되는 구분 값
  * keyGenerator : 특정 로직에 의해 cache key를 만들고자 하는 경우 사용
  * cacheManager : 사용할 CacheManager를 지정 (EHCacheManager, RedisCacheManager등)
  * cacheResolver : Cache 키에 대한 결과 값을 돌려주는 Resolver. 결과값을 Custom 하게 처리할 수 있다.
  * condition : SpEL 표현식을 통해 특정 조건에 부합하는 경우에만 캐시 사용. 연산 조건이 true인 경우에만 캐싱
  * unless : 캐싱이 이루어지지 않는 조건을 설정. 연산 조건이 true 이면 경우에는 캐싱되지 않는다.
  예시) id가 null아 아닌 경우에만 캐싱 (unless = "#id == null")
```

```
* @Caching
  * @CacheEvict이나 @CachePut을 여러개 지정해야 하는 경우에 사용
  * 조건식이나 표현식이 다른 경우에 사용한다.
  * 여러가지의 key에 대한 캐시를 중첩적으로 삭제해야 할 때 사용
  * cacheable[]	: @Cacheable 적용할 Annotation을 등록한다.
  * put[] : @CachePut 적용할 Annotation을 등록한다.
  * evict[] : @CacheEvic 적용할 Annotation을 등록한다.
```

```
* @CacheConfig
  * 클래스 단위로 캐시설정을 동일하게 하는데 사용
  * 이 설정은 CacheManager가 여러개인 경우에만 사용
  * Member조회 클래스에서는 Redis기반 캐시를 사용하고 Product 조회 클래스에서는 EHCache 기반 캐시를 사용할 때 각 클래스 별로 CacheManager를 지정 가능
  * cacheNames : 캐시명
  * cacheManager : 사용할 CacheManager를 지정 (EHCacheManager, RedisCacheManager등)
  * cacheResolver : Cache 키에 대한 결과 값을 돌려주는 Resolver. 결과값을 Custom 하게 처리할 수 있다.
```

### 참고)

* wiki: https://ko.wikipedia.org/wiki/%EC%BA%90%EC%8B%9C
* https://jaehun2841.github.io/2018/11/07/2018-10-03-spring-ehcache/#spring-cache-abstraction
