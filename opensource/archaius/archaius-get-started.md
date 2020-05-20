# Archaius Get Started!

* Archaius 를 시작해보자.
* dependency를 추가하여 시작할 수 있다.

```
<dependency>
  <groupId>com.netflix.archaius</groupId>
  <artifactId>archaius-core</artifactId>
  <version>0.6.0</version>
 </dependency>
```

## Using a local file as the configuration source

* local configuration 을 사용하여 애플리케이션에 dynamic property 를 제공해서
Archaius 를 즉시 사용할 수 있는 두가지 방법이 있다.
  * 1) 기본적으로, Archaius 는 응용프로그램의 클래스 경로에서 `config.properties` 라는 파일을 찾고
  해당 내용을 configuration properties 로 읽는다. 파일은 또한 jar 파일의 루트 디렉토리에 있을 수 도 있다. (?)
  * 2) 또한, system property 를 이용해서 정의할 수도 있다. `archaius.configurationSource.additionalUrls`
  에 local file의 path 를 직접 정의할 수 있다. 예를 들어서 아래와 같은 스크립트를 어플리케이션이 시작할 때 적용할 수 있다.

  ```
  -Darchaius.configurationSource.additionalUrls=file:///apps/myapp/application.properties
  ```

* `lock.waitTime` 이라는 프로퍼티가 configuration file 내부에 있고 이를 동적으로 변경할 수 있다고
가정해보자.
* Archaius 에서 이 propery 를 dynamic property 로 활용하기 위한 코드는 아래와 같다.

```
// 값의 타입이 `long`이고 기본 값이 1000을 사용하는 프로퍼티를 생성하자.
// property 가 정의되어 있지 않다면 기본값을 사용한다.
DynamicLongProperty timeToWait =
    DynamicPropertyFactory.getInstance().getLongProperty("lock.waitTime", 1000);
// ...
ReentrantLock lock = ...;
// ...
lock.tryLock(timeToWait.get(), TimeUnit.MILLISECONDS);
// 동적 프로퍼티인 `timeToWait` 는 `get()` 함수를 호출할 때, 최신 값을 반환하게 된다.
```

* 위의 코드에서 두가지 주목할 점이 있다.
  * 1) `timeToWat` 프로퍼티는 long value 에 바인드 된 값이다. 문자열을 long으로 parse 하는데
  필요한 코드가 없다.
  * 2) `lock.tryLock()` 호출에서 미리 정의된 long 파라미터를 사용하는 대신 timeToWait.get() 을 사용하여
  런타임시 변경될 수 있는 값을 가져왔다.
* 이제 `lock.waitTime` 값을 configuration file 에서 변경하면, 1분 안에는 어플리케이션에 적용이
될 것이다.
* Archaius 는 default 설정으로 매 분마다 변경된 config 를 읽어온다.

## 여러 URL을 co Using multiple URLs as the configuration sources

* Archaius 는 먼저 `config.properties` 를 읽고 그 다음에 Url 정의된 곳에서
시스템 프로퍼티들을 읽어들인다.
* 만약에 두 URL 이 동일한 프로퍼티를 가지고 있을 경우에, 적용이 되는 프로퍼티는 나중 순서의
URL 의 것이 적용된다.
* 만약 default configuration file 이나 URL로 정의된 Sysyem property 를 읽을 수 없으면, 치명적인
오류는 없지만, `DynamicPropertyFactory` 에 configuration source 를 등록하지 않으면,
Properties 의 실제 값을 얻을 수는 없다.
* 예를 들어서, 어플리케이션의 기본적인 프로퍼티가 `config.properties` 파일에 있고, 또 다른 Properties
은 URL 을 통해서 어플리케이션을 배포할 수 있다.

```
lock.waitTime=200
```

* 위와 같이 `config.properties` 가 정의되어있다고 하자.
* 그리고 아래와 같이 URL 로 properties 를 override 한다고 하자.

```
-Darchaius.configurationSource.additionalUrls=http://myserver/properties
```

* 또한, 위에서 지정한 `http://myserver/properties` 는 아래와 같은 설정을 지니고 있다고 하자.

```
lock.waitTime=500
```

* 위와 같이 설정되어있을 경우에, 최종적으로 어플리케이션에 적용되는 property 는 200이 아니라 500이 적용된다.
* 이러한 use case 는 특히나 cluster 환경에서 어플리케이션을 운용하는데에 도움이 될 것이다.
* 추가적인 configuration URL 을 중앙 집권화된 HTTP URL 을 이용해서 정의하면, 클러스터의 각 서버에서
동일한 구성 파일을 변경하는 번거로움을 피할 수 있다.

## Change the default setting

* 아래 리스트는 바꿀 수 있는 system properties 에 대한 설정이다.
  * `archaius.configurationSource.defaultFileName`
    * classpath 내의 기본적인 configuration file 이름
    * 기본 값 : config.properties
  * `archaius.fixedDelayPollingScheduler.initialDelayMills`
    * configuration source 로 부터 읽어들이는 초기 지연 시간 (ms)
    * 기본 값 : 30000 (ms)
  * `archaius.fixedDelayPollingScheduler.delayMills`
    * fixed delay between two reads of the configuration URLs
    * 기본 값: 60000 (ms)

## Logging

* Archaius 는 로깅을 위해서 SLF4J 를 사용한다.    



### 참고 링크

* https://github.com/Netflix/archaius/wiki/Getting-Started
