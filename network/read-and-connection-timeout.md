# Connection Timeout 과 Read Timeout

* 아예 서버 자체에 클라이언트가 어떤 사유로 접근 실패시, `Connection Timeout`
* Server와 커넥션은 하였으나, 서버의 로직 수행시간이 길어서 client가 연결을 해제하는 것이
`Read Timeout`
* 위의 두 개념을 이용해서 실제로 서버간 통신 시에 설정을 거는 것이 중요하다.
* `Fail Fast` 를 위해서다.
  - `Fail Fast` 란 빠르게 실패하게 하는 것.
* 이를 테면 connection timeout 을 10s 로 걸었다고 가정해보자.
  - A 서버는 B 서버가 과부하 상황에서 Connection 요청을 하고 10s 동안 커넥션이 맺어질때까지
  Blocking 이 될 것이다.
* 위와 같은 상황은 굉장히 비효율적이다. 차라리 `Connection Timeout` 을 짧게 가져가서 빠르게
실패하게 하는 것이 낫다. ex) connection timeout = 200ms
* `Read Timeout` 도 예를 들어보자. DB에 Query 를 걸고 `10s` 간 기다려보자.
* 10s 동안도 쿼리가 수행이 안되었다면, 과연 DB Connection 을 물고서 10s 간 block 되는게 옳을까?
* 쿼리의 수행시간이 1s 미만이라면, `1s` 로 걸어서 빠르게 `Fail-Fast` 시키는 것이 좋다.
