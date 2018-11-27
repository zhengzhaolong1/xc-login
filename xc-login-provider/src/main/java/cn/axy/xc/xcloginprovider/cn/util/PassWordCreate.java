package cn.axy.xc.xcloginprovider.cn.util;

import java.util.Random;

public class PassWordCreate {


    public int createRandomInt(){
        //得到0.0到1.0之间的数字，并扩大100000倍
        double temp = Math.random()*100000;
        //如果数据等于100000，则减少1
        if(temp>=100000){
            temp = 99999;
        }
        //lua中的一个函数,math.ceil(x)返回大于参数x的最小整数,即对浮点数向上取整.
        int tempint = (int)Math.ceil(temp);
        return tempint;
    }


    public String createPassWord(int random,int len){
        Random rd = new Random(random);
        final int  maxNum = 62;
        StringBuffer sb = new StringBuffer();
        int rdGet;//取得随机数
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', 'A','B','C','D','E','F','G','H','I','J','K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y' ,'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        int count=0;
        while(count < len){
            //该方法返回x的绝对值 但也有例外
            // https://blog.csdn.net/xmc281141947/article/details/56017147
            //生成的数最大为62-1
            rdGet = Math.abs(rd.nextInt(maxNum));
            if (rdGet >= 0 && rdGet < str.length) {
                sb.append(str[rdGet]);
                count ++;
            }
        }
        return sb.toString();
    }

    /**
     * 获得密码
     * @param len 密码长度
     * @return
     */
    public String createPassWord(int len){
        int random = this.createRandomInt();
        return this.createPassWord(random, len);
    }



//    public static void main(String[] args){
//        PassWordCreate pwc = new PassWordCreate();
//        System.out.println(pwc.createPassWord(21));
//    }


}
