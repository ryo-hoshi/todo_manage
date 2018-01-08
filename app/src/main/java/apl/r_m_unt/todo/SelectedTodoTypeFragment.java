package apl.r_m_unt.todo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class SelectedTodoTypeFragment extends ListFragment {

    private static final String TAG = "SelectedTodoTypeFragmen";

    private TodoControl todoControl = new TodoControl();
    private TodoInfoAdapter todoInfoAdapter = null;
    private List<TodoInfo> todoInfo;

    private ListView todoListView;

    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        // FragmentのレイアウトをViewとして作成します
        return inflater.inflate(R.layout.fragment_main_todo_type, container, false);
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO todoTypeを使う場所がなければ削除
        TodoType todoType = TodoType.getInstance();

        // 選択したTODO種別のTODOリスト
        todoListView = (ListView) view.findViewById(R.id.listview_todoList);
        Bundle bundle = getArguments();
        todoInfo = Arrays.asList((TodoInfo[])bundle.getSerializable(MainFragment.BUNDLE_TODO_INFO));

        // TODO種別毎に集計したTodo一覧をListViewに作成
        todoInfoAdapter = new TodoInfoAdapter(getActivity(), 0, todoInfo);
        setListAdapter(todoInfoAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, TAG+" onResume起動");


    }

    /*
     * アイテムのクリック
     *
     * @see
     * android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView
     * , android.view.View, int, long)
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(TAG, "onListItemClick position => " + position + " : id => " + id);

        // タップした行の情報を取得
        TodoInfo selected = todoInfo.get(position);

        // 選択したTODOのFragmentを設定
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction
                = fragmentManager.beginTransaction();

        SelectedTodoTypeFragment mainFragment = new SelectedTodoTypeFragment();
        fragmentTransaction.replace(android.R.id.content, mainFragment);

        fragmentTransaction.commit();
    }

    /**
     * Todo種別ごとのTODO一覧表示用
     */
    public class TodoInfoAdapter extends ArrayAdapter<TodoInfo> {

        // レイアウトxmlファイルからIDを指定してViewを使用する
        private LayoutInflater mLayoutInflater;

        public TodoInfoAdapter(Context context, int resourceId, List<TodoInfo> objects) {
            super(context, resourceId, objects);
            // getLayoutInflater()メソッドはActivityじゃないと使えない
            mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // getView()メソッドに各行を表示しようとした時に呼ばれる処理を設定
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            // 特定行(position)のデータを得る
            TodoInfo item = (TodoInfo)getItem(position);
            // convertViewは使いまわされている可能性があるのでnullの時だけ新しく作る
            if (null == rowView) rowView = mLayoutInflater.inflate(R.layout.todo_list, null);

            // 登録内容
            TextView textViewTodoType = (TextView)rowView.findViewById(R.id.textView_todoTypeSumTodoType);
            textViewTodoType.setText(item.getTodo());
            // 完了フラグ
            TextView textViewTodoNum = (TextView)rowView.findViewById(R.id.textView_todoTypeSumTodoNum);
            textViewTodoNum.setText(item.getIsComplete());

            return rowView;
        }
    }
}
