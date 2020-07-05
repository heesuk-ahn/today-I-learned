# [이펙티브 자바] 생성자 대신 정적 팩터리 메서드를 고려하라

* class의 인스턴스를 어떻게 얻냐? 라고 했을 때, 바로 떠오르는 것은 `public 생성자` 이다.
* 하지만 생성자 대신 `static factory method` 를 써보는 것은 어떨까? 아래와 같은 장점들을 가질 수 있다.

## 장점들에 대해서 살펴보자.

* 이름을 가질 수 있다.
  * 자바에서는 다형성으로 인해 여러개의 생성자를 가질 수 있다.
  * 하지만, 생성자에는 __이름을 줄 수 없다__
  * 단지, 매개변수에 따라서 생성자가 매핑될 뿐이다.
  * 그렇다면, 매개변수에 따라서 얻게되는 오브젝트의 의미를 파악하려면 코드 내부를 살펴봐야 할 수 도 있다.
  * 반면, __정적 팩토리 메서드는 이름을 가질 수 있기에 보다 명확하고 반환될 객체의 특성을 쉽게 묘사할 수
  있다.__ (장점 1)
  * 예를 들어 값이 소수인 BigInteger를 반환하는 객체를 만든다고 가정할 때
  `BigInteger(int, int, Random)` 과 `BingInteger.problePrime` 중 어느 쪽이 의미를
  잘 전달하는지 생각해보면 이름을 가진다는 것에 대한 이점을 이해 할 수 있을 것이다.

* 호출 될 때마다 인스턴스를 새로 생성하지 않아도 된다.
  * 인스턴스를 반드시 새롭게 만들 필요가 없이 새로 생성한 인스턴스를 캐싱하여 재활용하는 식으로
 불필요한 객체 생성을 피할 수 있다.
  * 때문에 생성비용이 큰 객체가 자주 요청되는 상황에서 큰 성능향상을 노려볼 수 있으며, 인스턴스 통제가 가능하다.

* 반환 타입의 하위 타입 객체를 반환 할 수 있다.
  * 생성자는 리턴값이 없는 반면, 정적 팩토리 메소드는 반환값을 가진다.
  * 이 때 반환 객체를 하위 클래스로 지정 할 수 있다.
  * 반환할 객체의 클래스를 자유롭게 선택할 수 있어 __엄청난 유연성을 가진다.__

  ```
  class OrderUtil {

    public static Discount createDiscountItem(String discountCode) throws Exception {
        if(!isValidCode(discountCode)) {
            throw new Exception("잘못된 할인 코드");
        }
        // 쿠폰 코드인가? 포인트 코드인가?
        if(isUsableCoupon(discountCode)) {
            return new Coupon(1000);
        } else if(isUsablePoint(discountCode)) {
            return new Point(500);
        }
        throw new Exception("이미 사용한 코드");
    }
  }

  class Coupon extends Discount { }
  class Point extends Discount { }
  ```

* 정적 팩터리 메서드 네이밍의 일반적인 이름
  * 생성자 처럼 API 설명에 명확하게 드러나지 않기 때문에, 사용자가 정적 메서드 네이밍을 직접해야한다.
  * 이를 극복하기 위해 메서드 이름으로 널리 알려진 규약을 따라 짓는다. 규약은 아래와 같다.
    * from : 매개변수 하나를 받아서 해당 타입의 인스턴스 반환하는 형변환 메서드
    ex) Date d = Date.from(instant)
    * of : 여러 매개변수를 받아, 적합한 타입의 인스턴스를 반환하는 집계 메서드
    ex) Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
    * instance 혹은 getInstance : 매개변수로 명시한 인스턴스 반환, 같은 인스턴스임을 보장하지는 않는다.
    ex) StackWalker luke = StackWalker.getInstance(options);
    * create 혹은 newInstance : 매번 새로운 인스턴스를 생성해 반환
    ex) Object newArray = Array.newInstance(classObject, arrayLen);
    * getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 사용
    ex) FileStore fs = Files.getFileStore(path);

### 참고)

* https://velog.io/@litien/%EC%83%9D%EC%84%B1%EC%9E%90-%EB%8C%80%EC%8B%A0-%EC%A0%95%EC%A0%81-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC-%EA%B3%A0%EB%A0%A4%ED%95%98%EB%9D%BC
