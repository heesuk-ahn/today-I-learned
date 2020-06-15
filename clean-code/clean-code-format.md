# 형식 갖추기

* 적절한 코드의 행수, 열수는 어떤 것이 적당할까?
* 클린 코드 책에서는 7가지 프로젝트의 평균 값을 비교하며 이에 대해서 설명한다.
* 7개의 유명한 오픈소스 프로젝트의 파일당 행수는 약 40%가 대략 100줄 정도가 평균이다.
* 복잡한 프로젝트도 200줄 내외로 작성이 되어있다.
* 이로 보아, 엄격하게 지켜야 할 규칙은 아니지만, __일반적으로 큰 파일보다 작은 파일이 이해하기가 쉽다.__

## 코드는 신문 기사처럼 작성하라.

* 코드를 작성할 때 추상적인 함수는 위로, 그리고 좀 더 구체적인 함수는 아래로 배치한다.
* 코드를 볼 때, 사람의 눈은 위에서 아래로, 왼쪽에서 오른쪽으로 움직인다.
* 이러한 것에 독자들은 익숙하므로, 신문 기사처럼 먼저 화두를 앞에 던지고, 구체적인 내용은 뒤에다
적으면 독자는 이해하기가 쉬울 것이다.

## 공백은 때로는 개념의 분리를 나타낸다.

* 관련이 있는 코드들은 같이 뭉쳐있으면 이해하기가 좋다.
* 코드에서 `new line`은 __새로운 개념을 시작한다는 시각적 단서다.__

```
public class Foo {

  private final int val;
  private final int val2;

  public Foo(final int val, final int val2) {
    this.val = val;
    this.val2 = val2;
  }

  public int getVal() {
    return val;
  }

  public int getVal2() {
    return val2;
  }
}
```

* 위와 같이 개념이 나뉠 때 빈행으로 표시하면 한결 보기 좋다.
* 빈행을 없애면 암호문 처럼 보일 것이다.

```
public class Foo {
  private final int val;
  private final int val2;
  public Foo(final int val, final int val2) {
    this.val = val;
    this.val2 = val2;
  }
  public int getVal() {
    return val;
  }
  public int getVal2() {
    return val2;
  }
}
```

## 세로 밀집도

* __줄바꿈이 개념을 분리한다면, 세로 밀집도는 연관성을 의미한다.__
* 즉, 서로 밀접한 코드 행은 __세로로 가까이 놓여야 한다는 것이다.__

```
public class ReportConfig {

  // 리포터 리스너의 이름
  private String mClassName;

  ///리포터 리스너의 속성
  private List<Property> mProperties = new ArrayList<Property>();
  public void addProeprty(Property proeprty) {
    mProperties.add(property);
  }
}
```

* 위 코드는 리스너의 이름과 리스너의 속성이 떨어져있다.
* 위 코드보다는 아래 코드가 코드의 구성을 이해하기 쉽다.

```
public class ReportConfig {

  // 리포터 리스너의 이름
  private String mClassName;
  private List<Property> mProperties = new ArrayList<Property>();

  public void addProeprty(Property proeprty) {
    mProperties.add(property);
  }
}
```

* 멤버변수가 2개있고 메서드가 1개라고 좀 더 빠르게 파악이 가능하다.

## 수직 거리

* 함수 연관 관계와 동작방식을 이해하려고 이 함수에서 저 함수로 오가며 소스 파일을 위아래로
뒤지는 등 뺑뺑이를 돈 경험이 있는가.
* 이 조각 저 조각이 어디에 있는지 찾고 기억하느라 시각과 노력이 소비된다.
* __서로 밀접한 개념은 가까이 둬야한다.__

## 변수 선언

* 변수는 사용하는 위치에 최대한 가까이 선언해야한다.

## 인스턴스 변수

* 인스턴스 변수는 클래스 맨 처음에 선언한다. 변수 간에 세로로 거리를 두지 않는다.
* 잘 설계한 클래스는 많은 클래스 메서드가 인스턴스 변수를 사용하기 때문이다.

## 종속 함수

* 한 함수가 다른 함수를 호출한다면, 호출하는 함수를 호출 되는 함수보다 먼저 배치해야한다.
* 그러면 코드가 자연스럽게 읽힌다.
* 우리 눈이 위에서 아래로 움직이기 때문이다.
* 추상적인 내용에서 구체적인 내용으로 점점 내려가야한다.

## 개념적 유사성

* 종속적인 관계가 아니더라도, 오버로딩 등 개념적인 친화도가 높은 함수들은 같이 배치하는 것이 좋다.

## 가로 형식 맞추기

* 한 행은 가로로 얼마나 길어야 적당한가?
* 7개의 유명 프로젝트를 살펴보면, 20~60자 사이인 행이 총 행 수의 40%에 달한다.
* 즉, 프로그래머는 명백하게 짧은 행을 선호한다.
