# [React-native] Props 의 사용

* props 을 사용하여 자식 컴포넌트에게 값을 전달한다.

```
// App.js

import React, { Component } from 'react';

/*

(KNOW-POINT)

- props 를 이용하여 자식 컴포넌트에게 값을 전달할 수 있다.

 */
import { View, Text, StyleSheet } from 'react-native';
import PropsChild from './propChild';

class App extends Component {

  state = {
    sampleText: 'HELLO, WORLD',
    sampleBoolean: false
  }

  inputText = () => (
    this.state.sampleBoolean ? <Text>sample Boolean true</Text> : <Text>sample Boolean false</Text>
  )

  changeState = () => {
    // state의 변경은 setState 로 해야한다.
    if (this.state.sampleBoolean) {
      this.setState(
        {
          sampleText: 'sample boolean state true',
          sampleBoolean: false
        })
    } else {
      this.setState(
        {
          sampleText: 'sample boolean state false',
          sampleBoolean: true
        })
    }
  }

  render() {
    /*
      - PropsChild 에 prop 을 이용하여 값을 넘긴다
    */
    return (
      <View style={styles.backgroud}>
        <PropsChild sampleText={this.state.sampleText} changeState={this.changeState} />
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

```
// PropsChild.js

import React from 'react'
import { View, Text } from 'react-native'

const PropsChild = (props) => {
    /*
        - props object 에는 parent component 로 부터 넘어온 값들을
        받을 수 있다.
    */

    appendText = sampleText => {
        return sampleText + " IN CHILD"    
    }

    return (
        <View>
            <Text onPress={props.changeState}>
                {this.appendText(props.sampleText)}
            </Text>
        </View>
    )
}

export default PropsChild;
```
