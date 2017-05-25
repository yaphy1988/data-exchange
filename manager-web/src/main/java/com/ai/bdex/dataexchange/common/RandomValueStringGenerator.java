package com.ai.bdex.dataexchange.common;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by yx on 2017/5/25.
 */
public class RandomValueStringGenerator {
    public static final char[] DEFAULT_CODEC = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static final char[] CODEC_ALL = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*".toCharArray();

    private char[] codec;

    private Random random = new SecureRandom();

    private int length;

    public RandomValueStringGenerator()
    {
        this(6);
        this.codec = DEFAULT_CODEC;
    }

    public RandomValueStringGenerator(int length)
    {
        this.length = length;
    }

    public RandomValueStringGenerator(int length, char[] codec) {
        this.length = length;
        this.codec = codec;
    }

    public String generate() {
        byte[] verifierBytes = new byte[this.length];
        this.random.nextBytes(verifierBytes);
        return getAuthorizationCodeString(verifierBytes);
    }

    protected String getAuthorizationCodeString(byte[] verifierBytes)
    {
        char[] chars = new char[verifierBytes.length];
        for (int i = 0; i < verifierBytes.length; i++) {
            chars[i] = this.codec[((verifierBytes[i] & 0xFF) % this.codec.length)];
        }
        return new String(chars);
    }

    public void setRandom(Random random)
    {
        this.random = random;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public static void generatCode(String[] arg) {
        char[] codec = DEFAULT_CODEC;
        int length = 32;
        if ((arg != null) && (arg.length > 0)) {
            try {
                for (int i = 0; i < arg.length; i++) {
                    if (arg[i].equalsIgnoreCase("-c")) {
                        i++;
                        if ("ALL".equalsIgnoreCase(arg[i])) {
                            codec = CODEC_ALL;
                        } else {
                            codec = new char[arg[i].length()];
                        }

                        arg[i].getChars(0, arg[i].length(), codec, 0);
                    }
                }
            } catch (Exception e) {
                System.out.println("参数格式格式不正确");
                e.printStackTrace();
                System.exit(0);
            }
        }
        RandomValueStringGenerator generator = new RandomValueStringGenerator(length, codec);

        System.out.println("32位随机数：" + generator.generate());
    }

    public static void main(String[] arg) {
        char[] codec = DEFAULT_CODEC;
        int length = 32;
        if ((arg != null) && (arg.length > 0)) {
            try {
                for (int i = 0; i < arg.length; i++) {
                    if (arg[i].equalsIgnoreCase("-h")) {
                        printHelp();
                        System.exit(0);
                    }
                    if (arg[i].equalsIgnoreCase("-C")) {
                        i++;
                        if ("ALL".equalsIgnoreCase(arg[i])) {
                            codec = CODEC_ALL;
                        } else {
                            codec = new char[arg[i].length()];
                        }

                        arg[i].getChars(0, arg[i].length(), codec, 0);
                    }
                    if (arg[i].equalsIgnoreCase("-l")) {
                        i++;
                        length = Integer.parseInt(arg[i]);
                    }
                }
            } catch (Exception e) {
                printHelp();
                System.out.println("参数格式格式不正确");
                e.printStackTrace();
                System.exit(0);
            }
        }

        RandomValueStringGenerator generator = new RandomValueStringGenerator(length, codec);
        System.out.println(generator.generate());
    }

    private static void printHelp() {
        System.out.println("useage: -h -c 编码数据 -l 长度");
        System.out.println("-h 打印帮助");
        System.out.println("-c [参数] 使用编码列表 ,默认包含所有的字母和数字");
        System.out.println("-l 长度 编码长度");
    }
}
