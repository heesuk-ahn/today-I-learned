package etc;

import java.util.ArrayList;
import java.util.HashMap;

public class HashPort {

  public int modeHash(int packetId, int numberOfPorts) {
    return packetId % numberOfPorts;
  }

  public int[] sentTime(int numberOfPorts, int transmissionTime, int[] packetIds) {
    HashMap<Integer, Integer> accessiblePortTable = new HashMap<>();
    ArrayList<Integer> ans = new ArrayList();
    int length = packetIds.length;

    for(int i=0; i<length; i++) {
      int nowTime = i;
      int sendPortNumber = modeHash(packetIds[i], numberOfPorts); //순환 포트

      Integer timeLimit = accessiblePortTable.get(sendPortNumber);
      //port는 순회 해야한다.
      if(timeLimit == null) {  //사용중이지 않은 포트
        accessiblePortTable.put(sendPortNumber, i + transmissionTime);
        ans.add(sendPortNumber);
      } else if (timeLimit != null && timeLimit < nowTime) { //사용중이지만, 사용가능 상태
        accessiblePortTable.put(sendPortNumber, i + transmissionTime);
        ans.add(sendPortNumber);
      } else { //사용 불가능한 port
        int nextPort = (sendPortNumber + 1) % numberOfPorts;
        timeLimit =  accessiblePortTable.get(nextPort);
        int count = numberOfPorts-1;
        boolean notAccessiblePort = timeLimit != null && timeLimit >= nowTime;

        //접근 가능한 port를 찾을 때 까지 순환.
        while(notAccessiblePort) {
          if (count < 0) break; //모두 꽉 차있다면, break해서 버린다.
          nextPort = (sendPortNumber + 1) % numberOfPorts;
          timeLimit =  accessiblePortTable.get(nextPort);
          notAccessiblePort = timeLimit != null && timeLimit > nowTime;
          count--;
        }

        //모두 꽉 차있는 경우가 아니라면, 포트에 추가. 아니면 버린다.
        if(count > 0) {
          accessiblePortTable.put(sendPortNumber, i + transmissionTime);
          ans.add(sendPortNumber);
        }
      }

    }

    int[] finalAns = new int[ans.size()];
    int index = 0;

    for(int number: ans) {
      finalAns[index++] = number;
    }

    return finalAns;
  }

  public static void main(String[] args) {
   // 1<= x <= 2000 패킷의 갯수가 2000보다 작으니까. 시간복잡도는 좀 여유있는듯
    int numberOfPorts = 10;
    int transmissionTime = 2;
    int[] packetIds = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    int[] ans = new HashPort().sentTime(numberOfPorts, transmissionTime, packetIds);

    //ans는 packet이 어디로 전송되었는지 포트 번호이다.
    for(int a: ans) {
      System.out.println(a);
    }
  }

}
