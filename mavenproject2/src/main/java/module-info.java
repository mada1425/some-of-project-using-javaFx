module com.mycompany.mavenproject2 {
    requires javafx.controls;
    exports  com.mycompany.mavenproject2;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
    opens com.mycompany.mavenproject2 to org.hibernate.orm.core;
    requires java.sql;

}
