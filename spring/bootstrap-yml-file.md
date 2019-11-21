# Spring-Cloud bootstrap.yml 파일

* Spring-cloud application 에서 사용되는 속성 파일
* `application.yml` 파일보다 먼저 load 되는 것이 bootstrap.yml 이다. (Spring cloud 내에서)
* 이 파일 설정 `bootstrap.yml`을 통해서 application이 bootRun 되기 전에 `Config Server`에서 환경에 맞는 설정 파일을 불러와 실행이 되게 된다.
* 따라서 먼저 Build시에 application.yml보다 먼저 설정이 필요할 경우 bootstrap.yml에 설정을 해주면 된다
