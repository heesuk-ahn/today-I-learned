# [TIL] java 8 `orElse` 와 `orElseGet` 차이

* Java 8 에서 `orElse` 는 `eagle evaluation (엄격한 평가)` 이다.
* Java 8 에서 `orElseGet` 은 `lazy evaluation (게으른 평가)` 이다.
* 이 두개가 어떤 차이가 있을까?
* 잘 못 사용할 경우 `side effect` 가 발생할 수 있다.

* 아래 예를 보자.

```
[orElse 사용한 코드]

public String sideEffectWithEagerEvaluation() {
    System.out.println("CALLED sideEffectWithEagerEvaluation");
    return "EagerEvaluation";
}

@Test
public void testOrElseValue() {
    Optional<Integer> optionalInteger = Optional.of(1);

    String res = optionalInteger.map(i -> {
        System.out.println("HO1");
        return "HELLO";
    }).orElse(sideEffectWithEagerEvaluation());

    System.out.println("RETURN VALUE : " + res);
}

[결과]

HO1
CALLED sideEffectWithEagerEvaluation
RETURN VALUE : HELLO
```

* 위에 예제를 살펴보면, optionalInteger 의 Optional<Integer> 에서 값이 있을 경우 Hello 를 반환하고, 값이 없을 경우
orElse를 사용하여 `EagerEvaluation`을 받는 것이다.
* 위의 실행 결과는 예상과는 다른 것을 알 수 있다.
* 실제 결과는 `Hello`를 받았지만, 중간에 orElse 에서 `sideEffectWithEagerEvaluation` 함수가 call 된 것을 알 수 있다.
* 이는 orElse는 엄격한 평가로 무조건 해당 함수가 계산되기 떄문이다.
* 이런 문제로 결과 값은 정상적으로 받았지만, 중간에 사이드 이펙트가 발생한 것을 알 수 있다.
* 이런 결과를 원하지 않는다면 `orElseGet`을 사용하여야 한다.
* `orElseGet`은 callByReference 로 호출 되기 때문에, 바로 값이 계산되지 않는 lazy 한 평가가 일어난다.

```
[orElseGet 사용한 코드]

public String sideEffectWithEagerEvaluation() {
    System.out.println("CALLED sideEffectWithEagerEvaluation");
    return "EagerEvaluation";
}

@Test
public void testOrElseValue() {
    Optional<Integer> optionalInteger = Optional.of(1);

    String res = optionalInteger.map(i -> {
        System.out.println("HO1");
        return "HELLO";
    }).orElseGet(sideEffectWithEagerEvaluation());

    System.out.println("RETURN VALUE : " + res);
}

[결과]

HO1
RETURN VALUE : HELLO
```

* 실제 실행 결과를 보면, 중간에 사이드 이펙트가 일어나지 않고 정상적으로 HELLO 만 결과 값으로 나온 것을 알 수 있다.

### 결론

* 만약 엄격한 평가를 하지 않는다면, orElse 보다는 orElseGet 을 사용하는 것이 좋다.
* 일반적으로도 orElse는 사이드 이펙트를 발생시킬 수 있기 때문에 orElseGet을 사용하는 것이 좋다.
