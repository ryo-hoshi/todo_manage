package apl.r_m_unt.todo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import android.support.v4.app.Fragment;


// Fragmentクラスを継承します
public class TodoRegisterFragment extends Fragment {

//    private TextView mTextView;
    private Spinner limitSpinner;
    private Button registButton;
    private TextView todoText;

    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        // FragmentのレイアウトをViewとして作成します
        return inflater.inflate(R.layout.fragment_todo_register, container, false);
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        // TextViewをひも付けます
//        mTextView = (TextView) view.findViewById(R.id.textView);
//        // Buttonのクリックした時の処理を書きます
//        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mTextView.setText(mTextView.getText() + "!");
//            }
//        });
        limitSpinner = (Spinner)view.findViewById(R.id.todo_register_spinner_limit);
        registButton = (Button)view.findViewById(R.id.todo_register_button_regist);
        todoText = (TextView)view.findViewById(R.id.todo_register_todo);

        // ■■■期限種別のスピナーを設定■■■
        // 期限種別のダミー値
        String[] limitType = {"今日中","明日まで","今週中","今月中","今年中","いつか"};
        // ArrayAdapterインスタンスを取得
        ArrayAdapter<String> adapterLimitType = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, limitType);

        // ドロップダウンリストのレイアウトを設定
        adapterLimitType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // スピナーの表示内容を設定
        limitSpinner.setAdapter(adapterLimitType);

        // 登録ボタン押下時に呼び出されるコールバックリスナー
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectLimit = (String)limitSpinner.getSelectedItem();
                String item = (String)limitSpinner.getSelectedItem();
                String todo = (String)todoText.getText();
                // デバッグ
                Toast.makeText(getActivity(), "期限:"+selectLimit+" todo:"+todo+"を登録", Toast.LENGTH_LONG).show();
            }
        });
    }
}
