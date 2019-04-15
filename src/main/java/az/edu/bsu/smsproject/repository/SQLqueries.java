package az.edu.bsu.smsproject.repository;

public class SQLqueries {
    public static String GET_PASSWORD_FROM_BDU_USER = "SELECT password FROM bdu_user WHERE email = ?";

    public static String GET_ROLE_ID_FROM_BDU_UDER = "SELECT role_id FROM bdu_user WHERE email = ?";

    public static String GET_ALL_DATA_FROM_JOINED_BDU_USER_AND_STUDENT = "SELECT * FROM bdu_user bu JOIN student s ON bu.user_id = s.user_id";

    public static String GET_ALL_DATA_FROM_JOINED_BDU_USER_AND_TUTOR = "SELECT * FROM bdu_user bu JOIN tutor t ON bu.user_id = t.user_id";

    public static String GET_ROLE_ID_IF_EMAIL_AND_PASSWORD_MATCH = "SELECT role_id FROM bdu_user WHERE email = ? and password = ?";

    public static String GET_ROLE_DATA_BY_ID = "select id, name, default_controller, default_page from role where id=? AND status=1";

    public static String GET_ROLE_ID_BY_ROLE_NAME = "SELECT id FROM role WHERE name = ?";

    public static String GET_SECTION_LIST_BY_YEAR = "SELECT section FROM groups WHERE year = ?";
}
