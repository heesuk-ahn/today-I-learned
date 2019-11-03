* 빌드 스크립트를 위한 의존 라이브러리
  * 빌드 스크립트도 코드이기 때문에 외부 라이브러리를 사용할 수 있다. 이를 위해 buildscript 메서드를 통해 의존성을 선언할 수 있다.
      ```
       buildscript {
           repositories {
               mavenCentral()
           }
           dependencies {
               "classpath"(group = "commons-codec", name = "commons-codec", version = "1.2")

           }
       }
       ```
       DSL로 선언된 코드로 buildscript에 전달된 코드 블록은 ScriptHandler인터페이스를 구현하고 있다.
       이 코드는 추가할 외부 라이브러리를 buildscript의 classpath에 선언한다.
       이제 아래와 같이 buildscript의 classpath에 포함된 외부 라이브러리를 참조할 수 있다.
      ```
       import org.apache.commons.codec.binary.Base64

       buildscript {
         repositories {
             mavenCentral()
         }
         dependencies {
             "classpath"(group = "commons-codec", name = "commons-codec", version = "1.2")
         }
       }

       tasks.register("encode") {
         doLast {
             val encodedString = Base64().encode("hello world\n".toByteArray())
             println(String(encodedString))
         }
       }
       ```
  * 멀티 프로젝트로 구성한 경우 하위 프로젝트는 상위 프로젝트의 buildscript에서 포함한 의존 라이브러리들을 모두 사용할 수 있다.
  * 보통 `buildscript`의 의존성은 `gradle`의 `Plugin`을 선언하는 경우가 많다.
  * 모든 프로젝트는 선언하지 않아도 기본으로 `buildEnvironment` task를 갖고 있다. 이 Task를 실행하면 `buildscript`의 classpath 의존성을
   확인할 수 있다.
