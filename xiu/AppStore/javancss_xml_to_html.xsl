<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:element name="table">
			<xsl:attribute name="border">
                0
            </xsl:attribute>
			<xsl:attribute name="cellspacing">
                <xsl:value-of select="0" />
            </xsl:attribute>
			<xsl:attribute name="cellpadding">
                <xsl:value-of select="0" />
            </xsl:attribute>
			<xsl:attribute name="width">
               	95%
            </xsl:attribute>
			<xsl:attribute name="valign">
               	center
            </xsl:attribute>

			<!-- <tr>
				<td height="25" width="40%" align="center">
					package name
				</td>
				<td align="center">
					ncss
				</td>

				<td align="center">
					ccn
				</td>
				<td align="center">
					javadocs
				</td>

			</tr> -->

			<xsl:for-each select="javancss/functions/function">
				<tr>
					<td height="25" align="center" width="45%" style="border-left:1px solid #888888;border-top:1px solid #888888;">
						<xsl:value-of select="name" />
					</td>
					<td align="center" width="20%" style="border-left:1px solid #888888;border-top:1px solid #888888;">
						<xsl:value-of select="ncss" />
					</td>

					<td align="center" width="20%" style="border-left:1px solid #888888;border-top:1px solid #888888;">
						<xsl:value-of select="ccn" />
					</td>
					<td align="center" style="border-left:1px solid #888888;border-top:1px solid #888888;border-right:1px solid #888888;">
						<xsl:value-of select="javadocs" />
					</td>

				</tr>
			</xsl:for-each>
		</xsl:element>

	</xsl:template>
</xsl:stylesheet>