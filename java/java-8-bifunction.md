# [TIL] Java 8 BiFunction<T, U, R>

* java의 Functional interface 중의 하나로 `BiFunction`이 있다.
* `BiFunction`은 __2개의 input 파라미터__ 와 __1개의 반환 값으로__ 이루어져있다.

```
[사용 예제]

BiFunction<Integer, Integer, String> bifunction = (valOne, valTwo) -> String.format("value 1 : %d, value 2 : %d", valOne, valTwo);
String result = bifunction.apply(1, 2); //출력 : 'value 1 : 1, value 2 : 2'
```
