package com.coship.depgm.sync;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.model.Asset;
import com.coship.depgm.model.AssetPackageMap;
import com.coship.mse.core.MseException;
import com.coship.mse.opcode.OpCodeXStreamService;
import com.thoughtworks.xstream.XStream;

@Scope("prototype")
@Component("CMS_SYNC_ASSET_PACKAGE,CMS_DEL_ASSET_PACKAGE,CMS_ADD_ASSET,CMS_UPDATE_ASSET,CMS_DELETE_ASSET")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AssetSyncService extends OpCodeXStreamService {
	protected Asset asset;

	@Autowired
	protected HibernateTemplate database;

	@Override
	protected void executeService() throws MseException {
		if (asset == null) {
			return;
		}
		String opCode = message.getOpCode();

		if ("CMS_DELETE_ASSET".equals(opCode) || "CMS_DEL_ASSET_PACKAGE".equals(opCode)) { // 删除媒资&媒资包
			database.delete(asset);
		} else if ("CMS_ADD_ASSET".equals(opCode) || "CMS_UPDATE_ASSET".equals(opCode)) { // 保存媒资
			database.saveOrUpdate(asset);
		} else if ("CMS_SYNC_ASSET_PACKAGE".equals(opCode)) {// 保存媒资包
			database.saveOrUpdate(asset);
			List<AssetPackageMap> pkgList = asset.getResourceCodeList();
			if (pkgList != null && pkgList.size() > 0) {
				for (AssetPackageMap pkg : pkgList) {
					pkg.setResourcePkgId(asset.getId());
				}
				database.saveOrUpdateAll(pkgList);
			}
		}

	}

	@Override
	protected void init() throws MseException {
		String opCode = this.message.getOpCode();
		if ("CMS_SYNC_ASSET_PACKAGE".equals(opCode)) {
			asset = (Asset) getXmlEntity(Asset.class, new XStreamHandler() {
				@Override
				public void handle(XStream xs) {
					xs.alias("resourcePackage", Asset.class);
					xs.alias("resourceCodeList", ArrayList.class);
					xs.alias("resourceCodeInfo", AssetPackageMap.class);
					xs.aliasField("resourceCode", AssetPackageMap.class, "resourceId");
					xs.aliasField("orderNum", AssetPackageMap.class, "orderNum");
					// Asset映射
					xs.aliasField("resourceCode", Asset.class, "id");
					xs.aliasField("mLName", Asset.class, "mlName");
				}
			});
		} else if ("CMS_DEL_ASSET_PACKAGE".equals(opCode)) {
			asset = (Asset) getXmlEntity(Asset.class, new XStreamHandler() {
				@Override
				public void handle(XStream xs) {
					xs.alias("packageCode", Asset.class);
					// Asset映射
					xs.aliasField("resourceCode", Asset.class, "id");
					xs.aliasField("mLName", Asset.class, "mlName");
				}
			});
		} else if ("CMS_ADD_ASSET".equals(opCode) || "CMS_UPDATE_ASSET".equals(opCode)
				|| "CMS_DELETE_ASSET".equals(opCode)) {
			asset = (Asset) getXmlEntity(Asset.class, new XStreamHandler() {
				@Override
				public void handle(XStream xs) {
					xs.alias("asset", Asset.class);
					xs.aliasField("resourceCode", Asset.class, "id");
					xs.aliasField("mLName", Asset.class, "mlName");
				}
			});
		}
	}

}