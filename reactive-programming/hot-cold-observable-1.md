# [TIL] HOT, COLD Observable 개념

* HOT, Cold 개념은 RxJAVA 에도 있는 개념으로 Reactor의 Hot, Cold 개념은 `https://projectreactor.io/docs/core/snapshot/reference/#reactor.hotCold` 에서도 확인 가능하다.
* `Cold`
  * `Cold` 는 각 `Flux` 나 `Mono`를 `subscribe` 할 때마다 매번 독립적으로 새로 데이터를 생성해서 동작한다.
  * 즉, `subscribe` 호출 전까지 아무런 동작도 하지 않고, `subscribe` 를 호출하면 새로운 데이터를 생성한다. (Laziness)
  * 기본적으로 특별하게 `Hot` 을 취급하는 연산자가 아닌 이상 `Flux` 나 `Mono`는 `Cold` 로 동작한다.
* `Hot`
  * `Hot`은 구독하기 전부터 데이터의 스트림이 동작할 수 있다.
  * 즉, `구독자의 수`에 의존하지 않는다.
  * `Hot` Observable은 구독자가 없어도 데이터 publishing을 즉각적으로 수행한다.
  * 새로운 구독자가 구독을 하면 __구독한 시점으로 부터 새로운 방출되는 data를 받을 수 있다.__
