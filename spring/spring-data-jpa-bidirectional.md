# Spring Data Jpa 양방향 연관관계

![singgle-mapping](../static/spring/bidirectional.png)

* 위의 이미지 처럼 `Member` 에서도 호출할 수 있고, `Team`에서도 호출할 수 있도록 양방향 관계는 어떻게 할 수 있을까
* 데이터베이스에서는 이미 양방향 관계가 가능하다.
* 객체에서는 서로가 서로를 알고 있어야 한다.
* 여기서 사용하는 것은 `@OneToMany` 어노테이션이다.

![one-to-many](../static/spring/one-to-many.png)

* 위와 같이 선언하면 이제 `Team`에서 `Team`에 속한 모든 `Member`들을 가져올 수 있다.

## 양방향 관게에서 `mappedBy`는 왜 필요할까?

* __만약 mapped by 설정을 해주지 않았다면, 그것은 `양방향 관계`가 아니라 서로가 서로를 참조하는 `단방향 관계`이다__
* 그래서 mapped by를 해주지 않는다면, 아래와 같이 2가지 관계가 생성된다.
  * `ManyToOne` : FK 키가 테이블에 추가된다.
  * `OneToMany` : `Join Table`이 생성된다.
* 서로가 서로를 참조하기 때문이다.
* 스키마 자체가 위에 처럼 조인 테이블이 새로 생기게 변경되는 것이다.
* 원하는 것이 서로가 서로를 참조하는게 아니라 양방향 관계라면 `mappedBy`를 설정해야한다.
* `@OneToMany`에서 Many 측에서 참조하는 게 `mappedBy=team`으로 설정하면 이제서야 양방향 관계이다.
* 조인테이블이 새로 생기지 않고 FK의 주인이 `Member`이기 때문에 FK 키는 `Member`에 생성된다.  
* 그래서 Memmber에서 `setTeam`을 통해 `FK`를 수정할 수가 있다.
* 양방향 관계에서는 한쪽에서만 `FK`를 수정하고, 한쪽에서는 `READ`만 가능하게 설계하기 위해 `mapped by`를 만들었다.
* `mappedBy`가 붙어있는 쪽이 바로 읽기만 가능한 쪽이다.

![bi](../static/spring/jpa-bi.png)

* 위 이미지를 보면, 둘다 `FK`를 수정하면 컨텍스트 이해가 어려워진다.

![bi-2](../static/spring/jpa-bi-2.png)

* 그래서 위 이미지 처럼 한쪽에서만 접근이 가능하도록 한 것이다.
* 이런 차이를 모르고 Team에서 Member를 수정했는데 __왜 `FK`가 안바뀌지?__ 하는 사례가 꽤 많다.

![bi-3](../static/spring/jpa-bi-3.png)

* 이 차이를 잘 모르면 위와 같이 __권한이 있는 주인에서 수정하지 않아서 `FK`가 `NULL`로 설정 되는 경우가 많다.__
