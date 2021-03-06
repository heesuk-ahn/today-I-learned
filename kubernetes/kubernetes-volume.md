# 쿠버네티스 볼륨 - 퍼시스턴트 볼륨과 퍼시스턴트 볼륨 클레임

* 컨테이너는 기본적으로 상태가 없는 (stateless) 앱을 사용한다.
* 상태가 없다는 것은 어떤 이유로건 컨테이너가 죽었을 때 현재까지의 데이터가 사라진다는 것을 의미한다.
* 상태가 없기 때문에 컨테이너에 문제가 있거나 노드에 장애가 발생해서 컨테이너를 새로 띄우거나
다른 곳으로 옮기는게 자유롭다
* stateless 앱들은 주로 webapp 형태들이다. webapp 은 어떤 노드에건 띄워도 괜찮다.
* 하지만, mysql 은 어떨까? 볼륨이 사라지면 데이터 저장된 것이 모두 사라지므로, 이 경우에는 어떻게
처리할 수 있을까? kubernetes 에서 pod 은 기본적으로 불멸의 존재가 아니라 언제든지 죽을 수 있는
존재라는걸 기억하자.
* `kubernetes`는 이러한 볼륨들을 관리할 수 있도록 `퍼시스턴트 볼륨`과 `퍼시스턴트 볼륨 클레임`을
제공한다

## 퍼시스턴트 볼륨과 퍼시스턴트 볼륨 클레임

* 쿠버네티스에서 볼륨을 사용하는 구조는 PV 라고 불리는 퍼시스턴트볼륨과 PVC 라고 불리우는 퍼시스턴트볼륨클레임
2개로 분리되어있다.
* PV는 볼륨 자체를 의미한다. 클러스터 내에서 리소스로 다루어진다.
* 포드하고는 별개로 관리되고 별도의 생명주기를 가지고 있다.
* PVC 는 사용자가 PV에 하는 요청이다.
* 사용하고 싶은 용량은 얼마인지 읽기/쓰기는 어떤 모드로 설정하고 싶은지 등을 정해서 요청한다.
* 쿠버네티스는 볼륨을 포드에 직접할당하는 방식이 아니라 이렇게 중간에 PVC를 둠으로써 포드와 포드가 사용할
스토리지를 분리했다.
* 이걸 통해서 파드 입장에서는 PVC 만 바라보면, 그 뒷단에 PV는 개방/폐쇄 원칙 처럼 확장이 가능하다.
* 이를테면, 퍼시스턴트 볼륨을 클라우드 서비스에서 제공해주는 볼륨을 사용하기도 하고, Local 볼륨을
사용하기도 하지만, pod 에서는 pvc만 바라보고 있으면 된다.
