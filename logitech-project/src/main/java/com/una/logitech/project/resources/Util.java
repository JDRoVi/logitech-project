
package com.una.logitech.project.resources;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

public class Util {
    public static void SaveImgTemporary(byte[] bytes, String name){
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance()
                .getExternalContext().getContext();
        String path = context.getRealPath("")+File.separatorChar+"resoutces"+
                File.separatorChar+"images"+File.separatorChar+name;
        File file=null;
        InputStream in = null;
         try{
            file=new File(path);
            in=new ByteArrayInputStream(bytes);
            FileOutputStream out= new FileOutputStream(file.getAbsolutePath());
            int bit=0;
            while((bit=in.read())>=0){
                out.write(bit);
            }
            out.flush();
            out.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
