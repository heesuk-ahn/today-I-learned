# Java method parameter `final`가 의미하는 것은?

* 자바에서 method 파라미터에 final 키워드를 사용하는 경우들이 있다.
* 자바는 항상 메서드에 파라미터를 전달하기 전에 파라미터들을 copy 한 후 전달한다.
* 그렇기 때문에 `final` 을 붙인다고 해서 메서드를 콜 할때 특별한 일이 일어나는 것은 아니다.

```

public String getFoo(final input1) {
  // anything...
}
```

* final 을 메서드 파라미터에 붙이는 것에 대한 차이점은 메서드 안에서 해당 변수를 `재할당 (reassigned)` 하지 않도록
하는 것이다.
* 주의할 점은 `final` 을 사용하더라도 object 는 여전히 바뀔 수 있다는 점이다.
