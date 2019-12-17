# [TIL] 스프링 부트 실행이 로컬 mac 환경에서 느릴 때

* 스프링 부트를 로컬 맥북에서 실행할 때, 스타트 타임이 긴 경우가 있다.
* 이러한 문제로 Spring boot integration test 시에도 속도가 나오지 않는 문제가 있다.
* 이를 간단하게 해결하는 방법은 `/etc/hosts` 를 수정해주는 것이다.
* 먼저, terminal에 아래와 같이 입력한다.

```
[terminal]

입력$ hostname
출력$ HOST_NAME.local
```

* 위와 같이 호스트 네임이 출력되면, 해당 정보를 복사해놓는다.
* 이후, `sudo vi /etc/hosts` 를 실행한다.
* root 권한으로 수정할 수 있는 파일이기 때문에 sudo로 실행한다.

```
127.0.0.1	localhost
::1       localhost
```

* 실행을 할 경우, 위와 같이 localhost 정보가 나올 것이다.
* 위의 호스트 정보 뒤에다가 아까 복사해놓은 hostname을 붙여넣으면 된다.

```
127.0.0.1	localhost HOST_NAME.local
::1       localhost HOST_NAME.local
```

* 위와 같이 vi로 수정 후, esc 를 누르고 :wq 로 저장 후 종료를 하면 된다.
* 위와 같은 방법으로 실행해보니, 나의 경우에는 전체적으로 로컬에서 integration test 실행시나 부트업 시간이 많이
줄어들었다.
