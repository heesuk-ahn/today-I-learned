# java spring bean은 singleton 이다.

* Spring bean은 별도 설정을 하지 않을 경우 한 개의 빈 객체만을 생성한다.
* 싱글톤이란 단일 객체를 의미한다.
* 기본적으로 빈의 스코프는 __싱글톤스코프__ 이다
* 싱글톤 스코프는 컨테이너 내에 한 개의 오브젝트만 만들어져서 강제로 제거하지 않는 한, 컨테이너가 살아있는 한 계속 유지된다.
* 그렇다면 왜 스프링컨테이너는 대부분의 객체를 싱글톤으로 제공할까
* 스프링은 자바 엔터프라이즈 개발을 위한 프레임워크이다.
* 그 말은 즉 사용자가 많다는 말.
* 사용자의 요청마다 새로운 객체를 생성해서 제공하는 것은 비용이 크다.
* 그렇기 때문에 기본적으로 싱글톤으로 객체의 갯수를 제한한다.
* 스프링 컨테이너는 싱글톤관리 컨테이너이기도 하다.
* 빈을 싱글톤으로 만드는 것은 결국 오브젝트의 생성방법을 제어하는 IoC 컨테이너로서의 역할 (라이프 사이클 관리)
* 싱글톤이기 때문에 주의해야 할 점
  * Thread safe 하지않다. 멤버 변수등 여러 스레드가 공유하는 값은 불변으로 사용해야한다.
* 싱글톤스코프 외의 스코프는?
  * 프로토타입 스코프 : 싱글톤스코프와는 다르게 컨테이너에 빈을 요청할 때마다 매번 새로운 오브젝트를 만든다.
  * 리퀘스트 스코프 : 웹을 통해 새로운 Http 요청이 들어올 때마다 생성된다.
* 코드로 직접 싱글톤스코프 빈을 간단하게 확인해보자.

``
<bean id=“Test” class=“exam.Test”>
	<property name=“” value=“ \>
</bean>

public class Test {
  private String value;

  public Test(String value) {
    this.value = value;
  }
}
``

Bean 생성 후,  bean을 가져와서 확인하면 같은 객체라는 것을 알 수 있다.

``
	Object obj1 = getBean(“Test”, Test.class)
 	Object obj2 = getBean(“Test”, Test.class)

	System.out.println(obj1 == objj2); //true
``

## 결론
  * Bean은 scope 설정을 따로 해주지 않으면 기본적으로 SingleTon 오브젝트다.
  * 그렇기 때문에 객체의 멤버변수는 thread safe 하지 않기 때문에 주의해야한다.
  * private final 이용해서 멤버 변수는 불변 상태를 지켜줘야한다.

참고 :
  * https://m.blog.naver.com/PostView.nhn?blogId=sksk3479&logNo=221175889439&proxyReferer=https%3A%2F%2Fwww.google.com%2F
  * https://velog.io/@govlmo91/%EC%8A%A4%ED%94%84%EB%A7%81%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88%EA%B0%80-%EC%8B%B1%EA%B8%80%ED%86%A4%EC%9D%B8-%EC%9D%B4%EC%9C%A0 [스프링컨테이너가 대부분의 객체를 싱글톤으로 제공하는 이유]
