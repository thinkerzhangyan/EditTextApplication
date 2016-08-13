# EditTextApplication
失去焦点时候搜索图标居中显示，获得焦点的时候搜索图标移动到左侧显示的EditText，同时当EditText不为空时显示一键清除图标,点击清除图标清除内容。

![此处输入图片的描述][1]

![此处输入图片的描述][2]

示例代码：

```
public class MainActivity extends AppCompatActivity {

    private IconCenterEditText iconCenterEditText_one ;
    private IconCenterEditText iconCenterEditText_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconCenterEditText_one = (IconCenterEditText) findViewById(R.id.et_search_one);

        iconCenterEditText_one.setOnEditTextListener(new IconCenterEditText.OnEditTextListener() {
            @Override
            public void onEnterKeyAction(View view) {
                Toast.makeText(MainActivity.this,"第一個EnterKey被点击了",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onHasFocusAction(View view) {
                Toast.makeText(MainActivity.this,"第一個得到焦点",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLostFocusAction(View view) {
                Toast.makeText(MainActivity.this,"第一個失去焦点",Toast.LENGTH_LONG).show();
            }

        });


        iconCenterEditText_two = (IconCenterEditText) findViewById(R.id.et_search_two);

        iconCenterEditText_two.setOnEditTextListener(new IconCenterEditText.OnEditTextListener() {
            @Override
            public void onEnterKeyAction(View view) {
                Toast.makeText(MainActivity.this,"第二個EnterKey被点击了",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onHasFocusAction(View view) {
                Toast.makeText(MainActivity.this,"第二個得到焦点",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLostFocusAction(View view) {
                Toast.makeText(MainActivity.this,"第二個失去焦点",Toast.LENGTH_LONG).show();
            }

        });

    }
}

```

附录参考链接

[Android自定义View示例(一)—带有删除按钮的EditText][6]
    
[【Android自定义控件】仿IOS风格的搜索框][7]
    
[Android中Bitmap和Drawable][8]
    
[求解答：android Drawable类的方法setBounds(int ,int, int, in ...][9]
    
[android Drawable setbounds()介绍][3]
    
[bitmap-setBounds方法参数研究][4]
    
[Canvas之translate、scale、rotate、skew方法讲解！][5]    




[1]: https://github.com/946898963/EditTextApplication/blob/master/app/src/main/res/mipmap-hdpi/have_focus.png?raw=true
[2]: https://github.com/946898963/EditTextApplication/blob/master/app/src/main/res/mipmap-hdpi/lose_focus.png?raw=true
[3]: http://blog.csdn.net/love_xsq/article/details/45306157
[4]: http://www.cnblogs.com/zhangshuli-1989/p/zhangshuli_setbound_15526135.html
[5]: http://blog.csdn.net/tianjian4592/article/details/45234419
[6]: http://blog.csdn.net/lfdfhl/article/details/18923079
[7]: http://blog.csdn.net/zhuyb829/article/details/46430989
[8]: http://dyh7077063.iteye.com/blog/970672
[9]: http://bbs.51cto.com/thread-1015195-1.html

