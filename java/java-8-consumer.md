# Java 8 consumer

* 리턴을 하지 않고, 인자를 받는다.
* 인자를 받아서 `소비`한다고 생각하면 된다.

```
Consumer<String> c = str -> System.out.println(str);
c.accept("HELLO, CONSUMER");
```
