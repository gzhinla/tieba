package project.TB.A;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author 公子
 *
 */
public class WNotice {
	/**
	 * 
	 * @throws IOException
	 */
	public static void writeNotice() throws IOException {
		FileOutputStream fos = null;
		File file = new File("D:\\demo.txt");
		if (!file.exists())
			file.createNewFile();
		try {
			fos = new FileOutputStream(file, true);
			String str1 = "\n有用户创建的新帖，请审核！发表时间：" + new Date(file.lastModified());
			fos.write(str1.getBytes());
			byte[] buf = str1.getBytes();
			for (byte b : buf)
				fos.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fos.close();
		}
	}
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String getNotice() throws IOException {
		File file = new File("D:\\demo.txt");
		byte[] bytes2 = new byte[1024];
		int len=0;
		try {
			FileInputStream input = new FileInputStream(file);
			 len= input.read(bytes2);
			System.out.println(new String(bytes2, 0, len));
			input.close();
			String a=new String(bytes2, 0, len);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(bytes2, 0, len);
	}

	public static void DeleteFile(){
		File file = new File("D:\\demo.txt");
		file.delete();
		 }
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		WNotice.writeNotice();
		WNotice.getNotice();
    }
}
