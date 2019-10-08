/*
 * This file is part of possy.
 *
 * possy is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * possy is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with possy. If not, see <http://www.gnu.org/licenses/>.
 */
package net.g24.possy.daemon.templaterenderer

import net.g24.possy.daemon.PrintRequest
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPageContentStream

class StoryTemplateRenderer : TemplateRenderer() {

    override fun render(printRequest: PrintRequest, doc: PDDocument, contents: PDPageContentStream, renderContext: RenderContext) {
        renderIssueFX(printRequest, doc, contents, renderContext)
        renderIssue(printRequest, doc, contents, renderContext)
        renderContent(printRequest, doc, contents, renderContext)
        drawBoldRect(contents, renderContext.width, renderContext.height, renderContext.marginBorder)
        renderWeight(printRequest, doc, contents, renderContext)
        renderTag(printRequest, doc, contents, renderContext)
    }

    private fun renderIssueFX(printRequest: PrintRequest, doc: PDDocument, contents: PDPageContentStream, renderContext: RenderContext) {
        if (printRequest.key.isNullOrBlank()) {
            return
        }

        val parts = printRequest.key.trim().split("-").toTypedArray()
        val text = if (parts.size == 2) parts[1] else parts[0]

        val headerFont = renderContext.headerFont

        contents.setNonStrokingColor(200, 200, 200)
        showTextAt(contents, text, Cursor(renderContext.marginBorder, renderContext.marginBorder), headerFont.applyFont(doc), renderContext.height / 1.5f)
        contents.setNonStrokingColor(0, 0, 0)
    }
}
