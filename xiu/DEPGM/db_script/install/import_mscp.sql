#注意修改gz_mscp为实际mscp中间库
truncate t_asset;
truncate t_resource_package_map;
truncate t_resource_poster;
truncate t_column_resource;
DELETE from depg_channel where soutvId is null;
-- 媒资
INSERT into t_asset(id, assetCode, assetENName, assetID, assetName, assetOrigin, assetTypeIds, assetTypes, captionName, chapters, createTime, director, issuerName, keyWord, leadingActor, letter, letterAb, mlName, originName, platform, prize, providerID, publishDate, recommendationLevel, score, screenWriter, series, showType, status, summaryLong, summaryMedium, summaryShort, tag, type, videoType, viewLevel)
SELECT RESOURCE_ID, ASSET_CODE, ASSET_EN_NAME, ASSET_ID, ASSET_NAME, ASSET_TYPE_ID, ASSET_TYPE, CAPTION_CODE, CHAPTERS, ASSET_ORIGIN, CREATE_TIME, DIRECTOR, ISSUER_ID, KEY_WORD, LEADING_ACTOR, ASSET_PINYIN_NAME, ASSET_HEAD_PINYIN_NAME, LANGUAGE_CODE, ORIGIN_CODE, PLATFORM, PRIZE, PROVIDER_CODE, PUBLISH_DATE, RECOMMENDATION_LEVEL, SCORE, SCREENWRITER, SERIES, SHOW_TYPE, STATUS, SUMMARY_LONG, SUMMARY_MEDIUM, SUMMARY_SHORT, TAG, TYPE, VIDEO_TYPE, VIEW_LEVEL FROM gz_mscp.t_asset_r;

-- 媒资包关系
INSERT INTO t_resource_package_map ( id, resourcePkgId, resourceId, orderNum ) 
SELECT CONCAT( RESOURCE_ID_PKG, "_", RESOURCE_ID ), RESOURCE_ID_PKG, RESOURCE_ID, ORDER_NUM FROM gz_mscp.t_bms_resource_pkg_map_r;

-- 海报
INSERT INTO t_resource_poster ( id, assetID, fileName, fileSize, height, localPath, platform, posterType, resourceId, sourceURL, STATUS, uploadTime, width ) 
SELECT POSTER_ID, ASSET_ID, FILE_NAME, FILE_SIZE, HEIGHT, LOCAL_PATH, PLATFORM, POSTER_TYPE, SOURCE_ID, SOURCE_URL, STATUS, UPLOAD_TIME, WIDTH FROM gz_mscp.t_resource_poster;

-- 上下架关系
INSERT into t_column_resource(id,columnId,resourceId,resourceType,rank)
SELECT COLUMN_REF_RESOURCE_ID,COLUMN_ID,RESOURCE_ID,RESOURCE_TYPE,RESOURCE_RANK from gz_mscp.t_res_column_map;

-- 频道
INSERT INTO depg_channel ( id, mscpId, NAME, networkID, tsID, serviceID, videoType, btv ) 
SELECT c.RESOURCE_ID, c.CHANNEL_ID, c.CHANNEL_NAME, 0, c.TS_ID, c.SERVICE_ID, c.VIDEO_TYPE, IF(count(f.FEATURE_CODE) > 0, 1, 0) 
FROM gz_mscp.t_channel c LEFT JOIN gz_mscp.t_ch_feature_list f ON c.RESOURCE_ID = f.RESOURCE_ID AND f.FEATURE_CODE = "btv" GROUP BY c.RESOURCE_ID;

commit;