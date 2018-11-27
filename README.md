## 并行算法实验
1. 目前完成了快排，归并，枚举3中串行算法以及枚举的并行算法。数据量为30000;
2. 比较结果如下:<br>
![image](https://github.com/czhnju161220026/image/blob/master/para.jpg?raw=true)
可以发现:并发的枚举排序效率有了极大提高，加速比为3-4,但是并发的快排和归并受限于PC的CPU核数，需要在核数更多的CPU上进行重新测试！