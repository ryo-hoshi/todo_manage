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

import java.util.List;

//import android.support.v4.app.ListFragment;

//import android.support.v4.app.Fragment;


// Fragmentクラスを継承します
public class MainFragment extends ListFragment {

    public static final String BUNDLE_TODO_INFO = "BUNDLE_TODO_INFO";
    private static final String TAG = "MainFragment";

    private TodoControl todoControl = new TodoControl();
    private TodoTypeSumAdapter todoTypeSumAdapter = null;
    private List<TodoTypeSummary> todoTypeSummary;

    private ListView todoTypeListView;

    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        // FragmentのレイアウトをViewとして作成します
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO todoTypeを使う場所がなければ削除
        TodoType todoType = TodoType.getInstance();
        todoTypeListView = (ListView) view.findViewById(R.id.listview_todoTypeList);
        //todoListView = getListView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, TAG+" onResume起動");

        // TODO集計リストを取得するのはここでよいか？バックグランドから復帰したときに自動で再取得になる
        // TODO種別集計リストを取得
        todoTypeSummary = todoControl.getTodoTypeSummary();
        // TODO種別毎に集計したTodo一覧をListViewに作成
        todoTypeSumAdapter = new TodoTypeSumAdapter(getActivity(), 0, todoTypeSummary);
        setListAdapter(todoTypeSumAdapter);

    }

    /*
     * アイテムのクリック
     * タップしたTODO種別のTODO一覧のFragmentに遷移させる
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Log.d(TAG, "onListItemClick position => " + position + " : id => " + id);

        // タップした行の情報を取得
        TodoTypeSummary selected = todoTypeSummary.get(position);

        // 選択したTODOのFragmentを設定
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction
                = fragmentManager.beginTransaction();

        String todoType = selected.getTodoType();
        List<TodoInfo> todoInfo = todoControl.getSelectedTodoType(todoType);
        // 次の画面表示に使用するTODO情報をシリアライズ型に変換してBundleにセットする
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainFragment.BUNDLE_TODO_INFO, todoInfo.toArray(new TodoInfo[todoInfo.size()]));

        // 選択したTODO種別のTODO一覧Fragmentに差し替える(期限切れとそれ以外で別表示)
        if (todoType.equals("期限切れ")) {

        } else {
            SelectedTodoTypeFragment selectedTodoTypeFragment = new SelectedTodoTypeFragment();
            selectedTodoTypeFragment.setArguments(bundle);
            fragmentTransaction.replace(android.R.id.content, selectedTodoTypeFragment);
        }

        fragmentTransaction.commit();
    }

    /**
     * TODO種別ごとのTODO一覧表示用
     */
    public class TodoTypeSumAdapter extends ArrayAdapter<TodoTypeSummary> {

        // レイアウトxmlファイルからIDを指定してViewを使用する
        private LayoutInflater mLayoutInflater;

        public TodoTypeSumAdapter(Context context, int resourceId, List<TodoTypeSummary> objects) {
            super(context, resourceId, objects);
            // getLayoutInflater()メソッドはActivityじゃないと使えない
            mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // getView()メソッドに各行を表示しようとした時に呼ばれる処理を設定
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            // 特定行(position)のデータを得る
            TodoTypeSummary item = (TodoTypeSummary)getItem(position);
            // convertViewは使いまわされている可能性があるのでnullの時だけ新しく作る
            if (null == rowView) rowView = mLayoutInflater.inflate(R.layout.todo_type_sum_list, null);

            // TODO種別
            TextView textViewTodoType = (TextView)rowView.findViewById(R.id.textView_todoTypeSumTodoType);
            textViewTodoType.setText(item.getTodoType());
            // TODO件数
            TextView textViewTodoNum = (TextView)rowView.findViewById(R.id.textView_todoTypeSumTodoNum);
            textViewTodoNum.setText(item.getTodoTypeNum());

            return rowView;
        }
    }
}
