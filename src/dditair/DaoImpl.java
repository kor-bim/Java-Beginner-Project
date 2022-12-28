package dditair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class DaoImpl implements Dao {

	// 회원가입
	@Override
	public int insertMember(MemberVO newMember) {
		String mem_id = newMember.getMem_id();
		String mem_pass = newMember.getMem_pass();
		String mem_name = newMember.getMem_name();
		String mem_hp = newMember.getMem_hp();
		String mem_passport = newMember.getMem_passport();

		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "INSERT INTO MEMBER (MEM_ID, MEM_PASS, MEM_NAME, MEM_HP, MEM_PASSPORT)"
					+ " VALUES('"
					+ mem_id
					+ "','"
					+ mem_pass
					+ "','"
					+ mem_name + "','" + mem_hp + "','" + mem_passport + "')";

			// 4. 결과를 받아서 저장
			rs = stmt.executeUpdate(sql); // int형이 반환됨

			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");

		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}

		return 0;
	}

	// 아이디 중복 확인
	@Override
	public boolean dupId(String mem_id) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT MEM_ID " + "FROM MEMBER " + "WHERE MEM_ID='"
					+ mem_id + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet

			String new_id = null; // 여기에 오라클에서 찾은걸 저장할거임
			while (rs.next()) {
				new_id = rs.getString("MEM_ID"); // 뒤져서 있으면 그 주소값이 아닌 String값을
													// 저장할거야. 그럼 new_id가 null이
													// 아니겠찌?
			}
			if (new_id == null) {// null이 아니면 중복이야
				return true;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}

		}// finally

		return false;
	}

	// 회원 로그인
	@Override
	public String memlogIn(Map<String, String> params) {
		String mem_id = params.get("mem_id");
		String mem_pass = params.get("mem_pass");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String logIn_ID = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT MEM_ID " + "FROM MEMBER " + "WHERE MEM_ID='"
					+ mem_id + "'AND MEM_PASS = '" + mem_pass + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet
			while (rs.next()) {
				logIn_ID = rs.getString("MEM_ID");

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}
		return logIn_ID;
	}

	// 관리자 로그인
	@Override
	public String managerLogIn(Map<String, String> params) {
		String manager_id = params.get("manager_id");
		String manager_pass = params.get("manager_pass");

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		String logIn_manager = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT MANAGER_ID " + "FROM MANAGER "
					+ "WHERE MANAGER_ID = '" + manager_id
					+ "' AND MANAGER_PASS = '" + manager_pass + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet
			while (rs.next()) {
				logIn_manager = rs.getString("MANAGER_ID");

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}

		}
		return logIn_manager;
	}

	// 모든 멤버 정보 저장
	@Override
	public ArrayList<MemberVO> showMemList() {
		ArrayList<MemberVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT * FROM MEMBER";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet
			while (rs.next()) {
				MemberVO mb = new MemberVO();
				mb.setMem_id(rs.getString("mem_id"));
				mb.setMem_pass(rs.getString("mem_pass"));
				mb.setMem_name(rs.getString("mem_name"));
				mb.setMem_level(rs.getString("mem_level"));
				mb.setMem_mileage(rs.getString("mem_mileage"));
				mb.setMem_hp(rs.getString("mem_hp"));
				mb.setMem_passport(rs.getString("mem_passport"));

				list.add(mb);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}

		}
		return list;
	}

	// id로 검색 후 해당 회원의 정보를 저장
	@Override
	public ArrayList<MemberVO> searchIdList(String mem_id) {
		ArrayList<MemberVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT * FROM MEMBER WHERE MEM_ID = '" + mem_id + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet
			while (rs.next()) {
				MemberVO mb = new MemberVO();
				mb.setMem_id(rs.getString("mem_id"));
				mb.setMem_pass(rs.getString("mem_pass"));
				mb.setMem_name(rs.getString("mem_name"));
				mb.setMem_level(rs.getString("mem_level"));
				mb.setMem_mileage(rs.getString("mem_mileage"));
				mb.setMem_hp(rs.getString("mem_hp"));
				mb.setMem_passport(rs.getString("mem_passport"));

				list.add(mb);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}

		}

		return list;

	}

	// 공지사항을 등록
	@Override
	public int insertNotice(NoticeVO notice) {

		String title = notice.getNotice_title();
		String content = notice.getNotice_content();

		Connection conn = null;
		Statement stmt = null;

		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함

			String sql = "INSERT INTO NOTICE (NOTICE_NO, NOTICE_TITLE, NOTICE_DATE, NOTICE_CONTENT, MANAGER_ID)"
					+ " VALUES(SEQ_NOTICE_No.NEXTVAL, '"
					+ title
					+ "', SYSDATE,'"
					+ content + "', \'admin\')";

			rs = stmt.executeUpdate(sql); // int형이 반환됨

			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");

		} finally {

			try {
				// if(rs2!=null){
				// rs2.close();
				// }
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}
		return 0;

	}

	// 공지사항 조회
	@Override
	public ArrayList<NoticeVO> readNotice() {

		ArrayList<NoticeVO> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT * FROM NOTICE";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet

			while (rs.next()) {
				NoticeVO nvo = new NoticeVO();

				nvo.setNotice_no(rs.getInt("notice_no"));
				nvo.setNotice_title(rs.getString("notice_title"));
				nvo.setNotice_date(rs.getString("notice_date"));
				nvo.setNotice_content(rs.getString("notice_content"));
				nvo.setManager_id(rs.getString("manager_id"));

				list.add(nvo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}
		}

		return list;

	}

	@Override
	public ArrayList<NoticeVO> selectNotice(int noticeNo) {
		ArrayList<NoticeVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT * " + "FROM NOTICE " + "WHERE NOTICE_NO = '"
					+ noticeNo + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet

			while (rs.next()) {
				NoticeVO nvo = new NoticeVO();
				nvo.setNotice_no(rs.getInt("notice_no"));
				nvo.setNotice_title(rs.getString("notice_title"));
				nvo.setNotice_date(rs.getString("notice_date"));
				nvo.setNotice_content(rs.getString("notice_content"));
				nvo.setManager_id(rs.getString("manager_id"));

				list.add(nvo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}
		}

		return list;
	}

	// 공지사항을 삭제
	@Override
	public int deleteNotice(int noticeNo) {
		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "DELETE  " + "FROM NOTICE " + "WHERE NOTICE_NO='"
					+ noticeNo + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeUpdate(sql); // 결과 저장 이니까 resultSet

			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}

		}// finally

		return 0;
	}

	@Override
	public ArrayList<ScheduleVO> choiceScheduleList(String departureDate,
			String departureAirPort, String arrivalAirPort) {

		ArrayList<ScheduleVO> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT * " + "     FROM SCHEDULE "
					+ "    WHERE SC_DEPARTURE = '" + departureDate + "'"
					+ "      AND SC_AIRPORT_D = '" + departureAirPort + "'"
					+ "      AND SC_AIRPORT_A = '" + arrivalAirPort + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet

			while (rs.next()) {
				ScheduleVO svo = new ScheduleVO();
				svo.setSc_no(rs.getString("SC_NO"));
				svo.setSc_airplane_no(rs.getString("SC_AIRPLANE_NO"));
				svo.setSc_airport_d(rs.getString("SC_AIRPORT_D"));
				svo.setSc_airport_a(rs.getString("SC_AIRPORT_A"));
				svo.setSc_departure(rs.getString("SC_DEPARTURE"));
				list.add(svo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}
		}

		return list;
	}

	@Override
	public ArrayList<ReserVO> reservationList(String mem_id) {
		ArrayList<ReserVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT B.SC_DEPARTURE AS 날짜, "
					+ "B.SC_AIRPORT_D AS 출발지, " + "B.SC_AIRPORT_A AS 도착지, "
					+ "C.BK_SEAT_NO AS 좌석 "
					+ "FROM  RESERVATION A, SCHEDULE B, BOOKINFO C "
					+ "WHERE B.SC_NO = C.BK_SC_NO "
					+ "AND A.RESERVE_SEAT = C.BKSEAT_NO "
					+ "AND  B.SC_AIRPLANE_NO = C.BK_AIRPLANE_NO "
					+ "AND A.RESERVE_MEMBER = '" + mem_id + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet

			while (rs.next()) {
				ReserVO rvo = new ReserVO();
				rvo.setSC_DEPARTURE(rs.getString("날짜"));
				rvo.setSC_AIRPORT_D(rs.getString("출발지"));
				rvo.setSC_AIRPORT_A(rs.getString("도착지"));
				rvo.setBK_SEAT_NO(rs.getString("좌석"));
				list.add(rvo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}
		}

		return list;
	}

	@Override
	public int deleteReservation(String rvo, String mem_id) {
		Connection conn = null;
	      Statement stmt = null;
	      int rs = 0;

	      try {
	         // 1. 드라이버 로딩
	         Class.forName("oracle.jdbc.driver.OracleDriver");

	         // 2. 접속
	         String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
	         String id = "airline";
	         String pw = "java";
	         conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
	                                                // 넣어줘야함

	         // 3. 질의
	         stmt = conn.createStatement(); // stmt에 담아줘야함
	         String sql = "DELETE  " + "FROM RESERVATION " + "WHERE RESERVE_NO='"
	               + rvo + "' AND RESERVE_MEMBER = '"+mem_id+"'";

	         // 4. 결과를 받아서 저장
	         rs = stmt.executeUpdate(sql); // 결과 저장 이니까 resultSet

	         if (rs > 0) {
	            return rs;
	         } else {
	            return rs;
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩 실패");

	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("접속실패");
	      } finally {
	         try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
	            if (stmt != null) {
	               stmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }

	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환실패");

	         }

	      }// finally

	      return rs;
	}

	@Override
	public ArrayList<MemberVO> myPageMileage(String mem_id) {
		ArrayList<MemberVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT MEM_MILEAGE FROM MEMBER WHERE MEM_ID = '"
					+ mem_id + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet

			while (rs.next()) {
				MemberVO mvo = new MemberVO();
				mvo.setMem_mileage(rs.getString("mem_mileage"));
				list.add(mvo);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}
		}

		return list;
	}

	@Override
	public int seatSelect(SeatVO seat, ScheduleVO schedule) {
		String sc_no = schedule.getSc_no();
		String airplane_no = schedule.getSc_airplane_no();
		String class_no = seat.getClass_no();
		String hang = seat.getHang();
		String yeol = seat.getYeol();
		String seat_no = seat.getSeat_no();

		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "INSERT INTO BOOKINFO(bkseat_no,BK_SC_NO,BK_AIRPLANE_NO,BK_SEAT_NO) "
					+ "select (d.t3||trim(d.t1)||trim(d.t2)), d.t3, d.t1, d.t2 "
					+ "from (select a.airplane_no as t1,"
					+ " b.seat_no as t2,"
					+ " c.sc_no as t3 "
					+ " from airplane a, seat b, schedule c "
					+ " where a.AIRPLANE_NO=b.AIRPLANE_NO "
					+ "  and a.AIRPLANE_NO=c.SC_AIRPLANE_NO "
					+ "  and a.airplane_no = '"
					+ airplane_no
					+ "'"
					+ "  and b.seat_no = '"
					+ (hang + yeol)
					+ "'"
					+ "  and c.sc_no = '" + sc_no + "') d";

			// 4. 결과를 받아서 저장
			try {
				rs = stmt.executeUpdate(sql); // int형이 반환됨

			} catch (Exception e) {
				System.out.println("중복되었습니다");
				return rs;
			}
			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");

		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}

		return rs;
	}

	@Override
	// 회원의 핸드폰번호 수정
	public int myPageUpdateHp(MemberVO m) {
		String mem_id = m.getMem_id();
		String mem_hp = m.getMem_hp();

		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
			// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함

			String sql = "UPDATE MEMBER SET MEM_ID = '" + mem_id
					+ "',MEM_HP= '" + mem_hp + "'WHERE MEM_ID = '" + mem_id
					+ "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeUpdate(sql); // int형이 반환됨
			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");

		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}

		return rs;
	}

	// 회원정보수정 여권 수정
	@Override
	public int myPageUpdatePassport(MemberVO m) {
		String mem_id = m.getMem_id();
		String mem_passport = m.getMem_passport();

		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
			// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함

			String sql = "UPDATE MEMBER SET MEM_ID = '" + mem_id
					+ "',MEM_PASSPORT= '" + mem_passport + "'WHERE MEM_ID = '"
					+ mem_id + "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeUpdate(sql); // int형이 반환됨
			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");

		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}

		return rs;
	}

	// 회원정보수정 비밀번호 수정
	@Override
	public int myPageUpdatePass(MemberVO m) {
		String mem_id = m.getMem_id();
		String mem_pass = m.getMem_pass();

		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
			// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함

			String sql = "UPDATE MEMBER SET MEM_ID = '" + mem_id
					+ "',MEM_PASS= '" + mem_pass + "'WHERE MEM_ID = '" + mem_id
					+ "'";

			// 4. 결과를 받아서 저장
			rs = stmt.executeUpdate(sql); // int형이 반환됨
			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");

		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}

		return rs;
	}

	/**
	 * 공항리스트 출력
	 * 
	 * @author 윤한빈
	 */
	@Override
	public ArrayList<AirportVO> airPortList() {
		ArrayList<AirportVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT * FROM AIRPORT";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet

			while (rs.next()) {
				AirportVO airp = new AirportVO();
				airp.setAirport_no(rs.getString("AIRPORT_NO"));
				airp.setAirport_name(rs.getString("AIRPORT_NAME"));
				airp.setAirport_country(rs.getString("AIRPORT_COUNTRY"));
				list.add(airp);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}
		}

		return list;
	}

	@Override
	public int inserBookInfo(String sc_no) {

		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "INSERT INTO BOOKINFO (BK_SC_NO)" + " VALUES('"
					+ sc_no + "')";

			// 4. 결과를 받아서 저장
			rs = stmt.executeUpdate(sql); // int형이 반환됨
			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");

		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}

		return 0;

	}

	/**
	 * 관리자가 비행기 등록 하기 위해 사용자로부터 입력받은 값을 DB로 넘기는 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void insertPlane(String airplane_model) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "INSERT INTO AIRPLANE VALUES('P'||TRIM(TO_CHAR(seq_airplane_no.NEXTVAL,'000')), '"
					+ airplane_model + "')";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	/**
	 * 관리자가 비행기 리스트를 조회하는 메서드
	 * 
	 * @author 이재형
	 * 
	 */
	@Override
	public ArrayList<AirplaneVO> showPlaneList() {

		ArrayList<AirplaneVO> planeList = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT * FROM AIRPLANE";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet
			while (rs.next()) {
				AirplaneVO ap = new AirplaneVO();
				ap.setAirplane_no(rs.getString("airplane_no"));
				ap.setAirplane_model(rs.getString("airplane_model"));

				planeList.add(ap);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}

		}

		return planeList;
	}

	/**
	 * 관리자가 입력받은 값을 비행기 테이블에 UPDATE 하는 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void updatePlane(Map<String, String> params) {
		String airplane_no = params.get("airplane_no");
		String airplane_model = params.get("airplane_model");

		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "UPDATE AIRPLANE SET AIRPLANE_MODEL = '"
					+ airplane_model + "' WHERE AIRPLANE_NO = '" + airplane_no
					+ "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	/**
	 * 관리자가 입력받은 비행기 번호에 해당하는 행을 삭제하는 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void deletePlane(String airplane_no) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "DELETE FROM AIRPLANE WHERE AIRPLANE_NO = '"
					+ airplane_no + "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	/**
	 * 관리자가 입력받은 값을 스케줄 테이블에 INSERT 하는 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void insertSchedule(ScheduleVO s) {
		String sc_airplane_no = s.getSc_airplane_no();
		String sc_departure = s.getSc_departure();
		String sc_airport_d = s.getSc_airport_d();
		String sc_airport_a = s.getSc_airport_a();
		int sc_price = s.getSc_price();

		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "INSERT INTO SCHEDULE VALUES(seq_sc_no.nextval, '"
					+ sc_airplane_no
					+ "', '"
					+ sc_departure
					+ "', '"
					+ sc_airport_d
					+ "', '"
					+ sc_airport_a
					+ "', '"
					+ sc_price
					+ "')";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	/**
	 * 관리자가 스케줄 테이블 전체를 조회하는 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public ArrayList<ScheduleVO> showScheduleList() {

		ArrayList<ScheduleVO> scheduleList = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "SELECT * FROM SCHEDULE";

			// 4. 결과를 받아서 저장
			rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet
			while (rs.next()) {
				ScheduleVO sc = new ScheduleVO();
				sc.setSc_no(rs.getString("sc_no"));
				sc.setSc_airplane_no(rs.getString("sc_airplane_no"));
				sc.setSc_departure(rs.getString("sc_departure"));
				sc.setSc_airport_d(rs.getString("sc_airport_d"));
				sc.setSc_airport_a(rs.getString("sc_airport_a"));
				sc.setSc_price(rs.getInt("sc_price"));

				scheduleList.add(sc);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}

		}

		return scheduleList;
	}

	/**
	 * 관리자가 스케줄 테이블에 있는 가격 컬럼sc_price의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void updateScheduleInt(Map<String, String> params,
			Map<String, Integer> paramsInt) {
		String sc_no = params.get("sc_no");
		int sc_price = paramsInt.get("sc_price");

		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "UPDATE SCHEDULE SET SC_PRICE = '" + sc_price
					+ "' WHERE SC_NO = '" + sc_no + "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	@Override
	public void deleteSchedule(String sc_no) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "DELETE FROM SCHEDULE WHERE SC_NO = '" + sc_no + "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	@Override
	public int deleteMember(String mem_id) {
		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "DELETE  " + "FROM MEMBER " + "WHERE MEM_ID='"
					+ mem_id + "'";

			// 4. 결과를 받아서 저장
			try {
				rs = stmt.executeUpdate(sql); // 결과 저장 이니까 resultSet
				
			} catch (Exception e) {
				System.out.println("삭제되었습니다");
			}

			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");
		} finally {
			try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");

			}

		}// finally

		return rs;
	}

	@Override
	public int reservation(String mem_id) {

		Connection conn = null;
		Statement stmt = null;
		int rs = 0;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
																// 넣어줘야함

			// 3. 질의
			stmt = conn.createStatement(); // stmt에 담아줘야함
			String sql = "INSERT INTO RESERVATION(RESERVE_NO,RESERVE_SEAT,RESERVE_DATE, RESERVE_MEMBER) "
					+ "select (trim(d.t1)||trim(d.t3)||seq_reserve_no.nextval), d.t1, d.t2, d.t3 "
					+ "from (select B.BKSEAT_NO as t1, "
					+ "TO_CHAR(SYSDATE,'YYYYMMDD') as t2, "
					+ "A.MEM_ID as t3 "
					+ "from  MEMBER A, BOOKINFO B "
					+ "WHERE A.MEM_ID = '"
					+ mem_id + "') d";

			// 4. 결과를 받아서 저장
			try {
				rs = stmt.executeUpdate(sql); // int형이 반환됨
			} catch (Exception e) {
				System.out.println("중복되었습니다 다시입력 해주세요");
				return rs;
			}
			if (rs > 0) {
				return rs;
			} else {
				return rs;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속실패");

		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					stmt.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환실패");
			}
		}

		return rs;

	}

	/**
	 * 관리자가 스케줄 테이블에 있는 비행기번호 컬럼sc_airplane_no의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void updateScheduleApNo(Map<String, String> params) {
		String sc_no = params.get("sc_no");
		String sc_airplane_no = params.get("sc_airplane_no");

		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "UPDATE SCHEDULE SET SC_AIRPLANE_NO = '" + sc_airplane_no
					+ "' WHERE SC_NO = '" + sc_no + "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	/**
	 * 관리자가 스케줄 테이블에 있는 출발일시 컬럼sc_departure의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void updateScheduleD(Map<String, String> params) {
		String sc_no = params.get("sc_no");
		String sc_departure = params.get("sc_departure");

		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "UPDATE SCHEDULE SET SC_DEPARTURE = '" + sc_departure
					+ "' WHERE SC_NO = '" + sc_no + "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	/**
	 * 관리자가 스케줄 테이블에 있는 출발지 컬럼sc_airport_d의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void updateScheduleApD(Map<String, String> params) {
		String sc_no = params.get("sc_no");
		String sc_airport_d = params.get("sc_airport_d");

		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "UPDATE SCHEDULE SET SC_AIRPORT_D = '" + sc_airport_d
					+ "' WHERE SC_NO = '" + sc_no + "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}

	/**
	 * 관리자가 스케줄 테이블에 있는 도착지 컬럼sc_airport_a의 값을 DB에서 UPDATE하기 위한 메서드
	 * 
	 * @author 이재형
	 */
	@Override
	public void updateScheduleApA(Map<String, String> params) {
		String sc_no = params.get("sc_no");
		String sc_airport_a = params.get("sc_airport_a");

		Connection conn = null;
		Statement stmt = null;

		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.접속
			String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
			String id = "Airline";
			String pw = "java";
			conn = DriverManager.getConnection(url, id, pw);

			// 3.질의
			stmt = conn.createStatement();
			String sql = "UPDATE SCHEDULE SET SC_AIRPORT_A = '" + sc_airport_a
					+ "' WHERE SC_NO = '" + sc_no + "'";
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("접속 실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("반환 실패");
			}
		}

	}



	@Override
	public ArrayList<ReservationVO> reservationList1(String mem_id) {
		ArrayList<ReservationVO> list = new ArrayList<>();
	      Connection conn = null;
	      Statement stmt = null;
	      ResultSet rs = null;

	      try {
	         // 1. 드라이버 로딩
	         Class.forName("oracle.jdbc.driver.OracleDriver");

	         // 2. 접속
	         String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
	         String id = "airline";
	         String pw = "java";
	         conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
	                                                // 넣어줘야함

	         // 3. 질의
	         stmt = conn.createStatement(); // stmt에 담아줘야함
	         String sql = "SELECT * FROM RESERVATION WHERE RESERVE_MEMBER = '"
	               + mem_id + "'";

	         // 4. 결과를 받아서 저장
	         rs = stmt.executeQuery(sql); // 결과 저장 이니까 resultSet

	         while (rs.next()) {
	            ReservationVO rvo = new ReservationVO();
	            rvo.setReserve_no(rs.getString("reserve_no"));
	            rvo.setReserve_seat(rs.getString("reserve_seat"));
	            rvo.setReserve_member(rs.getString("reserve_member"));
	            rvo.setReserve_date(rs.getString("reserve_date"));
	            list.add(rvo);
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩 실패");

	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("접속실패");
	      } finally {
	         try {// null인지 아닌지 확인, null이 아닐때만 close 해야한다.
	            if (rs != null) {
	               rs.close();
	            }
	            if (stmt != null) {
	               stmt.close();
	            }
	            if (conn != null) {
	               conn.close();
	            }

	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환실패");

	         }
	      }

	      return list;
	}
	
	@Override
	   public int chargeMileage(int inputMileage, String mem_id) {
	      Connection conn = null;
	      Statement stmt = null;
	      int rs = 0;

	      try {
	         // 1. 드라이버 로딩
	         Class.forName("oracle.jdbc.driver.OracleDriver");

	         // 2. 접속
	         String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
	         String id = "Airline";
	         String pw = "java";
	         conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
	         // 넣어줘야함

	         // 3. 질의
	         stmt = conn.createStatement(); // stmt에 담아줘야함

	         String sql = "UPDATE MEMBER SET MEM_MILEAGE= '" + inputMileage
	               + "' WHERE MEM_ID = '" + mem_id + "'";

	         // 4. 결과를 받아서 저장
	         rs = stmt.executeUpdate(sql); // int형이 반환됨
	         if (rs > 0) {
	            return rs;
	         } else {
	            return rs;
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩실패");
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("접속실패");

	      } finally {

	         try {
	            if (stmt != null) {
	               stmt.close();
	            }
	            if (conn != null) {
	               stmt.close();
	            }

	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환실패");
	         }
	         return rs;
	      }
	   }
	   

	   @Override
	   public int refundMileage(int refundMileage, String mem_id) {
	      Connection conn = null;
	      Statement stmt = null;
	      int rs = 0;

	      try {
	         // 1. 드라이버 로딩
	         Class.forName("oracle.jdbc.driver.OracleDriver");

	         // 2. 접속
	         String url = "jdbc:oracle:thin:@192.168.45.47:1521/XE";
	         String id = "Airline";
	         String pw = "java";
	         conn = DriverManager.getConnection(url, id, pw); // 만들었으면 conn에
	         // 넣어줘야함

	         // 3. 질의
	         stmt = conn.createStatement(); // stmt에 담아줘야함

	         String sql = "UPDATE MEMBER SET MEM_MILEAGE= '" + refundMileage
	               + "' WHERE MEM_ID = '" + mem_id + "'";

	         // 4. 결과를 받아서 저장
	         rs = stmt.executeUpdate(sql); // int형이 반환됨
	         if (rs > 0) {
	            return rs;
	         } else {
	            return rs;
	         }

	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         System.out.println("드라이버 로딩실패");
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("접속실패");

	      } finally {

	         try {
	            if (stmt != null) {
	               stmt.close();
	            }
	            if (conn != null) {
	               stmt.close();
	            }

	         } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("반환실패");
	         }
	      }

	      return rs;
	   }

	

}
