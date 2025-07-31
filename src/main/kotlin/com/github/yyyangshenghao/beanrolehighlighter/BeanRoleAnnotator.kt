package com.github.yyyangshenghao.beanrolehighlighter

import com.intellij.lang.annotation.*
import com.intellij.psi.*

class BeanRoleAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        // 只处理变量名 identifier
        if (element !is PsiIdentifier) return

        val parent = element.parent
        // 先判断 parent 类型，快速过滤
        if (parent is PsiField || parent is PsiParameter || parent is PsiLocalVariable) {
            val name = element.text
            // 再判断首字母是否小写
            if (name.isNotEmpty() && name[0].isLowerCase()) {
                when {
                    name.endsWith("Service") -> {
                        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(element.textRange)
                            .textAttributes(BeanTextAttributes.SERVICE)
                            .create()
                    }
                    name.endsWith("Mapper") -> {
                        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                            .range(element.textRange)
                            .textAttributes(BeanTextAttributes.MAPPER)
                            .create()
                    }
                }
            }
        }
    }

}
