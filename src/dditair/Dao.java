package dditair;

import java.util.ArrayList;
import java.util.Map;

public interface Dao {

	int insertMember(MemberVO newMember);

	boolean dupId(String mem_id);

	String memlogIn(Map<String, String> params);

	String managerLogIn(Map<String, String> params);

	ArrayList<MemberVO> showMemList();

	ArrayList<MemberVO> searchIdList(String mem_id);

	int insertNotice(NoticeVO notice);

	ArrayList<NoticeVO> readNotice();

	ArrayList<NoticeVO> selectNotice(int noticeNo);

	int deleteNotice(int noticeNo);

	ArrayList<ScheduleVO> choiceScheduleList(String departureDate,
			String departureAirPort, String arrivalAirPort);

	ArrayList<ReserVO> reservationList(String mem_id);

	ArrayList<MemberVO> myPageMileage(String mem_id);

	int seatSelect(SeatVO seat, ScheduleVO schedule);

	// 비번 수정
	int myPageUpdatePass(MemberVO m);

	// 여권수정
	int myPageUpdatePassport(MemberVO m);

	// 폰번호 수정
	int myPageUpdateHp(MemberVO m);

	ArrayList<AirportVO> airPortList();

	int deleteMember(String mem_id);

	int inserBookInfo(String sc_no);

	int reservation(String mem_id);

	void insertSchedule(ScheduleVO s);

	void insertPlane(String airplane_model);

	ArrayList<AirplaneVO> showPlaneList();

	void updatePlane(Map<String, String> params);

	void deletePlane(String airplane_no);

	ArrayList<ScheduleVO> showScheduleList();

	void updateScheduleInt(Map<String, String> params,
			Map<String, Integer> paramsInt);

	void deleteSchedule(String sc_no);

	void updateScheduleApNo(Map<String, String> params);

	void updateScheduleD(Map<String, String> params);

	void updateScheduleApD(Map<String, String> params);

	void updateScheduleApA(Map<String, String> params);

	int deleteReservation(String rvo, String mem_id);

	ArrayList<ReservationVO> reservationList1(String mem_id);

	int refundMileage(int refundMileage, String mem_id);

	int chargeMileage(int inputMileage, String mem_id);

}
