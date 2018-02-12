/**
 * 
 */
package mx.com.amx.unotv.wsb.testpush.bo;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import mx.com.amx.unotv.wsb.testpush.bo.exception.PushBOException;


/**
 * @author Jesus A. Macias Benitez
 *
 */
public class PushBO {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(PushBO.class);
	
	@Value("${one.signal.autorizaction}")
	private String _autorizactionOnesingal;
	
	@Value("${one.signal.url}")
	private String _urlOnesingal;
	
	
	public void printLog(String json) {
		logger.info("--- printLog [ PushBO ] ---");
		logger.debug("--- json---");
		 logger.debug("------------------------------------------");
         logger.debug(""+json.toString());
         logger.debug("------------------------------------------");
		
		
		String new_json = cambiaCaracteres(json);
		logger.debug("--- new json---");
		logger.debug(""+new_json.toString());
	}

	public String  push(String jsonRecibido) throws PushBOException {
		logger.info("--- push [ PushBO ] ---");
		logger.debug("--- json---");
		 logger.debug("------------------------------------------");
         logger.debug(""+jsonRecibido.toString());
         logger.debug("------------------------------------------");
		
         
       
        
		
		String json = jsonRecibido; //StringEscapeUtils.unescapeJava(jsonRecibido);
		/*
		json = Normalizer.normalize(json, Normalizer.Form.NFD);
		json= json.replaceAll("[^\\p{ASCII}]", "");
		json = json.replaceAll("\\p{M}", "");
	*/
	
		String respuesta="";
		
		  logger.debug("------------------------------------------");
      	  logger.debug("--- json cambiaCaracteres---");
          logger.debug(""+json.toString());
          logger.debug("------------------------------------------");
          
          
		try {
			
			logger.debug("--- _urlOnesingal : "+_urlOnesingal+" ---");
			logger.debug("--- _autorizactionOnesingal : "+_autorizactionOnesingal+"---");
			
			String llave_fija = _autorizactionOnesingal;
			
			logger.debug("Conectandose a : "+ _urlOnesingal);
						
			URL url = new URL(_urlOnesingal);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            logger.debug("Authorization [llave_fija]: "+llave_fija);
            
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Authorization", llave_fija);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
	        
            logger.debug("------------------------------------------");
        	logger.debug("--- json Replace it ---");
            logger.debug(""+json.toString());
            logger.debug("------------------------------------------");
            // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(json.toString().getBytes());

            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
            respuesta = IOUtils.toString(inputStream);
            logger.debug("Respuesta: "+respuesta);
    		return respuesta;

		} catch (Exception e) {
			logger.error("Exception push  [ PushBO ]: ",e);
			throw new PushBOException(e.getMessage());
		}
	}

	
	/**
	  * Clase para la codificacion de Caracteres
	  * @param String, Texto a codificar
	  * @return String, Texto codificado
	  * */
	public static String cambiaCaracteres(String texto) {
			texto = texto.replaceAll("�", "&#225;");
	        texto = texto.replaceAll("�", "&#233;");
	        texto = texto.replaceAll("�", "&#237;");
	        texto = texto.replaceAll("�", "&#243;");
	        texto = texto.replaceAll("�", "&#250;");  
	        texto = texto.replaceAll("�", "&#193;");
	        texto = texto.replaceAll("�", "&#201;");
	        texto = texto.replaceAll("�", "&#205;");
	        texto = texto.replaceAll("�", "&#211;");
	        texto = texto.replaceAll("�", "&#218;");
	        texto = texto.replaceAll("�", "&#209;");
	        texto = texto.replaceAll("�", "&#241;");        
	        texto = texto.replaceAll("�", "&#170;");          
	        texto = texto.replaceAll("�", "&#228;");
	        texto = texto.replaceAll("�", "&#235;");
	        texto = texto.replaceAll("�", "&#239;");
	        texto = texto.replaceAll("�", "&#246;");
	        texto = texto.replaceAll("�", "&#252;");    
	        texto = texto.replaceAll("�", "&#196;");
	        texto = texto.replaceAll("�", "&#203;");
	        texto = texto.replaceAll("�", "&#207;");
	        texto = texto.replaceAll("�", "&#214;");
	        texto = texto.replaceAll("�", "&#220;");
	        texto = texto.replaceAll("�", "&#191;");
	        texto = texto.replaceAll("�", "&#8220;");        
	        texto = texto.replaceAll("�", "&#8221;");
	        texto = texto.replaceAll("�", "&#8216;");
	        texto = texto.replaceAll("�", "&#8217;");
	        texto = texto.replaceAll("�", "...");
	        texto = texto.replaceAll("�", "&#161;");
	        texto = texto.replaceAll("�", "&#191;");
	        texto = texto.replaceAll("�", "&#176;");
	        
	        texto = texto.replaceAll("�", "&#8211;");
	        texto = texto.replaceAll("�", "&#8212;");
	        //texto = texto.replaceAll("\"", "&#34;");
			return texto;
		}
	

}
