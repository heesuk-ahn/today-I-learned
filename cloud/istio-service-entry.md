# istio service enrty 란?

* service entry 를 사용하면 Istio의 내부 서비스 레지스트리에 추가 항목을 추가할 수 있으므로
서비스 메시에서 자동검색된 서비스가 이러한 수동으로 지정된 서비스에 대해 엑세스 / 라우팅 할 수 있다.
* 서비스 항목은 서비스의 속성 (DNS 이름, VIP, 포트, 프로토콜, endpoint)을 설명한다.
* 이러한 서비스는 외부에 mesh하기 위한것이 될 수 있고 (예 : 웹 API) 또는 플랫폼 서비스 레지스트리의 일부가 아닌 메시 내부 서비스
(예 : Kubernetes의 서비스와 통신하는 VM 집합) 일 수 있다.
* 또한 workloadSelector 필드를 사용하여 서비스 항목의 endpoint 동적으로 선택할 수도 있다.
* 이러한 엔드포인트들은 `WorkloadEntry` 개체 또는 `Kubernetes 포드`를 사용하여 선언 된 VM 워크로드 일 수 있습니다.
* 단일 서비스에서 pod와 VM을 모두 선택할 수있는 기능을 통해 서비스와 연결된 기존 DNS 이름을 변경하지 않고도 VM에서 Kubernetes로 서비스를 마이그레이션 할 수 있다.

### 참고

* https://istio.io/latest/docs/reference/config/networking/service-entry/ (번역)
