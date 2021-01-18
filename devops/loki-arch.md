# Loki: Read & Write path

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/afc89891-d071-470e-ac78-d4a189b1cafc/_2021-01-18__5.38.55.png](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/afc89891-d071-470e-ac78-d4a189b1cafc/_2021-01-18__5.38.55.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210118%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210118T143347Z&X-Amz-Expires=86400&X-Amz-Signature=69b4ecad24c1c8ae0f9bce31cb2c6fe738dfec69cfa13b721048a49182c1f0c0&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22_2021-01-18__5.38.55.png%22)

## Distributor

일단 promtail 이 로그를 수집하고 그 로그들을 Loki 로 보내면, Distributor 가 가장 먼저 로그데이터를 받게된다.

여기서 우리는 초당 수백만의 로그 write 요청을 받을 수 있다. 그리고 이런 쓰기 요청을 들어오는 대로 바로 database에 기록하는 것을 우리는 원하지 않는다. 만약 그렇게 한다면 외부에 있는 그 어떤 데이터베이스라도 죽어버리고 말것이다. 우리는 들어오는 데이터들을 압축하고 batch 작업으로 처리할 필요가 있다.

우리는 데이터들을 압축하기 위해서 들어오는 데이터들을 데이터의 chunks 단위로 gzip을 이용해 로그들을 압축했다. Ingester 컴포넌트는 chunks 들을 빌드하고, 그리고 후에 chunks 들을 데이터베이스에 flush 한다는 점에서 stateful한 컴포넌트이다.

우리는 multiple ingesters을 갖고 있고, 각 스트림에 속하는 로그는 모든 관련 항목이 동일한 청크로 끝나기 위해 항상 동일한 ingester에서 끝나야 한다.

이런 ingester를 구현하기 위해 우리는 ingesters 을 consistent hashring 을 이용해서 ingesters들을 ring으로 구성했다.
entry가 들어올 때, distributor는 로그의 라벨을 해시하고, 그리고 해시 값을 기반으로 어떤 ingester에게 그 entry를 보내야할지 찾게된다.

## Ingester

Ingester 는 Distribuotr 에게 Entry 데이터를 받게 되고 chunk 파일을 만들기 시작한다.

![ingester](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/5b499e3c-c013-4110-bb69-93d4100b12cc/_2021-01-18__5.47.01.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210118%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210118T143459Z&X-Amz-Expires=86400&X-Amz-Signature=da9a9087e658d7304d16150d4e8132f8043b58021c75699e426281f9250e9673&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22_2021-01-18__5.47.01.png%22)

기본적으로 로그들은 gzip 으로 압축된다. 일단 chunk가 꽉차게되면, loki의 ingster는 해당 chunk를 database에 flush 한다.
우리는 청크 (ObjectStorage)와 인덱스에 대해 저장하는 데이터 유형이 다르기 때문에 별도의 데이터베이스를 사용한다.


![ingester-2](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f758c741-4193-49cc-b889-30cd98d217d8/_2021-01-18__5.49.59.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210118%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210118T143531Z&X-Amz-Expires=86400&X-Amz-Signature=b2adf789fb05bbc86f74327a85772b80c2f0ac1159e51e69836c122375d63d0c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22_2021-01-18__5.49.59.png%22)

 chunk 를 플러시 하고 난 후, ingester는 새롭게 빈 chunk 객체를 만들고, 새로운 엔트리들을 다시 추가하기 시작한다.

## Querier

read path는 매우 심플하고 쿼리어가 대부분의 무거운 작업을 수행한다. 주어진 time-rage 그리고 label selectors에서 쿼리어는 어떤 chunks가 매치되는지 이해하기 위해서 index를 참조하고, 그리고 그것을 이용해서 결과를 반환하기 위해 훑어본다.
 쿼리어는 또한 ingester 에 저장되어있는 in-memory 값을 확인한다. 아직 chunk flush 되지 않고 ingester에 있는 스트림 데이터가 있을 수 도 있기 때문이다.
 지금은 각 쿼리에 대해 단일 쿼리자가 모든 관련 로그를 검색하낟. 우리는 frontend를 사용하여 Cortex에서 쿼리 병렬화를 구현했으며 동일한 것을 loki 로 확장하여 분산된 grep을 제공하여 큰 쿼리도 충분히 빠르게 만들 수 있다.

![Querier](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/0310d9aa-2d29-443a-996c-1ef004695128/_2021-01-18__6.00.20.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210118%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210118T143600Z&X-Amz-Expires=86400&X-Amz-Signature=4d7a56b6d9a3e6a0930c471c72ade3eb9903c71ae475d304c824ec9eaffa7001&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22_2021-01-18__6.00.20.png%22)

## Scalability

이제 scales에 대해서 살펴보자.

1) 청크파일을 object store에 넣고 그리고 그것은 확장된다.

* object storage 는 인터넷 기반 스토리지 서비스로 안전하게 데이터를 보관하거나 대용량의 데이터를 무제한으로 저장할 수 있는 대용량 스토리지 서비스이다.
* REST API 기반으로 데이터를 안전하게 저장되며 데이터 백업, 미디어, 파일서버 용도로 사용할 수 있다.
* REST API 기반이기 때문에 로드밸런서를 통해서 쉽게 확장할 수 있다.

2) 우리는 index를 cassandra /  bigTable / DynamoDB에 넣고 이것 또한 확장된다.

3) Distributors 과 queriers 는 상태가 없는 컴포넌트들이고, 그렇기 때문에 이것 또한 확장이 가능하다.

Ingester로 돌아와서, Ingester는 stateful한 컴포넌트이지만, 우리는 전체 샤딩 및 re-sharding 라이프 사이클을 구축했다.

롤 아웃이 완료되거나 ingester가 scaled up or down 할 때, ring topology는 변경되고 distributor가 새 토폴로지와 일치 하도록 청크를 재배포한다. 이것은 대부분 2년이상 운영된 cortex의 코드 베이스에서 가져온 로직이다.


## 참고)

* https://grafana.com/blog/2018/12/12/loki-prometheus-inspired-open-source-logging-for-cloud-natives/ (번역)
