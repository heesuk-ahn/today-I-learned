# resilience4j Introduction

* resilience4j의 공식 문서를 번역하고자 한다.

## Introduction

* `Resilience4j`는 `Netflix Hystrix`로 부터 영감을 받은 가벼운 fault tolerance 라이브러리 이다.
* `Resilience4j` 는 JAVA 8 과 함수형 프로그래밍으로 작성되어 있다.
* `Resilience4j` 는 오직 `Vavr` 라이브러리를 사용하고 다른 외부적인 라이브러리를 사용하지 않기 때문에,
가볍다.
* 반면에 `Netflix Hystrix` 는 `Guava` 및 `Apache Commons Configuration` 과 같은 외부 라이브러리 종속성이 훨씬 많은 `Archaius`에 대한 컴파일 종속성이 있다.
* 또한, __NetFlix Hystrix는 더이상 개발하지 않고 유지 모드이다.__
* `Resilience4j`는 `Circuit Breaker`, `Rate Limiter`, `Retry or Bulkhead`를
functional interface, lambda 표현식, method reference 로 향상시키기 위해서 `고차 함수 (higher-order)` 들을
제공합니다.
* 하나 이상의 함수형 인터페이스, 람다 표현식 또는 메서드 참조를 사용해서 하나 이상의 `Decorator`들을
합쳐서 사용할 수 있다.
* 이것의 장점은 decorators 를 필요하면 필요한 것들만 골라서 사용할 수 있다는 것이다.
* 여기서 말하는 `Decorator` 는 위에서 언급한 `Circuit Breaker`, `Rate Limiter` 등이다.
* 아래의 예제 코드를 보면 좀 더 이해하기가 좋을 수 있다.

```
// Create a CircuitBreaker with default configuration
CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("backendService");

// Create a Retry with default configuration
// 3 retry attempts and a fixed time interval between retries of 500ms
Retry retry = Retry.ofDefaults("backendService");

// Create a Bulkhead with default configuration
Bulkhead bulkhead = Bulkhead.ofDefaults("backendService");

Supplier<String> supplier = () -> backendService
  .doSomething(param1, param2);

// Decorate your call to backendService.doSomething()
// with a Bulkhead, CircuitBreaker and Retry
// **note: you will need the resilience4j-all dependency for this
Supplier<String> decoratedSupplier = Decorators.ofSupplier(supplier)
  .withCircuitBreaker(circuitBreaker)
  .withBulkhead(bulkhead)
  .withRetry(retry)
  .decorate();

// Execute the decorated supplier and recover from any exception
String result = Try.ofSupplier(decoratedSupplier)
  .recover(throwable -> "Hello from Recovery").get();

// When you don't want to decorate your lambda expression,
// but just execute it and protect the call by a CircuitBreaker.
String result = circuitBreaker
  .executeSupplier(backendService::doSomething);

// You can also run the supplier asynchronously in a ThreadPoolBulkhead
 ThreadPoolBulkhead threadPoolBulkhead = ThreadPoolBulkhead
  .ofDefaults("backendService");

// The Scheduler is needed to schedule a timeout on a non-blocking CompletableFuture
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
TimeLimiter timeLimiter = TimeLimiter.of(Duration.ofSeconds(1));

CompletableFuture<String> future = Decorators.ofSupplier(supplier)
    .withThreadPoolBulkhead(threadPoolBulkhead)
    .withTimeLimiter(timeLimiter, scheduledExecutorService)
    .withCircuitBreaker(circuitBreaker)
    .withFallback(asList(TimeoutException.class, CallNotPermittedException.class, BulkheadFullException.class),
      throwable -> "Hello from Recovery")
    .get().toCompletableFuture();
```
