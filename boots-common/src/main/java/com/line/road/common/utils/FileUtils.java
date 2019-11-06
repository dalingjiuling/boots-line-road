package com.line.road.common.utils;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class FileUtils extends org.apache.commons.io.FileUtils {

	private static Logger LOG = LoggerFactory.getLogger(FileUtils.class);
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	public FileUtils() {
	}

	public static boolean copyFile(String srcFileName, String descFileName) {
		return copyFileCover(srcFileName, descFileName, false);
	}

	public static boolean copyFileCover(String srcFileName, String descFileName, boolean coverlay) {
		File srcFile = new File(srcFileName);

		if (!srcFile.exists()) {
			LOG.debug("复制文件失败，源文件 " + srcFileName + " 不存在!");
			return false;
		}

		if (!srcFile.isFile()) {
			LOG.debug("复制文件失败，" + srcFileName + " 不是一个文件!");
			return false;
		}
		File descFile = new File(descFileName);

		if (descFile.exists()) {
			if (coverlay) {
				LOG.debug("目标文件已存在，准备删除!");
				if (!delFile(descFileName)) {
					LOG.debug("删除目标文件 " + descFileName + " 失败!");
					return false;
				}
			} else {
				LOG.debug("复制文件失败，目标文件 " + descFileName + " 已存在!");
				return false;
			}
		} else if (!descFile.getParentFile().exists()) {
			LOG.debug("目标文件所在的目录不存在，创建目录!");

			if (!descFile.getParentFile().mkdirs()) {
				LOG.debug("创建目标文件所在的目录失败!");
				return false;
			}
		}

		int readByte = 0;
		InputStream ins = null;
		OutputStream outs = null;
		try {
			ins = new FileInputStream(srcFile);

			outs = new FileOutputStream(descFile);
			byte[] buf = new byte['Ѐ'];

			while ((readByte = ins.read(buf)) != -1) {
				outs.write(buf, 0, readByte);
			}
			LOG.debug("复制单个文件 " + srcFileName + " 到" + descFileName + "成功!");

			return true;
		} catch (Exception e) {
			LOG.debug("复制文件失败：" + e.getMessage());
			return false;
		} finally {
			if (outs != null) {
				try {
					outs.close();
				} catch (IOException oute) {
					oute.printStackTrace();
				}
			}
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException ine) {
					ine.printStackTrace();
				}
			}
		}
	}

	public static boolean copyDirectory(String srcDirName, String descDirName) {
		return copyDirectoryCover(srcDirName, descDirName, false);
	}

	public static boolean copyDirectoryCover(String srcDirName, String descDirName, boolean coverlay) {
		File srcDir = new File(srcDirName);

		if (!srcDir.exists()) {
			LOG.debug("复制目录失败，源目录 " + srcDirName + " 不存在!");
			return false;
		}

		if (!srcDir.isDirectory()) {
			LOG.debug("复制目录失败，" + srcDirName + " 不是一个目录!");
			return false;
		}

		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);

		if (descDir.exists()) {
			if (coverlay) {
				LOG.debug("目标目录已存在，准备删除!");
				if (!delFile(descDirNames)) {
					LOG.debug("删除目录 " + descDirNames + " 失败!");
					return false;
				}
			} else {
				LOG.debug("目标目录复制失败，目标目录 " + descDirNames + " 已存在!");
				return false;
			}
		} else {
			LOG.debug("目标目录不存在，准备创建!");
			if (!descDir.mkdirs()) {
				LOG.debug("创建目标目录失败!");
				return false;
			}
		}

		boolean flag = true;

		File[] files = srcDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = copyFile(files[i].getAbsolutePath(), descDirName + files[i].getName());

				if (!flag) {
					break;
				}

			} else if (files[i].isDirectory()) {
				flag = copyDirectory(files[i].getAbsolutePath(), descDirName + files[i].getName());

				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			LOG.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 失败!");
			return false;
		}
		LOG.debug("复制目录 " + srcDirName + " 到 " + descDirName + " 成功!");
		return true;
	}

	public static String readFileToString(String classResourcePath) {
		InputStream in = null;
		try {
			in = new ClassPathResource(classResourcePath).getInputStream();
			return IOUtils.toString(in, Charsets.toCharset("UTF-8"));
		} catch (IOException e) {
			LOG.warn("Error file convert: {}", e.getMessage());
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static boolean delFile(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			LOG.debug(fileName + " 文件不存在!");
			return true;
		}
		if (file.isFile()) {
			return deleteFile(fileName);
		}
		return deleteDirectory(fileName);
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if ((file.exists()) && (file.isFile())) {
			if (file.delete()) {
				LOG.debug("删除文件 " + fileName + " 成功!");
				return true;
			}
			LOG.debug("删除文件 " + fileName + " 失败!");
			return false;
		}
		LOG.debug(fileName + " 文件不存在!");
		return true;
	}

	public static boolean deleteDirectory(String dirName) {
		String dirNames = dirName;
		if (!dirNames.endsWith(File.separator)) {
			dirNames = dirNames + File.separator;
		}
		File dirFile = new File(dirNames);
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			LOG.debug(dirNames + " 目录不存在!");
			return true;
		}
		boolean flag = true;

		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());

				if (!flag) {
					break;
				}

			} else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i].getAbsolutePath());

				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			LOG.debug("删除目录失败!");
			return false;
		}

		if (dirFile.delete()) {
			LOG.debug("删除目录 " + dirName + " 成功!");
			return true;
		}
		LOG.debug("删除目录 " + dirName + " 失败!");
		return false;
	}

	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			LOG.debug("文件 " + descFileName + " 已存在!");
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			LOG.debug(descFileName + " 为目录，不能创建目录!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			if (!file.getParentFile().mkdirs()) {
				LOG.debug("创建文件所在的目录失败!");
				return false;
			}
		}

		try {
			if (file.createNewFile()) {
				LOG.debug(descFileName + " 文件创建成功!");
				return true;
			}
			LOG.debug(descFileName + " 文件创建失败!");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug(descFileName + " 文件创建失败!");
		}
		return false;
	}

	public static boolean createDirectory(String descDirName) {
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		if (descDir.exists()) {
			LOG.debug("目录 " + descDirNames + " 已存在!");
			return false;
		}

		if (descDir.mkdirs()) {
			LOG.debug("目录 " + descDirNames + " 创建成功!");
			return true;
		}
		LOG.debug("目录 " + descDirNames + " 创建失败!");
		return false;
	}

	public static void writeToFile(String fileName, String content, boolean append) {
		try {
			write(new File(fileName), content, "utf-8", append);
			LOG.debug("文件 " + fileName + " 写入成功!");
		} catch (IOException e) {
			LOG.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
		}
	}

	public static void writeToFile(String fileName, String content, String encoding, boolean append) {
		try {
			write(new File(fileName), content, encoding, append);
			LOG.debug("文件 " + fileName + " 写入成功!");
		} catch (IOException e) {
			LOG.debug("文件 " + fileName + " 写入失败! " + e.getMessage());
		}
	}

	public static void zipFiles(String srcDirName, String fileName, String descFileName) {
		if (srcDirName == null) {
			LOG.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
			return;
		}
		File fileDir = new File(srcDirName);
		if ((!fileDir.exists()) || (!fileDir.isDirectory())) {
			LOG.debug("文件压缩失败，目录 " + srcDirName + " 不存在!");
			return;
		}
		String dirPath = fileDir.getAbsolutePath();
		File descFile = new File(descFileName);
		try {
			ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(descFile));

			if (("*".equals(fileName)) || ("".equals(fileName))) {
				zipDirectoryToZipFile(dirPath, fileDir, zouts);
			} else {
				File file = new File(fileDir, fileName);
				if (file.isFile()) {
					zipFilesToZipFile(dirPath, file, zouts);
				} else {
					zipDirectoryToZipFile(dirPath, file, zouts);
				}
			}

			zouts.close();
			LOG.debug(descFileName + " 文件压缩成功!");
		} catch (Exception e) {
			LOG.debug("文件压缩失败：" + e.getMessage());
			e.printStackTrace();
		}
	}

	public static boolean unZipFiles(String zipFileName, String descFileName) {
		String descFileNames = descFileName;
		if (!descFileNames.endsWith(File.separator)) {
			descFileNames = descFileNames + File.separator;
		}
		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipEntry entry = null;
			String entryName = null;
			String descFileDir = null;
			byte[] buf = new byte['က'];
			int readByte = 0;

			Enumeration<ZipEntry> enums = zipFile.getEntries();

			while (enums.hasMoreElements()) {
				entry = (ZipEntry) enums.nextElement();

				entryName = entry.getName();
				descFileDir = descFileNames + entryName;
				if (entry.isDirectory()) {
					new File(descFileDir).mkdirs();
				} else {
					new File(descFileDir).getParentFile().mkdirs();

					File file = new File(descFileDir);

					OutputStream os = new FileOutputStream(file);

					InputStream is = zipFile.getInputStream(entry);
					while ((readByte = is.read(buf)) != -1) {
						os.write(buf, 0, readByte);
					}
					os.close();
					is.close();
				}
			}
			zipFile.close();
			LOG.debug("文件解压成功!");
			return true;
		} catch (Exception e) {
			LOG.debug("文件解压失败：" + e.getMessage());
		}
		return false;
	}

	public static void zipDirectoryToZipFile(String dirPath, File fileDir, ZipOutputStream zouts) {
		if (fileDir.isDirectory()) {
			File[] files = fileDir.listFiles();

			if (files.length == 0) {
				ZipEntry entry = new ZipEntry(getEntryName(dirPath, fileDir));
				try {
					zouts.putNextEntry(entry);
					zouts.closeEntry();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}

			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					zipFilesToZipFile(dirPath, files[i], zouts);
				} else {
					zipDirectoryToZipFile(dirPath, files[i], zouts);
				}
			}
		}
	}

	public static void zipFilesToZipFile(String dirPath, File file, ZipOutputStream zouts) {
		FileInputStream fin = null;
		ZipEntry entry = null;

		byte[] buf = new byte['က'];
		int readByte = 0;
		if (file.isFile()) {
			try {
				fin = new FileInputStream(file);

				entry = new ZipEntry(getEntryName(dirPath, file));

				zouts.putNextEntry(entry);

				while ((readByte = fin.read(buf)) != -1) {
					zouts.write(buf, 0, readByte);
				}
				zouts.closeEntry();
				fin.close();
				System.out.println("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static String getEntryName(String dirPath, File file) {
		String dirPaths = dirPath;
		if (!dirPaths.endsWith(File.separator)) {
			dirPaths = dirPaths + File.separator;
		}
		String filePath = file.getAbsolutePath();

		if (file.isDirectory()) {
			filePath = filePath + "/";
		}
		int index = filePath.indexOf(dirPaths);

		return filePath.substring(index + dirPaths.length());
	}

	public static String getContentType(String fileName) {
		return new MimetypesFileTypeMap().getContentType(fileName);
	}

	public static String getRealContentType(File file) {
		try {
			MagicMatch match = Magic.getMagicMatch(file, false, true);
			if (match != null) {
				return match.getMimeType();
			}
		} catch (Exception e) {
		}

		return "";
	}

	public static String path(String path) {
		String p = StringUtils.replace(path, "\\", "/");
		p = StringUtils.join(StringUtils.split(p, "/"), "/");
		if (!StringUtils.startsWithAny(p, new CharSequence[] { "/" }))
			if (StringUtils.startsWithAny(path, new CharSequence[] { "\\", "/" })) {
				p = p + "/";
			}
		if (!StringUtils.endsWithAny(p, new CharSequence[] { "/" }))
			if (StringUtils.endsWithAny(path, new CharSequence[] { "\\", "/" })) {
				p = p + "/";
			}
		if ((path != null) && (path.startsWith("/"))) {
			p = "/" + p;
		}
		return p;
	}

	public static List<String> findChildrenList(File dir, boolean searchDirs) {
		List<String> files = Lists.newArrayList();
		for (String subFiles : dir.list()) {
			File file = new File(dir + "/" + subFiles);
			if (((searchDirs) && (file.isDirectory())) || ((!searchDirs) && (!file.isDirectory()))) {
				files.add(file.getName());
			}
		}
		return files;
	}

	public static String getFileExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1)
				|| (fileName.lastIndexOf(".") == fileName.length() - 1)) {
			return null;
		}
		return StringUtils.lowerCase(fileName.substring(fileName.lastIndexOf(".") + 1));
	}

	public static String getFileNameWithoutExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1)) {
			return null;
		}
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	public static ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public static InputStream getResourceFileInputStream(String location) {
		Resource resource = resourceLoader.getResource(location);
		try {
			return resource.getInputStream();
		} catch (IOException e) {
			throw Exceptions.unchecked(e);
		}
	}
}
