package br.com.livroandroid.carros.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    fun validade(activity: Activity, requestCode: Int, vararg permissions: String): Boolean {
        val list = ArrayList<String>()
        for (permission in permissions) {
            // Valida permissão
            val ok = ContextCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_GRANTED
            if (!ok) {
                list.add(permission)
            }
        }
        if (list.isEmpty()) {
            // Tudo ok
            return true
        }
        // Lista de permissões que falta acesso
        val newPermission = arrayOfNulls<String>(list.size)
        list.toArray(newPermission)
        // Solicita permissão
        ActivityCompat.requestPermissions(activity, newPermission, 1)
        return false
    }
}