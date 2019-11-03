* Project API
  * `gradle`의 `buildscript`는 하나의 `Project`에 대응한다고 보면 된다.
  * `buildscript`는 `Project 객체`를 구성하는 스크립트
  * `Groovy`가 되었든 `Kotlin`이 되었든 핵심은 `buildscript`에서 `Gradle`이 제공하는 `Project API`를 통해서
   프로젝트의 `빌드 설정`을 구성하는 것
  * 따라서 `buildscript`에서 사용하는 메서드나 속성 중 `명시적으로 정의하지 않은 것`은 모두 `Project`에 위임하는 것이다.
  * code를 통해서 `Project` 객체를 살펴보자.
      ```
         println(name)
         println(project.name

         # gradle-study
         # gradle-study
       ```
     위 두 줄의 코드는 모두 동일한 `Project` 객체의 name 속성을 출력한다. 첫 줄은 `Project`의 `name` 속성을 직접 참조한 것이고,
     두 번째 줄은 `Project`의 객체를 통해서 `name`을 참조한 것이다.
  * 자주 사용되는 `Project`의 속성은 아래와 같다.
      * name : 프로젝트의 폴더명
      * path : 프로젝트 FQN
      * description : 프로젝트 설명
      * projectDir : `buildscript` 위치
      * buildDir : 빌드 디렉토리 위치 projectDir/build
      * group : 지정하지 않으면 empty str
      * version : unspecified
