/*
 * package org.management.pojo;
 * 
 * import java.time.LocalDate;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.Id; import javax.persistence.Table;
 * 
 * import org.hibernate.annotations.CreationTimestamp; import
 * org.hibernate.annotations.UpdateTimestamp;
 * 
 * import lombok.Data;
 * 
 * @Data
 * 
 * @Entity
 * 
 * @Table(name = "USER_REGISTRATION_TBL") public class UserRegistration {
 * 
 * @Id
 * 
 * @Column(name = "USE_RREGISTRATION_NUMBER") private Integer
 * userRegistrationNumber;
 * 
 * @Column(name = "USER_FULL_NAME") private String userFullName;
 * 
 * @Column(name = "verification_code", length = 64) private String
 * verificationCode;
 * 
 * private boolean enabled;
 * 
 * @Column(name = "USER_EMAI_ID") private String userEmaiId;
 * 
 * @Column(name="password") private String password;
 * 
 * @Column(name = "USER_MOBILE_NUMBER") private Long userMobileNumber;
 * 
 * @Column(name = "USER_DOB") private LocalDate userDob;
 * 
 * @Column(name = "SSN") private Long ssn;
 * 
 * public String getVerificationCode() { return verificationCode; }
 * 
 * public void setVerificationCode(String verificationCode) {
 * this.verificationCode = verificationCode; }
 * 
 * public boolean isEnabled() { return enabled; }
 * 
 * public void setEnabled(boolean enabled) { this.enabled = enabled; }
 * 
 * @Column(name = "GENDER") private Character gender;
 * 
 * @Column(name = "CREATED_DATE", updatable = false)
 * 
 * @CreationTimestamp private LocalDate createdDate;
 * 
 * @Column(name = "UPDATED_DATE", insertable = false)
 * 
 * @UpdateTimestamp private LocalDate updatedDate;
 * 
 * @Column(name = "CREATED_BY") private String createdBy;
 * 
 * @Column(name = "UPDATED_BY") private String updatedBy;
 * 
 * public String getUserFullName() { return userFullName; }
 * 
 * public void setUserFullName(String userFullName) { this.userFullName =
 * userFullName; }
 * 
 * public String getUserEmaiId() { return userEmaiId; }
 * 
 * public void setUserEmaiId(String userEmaiId) { this.userEmaiId = userEmaiId;
 * }
 * 
 * public Long getUserMobileNumber() { return userMobileNumber; }
 * 
 * public void setUserMobileNumber(Long userMobileNumber) {
 * this.userMobileNumber = userMobileNumber; }
 * 
 * public LocalDate getUserDob() { return userDob; }
 * 
 * public void setUserDob(LocalDate userDob) { this.userDob = userDob; }
 * 
 * public Long getSsn() { return ssn; }
 * 
 * public void setSsn(Long ssn) { this.ssn = ssn; }
 * 
 * public LocalDate getCreatedDate() { return createdDate; }
 * 
 * public void setCreatedDate(LocalDate createdDate) { this.createdDate =
 * createdDate; }
 * 
 * public LocalDate getUpdatedDate() { return updatedDate; }
 * 
 * public void setUpdatedDate(LocalDate updatedDate) { this.updatedDate =
 * updatedDate; }
 * 
 * public String getCreatedBy() { return createdBy; }
 * 
 * public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
 * 
 * public String getUpdatedBy() { return updatedBy; }
 * 
 * public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
 * 
 * public Character getGender() { return gender; }
 * 
 * public void setGender(Character gender) { this.gender = gender; }
 * 
 * public Integer getUserRegistrationNumber() { return userRegistrationNumber; }
 * 
 * public void setUserRegistrationNumber(Integer userRegistrationNumber) {
 * this.userRegistrationNumber = userRegistrationNumber; }
 * 
 * 
 * public String getPassword() { return password; }
 * 
 * public void setPassword(String password) { this.password = password; }
 * 
 * public String getEmail() { // TODO Auto-generated method stub return null; }
 * 
 * }
 */