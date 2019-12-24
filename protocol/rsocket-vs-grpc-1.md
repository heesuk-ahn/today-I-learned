## [TIL] RSocket vs gRPC

* RSocket과 gRPC의 차이에 대해서 비교해보자.

## RSocket

* RSocket은 reactive 문법을 어플리케이션 네트워크에 구현한 것이다.
* 네트워킹 프로토콜에 `역압` 이 존재한다.
* End-to-end의 리액티브 스트림 컨셉을 가지고 있다.
* 4가지 인터랙션 모델을 지원한다.

## gRPC

* RPC의 polyglot 을 구현했다.
* 2가지 파트로 나뉘어진다.
  * Protobuf : IDL (인터페이스 정의 언어)
  * Http 2 네트워킹 프로토콜

## 어떻게 효과적으로 비교할까?

* 같은 어플리케이션을 가지고 벤치마크 하는 것.
* gRPC 프로토콜에서 어플리케이션을 실행하기 위해서는 RPC Sdk가 필요하다.
* RSocket은 `encoders` 에서 매우 독립적이다.
* RSocket은 JSON, protobuf 그리고 다른 definition을 지원한다.
* 이 벤치마크에서, RSocket은 protobuf, java roc, messagepack를 사용하고, gRPC 또한 protobuf를 사용한다. (gRPC는 이미 encoder로 protobuf를 사용하는 것이 최적이라는 것이 증명됨.)

## Context

* 벤치마크 전에, 두가지 프로토콜을 비교해보자.
* RSocket은 __어플리케이션 간 통신__ 을 디자인 한 것이다.
* gRPC는 web traffic을 다루기 위해 디자인 되었다.
* 여기서 web traffic을 다룬다는 것은 무엇을 의미하는가?
* 통신에 대한 주체가 클라이언트와 서버라는 것이다.
* 즉 , Single Request & Response에 집중한 것이다.
* 물론 스트림도 가능하다. (Http 2)
* 여기서 기억해야 할 점은 TCP 는 서버와 클라이언트의 구분을 강제하지 않는다는 점이다.
* 우리가 HTTP / 2 프로토콜을 이용할 때, 동일한 소켓 연결을 사용하지 않고 서버가 클라이언트에 __역방향 요청을 하기에는 어렵다.__
* 반면, 어플리케이션 간 통신에서는 이는 매우 다르다.
* 어플리케이션은 양방향 통신이 가능하다 (bi-direction)
* 누가 서버이고 누가 클라이언트인지에 대한 제한은 크게 없다. 특히, 마이크로 소프트웨어 아키텍처에서는 더욱 그렇다.
* 누구나 서버가 될 수 있고 누구나 클라이언트가 될 수 있다.
* 대부분의 커뮤니케이션에 대한 시나리오를 커버하기 위해서, RSocket은 4가지 인터랙션 모델을 구현했다.
  * Request / Response ( stream of 1)
  * Request / Stream (finite stream of many)
  * Fire-and-forget (no response)
  * Channel (bi-directional streams)
  * RSocket은 multiplexed 할 뿐만 아니라, 같은 socket 커넥션을 유지하면서 sender와 receiver가 역할을 바꿀 수 도 있다.

## Benchmark Test

* Setup
* 2대의 서버는 아래와 같은 사양이다.
  * 4-core Intel(R) Xeon(R) Platinum 8163 CPU @ 2.50GHz + 8G
* JVM Configuration
  * -Xmx2g -Xms2g -XX:+AlwaysPreTouch -XX:+UseStringDeduplication
* Sampling Rule
  * 10번의 시도 중 가장 최고의 결과
* Tool
  * Netify에서 개발된 비교 툴. (자바 스택)

## Results

* 결과들을 살펴보면, Java version 에서 RSocket SDK는 gRPC에 비해 QPS, latency, CPU 소비등에서 모두 성능이 더 나은걸로 나온다.

### 참고

https://dzone.com/articles/rsocket-vs-grpc-benchmark (rocket vs grace benchmark)
https://medium.com/netifi/differences-between-grpc-and-rsocket-e736c954e60 (Differences between gRPC and RSocket)
