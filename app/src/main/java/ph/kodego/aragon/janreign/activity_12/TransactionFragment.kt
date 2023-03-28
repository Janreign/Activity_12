package ph.kodego.aragon.janreign.activity_12

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.aragon.janreign.activity_12.adapter.StudentAdapter
import ph.kodego.aragon.janreign.activity_12.databinding.FragmentSearchBinding
import ph.kodego.aragon.janreign.activity_12.databinding.FragmentTransactionBinding
import ph.kodego.aragon.janreign.activity_12.viewpager.ViewPagerActivity

class TransactionFragment(var viewPagerActivity: ViewPagerActivity) : Fragment() {
    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!
    private lateinit var studentAdapter: StudentAdapter

    val courses = arrayOf("SELECT", "Mobile Development", "Web Development", "Graphic Design")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentAdapter = StudentAdapter(viewPagerActivity.students, viewPagerActivity)

        binding.list.layoutManager = LinearLayoutManager(viewPagerActivity)
        binding.list.adapter = studentAdapter

        binding.list.adapter!!.notifyDataSetChanged()
        studentAdapter!!.notifyDataSetChanged()

        val arrayAdapter = ArrayAdapter<String>(viewPagerActivity, android.R.layout.simple_spinner_dropdown_item,courses)
        binding.spinner.adapter = arrayAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                if (position > 0) {
                    viewPagerActivity.students =
                        viewPagerActivity.studentDAO.searchStudentByCourse(courses[position]!!)
                    studentAdapter.updateStudents(viewPagerActivity.students)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                studentAdapter.filter.filter("")
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}