package com.gz.complain.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.gz.common.JDBCTemplate;
import com.gz.common.model.vo.PageInfo;
import com.gz.post.model.dao.PostDao;
import com.gz.complain.model.vo.ComAttachment;
import com.gz.complain.model.vo.Complain;

public class ComplainDao {
	
	private Properties prop = new Properties();
	
	public ComplainDao() {
	
		try {
			String filePath = ComplainDao.class.getResource("/db/sql/complain-mapper.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
	      }
	}
	
	//신고/건의 게시글 조회
	
		public int listCount(Connection conn) {
			
			int count = 0;
		    ResultSet rset = null; 
		    Statement stmt = null; 
	
		    String sql = prop.getProperty("listCount");
	
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(sql);
				
				  if (rset.next()) {
			            // 조회된 게시글 개수
			            count = rset.getInt("COUNT");
			         }
			      } catch (SQLException e) {
			         // TODO Auto-generated catch block
			         e.printStackTrace();
			      } finally {
			         JDBCTemplate.close(rset);
			         JDBCTemplate.close(stmt);
			      }
			      return count;
	
			   
	
	}
			public ArrayList<Complain> selectComplainList(Connection conn, PageInfo pi) {

				ArrayList<Complain> list = new ArrayList<>();
				ResultSet rset = null;
				PreparedStatement pstmt = null;
				String sql = prop.getProperty("selectComplainList");
				
				int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
				int endRow = pi.getCurrentPage()*pi.getBoardLimit();
			      
			      try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					
					rset = pstmt.executeQuery();
					
					while(rset.next()) {
						list.add(new Complain(rset.getInt("COMPLAIN_NO")
											,rset.getString("COMPLAIN_CATEGORY")
											,rset.getString("COMPLAIN_TITLE")
											,rset.getString("MEMBER_NICKNAME")
											,rset.getInt("COUNT")
											,rset.getDate("COMPLAIN_DATE")
											));
								
			
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
				
				
				
				return list;
			}
			
			//신고,건의 상세조회
			
			public Complain selectComplain(Connection conn, int complainNo) {
				
				ResultSet rset = null;
				
				PreparedStatement pstmt = null;
				
				Complain c = null;
				
				String sql = prop.getProperty("selectComplain");
				
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, complainNo);
					
					rset = pstmt.executeQuery();
					
					if(rset.next()) {
						
						c = new Complain(rset.getInt("COMPLAIN_NO")
										,rset.getString("COMPLAIN_TITLE")
										,rset.getString("COMPLAIN_CATEGORY")
										,rset.getNString("COMPLAIN_CONTENT")
										,rset.getString("COMPLAIN_WRITER")
										,rset.getInt("COUNT")
										,rset.getDate("COMPLAIN_DATE")
										,rset.getString("STATUS"));
						
					}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
					
				}
				
				
				
				
				
