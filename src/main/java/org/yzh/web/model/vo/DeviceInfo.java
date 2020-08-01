package org.yzh.web.model.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeviceInfo {

    /** 签发时间（秒） */
    private int issuedAt;
    /** 有效期 （秒） */
    private int validAt;
    /** 车牌颜色 */
    private byte plateColor;
    /** 车牌号 */
    private String plateNo;
    /** 设备ID */
    private String deviceId;

    public int getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(int issuedAt) {
        this.issuedAt = issuedAt;
    }

    public int getValidAt() {
        return validAt;
    }

    public void setValidAt(int validAt) {
        this.validAt = validAt;
    }

    public byte getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(byte plateColor) {
        this.plateColor = plateColor;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("issuedAt", issuedAt)
                .append("validAt", validAt)
                .append("plateColor", plateColor)
                .append("plateNo", plateNo)
                .append("deviceId", deviceId)
                .toString();
    }
}