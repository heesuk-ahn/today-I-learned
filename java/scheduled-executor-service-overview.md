# [JAVA] ScheduledExecutorService 란?

* ScheduledExecutorService란 무엇인가?
*  java.util.concurrent 에 포함된 스케줄링을 할 수 있는 스레드 풀 매니저이다.
* __지정된 지연 후 실행되거나 주기적으로 실행되도록 명령을 예약 할 수있는 ExecutorService 이다.__

## 제공되는 메서드

* scheduledExecutorService 에서는 아래와 같은 메서드들을 제공한다.
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
