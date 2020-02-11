# [Java Functional Library Varv] Try 를 이용하여 Exception Handling

* Varv 에서는 `Try` 타입으로 통해 `Exception` side effect 를 순수 함수로 처리할 수 있다.
* 아래와 같은 상황을 고려해보자.

```
Object rawObject = "A";

System.out.println((Integer) rawObject);

//BOOM!!
```

* 위와 같은 경우에 rawObject 를 강제로 Integer 로 강제 형변환을 하게 되면, `java.lang.String cannot be cast to java.lang.Integer`
Exception 이 발생하게 된다.
* 위와 같은 `강제 형변환`은 사이드 이펙트를 담고 있다.
* Try 를 통해서 사이드 이펙트를 감싸서 순수 함수로 처리해보자.
* 여기서 순수 함수란, 사이드 이펙트가 없는 함수이며, 참조 투명한 것을 의미한다.
  * 참조 투명성? 함수의 결과 값으로 대체해도 변함이 없는 경우.

```
[varv Try 로 감싼 경우]

Object value = "a";
Try<Integer> integerTry = Try.of(() -> (Integer) value);

//Failure(java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Integer)
```  

* 위의 경우를 살펴보면 `ClassCastException` 이 `Failure` 로 감싸진 것을 알 수 있다.
* 감싸진 Try 에서 원하는 Custom Exception 으로 처리할 수 도 있다.

```
[Exception Recover]


Object value = "a";

Try.of(() -> (Integer) value)
  .recoverWith(throwable -> Try.failure(new RuntimeException("ERROR!")));

//Failure(java.lang.RuntimeException: ERROR!)
```

* 이 Try 를 이용하면 Try 의 Monad 성질로 부수효과가 있는 여러 Try 를 조합하여서 깔끔하게 에러를 핸들링 할 수 있다.

```
object value = "FOO";

Try<Integer> sideEffectOne = Try.of(() -> (Integer) value).recoverWith(throwable -> Try.failure(new RuntimeException("First Exception")));
Try<Integer> sideEffectTwo = Try.of(() -> (Integer) value).recoverWith(throwable -> Try.failure(new RuntimeException("Second Exception")));

Try<Integer> res = sideEffectOne
        .flatMapTry(v1 -> sideEffectTwo
        .flatMapTry(v2 -> Try.of(() -> v1 + v2)))
        .onSuccess(s -> log.info("success!"))
        .onFailure(throwable -> log.error("Exception!! --> " + throwable.getMessage()));

// Exception!! --> First Exception
```

* 위의 코드를 보면, 2개의 사이드 이펙트를 지니고 있는 Try 를 모나드 성질을 통해 조합을 했다.
* 원하는 것은 각각의 Try 의 Integer 값을 더하는 것이다.
* 하지만, `sideEffectOne` 에서 `Exception` 이 발생한 경우이다.
* 이 경우, 만약 따로 `onFailure` 로 조합된 값에서 핸들링을 하지 않는다면, `new RuntimeException("First Exception")` 이 발생하게 될 것이다.
* 하지만, onFailure 로 맨 마지막에 발생된 `Exception` 을 핸들링 해 줄 수도 있다.
