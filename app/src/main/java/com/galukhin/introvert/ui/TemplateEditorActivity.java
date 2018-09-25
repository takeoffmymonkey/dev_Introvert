package com.galukhin.introvert.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.galukhin.introvert.R;
import com.galukhin.introvert.model.introvert.editor.Template;

public class TemplateEditorActivity extends AppCompatActivity {
    Template template;
    ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_editor);
        root = findViewById(R.id.template_editor_ll);
        template = new Template(root);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_add_element, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_text_field:
                template.addElement(new TextElement(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

/*    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.text_editor_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.text_editor_context_edit:
                return true;
            case R.id.text_editor_context_delete:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }*/
}
