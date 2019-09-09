package com.example.ycx36.newems.util;

public class getCarAllBean {

    /**
     * response : {"info":{"code":100000,"msg":"成功"},"content":{"id":1,"uid":"5A75737441747461636B6572466C617368","updatetime":"2019-05-16 17:17:10","motorspeed":"4130","motortorque":"41.0","totalcurrent":"0","totalvoltage":"0","vin":null,"vehiclestatus":"02","chargingstate":"03","model":"01","speed":"0","mileage":"-1","voltage":"353.0","current":"44.0","aftercurrent":"00","qid":"01","temperature":"0","motortem":"57","motorvm":"353.0","motoram":"45.0","longitude":"120109085","latitude":"30294926","unitvm":"0","unitid":"00","unitvmlow":"0","unittemid":"00","unittem":"0","unittemlow":"0","unitidlow":"00"}}
     */

    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {
        /**
         * info : {"code":100000,"msg":"成功"}
         * content : {"id":1,"uid":"5A75737441747461636B6572466C617368","updatetime":"2019-05-16 17:17:10","motorspeed":"4130","motortorque":"41.0","totalcurrent":"0","totalvoltage":"0","vin":null,"vehiclestatus":"02","chargingstate":"03","model":"01","speed":"0","mileage":"-1","voltage":"353.0","current":"44.0","aftercurrent":"00","qid":"01","temperature":"0","motortem":"57","motorvm":"353.0","motoram":"45.0","longitude":"120109085","latitude":"30294926","unitvm":"0","unitid":"00","unitvmlow":"0","unittemid":"00","unittem":"0","unittemlow":"0","unitidlow":"00"}
         */

        private InfoBean info;
        private ContentBean content;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public static class InfoBean {
            /**
             * code : 100000
             * msg : 成功
             */

            private int code;
            private String msg;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }

        public static class ContentBean {
            /**
             * id : 1
             * uid : 5A75737441747461636B6572466C617368
             * updatetime : 2019-05-16 17:17:10
             * motorspeed : 4130
             * motortorque : 41.0
             * totalcurrent : 0
             * totalvoltage : 0
             * vin : null
             * vehiclestatus : 02
             * chargingstate : 03
             * model : 01
             * speed : 0
             * mileage : -1
             * voltage : 353.0
             * current : 44.0
             * aftercurrent : 00
             * qid : 01
             * temperature : 0
             * motortem : 57
             * motorvm : 353.0
             * motoram : 45.0
             * longitude : 120109085
             * latitude : 30294926
             * unitvm : 0
             * unitid : 00
             * unitvmlow : 0
             * unittemid : 00
             * unittem : 0
             * unittemlow : 0
             * unitidlow : 00
             */

            private int id;
            private String uid;
            private String updatetime;
            private String motorspeed;
            private String motortorque;
            private String totalcurrent;
            private String totalvoltage;
            private Object vin;
            private String vehiclestatus;
            private String chargingstate;
            private String model;
            private String speed;
            private String mileage;
            private String voltage;
            private String current;
            private String aftercurrent;
            private String qid;
            private String temperature;
            private String motortem;
            private String motorvm;
            private String motoram;
            private String longitude;
            private String latitude;
            private String unitvm;
            private String unitid;
            private String unitvmlow;
            private String unittemid;
            private String unittem;
            private String unittemlow;
            private String unitidlow;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getMotorspeed() {
                return motorspeed;
            }

            public void setMotorspeed(String motorspeed) {
                this.motorspeed = motorspeed;
            }

            public String getMotortorque() {
                return motortorque;
            }

            public void setMotortorque(String motortorque) {
                this.motortorque = motortorque;
            }

            public String getTotalcurrent() {
                return totalcurrent;
            }

            public void setTotalcurrent(String totalcurrent) {
                this.totalcurrent = totalcurrent;
            }

            public String getTotalvoltage() {
                return totalvoltage;
            }

            public void setTotalvoltage(String totalvoltage) {
                this.totalvoltage = totalvoltage;
            }

            public Object getVin() {
                return vin;
            }

            public void setVin(Object vin) {
                this.vin = vin;
            }

            public String getVehiclestatus() {
                return vehiclestatus;
            }

            public void setVehiclestatus(String vehiclestatus) {
                this.vehiclestatus = vehiclestatus;
            }

            public String getChargingstate() {
                return chargingstate;
            }

            public void setChargingstate(String chargingstate) {
                this.chargingstate = chargingstate;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getSpeed() {
                return speed;
            }

            public void setSpeed(String speed) {
                this.speed = speed;
            }

            public String getMileage() {
                return mileage;
            }

            public void setMileage(String mileage) {
                this.mileage = mileage;
            }

            public String getVoltage() {
                return voltage;
            }

            public void setVoltage(String voltage) {
                this.voltage = voltage;
            }

            public String getCurrent() {
                return current;
            }

            public void setCurrent(String current) {
                this.current = current;
            }

            public String getAftercurrent() {
                return aftercurrent;
            }

            public void setAftercurrent(String aftercurrent) {
                this.aftercurrent = aftercurrent;
            }

            public String getQid() {
                return qid;
            }

            public void setQid(String qid) {
                this.qid = qid;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getMotortem() {
                return motortem;
            }

            public void setMotortem(String motortem) {
                this.motortem = motortem;
            }

            public String getMotorvm() {
                return motorvm;
            }

            public void setMotorvm(String motorvm) {
                this.motorvm = motorvm;
            }

            public String getMotoram() {
                return motoram;
            }

            public void setMotoram(String motoram) {
                this.motoram = motoram;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getUnitvm() {
                return unitvm;
            }

            public void setUnitvm(String unitvm) {
                this.unitvm = unitvm;
            }

            public String getUnitid() {
                return unitid;
            }

            public void setUnitid(String unitid) {
                this.unitid = unitid;
            }

            public String getUnitvmlow() {
                return unitvmlow;
            }

            public void setUnitvmlow(String unitvmlow) {
                this.unitvmlow = unitvmlow;
            }

            public String getUnittemid() {
                return unittemid;
            }

            public void setUnittemid(String unittemid) {
                this.unittemid = unittemid;
            }

            public String getUnittem() {
                return unittem;
            }

            public void setUnittem(String unittem) {
                this.unittem = unittem;
            }

            public String getUnittemlow() {
                return unittemlow;
            }

            public void setUnittemlow(String unittemlow) {
                this.unittemlow = unittemlow;
            }

            public String getUnitidlow() {
                return unitidlow;
            }

            public void setUnitidlow(String unitidlow) {
                this.unitidlow = unitidlow;
            }
        }
    }
}
