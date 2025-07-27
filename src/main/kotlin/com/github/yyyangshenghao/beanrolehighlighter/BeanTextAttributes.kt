package com.github.yyyangshenghao.beanrolehighlighter

import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.editor.colors.TextAttributesKey
import java.awt.Color
import java.awt.Font

object BeanTextAttributes {
    val SERVICE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("BEAN_HIGHLIGHT_SERVICE")
    val MAPPER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("BEAN_HIGHLIGHT_MAPPER")
}