#### answer7(a)

```java
public <T> int findPos(T x)const
    {
        int offset = 1;
        int currentPos = myhash(x);
        int firstPos = currentPos;
         //循环结束时，firstPos为第一个inactive值或者active（值为x)
        while(array[firstPos].info == ACTIVE && array[firstPos].element!= x)
        {
            firstPos += offset;
            offset += 2;

            if(firstPos >= array.size())
                firstPos -= array.size();
        }

        currentPos = firstPos;
        //循环结束时currentPos的状态为empty或者deleted（值为x）或者active（值为x）
        while(array[currentPos].info != EMPTY
              &&array[currentPos].element != x)
        {

            currentPos += offset;
            offset += 2;

            if(currentPos >= array.size())
            {
                currentPos -= array.size();
            }
        }
        //找到active（值为x)时，返回currentPos，否则返回第一个inactive位置
        if(array[currentPos].info == ACTIVE)
            return currentPos;
        else
            return firstPos;

    }

```

