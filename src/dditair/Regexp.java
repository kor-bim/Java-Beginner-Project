package dditair;

import java.util.regex.Pattern;

public class Regexp {

   //회원의 이름을 체크하는 메서드 한글만 된다.
   public boolean checkName(String mem_name){
      return Pattern.matches("[가-힣]{2,}",mem_name);

   }
   //아이디의 형식을 체크하는 메서드
   public boolean checkId(String mem_id){
      return Pattern.matches("[a-z0-9]{4,}",mem_id);

   }
   
   //핸드폰 형식을 체크하는 메서드
   public boolean checkHp(String mem_hp){
      return Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", mem_hp);
      
   }

   //비밀번호는 영문자와 숫자만 가능하다. 한글안됨
   public boolean checkPass(String mem_pass){
      return Pattern.matches("[a-zA-Z0-9]{4,}",mem_pass);

      
   }
   
   //여권번호 형식 검사. m,s,r,g,d으로 시작하고 8자리 숫자
   public boolean checkPassport(String mem_passport){
      return Pattern.matches("[MSRGD]{1}[0-9]{8}",mem_passport);
      
      
   }
   
}