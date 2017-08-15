package domon.cn.mmphoto.data;

import java.util.List;

/**
 * Created by Domon on 2017/8/15.
 */

public class UserProfileData {


    /**
     * user : {"ID":156881,"UserCode":"1","Balance":40,"VIPType":0}
     * paid : [1, 2, 3]
     */

    private UserEntity user;
    private List<Integer> paid;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<?> getPaid() {
        return paid;
    }

    public void setPaid(List<Integer> paid) {
        this.paid = paid;
    }

    public static class UserEntity {
        /**
         * ID : 156881
         * UserCode : 1
         * Balance : 40
         * VIPType : 0
         */

        private int ID;
        private String UserCode;
        private int Balance;
        private int VIPType;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getUserCode() {
            return UserCode;
        }

        public void setUserCode(String UserCode) {
            this.UserCode = UserCode;
        }

        public int getBalance() {
            return Balance;
        }

        public void setBalance(int Balance) {
            this.Balance = Balance;
        }

        public int getVIPType() {
            return VIPType;
        }

        public void setVIPType(int VIPType) {
            this.VIPType = VIPType;
        }
    }
}
