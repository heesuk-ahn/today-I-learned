# Gradle

* 자바 프로젝트 빌드를 도와주는 빌드 툴이다. 다양한 플러그인들을 제공해주어 빌드를 쉽게 도와준다.
* `gradle 스크립트`는 `groovy`를 사용해서 만든 `DSL` (Domain-specific language) 이다.
  * `gradle 5.0`이 되면서 `kotlin 빌드 스크립트`도 지원하기 시작함.
* 모든 gradle 스크립트는 두 가지 개념 (projects와 tasks)로 구성
 * 모든 gradle 빌드는 하나 이상의 project 로 구성
 * 각 project는 하나 이상의 task들로 구성
   * task는 어떤 클래스를 컴파일 하거나 JAR을 생성하거나 javadoc을 만드는 작업들을 의미
 * `task`에 대해서 살펴보자.
   * `task`는 아래와 같이 설정할 수 있다.
      ```
       task 테스크명 {
           ... do something ...     
       }
       ```
   * 간단한 헬로우 월드 테스크를 생성해보자. build.gradle에 작성 후, gradle hello로 테스크를 직접 호출할 수 있다.
      ```
       task hello {
           println 'HELLO WORLD!'
       }
       ```
   * `task` 내부에 실행 가능한 코드 블록을 `action`이라고 한다.
   * `task`는 내부에서 일어나는 액션들은 순차적으로 실행된다. 이러한 액션들 중 `doFirst`, `doLast` 등을 이용하면 실행 순서를
     제어할 수 있다. 여기서 `doFirst`와 `doLast`는 `action`이다.
     ```
       task hello {
         doLast {
           println("last action!")
         }
         doFirst {
           println("first action!)
         }
       }
     ```
     위의 `task`를 실행하면, doFirst 코드가 더 나중에 선언되어있지만, doFirst가 최초로 실행되고 그 이후에 doLast가 실행된다.
   * `task`는 매개변수 또한 전달할 수 있다. 단순히 작업 처리 중 변수를 사용하면 된다.
     이 경우, `gradle msg -Pv=값` 처럼 `-P` 파라미터를 붙여서 매개 변수를 전달한다.
      ```
       task msg {
           println("value: " + v)
       }
       ```
   * `task`에서 다른 `task`를 호출할 때에는 `task`들이 모여있는 `tasks`를 호출해야한다.
      ```
       task a {...}
       task b {...}
       ```
     위와 같은 task가 있다고 생각해보자. task b에서 task a를 사용한다면 우리는 아래와 같이 생각할 수 있을 것이다.
     ```
       b()
     ```
     하지만 이렇게 작동을 하지 않는다. `tasks`안에 메소드를 호출하여 수행해야 한다. `tasks`는 모든 `task`를 관리하기 때문에,
     `tasks` 객체 안에서 `a`와 `b` task를 호출하여야 한다.
     ```
       tasks.a.execute()
       tasks.b.execute()
     ```
   * 어떤 `task`를 수행할 때, 다른 작업 수행이 필수적인 경우도 있다. 이러한 경우에는 `dependsOn` 이라는 기능을 사용할 수 있다.
      ```
         task 테스크명 (dependsOn : '테스크') {
           dependsOn : '테스크'
           ... do something ...
         }    
       ```
     또한 다음과 같은 작성도 가능하다.
      ```
           task 테스크명 {
               dependsOn: '테스크'
               ... do something ...
           }
       ```
     이와 같이 기술해 두면 작업이 호출될 때, 먼저 dependsOn에 지정된 작업을 수행하고 그것이 끝난 후에 테스크의 본 처리를 수행한다.
     여러 테스크를 지정해야 하는 경우는 테스크명을 배열로 지정한다. ['a', 'b', 'c']와 같은 식이다.
     ```
       task hello(dependsOn:['a', 'b', 'c']) {
           ... do something ...
       }
     ```
     위와 같이 선언하면 `'a' 실행 -> 'b' 실행 -> 'c' 실행 -> 'hello' 실행`이 순차적으로 이루어진다.
