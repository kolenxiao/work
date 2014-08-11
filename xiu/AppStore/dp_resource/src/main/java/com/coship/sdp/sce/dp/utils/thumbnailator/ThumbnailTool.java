package com.coship.sdp.sce.dp.utils.thumbnailator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

/**
 * 图片裁剪缩放处理工具类.
 * 
 * @author 907948
 * 
 */
public class ThumbnailTool {
	private static Rename defaultRename = new ResizedNameSuffix();

	/**
	 * 将单张图片按指定比例缩放，处理后的图片放到原图片所在目录，图片名后面跟比例值，如c:\a.jpg，处理后的图片名为：c:\a_500x200.
	 * jpg
	 * 
	 * @param imgFile
	 * @param resize
	 *            格式：宽x高,如：500x200，实际转换是等比例转换，所以图片大小会有差别.
	 * @return 处理后的图片路径（包含图片文件名）.
	 */
	public static File resize(String imgFile, String resize) {
		ThumbnailOption option = ThumbnailOption.parseResize(resize);
		try {
			List<File> result = Thumbnails.of(imgFile).size(option.getWidth(), option.getHeight())
					.asFiles(defaultRename);

			return result.iterator().next();
		} catch (Exception e) {
			throw new ThumbnailException("图片缩放发生异常.", e);
		}
	}

	/**
	 * 将单张图片按指定比例缩放，处理后的图片放到原图片所在目录，图片名后面跟比例值，如c:\a.jpg，处理后的图片名为：c:\a_500x200.
	 * jpg
	 * 
	 * @param imgFile
	 *            图片文件
	 * @param resize
	 *            转换大小.
	 * @return
	 */
	public static File resize(File imgFile, String resize) {
		ThumbnailOption option = ThumbnailOption.parseResize(resize);
		try {
			List<File> result = Thumbnails.of(imgFile).size(option.getWidth(), option.getHeight())
					.asFiles(defaultRename);

			return result.iterator().next();
		} catch (Exception e) {
			throw new ThumbnailException("图片缩放发生异常.", e);
		}
	}

