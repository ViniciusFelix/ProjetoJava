package br.jus.cnj.corporativo.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

import br.jus.cnj.utils.exception.CamposNaoPreenchidosRuntimeException;

public class ArquivoUtils {
	
	
	/**
	 * <p>
	 * Salva um arquivo em um diretório através dos seus bytes. 
	 * </p>
	 * 
	 * salvarArquivo(bytesArquivo,"D:\\tmp\\arquivos\\","arquivo.pdf")
	 * 
	 * @param bytesArquivo
	 *            array de bytes do arquivo a ser salvo
	 * @param caminhoDiretorio
	 *            caminho do diretório a ser salvo o arquivo Ex.: "D:\\tmp\\arquivos\\"
	 * @param nomeArquivo
	 *            nome do arquivo a ser salvo(com sua extensão)
	 * @return void
	 */
	public static void salvarArquivo(byte[] bytesArquivo, String caminhoDiretorio,String nomeArquivo) throws CamposNaoPreenchidosRuntimeException {
		if (bytesArquivo != null && !StringUtils.isBlank(caminhoDiretorio) && !StringUtils.isBlank(nomeArquivo)) {
			InputStream inputStreamArquivo = new ByteArrayInputStream(bytesArquivo);
			salvarArquivo(inputStreamArquivo,caminhoDiretorio,nomeArquivo);
		}else{
			throw new CamposNaoPreenchidosRuntimeException();
		}
	}
	
	/**
	 * <p>
	 * Salva um arquivo em um diretório através do seu InputStream
	 * </p>
	 * 
	 * salvarArquivo(inputStream,"D:\\tmp\\arquivos\\","arquivo.pdf")
	 * 
	 * @param inputStream
	 *            inputStream do arquivo a ser salvo
	 * @param caminhoDiretorio
	 *            caminho do diretório a ser salvo o arquivo Ex.: "D:\\tmp\\arquivos\\"
	 * @param nomeArquivo
	 *            nome do arquivo a ser salvo(com sua extensão)
	 * @return void
	 */
	public static void salvarArquivo(InputStream inputStreamArquivo, String caminhoDiretorio,String nomeArquivo) throws CamposNaoPreenchidosRuntimeException {
		if (inputStreamArquivo != null && !StringUtils.isBlank(caminhoDiretorio) && !StringUtils.isBlank(nomeArquivo)) {
			
			try {
				criarDiretorio(caminhoDiretorio);

				File file = new File(caminhoDiretorio+File.separatorChar+nomeArquivo);
				BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamArquivo);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				try {
					byte[] buffer = new byte[1024];
					int count;
					while ((count = bufferedInputStream.read(buffer)) > 0)
						fileOutputStream.write(buffer, 0, count);
				} finally {
					bufferedInputStream.close();
					fileOutputStream.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			throw new CamposNaoPreenchidosRuntimeException();
		}
	}
	
	/**
	 * <p>
	 * Recupera um arquivo salvo em um diretório e retorna seu InputStream
	 * </p>
	 * 
	 * recuperarArquivoDeDiretorio("D:\\tmp\\arquivos\\","arquivo.pdf")
	 * 
	 * @param caminhoDiretorio
	 *            caminho do diretório aonde o arquivo se encontra
	 * @param nomeArquivo
	 *            nome do arquivo a ser recuperado(com sua extensão)
	 * @return inputstream 
	 */
	public static byte[] recuperarArquivoDeDiretorio(String caminhoDiretorio, String nomeArquivo)
			throws IOException, CamposNaoPreenchidosRuntimeException {
		
		return recuperarArquivoDeDiretorio(caminhoDiretorio+ File.separatorChar + nomeArquivo);
	}
	
	/**
	 * <p>
	 * Recupera um arquivo salvo em um diretório e retorna seu InputStream
	 * </p>
	 * 
	 * recuperarArquivoDeDiretorio("D:\\tmp\\arquivos\\arquivo.pdf")
	 * 
	 * @param caminhoDiretorioCompleto
	 *            caminho completo do diretório+nomeArquivo
	 * @return void
	 */
	public static byte[] recuperarArquivoDeDiretorio(String caminhoDiretorioCompleto)
			throws IOException, CamposNaoPreenchidosRuntimeException {
		
		if(StringUtils.isBlank("caminhoDiretorioCompleto")) {
			throw new CamposNaoPreenchidosRuntimeException();
		}
		
		File f = new File(caminhoDiretorioCompleto);
		
		InputStream stream = new FileInputStream(f);
		int len = (int) f.length();
		byte[] sendBuf = new byte[len];
		stream.read(sendBuf, 0, len);
		
		return sendBuf;
	}
	
	/**
	 * <p>
	 * Exclui um arquivo salvo em um diretório(se o arquivo estiver sendo usado(aberto), não há garantia de exclusão)
	 * </p>
	 * 
	 * excluirArquivo("D:\\tmp\\arquivos\\","arquivo.pdf")
	 * 
	 * @param caminhoDiretorioCompleto
	 *            caminho do diretório aonde o arquivo se encontra
	 * @param nomeArquivo
	 *            nome do arquivo a ser recuperado(com sua extensão)
	 * @return void
	 */
	public static void excluirArquivo(String caminhoDiretorio,String nomeArquivo) {
		excluirArquivo(caminhoDiretorio + File.separatorChar + nomeArquivo);
	}
	
	
	/**
	 * <p>
	 * Exclui um arquivo salvo em um diretório(se o arquivo estiver sendo usado(aberto), não há garantia de exclusão)
	 * </p>
	 * 
	 * excluirArquivo("D:\\tmp\\arquivos\\arquivo.pdf")
	 * 
	 * @param caminhoDiretorioCompleto
	 *            caminho completo do diretório+nomeArquivo
	 * @return void
	 */
	public static void excluirArquivo(String caminhoDiretorioCompletoArquivo) {
		File file = new File(caminhoDiretorioCompletoArquivo);  
		if(file.exists())
			file.delete();
	}
	
	/**
	 * <p>
	 * Cria um diretório passado por uma String
	 * </p>
	 * 
	 * criarDiretorio("D:\\tmp\\arquivos\\")
	 * 
	 * @param caminhoDiretorio
	 *            caminho completo do diretório
	 * @return void
	 */
	public static void criarDiretorio(String diretorio) throws Exception {

		File file = new File(diretorio);
		if (!file.isDirectory()) {
			file.mkdirs();
		}

	}
	
}
