# [TIL] EntityManagerFactory, EntitiyManager, PersistenceContext 란?


## EntityManagerFactory란?

* 데이터베이스를 하나만 사용하는 애플리케이션들은 일반적으로 `EntityManagerFactory`를 한개만 생성한다.
* 이 엔터티 매니저 팩토리로 엔터니 매니저를 생성할 수 있다.
* `EntityManagerFactory`는 `Thread Safe` 해서 여러 스레드가 동시에 접근해도 안전하므로, 서로 다른 스레드간에 공유해서 사용한다.

## EntityManager 란?

* 엔터티 매니저는 엔터티를 저장하는 메모리상의 데이터베이스라고 생각하면 될 것 같다.
* 엔터티 매니저는 엔터티를 __저장하고 삭제하고 수정하고 조회하는 등__ 엔터티와 관련된 모든 일을 한다.
* 하지만, 위의 `EntityManagerFactory`와 달리 `Thread Safe` 하지 않기 때문에, 동시성 문제가 발생하므로, __스레드간에 절대 공유해서는 안된다.__
* 그래서 일반적으로 `EntityManager`를 `EntityManagerFactory` 를 이용하여 생성한 것을 사용하는 것이 아닌, 스프링에서 관리하는 `EntityManager` 를 아래와 같이 선언하여 사용한다.
* 이렇게 되면 스프링에서 알아서 `EntitiyManager` 를 proxy 로 감싼 `EntityManager` 를 생성 하여 주입해주기 때문에 `Thread-Safety`를 보장한다.
* Hibernate 에서는 `Session(EntitiyManager)`으로 불리운다.

```
@PersistenceContext
Private EntitiyManager entitiyManger;
```

## PersistenceContext (영속성 컨텍스트)란

* 영속성 컨텍스트란 엔티티 (Entitiy)를 영구 저장하는 환경을 말한다.
* 엔티티 매니저로 엔티티를 저장하거나 조회하면 엔티티 매니저는 영속성 컨텍스트에 엔티티를 보관하고 관리한다.
* 영속성 컨텍스트는 엔티티 매니저 (Session)를 생성할 때 하나 만들어진다. 그리고 엔티티 매니저(Session)를 통해서 영속성 컨텍스트에 접근할 수 있고 영속성 컨텍스트를 관리 할 수 있다.
* 여러 엔티티 매니저(Session)가 같은 영속성 컨텍스트에 접근할 수 도 있다.

### 참고

* https://kihoonkim.github.io/2017/01/27/JPA(Java%20ORM)/2.%20JPA-%EC%98%81%EC%86%8D%EC%84%B1%20%EA%B4%80%EB%A6%AC/
* https://minwan1.github.io/2018/06/14/2018-06-14-EntityManagerFactory,%20EntityManager,%20PersistenceContext%EB%9E%80/
* https://medium.com/@SlackBeck/jpa-entitymanager%EC%99%80-%EB%8F%99%EC%8B%9C%EC%84%B1-e30f841fcdf8
