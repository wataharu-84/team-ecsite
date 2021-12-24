package jp.co.internous.sirius.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.sirius.model.domain.MstDestination;
import jp.co.internous.sirius.model.mapper.MstDestinationMapper;
import jp.co.internous.sirius.model.mapper.TblCartMapper;
import jp.co.internous.sirius.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.sirius.model.session.LoginSession;

/**
 * 決済に関する処理を行うコントローラー
 * @author wataharu-84
 */
@Controller
@RequestMapping("/sirius/settlement")
public class SettlementController {
	
	@Autowired
	private LoginSession loginSession;
	
	private Gson gson = new Gson();
	
	@Autowired
	private MstDestinationMapper destinationMapper;
	
	@Autowired
	private TblCartMapper cartMapper;

	@Autowired
	private TblPurchaseHistoryMapper  purchaseHistoryMapper;
	
	/**
	 * 宛先選択・決済画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return 宛先選択・決済画面
	 */
	@RequestMapping("/")
	public String settlement(Model m) {
		List<MstDestination> destinations = destinationMapper.findByUserId(loginSession.getUserId());
		m.addAttribute("destinations",destinations);
		
		m.addAttribute("loginSession",loginSession);
		
		return "settlement";
	}
	
	/**
	 * 決済処理を行う
	 * @param destinationId 宛先情報id
	 * @return true:決済処理成功、false:決済処理失敗
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/complete")
	public boolean settlementComplete(@RequestBody String destinationId) {
		
		Map<String, String> map = gson.fromJson(destinationId, Map.class);
		String id = map.get("destinationId");
		
		int userId = loginSession.getUserId();
		boolean insertResult = false;
		boolean deleteResult = false;
		
		//　カート情報を購入履歴テーブルへ登録
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("destinationId", id);
		parameter.put("userId", userId);
		
		if(purchaseHistoryMapper.insertCart(parameter)) {
			insertResult = true;
		}
		
		//　登録に成功していたら、カート情報を削除
		if(insertResult) {
			if(cartMapper.deleteCartByUserId(userId)) {
				deleteResult = true;
			}
		}
		return deleteResult;
		
	}

}
