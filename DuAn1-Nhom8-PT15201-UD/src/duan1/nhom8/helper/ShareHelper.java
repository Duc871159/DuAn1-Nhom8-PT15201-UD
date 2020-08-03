/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.helper;

import duan1.nhom8.model.NguoiDung;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author Nguyen Van Dien
 */
public class ShareHelper {
//    public static final Image APP_ICON;
//    public static final ImageIcon APP_ICON_1;
//    static{
//        String file="/icon/fpt.png";
//        APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
//        APP_ICON_1 = new ImageIcon(ShareHelper.class.getResource(file));
//    }
    public static boolean saveLogo(File file){
       File dir = new File("src/logo");
       if(!dir.exists()){
           dir.mkdir();
       }
       File newFile = new File(dir,file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static ImageIcon readLogo(String fileName){
        File path = new File("src/logo",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    public static NguoiDung USER = null;
    public static void logoff(){
        ShareHelper.USER =  null;
    }
    public static boolean authenticated(){
        return ShareHelper.USER != null;
    }
}