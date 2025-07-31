package com.github.yyyangshenghao.beanrolehighlighter

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.PlainTextLanguage
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class BeanHighlightColorSettingsPage : ColorSettingsPage {
    private val DESCRIPTORS = arrayOf(
        AttributesDescriptor("Service Bean", BeanTextAttributes.SERVICE),
        AttributesDescriptor("Mapper Bean", BeanTextAttributes.MAPPER),
    )

    override fun getDisplayName(): String = "Bean Role Highlighter"

    override fun getIcon(): Icon? = null

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getHighlighter(): SyntaxHighlighter {
        // 使用 PlainText 作为占位高亮器
        return SyntaxHighlighterFactory.getSyntaxHighlighter(PlainTextLanguage.INSTANCE, null, null)
    }

    override fun getDemoText(): String = "<service>userService</service>: UserService\n<mapper>userMapper</mapper>: UserMapper"

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> = mapOf(
        "service" to BeanTextAttributes.SERVICE,
        "mapper" to BeanTextAttributes.MAPPER,
    )
}