	/**
	 * 将图片按指定比例缩放，指定处理后的图片路径和名称.
	 * 
	 * @param imgFilePath
	 * @param resize
	 *            格式：宽x高,如：500x200，实际转换是等比例转换，所以图片大小会有差别.
	 * @param outputDir
	 *            处理后的图片存放目录.
	 * @return 处理后的图片.
	 */
	public static File resize(String imgFilePath, String resize, String outputDir) {
		ThumbnailOption option = ThumbnailOption.parseResize(resize);

		try {
			File dir = new File(outputDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			List<File> result = Thumbnails.of(imgFilePath).size(option.getWidth(), option.getHeight())
					.asFiles(defaultRename);
			if (result == null) {
				return null;
			}
			List<File> moved = moveFileToOutputDir(outputDir, result);
			return moved.iterator().next();
		} catch (Exception e) {
			throw new ThumbnailException("图片缩放发生异常.", e);
		}
	}
	
	public static File resize(File imgFile, String resize, String outputDir) {
		ThumbnailOption option = ThumbnailOption.parseResize(resize);

		try {
			File dir = new File(outputDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			List<File> result = Thumbnails.of(imgFile).size(option.getWidth(), option.getHeight())
					.asFiles(defaultRename);
			if (result == null) {
				return null;
			}
			List<File> moved = moveFileToOutputDir(outputDir, result);
			return moved.iterator().next();
		} catch (Exception e) {
			throw new ThumbnailException("图片缩放发生异常.", e);
		}
	}
	
//	/**
//	 * 根据图片width等比缩小
//	 * @param imgFilePath
//	 * @param width
//	 * @param outputDir
//	 * @return
//	 */
//	public static File resizeBywidth(String imgFilePath,int width,String outputDir)
//	{
//		try
//		{
//			int[] ratioArray = FileUtil.readPicRatio(imgFilePath);
//			if(null==ratioArray)
//			{
//				return null;
//			}
//			
//			File dir = new File(outputDir);
//			if (!dir.exists()) 
//			{
//				dir.mkdirs();
//			}
//			
//			List<File> result = Thumbnails.of(imgFilePath).scale((float)width/ratioArray[0]).asFiles(defaultRename);
//			if (result == null) 
//			{
//				return null;
//			}
//			List<File> moved = moveFileToOutputDir(outputDir, result);
//			return moved.iterator().next();
//		} 
//		catch (Exception e) 
//		{
//			e.printStackTrace();
//			throw new ThumbnailException("图片缩放发生异常.", e);
//		}
//	}
	
	/**
	 * 将单张图片缩放为各种尺寸.
	 * 
	 * @param imgFile
	 * @param resizeSet
	 *            格式：宽x高,如：500x200
	 * @return 返回处理后的图片路径.
	 */
	public static List<File> resize(String imgFile, List<String> resizeList) {
		if (resizeList == null) {
			return null;
		}

		List<File> result = new ArrayList<File>();
		for (String resize : resizeList) {
			try {
				File resizedFile = resize(imgFile, resize);
				result.add(resizedFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 将单张图片缩放为各种尺寸.
	 * 
	 * @param imgFile
	 * @param resizeSet
	 *            格式：宽x高,如：500x200
	 * @param outputDir
	 *            处理后的图片存放目录.
	 * @return 返回处理后的图片列表.
	 */
	public static List<File> resize(String imgFile, List<String> resizeList, String outputDir) {
		if (resizeList == null) {
			return null;
		}

		List<File> result = new ArrayList<File>();
		for (String resize : resizeList) {
			try {
				File resizedFile = resize(imgFile, resize);
				result.add(resizedFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return moveFileToOutputDir(outputDir, result);
	}

	/**
	 * 移动转换后的文件到指定目录.
	 * 
	 * @param outputDir
	 * @param result
	 */
	private static List<File> moveFileToOutputDir(String outputDir, List<File> result) {
		List<File> movedList = new ArrayList<File>();
		for (File converted : result) {
			String name = converted.getName();
			File file = new File(outputDir + File.separator + name);
			converted.renameTo(file);
			movedList.add(file);
		}

		return movedList;
	}

	/**
	 * 将单张图片缩放为各种尺寸.
	 * 
	 * @param imgFile
	 *            图片文件
	 * @param resizeSet
	 *            格式：宽x高,如：500x200
	 * @return 返回处理后的图片路径.
	 */
	public static List<File> resize(File imgFile, List<String> resizeList) {
		if (resizeList == null) {
			return null;
		}

		List<File> result = new ArrayList<File>();
		for (String resize : resizeList) {
			try {
				File resizedFile = resize(imgFile, resize);
				result.add(resizedFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 将单张图片缩放为各种尺寸.
	 * 
	 * @param imgFile
	 *            图片文件
	 * @param resizeSet
	 *            格式：宽x高,如：500x200
	 * @param outputDir
	 *            处理后的图片存放目录.
	 * @return 返回处理后的图片路径.
	 */
	public static List<File> resize(File imgFile, List<String> resizeList, String outputDir) {
		if (resizeList == null) {
			return null;
		}

		List<File> result = new ArrayList<File>();
		for (String resize : resizeList) {
			try {
				File resizedFile = resize(imgFile, resize);
				result.add(resizedFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return moveFileToOutputDir(outputDir, result);
	}

	/**
	 * 批量转换目录下的所有图片（不递归子目录）.
	 * 
	 * @param path
	 *            图片目录.
	 * @param resize
	 *            缩放比例， 格式：宽x高,如：500x200
	 * @return
	 */
	public static void resizePath(String path, String resize) {
		File filePath = new File(path);
		for (File imgFile : filePath.listFiles()) {
			try {
				if(imgFile.isFile())
				{
					resize(imgFile, resize);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量转换目录下的所有图片（不递归子目录）.
	 * 
	 * @param path
	 *            图片目录.
	 * @param resize
	 *            缩放比例， 格式：宽x高,如：500x200
	 * @param outputDir
	 *            处理后的图片存放目录.
	 * @return
	 */
	public static void resizePath(String path, String resize, String outputDir) {
		File filePath = new File(path);
		List<File> result = new ArrayList<File>();
		for (File imgFile : filePath.listFiles()) {
			try {
				result.add(resize(imgFile, resize));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		moveFileToOutputDir(outputDir, result);
	}

	/**
	 * 目录下的每个图片文件转换多种格式。
	 * 
	 * @param path
	 *            图片目录.
	 * @param resizeList
	 * @return 返回处理后的图片所在目录.
	 */
	public static void resizePath(String path, List<String> resizeList) {
		File filePath = new File(path);
		for (File srcFile : filePath.listFiles()) {
			resize(srcFile, resizeList);
		}
	}

	/**
	 * 目录下的每个图片文件转换多种格式。
	 * 
	 * @param path
	 *            图片目录.
	 * @param resizeList
	 * @param outputDir
	 *            处理后的图片存放目录.
	 */
	public static void resizePath(String path, List<String> resizeList, String outputDir) {
		File filePath = new File(path);
		List<File> result = new ArrayList<File>();
		for (File srcFile : filePath.listFiles()) {
			result.addAll(resize(srcFile, resizeList));
		}

		moveFileToOutputDir(outputDir, result);
	}
}
