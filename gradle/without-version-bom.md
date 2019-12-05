# [TIL] ‘버전’을 직접 입력하지 않아도 임포트되는 dependencies with bom

## ‘버전’을 직접 입력하지 않아도 임포트 되는 dependencies with bom 의 매직

* gradle에서 디펜던시들의 버전을 관리할 때, 특정 디펜던시에 버전을 넣어주지 않아도 디펜던시가 임포트 되는 경우가 있다.
* `Spring cloud`의 경우 `BOM` 버전 템플릿을 제공해주기 때문에, 버전을 입력하지 않아도 boot 버젼에 맞는 해당 `cloud dependency set up`에 맞는 버전을 자동으로 넣어준다.
* 물론 아래와 같은 BOM platform import가 필요하다.
  * implementation platform(SpringBootPlugin.BOM_COORDINATES)
  * implementation platform("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}”)
  * 위에서 `SpringBootPlugin.BOM_COORDINATES` 는 확인해보면 현재 boot version bom 을 설정해주는 것을 확인해 볼 수 있었다.
  * public static final String BOM_COORDINATES = "org.springframework.boot:spring-boot-dependencies:" + SPRING_BOOT_VERSION;
* 즉, platform 에서 boot dependency 도 bom 설정을 가져온다고 볼 수 있다.
* 예전에는 `platform` 이라는 예약어가 없었는데, gradle 5.0 부터 `platform` 이라는 정의가 생겼다.
* 이를 통해서 예전에 dependencyManagement로 설정하지않고 dependencies 에 바로 선언할 수 있게 되었다.
* Grade에서 lombok에 직접적으로 `version`을 추가하지 않아도 dependency에 특정 버전이 import 되는 것을 확인할 수 있었다.
* Lombok 뿐만 아니라, common:lang3, redis.client 도 마찬가지였다.
* 이는 어디서 설정이 되었을까? 하고 살펴보니, 위에서 platform 으로 이식한 bom 중에서 spring boot 내부에 선언되어있는 것을 확인할 수 있었다.
  * https://github.com/spring-projects/spring-boot/blob/master/spring-boot-project/spring-boot-dependencies/pom.xml
* 위의 `spring-boot-dependencies/pom.xml` 링크를 확인해보면, boot version에 따른 세트 버전들이 정의되어있는 것을 확인할 수 있다.
몇가지만 살펴보면 아래와 같다.

```
[sprinjg boot dependencies boot version 2.1.10에 선언되어있는 dependency]

<lombok.version>1.18.10</lombok.version>
<jedis.version>2.9.3</jedis.version>

—
[gradle에서 선언한 dependency]

Dependencies {
  compileOnly "org.projectlombok:lombok”
  implementation 'redis.clients:jedis'
}
```

* Build 된 dependency 확인을 직접 해보면. 정상적으로 해당 version이 mapping 된 것을 알 수 있다.
  * org.projectlombok:lombok:1.18.10
  * redis.clients:jedis:2.9.3
* 이런 경우의 편의성은 개발자가 dependency 들간에 호환성 걱정을 안해도 된다는 것이다.
* 직접 버전을 관리하다보면 어떤 라이브러리는 해당 버전에서 호환이 되지않아 동작을 하지 않을 수 도 있는데, spring 에서 특정 버전이 해당 프레임워크 위에서 동작한다는 것을 정의해준것이기 때문이다.

## gradle 에서 maven 에서 사용되는 pom.xml 을 어떻게 사용하는 것일까?

* 현재 개발하는 프로젝트들은 `gradle` 로 구성이 되어있다.
* 그런데 어떻게 `BOM` 을 이용한 without version 을 하기위해서  maven의 `pom.xml` 에서 버전 정보를 가져와서 mapping 하여 gradle에 주입시키는 것일까?
* 비밀은 `dependency handler`에 있다.
* Dependency handler는 `dependencies` task 를 통해서 트리거되는 영역이다.
* Dependency handler는 gradle 5.0 이상부터 지원되는 `platform` 으로 주입되는 `bom` 정보를 디펜던시 핸들러에서 제공하는 메소드 `enforcedPlatform` 를 통해서 강제로 bom의 버전을 따르도록 하는 것이다.
* 그래서 만약 우리가 어떤 version을 specific하게 수정하더라도, platform 으로 직접 bom을 사용한다면 __bom에서 정의된 버전을 먼저 따른다__

```
EX) 2.9.0 버전을 강제로 사용하도록 하지만, bom에 의해서 2.9.3이 셋팅된다.

 gradle -q dependencies --configuration compileClasspath

+--- redis.clients:jedis:2.9.0 -> 2.9.3
```

### 참고

https://mrhaki.blogspot.com/2019/04/gradle-goodness-use-bill-of-materials.html
