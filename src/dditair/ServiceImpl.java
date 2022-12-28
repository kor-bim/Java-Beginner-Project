package dditair;

import java.util.ArrayList;
import java.util.Map;

public class ServiceImpl implements Service {

	private Dao dao = new DaoImpl();

	@Override
	public int insertMember(MemberVO newMember) {
		return dao.insertMember(newMember);
	}

	@Override
	public boolean dupId(String mem_id) {
		return dao.dupId(mem_id);
	}

	@Override
	public String memlogIn(Map<String, String> params) {
		return dao.memlogIn(params);
	}

	@Override
	public String adminLogIn(Map<String, String> params) {
		return dao.managerLogIn(params);
	}

	@Override
	public ArrayList<MemberVO> showMemList() {
		return dao.showMemList();
	}

	@Override
	public ArrayList<MemberVO> searchIdMemList(String mem_id) {
		return dao.searchIdList(mem_id);
	}

	@Override
	public int insertNotice(NoticeVO notice) {
		return dao.insertNotice(notice);
	}

	@Override
	public ArrayList<NoticeVO> readNotice() {
		return dao.readNotice();
	}

	@Override
	public ArrayList<NoticeVO> selectNotice(int noticeNo) {
		return dao.selectNotice(noticeNo);
	}

	// 비번수정
	@Override
	public int myPageUpdatePass(MemberVO m) {
		return dao.myPageUpdatePass(m);
	}

	// 폰번호수정
	@Override
	public int myPageUpdateHp(MemberVO m) {
		return dao.myPageUpdateHp(m);
	}

	// 여권수정
	@Override
	public int myPageUpdatePassport(MemberVO m) {
		return dao.myPageUpdatePassport(m);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return dao.deleteNotice(noticeNo);
	}

	@Override
	public ArrayList<AirportVO> airPortList() {
		return dao.airPortList();
	}

	@Override
	public ArrayList<ScheduleVO> choiceScheduleList(String departureDate,
			String departureAirPort, String arrivalAirPort) {
		ArrayList<ScheduleVO> list = dao.choiceScheduleList(departureDate,
				departureAirPort, arrivalAirPort);
		return list;
	}

	@Override
	public ArrayList<ReserVO> reservationList(String mem_id) {
		return dao.reservationList(mem_id);
	}

	@Override
	public int deleteReservation(String rvo, String mem_id) {
		return dao.deleteReservation(rvo, mem_id);
	}

	@Override
	public ArrayList<MemberVO> myPageMileage(String mem_id) {
		return dao.myPageMileage(mem_id);
	}

	@Override
	public int seatSelect(SeatVO seat, ScheduleVO schedule) {
		return dao.seatSelect(seat, schedule);
	}

	public void insertPlane(String airplane_model) {
		dao.insertPlane(airplane_model);

	}

	@Override
	public ArrayList<AirplaneVO> showPlaneList() {
		return dao.showPlaneList();
	}

	@Override
	public void updatePlane(Map<String, String> params) {
		dao.updatePlane(params);

	}

	@Override
	public void deletePlane(String airplane_no) {
		dao.deletePlane(airplane_no);

	}

	@Override
	public ArrayList<ScheduleVO> showScheduleList() {
		return dao.showScheduleList();
	}

	@Override
	public void updateScheduleApNo(Map<String, String> params) {
		dao.updateScheduleApNo(params);

	}

	@Override
	public void updateScheduleD(Map<String, String> params) {
		dao.updateScheduleD(params);
	}

	@Override
	public void updateScheduleApD(Map<String, String> params) {
		dao.updateScheduleApD(params);
	}

	@Override
	public void updateScheduleApA(Map<String, String> params) {
		dao.updateScheduleApA(params);
	}

	@Override
	public void updateScheduleInt(Map<String, String> params,
			Map<String, Integer> paramsInt) {
		dao.updateScheduleInt(params, paramsInt);

	}

	@Override
	public void deleteSchedule(String sc_no) {
		dao.deleteSchedule(sc_no);

	}

	@Override
	public void insertSchedule(ScheduleVO s) {
		dao.insertSchedule(s);

	}

	@Override
	public int insertBookInfo(String sc_no) {
		return dao.inserBookInfo(sc_no);

	}

	@Override
	public int reservation(String mem_id) {
		return dao.reservation(mem_id);

	}

	@Override
	public int deleteMember(String mem_id) {
		return dao.deleteMember(mem_id);
	}

	@Override
	public ArrayList<ReservationVO> reservationList1(String mem_id) {
		return dao.reservationList1(mem_id);
	}

	@Override
	public int chargeMileage(int inputMileage, String mem_id) {
		return dao.chargeMileage(inputMileage, mem_id);
	}

	@Override
	public int refundMileage(int refundMileage, String mem_id) {
		return dao.refundMileage(refundMileage, mem_id);
	}

}
