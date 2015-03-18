package nd.com.ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ChangeCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("D:/logoutput.log");
		try {
			if (!file.exists()) {

				file.createNewFile();

			}
			OutputStreamWriter writerStream = new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8");
			writerStream.write(readFileByLines("D:/mult.txt"));
			writerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String readFileByLines(String fileName) {
		System.out.println(fileName);
		StringBuilder sb = new StringBuilder();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				if (!tempString.equals("")) {
					String[] tempArray = tempString.split(" ");
					String output = "";

					for (int i = 0; i < tempArray.length; i++) {
						if (i == 0) {
							output += Integer.parseInt(tempArray[0]);
						} else {
							tempArray[i] = " $((0x" + tempArray[i] + "))";
							output += tempArray[i];
						}
					}

					sb.append("sendevent /dev/input/event1 " + output + "\n");
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString() + "exit\n";
	}
}
