# 스칼라에서 공변성이란?

* 스칼라에 타입 시스템은 __다형성 뿐 만 아니라, 클래스의 계층관계도 처리해야한다.__
* 이때 클래스의 계층은 `상/하위` 타입 관계를 만든다.
* 공변성은 타입에 대한 __상/하위 클래스 계층을 표현할 때 쓰는 것으로, 주로 +T라는 형태로 쓰인다.__
* 이 타입이 의미하는 것은 해당 __T라는 클래스의 하위클래스가 대체될 수 있다는 것을 의미한다.__
* 스칼라의 `타입시스템`에서는 `다형성`을 위해서 __클래스의 상/하위 계층 클래스 또한 포함시키기 위하여__
`공변성`이라는 개념이 있다.

```
[공변성 정의]

|의미 | 스칼라 표기|
________________________________
|C[T’]는 C[T]의 하위 클래스이다.|[+T]|
```

```
[공변성 코드 예제]

scala> class Covariant[+A]
defined class Covariant

scala> val cv: Covariant[AnyRef] = new Covariant[String]
cv: Covariant[AnyRef] = Covariant@4035acf6

scala> val cv: Covariant[String] = new Covariant[AnyRef]
<console>:6: error: type mismatch;
 found   : Covariant[AnyRef]
 required: Covariant[String]
       val cv: Covariant[String] = new Covariant[AnyRef]
```

* 위의 코드를 보면 더 잘 이해할 수 있을 것이다.
* `+A`는 공변성으로, A를 포함하여 하위 클래스 타입도 해당 클래스의 타입으로 선언될 수 있다는 것을
의미한다.
* `AnyRef` 는 java에서 Object 와 같은 Root 타입 객체로 이해하면 된다.
* `AnyRef`의 하위 타입인 `String`은 `AnyRef` 대신 선언될 수 있다.
* 하지만, 반대로 `String`은 `AnyRef`의 하위 타입이므로, AnyRef 가 대치할 수 없다.
* 공변성과 관련이 있는 원칙은 `리스코프 치환의 원칙`이다.
* 리스코프 치환의 원칙은 __어떤 타입 T에 대해 T`이 하위 타입이면, T`은 T를 대치할 수 있는가?__ 하는 문제이다.
* 이는 상위 타입이 쓰이는 그 어떤 곳에서도 언제나 하위 타입의 인스턴스를 넣어도 이상 없이 동작해야한다는 것을 의미한다.
* __이를 위해 하위 타입은 상위 타입의 인터페이스를 모두 지원하고, 상위 타입에서 가지고 있는 가정을 어겨서는 안된다.__
