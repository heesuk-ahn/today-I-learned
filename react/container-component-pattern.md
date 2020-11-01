# [React] Container & Component 아키텍처 패턴

* `container/component` 아키텍쳐가 무엇일까?
* 리액트에서 Component 를 두가지로 나누는 것이다.
* 하나는 `Presentational Component`, 또 하나는 `Container Component`이다.
* 이렇게 나누는 이유는 재사용성과 유지보수성을 높이기 위함이다.
* React 에서 Component 는 `상태관리`, `DOM` 관리, `이벤트 관리` 등 다양한 역할을 하는 중요한
개념이다.
* 하지만, 이것을 단지 컴포넌트 하나에서 모두 한다는 것은 컴포넌트가 무거워지고 유지보수하기 복잡해질 수 있다.
* __그래서 그래서 Redux 를 개발한 Dan abramov 는 재사용성과 유지보수성에 초점을 두어 이러한 패턴을 발견하고 이를 사용하길 권하고 있다.__
* Presentational Component 와 Container Component 의 각각의 사용 목적과 개념은 다음과 같다.

## 프레젠테이션 컴포넌트 (Presentational Component)

* 프레젠테이션 컴포넌트(Presentational Component) 는 아래와 같은 성질을 갖는다.
  * __어떻게 보여지는 지를 책임진다.__
  * 내부에 Presentational Component 와 Container 컴포넌트 모두를 가질 수 있고, 대게 DOM 마크업 과 자체 스타일을 가진다.
  * this.props.children 을 통해 다른 컴포넌트 를 포함 하게끔 만들 수 있다.
  * 어플리케이션의 나머지 부분에 대해 아무런 의존성을 가지지 않는다. (예를 들면 Flux 의 Action 이나 Stroe)
   즉, 단독적인 Component 가 된다.
  * __데이터를 불러오거나 변경하는 작업은 Presentational Component 에서 작성하지 않는다.__
  * __데이터 및 데이터와 관련된 Callback 은 props 를 통해서 받는 작업만 한다.__
  * 상태(state) 는 UI 상태를 관리하기 위해서만 갖게된다.
  * state, LifeCycle, 성능 최적화가 필요없는 경우라면 Functional Component 로 작성된다.

## 컨테이너 컴포넌트(Container Component)

* 컨테이너 컴포넌트(Container Component) 는 아래와 같은 성질을 갖는다.
  * 어떻게 동작해야 할지를 책임진다.
  * __내부에 Presentational Component 와 Container 컴포넌트 모두를 가질 수 있지만,
   대게 전체를 감싸는 div를 제외하고 자체적인 DOM 마크업이나 스타일을 갖고 있지 않다.__
  * 데이터 및 데이터와 관련된 동작을 다른 Presentational Component 와 Container Component 에게 제공한다.
  * Flux 의 Action 을 호출하는 작업을 Container Component 에서 작성하며, 이 Callback 들은 다른 Presentational Component 에게 넘겨준다.
  * 주로 데이터 저장소로 활용되며 상태(state) 를 갖고 있는 경우가 많다.
  * 직접 작성되기 보다는 HOC(Higher-Order Components) 로 부터 생성되는 경우가 많다.

## 예제 살펴보기

* `react-boilerplate` 에서 제공되는 코드를 봐서 위의 각 컴포넌트 레이어의 코드를 직접 살펴보자.

```
<프레젠테이션 컴포넌트>

import React from 'react';

function H3(props) {
  return <h3 {...props} />;
}

export default H3;
```  

* 위의 간단한 프레젠테이션 컴포넌트를 보면 UI 에 대해서만 작성되어있는 것을 볼 수 있다.
* 데이터는 다른 컴포넌트에서 `props` 으로 주입되고 있다.

```
<컨테이너 컴포넌트>
/**
 * NotFoundPage
 *
 * This is the page we show when the user visits a url that doesn't have a route
 */

import React from 'react';
import { FormattedMessage } from 'react-intl';

import H1 from 'components/H1';
import messages from './messages';

export default function NotFound() {
  return (
    <article>
      <H1>
        <FormattedMessage {...messages.header} />
      </H1>
    </article>
  );
}
```

* 컨테이너 컴포넌트는 어떻게 동작되어야 하는지 비즈니스 로직들이 주로 들어가 있다.
* UI 부분을 프레젠테이션 컴포넌트에서 처리하고, 데이터를 주입하고, 어떤 화면들이 그려져야하는지
처리를 하는 것을 확인할 수 있다.

## 장점

* 관심사의 분리가 더 명확해진다 (view, 동작)
* 재 사용성을 높일 수 있다.
  * 완전히 다른 곳으로부터 온 여러 상태(state) 라 할지라도, 이를 표현하기 위해 같은 Presentational Component 사용할 수 있는데 이때, 각 상태를 나타내는 Container Component 를 만들어 이를 또 재사용 할 수 있다.

### 참고)

* https://medium.com/@dan_abramov/smart-and-dumb-components-7ca2f9a7c7d0#.4rmjqneiw
* https://blog.naver.com/backsajang420/221368885149
