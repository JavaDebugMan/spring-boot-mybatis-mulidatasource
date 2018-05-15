package com.neo.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全散列算法 SHA (Secure Hash Algorithm)
 * <p>
 * 是美国国家标准和技术局发布的国家标准FIPS PUB 180-1，一般称为SHA-1。<br>
 * 其对长度不超过264二进制位的消息产生160位的消息摘要输出。<br>
 * SHA是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，<br>
 * 现在已成为公认的最安全的散列算法之一，并被广泛使用。<br>
 * 该加密算法是单向加密，即加密的数据不能再通过解密还原。
 */
public final class SHAUtils {

    final static public String[] INFO_SALT = {"vaBci", "l6AF8", "vM9vH"};
    private static final String SHA_ALGORITHM = "SHA-1";

    private SHAUtils() {
    }

    private static MessageDigest getSHADigestAlgorithm()
            throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(SHA_ALGORITHM);
    }

    public static byte[] getSHADigest(byte source[])
            throws NoSuchAlgorithmException {
        return getSHADigestAlgorithm().digest(source);
    }

    public static byte[] getSHADigest(String source)
            throws NoSuchAlgorithmException {
        return getSHADigest(source.getBytes());
    }

    public static String getSHADigestHex(byte source[])
            throws NoSuchAlgorithmException {
        return new String(Hex.encodeHex(getSHADigest(source)));
    }

    public static String getSHADigestHex(String source)
            throws NoSuchAlgorithmException {
        return new String(Hex.encodeHex(getSHADigest(source.getBytes())));
    }

    public static String getSHADigestBase64(byte source[])
            throws NoSuchAlgorithmException {
        return new String(Base64.encodeBase64(getSHADigest(source)));
    }

    public static String getSHADigestBase64(String source)
            throws NoSuchAlgorithmException {
        return new String(Base64.encodeBase64(getSHADigest(source.getBytes())));
    }

    /**
     * 密码加密算法，字符串分成和 盐 等分的长度， 将盐插入到指定的位置后进行加密
     *
     * @param source
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getSHADigestHexPassword(String source) {
        Integer saltLength = INFO_SALT.length;
        Integer splitLength = source.length() / saltLength;
        StringBuffer tempSource = new StringBuffer();

        for (int i = 0; i < saltLength; i++) {
            if (i == (saltLength - 1)) {
                tempSource.append(source.substring(splitLength * i, source.length()));
                tempSource.append(INFO_SALT[i]);
            } else {
                tempSource.append(source.substring(splitLength * i, splitLength * (i + 1)));
                tempSource.append(INFO_SALT[i]);
            }

        }

        String miString = source;
        try {
            miString = getSHADigestHex(tempSource.toString());
        } catch (NoSuchAlgorithmException e) {
        }
        return miString;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(SHAUtils.getSHADigestHexPassword("abc123456"));
    }

}
