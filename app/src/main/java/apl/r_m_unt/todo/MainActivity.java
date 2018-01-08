package apl.r_m_unt.todo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

//    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction
                    = fragmentManager.beginTransaction();

            // Fragmentの切り替え
            // TODO
            // 各画面の状態は維持しておきたい場合などはViewPagerと連動させたりShow/Hideで切り替えることを選ぶと良さそう。
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);

                    LimitRegisterFragment limitRegisterFragment = new LimitRegisterFragment();
                    fragmentTransaction.replace(android.R.id.content, limitRegisterFragment);

                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);

                    ScheduleFragment scheduleFragment = new ScheduleFragment();
                    fragmentTransaction.replace(android.R.id.content, scheduleFragment);

                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);

                    TodoRegisterFragment todoRegisterFragment = new TodoRegisterFragment();
                    fragmentTransaction.replace(android.R.id.content, todoRegisterFragment);

                    fragmentTransaction.commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction
                = fragmentManager.beginTransaction();

        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.replace(android.R.id.content, mainFragment);

        fragmentTransaction.commit();


      //  mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
