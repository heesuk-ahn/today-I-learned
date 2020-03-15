# Factory Pattern


## 팩토리 패턴 사용 전의 중복되는 구현들

* `Factory Pattern` 에 대해서 정리하자.
* 어떤 객체를 생성 할 때, 우리가 하나의 Concrete 객체를 만든다면 어떨까?
* 아래와 같은 상황을 고려해보자. 우리가 다양한 Thirdparty 서비스와 remote 통신을 해야하는
상황이다.

```
pbulic class ServiceImpl {

  private final RemoteServiceA remoteServiceA;
  private final RemoteServiceB remoteServiceB;
  .
  .
  .

  public ServiceImpl(RemoteServiceA remoteServiceA, RemoteServiceB remoteServiceB) {
    this.remoteServiceA = remoteServiceA;
    this.remoteServiceB = remoteServiceB;
  }

  public getRemoteData(String remoteServiceName) {
      if (remoteServiceName == "remoteServiceA") {
        return remoteServiceA.getData();
      } else {
        return remoteServiceB.getData();
      }
  }
}
```

* 위의 예제에서 `ServiceImpl` 는 `remoteServiceA`와 `remoteServiceB` 에 의존적이다.
* 만약 새로운 `remoteServiceC` 가 추가된다고 해보자. 그러면 우리는 어떻게 해야할까?


```
pbulic class ServiceImpl {

  private final RemoteServiceA remoteServiceA;
  private final RemoteServiceB remoteServiceB;
  private final RemoteServiceC remoteServiceC; // 1) 새롭게 추가된 remoteService C
  .
  .
  .

  public ServiceImpl(RemoteServiceA remoteServiceA,
                     RemoteServiceB remoteServiceB,
                     RemoteServiceC remoteServiceC) {
    this.remoteServiceA = remoteServiceA;
    this.remoteServiceB = remoteServiceB;
    this.remoteServiceC = remoteServiceC;
  }

  public getRemoteData(String remoteServiceName) {
      if (remoteServiceName == "remoteServiceA") {
        return remoteServiceA.getData();
      }
      if (remoteServiceName == "remoteServiceB") {
        return remoteServiceB.getData();
      }
      if (remoteServiceName == "remoteServiceC") {
        return remoteServiceC.getData();
      } // 2) 새롭게 추가되었기 때문에, getRemoteData 에 코드를 수정해주어야 한다.
      return new IllegalArgumentException("Invalid Service Name! Not Matched!");
  }

}
```

* 위의 예제를 보면 알겠지만, 새롭게 추가된 서비스가 생성될 때마다 `ServiceImpl` 내부의 코드가 영향을 받는다.
* 이런 경우에 유지보수적인 측면, 그리고 코드의 재사용 가능성을 살펴보자면 관리 포인트가
늘어나게 된다.
* 무언가 코드 내에서 __중복되는 코드, 중복되는 작업__ 등이 발생하고 있다면, 이는 __리팩토링의 필요성이 있다__ 라는
하나의 시그널이라고 볼 수 있다.
* 위와 같은 경우에 사용할 수 있는 패턴 중 하나가 오늘 정리하는 __팩토리 패턴__ 이다.
* `팩토리 패턴`은 객체를 생성하기 위한 인터페이스를 정의하는데, 어떤 클래스의 인스턴스를 만들지는 서브 클래스
에서 결정하게 만든다. 팩토리 메소드 패턴을 이용하면 클래스의 인스턴스를 만드는 일을 서브 클래스에게 맡기는 것이다.
* 여기서 생각해 봐야하는 SOLID 원칙 중 하나가 `의존성 뒤집기 원칙`이다.
* 우리는 객체간 결합도를 낮추기 위해서 __추상화 된 것에 의존하도록__ 만들어야 한다.

## 팩토리 메서드 사용으로 리팩토링을 하자.

```
public AbstractRemoteSerivceFactory {

  abstract RemoteService createRemoteService(String serviceName);

}

public RemoteServiceFactory extends AbstractRemoteSerivceFactory {

  @Override
  public RemoteService createRemoteService() {
    switch(serviceName) {
      case "remoteServiceA":
        return new RemoteServiceA();
      case "remoteServiceB":
        return new RemoteServiceB();
      case "remoteServiceC":
        return new RemoteServiceC();
    }
  }

}

public class ServiceImpl {

  public ServiceImpl(RemoteServiceFactory remoteServiceFactory) {
    this.remoteServiceFactory = remoteServiceFactory;
  }

  public getRemoteData(String remoteServiceName) {
    return remoteServiceFactory.createRemoteService(remoteServiceName).getData();
  }

}

```

* 위와 같이 Factory 추상 클래스를 만들어서 서브 클래스가 생성에 대한 책임을 갖도록 할 수 있다.
* 그리고 이를 사용하는 ServiceImpl 에서는 __추상화된 것에 의존__ 하게 됨으로, 변화에 유연해진다.
