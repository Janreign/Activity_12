package ph.kodego.aragon.janreign.activity_12

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.aragon.janreign.activity_12.adapter.StudentAdapter
import ph.kodego.aragon.janreign.activity_12.databinding.FragmentListBinding
import ph.kodego.aragon.janreign.activity_12.viewpager.ViewPagerActivity

class ListFragment(var viewPagerActivity: ViewPagerActivity) : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var studentAdapter: StudentAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
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

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}

