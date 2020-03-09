# 리눅스 컨테이너란?

* 최초의 컨테이너 기술은 리눅스 컨테이너다. 줄여서 LXC (Linux Containers) 라고 한다.
* LXC는 단일 컨트롤 호스트 상에서 __여러개의 고립된 리눅스 시스템 (컨테이너)들을 실행하기 위한 운영 시스템 레벨 가상화 방법이다.__
* 리눅스 커널은 __cgroups__ 를 절충하여 __가상화 머신을 시작할 필요 없이 자원 할당 (CPU, 메모리, 블록 I/O, 네트워크 등)을 한다.__
* Cgroups는 또한 애플리케이션 입장에서 __프로세스 트리, 네트워크, 사용자 ID, 마운트된 파일 시스템 등의 운영 환경을 완전히 고립시키기 위해
namespace isolation을 제공한다.__
* __LXC는 cgroups와 namespace를 결합하여 애플리케이션을 위한 고립된 환경을 제공한다.__
* __Docker 또한 실행 드라이버의 하나로 LXC를 사용할 수 있으며 이를 통해 이미지 관리와 개발 서비스를 제공한다.__

### 참고

* http://www.opennaru.com/openshift/docker/what-is-the-difference-between-docker-lxd-and-lxc/
* http://www.itworld.co.kr/news/105426
