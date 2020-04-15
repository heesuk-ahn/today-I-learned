# kustomize `namePrefix` 와 `nameSuffix`

* kustomize 을 사용할 때, 때로는 service name 을 다르게 설정해야 할 수 있다.
* kustomize 은 이를 위해서 `namePrefix` 와 `nameSuffix` 를 제공한다.
* kustomize 의 `namePrefix`와 `nameSuffix`를 이용하면 `resources` 에 선언된
모든 yml에 name에 prefix 또는 suffix 를 달게 된다.

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-nginx <--- name 에 앞과 뒤에 값을 붙여서 구분하고 싶다.
.
.
```

* 위와 같은 `Deployment` 가 존재한다고 할 때, `namePrefix` 와 `nameSuffix`는 아래와 같이
kustomize 템플릿을 만들어서 변경이 가능하다.

```
namePrefix: dev- //원하는 prefix 값
nameSuffix: "-001" //원하는 suffix 값

resources:  // 재료가 되는 yaml 들을 설정한다.
- deployment.yaml
- service.yaml
```

* `kubectl kustomize ./` 을 실행하면, 컨테이너에 prefix, suffix name 이 설정된 것을
확인할 수 있다.

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: dev-my-nginx-001
.
.
```
