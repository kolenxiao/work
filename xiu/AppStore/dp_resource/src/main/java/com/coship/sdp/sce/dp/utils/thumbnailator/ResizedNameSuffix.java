package com.coship.sdp.sce.dp.utils.thumbnailator;

import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.name.Rename;

public class ResizedNameSuffix extends Rename {
	private static final String resizeSuffixTemplate = "_%dx%d";

	@Override
	public String apply(String name, ThumbnailParameter param) {
		if (param == null || param.getSize() == null) {
			return appendSuffix(name,
					String.valueOf(System.currentTimeMillis()));
		} else {
			return appendSuffix(name, String.format(resizeSuffixTemplate,
					param.getSize().width, param.getSize().height));
		}

	}
}
