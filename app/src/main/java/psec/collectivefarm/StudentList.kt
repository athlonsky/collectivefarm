package psec.collectivefarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import psec.collectivefarm.database.adapter.StudentWithBucketsAdapter
import psec.collectivefarm.databinding.ActivityStudentListBinding

class StudentList : Fragment() {
    private var _binding: ActivityStudentListBinding? = null
    private val binding get() = _binding!!

    private val adapter = StudentWithBucketsAdapter()

    private lateinit var viewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ActivityStudentListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)

        return binding.root
    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT.or(
            ItemTouchHelper.RIGHT
        )
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            var position = viewHolder.adapterPosition
            var currentStudent = adapter.student[position]

            when (direction) {
                ItemTouchHelper.LEFT -> {
                    AlertDialog.Builder(requireContext()).also {
                        it.setTitle("Вы действительно хотите удалить этого клиента?")
                        it.setPositiveButton("Да") { dialog, which ->
                            viewModel.deleteStudent(currentStudent)
                            binding.recyclerViewStudent.adapter?.notifyItemRemoved(position)
                            Toast.makeText(context, "Клиент успешно удалён", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }.create().show()
                }
            }

            binding.recyclerViewStudent.adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}