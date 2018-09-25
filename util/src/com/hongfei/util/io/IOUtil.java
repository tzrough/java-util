package com.hongfei.util.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author hongfei 
 * 流操作的工具类
 * 
 */
public final class IOUtil
{

	/**
	 * 缓存区大小
	 */
	private static final int BUFFER_SIZE = 4096;

	private IOUtil()
	{
	}

	/**
	 * 关闭一个或多个流对象
	 * 
	 * @param closeables
	 *            可关闭的流对象列表
	 */
	public static void close(Closeable... closeables)
	{

		if (closeables != null)
		{
			try
			{
				for (Closeable closeable : closeables)
				{
					if (closeable != null)
					{
						closeable.close();
					}
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将输入流中的内容写到输出流中
	 * 
	 * @param inputStream
	 * @param outputStream
	 * @throws IOException
	 */
	public static void write(InputStream inputStream, OutputStream outputStream)
	{

		int length = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		try
		{
			while ((length = inputStream.read(buffer)) != -1)
			{
				outputStream.write(buffer, 0, length);
			}
			outputStream.flush();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
