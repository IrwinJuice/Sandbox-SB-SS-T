package org.springframework.gsspringboot.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AbsolutePath {
    public static void main(String[] args) {
 String UPLOAD_FOLDER = "D:\\Documents\\Projects\\ShopOnSB\\src\\main\\resources\\static\\images\\vinil\\imgs_bd_vinil\\vin1.png";
        Path path = Paths.get(UPLOAD_FOLDER);
        System.out.println(path.toAbsolutePath());
    }

}
