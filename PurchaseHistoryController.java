package jp.co.internous.sirius.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.sirius.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.sirius.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.sirius.model.session.LoginSession;

/**
 * 購入履歴情報に関する処理を行うコントローラー
 * @author wataharu-84
 */
@Controller
@RequestMapping("/sirius/history")
public class PurchaseHistoryController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblPurchaseHistoryMapper PurchaseHistoryMapper;
	
	/**
	 * 購入履歴画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return 購入履歴画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		int userId = loginSession.getUserId();
		List<PurchaseHistoryDto> historyList = PurchaseHistoryMapper.findByUserId(userId);
		
		m.addAttribute("historyList",historyList);	
		
		m.addAttribute("loginSession",loginSession);
		
		return "purchase_history";
	}
	
	/**
	 * 購入履歴情報を論理削除する
	 * @return true:削除成功、false:削除失敗
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public boolean deleteHistory() {
		int userId = loginSession.getUserId();
		
		return PurchaseHistoryMapper.deleteHistoryByUserId(userId);
	}
}
