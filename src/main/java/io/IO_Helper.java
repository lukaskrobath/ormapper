package io;

import annotations.Entity;
import database.DB_Access;
import database.DB_Manager;


import java.io.File;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IO_Helper {

    public static List<String> getEntityClassnames(Path dirPath) {
        List<String> classname = new ArrayList<String>();
        File dir = new File((dirPath.toString()));
        File[] dirContent = dir.listFiles();
        for (File file : dirContent) {
            if (file.isDirectory()) {
                classname.addAll(getEntityClassnames(file.toPath()));
            } else if (file.isFile()) {
                if (file.getAbsolutePath().endsWith(".java")) {
                    String tmp = file.getAbsolutePath().split("java")[1].substring(1).replace("\\", ".");
                    tmp = tmp.substring(0, tmp.length() - 1);
                    try {
                        Class entityClass = Class.forName(tmp);
                        if (entityClass.isAnnotationPresent(Entity.class)) {
                            classname.add(tmp);
                        }
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.toString());
                    }
                }

            }
        }
        return classname;
    }

    public static void main(String[] args) {
        Path dirPath = Paths.get(".", "src", "main", "java");
        DB_Access access = DB_Access.getInstance();
        try {
            DB_Manager man = new DB_Manager(getEntityClassnames(dirPath));
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }
}
