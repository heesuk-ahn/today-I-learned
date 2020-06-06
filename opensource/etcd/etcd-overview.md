# [분산 KEY-VALUE 스토어] ETCD 살펴보기

* etcd 는 `분산 key-value store`이다.
* CoreOs 에서 CoreOs 인스턴스의 클러스터를 관리하기 위해서 사용되었으며, kubernetes 의 클러스터
관리를 위한 key-value store 로 사용되면서 더 유명해졌다.
* etcd 도 클러스터를 위해서 리더 선출 방식을 이용한다.
* `선출 방식`을 사용하기 때문에, 하나의 마스터와 하나 이상의 팔로워로 이루어져 있다.


## ETCD 문서 살펴보기 (https://github.com/etcd-io/etcd)

* ETCD는 분산 환경에서 중요한 데이터를 저장하는 분산 신뢰성 key value 저장소이다
* 아래와 같은 장점이 있다.
  * 간편함 : 유저가 사용하기 쉬운 API 들이 제공된다.
  * 속도 : 벤치마크로는 10,000 writes/sec 이다.
  * 신뢰성: Raft 를 사용하여 적절하게 배포한다.
  * 보안 : TLS 를 제공한다.
* ETCD 는 Go 로 작성되어있고, 고가용성을 위해 `Raft` 를 이용한 합의 알고리즘을 사용한다.

## ETCD 시작하기

* 먼저 가장 간단하게 etcd 를 single member cluster 로 구성하여 시작해보자.

```
> brew install etcd
```

* 위와 같이 brew 를 통해서 etcd 를 받으면, etcdctl 도 함께 받아져서 쉽게 클러스터를
조작할 수 있다.
* 받고 난 후에 `etcd` 를 터미널창에 입력하면 etcd 가 동작한다.

```
<terminal 입력>
> etcd
```

* 간단하게 etcd 에 etcdctl 을 이용하여 key-value 를 저장해보자.

```
> etcdctl put mykey "hello world"
> etcdctl get mykey

mykey
hello world
```

## ETCD TCP port

* etcd 는 공식적으로 2379 포트는 client 요청을 받기 위한 포트로 사용하고, 2380 은 peer 커뮤니케이션을
위해 사용한다.

## ETCD gRPC gateway

* etcd v3 부터는 gRPC 를 메세징 프로토콜을 사용한다. ETCD 프로젝트에서는 내부적으로 gRPC 베이스의 Go Client 와
커맨드 라인툴을 갖고 있다.
* 위와 같은 커맨드 라인툴과 go Client 를 통해서 grpc를 통해 etcd 클러스터와 통신한다.
* gRPC 를 지원하지 않는 언어를 위해서, etcd 는 JSON 기반의 통신을 위해 grpc-gateway 또한 포함하고 있다.
* gateway 서버는 RESTFul API 요청을 gRPC 메세지로 변환하여 프록시해준다.

* https://etcd.io/docs/v3.2.17/dev-guide/api_grpc_gateway/
