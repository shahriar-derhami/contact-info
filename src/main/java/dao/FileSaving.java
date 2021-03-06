package dao;

import exceptions.ErrorCode;
import exceptions.UserInfoException;
import model.UserInfo;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by shahriar on 12/12/14.
 */
public class FileSaving {
    /**
     * write list of userInfo in file
     *
     * @param userInfos
     * @throws UserInfoException
     */
    public  void writeUserInfos(ArrayList<UserInfo> userInfos,String filePath) throws  Exception {

            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(userInfos);
            byte b[] = baos.toByteArray();
            raf.seek(0);
            raf.write(b);
            raf.close();
            oos.close();
            baos.close();

    }

    /**
     * read list of userInfos from file
     *
     * @return userInfos
     * @throws UserInfoException
     */
    public  ArrayList<UserInfo> readUserInfos(String filePath) throws Exception {
        ArrayList<UserInfo> members = new ArrayList<UserInfo>();


            if(filePath == null){
                throw new UserInfoException(null,ErrorCode.NULL);
            }
            File f = new File(filePath);
            if (!f.exists()) {
                return members;
            }
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.seek(0);
            byte b[] = new byte[10000];
            raf.read(b);
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            ObjectInputStream ois = new ObjectInputStream(bais);
            members = (ArrayList<UserInfo>) ois.readObject();
            ois.close();
            bais.close();

        return members;
    }

}
