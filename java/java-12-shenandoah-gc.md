# [TIL] Java 12 - Shenandoah GC

## Overview

* Java 12 부터 Redhat 에서 개발한 Shenandoah GC 가 릴리즈 된다.
* Shenandoah 의 강점은 GC 의 Stop-the-world 시간이 짧다는 것이다.
* Stop the world 의 시간을 줄이기 위해서,  __Shenandoah 에서는 GC를 가동할 때, Java의 스레드로 동시적으로 GC를 실행한다.__
* 또한, 세난도어는 __Heap Size에 상관 없이 일관되고 예측 가능한 짧은 GC 타임을 목표로 한다.__
* 따라서, 힙 크기가 15MB 인지 또는 15GB 인지는 중요하지 않다.

### 참고

https://www.journaldev.com/28666/java-12-features#1-jep-189-8211-shenandoah-a-low-pause-time-garbage-collector-experimental
