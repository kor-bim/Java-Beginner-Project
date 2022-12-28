package dditair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dditair.MemberVO;
import dditair.AirportVO;
import dditair.ScheduleVO;

public class View {
	private Scanner sc = new Scanner(System.in);
	private Service service = new ServiceImpl();
	private String logIn_id;
	private String logIn_manager;
	Regexp regx = new Regexp();

	/**
	 * 프로그램 시작시 처음으로 시작되는 메뉴화면
	 * 
	 * @author 유수빈
	 * 
	 */
	public void startMethod() {
		while (true) {

			System.out.println("┌────────────────────────┐");
			System.out.println("│   반갑습니다.대덕항공입니다.   │");
			System.out.println("└────────────────────────┘");
			System.out.println("─────원하는 메뉴를 선택해주세요.─────");
			System.out.println("         1.회원가입");
			System.out.println("         2. 로그인");
			System.out.println("         3. EXIT");

			int input = 0;

			try {
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();// https://hashcode.co.kr/questions/6377/ 무한루프해결방법
				continue;
			}

			switch (input) {
			case 1:
				// 회원가입하는 메서드
				insertMember();
				break;

			case 2:
				// 로그인하는 메서드
				startLogIn();
				break;

			case 3:
				System.exit(0);
				break;

			default:
				System.out.println("잘못입력하셨습니다.");
				break;
			} // 스위치문
		}// while 문

	}// 메서드

	/**
	 * 회원가입을 위한 메서드
	 * 
	 * @author 유수빈
	 */
	private void insertMember() {
		MemberVO newMember = new MemberVO();

		String mem_id = creId();
		newMember.setMem_id(mem_id);

		String mem_pass = crePass();
		newMember.setMem_pass(mem_pass);

		String mem_name = creName();
		newMember.setMem_name(mem_name);

		String mem_passport = crePassport();
		newMember.setMem_passport(mem_passport);

		String mem_hp = creHp();
		newMember.setMem_hp(mem_hp);

		int insertresult = service.insertMember(newMember);

		if (insertresult > 0) {
			System.out.println(mem_name + "님 대덕항공의 가족이 되었습니다.");
		} else {
			System.out.println("다시 회원가입을 시도해주세요.");
		}
	}// 메서드

	/**
	 * 아이디를 입력받고 형식을 검사하는 메서드
	 * 
	 * @author 유수빈
	 * @return mem_id=사용자한테 입력받은 아이디
	 */
	private String creId() {
		while (true) {// 아이디 맞을때까지 돌림
			System.out.println("아이디를 입력해주세요. 영어, 소문자 조합");
			String mem_id = sc.next();
			if (regx.checkId(mem_id)) {
				if (dupId(mem_id)) {
					return mem_id;
				} else {
					System.out.println("중복된 아이디입니다.");
					sc.nextLine();
					continue;
				}
			} else {
				System.out.println("형식에 맞게 다시 입력해주세요.");
				sc.nextLine();
				continue;
			}
		}

	}

	/**
	 * 아이디의 중복을 검사하는 메서드
	 * 
	 * @author 유수빈
	 * @param mem_id
	 *            =사용자한테 입력받은 아이디
	 * @return 1이면 중복아님 0이면 중복
	 */
	private boolean dupId(String mem_id) {
		boolean dupid = service.dupId(mem_id);
		if (dupid) {
			System.out.println("사용가능한 아이디입니다.");
			return true;
		} else {
			return false;

		}
	}

	/**
	 * 비밀번호 입력받고 형식을 검사하는 메서드
	 * 
	 * @author 유수빈
	 * @return mem_pass=사용자한테 입력받은 비밀번호
	 */
	private String crePass() {
		while (true) {
			System.out.println("비밀번호를 입력해주세요. 최소4자리부터 영문자 또는 숫자만 가능합니다.");
			String mem_pass = sc.next();

			if (regx.checkPass(mem_pass)) {// 정규식
				return mem_pass;
			} else {
				System.out.println("양식에 맞게 다시 입력해주세요.");
				sc.nextLine();
				continue;
			}
		}
	}

	/**
	 * 이름을 입력받고 형식을 검사하는 메서드
	 * 
	 * @author 유수빈
	 * @return mem_name=사용자한테 입력받은 이름
	 */
	private String creName() {
		while (true) {
			System.out.println("이름을 입력해주세요.");
			String mem_name = sc.next();

			if (regx.checkName(mem_name)) {// 정규식
				return mem_name;

			} else {
				System.out.println("이름은 한글만 입력이 가능합니다.");
				sc.nextLine();
				continue;
			}
		}// while
	}// 메서드

	/**
	 * 핸드폰 번호를 입력받아 형식을 검사하는 메서드
	 * 
	 * @author 유수빈
	 * @return mem_hp=사용자한테 입력받은 핸드폰번호
	 */
	private String creHp() {
		while (true) {
			System.out.println("핸드폰 번호를 입력해주세요.예)01012345678");
			String mem_hp = sc.next();
			if (regx.checkHp(mem_hp)) {// 정규식
				return mem_hp;
			} else {
				System.out.println("형식에 맞게 다시입력해주세요.");
				sc.nextLine();
				continue;
			}
		}
	}

	/**
	 * 여권 번호를 입력받아 형식을 검사하는 메서드
	 * 
	 * @author 유수빈
	 * @return mem_passport=사용자한테 입력받은 여권번호
	 */
	private String crePassport() {
		while (true) {
			System.out.println("여권번호를 입력해주세요. 처음 문자는 영어 대문자입니다.");
			String mem_passport = sc.next();
			if (regx.checkPassport(mem_passport)) {
				return mem_passport;
			} else {
				System.out.println("여권번호 형식이 아닙니다. 다시입력해주세요.");
				sc.nextLine();
				continue;
			}
		}
	}

