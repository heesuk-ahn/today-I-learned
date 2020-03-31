# Spirng Cloud 환경 변수 관리를 위한 Config, Consul, Archaius 간단히 살펴보기

* 환경 변수를 특정 인스턴스에 종속시키는게 아니라, 공통적으로 매니징 하기 위해서 Spring cloud 에서는
`config`, `consul`, `archaius` 를 제공한다.
* 공통점이라면, __환경 설정 정보를 외부에 보관하고 변경 내용을 동적으로 적용한다__ 는 것이다.

## 각각에 대해서 간단하게 살펴보자.

* Spring Cloud Config
  * Spring cloud
  * Git 저장소를 지원하는 중앙 집중식
  * 수동으로 각 인스턴스에 API를 호출하여 Refresh 하거나, Spring Cloud Bus 를 통해 변경사항을 전파
* Archaius
  * Spring Cloud Netflix
  * 하나 이상의 Configuration Source 를 지원
* Consul
  * 클라우드 환경에서 서비스를 연결 (Connect), 보안 (Secure) 및 구성 (Configure) 하는 분산 Service Mesh
  * Web-UI 기능 존재


### 참고)

* https://woowabros.github.io/tools/2018/10/08/location-service-with-rcs.html  
