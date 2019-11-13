# JAVA 8에서 Interface와 Abstract 차이

* JAVA 8부터는 `interface` 도 메서드를 직접 구현할 수 있게 되었다.
* 이를 default method 라고 하는데, 기존에 `Abstract class` 와 차이점이 모호해져서 정리를 한다.
  * 개인적으로 interface에도 정의가 가능해 짐으로써, Scala의 `trait` 과 비슷하다는 생각이 들었다.
  * interface를 구현하는 구현 클래스에서 모두 같은 함수를 구현해야할 때는 이 또한 중복이라고 볼 수 있는데,
  이럴 때 interface 에서도 구현이 가능했으면 했는데, JAVA 8부터는 이것이 `default method` 를 통해 가능해졌다.
* JAVA 8에서 `Interface` 와 `Abstract Class` 차이
  * 1) abstract class는 인스턴스 객체를 생성하기 위한 클래스이기 때문에 생성자를 정의할 수 있지만, interface는 그렇지 못하다.
  * 2) abstract class는 멤버 변수가 mutable 할 수 있지만, interface는 멤버변수는 immutable 해야한다.
  즉, abstract class는 `non-final`, `non-static` 한 필드를 사용할 수 있고, interface는 `static`, `final` 한 필드만 사용할 수 있다. (상수 필드)
* 그렇다면 언제 `interface` 를 사용하고 언제 `abstract class` 를 사용하는가?
  * abstract class 사용
    * 여러개의 가까운 클래스들 `is-a (부모 자식) 관계` 사이에 동일한 코드를 공유해서 사용하고 싶을 때.
      * 즉, 의미상으로 비슷한 클래스들 끼리 상위에 클래스들고 __공통적인 추상성__ 을 뽑아서 계층 구조를 만들고 상속의 이점을 이용하고 싶을 때 사용
      * 예를 들어 `동물 <- 고양이과 <- 사자, 고양이` 를 보면 하위 클래스로 내려갈 수록 구체적이 되고 상위 클래스로 갈 수록 추상적이 된다.
    * non-static, non-final 한 필드를 선언하고 싶을 때. 즉, 객체들의 상태를 메소드에서 접근하고 수정할 수 있다.
      * 그러나, 개인적인 견해로는 멤버변수는 `final` 하게 쓰는 것이 좋다. 변화는 항상 문제를 일으킬 수 있기 때문이다.
  * interface 사용  
    * interface 는 `다중 상속`이 필요해서 만들어졌다.
    * 자바에서는 `다중 상속`을 좋아하지 않았다. `다중 상속` 하게 되는 클래스는 상속의 의미가 옅어지기 때문이다.
    * 하지만, __하나의 상속만으로 클래스의 구현을 강제 할 수 에 없기 때문 불편한 점이 생길 수 밖에 없었다.__
    * 그래서 인터페이스가 만들어졌고, 인터페이스는 한 개의 클래스에 여러가지 구현을 `강제` 함으로써 위의 문제점을 해결했다.
    * 즉, 인터페이스는 __구현의 강제__ 를 맡고 있다.
    * A 인터페이스를 구현한 클래스 B, C가 있을 때, B와 C는 전혀 관련이 없어도 상관 없다. (물론 관련이 있어도 사용할 수 있다)
    * 반면, A 클래스를 상속받은 B, C 클래스가 있을 때, 이 클래스들은 관련이 높다.
    * 즉 인터페이스는 클래스 간 `has-a (포함관계)`일 때, 사용하면 좋다.
    * 발생할 수 있는 문제 `Diamond Problem`
      * JAVA 8 이전에는 `interface` 를 다중 상속한다 하더라도 구현은 구현 클래스에서 하기 때문에
      문제는 없었다.
      * 하지만, JAVA 8 부터는 `interface` 가 동일한 method를 지닌 interface 를 다중 상속할 경우,
      default method로 구현이 이루어질 수 있기 때문에 `Diamond Problem` 이 발생할 수 있다.
      * `Diamond Problem` 이란 동일한 메소드의 구현으로 인한 충돌을 의미한다.
      ```
      public interface Person {
        String getName();
        default int getId() { return 0; }
      }

      public interface Identified {
        default int getId() { return Math.abs(hashCode()); }
      }

      public class Employee implements Person, Identified {
        ...
      }
      ```
      * 위 상황에서 `Employee` class가 `getId()` 를 Override 하지 않으면 컴파일 에러가 발생한다.
        * `Employee inherits unrelated defaults for getId() from types Person and Identified`
      * 그렇다면 하나의 인터페이스가 `default` 메서드를 구현하지 않으면 위의 컴파일 에러는 해결될까?
      * 그렇지않다. 그대로 컴파일 에러는 발생한다.
    * 해결책은?
      * 구현 클래스에서 override 하여 사용한다
      * 이때 특정 interface 의 메소드를 선택하여 사용도 가능하다.
      ```
      public class Employee implements Person, Identified {
        @Override
        public int getId() {
          // Person의 기본 메서드를 사용하고 싶은 경우.
          // Identified의 기본 메서드를 사용하려면 Identified.super.getId()를 반환한다.
          return Person.super.getId();
        }
      }
      ```
      * Interface간 상속

      ```
      public interface Person extends Identified {
        String getName();
        default int getId() { return 0; }
      }

      public interface Identified {
        default int getId() { return Math.abs(hashCode()); }
      }
      ```
      * 하지만 위와 같이 사용할 경우, `Employee`는 결국 `Person`의 default method를 쓸 수 없다.
* 상속은 `확장`이고, implement는 구현의 강제 (그 기능을 가지게 하는 것)이다.

## 결론

* java 8에서 default method가 생김으로써, 이전에 interface 를 구현하는 클래스에서 반복적으로 구현해야 했던
중복된 코드들을 위해 abstract class를 써줄 필요가 없어졌다.
* abstract class는 'is-a(부모-자식)' 간에 밀접한 관계일 때 사용하고, interface는 'has-a' 관계에 사용하자.
* 하나 드는 의문은 상속의 관계가 구현의 강제 보다 더 큰 이득을 갖는 경우가 어떤 점일지 좀 더 고민해볼 필요가 있다는 것이다.

### 참고
  * https://yaboong.github.io/java/2018/09/25/interface-vs-abstract-in-java8/ [자바 8에서 인터페이스와 추상클래스의 차이]
  * http://happinessoncode.com/2017/04/19/java8-changes-in-interface/ [java8 인터페이스의 변화]
  * https://dingue.tistory.com/4 [추상클래스와 인터페이스]
