package apl.r_m_unt.todo;

import java.util.Map;
import java.util.TreeMap;

/**
 * Todo種別を取得する
 * 最初は固定値を取得するが後にDBから取得することを検討
 *
 */
public class TodoType {

    public static int VERSION_KEY_NUM = 99;

    private static Map<Integer, String> todoTypeMap = new TreeMap<Integer, String>();

    private static TodoType instance = new TodoType();

    private TodoType() {
        // singleton
    }

    // 保存情報取得
    public static TodoType getInstance() {

        // 初回の場合
        if (todoTypeMap.isEmpty()) {
            // KYE:99にバージョンを保持
            todoTypeMap.put(0, "今日");
            todoTypeMap.put(1, "明日");
            todoTypeMap.put(2, "今週");
            todoTypeMap.put(3, "来週");
            todoTypeMap.put(4, "今月");
            todoTypeMap.put(5, "来月");
            todoTypeMap.put(6, "今年");
            todoTypeMap.put(7, "いつか");
            todoTypeMap.put(90, "期限切れ");
            todoTypeMap.put(VERSION_KEY_NUM, "01");
        }

        return instance;
    }

    /**
     * TODO種別を取得
     * @return TODO種別
     */
    public static Map<Integer, String> getTodoTypeMap(){
        return todoTypeMap;
    }
}
