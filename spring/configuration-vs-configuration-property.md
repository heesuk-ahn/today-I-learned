# `@Configuration` vs `@ConfigurationProperties`

## @Configuration

* 이 어노테이션은 해당 클래스가 Bean의 설정을 할 것이라는 것을 나타낸다. Bean 설정 클래스는 항상 이것을 붙인다.
* Configuration 의 경우에는 설정을 위한 새로운 Bean 들을 등록하는데에 사용한다.
* 메소드에 `@Bean` 어노테이션을 붙여주어야 한다.

```
@Configuration
public class CustomConfiguration {

    @Bean
    public SomeClass someClass() {
        return new SomeClass();
    }
}
```
* @Configuration = @Documented  + @Component
  * `Documented` 어노테이션은 해당 어노테이션을 `javadoc`에 추가함
  * @Component는 component-scan에 의해 빈으로 등록되는 클래스에 붙는다.
  * @Component 는 @Controller, @Service, @Repository등의 어노테이션으로 구체화 될 수 있다.
  * 기본적으로 4가지 어노테이션은 모두 빈으로 등록된다는 점은 같지만, __차이점을 명확하게 시각적으로__ 보여주기 위함이다. (가독성 측면)
  * 이런 측면을 이용해서 AOP 에서 PointCut에서 어노테이션 단위로 지정할 수 있기 때문에 유용하게 사용될 수 있다.

## @ConfigurationProperties

* __외부에 있는 설정을 클래스의 멤버변수로 매핑하는 것__
* `@Value` 와 비교하여 이점은 모든 멤버변수에 `@Value` 를 붙여주어야 하는데, `@ConfigurationProperties` 는 `prefix` 와 매핑되는 설정 값들을 읽어오기때문에 장점이 있음.
* `@ConfigurationProperteis`는 이것만으로 `@Bean` 으로 등록이 되지는 않기 때문에, `@EnableConfigurationProperties` 를 이용하여 `@Bean` 등록이 가능하다.

### 참고

* https://stackoverflow.com/questions/53072107/springboot-difference-between-configuration-and-configurationproperties configuration vs configuration properties
