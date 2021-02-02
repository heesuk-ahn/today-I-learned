# [ITEM 2] 생성자에 매개변수가 많다면 빌더를 고려하라

우리는 생성자를 만들 때, 때로는 매개변수가 많은 생성자가 만들어질 수 있다.

예를 들어서 식품의 영양정보를 나타내는 객체인 `FoodInformation` 이 있다고 생각해보자.

이 `FoodInformation` 에는 다양한 멤버변수가 존재할 수 있다.

- 1회 제공량 (required)
- 칼로리 (required)
- 나트륨 (optional)
- 콜레스테롤 (optional)
- 포화지방 (optional)
- 트랜스지방 (optional
- ...

그러나 이런 멤버변수들이 꼭 필수가 아닌 경우들도 많다. 일부 값들은 `required` 이지만 일부 값들은 `optional` 일 수 있다.

이런 경우에 우리는 어떻게 이를 구현할까?

예전에는 자바의 `오버로딩` 을 사용해서 `점층적 생성자 패턴` 을 사용했다.

```java

public class FoodInformation {

  private final int servingSize;  // 필수
  private final int calories;     // 필수
  private final int fat;          // 선택
  private final int sodium;       // 선택
  private final int carbohydrate; // 선택

  public FoodInformation(int servingSize, int calories) {
    this(servingSize, calories, 0);
  }

  public FoodInformation(int servingSize, int calories, int fat) {
    this(servingSize, calories, fat, 0);
  }

  public FoodInformation(int servingSize, int calories, int fat, int sodium) {
    this(servingSize, calories, fat, sodium, 0);
  }

  public FoodInformation(int servingSize,
											   int calories,
												 int fat,
												 int sodium,
												 int carbohydrate) {
		this.servingSize = servingSize;
    this.calories = calories;
    this.fat = fat;
    this.sodium = sodium;
    this.carbohydrate = carbohydrate;
  }

}
```

위에 클래스를 보면 생성자가 하나씩 늘어나면서 점층적으로 쌓여져 가는걸 볼 수 있다.

위와 같은 방식으로 하면, 원하는 생성자를 이용해서 객체를 생성할 수 있다.

그러나, 명확하게 단점이 있다. 생성자가 많아지는 것도 그렇지만, 우리가 만약 `servicngSize`, `calories`, `sodium` 만 파라미터로 넘겨서 생성한다고 가정해보자.

그렇다면 위에 생성자들 중에서

```java
 public FoodInformation(int servingSize, int calories, int fat, int sodium) {
    this(servingSize, calories, fat, sodium, 0);
  }
```

이 메서드를 이용해서 구현을 해야한다. 그런데, `fat` 은 optional이기 때문에

```java
new FoodInformation(1, 1, 0, 1);
```

위에 처럼 `fat` 에는 0을 넣어줄 수 밖에 없다. 불필요한 파라미터를 꼭 넣어주어야 하는 것이다.

만약에 파라미터가 20개가 된다고 생각해보자. 그러면 우리는 단지 이를 위해서 20개나 되는 생성자를 구현을 해주어야 한다. 이는 코드를 보기 어렵게도 하고 작성하기에도 힘들다.

 **또, 사용자 측에서 생성자에 매개변수를 넘겨줄 때 매개변수를 실수로 잘못 넘겨주는 가능성도 높아진다. 그러다보면 눈치채기 어려운 버그를 생산해 낼 수도 있다.**

그럼 이를 좀 더 개선한 방법은 무엇이 있을까? 두번째 방법은 `자바 빈즈 패턴` 이다. 이 패턴은 아마 많은 자바 개발자들에게 익숙한 방법일 수 있다.

 매개변수가 없는 생성자로 객체를 만든 후, 해당 객체를 `set` 메서드들을 통해서 생성하는 것이다.

```java
public class FoodInformation {

  private int servingSize = 1; // 필수
  private int calories = 1;    // 필수
  private int fat = 0;         // 선택
  private int sodium = 0;      // 선택
  private int carbohydrate = 0;// 선택

  public FoodInformation() { }

  public void setServingSize(int servingSize) {
    this.servingSize = servingSize;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }

  public void setFat(int fat) {
    this.fat = fat;
  }

  public void setSodium(int sodium) {
    this.sodium = sodium;
  }

  public void setCarbohydrate(int carbohydrate) {
    this.carbohydrate = carbohydrate;
  }

}
```

```java
var foodInformation = new FoodInformation(); // jdk 11 에서는 타입 추론이 가능하다.

foodInformation.setServingSize(2);
foodInformation.setCalories(3);
foodInformation.setFat(4);
```

그러나 자바 빈즈 패턴에서는 치명적인 단점들이 존재한다.

- 객체 하나를 생성하기 위해서 메서드를 여러번 호출해야 한다.
- 객체가 완전히 생성되기 전까지는 일관성이 무너진 상태이다.

이 방식은 객체를 어디서든 수정을 할 수 있기 때문에 객체에 대한 변화가 일어나는 장소와 수정된 객체가 사용되는 장소가 코드의 물리적 거리가 멀리 떨어져있을 가능성이 있다.

 그렇게 되면 디버깅하는 것도 쉽지 않게 될 수 있다. 그렇다. `자바 빈즈 패턴` 은 객체를 불변 아이템으로 만들 수 없으며 스레드 안전성을 얻으려면 프로그래머가 추가 작업을 해주어야만 한다.

 그러면 좀 더 나은 대안은 없는 것일까? 좀 더 나은 대안은 바로  이 챕터에 주제인 `빌더 패턴을 사용하라` 이다.

빌더 패턴은 앞에서 소개한 `점층적 생성자 패턴` 과 `자바 빈즈 패턴` 의 장점만 취한 패턴이고 production 에서도 많이 사용되는 패턴이다.

 빌더 패턴은 생성하려는 객체의 내부에 속하며 static 클래스로 제공되는 경우가 일반적이다. 코드로 직접 확인해보자.

```java
public class FoodInformation {

  // 모든 멤버변수가 `final` 로 선언되어있다. 한번 선언되고 나면 불변이기에 스레드 세이프하다.
  private final int servingSize;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;

  private FoodInformation(final int servingSize,
												  final int calories,
													final int fat,
													final int sodium,
													final int carbohydrate) {
		this.servingSize = servingSize;
    this.calories = calories;
    this.fat = fat;
    this.sodium = sodium;
    this.carbohydrate = carbohydrate;
  }

  public static class Builder {

    // 필수 매개변수
    private final int servingSize;
    private final int calories;

    //선택 매개변수 - 기본 값으로 초기화한다.
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public Builder(final int servingSize, final int calories) {
      this.servingSize = servingSize;
      this.calories = calories;
    }

    public Builder fat(int val) {
      this.fat = val;
    }

		public Builder sodium(int val) {
      this.sodium = val;
    }

		public Builder carbohydrate(int val) {
      this.carbohydrate = val;   
    }

    public FoodInformation build() {
      return new FoodInformation(servingSize, calories, fat, sodium, carbohydrate);
    }
}
```

위와 같이 빌더 패턴으로 클래스를 만든 후에 사용을 해보자

```java
new FoodInformation.Builder(1, 1)
									 .fat(2)
									 .sodium(1)
									 .build();
```

위와 같이 필수 매개변수는 빌더 호출시에 강제로 넣도록 처리할 수 있고 옵셔널한 매개변수들은 뒤에 메서드 호출을 통해서 넣을 수 있다.

 빌더 패턴에서 반환타입이 빌더 자기자신을 다시 반환하기 때문에 일련의 메서드들을 연속적으로 호출할 수 있는데 이런 방식을 `메서드 체이닝` 또는 `Fluent API` 라고도 한다.

 위와 같은 예제가 가장 기본적인 사용이지만, `빌더 패턴` 은 계층적으로 잘 설계된 클래스들에서도 유용하게 사용 될 수 있다.

```java
public abstract class Pizza {

    public enum Topping {
        HAM,
        MUSHROOM,
        ONION,
        PEPPER,
        SAUSAGE
    }

    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {

        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        abstract Pizza build();

        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }

}
```

위에 코드를 보자. 먼저 `Pizza.Builder` 는 재귀적 타입한정을 사용한 제네릭 타입이다. (recursive type bound)

 재귀적 타입 바운드는 제네릭 타입인 `T` 매개변수가 자신을 포함하는 수식에 의해서 한정되는 것을 의미한다.  그렇기 때문에 타입 `T` 는 자신을 포함하는 수식의 하위 타입만 올 수 있다는 것을 의미한다.

주로 많이 사용되는 경우는 <T extends Comparable<T>> 인데, 자바에서는 연산자 오버로딩이 지원되지 않기 때문에 제네릭 타입을 비교해야 하는 경우에는 컴파일 에러가 발생하고 만다.
 그러면 제네릭 타입이라면 비교 연산을 할 수 없는 것인가? 일반적으로는 그렇지만,

public interface Comparable<T> {
  int compareTo(T o);
}

위와 같은 인터페이스를 지닌 Comparable을 통해서 제네릭을 정의하면 가능하다

static <T extends Comparable<T>> T max(List<T> list) {
    Iterator<T> iterator = list.iterator();
    T result = iterator.next();
    while (iterator.hasNext()) {
        T t = iterator.next();
        if (t.compareTo(result) > 0) result = t;
    }

    return result;
}

여기서 T 타입은 "자신과 비교될 수 있는 모든 타입 T" 라고 해석될 수 있다.

재귀적 타입한정을 사용하면 이점은 "하위 타입에서 상위 타입에 메서드를 사용할 수 있다" 라는 것이다.

```java
public class NewYorkPizza extends Pizza {

    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {

        private final Size size;

        public Builder(Size size) {
            this.size = size;    
        }

        @Override
        Pizza build() {
            return new NewYorkPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NewYorkPizza(Builder builder) {
        super(builder);
        size = builder.size;
    }
}
```

```java
public class CalzonePizza extends Pizza {

    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {

        private final boolean sauceInside;

        public Builder(final boolean sauceInside) {
            this.sauceInside = sauceInside;
        }

        @Override
        Pizza build() {
            return new CalzonePizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private CalzonePizza(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }
}
```

위에 코드들을 보면 `Pizza` 의 하위 클래스가 2개 있다. 하나는 뉴욕피자이고 하나는 칼초네 피자다.

뉴욕 피자는 크기 매개변수를 필수로 받고 칼초네 피자는 소스를 안에넣을지 선택하는 매개변수를 필수로 받는다.

각각이 필요한 매개변수는 각자 하위타입에서 지정할 수 있으며, `build` 를 하게되면 각각 상위 객체 타입이 아니라 하위 객체의 타입을 반환하는 걸 볼 수 있다.

 하위 클래스의 메서드가 상위 클래스의 메서드가 정의한 반환 타입이 아닌 그 하위타입을 반환하는 기능을 `공변반환 타이핑` 이라 한다.

 이 기능을 이용하면 클라이언트가 형 변환에 신경 쓰지 않고도 빌더를 사용할 수 있다.

 또 위에 코드에서 인상깊은 점은 `재귀적 타입 바운드` 를 사용함으로써 하위 구현객체에서 상위 구현객체의 메서드를 사용할 수 있도록 처리하였다는 점이다.

 생성자로는 누릴 수 없는 사소한 이점으로, 빌더를 이용하면 가변인수 매개변수를 여러개 사용할 수 있다. 각각을 적절한 메서드로 나눠 선언하면 된다. 아니면 메서드를 여러번 호출하도록 하고 각 호출 때 넘겨진 매개변수들을 하나의 필드로 모을 수 도 있다.

 위의 코드에서 addTopping 메서드가 이러한 사례다.

 빌더 패턴은 상당히 유연하다. 빌더 하나로 여러 객체를 순회하면서 만들 수 있고, 빌더에 넘기는 매개변수에 따라 다른 객체를 만들 수 도 있다.

 물론 빌더 패턴에 장점만 있는 것은 아니다. 객체를 만들려면 그에 앞서 빌더부터 만들어야 한다.

또한 점층적 생성자 패턴보다는 코드가 장황해서 파라미터가 4개 이상은 되어야 값어치를 한다.

 하지만 API는 시간이 지날수록 매개변수가 많아지는 경향이 있음을 명심하자. 애초에 빌더로 시작하는 편이 나을때가 많다.

## 핵심정리

- 생성자나 정적 팩토리가 처리해야할
