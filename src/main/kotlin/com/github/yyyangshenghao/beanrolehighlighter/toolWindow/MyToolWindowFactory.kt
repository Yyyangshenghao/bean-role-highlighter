package com.github.yyyangshenghao.beanrolehighlighter.toolWindow

import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.github.yyyangshenghao.beanrolehighlighter.MyBundle
import com.github.yyyangshenghao.beanrolehighlighter.services.MyProjectService
import java.awt.Dimension
import java.awt.Font
import java.awt.Insets
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField


class MyToolWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        // 用于存储当前 ToDo 列表（如需持久化可升级到 ProjectService）
        private val todos = mutableListOf<String>()

        fun getContent(): JPanel {
            return JBPanel<JBPanel<*>>().apply {
                layout = BoxLayout(this, BoxLayout.Y_AXIS)

                val title = JLabel("📝 ToDo List")
                title.font = Font(title.font.name, Font.BOLD, 16)
                add(title)
                add(Box.createVerticalStrut(8))

                val inputPanel = JPanel().apply {
                    layout = BoxLayout(this, BoxLayout.X_AXIS)
                }
                val inputField = JTextField(16)
                val addButton = JButton("添加")
                inputPanel.add(inputField)
                inputPanel.add(Box.createHorizontalStrut(8))
                inputPanel.add(addButton)
                add(inputPanel)
                add(Box.createVerticalStrut(8))

                val todoListPanel = JPanel().apply {
                    layout = BoxLayout(this, BoxLayout.Y_AXIS)
                }
                val scrollPane = JScrollPane(todoListPanel)
                scrollPane.preferredSize = Dimension(260, 220)
                add(scrollPane)

                // 刷新列表
                fun refreshList() {
                    todoListPanel.removeAll()
                    todos.forEachIndexed { idx, todo ->
                        val rowPanel = JPanel().apply {
                            layout = BoxLayout(this, BoxLayout.X_AXIS)
                            maximumSize = Dimension(Int.MAX_VALUE, 28)
                        }
                        val label = JLabel(todo)
                        val delBtn = JButton("删除").apply {
                            margin = Insets(2, 8, 2, 8)
                            addActionListener {
                                todos.removeAt(idx)
                                refreshList()
                            }
                        }
                        rowPanel.add(label)
                        rowPanel.add(Box.createHorizontalGlue())
                        rowPanel.add(delBtn)
                        todoListPanel.add(rowPanel)
                    }
                    todoListPanel.revalidate()
                    todoListPanel.repaint()
                }

                addButton.addActionListener {
                    val text = inputField.text.trim()
                    if (text.isNotEmpty()) {
                        todos.add(text)
                        inputField.text = ""
                        refreshList()
                    }
                }

                inputField.addActionListener {
                    addButton.doClick()
                }

                refreshList()
            }
        }
    }
}
