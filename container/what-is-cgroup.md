# 리눅스 커널의 cgroups 이란?

* 리눅스 커널에서 컨테이너 기술의 기본이 되는 `cgroups` 에 대해서 정리한다.

## 하드웨어 리소스를 분리하기 위한 리눅스 커널 cgroups 의 탄생

* `cgroups`(control groups의 약자)는 __프로세스들의 자원의 사용(CPU, 메모리, 디스크 입출력, 네트워크 등)을 제한하고 격리시키는 리눅스 커널의 강력한 기능이다.__
* `cgroups` 은 구글 엔지니어들이 2006년에 리눅스 커널에서 사용자 공간에 물리적인 리소스를 분리하기
위해 최초로 개발을 하게 되었다.
* 2007년 말에 `컨테이너` 라는 용어의 의미가 너무 추상적이고 다양하게 추측 될 수 있으므로, 혼란을 방지하기 위해 `컨트롤 그룹 (control groups)` 로 변경되었다.
* 컨트롤 그룹 기능은 2008년 1월에 출시된 커널 버전 2.6.24에 리눅스 커널의 메인 라인으로 머지되었다.
* 머지되고 난 후 cgroups 을 통해서 리눅스 컨테이너 등으로 확산되게 되었다.

## cgroups 사용하기

* cgroups 을 사용하면 아래와 같은 자원을 제한 및 격리할 수 있다.
```
  - Memory
  - CPU
  - Network
  - Device
  - I/O
```  
* cgroups 을 관리하는 방법에는 여러가지가 있다.
```
  - cgroup 파일 시스에 직접 접근
  - cmanager 이용
  - cgroup-tools 이용
```
* 위의 3가지 방법 중에 `cgroup-tools` 를 사용해서 간단한 확인을 해보자.
* `cgroup-tools` 를 받으면, `cgcreate` 와 `cgdelete` 명령어를 통해서 관리가 가능해진다.

```
(CPU 제한을 위해 그룹 생성)

sudo cgcreate -a foo -g cpu:testCpuGroup
ls -al /sys/fs/cgroup/cpu/ | grep testCpuGroup

(출력)

drwxr-xr-x 2 foo root 3월 6일 12:00 testCpuGroup
```
* 위에 cpu를 제한하기 위해 그룹을 만들고, -a 옵션을 통해서 foo 사용자가 소유하도록 하였다.
* cgroup 을 생성하게 되면, `/sys/fs/cgroup` 디렉토리 내부에 제한하는 자원에 따라
각자의 디렉토리 내부에 새로 디렉토리가 생성되는 것을 확인할 수 있다.
* CPU 제한이 잘 걸리는지 확인하기 위해서 먼저 `stress` 커맨드로 CPU 과부하를 걸어보자.

```
(부하 걸기)

stress -c 1
top

//%CPU 퍼센트 100
```

* 이제 CPU에 제한을 걸고 stress 를 주었을 때, 해당 group 에 정확하게 제한이 걸리는지 확인
해보자.

```
(CPU 제한 설정 - cgset 명령어)

//cpu를 3G 로 제한
sudo cgset -r cpu.cfs_quota_us=30000 testCpuGroup

//특정 그룹에 CPU 부하를 준다.
sudo cgexec -g cpu:testCpuGroup stress -c 1

//CPU 부하 리소스를 확인한다.
top

//%CPU 퍼센트 : 29.9%
```

* 이렇게 위와 같이 cgroup 을 생성하고 특정 리소스를 제한하는 것을 확인했다.
* cgroup 을 사용하면, __프로세스를 그룹지어 제한__ 또한 가능하며, 다양한 리소스들을
제한할 수 도 있다.

### 참고

* https://ko.wikipedia.org/wiki/Cgroups (WIKI)
* https://hwwwi.tistory.com/12
