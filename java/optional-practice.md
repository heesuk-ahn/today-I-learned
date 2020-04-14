# Optional을 올바르게 사용하기

* optional 올바르게 사용하기 위한 내용을 정리한다.


## 1. Optional 에 절대로 null 을 할당하지 말 것

```
나쁜 예)

Optional<Person> findById(int id) {
  if (result ==0) {
    return null;
  }
}
```
```
좋은 예)

Optional<Person> findById(int id) {
  if (result ==0) {
    return Optional.empty();
  }
}
```

* Optional 대신에 null을 반환하는 것은 도입 의도에 어긋난다.
* null 사용으로 인한 에러들을 방지하기 위해 만든 Type 이기 때문이다.
* `결과 없음`을 표현하려면 Optional.empty() 를 사용함이 옳다.

## 2. Optional.get() 호출 전에 Optional에 값이 있음을 확실히 할 것

* Optional 에 값이 없다면 `NoSuchElementException` 이 발생한다.
* 그렇기 때문에 Optional 에 무작정 get() 함수로 값을 가져오려 한다면, 사이드 이펙트가 발생할 수 있다.
* Optional 에서 값을 꺼낼 때는 항상 값이 존재하는지 확인하는 것이 필요하다.

```
나쁜 예)

Optional<Person> maybePerson = findById(1);
String name = maybePerson.get().getName();

//maybePerson 이 없는 경우 NoSuchElementException 이 발생 (사이드 이펙트)
```

```
피해야 하는 예)

Optional<Person> maybePerson = findById(1);

if (maybePerson.ifPresent()) {
  return maybePerson.get();
}
return UNKNOWN_PERSON;
```

```
좋은 예)

Person person = findById(4).orElseThrow(PersonNotFoundException::new);
String name = person.getName();
```

## 3. 값이 없는 경우, `Optional.orElse()`를 통해 기본 값을 제공

* `값이 없는 상황`에서는 값을 받아서 처리하기 위해서는 아래와 같은 두가지 경우로 핸들링 할 수 있을 것이다

1) 에러 처리 : 값이 없어 다음으로 진행할 수 없다면, 에러를 던진다.
2) 기본 값 제공 : 값이 없지만, 기본 값을 제공하여 로직을 이어갈 수 있다면, 기본 값을 제공한다.
3) 로직 skip : 값이 없으면 이후 로직을 이어서 안하면 된다면 로직 skip 으로 사용할 수 도 있다.

```
기본 값 제공 예)

findById(1).orElse(UNKNOWN_PERSON);
```

* 위와 같이 `orElse` 를 사용한다면, 엄격한 평가가 적용되어 즉각적으로 값이 사용된다는 점은 주의해야 한다.

```
주의)

findById(1).orElse(new Person());
```

* 위와 같이 사용한다면, 매번 값이 없을 때, Person 객체가 생성된다는 점이다.
* 이는 메모리를 소비할 수 있고, 만약 객체 생성이 무거운 비용이라면 주의해야 한다.
* 또, orElse 가 엄격한 평가이기 때문에 즉각즉각 평가가 되기 때문에, 사용되지 않는 경우에도
`Person` 객체가 생성된다는 것이다.

## 4. 값이 없는 경우, lazy 하게 처리하기 위해 `Optional.orElseGet()`를 사용해 기본 값 제공

```
좋은 예)

findById(1).orElseGet(() -> new Person());
```

* 이런 경우에는 위와 같이 `orElseGet` 을 사용하여 lazy 하게 필요할 때만 생성되도록 처리하는 것이
옳다.


## 5. 값이 없는 경우, 에러를 던져야 할 때는 `orElseThrow` 사용

```
findById(1).orElseThrow(() -> new RunTimeException("ERROR"));
```

## 6. 값이 있는 경우에는 사용하고, 없는 경우에는 아무 것도 안한다면, `Optional.ifPresent()` 를 활용

* Optional.ifPresent는 `Optional` 객체 안에 값이 있는 경우 실행할 람다를 인자로 받는다.
* 값이 있을 경우에 실행하고, 값이 없을 때는 실행하지 않을 때는 ifPresent 활용이 적절하다.

