package ph.kodego.aragon.janreign.activity_12.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import ph.kodego.aragon.janreign.activity_12.ListFragment
import ph.kodego.aragon.janreign.activity_12.R
import ph.kodego.aragon.janreign.activity_12.SearchFragment
import ph.kodego.aragon.janreign.activity_12.TransactionFragment
import ph.kodego.aragon.janreign.activity_12.dao.StudentDAO
import ph.kodego.aragon.janreign.activity_12.dao.StudentDAOSQLImpl
import ph.kodego.aragon.janreign.activity_12.databinding.ActivityViewPagerBinding
import ph.kodego.aragon.janreign.activity_12.model.Student

class ViewPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPagerBinding
    var students: ArrayList<Student> = ArrayList()
    lateinit var studentDAO: StudentDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
    }

    private fun initComponents() {
        studentDAO = StudentDAOSQLImpl(applicationContext)
        students =  studentDAO.getStudents()

        var fragmentAdapter = FragmentAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragement(ListFragment(this))
        fragmentAdapter.addFragement(SearchFragment(this))
        fragmentAdapter.addFragement(TransactionFragment(this))

        with(binding.viewPager2) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
//            setPageTransformer(ZoomOutPageTransformer())
            adapter = fragmentAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2){
                tab, position ->
        }.attach()
        with(binding.tabLayout) {
            getTabAt(0)!!.text = "LIST"
            getTabAt(1)!!.text = "SEARCH"
            getTabAt(2)!!.text = "TRANSACTION"
        }
    }

    override fun onBackPressed(){
        if(binding.viewPager2.currentItem == 0){
            super.onBackPressed()
        }else{
            binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
        }
    }
}
