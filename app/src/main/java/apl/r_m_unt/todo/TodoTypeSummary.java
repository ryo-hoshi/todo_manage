package apl.r_m_unt.todo;

/**
 * TODO種別サマリクラス
 * TODO情報をTODO種別でサマリするために使用するクラス
 *
 * Created by ryota on 2018/01/03.
 */
public class TodoTypeSummary {

    /** TODO種別 */
    private String todoType;
    private int todoTypeNum;

    private TodoTypeSummary() {
        // private
    }

    public TodoTypeSummary(String todoType, int todoTypeNum) {
        this.todoType = todoType;
        this.todoTypeNum = todoTypeNum;
    }


    public String getTodoType() {
        return todoType;
    }

    public int getTodoTypeNum() {
        return todoTypeNum;
    }

//    public void setTodoType(String todoType) {
//        this.todoType = todoType;
//    }
//
//    public void setTodoTypeNum(int todoTypeNum) {
//        this.todoTypeNum = todoTypeNum;
//    }
}
