package shoppingMall;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	
	public static Connection getCon() throws Exception {
	Class.forName("oracle.jdbc.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","shopping","hkit2019");
	
	return con;
	
	}
	public static void close(Connection con, PreparedStatement ps,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//회원가입
	public static int signUp(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		
		String sql = " insert into T_MEMBER (i_member,mid,mpw,nm,sex) "
				+ " values ((select nvl(max(i_member),0)+1 from T_MEMBER),?,?,?,?) ";
				
		try {
			con = getCon();
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getMid());
			ps.setString(2, vo.getMpw());
			ps.setString(3, vo.getNm());
			ps.setString(4, vo.getSex());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result; 
	}
	
	//멤버 번호 가져오기
	public static String i_member(ShoppingVo vo) {
		String result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select i_member from t_member "
				   + " where mid = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1,vo.getMid());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String i_member = rs.getString("i_member");
				result = i_member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		
		return result;
	}
	
	//유저 로그인
	public static int userlogin (ShoppingVo vo) {
		int result = 0; //아이디가 없음
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select mpw from t_member "
				   + " where mid = ? ";
		
		try {
			con=getCon();
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getMid());
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				String mpw = rs.getString("mpw");
				if(mpw.equals(vo.getMpw())) {
					vo.setMpw("");
					result = 1;		
				}else {
					result = -1; // 비밀번호 틀림
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		
		return result;
	}
	
	//유저 비밀번호 수정
	public static int updatePw(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update t_member " 
					+" set mpw = ? "
					+" where i_member = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getMpw());
			ps.setString(2, vo.getI_member());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//관리자 로그인
	public static int adminLogin(ShoppingVo vo) {
		int result = 0; //아이디가 없다
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT MPW FROM T_ADMIN "
				   + " WHERE MID = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1,vo.getMid());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String mpw = rs.getString("mpw");
				if(mpw.equals(vo.getMpw())) {
					vo.setMpw(""); //보안, 비밀번호 지움 
					// 토큰
					result = 1; //1이상이면 로그인 
				} else {
					result = -1; //비밀번호 틀림
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}			
		return result;
	}
	
	//관리자 상품 등록
	public static int productReg(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_product (i_product,nm,price,pic) "
				   + " values ((select nvl(max(i_product),0)+1 from t_product),?,?,?) ";
		
		try {
			con = getCon();
			ps=con.prepareStatement(sql);
			
			ps.setString(1, vo.getNm());
			ps.setString(2, vo.getPrice());
			ps.setString(3, vo.getPic());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,null);
		}
		
		return result;
	}
	
	//관리자 상품 리스트
	public static List<ShoppingVo> productList(){
		List<ShoppingVo> list = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select * from t_product "
			       + " order by i_product desc ";
		
		try {
			con = getCon();
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				ShoppingVo vo = new ShoppingVo();
				vo.setI_product(rs.getString("i_product"));
				vo.setPic(rs.getString("pic"));
				vo.setNm(rs.getString("nm"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty"));
				vo.setVn_sale(rs.getString("vn_sale"));
				vo.setInfo(rs.getString("info"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con,ps,rs);
		}
		return list;
	}
	
	//상품 수정
	public static int productMod(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " update t_product "
					+" set price = ?, pic = ?, vn_sale = ?, info = ? "
					+" where nm = ? ";
		
		try {
			con = getCon();
			ps=con.prepareStatement(sql);
			
			ps.setString(1, vo.getPrice());
			ps.setString(2, vo.getPic());
			ps.setString(3, vo.getVn_sale());
			ps.setString(4, vo.getInfo());
			ps.setString(5, vo.getNm());
			
			result = ps.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
			
		return result;
	}
	
	//상품 삭제  구현안됌 수정해야함 (AdminProductList)
	public static void deleteproduct(String nm, String i_product) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " delete from t_produuct where nm = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, nm);			
			ps.execute();
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}
	
	//상품 입고
	public static int productImport(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_product_import (i_pi,i_product,qty) "
				+ " values ((select nvl(max(i_pi),0)+1 from t_product_import),?,?) " ;
				
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getI_product());
			ps.setString(2, vo.getQty());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		
		return result;
	}
	
	
	//입고 리스트
	public static List<ShoppingVo> productImportList() {
		List<ShoppingVo> importlist = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select a.i_pi, b.nm,(b.price*a.qty) as sum,b.price,a.qty "
				+ " from t_product_import A "
				+ " inner join t_product B on a.i_product = b.i_product "
				+ " order by i_pi desc ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				ShoppingVo vo = new ShoppingVo();
				
				vo.setI_pi(rs.getString("i_pi"));
				vo.setNm(rs.getString("nm"));
				vo.setSum(rs.getString("sum"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty"));
				importlist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return importlist;
	}
	
	//입고수량을 상품리스트수량에 더해지게하는(누적되게) 메소드
	public static int Qty(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
	
		
		String sql = " update t_product "
					+" set qty = qty+? "
					+" where i_product = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);

			ps.setString(1, vo.getQty());
			ps.setString(2, vo.getI_product());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//유저 상품 리스트
	public static List<ShoppingVo> pList (){
		List<ShoppingVo> plist = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select i_product,pic,nm,price,qty from t_product ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ShoppingVo vo = new ShoppingVo();
				vo.setI_product(rs.getString("i_product"));
				vo.setPic(rs.getString("pic"));
				vo.setNm(rs.getString("nm"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty"));
				plist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		
		return plist;
	}
	
	//유저 상품정보 디테일
	public static ShoppingVo selectVo(String i_product2) {
		ShoppingVo vo = new ShoppingVo();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " select * from t_product where i_product = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, i_product2);
			rs = ps.executeQuery();
			
			while (rs.next()) {

				vo.setI_product(rs.getString("i_product"));
				vo.setNm(rs.getString("nm"));
				vo.setPic(rs.getString("pic"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty"));
				vo.setInfo(rs.getString("info"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return vo;
	}
	
	//장바구니에 담기
	public static int basket(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_basket (i_basket,i_member,i_product,qty,price) "
					+" values ((select nvl(max(i_basket),0)+1 from t_basket),?,?,?,?)";
					
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getI_member());
			ps.setString(2, vo.getI_product());
			ps.setString(3, vo.getCount()); //재고수량 qty
			ps.setString(4, vo.getPrice());
			result = ps.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	
	//바로 구매
	public static int purchase(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " insert into t_purchase (i_purchase,i_member,i_product,qty,price)"
					+" values ((select nvl(max(i_purchase),0)+1 from t_purchase),?,?,?,?)";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vo.getI_member());
			ps.setString(2, vo.getI_product());
			ps.setString(3, vo.getCount()); //재고수량 qty
			ps.setString(4, vo.getPrice());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		return result;
	}
	//장바구니 리스트
	public static List<ShoppingVo> basketList(String i_member){
		List<ShoppingVo> basketlist = new ArrayList( );
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//장바구니 번호, 이미지, 상품명, 금액(단가,구매 금액), 수량(현재량,구매 수량) 
		String sql = " select a.i_basket,a.i_member,a.i_product,b.nm,b.pic,a.price,(a.price*a.qty) as sumprice, a.qty,b.qty as sumqty "
				+" from t_basket a "
				+" inner join t_product b on a.i_product = b.i_product "
				+" where i_member = ? and vn_sale = 1 "
				+" order by i_basket desc ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, i_member);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ShoppingVo vo = new ShoppingVo();
				vo.setI_basket(rs.getString("i_basket"));
				vo.setI_member(rs.getString("i_member"));
				vo.setI_product(rs.getString("i_product"));
				vo.setNm(rs.getString("nm"));
				vo.setPic(rs.getString("pic"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty"));
				vo.setSumprice(rs.getString("sumprice"));
				vo.setSumqty(rs.getString("sumqty")); //b.qty
				basketlist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return basketlist;
	}
	
	//현재고(t_product qty) - 구매수량(t_purchase qty)
	public static int mQty(ShoppingVo vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement rs = null;
		
		String sql = " update t_product"
					+" set qty = qty - ? "
		  		    +" where i_product = ? ";
		
		try {
			con = getCon();
			rs = con.prepareStatement(sql);
			
			rs.setString(1, vo.getCount());
			rs.setString(2, vo.getI_product());
			result=rs.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,rs,null);
		}
		return result;
	}
	
	//구매리스트
	public static List<ShoppingVo> purchaseList(String i_member){
		List<ShoppingVo> purchaseList = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//구매번호, 이미지, 상품명, 금액(단가,구매금액),수량(현재고	,구매수량),구매일
		String sql = " select a.i_purchase,a.i_member,a.i_product,b.pic,b.nm,b.price,(a.price*a.qty) as sumprice,b.qty,a.qty as sumqty,b.vn_sale,to_char(a.r_dt,'YYYY-MM-DD') as r_dt "
					+" from t_purchase a" 
					+" inner join t_product b on a.i_product = b.i_product "
					+" where i_member = ? " 
					+" order by i_purchase desc";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1,i_member);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ShoppingVo vo = new ShoppingVo();
				vo.setI_purchase(rs.getString("i_purchase"));
				vo.setI_member(rs.getString("i_member"));
				vo.setI_product(rs.getString("i_product"));
				vo.setPic(rs.getString("pic"));
				vo.setNm(rs.getString("nm"));
				vo.setPrice(rs.getString("price"));
				vo.setSumprice(rs.getString("sumprice"));
				vo.setQty(rs.getString("qty"));
				vo.setSumqty(rs.getString("sumqty"));//a.qty
				vo.setVn_sale(rs.getString("vn_sale"));
				vo.setR_dt(rs.getString("r_dt"));//날짜
				purchaseList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return purchaseList;
	}
	
	//일별 판매 현황
	public static List<ShoppingVo> PSaleDay(String R_dt, String R_dt2){
		List<ShoppingVo> psaleday = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//날짜,이미지,상품명,단가,총 (판매)수량, 총 (판매)금액
		String sql = " select to_char(b.r_dt,'YYYY-MM-DD')as r_dt,a.i_product,a.nm,a.pic,a.price,a.qty,a.qty,(a.price*a.qty)as sumprice "
		            +" from t_product a "
		            +" inner join t_purchase b on a.i_product = b.i_product "
		            +" where to_char(r_dt,'yyyy-mm-dd') between ? and ? "
		            +" order by r_dt desc ";
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, R_dt);
			ps.setString(2, R_dt2);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ShoppingVo vo = new ShoppingVo();
				vo.setR_dt(rs.getString("r_dt"));
				vo.setI_product(rs.getString("i_product"));
				vo.setNm(rs.getString("nm"));
				vo.setPic(rs.getString("pic"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty")); //수량
				vo.setSumprice(rs.getString("sumprice"));//총 판매 금액
				psaleday.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return psaleday;
	}
	
	//월별 상품 판매 현황
	public static List<ShoppingVo> PSaleMon(String yymm,String i_product){
		List<ShoppingVo> psalemon = new ArrayList();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//날짜,이미지,상품명,단가,총 (판매)수량, 총 (판매)금액
		String sql = " select a.i_product,to_char (a.r_dt,'yyyy-mm') as r_dt,b.pic,b.nm,b.price,sum(a.qty) as qty,sum(b.price*a.qty) as sumprice "
					+" from t_purchase a "
					+" inner join t_product b on a.i_product = b.i_product "
					+" where to_char(a.r_dt,'yyyy-mm') = ? and a.i_product = ?"
					+" group by to_char (a.r_dt,'yyyy-mm'),a.i_product,b.pic,b.nm,b.price "
					+" order by a.i_product desc ";
		if(i_product.equals("0")) {
			sql =  " select a.i_product,to_char (a.r_dt,'yyyy-mm') as r_dt,b.pic,b.nm,b.price,sum(a.qty) as qty,sum(b.price*a.qty) as sumprice "
					+" from t_purchase a "
					+" inner join t_product b on a.i_product = b.i_product "
					+" where to_char(a.r_dt,'yyyy-mm') = ? "
					+" group by to_char (a.r_dt,'yyyy-mm'),a.i_product,b.pic,b.nm,b.price "
					+" order by a.i_product desc ";
		}
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, yymm);
			if(!(i_product.equals("0"))) {
			//if(i_product.equals("0")==false){
			ps.setString(2, i_product);
			}
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ShoppingVo vo = new ShoppingVo();
				vo.setR_dt(rs.getString("r_dt"));
				vo.setI_product(rs.getString("i_product"));
				vo.setNm(rs.getString("nm"));
				vo.setPic(rs.getString("pic"));
				vo.setPrice(rs.getString("price"));
				vo.setQty(rs.getString("qty")); //수량
				vo.setSumprice(rs.getString("sumprice")); //총 판매 수량
				psalemon.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		return psalemon;
	}
}
