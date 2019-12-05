# [TIL] Groovy Single vs Double quote

* Groovy 에서 Single과 Double quote에 차이점에 대해서 알아보자
* Single : Single quote 는 java의 String 처럼 일반적인 스트링을 의미한다.
* Double : Double quote는 스트링이긴 하지만, 안에 표현식을 넣어서 실행할 수 있는 GString 이다.
* 예를 들어서 아래를 보면 차이가 명확하다.

```
println("${40 + 5}”) // 45를 출력

println('${40 + 5}’) // ${40 + 5}를 출력

```

### 참고

* https://stackoverflow.com/questions/37464887/vs-vs-in-groovy-when-to-use-what
