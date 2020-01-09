# [TIL] gradle에서 라이브러리 버전 비교하는 Task 만들기

* gradle에서 라이브러리 버전에 따라서 assert 로 에러를 출력하고 싶을 수가 있다.
* 이는 프로젝트에서 특정 라이브러리에 대해 버전관리를 할 때 필요한 경우이다.
* 이를테면, 특정 라이브러리 A는 `1.5.0` 버전이상일 경우에 gradle build를 실패시킨다고 생각해보자.
* 이때, 버전을 비교하는 코드는 어떤 식으로 작성할 수 있을까?
* 아래 테스크를 살펴보자.

```
import org.gradle.util.VersionNumber

task checkLibraryVersion {
  def expectedLimitVersion  = '1.5.0'
  def errorMessage = '해당 라이브러리는 버전이 1.5.0 이상일 경우 문제가 생길 수 있습니다'

  doLast {
    configurations.compileClasspath.resolvedConfiguration.resolvedArtifacts.each {
      def getModuleInfo = it.moduleVersion.getId()
      def libraryName = getModuleInfo.getId()
      def actualLibraryVersion = getModuleInfo.version

      if (libraryName == 'libraryA') {
        assert VersionNumber.parse(actualLibraryVersion)  <= VersionNumber.parse(expectedLimitVersion) : errorMessage
      }
    }
  }
}
compileJava.dependsOn(checkLibraryVersion)
```

* 위의 테스크를 하나씩 살펴보자.
* 먼저 `configurations`는 gradle 에서 제공하는 설정들의 정보이다.
* compile, implementation, testImplementation 등의 configuration이 존재한다.
* resolvedConfiguration는 해당 configuration에 설정된 값을 실제로 network에서 download 받도록 한다.
* 이후 each 메소드를 통해, 각 라이브러리의 정보를 읽어들인다.
* 읽어들인 정보중에 `libraryA`가 존재한다면, 이때 `VersionNumber.parse` 를 통해서 버전 넘버로 변경한다.
* `VersionNumber`는 library 간 버전 비교를 위해 gradle에서 제공하는 라이브러리이다.
* 이를 통하면 쉽게 버전을 비교할 수 있다.
* task의 밑에 `compileJava.dependsOn(checkLibraryVersion)`는 테스크간 의존성을 설정한 것이다.
* `compileJava`는 이미 존재하는 task 인데, compileClasspath에 접근하기 위해서는 `compileJava` 이전에
실행이 되어야만 resolvedConfiguration 에 접근할 수 잇다.
* 이를 위해 task 의존성을 통해 __compileJava보다 먼저 checkLibraryVersion가 실행 되도록 하였다.__
