module com.healthsync {
    requires javafx.controls;
    requires java.sql;
    requires jbcrypt;

    opens com.healthsync.dao to javafx.base;
    exports com.healthsync.app;
    opens com.healthsync.util to javafx.base;
}
