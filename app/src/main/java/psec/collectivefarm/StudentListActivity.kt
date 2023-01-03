package psec.collectivefarm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import psec.collectivefarm.database.adapter.StudentWithBucketsAdapter
import psec.collectivefarm.databinding.ActivityStudentListBinding

class StudentListActivity : AppCompatActivity() {
    private var _binding: ActivityStudentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: StudentWithBucketsAdapter

    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory(
            (this.application as MyApplication).database.getStudentsDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = StudentWithBucketsAdapter {
            viewModel.updateStudent(it)
        }

        binding.recyclerViewStudent.adapter = adapter

        lifecycleScope.launch(
            Dispatchers.Main
        ) {
            viewModel.students.collect {
                adapter.updateStudentWithBuckets(it)
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewStudent)

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
            val position = viewHolder.adapterPosition
            val currentStudent = adapter.studentsWithBuckets[position]

            when (direction) {
                ItemTouchHelper.LEFT -> {
                    AlertDialog.Builder(this@StudentListActivity).also {
                        it.setTitle("Вы действительно хотите удалить этого студента?")
                        it.setPositiveButton("Да") { dialog, which ->
                            viewModel.deleteStudent(currentStudent.student)
                            binding.recyclerViewStudent.adapter?.notifyItemRemoved(position)
                            Toast.makeText(
                                this@StudentListActivity,
                                "Студент успешно удалён",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        it.setNegativeButton("Нет") { _, _ -> }
                    }.create().show()
                }
            }
            binding.recyclerViewStudent.adapter?.notifyDataSetChanged()
        }
    }
}