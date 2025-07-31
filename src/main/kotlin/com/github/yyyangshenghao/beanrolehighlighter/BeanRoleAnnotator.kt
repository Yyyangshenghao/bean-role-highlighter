package com.github.yyyangshenghao.beanrolehighlighter

import com.intellij.lang.annotation.*
import com.intellij.psi.*

class BeanRoleAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        // 只处理变量名 identifier
        if (element !is PsiIdentifier) return

        val name = element.text
        // 快速短路：不是 Service/Mapper 结尾，直接 return
        if (name.isNullOrEmpty() || !name[0].isLowerCase()) return
        if (!(name.endsWith("Service") || name.endsWith("Mapper"))) return

        val parent = element.parent

        // 声明
        if (parent is PsiField || parent is PsiParameter || parent is PsiLocalVariable) {
            doHighlight(name, element, holder)
            return
        }

        // 引用
        if (parent is PsiReferenceExpression) {
            // 只对 Service/Mapper 风格的变量引用才 resolve，极大减少不必要操作
            val resolved = parent.resolve()
            if (resolved is PsiField || resolved is PsiParameter || resolved is PsiLocalVariable) {
                doHighlight(name, element, holder)
            }
        }
    }

    private fun doHighlight(name: String, element: PsiElement, holder: AnnotationHolder) {
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
