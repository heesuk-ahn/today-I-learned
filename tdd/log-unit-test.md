# Java Junit 에서 Log 테스트하기

* 때때로 log 정보를 assert 로 비교해야하는 테스트를 할 수 있다.
* 예를 들어서, 우리가 file을 생성하고 이에 대해서 log 로 `create file success!`를 찍었다고
해보자.
* file을 생성하는 작업의 함수 호출을 verify 로 증명할 수 도 있지만, 찍힌 log 를 확인함으로써
부수효과에 대한 테스트를 진행할 수 있다.
* Java 에서 Junit 을 통해 log 테스트를 위해서는 먼저 logger에 log appender 를 셋팅해주어야
한다.
* 아래 코드를 살펴보자.

```
@Sl4j
public class Hello {

  public void writeFile() {
      log.info("create file success!")
  }

}
```
* 위와 같이 writeFile 메서드를 가진 Hello class 가 있다고 해보자.
* 우리는 이것을 어떻게 테스트 할 수 있을까?


```
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class HelloTest {

  private final Hello hello = new Hello();

  private ListAppender<ILoggingEvent> getListAppenderForClass(Class clazz) {
    Logger logger = (Logger) LoggerFactory.getLogger(clazz);

    ListAppender<ILoggingEvent> loggingEventListAppender = new ListAppender<>();
    loggingEventListAppender.start();

    logger.addAppender(loggingEventListAppender);

    return loggingEventListAppender;
  }

  @Test
  public void testWriteFile() {
      ListAppender<ILoggingEvent> logEvent = getListAppenderForClass(Hello.class);

      hello.writeFile();

      assertThat(logEvent.get(0)).isEqualTo("create file success!");
  }

}
```

* 위에서 log 정보를 기록하기 위한 appender를 logger에 추가하기 위해서 `getListAppenderForClass`
를 만들었다.
* 우리는 여기에 우리가 테스트 하기를 원하는 class 정보를 넣어서 해당 class의 log 정보를 기록하는
ListAppender 정보를 얻을 수 있다.
* 이를 통해서 log 에 대한 테스트를 쉽게 진행할 수 있다.
