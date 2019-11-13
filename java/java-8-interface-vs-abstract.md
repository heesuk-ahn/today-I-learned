# JAVA 8에서 Interface와 Abstract 차이

* JAVA 8부터는 `interface` 도 메서드를 직접 구현할 수 있게 되었다.
* 이를 default method 라고 하는데, 기존에 `Abstract class` 와 차이점이 모호해져서 정리를 한다.
  * 개인적으로 interface에도 정의가 가능해 짐으로써, Scala의 `trait` 과 비슷하다는 생각이 들었다.
  * interface를 구현하는 구현 클래스에서 모두 같은 함수를 구현해야할 때는 이 또한 중복이라고 볼 수 있는데,
  이럴 때 interface 에서도 구현이 가능했으면 했는데, JAVA 8부터는 이것이 `default method` 를 통해 가능해졌다.
* JAVA 8에서 `Interface` 와 `Abstract Class` 차이
  * 1) abstract class는 인스턴스 객체를 생성하기 위한 클래스이기 때문에 생성자를 정의할 수 있지만, interface는 그렇지 못하다.
  * 2) abstract class는 멤버 변수가 mutable 할 수 있지만, interface는 멤버변수는 immutable 해야한다.
  즉, abstract class는 `non-final`, `non-static` 한 필드를 사용할 수 있고, interface는 `final` 한 필드만 사용할 수 있다.
* 그렇다면 언제 `interface` 를 사용하고 언제 `abstract class` 를 사용하는가?
  * abstract class 사용
    * 여러개의 가까운 클래스들 `is-a (부모 자식) 관계` 사이에 동일한 코드를 공유해서 사용하고 싶을 때.
    * non-static, non-final 한 필드를 선언하고 싶을 때. 즉, 객체들의 상태를 메소드에서 접근하고 수정할 수 있다.
      * 그러나, 멤버변수는 `final` 하게 쓰는 것이 좋다. 변화는 항상 문제를 일으킬 수 있기 때문이다.
  * interface 사용  
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

### 참고
  * https://yaboong.github.io/java/2018/09/25/interface-vs-abstract-in-java8/ [자바 8에서 인터페이스와 추상클래스의 차이]
  * http://happinessoncode.com/2017/04/19/java8-changes-in-interface/ [java8 인터페이스의 변화]
