# [TIL] git remote 를 ssh로 변경하기

* Git remote를 http를 사용하면 항상 id와 password를 입력해야 하는것이 귀찮을 수 있다.
* 이때 ssh 키 방식을 사용하면 이런 불편을 줄일 수 있다.
* 먼저 ssh 를 생성하여 사용하는 원격 repo에 등록하도록 하자.
* Ssh 키 생성 커맨드

```
ssh-keygen -t rsa -C “이메일”
```

* 위와 같이 ssh 키를 생성하고 나면, `~/.ssh/id_rsa.pub.` 경로에 생성된 public key를 확인할 수 있다.
* 생성된 public key를 복사하여 bitbucket의 자기 계정 내의 `manage account`로 들어간다.
* 해당 탭에 가면 `Add ssh key`를 할 수 있고 거기에 public key를 등록한다.
* 이후, clone을 받을 때 http 방식이 아니라 ssh 방식으로 받으면 해당 repo는 ssh 방식으로 push / fetch 등을 할 수 있다.
* 만약 이미 http 로 clone 받은 것들은 어떨까?
* 이 경우에는 http -> ssh로 변경해야 한다.
* git을 사용할 때 원격 repository 를 ssh 로 등록하고 나면, 기존에 http 로 clone 하여 받은 repo들은 remote가 http 로 설정되어있다.
* 이 경우, push 할 때, id와 password 방식으로 접근한다.
* ssh를 등록 후에, http -> ssh 로 변경해보자.
* 자신이 사용하는 repo 디렉토리로 이동한 후, `git remote -v` 커맨드를 사용하면 리모트 정보를 확인할 수 있다.

```
origin	http://blah.git (fetch)
origin	http:/blah.git (push)
```

* 위와 같이 remote가 http로 되어있는 것을 ssh로 수정해보자.

```
git remote set-url origin ssh://blah.git
```

* (참고) remote ssh 정보는 해당 repo에서 직접 가져오면 된다.
* 설정을 하고 다시 git remote -v 로 확인하면 정상적으로 변경되어있는 것을 확인할 수 있다.
