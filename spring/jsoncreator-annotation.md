# @JsonCreator 어노테이션

* Jackson이 json을 deserialize해서 객체로 만들기 위해서는 객체를 생성하고 필드들을 채울 수 있어야 한다. 별도의 설정이 없으면 기본적으로 다음과 같이 동작한다
  * 생성 : 기본 생성자를 사용
  * 채우기 : field가 public 이면 직접 assignment
  * Private 이면 setter 사용
* 객체를 encapsulation하는 것은 기본에 가까운 권장 습관이므로 무조건 setter를 사용한다고 보면 된다.
* 근데 만약 deserialize 한 후에 해당 객체가 immutable하기를 원한다면? setter가 없어야 한다.
* 이때 기본 생성자 + setter 조합을 대신해 객체를 생성할 수 있도록 해주는 것이 @JsonCreator 어노테이션이다.
* 이 어노테이션을 생성자나 팩토리 메소드 위에 붙이면 jackson이 해당 함수를 통해 객체를 생성하고 필드를 생성과 동시에 채운다. 이렇게 생성자나 팩토리 메소드를 통해 필드 주입까지 끝내버리면 setter 함수가 필요 없게 된다.
* __  jackson을 통해 deserialze한 immutable한 객체를 얻을 수 있는 것이다. __

```
@Getter
public final class BookDTO {

  private final String name;
  private final String author;

  @JsonCreator
  public BookDTO(String name, String author) {
    this.name = name;
    this.author = author;
  }

}
```
