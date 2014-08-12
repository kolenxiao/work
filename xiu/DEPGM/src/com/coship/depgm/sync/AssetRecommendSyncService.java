package com.coship.depgm.sync;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.coship.depgm.model.AssetRecommend;
import com.coship.mse.core.MseException;
import com.coship.mse.opcode.OpCodeXStreamService;
import com.thoughtworks.xstream.XStream;

/**
 * 上下架信息同步
 * 
 * @author 907900
 * 
 */
@Scope("prototype")
@Component("IEPGM_SYNC_ASSET_RECOMMEND")
public class AssetRecommendSyncService extends OpCodeXStreamService {
	protected List<AssetRecommend> list;

	@Override
	protected void executeService() throws MseException {
		if (list == null || list.size() == 0) {
			return;
		}

		// todo 同步depg
		System.out.println("list.size=" + list.size());
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void init() throws MseException {
		list = (List<AssetRecommend>) getXmlEntity(AssetRecommend.class, new XStreamHandler() {
			@Override
			public void handle(XStream xs) {
				xs.alias("assetRecommendList", ArrayList.class);
				xs.alias("assetRecommend", AssetRecommend.class);
			}
		});
	}

}
