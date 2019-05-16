package com.example.ycx36.newems.util;

public class requestRegisterUserBean {

    private RequestBean request;

    public RequestBean getRequest() {
        return request;
    }

    public void setRequest(RequestBean request) {
        this.request = request;
    }

    public static class RequestBean {

        private CommonBean common;
        private ContentBean content;

        public CommonBean getCommon() {
            return common;
        }

        public void setCommon(CommonBean common) {
            this.common = common;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public static class CommonBean {

            private String action;
            private String reqtime;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getReqtime() {
                return reqtime;
            }

            public void setReqtime(String reqtime) {
                this.reqtime = reqtime;
            }
        }

        public static class ContentBean {


            private Object id;
            private String name;
            private String phone;
            private String password;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }
        }
    }
}
