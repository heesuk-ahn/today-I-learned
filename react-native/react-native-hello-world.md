# [React-Native] Hello World 출력하기

```
import React, { Component } from 'react';

/*

- react-natvie 에서 View 와 Text 를 로드해야 화면에 글자를 띄울 수 있다.
- style 적용을 위해서는 StyleSheet 를 이용한다. (css 처럼 사용가능)

 */
import { View, Text, StyleSheet } from 'react-native';


// React의 Component 를 상속받아서 View 객체를 렌더링한다.
class App extends Component {

  render() {
    return (
      // inline style 을 직접적으로 넣어줄 수 있다.
      <View style={styles.backgroud}>
        <Text> Hello World </Text>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  backgroud: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center'
  }
})

export default App;
```
