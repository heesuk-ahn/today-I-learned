# Value Based Class

* Value Based Class 는 어떤 타입의 값을 감싸고 있는 컨테이너 클래스이다.
* 이러한 Value Based Class 는 아래와 같은 원칙을 가진다. (Java Documentation)

## Value Base Class 의 원칙

1) final 하고 immutable 하다.
2) equals, hashCode, toString 와 같은 구현은 인스턴스의 상태 또는 다른 객체에 의해서가
아닌 __인스턴스의 상태__ 에서만 계산된다.
3) 식별에 예민한 오퍼레이션에서 사용하지 않는다. 예를 들어서 hash code를 이용한 식별,
synchronization 에서의 사용, 참조 동등성에서의 사용 (==) 등이다.
 이 경우에 사용하면 예기치 않은 결과가 발생할 수 있으므로, 피해야 한다.
4) 참조 동등성 (==) 이 아닌 equals() 만을 기반으로 동등성을 간주할 수 있습니다.
즉, 값으로만 비교를 해야지 객체와 객체의 참조 관계로 비교하는 게 아니라는 것이다.
5) 접근 가능한 생성자가 없지만, 팩토리 메소드를 통해 인스턴스화 된다. 이때 이 인스턴스는
식별하기 위해서 내부적으로 저장을 하지 않은 인스턴스들이 반환된다.
6) 계산 또는 메서드 호출에서 equals() 에 따라 인스턴스 x와y가 같다면, x와 y의 자리를 바꾸어도
동작의 변화는 없이 같은 동작을 해야한다 __(참조 투명성)__

## Value Base Class - optional

* java에서 Optional 클래스는 Value Base Class 이다.
* 위의 원칙들을 대입해서 어떻게 일치하는지 살펴보도록 한다.

* 인스턴스 생성 : 생성자가 아니라, Optional.of() 팩토리 메서드를 통해 생성된다.
* 참조 투명성 :
```
ex)
  var a = Optional.of(1);
  var b = Optional.of(1);

  a.flatMap(valueA -> b.map(valueB -> valueB + valueB)); // Optional.of(2);

  이 결과는 a와 b의 위치를 바꿔도 동일하다.
```
* final 하고 immutable 하기 때문에, 선언되어 생성된 인스턴스의 값을 바꿀 수 없다.
* 인스턴스의 상태에서만 계산 :
```
- 기본적으로 final class 이기 떄문에 다른 객체에서 상속받아서 수정할 수 없다.

public final class Optional<T> {


  public String toString() {
      return this.value != null ? String.format("Optional[%s]", this.value) : "Optional.empty";
  }

  public int hashCode() {
      return Objects.hashCode(this.value);
  }

  public boolean equals(Object obj) {
      if (this == obj) {
          return true;
      } else if (!(obj instanceof Optional)) {
          return false;
      } else {
          Optional<?> other = (Optional)obj;
          return Objects.equals(this.value, other.value);
      }
  }  
}
```
