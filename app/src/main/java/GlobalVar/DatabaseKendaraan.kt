package GlobalVar

import Model.Kendaraan

class DatabaseKendaraan {
    companion object {
        val STORAGE_PERMISSION_CODE: Int=3
        val listDataKendaraan = ArrayList<Kendaraan>()
        val CAMERA_PERMISSION_CODE = 5
    }
}