package psec.collectivefarm.database.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import psec.collectivefarm.database.entity.StudentWithBuckets
import psec.collectivefarm.databinding.RecyclerViewStudentBinding

class StudentWithBucketsAdapter(
    private val onItemChanged: (StudentWithBuckets) -> Unit
) : RecyclerView.Adapter<StudentWithBucketsAdapter.ViewHolder>() {
    var studentsWithBuckets = mutableListOf<StudentWithBuckets>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerViewStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewName.text = studentsWithBuckets[position].student.name
        holder.binding.textViewValue.text = studentsWithBuckets[position].bucket.value.toString()

        holder.binding.incrementBucketButton.setOnClickListener {
            studentsWithBuckets[position].bucket.value++
            notifyItemChanged(position)
            onItemChanged(
                studentsWithBuckets[position]
            )
        }

        holder.binding.decrementBucketButton.setOnClickListener {
            if (studentsWithBuckets[position].bucket.value > 0) {
                studentsWithBuckets[position].bucket.value--
                notifyItemChanged(position)
                onItemChanged(
                    studentsWithBuckets[position]
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return studentsWithBuckets.size
    }

    inner class ViewHolder(val binding: RecyclerViewStudentBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateStudentWithBuckets(students: List<StudentWithBuckets>) {
        // remove items that are not in the new list and notify the adapter
        val iterator = studentsWithBuckets.iterator()
        while (iterator.hasNext()) {
            val student = iterator.next()
            if (!students.contains(student)) {
                val position = studentsWithBuckets.indexOf(student)
                iterator.remove()
                notifyItemRemoved(position)
            }
        }

        // add items that are not in the old list and notify the adapter
        students.forEach { student ->
            if (!studentsWithBuckets.contains(student)) {
                studentsWithBuckets.add(student)
                notifyItemInserted(studentsWithBuckets.indexOf(student))
            }
        }

        // update items that are in both lists and notify the adapter
        students.forEach { student ->
            if (
                studentsWithBuckets.find { it.student == student.student }?.bucket != student.bucket
//                    .any { it.student == student.student && it.bucket != student.bucket }
            ) {
                val index = studentsWithBuckets.indexOf(student)
                studentsWithBuckets[index] = student
                notifyItemChanged(index)
            }
        }

        // sort quizzes by id
        studentsWithBuckets.sortBy { it.student.id }

//        studentsWithBuckets = students.toMutableList()
//        notifyDataSetChanged()
    }
}