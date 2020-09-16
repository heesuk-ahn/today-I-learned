# [JAVA] ScheduledExecutorService 란?

* ScheduledExecutorService란 무엇인가?
* ScheduledExecutorService 인터페이스는 ExecutorService 인터페이스를 확장한 것으로 다음과 같은 메소드를 정의하고 있다.
* __지정된 지연 후 실행되거나 주기적으로 실행되도록 명령을 예약 할 수있는 ExecutorService 이다.__

## 제공되는 메서드

* scheduledExecutorService 에서는 아래와 같은 메서드들을 제공한다.
  * schedule(Runnable command, long delay, TimeUnit unit) :
    * command 가 일정시간 이후에 한번 실행되는 메서드이다.
  * scheduleAtFixedRate(command, initialDelay, period, TimeUnit) :
    * 초기 지연 후에 일정한 간격으로 작업을 실행한다.
    * 예를들면, scheduleAtFixedRate(runnable, 1, 2, SECONDS) 라고 한다면,
    1) 초기 지연 1초 -> 12:00 시 정각에 1초 딜레이된 12:00:01 에 runnable 이 실행
    2) Period 2초 -> 12:00:03, 12:00:05, 12:00:07... 순으로 command 가 실행된다.
  * scheduleWithFixedDelay(command, initialDelay, delay, unit) :
    * 초기 지연 후에 커맨드가 끝나고 나서부터 delay 를 한후 실행한다.
    * 예를 들면, scheduleWithFixedDelay(runnable, 1, 2, SECONDS) 라고 한다면,
    1) 초기 지연 1초 -> 12:00 시 정각에 1초 딜레이된 12:00:01 에 runnable 실행
    2) runnable 이 실행되고 완료되는데까지 2초가 소요 (12:00:03에 종료)
    3) runnable 이 종료된 2초 뒤에 실행 -> 12:00:05
    4) 12:00:07에 runnable 이 종료되면 다시 2초 딜레이 후에 실행 -> 12:00:09 실행

## ScheduledFuture

* ScheduledExecutorService 에서 실행된 결과값은 `ScheduledFuture` 로 반환된다.

```
ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)
ScheduledFuture<?> schedule(Callable<V> callable, long delay, TimeUnit unit)
ScheduledFuture<?> scheduleWithFixedDelay(Runnable, long initialDelay, long delay, TimeUnit unit)
ScheduledFuture<?> scheduleWithFixedRate(Runnable, long initialDelay, long delay, TimeUnit unit)
```

* ScheduledFuture 인터페이스는 Future 인터페이스와 Delayed 인터페이스를 상속한다.
* Delayed 인터페이스는 다음의 메소드만을 정의한다. 이것은 다음 실행 스케줄까지 얼마나 남았는지 반환한다.

```
long getDelay(TimeUnit unit)
```
