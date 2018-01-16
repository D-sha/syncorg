
import android.widget.TextView
import android.R.attr.button
import android.R.attr.setupActivity
import android.widget.Button
import com.coste.syncorg.MainActivity
import com.coste.syncorg.R
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals


/**
 * Created by dean on 19/10/17.
 */
class WebDavWizard {
    @RunWith(RobolectricTestRunner::class)
    inner class MyActivityTest {

        @Test
        @Throws(Exception::class)
        fun clickingButton_shouldChangeResultsViewText() {
            val activity = Robolectric.setupActivity(MainActivity::class.java)

            val button = activity.findViewById(R.id.wizard_webdav_login_button) as Button
            val username = activity.findViewById(R.id.wizard_webdav_username) as TextView
            val password = activity.findViewById(R.id.wizard_webdav_password) as TextView
            val URL = activity.findViewById(R.id.wizard_webdav_url) as TextView

            button.performClick()


        }
    }

}