/**
 * 
 */
package com.company.modules.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩 解压工具类
 * 
 * @author wx
 * @date Mar 24, 2011
 */
public class ZipUtil {
	private static final int BUFFEREDSIZE = 1024;

	/**
	 * 压缩文件
	 * 
	 * @param inputFilename
	 *            需要压缩的文件路径（可以是目录）
	 * @param zipFilename
	 *            压缩后的路径
	 * @throws java.io.IOException
	 */
	public static void zip(String inputFilename, String zipFilename)
			throws IOException {
		zip(new File(inputFilename), zipFilename);
	}

	/**
	 * 压缩文件
	 * 
	 * @param inputFile
	 *            需要压缩的文件
	 * @param zipFilename
	 *            压缩后的路径
	 * @throws java.io.IOException
	 */
	public static void zip(File inputFile, String zipFilename)
			throws IOException {
		ZipOutputStream out = null;
		try {
			File file = new File(zipFilename);
			out = new ZipOutputStream(new FileOutputStream(file));
			zip(inputFile, out, inputFile.getName(), file.getName());
		} finally {
			if (out != null)
				out.close();
		}
	}

	private static synchronized void zip(File inputFile, ZipOutputStream out,
			String base, String zipFileName) throws IOException {
		FileInputStream in = null;
		try {
			if (inputFile.isDirectory()) {
				File[] inputFiles = inputFile.listFiles();
				out.putNextEntry(new ZipEntry(base + "/"));
				base = base.length() == 0 ? "" : base + "/";
				for (int i = 0; i < inputFiles.length; i++) {
					if (!zipFileName.equals(base + inputFiles[i].getName()))
						zip(inputFiles[i], out, base + inputFiles[i].getName(), zipFileName);
				}
			} else {
				if (base.length() > 0) {
					out.putNextEntry(new ZipEntry(base));
				} else {
					out.putNextEntry(new ZipEntry(inputFile.getName()));
				}

				in = new FileInputStream(inputFile);
				int c;
				byte[] by = new byte[BUFFEREDSIZE];
				while ((c = in.read(by)) != -1) {
					out.write(by, 0, c);
				}
			}
		} finally {
			if (in != null)
				in.close();
		}
	}

	/**
	 * 解压文件
	 * 
	 * @param zipFilename
	 *            需要解压的文件
	 * @param outputDirectory
	 *            解压后的文件
	 * @throws java.io.IOException
	public synchronized static void unzip(String zipFilename, String outputDirectory)
			throws IOException {
		InputStream in = null;
		FileOutputStream out = null;
		try {
			File outFile = new File(outputDirectory);
			if (!outFile.exists()) {
				outFile.mkdirs();
			}

			ZipFile zipFile = new ZipFile(zipFilename);
			Enumeration en = zipFile.getEntries();
			ZipEntry zipEntry = null;
			while (en.hasMoreElements()) {
				zipEntry = (ZipEntry) en.nextElement();
				if (zipEntry.isDirectory()) {
					// mkdir directory
					String dirName = zipEntry.getName();
					dirName = dirName.substring(0, dirName.length() - 1);

					File f = new File(outFile.getPath() + File.separator
							+ dirName);
					f.mkdirs();

				} else {
					// unzip file
					File f = new File(outFile.getPath() + File.separator
							+ zipEntry.getName());
					f.createNewFile();
					in = zipFile.getInputStream(zipEntry);
					out = new FileOutputStream(f);
					int c;
					byte[] by = new byte[BUFFEREDSIZE];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
				}
			}
		} finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
	}
	 */
	
	public static void main(String[] args) {
		try {
			zip("D:\\XT\\桌面\\20121028","D:\\XT\\桌面\\20121028.zip");
//			unzip("E://Program%20Files//bea//user_projects//domains//hmykt//tempFiles//YWSJTemplateExportFiles//123//FileExportServlet.zip","E://Program%20Files//bea//user_projects//domains//hmykt//tempFiles//YWSJTemplateExportFiles//123");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
