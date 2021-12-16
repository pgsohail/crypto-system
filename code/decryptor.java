/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encrypt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Decryptor {
    private static Decryptor decrypter = new Decryptor();

	private static boolean deleteOriginal;
	
	private Decryptor()
	{
	}

	public static Decryptor getDecrypter(boolean originalFileDeleted)
	{
		deleteOriginal = originalFileDeleted;
		
		return decrypter;
	}
	
	public void decrypt(File src, File dst)
	{
		if (!dst.exists())
			dst.mkdir();
		if (!dst.isDirectory())
			return;

		try
		{
			if (!src.isDirectory())
			{
				copyDecrypted(src, dst);
			} else
			{
				File[] files = src.listFiles();

				System.out.println("Decryting...");

				for (File f : files)
				{
					copyDecrypted(f, dst);
					if(deleteOriginal) f.delete();
				}

				System.out.println(files.length + " files are decrytped");
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void copyDecrypted(File source, File dest) throws IOException
	{
		InputStream is = null;
		OutputStream os = null;

		try
		{
			is = new FileInputStream(source);

			byte[] buffer = new byte[1024];

			byte[] name = new byte[is.read() * 2];
			is.read(name);
			String fileName = bytesToString(name);

			os = new FileOutputStream(dest.getPath().concat("/").concat(fileName));

			int length;

			while ((length = is.read(buffer)) > 0)
			{
				decryptBytes(buffer);
				os.write(buffer, 0, length);
			}

		} finally
		{
			is.close();
			os.close();
		}
	}

	public String bytesToString(byte[] data)
	{
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < data.length / 2; i++)
		{
			char c = (char) ((data[i * 2] << 8) | data[i * 2 + 1]);
			res.append(c);
		}

		return res.toString();
	}

	private void decryptBytes(byte[] data) // Decryption Algorithm is written into here
	{
		for (int i = 0; i < data.length; i++)
		{
			data[i] = (byte) ~data[i];
		}
	}

	public void copy(File source, File dest) throws IOException
	{
		InputStream is = null;
		OutputStream os = null;

		try
		{
			dest = new File(dest.getPath().concat("/").concat(source.getName()));

			is = new FileInputStream(source);
			os = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			int tl = 0;

			while ((length = is.read(buffer)) > 0)
			{
				tl += length;
				os.write(buffer, 0, length);
			}

			System.out.println(tl + " bytes");
		} finally
		{
			is.close();
			os.close();
		}
	}
    
}
