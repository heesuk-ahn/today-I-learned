# Archaius의 Dynamic Properties Validation

* 어플리케이션들은 dynamic property 값들의 유효성 검사 규칙을 정의할 수 있다.
* 만약에 유효성 검사가 Dynamic property 를 업데이트 하는 동안 실패한다면,
`RunTimeException` 이 던져지고, `Dynamic Property`는 변경되지 않고 유지된다.
* 다음은 어플리케이션에서 `DynamicProperty`가 음수가 아니여야 하는 경우의 유효성 검사
예제이다.

```
DynamicIntProperty validatedProp = new DynamicIntProperty("abc", 0) {
            @Override
            public void validate(String newValue) {
                if (Integer.parseInt(newValue) < 0) {
                    throw new ValidationException("Cannot be negative");
                }
            }
        };
```


### 참고 링크

* https://github.com/Netflix/archaius/wiki/Dynamic-properties-validation
