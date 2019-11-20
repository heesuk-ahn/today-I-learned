#  Hubble - 쿠버네티스를 위한 분산 모니터링 시스템

* Cilium 에서 만든 쿠버네티스를 위한 분산 모니터링 시스템이다.
* 4가지 기능을 제공해준다.
  * Service dependencies & communication map
    * 서비스 간 통신하는 디펜던시는 어떠한가 ( 데이터베이스, 외부 API 포함)
    * 얼마나 자주 통신하는가
    * 디펜던시를 그래프로 볼 수 있는가
  * Operational monitoring & alerting
    * 어떤 네트워크가 실패하는가
    * 왜 실패했는가
    * Network layer 4 계층 문제인가? (TCP) 아니면 layer 7 계층 문제인가? (HTTP)
  * Application monitoring
    * 5xx, 4xx 비율은 어떠한가
    * Request, response 의 95th, 99th 퍼센트 레이턴시는 어떠한가
    * 어떤 서비스 퍼포먼스가 안좋은가
    * 서비스간 레이턴시는 어떠한가
  * Security observability
    * 어떤 서비스가 어떤 네트워크 정책때문에 막혀있는가
    * 외부 클러스터 서비스에서 해당 서비스에 접근할 수 있는가
* 시스템 리소스를 모니터링 할 수 있지만, zipkkin 처럼 분산 서비스 간에 디펜던시도 UI로 그려준다는 것이다.
* 여기서 Hubble이 강조하는 것은 kubernetes 환경에서 `zero-effort automatic discovery of the service dependency graph for Kubernetes`
특별하게 해줄 것 없이 적용된다는 것
* 그게 가능한 이유는 Humbble이 `eBPF` 기반으로 만들어졌기 때문이다.

### 참고
* https://cilium.io/blog/2019/11/19/announcing-hubble/
