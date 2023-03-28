package ph.kodego.aragon.janreign.activity_12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.aragon.janreign.activity_12.adapter.StudentAdapter
import ph.kodego.aragon.janreign.activity_12.databinding.FragmentSearchBinding
import ph.kodego.aragon.janreign.activity_12.viewpager.ViewPagerActivity

class SearchFragment(var viewPagerActivity: ViewPagerActivity) : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent()
    }
    private fun initComponent() {
        studentAdapter = StudentAdapter(viewPagerActivity.students, viewPagerActivity)

        binding.list.layoutManager = LinearLayoutManager(viewPagerActivity)
        binding.list.adapter = studentAdapter

        binding.list.adapter!!.notifyDataSetChanged()
        studentAdapter!!.notifyDataSetChanged()

        binding.searchStudentRecords.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                viewPagerActivity.students = viewPagerActivity.studentDAO.searchStudentByLastName(newText!!)
                studentAdapter.updateStudents(viewPagerActivity.students)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                studentAdapter.filter.filter(query)
                return false
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}