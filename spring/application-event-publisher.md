# [TIL] Spring ApplicationEventPublisher

* 메서드를 통해 스프링 기반 어플리케이션에 이벤트를 발생시킬 수 있다.
* POJO 스타일로 Event 객체를 정의할 수 있다.
* 원래는 ApplicationEvent 를 상속받아 만들어야 했지만, Spring 4.2 부터는 클래스를 상속받지 않아도 이벤트로 사용할 수 있다.

```
@Getter
@Setter
Public class FooEvent {

	private String eventName;
	private String eventData;

}
```

* 위의 `FooEvent` 를 수신하는 방법은 스프링 빈으로 등록된 클래스의 메소드에 @EventListner 를 등록하여 이벤트를 받는 메서드를 작성하면 된다.

```
@Component
public class FooEventHandler {

	@EventListner
	public void handle(FooEvent fooEvent) {
		System.out.println(“GET EVENT ID : “ + fooEvent.getName);
	}

}
```

* 위의 앱을 실행할 경우 `eventName` 이 실행될 것이다.

### 참고

* https://engkimbs.tistory.com/718