	/**
	 * 로그인 선택시 뜨는 창(로그인을 위한 메서드)
	 * 
	 * @author 유수빈
	 * 
	 */
	private void startLogIn() {
		while (true) {
			System.out.println("──────로그인 하시겠습니까?──────");
			System.out.println("1. 관리자로그인");
			System.out.println("2. 회원로그인");
			System.out.println("3. 뒤로가기");

			int input = 0;
			try {
				input = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			switch (input) {
			case 1:
				// 관리자로그인
				adminLogIn();
				break;
			case 2:
				// 회원로그인
				memberLogIn();
				break;
			case 3:
				// 뒤로가기
				return;
			default:
				System.out.println("다시입력해주세요.");
				break;
			}// 스위치문
		}// while
	}// 메서드

	/**
	 * 회원이 로그인하는 메서드
	 * 
	 * @author 유수빈
	 */
	private void memberLogIn() {
		while (true) {
			System.out.println("──────아이디를 입력해주세요.──────");
			String mem_id = sc.next();

			System.out.println("──────비밀번호를 입력해주세요.─────");
			String mem_pass = sc.next();

			Map<String, String> params = new HashMap<>();
			params.put("mem_id", mem_id);
			params.put("mem_pass", mem_pass);

			// service에 params값 보내줌
			// sql
			mem_id = service.memlogIn(params);
			int input = 0;
			while (true) {
				if (mem_id != null) {
					System.out.println(mem_id + "님 어서오세요.");
					memberStart(mem_id);// 로그인성공하면 회원창불러옴
					return;
				} else {
					System.out.println("잘못된 정보입니다.");
					System.out.println("뒤로가시겠습니까?");
					System.out.println("1. 뒤로가기");
					System.out.println("2. 머무르기");
					try {
						input = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("숫자만 입력해주세요.");
						sc.nextLine();
						continue;

					}
					switch (input) {
					case 1:
						return;
					case 2:
						break;
					default:
						System.out.println("다시 입력해주세요.");
						// continue;
						break;
					}
				}
				if (input == 2) {
					break;
				}
			}// while

		}// 메서드
	}

	/**
	 * 관리자 로그인을 위한 메서드
	 * 
	 * @author 유수빈
	 */
	private void adminLogIn() {

		while (true) {
			System.out.println("───────아이디를 입력해주세요.────────");
			String manager_id = sc.next();

			System.out.println("───────비밀번호를 입력해주세요.───────");
			String manager_pass = sc.next();
			Map<String, String> params = new HashMap<>();
			params.put("manager_id", manager_id);
			params.put("manager_pass", manager_pass);

			logIn_manager = service.adminLogIn(params);

			while (true) {
				int input = 0;
				if (logIn_manager != null) {
					System.out.println("안녕하세요 admin님");
					adminStart(); // 관리자 로그인 성공하면 관리자 창을 넘겨줘야함
					return;
				} else {
					System.out.println("잘못된 정보입니다.");
					System.out.println("뒤로가시겠습니까?");
					System.out.println("1. 뒤로가기");
					System.out.println("2. 머무르기");

					try {
						input = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("숫자만 입력해주세요.");
						sc.nextLine();
						continue;
					}
					switch (input) {
					case 1:
						return;
					case 2:
						break;
					default:
						System.out.println("다시입력해주세요.");
						break;

					}// switch
				}
				if (input == 2) {
					break;
				}
			}// while
		}
	}

	/**
	 * 관리자 메뉴 메서드
	 * 
	 * @author 유수빈
	 * 
	 */
	private void adminStart() {
		while (true) {
			System.out.println("관리자 창입니다.");
			System.out.println("원하는 메뉴를 선택해주세요.");
			System.out.println("1. 비행기관리");
			System.out.println("2. 스케줄관리");
			System.out.println("3. 회원관리");
			System.out.println("4. 공지사항관리");
			System.out.println("5. 뒤로가기");

			int input = 0;
			try {
				input = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}

			switch (input) {
			case 1:
				// 비행기관리
				adminPlane();
				break;
			case 2:
				// 스케줄관리
				adminSchedule();
				break;
			case 3:
				// 회원관리
				adminMemeberInfo();
				break;
			case 4:
				// 공지사항관리
				adminNotice();
				break;
			case 5:
				// 뒤로가기
				return;

			default:
				System.out.println("다시입력해주세요.");
				break;
			}// 스위치문

		}// while

	}

	/**
	 * 고객관리 초기메뉴창
	 * 
	 * @author 유수빈
	 * 
	 */
	private void adminMemeberInfo() {
		while (true) {
			System.out.println("─────────────회원 정보 관리──────────────");
			System.out.println("1. 모든 회원정보 보기");
			System.out.println("2. 특정 회원정보 보기");
			System.out.println("3. 뒤로가기");

			int input = 0;
			try {
				input = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			switch (input) {
			case 1:
				// 회원정보 모두 보는 메서드
				showAllMemList();
				break;

			case 2:
				// 특정회원정보 보기
				searchMem();
				break;

			case 3:
				// 뒤로가기
				return;
			default:
				System.out.println("다시입력해주세요.");
				break;
			}// switch

		}// while
	}

	/**
	 * 전체회원 조회 메서드
	 * 
	 * @author 유수빈
	 * 
	 */
	private void showAllMemList() {
		// sql
		ArrayList<MemberVO> list = service.showMemList();
		try {
			if (list.size() == 0) {
				System.out.println("회원이 없습니다");
			} else {
				for (MemberVO mb : list) {
					System.out.println("┌─────────────────────────┐");
					System.out.println("  회원 아이디: " + mb.getMem_id() + "\t");
					System.out.println("  회원 비밀번호: " + mb.getMem_pass() + "\t");
					System.out.println("  회원 이름: " + mb.getMem_name() + "\t");
					System.out.println("  회원 등급: " + mb.getMem_level() + "\t");
					System.out.println("  회원 마일리지: " + mb.getMem_mileage()
							+ "\t");
					System.out.println("  회원 전화번호: " + mb.getMem_hp() + "\t");
					System.out.println("  회원 여권번호: " + mb.getMem_passport()
							+ "\t");
					System.out.println("└─────────────────────────┘");
					System.out.println();

				}
			}

		} catch (NullPointerException e) {
			System.out.println("회원이 없습니다.");
			sc.nextLine();
		}
	}

	/**
	 * 회원검색 메서드(ID)
	 * 
	 * @author 유수빈
	 * 
	 */
	private void searchMem() {
		// sql
		while (true) {
			ArrayList<MemberVO> list = service.showMemList();

			int num = 1;
			System.out.println("─────────────ID로 회원검색───────────────");
			try {
				for (MemberVO mb : list) {
					System.out.print("선택 번호: " + num + "\t");
					System.out.println("회원 아이디: " + mb.getMem_id() + "\t");
					System.out.println("─────────────────────────────────────");
					num++;
				}
			} catch (NullPointerException e) {
				System.out.println("회원이 없습니다.");
				continue;
			}
			int input = 0;
			System.out.println();
			System.out.println("조회하고 싶은 회원을 입력해주세요.");
			System.out.println("뒤로가기는 0번");

			try {
				input = sc.nextInt();// try-catch 필요
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}

			switch (input) {
			case 0:
				return;

			}
			try {
				String memberId = list.get(input - 1).getMem_id();
				ArrayList<MemberVO> searchList = service
						.searchIdMemList(memberId);
				// sql SELECT * FROM NOTICE WHERE NOTICE_NO = 'index'
				// 반환타입은 noticeList

				for (MemberVO mb : searchList) {
					System.out.println("┌─────────────────────────┐");
					System.out.println("  회원 아이디: " + mb.getMem_id() + "\t");
					System.out.println("  회원 비밀번호: " + mb.getMem_pass() + "\t");
					System.out.println("  회원 이름: " + mb.getMem_name() + "\t");
					System.out.println("  회원 등급: " + mb.getMem_level() + "\t");
					System.out.println("  회원 마일리지: " + mb.getMem_mileage()
							+ "\t");
					System.out.println("  회원 전화번호: " + mb.getMem_hp() + "\t");
					System.out.println("└─────────────────────────┘");
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("범위를 벗어났습니다. 다시입력해주세요.");
				sc.nextLine();
				continue;
			} catch (NullPointerException e) {
				System.out.println("회원이 없습니다.");
				continue;
			}
		}
	}

	/**
	 * 관리자공지 메뉴 메서드
	 * 
	 * @author 유수빈
	 * 
	 */
	private void adminNotice() {
		while (true) {

			System.out.println("──────공지사항을 관리하는 페이지 입니다──────");
			System.out.println("1. 공지사항 등록하기");
			System.out.println("2. 공지사항 조회하기");
			System.out.println("3. 공지사항 삭제하기");
			System.out.println("4. 뒤로가기");

			int input = 0;

			try {
				input = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			switch (input) {
			case 1:
				// 공지사항 등록
				insertNotice();
				break;
			case 2:
				// 공지사항 조회
				readNotice();
				break;
			case 3:
				// 공지사항 삭제하기
				deleteNotice();
				break;
			case 4:
				// 뒤로가기
				return;
			default:
				System.out.println("다시입력해주세요.");
				break;

			}// switch

		}// while
	}

	/**
	 * 관리자가 공지사항을 등록하는 매서드
	 * 
	 * @author 유수빈
	 * 
	 */
	private void insertNotice() {
		// sql
		NoticeVO notice = new NoticeVO();
		// service.insertNotice(notice);
		while (true) {
			System.out.println("──────공지사항 제목을 입력해주세요─────");
			String title = sc.nextLine();

			System.out.println("─────공지사항 내용을 입력해주세요─────");
			String content = sc.next();
			sc.nextLine();

			notice.setNotice_title(title);
			notice.setNotice_content(content);

			int insertresult = service.insertNotice(notice);
			if (insertresult > 0) {
				System.out.println("등록에 성공했습니다.");
				break;
			} else {
				System.out.println("등록에 실패했습니다.");
			}

		}
	}

	/**
	 * 공지사항을 조회하는 메서드
	 * 
	 * @author 유수빈
	 */
	private void readNotice() {
		// sql
		while (true) {
			ArrayList<NoticeVO> list = service.readNotice();
			int num = 1;
			System.out.println("─────────────공지사항 게시판───────────────");
			if (list.size() == 0) {
				System.out.println("공지사항이 없습니다.");
			}
			for (NoticeVO nvo : list) {
				System.out.print("글 번호: " + num + "\t");
				System.out.println("글 제목: " + nvo.getNotice_title() + "\t");
				System.out.println("─────────────────────────────────────");
				num++;
			}
			int input = 0;
			System.out.println();
			System.out.println("열람하고 싶은 글 번호를 입력해주세요.");
			System.out.println("뒤로가기는 0번");

			try {
				input = sc.nextInt();// try-catch 필요
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}

			switch (input) {
			case 0:
				return;

			}
			try {
				int noticeNo = list.get(input - 1).getNotice_no();
				ArrayList<NoticeVO> selectList = service.selectNotice(noticeNo); // 해당
																					// 글번호만
																					// 반환

				// sql SELECT * FROM NOTICE WHERE NOTICE_NO = 'index'
				// 반환타입은 noticeList

				for (NoticeVO nvo : selectList) {
					System.out
							.println("──────────────────────────────────────────");
					System.out.println("글 번호" + input);
					System.out.println("글 제목: " + nvo.getNotice_title());
					System.out.println("작성일자: " + nvo.getNotice_date());
					System.out.println("작성자: " + nvo.getManager_id());
					System.out.println("글 내용: " + nvo.getNotice_content());
					System.out
							.println("──────────────────────────────────────────");

				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("범위를 벗어났습니다. 다시입력해주세요.");
				sc.nextLine();
				continue;
			}
		}

	}

	// ArrayList<NoticeVO> list = service.selectNotice();

	/**
	 * 공지사항을 삭제하는 메서드
	 * 
	 * @author 유수빈
	 */
	private void deleteNotice() {
		// sql

		while (true) {
			ArrayList<NoticeVO> list = service.readNotice();
			System.out.println();
			System.out.println("공지사항을 삭제합니다.");
			int num = 1;
			if (list.size() == 0) {
				System.out.println("공지사항이 없습니다.");
			}
			try {
				for (NoticeVO nvo : list) {// 출력만 제목이고 안에 들어있음

					System.out.println("──────────────────────────────────");
					System.out.println("글 번호: " + num);
					// System.out.println("글 번호: "+nvo.getNotice_no()+"\t");
					System.out.println("글 제목: " + nvo.getNotice_title() + "\t");
					System.out.print("───────────────────────────────────");
					System.out.println();
					num++;
				}
			} catch (NullPointerException e) {
				System.out.println("공지사항이 없습니다.");
				sc.nextLine();
			}
			System.out.println("삭제하고 싶은 글의 번호를 입력해주세요.");
			System.out.println("0. 뒤로가기");

			int input = 0;
			try {
				input = sc.nextInt(); // 삭제하고싶은 글번호, try-catch해야함
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			switch (input) {
			case 0:
				return;
			}

			int deleteresult = 0;
			try {
				int noticeNo = list.get(input - 1).getNotice_no();
				// ArrayList<NoticeVO> selectList =
				// service.selectNotice(noticeNo);
				deleteresult = service.deleteNotice(noticeNo);
			} catch (IndexOutOfBoundsException e) {
				System.out.print("글이 없습니다.");
				sc.nextLine();
				continue;
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}

			if (deleteresult > 0) { // dao에서 삭제하고 성공하면 삭제되었다고 출력
				System.out.println("삭제되었습니다");
			} else {
				System.out.println("삭제되지 않았습니다.");
			}
			// sql DELETE FROM NOTICE WHERE NOTICE_NO = index;

		}
	}

	/**
	 * 공지사항을 삭제하는 메서드
	 * 
	 * @param mem_id
	 *            =로그인된 회원의 아이디를 가져온다.
	 * @author 윤한빈
	 * 
	 */

	private void memberStart(String mem_id) {
		while (true) {

			System.out.println("회원창입니다.");
			System.out.println("원하시는 메뉴를 선택해주세요.");
			System.out.println("1.티켓예약");
			System.out.println("2.마이페이지");
			System.out.println("3.공지사항보기");
			System.out.println("4.뒤로가기");

			int input = 0;
			try {
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}

			switch (input) {
			case 1:
				// 티켓예약메서드
				memberReservation(mem_id);
				break;
			case 2:
				// 마이페이지
				myPage(mem_id);
				break;
			case 3:
				// 공지사항메서드
				readNotice();
				break;
			case 4:
				// 로그아웃
				return;

			default:
				System.out.println("다시입력해주세요.");
				break;

			}// 스위치문

		}

	}

	/**
	 * 로그인된 회원의 예매 메뉴
	 * 
	 * @param mem_id
	 *            =로그인된 회원의 아이디를 가져온다.
	 * @author 윤한빈
	 * 
	 */

	private void memberReservation(String mem_id) {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("────────────티켓예매──────────");
			System.out.println("1.예매하기");
			System.out.println("2.뒤로가기");
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			switch (select) {
			case 1:
				String departureDate = date();

				// 출발지선택 메서드 //DB에서 공항명리스트출력 && 공항선택 메서드
				String departureAirPort = selectDeparture();
				// 도착지 선택 메서드 //DB에서 공항명리스트출력 && 공항선택 메서드
				String arrivalAirPort = selectArrival();
				// 운항편선택 메서드 // 출발날짜, 출발지, 도착지
				ScheduleVO schedule = selectAirLine(departureDate,
						departureAirPort, arrivalAirPort);
				// 선택한 운항리스트를 ArrayList
				// 타입의 air_id에 저장
				seatSelect(schedule);
				// 좌석선택 메서드
				reservation(mem_id);

				break;
			case 2:
				return;
			}// switch문
		}// while문
	}

	private void reservation(String mem_id) {
		
		service.reservation(mem_id);
		
	}

	/**
	 * 예매할때 출발날짜 선택
	 * 
	 * @author 윤한빈
	 * @return uDate=선택한날짜(String)
	 */

	private String date() {
		System.out.println("날짜를 입력해주세요(yyyy/mm/dd)");
		// String uDate = kb.nextLine();
		// int userDate = Integer.parseInt(uDate);
		// int length = uDate.length();
		//
		// try {
		// DateFormat df = new SimpleDateFormat("yy/MM/dd/");
		// df.setLenient(false);
		// df.parse(uDate);
		// System.out.println(uDate + "가능하지않는 날짜입니다");
		//
		// } catch (ParseException e) {
		// System.out.println(uDate + "가능한 날짜입니다");
		// }
		// return uDate;
		String date = sc.next();
		return date;
	}

	/**
	 * 공항리스트 출력하는 메서드
	 * 
	 * @author 윤한빈
	 * 
	 */

	private String selectDeparture() {
		// sql
		ArrayList<AirportVO> list = service.airPortList();
		int count = 0;
		if (list == null) {
			System.out.println("출발지 정보가 존재하지않습니다.");
			return null;
		}

		System.out.println();
		System.out.println("───────────공항리스트───────────");
		for (AirportVO airport : list) {
			System.out.println(count + 1 + "번 " + airport.getAirport_no() + " "
					+ airport.getAirport_name() + " "
					+ airport.getAirport_country());
			System.out.println("──────────────────────────");
			count++;
		}

		System.out.println();
		
		int select = 0;
		while (true) {
			System.out.println("출발 공항 번호를 선택해주세요");
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			break;
		}
		String departureAirPort = (list.get(select - 1)).getAirport_no();
		System.out.println("출발지 선택: " + departureAirPort);
		return departureAirPort;
	}

	/**
	 * 예매할때 도착지 선택
	 * 
	 * @author 윤한빈
	 * @return 도착 공항명
	 */

	private String selectArrival() {
		// sql
		ArrayList<AirportVO> list = service.airPortList();
		int count = 0;
		if (list == null) {
			System.out.println("도착지 정보가 존재하지않습니다.");
			return null;
		}
		System.out.println();
		System.out.println("공항리스트");
		for (AirportVO airport : list) {
			System.out.println(count + 1 + "번 " + airport.getAirport_no() + " "
					+ airport.getAirport_name() + " "
					+ airport.getAirport_country());
			count++;
		}

		int select = 0;
		while (true) {
			System.out.println("도착 공항 번호를 선택해주세요");
			try {
				select = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			break;
		}
		String arrivalAirPort = (list.get(select - 1)).getAirport_no();
		System.out.println("도착지 선택: " + arrivalAirPort);
		return arrivalAirPort;
	}

	/**
	 * 고른 스케쥴을 보여주는 메서드
	 * 
	 * @param 출발날짜
	 *            , 출발지, 도착지
	 * @author 윤한빈
	 * @return 스케쥴
	 */

	private ScheduleVO selectAirLine(String departureDate,
			String departureAirPort, String arrivalAirPort) {
		// sql
		ArrayList<ScheduleVO> list = service.choiceScheduleList(departureDate,
				departureAirPort, arrivalAirPort);
		int count = 1;
		if (list == null) {
			System.out.println("도착지 정보가 존재하지않습니다.");
			return null;
		}
		System.out.println();
		System.out.println("운항정보를 선택해주세요");
		for (ScheduleVO s : list) {
			System.out.println(count + "번 " + s.getSc_airplane_no() + " "
					+ s.getSc_airport_d() + " " + s.getSc_airport_a() + " "
					+ s.getSc_departure() + " ");
			count++;
		}

		int select = 0;

		while (true) {
			System.out.println("스케쥴번호를 선택해주세요");
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			break;
		}
		try {
			ScheduleVO schedule = list.get(select - 1);
			System.out.println();
			return schedule;

		} catch (Exception e) {
			System.out.println("실패하였습니다");
		}
		return null;
	}

	/**
	 * 좌석 선택메서드
	 * 
	 * @param 스케쥴
	 *            =선택한 스케쥴
	 * @author 윤한빈
	 */

	private void seatSelect(ScheduleVO schedule) {

		SeatVO seat = new SeatVO();

		System.out.println("───────클래스를 입력해주세요───────");
		System.out.println("		1. FIRST");
		System.out.println("		2. BUSINESS");
		System.out.println("		3. ECONOMY");
		System.out.println("────────────────────────────");
		int select = sc.nextInt();
		switch (select) {
		case 1:
			seat.setClass_no("FIRST");
			break;
		case 2:
			seat.setClass_no("BUSINESS");
			break;
		case 3:
			seat.setClass_no("ECONOMY");
			break;

		default:
			break;
		}

		System.out.println("─────좌석의 행을 입력해주세요────");
		String hang = sc.next();
		seat.setHang(hang);
		System.out.println("─────좌석의 열을 입력해주세요─────");
		String yeol = sc.next();
		seat.setYeol(yeol);

		// sql
		try {
			int result = service.seatSelect(seat, schedule);
			if (result > 0) {
				System.out.println("성공하였습니다");
			} else {
				System.out.println("실패하였습니다");
			}
		} catch (Exception e) {
			System.out.println("(예외처리)실패하였습니다");
		}
	}

	/**
	 * 마이페이지 메뉴
	 * 
	 * @param mem_id
	 *            로그인된 회원의 회원 번호
	 * @author 윤한빈
	 */
	private void myPage(String mem_id) {
		while (true) { // 가져온다
			System.out.println("─────────마이페이지─────────");
			System.out.println("1. 내정보 조회");
			System.out.println("2. 내정보 수정");
			System.out.println("3. 예약관리");
			System.out.println("4. 회원탈퇴");
			System.out.println("5. 마일리지 관리");
			System.out.println("6. 뒤로가기");
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			switch (select) {
			case 1:
//				System.out.println("내정보 ㅈ");
				// 로그인한 회원정보를 볼 수있는 메서드
				myPageSelect(mem_id);
				break;
			case 2:
//				System.out.println("내정보 수정");
				// 로그인한 회원정보를 볼 수있는 메서드
				myPageUpdateMember(mem_id);
				break;
			case 3:
//				System.out.println("2. 예약관리");

				// 예약조회, 예약취소할 수 있는 메서드
				myPageReservation(mem_id);

				break;
			case 4:
//				System.out.println("3. 회원탈퇴");
				// 회원탈퇴하는 메서드
				deleteMember(mem_id);
				break;
			case 5:
//				System.out.println("4. 마일리지 관리");
				// 마일리지 충전, 조회, 환불 가능한 메서드
				myPageMileage(mem_id);
				break;
			case 6:
				return;
			default:
				break;
			}
		}
	}

	 /**
	  * 내정보 출력
	  * @param mem_id
	  */
	 private void myPageSelect(String mem_id) {
	      
	      ArrayList<MemberVO> myInfo = service.searchIdMemList(mem_id);
	      System.out.println("─────"+mem_id+"님의 회원정보입니다─────");
	      for(MemberVO mb : myInfo){
	         System.out.println("아이디: "+mb.getMem_id());
	         System.out.println("비밀번호: "+mb.getMem_pass());
	         System.out.println("이름: "+ mb.getMem_name());
	         System.out.println("전화번호: "+mb.getMem_hp());
	         System.out.println("회원등급: "+mb.getMem_level());
	         System.out.println("마일리지: "+mb.getMem_mileage());
	         System.out.println("여권번호: "+mb.getMem_passport());
	         System.out.println("──────────────────────────────");
	      }
	      while(true){
	         System.out.println("0. 뒤로가기");
	         
	         int input = 0;
	         try{
	            input = sc.nextInt();
	         }catch(InputMismatchException e){
	            System.out.println("숫자만 입력해주세요.");
	            sc.nextLine();
	            continue;
	         }
	         switch(input){
	         case 0:
	            return;
	         default :
	            System.out.println("다시입력해주세요.");
	            sc.nextLine();
	            continue;
	         }
	      }
	 }

	
	/**
	 * 회원정보 수정하는 메서드
	 * 
	 * @param mem_id
	 *            로그인된 회원의 회원 번호
	 * @author 윤한빈
	 */
	private void myPageUpdateMember(String mem_id) {
//	    MemberVO m = new MemberVO();
	    
	    while(true){
	       System.out.println("──────회원정보를 수정합니다.──────");
	      System.out.println("1. 비밀번호 수정");
	      System.out.println("2. 핸드폰번호 수정");
	      System.out.println("3. 여권번호 수정");
	      System.out.println("4. 뒤로가기");
	      
	      int input = 0;
	      try{
	         input = sc.nextInt();
	      }catch(InputMismatchException e){
	         System.out.println("숫자만 입력해주세요.");
	         sc.nextLine();
	         continue;
	      }
	      
	      switch(input){
	      
	      case 1:
	         //비밀번호 수정
	         myPageUpdatePass(mem_id);
	         break;
	      case 2:
	         //핸드폰번호 수정
	         myPageUpdateHp(mem_id);
	         break;
	      case 3:
	         //여권번호수정
	         myPageUpdatePassport(mem_id);
	         break;
	      case 4:
	         return;
	      default:
	         System.out.println("다시입력해주세요.");
	         sc.nextLine();
	         continue;
	      }
	      
	      
	    }//while
	 }//메서드   

	
	 //비번변경 메서드
	 private void myPageUpdatePass(String mem_id) {
	      while(true){
	         MemberVO mb = new MemberVO();
	         System.out.println("───────비밀번호를 변경합니다──────");
	         System.out.println("변경할 비밀번호를 입력해주세요.");
	         
	         String mem_pass = sc.next();
	         
	         if(regx.checkPass(mem_pass)){
	               
	            mb.setMem_id(mem_id);
	            mb.setMem_pass(mem_pass);
	            
	            int result = service.myPageUpdatePass(mb);
	            
	            if(result>0){
	               System.out.println("변경되었습니다.");
	               break;
	            }else{
	               System.out.println("변경되지 않았습니다.");
	               sc.nextLine();
	               continue;
	            }
	         }System.out.println("비밀번호 형식이 아닙니다. 영어소문자 또는 숫자");
	      }
	 }  

	 
	 //폰번호 수정
	 private void myPageUpdateHp(String mem_id) {
	      while(true){
	         MemberVO mb = new MemberVO();
	         System.out.println("──────핸드폰 번호를 변경합니다──────");
	         System.out.println("변경할 핸드폰 번호를 입력해주세요.");
	         
	         String mem_hp = sc.next();
	         
	         if(regx.checkHp(mem_hp)){
	           
	            mb.setMem_id(mem_id);
	            mb.setMem_hp(mem_hp);
	            
	            int result = service.myPageUpdateHp(mb);
	            
	            if(result>0){
	               System.out.println("변경되었습니다.");
	               break;
	            }else{
	               System.out.println("변경되지 않았습니다.");
	               sc.nextLine();
	               continue;
	            }
	         
	          }System.out.println("핸드폰번호 형식이 아닙니다. 예) 01012345678");
	         
	         }
	      }
	 
	 //여권번호 수정
	 private void myPageUpdatePassport(String mem_id) {
	      while(true){
	         MemberVO mb = new MemberVO();
	         System.out.println("──────여권 번호를 변경합니다─────");
	         System.out.println("변경할 여권 번호를 입력해주세요.");
	         
	         String mem_passport = sc.next();
	         
	         int result = 0;
	         
	         if(regx.checkPassport(mem_passport)){
	            mb.setMem_id(mem_id);
	            mb.setMem_passport(mem_passport);
	            result = service.myPageUpdatePassport(mb);
	         
	            if(result>0){
	               System.out.println("변경되었습니다.");
	               break;
	            }else{
	               System.out.println("변경되지 않았습니다.");
	               sc.nextLine();
	               continue;
	            }
	         
	         }System.out.println("여권형식이 아닙니다. 첫글자는 영어대문자.");
	      }
	 }
	 

	/**
	 * 회원의 예약관리 메뉴
	 * 
	 * @param mem_id
	 *            로그인된 회원의 회원 번호
	 * @author 윤한빈
	 */
	private void myPageReservation(String mem_id) {
		while (true) {
			ArrayList<ReserVO> list = service.reservationList(mem_id);
			if (list != null) {
				System.out.println("──────────────나의 예약정보──────────────");
				System.out.println(" 출발날짜    출발지  도착지  좌석번호");
				for (ReserVO rs : list) {
					System.out.println(rs.getSC_DEPARTURE() + " "
							+ rs.getSC_AIRPORT_D() + " " + rs.getSC_AIRPORT_A()
							+ " " + rs.getBK_SEAT_NO());
				}
			} else {
				System.out.println("list가 비었습니다");
			}
			System.out.println("─────────────나의 예약정보───────────────");
			System.out.println("1. 예약정보 취소");
			System.out.println("2. 뒤로가기");

			int select = 0;
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}

			switch (select) {
			case 1:
				deleteReservation(mem_id);
				break;
			case 2:
				return;
			default:
				break;
			}
		}
	}

	private void deleteReservation(String mem_id) {
		
		 while (true) {
	         ArrayList<ReservationVO> list = service.reservationList1(mem_id);
	         if (list != null) {
	        	System.out.println("────────────────나의 티켓───────────────────");
	        	System.out.println("    예약번호	 	예약날짜 	 항공번호	");
	            for (ReservationVO rs : list) {
	               System.out.println(rs.getReserve_no() + " "
	                     + rs.getReserve_date() + " " + rs.getReserve_seat()
	                     + " " + rs.getReserve_price());
	            }
	         } else {
	            System.out.println("list가 비었습니다");
	         }
	         System.out.println("─────────────────나의 티켓──────────────────");
	         System.out.println("1. 예약정보 취소");
	         System.out.println("2. 뒤로가기");

	         int select = 0;
	         try {
	            select = sc.nextInt();
	         } catch (InputMismatchException e) {
	            System.out.println("숫자만 입력해주세요.");
	            sc.nextLine();
	            continue;
	         }

	         switch (select) {
	         case 1:
	            int select1 = 0;
	            while (true) {
	               System.out.println("취소할 번호를 선택해주세요");
	               try {
	                  select1 = sc.nextInt();
	               } catch (InputMismatchException e) {
	                  System.out.println("숫자만 입력해주세요.");
	                  sc.nextLine();
	                  continue;
	               }
	               // sql
	               try {
	                  int result = service.deleteReservation((list.get(select1)).getReserve_no(), mem_id);
	                  if (result > 0) {
	                     System.out.println("취소되었습니다");
	                     break;
	                  } else {
	                     System.out.println("실패하였습니다");
	                  }
	               } catch (Exception e) {
	                  System.out.println("실패 실패하였습니다");
	                  sc.nextLine();
	                  break;
	               }
	            }
	            continue;
	         case 2:
	            return;
	         default:
	            break;
	         }
	      }
		
	}

	/**
	 * 회원정보를 삭제하는 메서드
	 * 
	 * @param mem_id
	 *            로그인된 회원의 회원 번호
	 * @author 윤한빈
	 */
	private void deleteMember(String mem_id) {

		while (true) {
			System.out.println("1. 회원탈퇴");
			System.out.println("2. 뒤로가기");
			int input = 0;
			try {
				input = sc.nextInt(); // 삭제하고싶은 글번호, try-catch해야함
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
			}
			switch (input) {
			case 1:
				// sql
				int deleteresult = service.deleteMember(mem_id);
				if (deleteresult > 0) { // dao에서 삭제하고 성공하면 삭제되었다고 출력
					System.out.println("삭제되었습니다");
				} else {
					System.out.println("삭제되었습니다");
				}
				break;
			case 2:
				// 뒤로가기
				return;

			default:
				break;
			}
		}

	}

	/**
	 * 로그인된 회원의 마일리지 메뉴
	 * 
	 * @param mem_id
	 *            로그인된 회원의 회원 번호
	 * @author 윤한빈
	 */
	private void myPageMileage(String mem_id) {
		// sql
		ArrayList<MemberVO> list = service.myPageMileage(mem_id); // 로그인된 회원의
		// 현재 마일리지 출력
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("─────────마일리지 관리─────────");
			System.out.println("1.마일리지 충전");
			System.out.println("2.마일리지 환불");
			System.out.println("3.뒤로가기");
			int select = 0;
			try {
				select = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			switch (select) {
			case 1:
				// 마일리지 충전
				chargeMileage(mem_id);
				break;
			case 2:
				// 마일리지 환불
				refundMileage(mem_id);
				break;
			case 3:
				// 이전화면으로가는 메서드
				return;
			default:
				break;
			}
		}
	}

	

	/**
	 * 비행기 관리자 메뉴
	 * 
	 * @author 이재형
	 */
	private void adminPlane() {
		Scanner sc = new Scanner(System.in);
		int input = 0;
		while (true) {

			// 비행기 정보 메뉴를 관리 위한 메서드
			System.out.println("───────────────────────────────────");
			System.out.println("1.비행기 등록");
			System.out.println("2.비행기 리스트 조회");
			System.out.println("3.비행기 정보 수정");
			System.out.println("4.비행기 삭제");
			System.out.println("5.뒤로가기");
			System.out.println("───────────────────────────────────");
			try {
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("메뉴의 번호만 입력해주세요.");
				sc.nextLine();
				continue;
			}
			// 비행기 관리 메뉴 번호 선택

			switch (input) {
			case 1:
				insertPlane(); // 비행기 정보 등록
				break;
			case 2:
				showPlaneList(); // 비행기 정보 조회
				break;
			case 3:
				updatePlane(); // 비행기 정보 수정
				break;
			case 4:
				deletePlane(); // 비행기 정보 삭제
				break;
			case 5:
				// 뒤로가기
				return;
			default:
				System.out.println("다시 입력해주세요.");
				break;
			}
		}
	}

	/**
	 * 관리자가 비행기 등록 하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	private void insertPlane() {
		// sql
		// Map<String, String> params = new HashMap<>();
		// service.insertPlane(params);
		Map<String, String> params = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("───────비행기 기종을 입력해주세요.───────");
				String airplane_model = sc.next();
				System.out.println("────────비행기가 입력되었습니다───────");

				service.insertPlane(airplane_model);
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				sc.nextLine();
				continue;
			}
			break;
		}
		showPlaneList();

	}

	/**
	 * 관리자가 비행기 리스트를 조회하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	private void showPlaneList() {
		// sql
		// ArrayList<AirplaneVO> list = service.showPlaneList();
		ArrayList<AirplaneVO> list = service.showPlaneList();
		System.out.println("───────비행기리스트 출력───────");
		System.out.println("비행기번호"+"\t\t"+ "비행기기종");
		if (list != null) {
			for (AirplaneVO plane : list) {

				System.out.print(plane.getAirplane_no() + "\t\t ");
				System.out.println(plane.getAirplane_model());
			}
			System.out.println();
		} else {
			System.out.println("리스트가 없습니다.");
		}
	}

	/**
	 * 관리자가 비행기 정보를 수정하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	private void updatePlane() {
		showPlaneList();
		Map<String, String> params = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("───────정보를 수정할 비행기번호를 입력하세요.───────");
				String airplane_no = sc.next();
				params.put("airplane_no", airplane_no);
				System.out.println("───────비행기 기종을 입력하세요.───────");
				String airplane_model = sc.next();
				params.put("airplane_model", airplane_model);

				service.updatePlane(params);
				break;
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				System.out.println();
				sc.nextLine();
				continue;
			}
		}
		showPlaneList();
	}

	/**
	 * 관리자가 비행기 정보를 삭제하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	private void deletePlane() {
		// sql
		// service.deletePlane();
		showPlaneList();
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("───────삭제할 비행기번호를 입력하세요.───────");
				String airplane_no = sc.nextLine();
				service.deletePlane(airplane_no);
				System.out.println("───────비행기 삭제를 완료 하였습니다.───────");
				break;
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				sc.nextLine();
				continue;
			}
		}
		showPlaneList();

	}

	/**
	 * 스케줄 관리자 메뉴
	 * 
	 * @author 이재형
	 */
	private void adminSchedule() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			int input = 0;
			System.out.println("───────────────────────────────────");
			System.out.println("1.스케줄 등록");
			System.out.println("2.스케줄 조회");
			System.out.println("3.스케줄 수정");
			System.out.println("4.스케줄 삭제");
			System.out.println("5.뒤로가기");
			System.out.println("───────────────────────────────────");

			try {
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("메뉴의 번호만 입력해주세요.");
				System.out.println();
				sc.nextLine();
				continue;
			}
			switch (input) {
			case 1:
				insertSchedule(); // 비행스케줄 등록
				break;
			case 2:
				showScheduleList(); // 비행스케줄 조회
				break;
			case 3:
				updateSchedule(); // 비행스케줄 수정
				break;
			case 4:
				deleteSchedule(); // 비행스케줄 삭제
				break;
			case 5:
				// 뒤로가기
				return;

			default:
				System.out.println("다시 입력해주세요.");
				System.out.println();
				break;
			}

		}
	}

	/**
	 * 관리자가 스케줄을 입력하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	private void insertSchedule() {
		// sql
		// Map<String, String> params = new HashMap<>();
		// service.insertSchedule(params);
		ScheduleVO s = new ScheduleVO();
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				showPlaneList();
				System.out.println("───────비행기 번호를 입력하세요.───────");
				try {
					String sc_airplane_no = sc.next();
					s.setSc_airplane_no(sc_airplane_no);

					System.out.println("───────출발지를 입력하세요.───────");
					String sc_airport_d = selectDeparture();
					s.setSc_airport_d(sc_airport_d);

					System.out.println("───────도착지를 입력하세요.───────");
					String sc_airport_a = selectArrival();
					 s.setSc_airport_a(sc_airport_a);

					System.out.println("───────출발일시를 입력해주세요.───────");
					String sc_departure = date();
					s.setSc_departure(sc_departure);

					System.out.println("───────가격을 입력해주세요.───────");
					int sc_price = sc.nextInt();
					s.setSc_price(sc_price);

					service.insertSchedule(s);
				} catch (Exception e) {
					System.out.println("다시 입력해주세요.");
					System.out.println();
					sc.nextLine();
					continue;
				}
			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				System.out.println();
				sc.nextLine();
				continue;
			}
			break;
		}

	}


	
/**
	 * 관리자가 스케줄 리스트를 조회하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	private void showScheduleList() {
		// sql
		// ArrayList<ScheduleVO> list = service.showScheduleList();
		ArrayList<ScheduleVO> list = service.showScheduleList();
		System.out.println("스케줄번호"+ "\t" + "비행기번호." + "\t" + "출발일시" + "\t" + "출발지" + "\t" + "도착지" + "\t" + "가격");
		for (ScheduleVO sc : list) {
			System.out.print(sc.getSc_no() + " ");
			System.out.print(sc.getSc_airplane_no() + " ");
			System.out.print(sc.getSc_departure() + " ");
			System.out.print(sc.getSc_airport_d() + " ");
			System.out.print(sc.getSc_airport_a() + " ");
			System.out.println(sc.getSc_price());
		}

	}

	/**
	 * 관리자가 스케줄을 수정하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	private void updateSchedule() {
		// sql
		// Map<String, String> params = new HashMap<>();
		// service.updateSchedule(params);
		showScheduleList();
		while (true) { // 번호를 선택하여 수정할 부분을 선택
			Scanner sc = new Scanner(System.in);
			Map<String, String> params = new HashMap<>(); 
			Map<String, Integer> paramsInt = new HashMap<>(); //int형 가격을 넣기 위한 맵
			while (true) {
				try {
					System.out.println("───────────────────────────────────");
					System.out.println("수정할 비행일정번호를 입력하세요.");
					String sc_no = sc.next();
					System.out.println("1.비행기번호 수정");
					System.out.println("2.출발일시 수정");
					System.out.println("3.출발지 수정");
					System.out.println("4.도착지 수정");
					System.out.println("5.가격 수정");
					System.out.println("6.나가기");
					System.out.println("───────────────────────────────────");
					
					int input = sc.nextInt();
					params.put("sc_no", sc_no);
					switch (input) {
					case 1:// 비행기 번호 수정
						showPlaneList();
						System.out.println("수정할 비행기 번호를 입력하세요.");
						String sc_airplane_no = sc.next();
						params.put("sc_airplane_no", sc_airplane_no);
						service.updateScheduleApNo(params);
						showScheduleList();
						break;

					case 2:// 출발일자 수정
						String sc_departure = date();
						params.put("sc_departure", sc_departure);
						service.updateScheduleD(params);
						showScheduleList();
						break;

					case 3:// 출발지 수정
						
						System.out.println("출발지를 수정해주세요.");
						String sc_airport_d = selectDeparture();;
						params.put("sc_airport_d", sc_airport_d);
						service.updateScheduleApD(params);
						showScheduleList();
						break;

					case 4:// 도착지 수정
						
						System.out.println("도착지를 수정해주세요.");
						String sc_airport_a = selectArrival();
						params.put("sc_airport_a", sc_airport_a);
						service.updateScheduleApA(params);
						showScheduleList();
						break;

					case 5:// 가격 수정
						System.out.println("가격을 수정해주세요.");
						int sc_price = sc.nextInt();
						paramsInt.put("sc_price", sc_price);
						service.updateScheduleInt(params, paramsInt);
						showScheduleList();
						break;
					case 6:// 나가기
						return;

					default:
						break;
					}

				} catch (Exception e) {
					System.out.println("다시 입력해주세요.");
					sc.nextLine();
					continue;
				}
			}
		}
	}

	/**
	 * 관리자가 스케줄을 삭제하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	private void deleteSchedule() {
		// sql
		// service.deleteSchedule();
		showScheduleList();
		while (true) {
			try {
				System.out.println("스케줄번호를 입력해주세요");
				String sc_no = sc.next();
				service.deleteSchedule(sc_no);

			} catch (Exception e) {
				System.out.println("다시 입력해주세요.");
				sc.nextLine();
				continue;
			}
			break;
		}
		showScheduleList();

	}
	/**
	    * 로그인된 회원의 마일리지 추가충전
	    * 
	    * @param mem_id
	    *            로그인된 회원의 회원 번호
	    * @author 이재형
	    */
	private void chargeMileage(String mem_id) {
	      // sql
	      // service.chargeMileage(mem_id);
	      String mileage = null;

	      ArrayList<MemberVO> myInfo = service.searchIdMemList(mem_id);
	      System.out.println("───" + mem_id + "님의 보유마일리지 입니다──");
	      for (MemberVO mb : myInfo) {
	         System.out.println("마일리지: " + mb.getMem_mileage());
	         mileage = mb.getMem_mileage();
	         System.out.println("──────────────────────────────");
	      }
	      System.out.println("충전할 금액을 입력해주세요.");
	      int inputMileage = 0;
	      try {
	         inputMileage = sc.nextInt();

	         inputMileage += Integer.parseInt(mileage);

	         int result = service.chargeMileage(inputMileage, mem_id);
	         if (result > 0) {
	            System.out.println("성공적으로 충전이 되었습니다.");
	         } else {
	            System.out.println("실패했습니다.");
	         }
	      } catch (Exception e) {
	         System.out.println("숫자만 입력해주세요.");
	      }
	   }

	   /**
	    * 로그인된 회원의 마일리지 환불
	    * 
	    * @param mem_id
	    *            로그인된 회원의 회원 번호
	    * @author 유수빈
	    */
	   private void refundMileage(String mem_id) {
	         // sql
	         // service.refundMileage(mem_id);
	         String mileage = null;
	         ArrayList<MemberVO> myInfo = service.searchIdMemList(mem_id);
	            System.out.println("───"+mem_id+"님의 보유마일리지 입니다──");
	            for(MemberVO mb : myInfo){
	                System.out.println("마일리지: "+mb.getMem_mileage());
	                mileage = mb.getMem_mileage();
	               System.out.println("──────────────────────────────");
	            }
	         System.out.println("환불할 마일리지 금액을 입력해주세요.");
	         int refundMileage = sc.nextInt();
	         refundMileage = Integer.parseInt(mileage)-refundMileage;

	         if(service.refundMileage(refundMileage, mem_id)>0){
	            System.out.println("환불되었습니다.");
	          
	         }else{
	            System.out.println("환불 실패했습니다.");
	            
	         }
	         
	      }
	   
//	   private void (String mem_id){
//		      MemberVO mb = new MemberVO();
//		      ReservationVO rv = new ReservationVO();
//		      
//		      String m = mb.getMem_mileage();
//		      int mileage = 0;
//		      
//		      try
//		      {
//		    	  mileage = Integer.parseInt(m);
//		         
//		      }
//		      catch (NumberFormatException e)
//		      {
//		          System.out.println("마일리지 형변화안댐");
//		      }
//		      
//		      ArrayList<ReservationVO> list = service.reservationList1(mem_id);
//		      String p = rv.getReserve_price();
//		      int price = Integer.parseInt(p);
//		      
//		      if(mileage>=price){
//		         int payment = mileage - price;
//		         service.refundMileage(payment, mem_id);
//		      }else{
//		         System.out.println("금액이 부족합니다.");
//		         System.out.println("금액을 충전하시겠습니까?");
//		         System.out.println("1. Yes");
//		         System.out.println("2. NOoooo");
//		         int input = sc.nextInt();
//		         switch (input) {
//		         case 1:
//		            chargeMileage(mem_id);
//		            break;
//		         case 2:
//		            
//		            return;
//		         default:
//		            System.out.println("다시 입력해주세요.");
//		            break;
//		         }
//		      }
//		      
//		   }

	   
	  
	
}// 클래스 닫기