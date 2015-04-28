package com.example.hollytatan.eventfloc2;

import java.util.Date;

/**
 * Created by hollytatan on 28/04/15.
 */
    public class Society extends User {
        private int societyID;
        private String societyName;
        private Date approvalDate;
        private String description;
        private String societyFaculty;

        public Society(String userEmail, String password, String societyName,
                       Date approvalDate, String description, String societyFaculty) {
            super(userEmail, password, 2);

            this.societyName = societyName;
            this.approvalDate = approvalDate;
            this.description = description;
            this.societyFaculty = societyFaculty;
        }

        /**
         * Empty Constructor for deleting society
         */
        public Society() {

        }

        public String getSocietyFaculty() {
            return societyFaculty;
        }

        public void setSocietyFaculty(String societyFaculty) {
            this.societyFaculty = societyFaculty;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(Date approvalDate) {
            this.approvalDate = approvalDate;
        }

        public String getSocietyName() {
            return societyName;
        }

        public void setSocietyName(String societyName) {
            this.societyName = societyName;
        }

        public int getSocietyID() {
            return societyID;
        }

        public void setSocietyID(int societyID) {
            this.societyID = societyID;
        }



}
