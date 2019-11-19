## 리액티브 프로그래밍을 구성하는 블록들

* Observables
  * `Observable` 들은 데이터 소스 / 스트림이다.
  * 여러가지 값, 한 개, empty 등 다양한 값을 방출 할 수 있다. ( 0 - N)
  * 에러를 방출할 수도 있다.
  * 데이터 스트림은 ‘무한’ 또는 ‘유한’ 일 수 있으며, 완료 이벤트 또한 방출할 수 있다.
* Subscribers
  * `Subscribers`은 `Observables`를 구독할 수 있다.
  * `Subscribers`은 `Observables`로 부터 데이터를 소비 할 수 있다.
  * `Subscribers`은 또한 `Observables`로 부터 `Errors` 와 `completion` 이벤트를 받을 수 있다.
* Operators
  * Observable를 만드는데 사용  (타이머, range, 다른 데이터소스)
  * Observable를 `변환` 하는데 사용 (map, buffer, group, scan, etc)
  * Observalbe를 `filter`하는데 사용 (filter, distinct, skip, debounce, etc)
  * Observalbe를 `combine`하는데 사용 (zip, merge, combine latest, etc)
* Schedulers
  * `Observable`과 `Subscriber`에 쉽게 스레딩을 추가 할 수 있는 메커니즘
  * `subscribeOn()` 은 `Observables`에 스케줄러가 작동해야하는 것을 지정한다.
  * `observableOn` 은 subscriber에게 통보 할 Scheduler / Thread를 지정한다.
* 리액티브 프로그래밍의 장점
  * `callback hell`을 피한다.
  * 비동기 / 스레드를 사용하기에 매우 간편하다.
  * 작업을 단순화 할 수 있는 많은 `operators`이 존재한다.
  * `stream of data`를 compose 하기에 간단하다.
  * 복잡한 threading 이 쉬워진다.
  * 더 깨끗하고 읽기 쉬운 코드를 작성할 수 있다.
  * `역압`을 쉽게 구현할 수 있다.
