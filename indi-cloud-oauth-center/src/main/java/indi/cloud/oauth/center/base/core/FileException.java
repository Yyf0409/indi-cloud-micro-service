package indi.cloud.oauth.center.base.core;

/**
 * 文件处理错误
 */
public class FileException extends RuntimeException {

   private int code;
   private String fileId;

    public int getCode() {
        return code;
    }

    public FileException(int code, String fileId) {

        this.code = code;
        this.fileId = fileId;
    }

    public FileException(int code, String fileId, String message) {
        super(message);
        this.code = code;
        this.fileId = fileId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public FileException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("%d [FileId = %s] %s", this.code, this.fileId, this.getMessage());
    }
}
