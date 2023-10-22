package bin;

import java.io.*;
import java.util.ArrayList;

/**
 * <h1>Bin</h1>
 * The Bin class provides the ability to save serializable objects and strings to and from files.
 * This class supports data persistence using Java's serialization feature.
 *
 * <br>
 * How To use.
 * <pre>
 * Bin bin = new Bin("data.bin");
 * bin.compress("テスト文字列");
 * String data = bin.decompressString();
 * </pre>
 *
 * @author rxxuzi
 */
public class Bin {
    protected String filename;
    protected byte[] data;

    public Bin(String filename) {
        this.filename = filename;
        File file = new File(filename);
        if (file.exists()) {
            try {
                this.data = decompress();
            } catch (IOException ignored) {}
        }
    }

    public void compress(String input) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(input);
        }
    }

    public void compress(ArrayList<?> list) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        }
    }

    public void compress(Serializable object) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        }
    }

    /**
     * 指定されたファイルからバイトデータを読み込み、そのバイト配列を返します。
     * このメソッドは、ファイルの内容を直接バイト配列として読み込むため、
     * シリアル化やデシリアル化の操作は行いません。
     *
     * @return ファイルの内容を表すバイト配列
     * @throws IOException ファイルの読み取りに関する例外が発生した場合
     */
    public byte[] decompress() throws IOException {
        try (FileInputStream fis = new FileInputStream(filename);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
    }

    /**
     * 指定されたファイルからシリアル化されたArrayListをデシリアル化して返します。
     * このメソッドは、指定されたクラスタイプに基づいてデータをデシリアル化します。
     *
     * @param clazz ArrayListの要素のクラスタイプ
     * @return デシリアル化されたArrayList
     * @throws IOException ファイルの読み取りまたはデシリアル化に関する例外が発生した場合
     * @throws ClassNotFoundException 指定されたクラスが見つからない場合
     */

    public <T> ArrayList<T> decompressToList(Class<T> clazz) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (ArrayList<T>) ois.readObject();
        }
    }

    /**
     * ファイルからシリアル化されたオブジェクトをデシリアル化して返す。
     *
     * @param clazz オブジェクトのクラスタイプ
     * @return デシリアル化されたオブジェクト
     * @throws IOException ファイルの読み取りに関する例外が発生した場合
     * @throws ClassNotFoundException 指定されたクラスが見つからない場合
     */
    public <T> T decompressObject(Class<T> clazz) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return clazz.cast(ois.readObject());
        }
    }

    /**
     * ファイルからシリアル化された文字列をデシリアル化して返す。
     *
     * @return デシリアル化された文字列
     * @throws IOException ファイルの読み取りに関する例外が発生した場合
     * @throws ClassNotFoundException 文字列のクラスが見つからない場合
     */
    public String decompressString() throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (String) ois.readObject();
        }
    }

}
