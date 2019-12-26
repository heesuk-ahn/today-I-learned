# [TIL] Spring Cloud RSocket


## Spring cloud rocket client

* BrokerClient 제공
  * `RSocket` Broker에 연결할 수 있는 auto configuration 제공한다.
  * Clients 는 `ROUTE_SETUP` 을 보낸다.
  * `RSocketRequester` 설정
  * 요청들을 생성할 때, configuration properties을 통해서 Forwarding metadata를 자동으로 추가한다.

## Spring cloud rocket broker

* 클라이언트 그리고 다른 broker로 부터의 connection을 허용한다.
* 클라이언트가 연결 될 때, 진입점이 라우팅 테이블 안에 만들어진다.
* 빠른 검색을 위해 `Roaring 비트 맵`을 사용하여 인덱스를 만든다.
  * http://roaringbitmap.org
* Sends BROKER_INFO before remote brokers can forward to local connections
* Clients 는 어떤 언어로도 작성될 수 있고, 정확한 메타정보가 필요하다.
* Local brokers forward ROUTE_SETUP to other brokers via ROUTE_ADD
* 브로커는 Spring Framework의 RSocket @MessageMapping 과 RSocketRequester를 통하여 커뮤니케이션한다.
* RSocket이 지원하는 어떤 전송도 listen할 수 있다.
* Security는 Spring security가 지원된다
* Broker는 여러 레벨의 정보 수집을 위해 Micrometer가 필요하다. 일반적인 정보, 구체적인 커넥션, 요청 정보 등

## Gateway Architecture

* Java Client <-> Gateway Cluster [Gateway 1, Gateway 2…] <-> Js Client
* Client는 Gateway Cluster 로 연결을 생성한다.
* 커넥션할 때, `ROUTE_SETUP` 을 전송한다.
  * Who am I?
    * Id
    * ServiceName
    * Other tags such as : Region, InstanceName or Version
* 요청 생성
  * 클라이언트가 Gateway Cluster에 연결되어있다.
  * FORWADING metadata
  * Who do I want to call?
    * ServiceName and / or other tags such as: Region, InstanceName, or Version
    * Routing metadata
  * How to Route?
    * Unicast (default)
    * Multicast
    * Sharded
* Circuit breaker 가 없다
* Sidecar 없다
* Client side loadbalancer 가 없다.
* 존재하지않는 서비스에 대한 요청
  * Gateway creates a placeholder
    * Applies 100% backpressure
  * Avoids service startup ordering problems
* 요청 필터
  * 요청 레벨에서의 security를 허용한다
    * Ex) Service A는 Service B와 통신만 허용
  * 요청 레벨의 Metrics 수집된다.
* 필요하지 않은 것들에 대한 요약
  * Circuit Breaker - RSocket 프로토콜에서 back-pressure, resume 등을 지원하기 때문에 더이상 Circuit Breaker가 필요없다.
  * Sidecar - RSocket이 다양한 언어를 지원하므로..
  * Client-side load balancer - client가 broker에 진입할 때, 자신의 정보를 Broker cluster에 직접 등록한다.
  * Message Broker - RSocket의 4가지 인터랙션 모델중 스트림 모델이 있기 때문에 Message Broker가 필요하지 않음.
  * Special cases for warmup
  * Startup ordering problems

### 참고

https://www.slideshare.net/SpringCentral/reactive-architectures-with-rsocket-and-spring-cloud-gateway
