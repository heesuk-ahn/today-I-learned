# Python venv를 이용한 환경 분리

* python 가상환경 venv란?
  * 개발을 할 때 우리는 다양한 디펜던시를 이용하여 개발을 하게 된다.
  * 이 때, python의 버전에 따라 패키지 의존성 버전이 달라질 수 도 있다.
  * 컴퓨터마다 다른 개발 환경으로 인해 패키지 의존성이 달라진다면 문제가 될 수 있다.
  * 그렇기에, 통합된 환경을 제공해주기 위해 가상환경 패키지 디펜던시를 제공할 수 있는 venv가 만들어졌다.
* venv 생성하기

  ```
  python3 -m venv example //example venv 생성
  cd example
  source bin/activate //해당 venv 활성화
  ```
  위와 같이 `example`이라는 venv 환경을 만들고 활성화를 시킬 수 있다.

* 해당 `venv` 환경에서 pip 을 이용하여 패키지를 받으면 `requirementes.txt`에 디펜던시가 기록되며,
  이후 `pip install -r /path/to/requirements.txt`를 통해 requirementes에 있는 디펜던시들을
  받을 수 있다. 
