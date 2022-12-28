package dditair;

import java.util.ArrayList;
import java.util.Map;

public interface Service {
	/**
	 * 회원가입을 위한 메서드
	 * 
	 * @author 유수빈
	 * @param newMember
	 *            - MemberVO 타입으로 회원에게 입력받은 아이디,비밀번호,이름,핸드폰번호,여권번호가 있다.
	 * @return 회원등록에 성공하면 1, 아니면 0을 반환
	 * @since 2020.09.09
	 */
	int insertMember(MemberVO newMember);

	/**
	 * 아이디 중복검사하는 메서드
	 * 
	 * @author 유수빈
	 * @param mem_id
	 *            - String타입으로 회원에게 입력받은 아이디
	 * @return boolean타입으로 중복이 아니면 true, false면 중복
	 */
	boolean dupId(String mem_id);

	/**
	 * 회원 로그인을 위한 메서드
	 * 
	 * @author 유수빈
	 * @params mem_id - 회원에게 입력받은 아이디, mem_pass - 회원에게 입력받은 비밀번호
	 * @return id와 pw가 일치하는 한 명의 아이디. 없으면 null
	 * @since 2020.09.09
	 */
	String memlogIn(Map<String, String> params);

	/**
	 * 관리자 로그인을 위한 메서드
	 * 
	 * @author 유수빈
	 * @param manager_id
	 *            - 사용자(관리자)에게 입력받은 아이디, manager_pass - 사용자(관리자)에게 입력받은 비밀번호
	 * @return id와 pass가 일치하는 아이디, 없으면 null
	 * @since 2020.09.09
	 */
	String adminLogIn(Map<String, String> params);

	/**
	 * 관리자가 모든 고객을 조회하기 위한 메서드
	 * 
	 * @author 유수빈
	 * @return MemberVO 타입의 모든 회원의 정보를 list에 담아서 반환
	 */
	ArrayList<MemberVO> showMemList();

	/**
	 * id로 해당 회원의 정보를 조회하는 메서드
	 * 
	 * @author 유수빈
	 * @param mem_id
	 *            - 사용자에게서 입력받은 아이디
	 * @return MemberVO 타입의 해당 회원의 정보를 list에 담아서 반환
	 */
	ArrayList<MemberVO> searchIdMemList(String mem_id);

	/**
	 * 공지사항 등록을 위한 메서드
	 * 
	 * @author 유수빈
	 * @param notice
	 *            = NoticeVO타입으로 notice_title-사용자에게 입력받은 공지사항 제목,
	 *            notice_content-사용자에게 입력받은 공지사항 내용
	 * @return 공지사항 등록에 성공하면 1, 아니면 0
	 * @since 2020.09.09
	 */
	int insertNotice(NoticeVO notice);

	/**
	 * 공지사항을 조회하는 메서드
	 * 
	 * @author 유수빈
	 * @return NoticeVO 타입으로 모든공지사항을 list에 담아서 반환
	 */
	ArrayList<NoticeVO> readNotice();

	/**
	 * 선택한 글번호의 공지사항을 조회하는 메서드
	 * 
	 * @author 유수빈
	 * @param noticeNo
	 *            - 사용자에게서 입력받은 글 번호
	 * @return NoticeVO 타입으로 입력받은 글 번호의 모든 정보를 list에 담아서 반환
	 */
	ArrayList<NoticeVO> selectNotice(int noticeNo);

	/**
	 * 선택한 글번호의 공지사항을 삭제하는 메서드
	 * 
	 * @author 유수빈
	 * @param noticeNo
	 *            - 사용자에게서 입력받은 글 번호
	 * @return 해당 글번호의 정보를 삭제 성공하면 1, 삭제되지 않으면 0
	 */
	int deleteNotice(int noticeNo);

	/**
	 * 공항리스트를 출력하기 위한 메서드
	 * 
	 * @author 윤한빈
	 * @since 2020.09.09
	 * @return DB의 공항테이블에서 전체 컬럼값들을 가져온다(공항리스트 출력)
	 */
	ArrayList<AirportVO> airPortList();

	/**
	 * 조건으로 출발날짜, 출발지, 도착지를 모두 충족하는 스케쥴리스트를 출력하는 메서드
	 * 
	 * @param departureDate
	 *            (출발날짜), departureAirPort(출발지), arrivalAirPort(도착지)
	 * @author 윤한빈
	 * @since 2020.09.09
	 * @return DB의 스케쥴테이블에 입력한 출발날짜, 출발지, 도착지를 모두 충족하는 스케쥴리스트
	 */
	ArrayList<ScheduleVO> choiceScheduleList(String departureDate,
			String departureAirPort, String arrivalAirPort);

	/**
	 * 로그인된 회원의 아이디번호를 조건으로 DB의 예약테이블에서 예약리스트를 가져와 출력한다.
	 * 
	 * @param mem_id
	 *            (로그인된 회원의 아이디)
	 * @author 윤한빈
	 * @since 2020.09.09
	 * @return DB의 예약테이블에서 로그인된 회원의 아이디번호를 조건으로 예약리스트를 가져온다
	 */
	ArrayList<ReserVO> reservationList(String mem_id);

	/**
	 * 로그인된 회원의 예약정보 삭제를 위한 메서드
	 * 
	 * @param reserve_no
	 *            (로그인된 회원의 예약리스트에서 선택한 예약리스트번호), mem_id(로그인된 회의 번호)
	 * @author 윤한빈
	 * @param reserve_no
	 * @param reserve_seat
	 * @return 삭제한 예약테이블의 컬럼의 갯수(1개면 성공, 실패했다면 0개)
	 * @since 2020.09.09
	 */
	int deleteReservation(String reserve_no, String mem_id);

