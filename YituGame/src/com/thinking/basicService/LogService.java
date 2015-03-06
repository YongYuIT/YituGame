package com.thinking.basicService;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Environment;
import android.util.Log;

public class LogService
{
    private static String  SDCardPath = Environment
                                              .getExternalStorageDirectory()
                                              .getPath();
    private static boolean isDebug    = true;

    public static void writeLog(String msg)
    {
        try
        {
            if (!isDebug)
                return;
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy年MM月dd日  HH:mm:ss  ");
            Date curDate = new Date(System.currentTimeMillis());
            String Time = formatter.format(curDate);
            msg = Time + ":" + msg + "\n";
            File file = new File(SDCardPath + "//" + "thinking_log.txt");
            if (!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(SDCardPath + "//" + "thinking_log.txt",
                    true);
            fw.write(msg);
            fw.close();
        } catch (Exception e)
        {
            Log.e("thinking----------------", "日志服务出错");
        }
    }
}
