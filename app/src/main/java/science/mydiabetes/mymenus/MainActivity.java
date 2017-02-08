package science.mydiabetes.mymenus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int ID_MENU_EXIT = 1;
    private static final int MENU_ITEM_ITEM1 = 2;
    private CheckBox chb1, chb2;
    private TextView tw;
    private SubMenu subMenu;

    //    @Override

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hello:
                showPopup(v);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tw = (TextView) findViewById(R.id.hello);
        tw.setOnClickListener(this);
        chb1 = (CheckBox) findViewById(R.id.checkBox1);
        chb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                Log.d("MyCheck", " isCheck " + isCheck);
            }
        });
        chb2 = (CheckBox) findViewById(R.id.checkBox2);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        tw = (TextView) findViewById(R.id.hello);
        if (chb1.isChecked()) {
            Toast.makeText(getApplicationContext(), "checkbox 1 Selected", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "checkbox 2 Selected", Toast.LENGTH_LONG).show();
        }

        menu.setGroupVisible(R.id.menuGroup, chb1.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //the menu option text is defined in resources
        super.onCreateOptionsMenu(menu);
        menu = menu.addSubMenu(0, 0, 0, null);
        menu.add("item 1").setIcon(R.mipmap.ic_launcher);
        menu.add("item 2").setIcon(R.mipmap.ic_launcher);
        MenuItem mi = subMenu.getItem();
        mi.setIcon(R.mipmap.ic_launcher);
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        dma.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        //get a SubMenu reference
        SubMenu sm = menu.addSubMenu("Options...");
        //add menu items to the submenu
        MenuItem ssmi = sm.add("Theme");
        ssmi.setIcon(R.drawable.ic_launcher);
        sm.add("Settings");

        //it is better to use final variables for IDs than constant values
        //menu.add(Menu.NONE,1,Menu.NONE,"Exit");

        //get the MenuItem reference
        MenuItem item = menu.add(Menu.NONE,ID_MENU_EXIT,Menu.NONE,R.string.exitOption);
        item.setIcon(R.drawable.ic_launcher);
        //set the shortcut
        item.setShortcut('5', 'x');

        //the menu option text is defined as constant String
        menu.add("Restart");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
//                Toast.makeText(getApplicationContext(),"Item 1 Selected", Toast.LENGTH_LONG).show();
                showPopup(null);
                return true;
            case R.id.help:
                Toast.makeText(getApplicationContext(),"Item 2 Selected", Toast.LENGTH_LONG).show();
                return true;
            case ID_MENU_EXIT:
                this.recreate();
                return true;
            case MENU_ITEM_ITEM1:
                Toast.makeText(getApplicationContext(),"Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showPopup(final View anchor) {
        PopupMenu popupMenu = new PopupMenu(anchor.getContext(), anchor);
        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu1, Menu.NONE, "slot1");
        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu1, Menu.NONE,"slot2");
        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu1, Menu.NONE,"slot3").setIcon(R.drawable.ic_launcher);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(anchor.getContext(), item.getTitle() + "clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popupMenu.show();
    }
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//    }
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId()) {
//            case R.id.new_game:
//                System.out.println("*/*/*/*/*/*/*/*/*/*/*/");
//                return true;
//            case R.id.help:
//                System.out.println("*/*/*/*/*/*/*/*/*/*/*/");
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }
}