```
좋은 예)

findById(1).ifPresent(user -> log.info(user.getName));
```

## 7. `ifPresent` + `get` 은 `orElse` 나 `orElseXXX` 등으로 대체할 것

```
피해야 하는 예)

Optional<Person> maybePerson = findById(1);
if (maybePerson.isPresent()) {
  Person person = maybePerson.get();
  System.out.println(person.getName());
} else {
  throw new NoPersonNotFoundException("ERROR");
}
```

```
좋은 예)

findById(1).orElseThrow(() -> new NoPersonNotFoundException("ERROR"));
person.getName();
```

## 8. Optional 을 필드 타입으로 사용하지 말 것.

* Optional 은 __반환 타입을 위해 설계 된 것이다.__
* 뿐만 아니라, `Serializable` 도 아니기 때문에 `Optional`을 메서드의 인자로 사용하거나
클래스의 필드로 사용하는 것은 `Optional`의 도입 의도와 반하는 것이다.

```
나쁜 예)

class Person {
  Optional<String> address;
}

혹은

void printUserName(Optional<Person> maybePerson){
  //Something..
}
```

```
좋은 예)

class Person {
  String address = "";
}

혹은

void printUserName(Person person) {
  //Something..
}
```

## 9. Optional 을 빈 컬렉션이나 배열을 반환하는 데 사용하지 말 것

* 컬렉션이나 배열을 통해 값의 결과 없음 을 표현하기 위한 가장 좋은 방법은 무엇일까?
* 컬렉션이 비어있는 게 값의 결과 없을을 표현하는 것이다.
* 아래와 같이 사용하지 않도록 주의하자.

```
나쁜 예)

Optional<List<Person>> findByLastName(String lastName);
```

```
좋은 예)

List<Person> findByLastName(String lastName);
```

## 10. Optional의 컬렉션을 사용하지 말 것

* `Optional` 이 `결과 없음`을 나타내는 방법이라는 점에서 이를 컬렉에 사용하는 것이 유용해 보일 수 있다.
* 그러나 `Optional` 로 어떤 오브젝트를 감싸는 것은 비용을 생각하지 않을 수 없다.
* `Map<String, Optional<Integer>>` 이와 같이 사용한다면, Optional이 많아지면 그만큼 메모리 자원을
많이 잡아먹게 된다.
* 그래서 collection 에서는 Optional 자료구조를 사용하지 않는 것이 좋다.

## 11. 원시 타입의 Optional 에는 OptionalInt, OptionalLong, OptionalDouble 사용을 고려 할 것.

* 원시 타입을 `Optional`로 사용해야 할 때에는 박싱과 언박싱을 거치면서 오버헤드가 발생하게 된다.
* 반드시 Optional의 제네릭 타입을 맞춰야 하는 게 아니라면, int, long, double 등에는 `OptionalXXX`
타입 사용을 고려하는 것이 좋습니다.
* 이들은 __내부 값 래퍼 클래스가 아닌 원시타입으로 갖고, 값의 존재 여부를 나타내는 isPresent 필드를
함께 갖는 구현체들이다.__
* 때문에 기존의 Optional 타입에 사용할 때와 비교하면 박싱과 언박싱에서 생기는 오버헤드를 줄이게 된다.

```
좋은 예)

OptionalInt maybeInt = OptionalInt.of(2);
OptionalLong maybeLong = OptionalLong.of(3L);
OptionalDouble maybeDouble = OptionalDouble.empty();
```

## 12. 내부 값의 비교에는 `Optional.equals` 사용을 고려할 것

* `Optional.equals` 의 구현은 아래와 같다.

```
@Override
public boolean equals(Object obj) {
  if (this == obj) {
      return true;
  }

  if (!(obj instanceof Optional)) {
      return false;
  }

  Optional<?> other = (Optional<?>) obj;
  return Objects.equals(value, other.value);
}
```
* 기본적인 참조 타입 확인과 타입 확인 이후에 `Optional`의 동치성은 `equals`로 각 `값`에 근거하여 비교를 한다.

