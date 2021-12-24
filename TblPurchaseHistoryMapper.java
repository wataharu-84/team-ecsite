package jp.co.internous.sirius.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.sirius.model.domain.dto.PurchaseHistoryDto;

/**
 * 決済に関する処理を行うコントローラー
 * @author wataharu-84
 */
@Mapper
public interface TblPurchaseHistoryMapper {
	
	/**
	 * 購入履歴情報を登録する
	 * @param parameter パラメーター(ユーザーID、宛先情報ID)
	 * @return 登録件数
	 */
	boolean insertCart(
			Map<String, Object> parameter);
	
	/**
	 * ユーザーIDを条件に購入履歴情報を取得する
	 * @param userId ユーザーID
	 * @return 購入履歴情報リスト
	 */
	List<PurchaseHistoryDto> findByUserId(
			@Param("userId") int userId);
	
	/**
	 * ユーザーIDを条件に購入履歴情報を論理削除する
	 * @param userId ユーザーID
	 * @return true:削除成功、false:削除失敗
	 */
	@Update("UPDATE tbl_purchase_history h SET h.status = 0, updated_at = now()"
			+ "WHERE h.user_id = #{userId}")
	boolean deleteHistoryByUserId(@Param("userId")int userId);
	
}
