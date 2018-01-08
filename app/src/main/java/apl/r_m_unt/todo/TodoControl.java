package apl.r_m_unt.todo;

//import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO設定クラス
 * TODO情報の管理を行う
 *
 */
public class TodoControl {

    private static final String TAG = "TodoControl";
    List<TodoInfo> todoInfoList = new ArrayList<>();

    public TodoControl() {
        // 本当は外部から呼ぶ（モックなのでコンストラクタから呼び出す）
        // 日付更新タイミング、TODO追加、削除、変更のタイミングで実施が必要
        getTodoInfo();
    }

    /**
     * 登録された全TODOを取得する
     */
    public void getTodoInfo() {
        todoInfoList.add(new TodoInfo("今日", new Date(), false, "みずやり"));
        todoInfoList.add(new TodoInfo("今日", new Date(), false, "えさやり"));
        todoInfoList.add(new TodoInfo("今日", new Date(), false, "買い物"));
        todoInfoList.add(new TodoInfo("明日", new Date(), false, "契約"));
        todoInfoList.add(new TodoInfo("今週", new Date(), false, "ゴミ捨て"));
        todoInfoList.add(new TodoInfo("今月", new Date(), false, "映画に行く"));
        todoInfoList.add(new TodoInfo("来月", new Date(), false, "確定申告"));
        todoInfoList.add(new TodoInfo("今年", new Date(), false, "ジムに行く"));
        todoInfoList.add(new TodoInfo("今日", new Date(), false, "筋トレ"));
    }

    /**
     * TODO情報（TodoInfo）をTodoType種別毎に集計し、TODO種別と件数のリストを返却する
     * （TodoInfoの期限日付をもとに種別を判定する）
     * @return TODO種別の件数リスト
     */
    public List<TodoTypeSummary> getTodoTypeSummary() {
        List<TodoTypeSummary> todoTypeSumList = new ArrayList<>();
        todoTypeSumList.add(new TodoTypeSummary("今日", 4));
        todoTypeSumList.add(new TodoTypeSummary("明日", 2));
        todoTypeSumList.add(new TodoTypeSummary("今週", 3));
        todoTypeSumList.add(new TodoTypeSummary("今月", 1));
        todoTypeSumList.add(new TodoTypeSummary("来月", 2));
        todoTypeSumList.add(new TodoTypeSummary("今年", 3));
        todoTypeSumList.add(new TodoTypeSummary("いつか", 2));
        todoTypeSumList.add(new TodoTypeSummary("期限切れ", 1));

        return todoTypeSumList;
    }

    /**
     * 指定したTODO種別のTODOのリストを返却する
     * @param todoType TODO種別
     * @return 指定したTODO種別のTODOのリスト
     */
    public List<TodoInfo> getSelectedTodoType(String todoType) {
        List<TodoInfo> selectedTodoInfo = new ArrayList<>();
        for(TodoInfo todo: todoInfoList){
            if (todoType.equals(todo.getTodoType())) {
                selectedTodoInfo.add(todo);
            }
        }
        return selectedTodoInfo;
    }

//    private static final String ALARM_SETTING_DATA = "ALARM_SETTING_DATA";
//    private static TodoControl instance;
//    private List<TodoSettingInfoList> todoSettingInfoList = new ArrayList<>();
//
//    private TodoControl() {
//        // singleton
//    }
//
//    // KEY
//    private static final String ALARM_SETTING_KEY = "ALARM_SETTING";
//
////    // 保存情報取得
////    public static TodoControl getInstance(Context context) {
////
////        // 初回の場合
////        if (instance == null) {
////            // 保存情報を取得
////            //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
////            SharedPreferences prefs = context.getSharedPreferences(ALARM_SETTING_DATA, Context.MODE_PRIVATE);
////            Gson gson = new Gson();
////            String TodoControlString = prefs.getString(ALARM_SETTING_KEY, "");
////
////            // 保存したオブジェクトを取得
////            if ( !TextUtils.isEmpty(alarmSettingString)) {
////                instance = gson.fromJson(alarmSettingString, TodoControl.class);
////            } else {
////                // 何も保存されていない　初期時点はデフォルト値を入れる
////                instance = getDefaultInstance();
////            }
////        }
////
////        return instance;
////    }
//
//    // デフォルト値の入ったオブジェクトを返す
//    private static TodoControl getDefaultInstance() {
//        TodoControl instance = new TodoControl();
//        //instance.todoSettingInfoList = new ArrayList<>();
//        // アラーム情報のインデックスに0を指定
//        instance.todoSettingInfoList.add(new todoSettingInfoList(0, 0));
//
//        return instance;
//    }
//
//    public int getAlarmSetSize() {
//        return todoSettingInfoList.size();
//    }
//
//    public List<TodoSettingInfoList> getTodoSettingInfoList() {
//        return todoSettingInfoList;
//    }
//
//    public void setTodoSettingInfoList(int index, TodoSettingInfoList todoSettingInfoList) {
//        todoSettingInfoList.set(index, todoSettingInfoList);
//    }
//
//    //    public List<TodoSettingInfoList> getAddedTodoSettingInfoList() {
////        todoSettingInfoList.add(new todoSettingInfoList());
////        return todoSettingInfoList;
////    }
////    public int addTodoSettingInfoList() {
////        int alarmNo = this.getAlarmNo();
////        int alarmIndex = todoSettingInfoList.size();
////        Log.d(TAG, "生成したアラームNo：" + alarmNo);
////        todoSettingInfoList.add(new TodoSettingInfoList(alarmNo, alarmIndex));
////        return alarmNo;
////    }
//
////    public List<TodoSettingInfoList> getRemovedtodoSettingInfoList(int index) {
////        todoSettingInfoList.remove(index);
////        return todoSettingInfoList;
////    }
//
//    public void removedTodoSettingInfoList(int index) {
//        todoSettingInfoList.remove(index);
//    }
//
////    // 状態保存
////    public void saveInstance(Context context) {
////        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
////        SharedPreferences prefs = context.getSharedPreferences(ALARM_SETTING_DATA, Context.MODE_PRIVATE);
////        Gson gson = new Gson();
////        // 現在のインスタンスの状態を保存
////        prefs.edit().putString(ALARM_SETTING_KEY, gson.toJson(this)).apply();
////    }
//
//    /**
//     * アラーム情報インデックスを取得
//     * @return
//     */
////    private int getAlarmNo() {
////
////        // アラーム情報が存在しない場合は無条件で0
////        if (todoSettingInfoList.isEmpty()) {
////            return 0;
////        }
////
////        // 既存のアラームNoとかぶらない値を取得
////        // 候補のアラームNoを取得
////        int alarmNo = todoSettingInfoList.size();
////        int maxIndex = 0;
////        boolean isIndexExist = false;
////
////        for (TodoSettingInfoList todoSettingInfoList : todoSettingInfoList) {
////            if (alarmNo == todoSettingInfoList.getAlarmNo()) {
////                isIndexExist = true;
////            }
////            if (maxIndex < alarmNo) {
////                maxIndex = alarmNo;
////            }
////        }
////
////        // 候補のアラームNoが使われていたら最大値＋１を取得
////        // intの上限まではいかない想定
////        if (isIndexExist) {
////            return maxIndex + 1;
////
////            // 使われていなければ候補をそのまま取得
////        } else {
////            return alarmNo;
////        }
////    }
}