```
# 동치성 비교는 값으로 한다.

Objects.equals(value, other.value);
```
* 그렇기 때문에 `maybeA` 와 `maybeB`의 두 내부 객체 `a` 와 `b`에 대해 `a.equals(b)`가 true 이면,
maybeA.equals(maybeB)도 true이며 그 역도 성립한다.
* 굳이 내부 값의 비교만을 위해 값을 꺼내올 필요는 없다는 것이다.

```
나쁜 예)

boolean comparePersonById(long id1, long id2) {
  Optional<Person> maybePersonA = findById(id1);
  Optional<Person> maybePersonB = findById(id2);
  if (!maybePersonA.isPresent() && !maybePersonB.isPresent()) { return false; }
  if (maybePersonA.isPresent() && maybePersonB.isPresent()) {
      return maybePersonA.get().equals(maybePersonB.get());
  }
  return false;
}
```

```
좋은 예)

boolean comparePersonById(long id1, long id2) {
  Optional<Person> maybePersonA = findById(id1);
  Optional<Person> maybePersonB = findById(id2);
  if (!maybePersonA.isPresent() && !maybePersonB.isPresent()) { return false; }
  return findById(id1).equals(findById(id2));
}
```


## 13. 변환에 map과 flatMap 사용을 고려할 것

* Optional에도 map과 flatMap 메서드가 있다.
* 이를 활용하면 스트림처럼 함수형 스타일로 코드를 작성할 수 있다.

```
class Address {
    public static Address of(String text) { /* ... */ }
}

Address getUserAddress(long id) {
  findById(id)
      .map(Person::getAddress)
      .map(Address::of)
      .orElseGet(Address::emptyAddress());
```

* optional 값이 존재할 경우만 map 을 거치게 된다.
* flatMap은 map과 비슷하지만 인자로 전달되는 매퍼 함수의 반환 타입이 Optional이어야 한다는 점이 다르다.

```
class Address {
    // 반환 타입이 Optional
    public static Optional<Address> of(String text) { /* ... */ }
}

Address getUserAddress(long id) {
  findById(id)
      .map(Person::getAddress)
      .flatMap(Address::of)
      .orElseGet(Address::emptyAddress());
}
```

* Address.of의 반환 타입이 Optional일 때 map 메서드를 사용하면 만들어지는 타입은 Optional<Optional<Address>>가 된다.
* 살펴본 것과 같이 매퍼 함수가 Optional 객체를 생성할 책임을 갖는 경우에는 flatMap을, 그렇지 않은 경우에는 map을 활용한다.


## 14. 값에 대해 미리 정의된 규칙(제약사항)이 있는 경우에는 filter 사용을 고려할 것

* Optional.filter도 스트림처럼 값을 필터링하는 역할을 한다.
* 인자로 전달된 predicate이 참인 경우에는 기존의 내부 값을 유지한 Optional이 반환되고, 그렇지 않은 경우에는 비어 있는 Optional을 반환합니다.
* 유저네임에 대한 몇 가지 제약 사항을 검증하는 기능을 아래 메서드를 활용하여 다음과 같이 구현해볼 수 있다.

```
boolean isIncludeSpace(String str) { /* ... */ } // check if string includes white space

boolean isOverLength(String str) { /* ... */ } // check if length of string is over limit

boolean isDuplicate(String str) { /* ... */ } // check if string is duplicates with already registered

기존 방식)
boolean isValidName(String username) {
  return isIncludeSpace(username) &&
    isOverLength(username) &&
    isDuplicate(username);
}

Optional을 활용한 방식)

boolean isValidName(String username) {
  return Optional.ofNullable(username)
    .filter(this::isIncludeSpace)
    .filter(this::isOverLength)
    .filter(this::isDuplicate)
    .isPresent();
}
```

### 참고)

* https://www.latera.kr/blog/2019-07-02-effective-optional/#1-optional-%EB%B3%80%EC%88%98%EC%97%90-%EC%A0%88%EB%8C%80%EB%A1%9C-null-%EC%9D%84-%ED%95%A0%EB%8B%B9%ED%95%98%EC%A7%80-%EB%A7%90-%EA%B2%83
