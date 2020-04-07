package org.totschnig.myexpenses.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import org.totschnig.myexpenses.R
import org.totschnig.myexpenses.fragment.ACTION_MANAGE
import org.totschnig.myexpenses.fragment.ACTION_SELECT_FILTER
import org.totschnig.myexpenses.fragment.ACTION_SELECT_MAPPING
import org.totschnig.myexpenses.fragment.TagList

class ManageTags: ProtectedFragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(themeIdEditDialog)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tags)
        setupToolbar(true)
        configureFloatingActionButton(R.string.content_description_tags_confirm)
        val action = intent?.action ?: ACTION_SELECT_MAPPING
        setTitle(when(action) {
            ACTION_MANAGE -> R.string.tags
            ACTION_SELECT_FILTER -> R.string.search_tag
            else -> R.string.tags_create_or_select
        })
        if (action == ACTION_MANAGE) {
            floatingActionButton.visibility = View.GONE
        } else {
            floatingActionButton.setImageResource(R.drawable.ic_menu_done)
        }
    }

    override fun dispatchCommand(command: Int, tag: Any?): Boolean {
        if (command == R.id.CREATE_COMMAND) {
            setResult(RESULT_OK, (supportFragmentManager.findFragmentById(R.id.tag_list) as TagList).resultIntent())
            finish()
        }
        return super.dispatchCommand(command, tag)
    }

    override fun doHome() {
        setResult(FragmentActivity.RESULT_CANCELED, (supportFragmentManager.findFragmentById(R.id.tag_list) as TagList).cancelIntent())
        finish()
    }

    override fun onBackPressed() {
        doHome()
    }
}