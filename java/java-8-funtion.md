# [TIL] Java 8 Function<T, R>

* Function<T, R> : java 8 Function Type
* 하나의 lambda 펑션을 지원한다.
* 하나의 input, 하나의 output이 존재한다.

```
[사용 예제]

* 직접 사용

Function<String, Integer> convertStrToInt = str -> Integer.parseInt(str);
Integer result = convertStrToInt.apply("1");

* map 사용시

List<String> stringNumbers = List.of("1", "2", "3");
List<Integer> convertedNumbers = stringNumbers.stream().map(convertStrToInt);
```
