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


  [1]: https://github.com/946898963/EditTextApplication/blob/master/app/src/main/res/mipmap-hdpi/have_focus.png?raw=true
  [2]: https://github.com/946898963/EditTextApplication/blob/master/app/src/main/res/mipmap-hdpi/lose_focus.png?raw=true

