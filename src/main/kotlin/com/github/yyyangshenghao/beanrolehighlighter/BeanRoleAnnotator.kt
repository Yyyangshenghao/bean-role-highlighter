package com.github.yyyangshenghao.beanrolehighlighter

import com.intellij.lang.annotation.*
import com.intellij.psi.*

class BeanRoleAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        // 只处理变量名、字段名
        if (element !is PsiIdentifier) return

        val text = element.text ?: return
        if (text.endsWith("Service")) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.textRange)
                .textAttributes(BeanTextAttributes.SERVICE)
                .create()
            println("Matched: ${element.text}")
        } else if (text.endsWith("Mapper")) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.textRange)
                .textAttributes(BeanTextAttributes.MAPPER)
                .create()
            println("Matched: ${element.text}")
        }
    }
}
