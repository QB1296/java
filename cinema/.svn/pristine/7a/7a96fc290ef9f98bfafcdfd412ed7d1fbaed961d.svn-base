package com.ganjx.cinema.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {

	private static Log log = LogFactory.getLog(StringUtil.class);

	public StringUtil() {

	}


    public final static String EMPTY_STRING = new String();
    
    
    
    /**
     * 字符串增加1  str0001  返回    str002
     * @param originalStr
     * @author YUJB
     * @return 加1后的字符串
     * @throws NumberFormatException  如果 字符串 末尾不是以数字开头
     */
    public static String nextVal(String originalStr){
        String[] strs = originalStr.split("[^0-9]");
        String numStr = strs[strs.length-1];
        if(numStr != null && numStr.length()>0){
            int n = numStr.length();
            long num = Long.parseLong(numStr)+1;
            String added = String.valueOf(num);
            n = Math.min(n, added.length());
            return originalStr.subSequence(0, originalStr.length()-n)+added;
        }else{
            throw new NumberFormatException();
        }
    }
    
    /**
     * 按照指定位数按照0补全  例如   size = 5  "MD" result: 000MD
     * @param originalStr
     * @param size
     * @return
     */
    public static String prefixFillZero(String originalStr,int size){
    	StringBuffer sb = new StringBuffer(originalStr);
		for (int i = originalStr.length();i < size ; i++) {
			sb.insert(0, "0");
		}
		return sb.toString();
    }
    

    public static boolean isEmpty(String s) {
        return (s == null || s.trim().length() == 0);
    }

    public static String limit(String s, int maxLength) {
        if (isEmpty(s)) {
            return EMPTY_STRING;
        }

        if (maxLength <= 0 || s.length() <= maxLength) {
            return s;
        }

        return s.substring(0, maxLength);
    }





	public static String encodePassword(String password) {
		return StringUtil.encodePassword(password, "MD5");
	}

	
	public static String encodePassword(String password, String algorithm) {
		byte[] unencodedPassword = password.getBytes();

		MessageDigest md = null;

		try {
			// first create an instance, given the provider
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			log.error("Exception: " + e);

			return password;
		}

		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static String movePosition(String pwd){
		StringBuffer buf = new StringBuffer(pwd);
		int len = buf.length();
		StringBuffer temp = buf.insert(len-2, buf.charAt(len-1));
		return temp.deleteCharAt(temp.length()-1).toString();
	}

	public static String longToString(Long l) {
		String s = null;
		if (l != null) {
			s = String.valueOf(l);
		}
		return s;
	}

	public static String intToString(Integer l) {
		String s = null;
		if (l != null) {
			s = String.valueOf(l);
		}
		return s;
	}

	public static Long convertToLong(String s) {
		Long l = null;

		if (s != null && s.length() > 0) {
			try {
				l = Long.parseLong(s);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return l;
	}

	public static Integer convertToInt(String s) {
		Integer l = null;

		if (s != null && s.length() > 0) {
			try {
				l = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return l;
	}

	public static String checkEmptyString(String s) {
		String ss = null;

		s = StringUtil.normalizeString(s);

		if (s != null && s.length() > 0) {
			ss = s;
		}

		return ss;
	}

	public static String normalizeString(String s) {
		return s == null ? "" : s.trim();
	}

	public static String gb2iso(String strGBK) {
		String s = "";
		try {
			s = new String(StringUtil.normalizeString(strGBK).getBytes("GBK"),
					"iso8859-1");
		} catch (java.io.UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		return s;
	}

	public static String iso2gb(String strGBK) {
		return StringUtil.normalizeString(strGBK);
	}

	public static String[] getCode(String s, String separator) {
		String[] codes = null;
		if (s != null && separator != null && s.length() > separator.length()) {
			String code = "";
			int i = 0;
			StringTokenizer st = new StringTokenizer(s, separator);
			codes = new String[st.countTokens()];
			while (st.hasMoreTokens()) {
				code = st.nextToken();
				codes[i] = code;
				i++;
			}
		}
		return codes;
	}

	public static String showDateTime(Date dt) {
		if (dt == null)
			return "";
		Locale locale = Locale.CHINESE;
		DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm aaa",
				locale);
		String s = formatter.format(dt);
		return s;
	}

	public static String showDate(Date dt) {
		if (dt == null)
			return "";
		Locale locale = Locale.CHINESE;
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", locale);
		String s = formatter.format(dt);
		return s;
	}

	public static Date getToday() {
		return new Date();
	}

	public static String showFixedString(String s, long length) {
		String result = "";
		if (s != null) {
			if (s.length() > length) {
				s = s.substring(0, (int) length);
				s += "...";
			}
			result = s;
		}
		return result;
	}

	
	//----------------------------------------------------------------------------------------upload.StringUtils.begin

    public static final String escapeHTMLTags(String input) {
        //Check if the string is null or zero length -- if so, return
        //what was sent in.
        if (input == null || input.length() == 0) {
            input = "";
        }
        //Use a StringBuffer in lieu of String concatenation -- it is
        //much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            if (ch == '<') {
                buf.append("&lt;");
            } else if (ch == '>') {
                buf.append("&gt;");
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }


    public synchronized static final String hash(String data) {
        MessageDigest digest = null;

        if (digest == null) {
            try {
                digest = MessageDigest.getInstance("MD5");
            }
            catch (NoSuchAlgorithmException nsae) {
                System.err.println("Failed to load the MD5 MessageDigest. " +
                        "Jive will be unable to function normally.");
                nsae.printStackTrace();
            }
        }
        //Now, compute hash.
        digest.update(data.getBytes());
        return toHex(digest.digest());
    }


    public static final String toHex(byte hash[]) {
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if (((int) hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) hash[i] & 0xff, 16));
        }
        return buf.toString();
    }
    
    /**
     * encode
     * @param plainText
     * @return
     */
    public static String encodeBase64(String plainText){
		byte[] b=plainText.getBytes();
		Base64 base64=new Base64();
		b=base64.encode(b);
		String s=new String(b);
		return s;
	}
	
    /**
     * decode
     * @param encodeStr
     * @return
     */
	public static String decodeBase64(String encodeStr){
		byte[] b=encodeStr.getBytes();
		Base64 base64=new Base64();
		b=base64.decode(b);
		String s=new String(b);
		return s;
	}
	

	public static String appendSingleQuote(String s){
		if(s != null){
			StringBuilder sb  = new StringBuilder("'");
			sb.append(s);
			sb.append("'");
			return sb.toString();
		}
		return null;
	}
//----------------------------------------------------------------------------------------upload.StringUtils.end

    
	public static void main(String[] args) {
	}
}