# kubernetes의  Health Check

* kubernetes는 다양한 pod들이 Node에 할당되어 동작한다.
* pod들은 기본적으로 `mutable`한 속성을 가지고 있어서 언젠가는 꼭 한번 이상 죽을 것이라는게 가정되어
있다.
* 그렇기 때문에 pod은 고정 IP를 사용하는 것이 아니라 service discovery를 위해 `Service Node`가
존재한다.
