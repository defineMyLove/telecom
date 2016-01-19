package com.company.electriccar.web;

/**
 * Created with IntelliJ IDEA.
 * User: zxl
 * Date: 13-10-17
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 */
public class MyFile {
    private String fileName;
    private byte[] file;
    private String url;
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }

    public boolean fileisNull(){
        return this.fileName==null ||this.file==null || this.file.length==0;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }


}
