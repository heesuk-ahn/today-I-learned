# [JAVA] sneaky Throws (몰래 예외처리하기) 란?

* Java 의 CheckedException 은 반드시 try - catch 를 통해서 에러를 핸들링 하거나, throws 를 메서드에
붙여서 강제적으로 처리해야한다.
* CheckedException 은 일반적으로 RunTimeException 이 아닌 예측이 가능한 예외라는 점에서 메서드 시그니처에
throws 를 붙여서 `예외 회피`를 하거나, 다른 값으로 `예외 복구`, 또는 구체적인 예외로 변환하여 `예외` 전환을
해야한다.
* java 8 에서는 이러한 CheckedException 으로 인해서 함수형 인터페이스를 제대로 활용하지 못하는 경우
들이 있다.
* 아래와 같은 예를 살펴보자.

```
private URL createURL(String url) throws MalformedURLException {
  return new URL(url);
}
```

* 위 코드를 보면, URL 을 생성한다. 하지만 CheckedException 인 `MalformedURLException` 가
정의되어있다.

```
Stream
  .of("https://www.hahnekamp.com", "https://www.austria.info")
  .map(url -> {
    try {
      return this.createURL(url);
    } catch (MalformedURLException mue) {
      throw new RuntimeException(mue);
    }
  })  
  .collect(Collectors.toList());
```

* Java 8 Stream API 에서 이를 처리하기 위해서는 강제적으로 Error 에 대한 처리를 해주어야 한다.
* 즉, try ~ catch 를 사용해야 하는 것이다.
* 이거는 __nice 해 보이지는 않는다.__
* 이럴 때 사용할 수 있는 것이 `sneaky throw (몰래 예외처리하기)` 이다.
* 자바에서 `sneaky throw` 는 throw가 메서드의 signature 로 정의되어 있지않지만, `checked exception` 을
throw 할 수 있도록 허용하는 개념이다.
* 따라서 `throws` 선언을 생략하여 runtime exception 의 특성을 효과적으로 모방할 수 있다.

```
public static <E extends Throwable> void sneakyThrow(Throwable e) throws E {
    throw (E) e;
}
```

* 위와 같이 `<E extends Throwable>` 라는 Generic Type 을 사용한다.
* 일반적으로`<E extends Throwable>` 는 컴파일러와 Java Runtime 에 `RuntimeException` 으로 추론된다.
* 그래서 우리는 `CheckedException` 을 `UnCheckedException` 으로 속여서 전파할 수 있는 것이다.
* 여기서 한가지 살펴봐야할 점은 자바 런타임에 던져지는 Error Exception 은 단지 `<E extends Throwable>`
타입이기 때문에, 예외에 타입에 대해서는 알 수 없다는 점이다.

```
sneakyThrow(new IOException());
```

* IOException 은 CheckedException 이지만, sneakyThrow 를 통해서 UnCheckedException
으로 전파되어서 메서드에 throws 키워드를 사용하거나, try ~ catch 를 하지않고도 익셉션을 전파할 수
있다.
* 이제 이걸 Functional 하게 사용할 수 있도록 수정해보자.

```
static <T, R> Function<T, R> unchecked(ThrowingFunction<T, R> f) {
    return t -> {
        try {
            return f.apply(t);
        } catch (Exception ex) {
            return ThrowingFunction.sneakyThrow(ex);
        }
    };
}
```

* 위와 같이 throw 이거나 값을 지니고 있는 `ThrowingFunction` 을 파라미터로 받는다.
* 이 메서드를 이용하여 위의 예제를 다시 수정하면 아래와 같이 사용이 가능하다.

```
Stream
  .of("https://www.hahnekamp.com", "https://www.austria.info")
  .map(unchecked(this::createURL))
  .collect(Collectors.toList());
```

## 결론

* sneakyThrow 를 사용하면, try ~ catch 를 사용하지 않고도 CheckedException 을 UnCheckedException
으로 전파가 가능하다.
* 하지만, 에러를 catch 할 때, 구체적인 익셉션 타입은 알 수 없다.
* sneakyThrow 를 이용해서 functional 한 인터페이스를 만들 수 있다.

### 참고)

* https://www.geeksforgeeks.org/exceptions-in-java/
* https://www.baeldung.com/java-sneaky-throws
* https://dzone.com/articles/sneakily-throwing-exceptions-in-lambda-expressions
* https://github.com/rainerhahnekamp/sneakythrow
