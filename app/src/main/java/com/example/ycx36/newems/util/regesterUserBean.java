package com.example.ycx36.newems.util;

public class regesterUserBean {



    private ResponseBean response;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public static class ResponseBean {


        private InfoBean info;
        private int content;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public int getContent() {
            return content;
        }

        public void setContent(int content) {
            this.content = content;
        }

        public static class InfoBean {


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
    }
}
