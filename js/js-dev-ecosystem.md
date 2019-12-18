# [TIL] JS Dev ecosystem

* NPM / yarn
  * 자바의 메이븐 처럼 라이브러리 저장소
  * 성능 / 보안 이슈로 yarn 을 많이 사용하는 추세
  * package.json 으로 디펜던시 명시
    * name
    * private : 기업 내부에서 사용하는지 boolean 으로 명시
    * version
    * scripts : Node 명령어를 커스텀하게 넣을 수 있다
* Dependencies
  * 런타임에 실행되는 라이브러리들
* devDependencies
  * 빌드 할 때 사용되는 라이브러리들
* package install을 하고나면 node_modules 에 install 된 라이브러리 명시
  * package.json lock 을 통해서 버전 명시 / install cash를 한다.
  * 만약 lock 이 없다면, 새롭게 다시 라이브러리들을 install 한다.
* Babel
  * ES5 로 문법을 변경해주는 라이브러리
  * ES6 -> transpile -> ES5
  * Ex) (x, y) -> x + y ==> function (x,y) { return x + y }
  * Babel 을 통해서 js 문법을 오픈소스처럼 사용가능하다
    * Stage 0~4까지 존재
    * Stage 4 면 실제 js에 적용되어 배포된다
    * 이를 통해서 Babel을 통해서 아직 표준화되지 않은 문법들을 사용가능하다.
  * Polyfill : 없는 걸 채워준다.
  * Ex) Map object 가 생겼으나 하위 버전에서는 Map object 개념이 없으므로 채워줌.
* Lint
  * Code style 체크
  * js 를 잘못짜게되면 디버깅 할 때 어려움을 겪을 수 있다.
  * 그래서 미리 code style을 분석하고 추천해주는 Lint가 필요하다
  * Style 가이드로 에어비앤비와 구글 스타일이 있다.
  * Prettier : code formatter
* Bundlers (Front-end / Libraries)
  * Webpack
* Flow/TypeScript
  * Flow : facebook
  * TypeScript : micro soft
  * 자바스크립트를 정적 타입을 도입하여 사용 가능하다.
  * TypeScript
    * .ts파일
    * TypeScript를 컴파일하면 JS
    * TyprScript -> Compile -> Vanila JS
    * Js 기반의 다른 언어라고 생각하면 된다.
