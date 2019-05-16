package com.example.ycx36.newems.util;

public class getCarAllBean {


    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {


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


            private int id;
            private String uid;
            private String updatetime;
            private String motorspeed;
            private String motortorque;
            private String totalcurrent;
            private String totalvoltage;

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
        }
    }
}
