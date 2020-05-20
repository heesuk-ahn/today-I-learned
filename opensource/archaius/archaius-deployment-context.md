# Archaius Deployment context

* `DeploymentContext` 는 환경에 따른 properties 들의 셋트를 정의한다.
* 이러한 properties의 값들은 optional 이고, 어떤 문자열이 될 수도 있다.
* 예를 들어, environment 는 deployment context 의 하나의 property 인데,
이 environment가 될 수 있는 것은 이를 테면 "prod", "test", "stg" 등.. 어떤 String
값으로 지정할 수 있다. 물론 환경에 대한 정의는 각 회사마다 다를 것이다.

### Using the DeploymentContext and load cascaded configuration files

* Deployment context properties 는 많은 방법으로 사용될 수 있다.
* Archaius 는 deploymentcontext 를 계단식의 configuration files 로 로드한다.
* 계단식으로 로드한다는게 무슨 말인가. 아래 use case 를 보자.
  * 어플리케이션은 기본적인 properties 셋트를 정의한다.
  * 런타임에, 이러한 properties 들은 deployment context 의 구체적인 값들로 오버라이드 되어야 한다.
* 예를 들어서, 어플리케이션은 database.properties 라는 configuration file을 가지고 있다.  
* 이 configuration file은 기본적으로 database properties 들의 셋트를 포함한다.
* 이것은 아래와 같은 두가지 properties file 로 나뉜다.
  * `database-prod.properties` : 프러덕션 환경에 적용될 프로퍼티들을 가지고 있다.
  * `database-test.properties` : test 환경에 적용될 프로퍼티들을 가지고 있다.
* 위의 파일 중 하나를 로드하기위해서, `ConfigurationManager` 는 `DeploymentContext` 객체
세트를 참조하여, `DeploymentContext.getDeploymentEnvironment()` 메서드를 통해서 어떤 환경이
추가될지 판단하고, 그 환경 값에 따라서 `dartabase-${environment}` 파일을 로드하여 프로퍼티 값들을
적용한다.
* `DeploymentContext`는 Archaius 에서 pluggable하다.
* `ConfigurationManager`는 주어진 system property `archaius.default.deploymentContext.class`
를 이용해서 `DeploymentContext` 구현을 초기화한다.
* 만약에, 위 설정이 정의되어 있지 않다면, Archaius 는 기본적으로 `DeploymentContext` 인스턴스를 미리 정의되어
있는 properties 의 셋트의 반환 값으로 설정한다.
  * archaius.deployment.environment
  * archaius.deployment.region
  * archaius.deployment.datacenter
  * archaius.deployment.applicationId
  * archaius.deployment.serverId
  * archaius.deployment.stack
  * ...
* 이러한 properties 값들은 system properties 의 set 로 설정될 수 있다.
* Archaius의 또 다른 feature 는 파일을 계단식으로 로드할 수 있다는 것이다.
* 예를들어서, 만약에 EC2 region 으로 구체적인 properites 파일인 `database-us-east-1.properties`
를 default database.proeprties 로 덮어씌울 수도 있다.
* 그렇게 하기 위해서, `database.properties` file 에 끝에다가 이러한 property 를 정의할 수 있다.

```
@next=database-${@region}.properties
```  

* `@next` 는 next file 을 load 하기 위한 특별한 property 다.
* `@region`은 자동으로 설정되는 속성이며, 런타임에 `DeploymentContext.getDeploymentRegion()`
으로 부터 반환된 값을 통해 확장된다.
* 값을 property 로 가져오려면, DeploymentContext.ContextKey 열거형에 `@`을 추가하면 된다.

```
// this will return the same value as DepolymentContext.getValue(ContextKey.environment)
// or DeploymentContext.getDeploymentEnvironment()
ConfigurationManager.getConfigInstance().getString("@environment");
```

* 별도의 설치나 구성없이, 계단식 configuration files 은 오직 static files 를 통해서만 지원된다.
* 어플리케이션은 Archaius의 기본 동작에 의존하는 대신 명시적으로 로딩을 호출해야 한다.
* 기본적으로, Archaius 는 poller 를 사용하여 default config file 인 `config.properites`
를 폴링하고, `config.properties` 를 동적인 소스로 취급한다.
* 동적 소스를 위해서, Deployment context 그리고 @next property 는 인식되지 않는다.
* 만약에 `config.properties` 를 계단식으로 구성하기를 원한다면, 어플리케이션을 초기화 할 때,
`ConfigurationManager.loadCascadedPropertiesFromResources(“config”)` 를
호출해야한다.


### 참고 링크

* https://github.com/Netflix/archaius/wiki/Deployment-context