	/**
	 * 로그인된 회원의 마일리지 리스트를 출력
	 * 
	 * @param mem_id
	 *            (로그인된 회원번호)
	 * @author 윤한빈
	 * @since 2020.09.09
	 * @return 로그인된 회원을 조건으로 DB의 회원테이블에서 마일리지리스트를 가져온다
	 */
	ArrayList<MemberVO> myPageMileage(String mem_id);

	/**
	 * 로그인된 회원정보를 삭제하기 위한 메서드
	 * 
	 * @param mem_id
	 *            (로그인된 회원번호)
	 * @author 윤한빈
	 * @since 2020.09.09
	 * @return 삭제한 회원테이블의 컬럼의 갯수(1개면 성공, 실패했다면 0개)
	 */
	int deleteMember(String mem_id);

	/**
	 * 비행기번호, 좌석클래스번호, 좌석의 행, 좌석의 열 선택하여 DB의 좌석테이블에 값을 저장한다.
	 * 
	 * @param SeatVO
	 *            seat(비행기번호, 좌석클래스번호, 좌석의 행, 좌석의 열)
	 * @author 윤한빈
	 * @param schedule
	 * @return 등록판단여부
	 * @since 2020.09.09
	 */
	int seatSelect(SeatVO seat, ScheduleVO schedule);

	/**
	 * 비번수정
	 * 
	 * @param m
	 * @return
	 */
	int myPageUpdatePass(MemberVO m);

	/**
	 * 사용자가 핸드폰번호 수정
	 */
	int myPageUpdateHp(MemberVO m);

	/**
	 * 여권번호 수정
	 * 
	 * @param m
	 * @return
	 */

	int myPageUpdatePassport(MemberVO m);

	/**
	 * 관리자가 비행기 테이블에 값을 추가한다.
	 * 
	 * @param params
	 *            (비행기번호, 비행기모델명)
	 * @author 이재형
	 * @since 2020.09.09
	 */

	void insertPlane(String airplane_model);

	/**
	 * 현재 비행기 테이블에 있는 비행기 리스트를 출력하기 위한 메서드
	 * 
	 * @author 이재형
	 * @since 2020.09.09
	 * @return DB의 비행기테이블에있는 컬럼값들
	 */
	ArrayList<AirplaneVO> showPlaneList();

	/**
	 * 현재 비행기 테이블에 있는 컬럼값들을 수정하기 위한 메서드
	 * 
	 * @param params
	 *            (비행기이름, 비행기모델명)
	 * @author 이재형
	 * @since 2020.09.09
	 */
	void updatePlane(Map<String, String> params);

	/**
	 * 현재 비행기 테이블에 있는 컬럼값들을 삭제하기 위한 메서드
	 * 
	 * @param airplane_no
	 *            (관리자가 입력한 비행기번호)
	 * @author 이재형
	 * @since 2020.09.09
	 */
	void deletePlane(String airplane_no);

	/**
	 * 관리자가 스케줄을 등록하기 위한 메서드
	 * 
	 * @param s
	 *            (관리자가 입력한 스케줄VO)
	 * @author 이재형
	 * @since 2020.09.09
	 */
	void insertSchedule(ScheduleVO s);

	/**
	 * 현재 스케쥴 테이블에 있는 스케쥴 리스트를 출력하기 위한 메서드
	 * 
	 * @author 이재형
	 * @since 2020.09.09
	 * @return DB의 스케쥴 테이블에있는 컬럼값들
	 */
	ArrayList<ScheduleVO> showScheduleList();

	/**
	 * 현재 스케쥴 테이블에 있는 컬럼값들을 수정하기 위한 메서드
	 * 
	 * @param params
	 *            (가격)
	 * @author 이재형
	 * @param params
	 * @since 2020.09.09
	 */
	void updateScheduleInt(Map<String, String> params,
			Map<String, Integer> paramsInt);

	/**
	 * 현재 스케쥴 테이블에 있는 컬럼값들을 삭제하기 위한 메서드
	 * 
	 * @param sc_no
	 *            (관리자가 입력한 스케쥴 번호)
	 * @author 이재형
	 * @since 2020.09.09
	 */
	void deleteSchedule(String sc_no);

	/**
	 * 관리자가 스케줄 테이블에 있는 비행기번호 컬럼sc_airplane_no의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 * @param params
	 */
	void updateScheduleApNo(Map<String, String> params);

	/**
	 * 관리자가 스케줄 테이블에 있는 출발일시 컬럼sc_departure의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 * @param params
	 */
	void updateScheduleD(Map<String, String> params);

	/**
	 * 관리자가 스케줄 테이블에 있는 출발지 컬럼sc_airport_d의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 * @param params
	 */
	void updateScheduleApD(Map<String, String> params);

	/**
	 * 관리자가 스케줄 테이블에 있는 출발지 컬럼sc_airport_a의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 * @param params
	 */
	void updateScheduleApA(Map<String, String> params);

	int insertBookInfo(String sc_no);

	int reservation(String mem_id);

	ArrayList<ReservationVO> reservationList1(String mem_id);

	int chargeMileage(int inputMileage, String mem_id);

	int refundMileage(int refundMileage, String mem_id);

}
