# [TIL] java 8 람다 캡처링

* java 8에서 람다는 lambda body 밖에 선언된 local 변수를 capture 할 수 있다.
* 간단한 예로 로컬 변수 람다 캡처링이 어떤 것을 의미하는지 보자.

## Local Variable Capture

```
[Interface]

public interface MyFactory {
    public String create(char[] chars);
}
```

```
[Lambda expression]

MyFactory myFactory = (chars) -> {
    return new String(chars);
};
```

* 위의 람다 표현은 MyFactory 인터페이스를 구현한 람다 표현이다.
* 위의 표현에서는 lambda 는 lambda의 input 으로 들어온 `내부 변수`만 참조하고 있다.
* 이제 로컬변수를 통해서 람다가 순수하지 않은 함수로써 동작하게 하기위해서 람다 외부의 로컬 변수를
참조하도록 해보자.

```
[로컬 변수를 참조하는 람다 표현]

String myString = "Test";

MyFactory myFactory = (chars) -> {
    return myString + ":" + new String(chars);
};
```

* 위의 예제를 살펴보면, 이제는 lambda 가 `myString` 로컬 변수를 참조하는 것을 알 수 있다.
* 자바에서는 이러한 참조가 가능하도록 허용한다.
* 단, 조건이 있다. `effectively final` 이여야 한다.
* 이것이 의미하는 것은 __변수가 선언한 이후에 변하지 않는 불변의 성질__ 을 가지고 있음을 의미한다.
* 만약 해당 변수의 값이 나중에 변하게 된다면, 컴파일 단계에서 이를 감지하여 컴파일 에러가 발생하게 된다.

## Instance Variable Capture

* 캡처링 람다는 __로컬 변수 뿐만 아니라, 인스턴스 변수도 캡처할 수 있다.__

```
public class EventConsumerImpl {

    private String name = "MyConsumer";

    public void attach(MyEventProducer eventProducer){
        eventProducer.listen(e -> {
            System.out.println(this.name);
        });
    }
}
```

* 위에서 주목할 점은 람다 바디 내부의 `this.name` 이다.
* `this.name` 은 캡슐화된 `EventConsumerImpl` 의 변수를 참조한다.
* 위의 멤버 변수는 해당 람다에서 __캡처된 이후에 변경될 수 있다.__
* 그리고 변경된 변수는 runtime에 반영된다.
* (?) 여기서 드는 의문은 그럼 thread safe 하지 않을 수 있다는 의미인지.
* `this` 는 의미론적으로 살펴보면, 일반적으로 inteface의 구현체인 현재 인스턴스 객체를 가리키는데, lambda 의 경우에 this는 조금 더
다른 의미를 가진다.
* lambda의 경우에는 instannce 변수가 없으므로, 항상 캡슐화된 객체의 인스턴스 변수를 가리킨다.
* (!) 위의 `instance lambda capturing` 패턴은 우아하지 않다. 단지 예시를 보여주기 위함임을 인지하자.

## Static Variable Capture

* 자바의 람다표현은 static variable 도 캡쳐링 가능하다.
* 이것은 사실 놀라운 것이 아니다. static 변수는 자바 어플리케이션 어디에서라도 접근할 수 있다.
* static variable capture 에 관한 예제를 살펴보자.

```
public class EventConsumerImpl {
    private static String someStaticVar = "Some text";

    public void attach(MyEventProducer eventProducer){
        eventProducer.listen(e -> {
            System.out.println(someStaticVar);
        });
    }
}
```

* 위의 static 변수는 람다가 capture 한 이후에 변경은 허용한다.

## 성능 이슈?

* 그러면 capturing 람다의 구체적인 단점은 무엇일까?
* 먼저, 기존의 lambda 는 __람다식이 재사용 된다는 장점__ 이 있다.
* 즉, 메모리적 이점이 있다.
* 하지만, 캡처링 람다의 경우에는 외부 변수를 `capture` 하여서 다시 람다식을 만들어야 하므로,
이러한 메모리적 측면에서는 성능의 저하를 일으킬 수 있다.

## 결론

* 물론 외부 변수를 참조하지 않고 순수 람다 펑션으로만 처리하면 베스트지만,  그렇지 않은 경우도 있을 수 있다.
* 그래서 캡처링 람다를 사용하지 않아도 되는 경우라면 캡처링 람다를 최대한 지양하지만,  
사용할 수 밖에 없다면 사용하는 것 또한 잘못된 것은 아니다.


### 참고

http://tutorials.jenkov.com/java/lambda-expressions.html#local-variable-capture
