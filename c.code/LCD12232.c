#include<reg51.h>
#define uchar unsigned char
#define uint unsigned int
#define PD1 61 //二分屏

sbit k1=P1^0;//定义按键k1停止计时
sbit k2=P1^1;//定义按键k2小时加一
sbit k3=P1^6;//定义按键k3分钟加一
sbit k4=P1^7;//定义按键k4确认

uchar Page_;//页地址寄存器
uchar Code_;//字符代码寄存器
uchar Command;//指令寄存器
uchar LCDData;//数据寄存器
uchar Column;//列地址

//uint ssec=0,sec=55,min=59,hor=23;
uint ssec=0,sec=0,min=0,hor=0;
uchar DisplayData[14];

xdata uchar CWADD1 _at_ 0x8000;//定义数据以及指令地址
xdata uchar DWADD1 _at_ 0x8001;
xdata uchar CRADD1 _at_ 0x8002;
xdata uchar DRADD1 _at_ 0x8003;
xdata uchar CWADD2 _at_ 0x8004;
xdata uchar DWADD2 _at_ 0x8005;
xdata uchar CRADD2 _at_ 0x8006;
xdata uchar DRADD2 _at_ 0x8007;

//定义字符内码值表
code uchar Tab[][8]=
{
    {0x00,0x3E,0x51,0x49,0x45,0x3E,0x00,0x00},//0
    {0x00,0x00,0x42,0x7F,0x40,0x00,0x00,0x00},//1
    {0x00,0x62,0x51,0x49,0x49,0x46,0x00,0x00},//2
    {0x00,0x21,0x41,0x49,0x4D,0x33,0x00,0x00},//3
    {0x00,0x18,0x14,0x12,0x7F,0x10,0x00,0x00},//4
    {0x00,0x27,0x45,0x45,0x45,0x39,0x00,0x00},//5
    {0x00,0x3C,0x4A,0x49,0x49,0x31,0x00,0x00},//6
    {0x00,0x01,0x71,0x09,0x05,0x03,0x00,0x00},//7
    {0x00,0x36,0x49,0x49,0x49,0x36,0x00,0x00},//8
    {0x00,0x46,0x49,0x49,0x29,0x1E,0x00,0x00},//9
    {0x00,0x24,0x54,0x54,0x38,0x40,0x00,0x00},//A//小写
    {0x00,0x7F,0x28,0x44,0x44,0x38,0x00,0x00},//B
    {0x00,0x38,0x44,0x44,0x44,0x08,0x00,0x00},//C
    {0x00,0x38,0x44,0x44,0x28,0x7F,0x00,0x00},//D
    {0x00,0x38,0x54,0x54,0x54,0x08,0x00,0x00},//E
    {0x00,0x08,0x7E,0x09,0x09,0x02,0x00,0x00},//F
    {0x00,0x98,0xA4,0xA4,0xA4,0x78,0x00,0x00},//G 10
    {0x00,0x7F,0x08,0x04,0x04,0x78,0x00,0x00},//H 11
    {0x00,0x00,0x00,0x79,0x00,0x00,0x00,0x00},//I 12
    {0x00,0x00,0x80,0x88,0x79,0x00,0x00,0x00},//J 13
    {0x00,0x7F,0x10,0x28,0x44,0x40,0x00,0x00},//K 14
    {0x00,0x00,0x41,0x7F,0x40,0x00,0x00,0x00},//L 15
    {0x00,0x78,0x04,0x78,0x04,0x78,0x00,0x00},//M 16
    {0x00,0x04,0x78,0x04,0x04,0x78,0x00,0x00},//n 17
    {0x00,0x38,0x44,0x44,0x44,0x38,0x00,0x00},//O 18
    {0x00,0xFC,0x24,0x24,0x24,0x18,0x00,0x00},//P 19
    {0x00,0x18,0x24,0x24,0x24,0xFC,0x00,0x00},//Q 1a
    {0x00,0x04,0x78,0x04,0x04,0x08,0x00,0x00},//R 1b
    {0x00,0x48,0x54,0x54,0x54,0x24,0x00,0x00},//S 1c
    {0x00,0x04,0x3F,0x44,0x44,0x24,0x00,0x00},//T 1d
    {0x00,0x3C,0x40,0x40,0x3C,0x40,0x00,0x00},//U 1e
    {0x00,0x1C,0x20,0x40,0x20,0x1C,0x00,0x00},//V 1f
    {0x00,0x3C,0x40,0x3C,0x40,0x3C,0x00,0x00},//W 20
    {0x00,0x44,0x28,0x10,0x28,0x44,0x00,0x00},//X 21
    {0x00,0x9C,0xA0,0xA0,0x90,0x7C,0x00,0x00},//Y 22
    {0x00,0x44,0x64,0x54,0x4C,0x44,0x00,0x00},//Z 23
    {0x00,0x00,0x36,0x36,0x00,0x00,0x00,0x00},//: 24
    {0x00,0x60,0x60,0x00,0x00,0x00,0x00,0x00},//. 25
    {0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00}
};// 空 删除用26

void Delay(uchar i)
{
    while(i--);
}

void Timer0Init()
{
    TMOD|=0X01;//选择为定时器0模式，工作方式1，仅用TR0打开启动。
    TH0=0Xd8;	//给定时器赋初值，定时10ms
    TL0=0Xf0;
    ET0=1;//打开定时器0中断允许
    EA=1;//打开总中断
    TR0=1;//打开定时器
    PT0=0;
}

