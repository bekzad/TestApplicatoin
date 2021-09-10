package com.bekzad.testapplicatoin.ui.activities


import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import com.bekzad.testapplicatoin.R
import com.bekzad.testapplicatoin.ui.KEY_PERMISSIONS_REQUEST_COUNT
import com.bekzad.testapplicatoin.ui.MAX_NUMBER_REQUEST_PERMISSIONS
import com.bekzad.testapplicatoin.ui.REQUEST_CODE_PERMISSIONS
import com.bekzad.testapplicatoin.ui.gallery.GalleryFragment
import java.util.*

class GalleryActivity : AppCompatActivity() {

    private val permissions = Arrays.asList(
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    private var permissionRequestCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        savedInstanceState?.let {
            permissionRequestCount = it.getInt(KEY_PERMISSIONS_REQUEST_COUNT, 0)
        }

        requestPermissionsIfNecessary()
    }

    /** Save the permission request count on configuration change  */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_PERMISSIONS_REQUEST_COUNT, permissionRequestCount)
    }

    /**
     * Request permission twice - if the user denies twice then show a toast about how to update
     * the permission of storage.
     * If permission is granted the fragment is loaded
     */
    private fun requestPermissionsIfNecessary() {
        if (!checkPermissions()) {
            if (permissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
                permissionRequestCount++
                ActivityCompat.requestPermissions(
                    this,
                    permissions.toTypedArray(),
                    REQUEST_CODE_PERMISSIONS)
            } else {
                Toast.makeText(this, R.string.set_permissions_in_settings,
                Toast.LENGTH_LONG).show()
            }
        } else {
            loadFragment()
        }
    }

    /** Checking permissions */
    private fun checkPermissions(): Boolean =
        ContextCompat.checkSelfPermission(this, permissions[0]) ==
                PackageManager.PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            requestPermissionsIfNecessary()
        }
    }

    /** Loads the fragment on the activity */
    private fun loadFragment() {
        supportFragmentManager.commit {
            replace(R.id.gallery_fragment_container, GalleryFragment.newInstance())
        }
    }

}