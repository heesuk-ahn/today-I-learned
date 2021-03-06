# 기술 부채

## 기술 부채란 무엇일까?

* 쉽게 말하면, '오늘 할 일을 내일로 미루는 것이다.'
* 때로는 개발을 할때 기술적으로 해결 되어야 할 문제들을 뒤로 미루고 비즈니스 문제를 해결하기 위한
시점을 앞당기게 되는데, 이 때, 뒤로 미루어진 문제들이 바로 __기술부채다.__

## 내가 겪어본 기술 부채의 사례는 무엇이 있을까?

* 서버리스 & Node 로 작성되어있는 서비스 Scala / Akka Actor 로 리팩토링
* Akka Actor 기반을 gRPC 통신으로 리팩토링
* 잘 작성되어있지 않은 테스트
* 리팩토링이 필요한 코드
* 자동화 되어있지 않은 CI

## 기술 부채는 당장 해결해야 할까?

* 개발을 하면서 기술 부채는 항상 발생한다.
* 매번 발생한 기술 부채를 해결하려고 하면, 당장 개발해야할 비즈니스 로직들이 뒤로 미뤄지게된다.
* 기술 부채를 쳐 나가는 것과 비즈니스 로직을 개발해 나가는 균형을 맞추는 것이 필요하다.
* 기술 부채는 __빅뱅 처럼 해결하는 것이 아니다.__
* 점진적으로, 유기적으로 해결을 해 나가야 한다.
* 점진적으로 해결하기 위해서 Legacy 를 통채로 바꾸기 보다 새로운 서비스를 작은 단위로
적용 시켜 점진적으로 리팩토링 해 나가는 것이 현명하다.
* 이와 같은 방법으로 비즈니스 로직 개발을 유지하면서 레거시를 점진적으로 리팩토링할 수 있다.

## 기술 부채 해결에 대한 의사 결정

* 의사 결정을 위해서는 몇가지 질문이 필요하다.

  - 기술 부채를 해결하기 위해 얼마나 노력이 드는가?
  - 기술 부채를 해결하여 얻게 되는 가치는 무엇인가?
  - 비즈니스 문제 해결을 미뤘을 때, 어떤 문제가 생기는가?

* 위와 같은 질문은 추상적일 수 있지만, 정량화해서 기술 부채 해결을 위한 우선순위를 정해야 할 것이다.