void Datapros()
{
    DisplayData[0]=0x1d;
    DisplayData[1]=0x12;
    DisplayData[2]=0x16;
    DisplayData[3]=0x0e;
    DisplayData[4]=0x26;
    DisplayData[5]=0x26;
    //显示time:五个字符
    DisplayData[6]=hor/10;
    DisplayData[7]=hor%10;
    DisplayData[8]=0x24;
    DisplayData[9]=min/10;
    DisplayData[10]=min%10;
    DisplayData[11]=0x24;
    DisplayData[12]=sec/10;
    DisplayData[13]=sec%10;
    //分别计算时分秒显示
}

void WriteDataE1()
{
    while(CRADD1&0x80);	//判忙  1忙 0等
    DWADD1=LCDData;
}

void WriteDataE2()
{
    while(CRADD2&0x80);
    DWADD2=LCDData;
}

void WriteCommandE1()
{
    while(CRADD1&0x80);
    CWADD1=Command;
}

void WriteCommandE2()
{
    while(CRADD2&0x80);
    CWADD2=Command;
}

void LCDinit()//初始化
{
    Command=0xe2; //复位
    WriteCommandE1();
    WriteCommandE2();

    Command=0xa4; //静态驱动
    WriteCommandE1();
    WriteCommandE2();

    Command=0xa9; //刷新率1/32
    WriteCommandE1();
    WriteCommandE2();

    Command=0xa1; //ADC
    WriteCommandE1();
    WriteCommandE2();

    Command=0xc0; //行
    WriteCommandE1();
    WriteCommandE2();

    Command=0xaf; //开显示
    WriteCommandE1();
    WriteCommandE2();
}

void Clear()//清屏
{
    uchar i,j;
    i=0;
    do
    {
        Command=(i+0xb8);
        WriteCommandE1();
        WriteCommandE2();

        Command=0x00;
        WriteCommandE1();
        WriteCommandE2();

        j=0x50;//页内八十列
        do
        {
            LCDData=0x00;
            WriteDataE1();
            WriteDataE2();
        }
        while(--j!=0);
    }
    while(++i!=4);//是否四页全部完成
}

void WriteEnglish()
{
    uchar i,j,k;
    i=0;
    j=0;
    while(j<1)
    {
        Command=((Page_+j)&0x03)|0xb8;
        WriteCommandE1();
        WriteCommandE2();
        k=Column;
        while(k<Column+8)
        {
            if(k<PD1)//写左屏
            {
                Command=k;
                WriteCommandE1();
                LCDData=Tab[Code_][i];
                WriteDataE1();
            }
            else//写右屏
            {
                Command=k-PD1;
                WriteCommandE2();
                LCDData=Tab[Code_][i];
                WriteDataE2();
            };
            i++;
            if(++k>=PD1*2)break;
        };
        j++;
    };
}

void Show()
{
    Page_=0x02;//在第二页显示

    Column=0x00;
    Code_=DisplayData[0];
    WriteEnglish();

    Column=0x08;
    Code_=DisplayData[1];
    WriteEnglish();

    Column=0x10;
    Code_=DisplayData[2];
    WriteEnglish();

    Column=0x18;
    Code_=DisplayData[3];
    WriteEnglish();

    Column=0x20;
    Code_=DisplayData[4];
    WriteEnglish();

    Column=0x28;
    Code_=DisplayData[5];
    WriteEnglish();

    Column=0x30;
    Code_=DisplayData[6];
    WriteEnglish();

    Column=0x38;
    Code_=DisplayData[7];
    WriteEnglish();

    Column=0x40;
    Code_=DisplayData[8];
    WriteEnglish();

    Column=0x48;
    Code_=DisplayData[9];
    WriteEnglish();

    Column=0x50;
    Code_=DisplayData[10];
    WriteEnglish();

    Column=0x58;
    Code_=DisplayData[11];
    WriteEnglish();

    Column=0x60;
    Code_=DisplayData[12];
    WriteEnglish();

    Column=0x68;
    Code_=DisplayData[13];
    WriteEnglish();

    Column=0x70;
    Code_=0x26;
    WriteEnglish();
}

void Time()	//K2 hor  K3 min k4 ok
{
    while(1)
    {
        if(k2==0)		  //检测按键K2是否按下
        {
            Delay(1000);   //消除抖动 一般大约10ms
            if(k2==0)	 //再次判断按键是否按下
            {
                hor+=1;
                if(hor==24)
                    hor=0;
            }
            while(!k2);	 //检测按键是否松开
        }
        if(k3==0)		  //检测按键K3是否按下
        {
            Delay(1000);   //消除抖动 一般大约10ms
            if(k3==0)	 //再次判断按键是否按下
            {
                min+=1;
                if(min==60)
                    min=0;
            }
            while(!k3);	 //检测按键是否松开
        }
        if(k4==0)//检测按键K4是否按下
        {
            while(!k4);	 //检测按键是否松开
            TR0=1;
            break;
        }
        Datapros();
        Show();
    }
}

void main()
{
    Timer0Init();  //定时器0初始化
    LCDinit();    //初始化
    Clear();	   //清屏
    while(1)
    {
        Datapros();
        Show();
        if(k1==0)		  //检测按键K1是否按下
        {
            Delay(1000);	//延时消抖
            if(k1==0)
            {
                while(!k1);	 //检测按键是否松开
                //light();
                TR0=0;
                Time();	 	 //调时间函数
                Timer0Init();  //定时器0初始化
            }
        }
    }
}

void Timer0() interrupt 1
{
    TH0=0Xd8;	//给定时器赋初值，定时10ms
    TL0=0Xf0;
    ssec++;
    if(ssec>=10)  //1sec
    {
        ssec=0;
        sec++;
        if(sec>=60) //1min
        {
            sec=0;
            min++;
            if(min>=60) //1hor
            {
                min=0;
                hor++;
                if(hor>23) //day
                {
                    hor=0;
                }
            }
        }
    }
}
