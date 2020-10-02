# [React-Native] TouchableOpacity 사용하기

* TouchableOpacity 를 사용하여 클릭 이벤트를 처리할 수 있다.

```
<Header.js>

import React, { Component } from 'react';
import { View, Text, TouchableOpacity} from 'react-native';

const Header = (props) => (
  <TouchableOpacity
    style={styles.headerStyle}
    // onPressLong={() => alert('hello world')} // 오래 눌러야 반응
    // onPressIn={() => alert('hello world')} // 눌렸을 때 즉각적으로 반응
    onPressOut={() => alert('hello world')} //터치했다가 버튼을 놓았을 때 이벤트 발생
  >
    <View>
      <Text>
        {props.name}
      </Text>
    </View>
  </TouchableOpacity>
)

const styles = {
 headerStyle: {
   backgroundColor: 'pink',
   width: '100%',
   alignItems: 'center',
   padding: 5
 }
}

export default Header;
```

```
<App.js>

import React, { Component } from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import Header from './src/Header.js'

class App extends Component {

  state = {
    appName: 'My First App'
  }

  // Touch Event 는 TouchableOpacity 를 이용하거나 또는 Text 는 onPress 로 추가 가능하다.
  render() {
    return (
      <View style={styles.mainViewStyle}>
        <Header name={this.state.appName}></Header>
        <Text onPress={() => alert('text touch')} style={styles.mainText}>
          Hello World
        </Text>
      </View>
    )
  }

}

const styles = StyleSheet.create({
  mainViewStyle: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  mainText: {
    color: 'white',
    backgroundColor: 'blue'
  }
})

export default App;
```
