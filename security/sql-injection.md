# SQL Injection

## 개요

* SQL 인젝션(SQL 삽입, SQL 주입으로도 불린다)은 코드 인젝션의 한 기법으로 클라이언트의 입력값을 조작하여 서버의 데이터베이스를 공격할 수 있는 공격방식을 말한다.
* 공격의 쉬운 난이도에 비해 파괴력이 어마어마하기 때문에 시큐어 코딩을 하는 개발자라면 가장 먼저 배우게 되는 내용이다.

## 공격 방법

* 로그인 폼에 아이디와 비밀번호를 입력하면 입력한 값이 서버로 넘어가고, 데이터베이스를 조회하여 정보가 있다면 로그인에 성공하게 된다. 이때 데이터베이스에 값을 조회하기 위해 사용되는 언어를 SQL이라고 하며, SQL문이 다음과 같이 작성되었다고 가정하자.

```
SELECT user FROM user_table WHERE id='입력한 아이디' AND password='입력한 비밀번호';
```

* 일반적인 유저라면 이렇게 로그인할 것이다.

```
아이디: hello
비밀번호: foo
```

```
SELECT user FROM user_table WHERE id='hello' AND password='foo';
```

* 그리고 SQL injection을 시도하려는 공격자가 다음과 같이 입력했다.

```
아이디: admin
비밀번호: ' OR '1' = '1
```

```
SELECT user FROM user_table WHERE id='admin' AND password=' ' OR '1' = '1';
```

* 비밀번호 입력값과 마지막 구문을 자세히 살펴보자.
* 따옴표를 올바르게 닫으며 password=' '를 만듦과 동시에 SQL 구문 뒤에 OR '1' = '1'을 붙였다.
* password는 OR 연산으로 인해 true 가 되므로 로그인에 성공하게된다.
* 로그인뿐만 아니라 JOIN이나 UNION 같은 구문을 통해 공격자가 원하는 코드를 실행할 수도 있다.

```
[SQL Injection 이 통하던 이유]

String query = "SELECT account_balance FROM user_data WHERE user_name = "
   + request.getParameter("customerName");

 try {
     Statement statement = connection.createStatement( … );
     ResultSet results = statement.executeQuery( query );
 }
```

* 과거에는 위에 예시처럼 직접 쿼리문을 파라미터 검증 없이 받은 후 파라미터를 바로 쿼리문에 붙여서
생성하는 방식이 있어서 특히나 SQL Injection에 취약했다.

## 방어 방법

* 아마도 XSS와 상당 부분 겹치겠지만 __기본적으로 유저에게 받은 값을 직접 SQL로 넘기면 안 된다.__
* 요즘에 쓰이는 거의 모든 데이터베이스 엔진은 유저 입력이 의도치 않은 동작을 하는 걸 방지하는 __escape 함수와 prepared statement[4]를 제공한다.__
  * prepared statement : 사전에 미리 쿼리를 컴파일하고 변수만 받는 방식.
