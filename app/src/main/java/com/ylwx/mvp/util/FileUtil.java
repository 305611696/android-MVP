package com.ylwx.mvp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Set;

public class FileUtil {
	
	/**
	 * 保存对象到文件 对象实现 Serializable 接口 
	 * @param path
	 * @param object
	 * @date 创建时间：2016-5-9 下午4:00:59
	 */
	public void saveDataToFile(String path, Object object){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			File file = new File(path);
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(oos!=null){
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static ThreadLocal<Set<String>> sets = new ThreadLocal<Set<String>>();

	/**
	 * 过滤MP3文件
	 * 
	 * @param strPath
	 */
	public static void refreshFileList(String strPath) {
		File dir = new File(strPath);
		File[] files = dir.listFiles();
		if (files == null) {
			return;
		}
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				refreshFileList(files[i].getAbsolutePath());
			} else {
				String strFilePath = files[i].getAbsolutePath().toLowerCase();
				String strName = files[i].getName();
				if (strName.endsWith(".mp3")) {
					boolean bFlag = sets.get().add(strName);
					if (bFlag == Boolean.FALSE) {
						// 删除重复文件
						removeFile(strFilePath);
					}
				}
				// System.out.println("FILE_PATH:" + strFilePath + "|strName:" +
				// strName);
			}
		}
	}

	/**
	 * 创建文件夹
	 * 
	 * @param strFilePath
	 *            文件夹路径
	 * @return boolean
	 */
	public static boolean mkdirFolder(String strFilePath) {
		boolean bFlag = false;
		try {
			File file = new File(strFilePath.toString());
			if (!file.exists()) {
				bFlag = file.mkdir();
			}
		} catch (Exception e) {
			System.out.println("新建目录操作出错" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return bFlag;
	}

	/**
	 * 创建文件
	 * 
	 * @param strFilePath
	 *            文件路径
	 * @param strFileContent
	 *            文件内容
	 * @return boolean
	 */
	public static boolean createFile(String strFilePath, String strFileContent) {
		boolean bFlag = false;
		try {
			File file = new File(strFilePath.toString());
			if (!file.exists()) {
				bFlag = file.createNewFile();
			}
			if (bFlag == Boolean.TRUE) {
				FileWriter fw = new FileWriter(file);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(strFileContent.toString());
				pw.close();
			}
		} catch (Exception e) {
			System.out.println("新建文件操作出错" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return bFlag;
	}

	/**
	 * 
	 * @param filePath
	 * @param bytes
	 * @return
	 */
	public static boolean createFile(String filePath, byte[] bytes) {
		boolean bFlag = false;
		try {
			File file = new File(filePath);

			FileOutputStream output = new FileOutputStream(file);
			output.write(bytes);
			output.flush();
			output.close();
			System.out.println("[CREATE_FILE:" + file.getPath() + "创建文件成功!]");

		} catch (Exception e) {
			System.out.println("新建文件操作出错" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return bFlag;
	}

	/**
	 * 删除文件
	 * 
	 * @param strFilePath
	 * @return boolean
	 */
	public static boolean removeFile(String strFilePath) {
		boolean result = false;
		if (strFilePath == null || "".equals(strFilePath)) {
			return result;
		}
		File file = new File(strFilePath);
		if (file.isFile() && file.exists()) {
			result = file.delete();
			if (result == Boolean.TRUE) {
				System.out.println("[REMOE_FILE:" + strFilePath + "删除成功!]");
			} else {
				System.out.println("[REMOE_FILE:" + strFilePath + "删除失败]");
			}
		}
		return result;
	}

	/**
	 * 删除文件夹(包括文件夹中的文件内容，文件夹)
	 * 
	 * @param strFolderPath
	 * @return
	 */
	public static boolean removeFolder(String strFolderPath) {
		boolean bFlag = false;
		try {
			if (strFolderPath == null || "".equals(strFolderPath)) {
				return bFlag;
			}
			File file = new File(strFolderPath.toString());
			bFlag = file.delete();
			if (bFlag == Boolean.TRUE) {
				System.out.println("[REMOE_FOLDER:" + file.getPath() + "删除成功!]");
			} else {
				System.out.println("[REMOE_FOLDER:" + file.getPath() + "删除失败]");
			}
		} catch (Exception e) {
			System.out.println("FLOADER_PATH:" + strFolderPath + "删除文件夹失败!");
			e.printStackTrace();
		}
		return bFlag;
	}

	/**
	 * 移除所有文件
	 * 
	 * @param strPath
	 */
	public static void removeAllFile(String strPath) {
		File file = new File(strPath);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] fileList = file.list();
		File tempFile = null;
		for (int i = 0; i < fileList.length; i++) {
			if (strPath.endsWith(File.separator)) {
				tempFile = new File(strPath + fileList[i]);
			} else {
				tempFile = new File(strPath + File.separator + fileList[i]);
			}
			if (tempFile.isFile()) {
				tempFile.delete();
			}
			if (tempFile.isDirectory()) {
				removeAllFile(strPath + "/" + fileList[i]);// 下删除文件夹里面的文件
				removeFolder(strPath + "/" + fileList[i]);// 删除文件夹
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			// int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			// System.out.println(oldfile.getName());
			// System.out.println(oldfile.getPath());
			if (oldfile.exists()) { // 文件存在时
				// 创建新文件存放目录
				String sep = System.getProperty("file.separator");
				sep = "/";
				String newDirPath = newPath.substring(0,
						newPath.lastIndexOf(sep));
				mkdirFolder(newDirPath);
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					// bytesum += byteread; // 字节数 文件大小
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();
				System.out.println("[COPY_FILE:" + oldfile.getPath() + "复制文件成功!]");
			} else {
				System.out.println(oldPath + "文件不存在或被其他进程占用！");
			}

		} catch (Exception e) {
			System.out.println("复制单个文件操作出错 ");
			e.printStackTrace();
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/ " + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/ " + file[i], newPath + "/ "
							+ file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错 ");
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void moveFile(String oldPath, String newPath) {
		// logger.info("oldPath："+oldPath);
		// logger.info("newPath："+newPath);
		copyFile(oldPath, newPath);
		removeFile(oldPath);
	}

	/**
	 * 移动文件夹
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		removeFolder(oldPath);
	}

	/**
	 * 获取文件名---日期格式
	 * 
	 * @return yyyyMMddHHmmssSSS
	 */
	public static final String getDateFileName() {
		return DateUtil.format(DateUtil.yyyyMMddHHmmssSSS,
				System.currentTimeMillis());
	}
	
}