				return c;
			}
			
			
			//건의사항 조회수 증가
			public int increaseCount2(int complainNo, Connection conn) {
				int result = 0;
				PreparedStatement pstmt = null;
				String sql = prop.getProperty("increaseCount2");
				
				try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, complainNo);
					
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(pstmt);
				}
				return result;
			}
			
			
			//건의사항 첨부파일 조회
			
			public ComAttachment selectAttachment2(Connection conn, int complainNo) {
		         ResultSet rset = null;  
		         PreparedStatement pstmt = null;
		         ComAttachment at = null;
		         String sql = prop.getProperty("selectAttachment2");
		         
		         try {
		             pstmt = conn.prepareStatement(sql);
		             pstmt.setInt(1,complainNo);
		             
		             rset = pstmt.executeQuery();
		               if (rset.next()) {
		                     at = new ComAttachment();
		                     at.setFileNo(rset.getInt("FILE_NO"));
		                     at.setOriginName(rset.getString("ORIGIN_NAME"));
		                     at.setChangeName(rset.getString("CHANGE_NAME"));
		                     at.setFilePath(rset.getString("FILE_PATH"));
		                 } 
		         } catch (SQLException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }finally {
		             JDBCTemplate.close(rset);
		             JDBCTemplate.close(pstmt);
		         }
		         return at;
			}
			
			
			//건의사항 글작성
			public int insertComplain(Connection conn, Complain c) {
				int result = 0;
			    PreparedStatement pstmt = null;
			    ResultSet rset = null;
			    String sql = prop.getProperty("insertComplain");
			    
			    try {
			        pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1, c.getComplainCategory());
			        pstmt.setString(2, c.getComplainTitle());
			        pstmt.setString(3, c.getComplainContent());
			        pstmt.setString(4, c.getComplainWriter());

			        result = pstmt.executeUpdate();

			 
			    } catch (SQLException e) {
			        e.printStackTrace();
			    } finally {
			        JDBCTemplate.close(rset);
			        JDBCTemplate.close(pstmt);
			    }
			    return result;
			}
			
			
			//건의사항 첨부파일등록
			public int insertAttachment2(Connection conn, ComAttachment at) {
				 
				 int result = 0;
		         PreparedStatement pstmt = null;
		         String sql = prop.getProperty("insertAttachment2");
		         
		         try {
		        	 
		        	 
		        	 
		             pstmt = conn.prepareStatement(sql);
		             
		             pstmt.setString(1, at.getOriginName());
		             pstmt.setString(2, at.getChangeName());
		             pstmt.setString(3, at.getFilePath());
		             
		             result = pstmt.executeUpdate();
		             
		         } catch (SQLException e) {
		             e.printStackTrace();
		         } finally {
		             JDBCTemplate.close(pstmt);
		         }
		         return result;
			}
			
			//건의사항 수정
			public int updateComplain(Connection conn, Complain c) {
				PreparedStatement pstmt = null;
				String sql = prop.getProperty("updateComplain");
				int result = 0;
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, c.getComplainCategory());
					pstmt.setString(2, c.getComplainTitle());
					pstmt.setString(3, c.getComplainContent());
					pstmt.setInt(4, c.getComplainNo());
					
					result = pstmt.executeUpdate();
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return result;
			}
			
			
			//건의사항 삭제
			
			public int delectComplain(Connection conn, int complainNo) {
				
				int result = 0;
				PreparedStatement pstmt = null;
				String sql = prop.getProperty("deleteComplain");
				
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, complainNo);
					result = pstmt.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return result;
			}
			
			//건의사항 첨부파일 수정메소드
			public int updateAttachment2(Connection conn, ComAttachment at) {
			       // DML (UPDATE)
			       int result = 0;
			       PreparedStatement pstmt = null;
			       String sql = prop.getProperty("updateAttachment2");

			       try {
			          pstmt = conn.prepareStatement(sql);
			          pstmt.setString(1, at.getOriginName());
			          pstmt.setString(2, at.getChangeName());
			          pstmt.setString(3, at.getFilePath());
			          pstmt.setInt(4, at.getFileNo());

			          result = pstmt.executeUpdate();
			       } catch (SQLException e) {
			          e.printStackTrace();
			       } finally {
			          JDBCTemplate.close(pstmt);
			       }
			       return result;
			    }
			
			 //기존게시글에 첨부파일 추가메소드
		     public int insertNewAttachment2(Connection conn, ComAttachment at) {
		         //DML ( INSERT )
		         int result = 0;
		         PreparedStatement pstmt = null;
		         String sql = prop.getProperty("insertNewAttachment2");
		         
		         try {
		             pstmt = conn.prepareStatement(sql);
		         
//		             pstmt.setInt(1, at.getFileNo());
		             pstmt.setInt(1, at.getRefBno());
		             pstmt.setString(2, at.getOriginName());
		             pstmt.setString(3, at.getChangeName());
		             pstmt.setString(4, at.getFilePath());
		         
		             result = pstmt.executeUpdate();
		         
		         } catch (SQLException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }finally {
		             JDBCTemplate.close(pstmt);
		         }
		         
		         return result;
		     }

				

}
