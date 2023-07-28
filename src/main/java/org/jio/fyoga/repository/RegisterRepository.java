package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 11:38 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Course;
import org.jio.fyoga.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository  extends JpaRepository<Register, Integer> {

    @Query("SELECT DISTINCT r.aDiscount.aPackage.course FROM Register r WHERE r.customer.accountID = ?1")
    List<Course> findCoursesByUserRegister(int AccountID);

    List<Register> findByCustomer_AccountID(int AccountID);
    //List<Register> findByCustomer_AccountIDAndStatusAndPackages_Course_CourseID(int AccountID, int status,int CourseID);

    List<Register> findByCustomer_AccountIDAndStatus(int AccountID, int status);

    @Query("SELECT MONTH(r.registeredDate) AS month, SUM(r.priceDiscount) AS totalAmount " +
            "FROM Register r " +
            //"WHERE r.status = 1 " +
            "GROUP BY MONTH(r.registeredDate)")
    List<Object[]> getMonthlyRegisterAmount();

    List<Register> findByStatusOrStatus(int statusPayingDone, int statusPayingUsing);

    Register findByStatus(int status);
    @Query("SELECT r FROM Register r " +
            "WHERE r.status = :status " +
            "AND r.aDiscount.aPackage.course.courseID = :courseID")
    Register findRegisterByStatusAndaDiscount_aPackage_course_courseID(int status, int courseID);

    //tim mối nhất

    Register findTopByStatusOrderByRegisteredDateDesc(int status);

    @Query("SELECT r FROM Register r " +
            "JOIN FETCH r.aDiscount a " +
            "JOIN FETCH a.aPackage ap " +
            "JOIN FETCH ap.course c " +
            "WHERE r.status = :status " +
            "AND c.courseID = :courseID " +
            "ORDER BY r.registeredDate DESC")
    List<Register> findTopByStatusAndADiscount_APackage_Course_CourseIDOrderByRegisteredDateDesc(int status, int courseID);
   //tìm cũ nhất
    Register findFirstByStatusOrderByRegisteredDateAsc(int status);
    @Query("SELECT r FROM Register r " +
            "JOIN FETCH r.aDiscount a " +
            "JOIN FETCH a.aPackage ap " +
            "JOIN FETCH ap.course c " +
            "WHERE r.status = :status " +
            "AND c.courseID = :courseID " +
            "ORDER BY r.registeredDate ASC")
    List<Register> findFirstByStatusAndADiscount_APackage_Course_CourseIDOrderByRegisteredDateAsc(int status);

}
