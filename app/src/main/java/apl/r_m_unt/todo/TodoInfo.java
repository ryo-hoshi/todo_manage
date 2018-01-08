package apl.r_m_unt.todo;

import java.io.Serializable;
import java.util.Date;

/**
 * TODO設定情報
 * TODO1件分の設定情報を管理するクラス
 *
 * Created by ryota on 2018/01/01.
 */
public class TodoInfo implements Serializable {

    private String todoType;
    private Date limit;
    private boolean isComplete;
    private String todo;

    TodoInfo(String todoType, Date limit, boolean isComplete, String todo) {
        this.todoType = todoType;
        this.limit = limit;
        this.isComplete = isComplete;
        this.todo = todo;
    }

    public String getTodoType() {
        return todoType;
    }
    public Date getLimit() {
        return limit;
    }
    public boolean getIsComplete() {
        return isComplete;
    }
    public String getTodo() {
        return todo;
    }
}
