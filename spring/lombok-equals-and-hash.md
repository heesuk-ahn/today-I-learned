# [TIL] Lombok annotation - @EqualsAndHashCode(callSuper = true)

* 빈 생성시에 `equals`와 `hashcode` 메소드를 자동 생성 할 수 있는 lombok 어노테이션

```
@EqualsAndHashCode(callSuper = true)
public class User extends Domain {
  private String username;
  private String password;
}
```

* `callSuper = true` 설정시, `equals`와 `hashcode` 메소드를 생성할 때 부모 필드도 고려할 수 있다.
* 예를 들어서, `equals` 로 두 객체를 비교한다고 해보자.
* `callSuper = true`일 경우에는 두 객체를 비교할 때, 상속받은 부모 클래스의 필드까지 call 하여 비교한다.
