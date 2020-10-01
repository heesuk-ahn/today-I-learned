# [React-Native] State 의 사용

```
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, { Component } from 'react';

/*

(KNOW-POINT)

- react-natvie 에서 View 와 Text 를 로드해야 화면에 글자를 띄울 수 있다.
- style 적용을 위해서는 StyleSheet 를 이용한다. (css 처럼 사용가능)
- state 를 조작할 수 있다

 */
import { View, Text, StyleSheet } from 'react-native';


// React의 Component 를 상속받아서 View 객체를 렌더링한다.
class App extends Component {

  /*
  - state 오브젝트에 변수를 할당해서 값을 가져와서 사용할 수 있다
  - state 는 직접 수정될 수 없다
    ex) func 내부에서 수정하면 갱신될 때 업데이트가 되지 않음
        this.state.sampleText = '변경 시도' (x!!)      
  - 그렇기 때문에 생성자에 state를 초기화 해주고, 변화가 생길때마다, setState를 불러 업데이트 해주어야한다.

  */
  state = {
    smapleText: 'HELLO, WORLD',
    sampleBoolean: false,
    sampleNum: 0
  }

  inputText = () => (
    this.state.sampleBoolean ? <Text>sample Boolean true</Text> : <Text>sample Boolean false</Text>
  )

  changeState = () => {
    // state의 변경은 setState 로 해야한다.
    if (this.state.sampleBoolean) {
      this.setState(
        {
          smapleText: 'sample boolean state true',
          sampleBoolean: false
        })
    } else {
      this.setState(
        {
          smapleText: 'sample boolean state false',
          sampleBoolean: true
        })
    }
  }

  onAdd = () => {
    /*
        - 단순하게 생각하면 아래와 같이 state value 에 +1 을 더할 수 잇을 것 같지만, smapleNum 값을 인식하지 못하고 에러가 난다
        ex) smapleNum: sampleNum + 1
        - 이를 방지하기 위해서 prevState 를 받아서 처리해주어야 한다.
      */
    this.setState(prevState => {
      return {
        sampleNum: prevState.sampleNum + 1
      }
    })
  }

  render() {
    return (
      /*
        - Event Hanlder 를 onPress 를 통해서 넣을 수 있다.
      */  
      <View style={styles.backgroud}>
        <Text onPress={this.changeState}>
          {this.state.smapleText}
        </Text>
        <Text onPress={this.onAdd}>
          {this.state.sampleNum}
        </Text>
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
